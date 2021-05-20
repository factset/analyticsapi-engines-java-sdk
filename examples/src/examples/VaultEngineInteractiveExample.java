package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.ClientBuilder;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientProperties;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.models.CalculationMeta.ContentorganizationEnum;
import factset.analyticsapi.engines.models.CalculationMeta.ContenttypeEnum;

import com.factset.protobuf.stach.extensions.ColumnStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.StachExtensions;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class VaultEngineInteractiveExample {

  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = "https://api.factset.com";
  private static String USERNAME = "<username-serial>";
  private static String PASSWORD = "<apiKey>";
  private static String VAULT_DEFAULT_DOCUMENT = "Client:/aapi/VAULT_QA_PI_DEFAULT_LOCKED";
  private static String VAULT_DEFAULT_ACCOUNT = "CLIENT:/BISAM/REPOSITORY/QA/SMALL_PORT.ACCT";
  private static String COMPONENT_NAME = "Average\r\nWeight";
  private static String COMPONENT_CATEGORY = "Performance / 4 Tiles Calculate";
  private static Integer DEADLINE_HEADER_VALUE = 20;

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      // Build Vault Calculation Parameters List

      // Get all component from VAULT_DEFAULT_DOCUMENT with Name COMPONENT_NAME & category COMPONENT_CATEGORY
      ComponentsApi componentsApi = new ComponentsApi(getApiClient());
      Map<String, ComponentSummary> components = componentsApi.getVaultComponents(VAULT_DEFAULT_DOCUMENT).getData();
      String componentId = components.entrySet().stream().filter(
          c -> c.getValue().getName().equals(COMPONENT_NAME) && c.getValue().getCategory().equals(COMPONENT_CATEGORY))
          .iterator().next().getKey();
      System.out.println("ID of component with Name '" + COMPONENT_NAME + "' and category '" + COMPONENT_CATEGORY
          + "' : " + componentId);

      ConfigurationsApi configurationsApi = new ConfigurationsApi(getApiClient());
      Map<String, VaultConfigurationSummary> configurationsMap = configurationsApi
          .getVaultConfigurations(VAULT_DEFAULT_ACCOUNT).getData();
      String configurationId = configurationsMap.entrySet().iterator().next().getKey();

      VaultCalculationParameters vaultItem = new VaultCalculationParameters();
      VaultCalculationParametersRoot calcParameters = new VaultCalculationParametersRoot();

      vaultItem.setComponentid(componentId);
      vaultItem.setConfigid(configurationId);

      VaultIdentifier account = new VaultIdentifier();
      account.setId(VAULT_DEFAULT_ACCOUNT);
      vaultItem.setAccount(account);

      VaultDateParameters dateParameters = new VaultDateParameters();
      dateParameters.setStartdate("20180101");
      dateParameters.setEnddate("20180331");
      dateParameters.setFrequency("Monthly");
      vaultItem.setDates(dateParameters);
      vaultItem.setComponentdetail("Groups");

      calcParameters.putDataItem("1", vaultItem);
      CalculationMeta meta = new CalculationMeta();
      meta.contentorganization(ContentorganizationEnum.SIMPLIFIEDROW);
      meta.contenttype(ContenttypeEnum.JSON);
      calcParameters.setMeta(meta);

      // Run Calculation Request
      VaultCalculationsApi apiInstance = new VaultCalculationsApi(getApiClient());

      ApiResponse<Object> response = apiInstance.postAndCalculateWithHttpInfo(DEADLINE_HEADER_VALUE, "max-stale=3600", calcParameters);
      Map<String, List<String>> headers = response.getHeaders();

      ApiResponse<CalculationStatusRoot> getStatus = null;
      Object result = null;
      if (response.getStatusCode() == 202) {
        String[] locationList = response.getHeaders().get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 2];

        // Get Calculation Request Status

        while (getStatus == null || getStatus.getStatusCode() == 202) {
          if (getStatus != null) {
            List<String> cacheControl = getStatus.getHeaders().get("Cache-Control");
            if (cacheControl != null) {
              int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
              System.out.println("Sleeping for: " + maxAge + " seconds");
              Thread.sleep(maxAge * 1000L);
            } else {
              System.out.println("Sleeping for: 2 seconds");
              Thread.sleep(2 * 1000L);
            }
          }
          getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
          headers = getStatus.getHeaders();
        }
        for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits().entrySet()) {
          if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS)
          {
            String[] location = calculationUnitParameters.getValue().getResult().split("/");
            String id = location[location.length - 4];
            String unitId = location[location.length - 2];
            ApiResponse<ObjectRoot> resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(id, unitId);
            result = resultResponse.getData().getData();
            headers = resultResponse.getHeaders();
          }}
      }
      else if(response.getStatusCode() == 201) {
        result = ((ObjectRoot)response.getData()).getData();
        headers = response.getHeaders();
      }

      System.out.println("Calculation Completed!!!");
      List<TableData> tableDataList = null;
      try {
        ObjectMapper mapper = new ObjectMapper();     
        String jsonString = mapper.writeValueAsString(result);

        if(headers.get("content-type").get(0).toLowerCase().contains("row")) {
          RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
          StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
          tableDataList = stachExtension.convertToTable();              
        }
        else {
          ColumnStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getColumnOrganizedBuilder(StachVersion.V2);
          StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
          tableDataList = stachExtension.convertToTable();              
        }        
      } catch(Exception e) {
        System.out.println(e.getMessage());
        e.getStackTrace();
      }

      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(tableDataList);
      System.out.println(json); // Prints the result in 2D table format.
      // Uncomment the following line to generate an Excel file
      // generateExcel(tableDataList);

    } catch (ApiException e) {
      handleException("VaultEngineExample#Main", e);
      return;
    }
  }

  private static void generateExcel(List<TableData> tableList) {
    for(TableData table : tableList) {
      writeDataToExcel(table, UUID.randomUUID().toString() + ".xlsv");
    }      
  }

  private static void writeDataToExcel(TableData table, String fileLocation) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Calculation Report");
    int rowsSize = table.getRows().size();
    for (int rowIndex = 0; rowIndex < rowsSize; rowIndex++) {
      XSSFRow xsswRow = sheet.createRow(rowIndex);
      List<String> cells = table.getRows().get(rowIndex).getCells();
      for (int cellIndex = 0; cellIndex < cells.size(); cellIndex++) {
        XSSFCell xssfCell = xsswRow.createCell(cellIndex);
        xssfCell.setCellValue(cells.get(cellIndex));
      }
    }
    try {
      FileOutputStream fileStream = new FileOutputStream(new File(fileLocation));
      workbook.write(fileStream);
      fileStream.close();
      workbook.close();
    } catch (Exception e) {
      System.err.println("Failed to write data to excel");
      e.printStackTrace();
    }
  }

  private static class FdsApiClient extends ApiClient
  {
    // Uncomment the below lines to use a proxy server
    /*@Override
    protected void customizeClientBuilder(ClientBuilder clientBuilder) {
      clientConfig.property( ClientProperties.PROXY_URI, "http://127.0.0.1:8866" );
      clientConfig.connectorProvider( new ApacheConnectorProvider() );
    }*/
  }

  private static FdsApiClient getApiClient() {
    if (apiClient != null) {
      return apiClient;
    }

    apiClient = new FdsApiClient();
    apiClient.setConnectTimeout(30000);
    apiClient.setReadTimeout(30000);
    apiClient.setBasePath(BASE_PATH);
    apiClient.setUsername(USERNAME);
    apiClient.setPassword(PASSWORD);

    return apiClient;
  }

  private static void handleException(String method, ApiException e) {
    System.out.println("Calculation Failed!!!");
    System.out.println("Status code: " + e.getCode());
    if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-datadirect-request-key")) {
      System.out.println("Request Key: " + e.getResponseHeaders().get("x-datadirect-request-key").get(0));
    }
    System.out.println("Reason: " + e.getClientErrorResponse());
    e.printStackTrace();
  }
}