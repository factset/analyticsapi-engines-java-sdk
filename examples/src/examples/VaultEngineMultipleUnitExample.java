package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.ClientBuilder;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

import factset.analyticsapi.engines.models.CalculationStatus.StatusEnum;

import com.factset.protobuf.stach.extensions.ColumnStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.StachExtensions;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientProperties;

public class VaultEngineMultipleUnitExample {
  
  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = "https://api.factset.com";
  private static String USERNAME = "<username-serial>";
  private static String PASSWORD = "<apiKey>";
  
  private static String VAULT_DEFAULT_DOCUMENT = "Client:/aapi/VAULT_QA_PI_DEFAULT_LOCKED";
  private static String VAULT_DEFAULT_ACCOUNT = "CLIENT:/BISAM/REPOSITORY/QA/SMALL_PORT.ACCT";
  private static String COMPONENT_NAME = "Performance Attribution";
  private static String COMPONENT_CATEGORY = "Performance / Fixed Income Attribution";
  
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
      
      VaultCalculationParametersRoot calcParameters = new VaultCalculationParametersRoot();
      
      VaultCalculationParameters vaultItem = new VaultCalculationParameters();
      
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
      
      vaultItem.setComponentdetail("GROUPS"); // It can be GROUPS or TOTALS
      
      calcParameters.putDataItem("1", vaultItem);
      calcParameters.putDataItem("2", vaultItem);
      
      // Run Calculation Request
      VaultCalculationsApi apiInstance = new VaultCalculationsApi(getApiClient());
      
      ApiResponse<Object> createResponse = apiInstance.postAndCalculateWithHttpInfo(null, null, calcParameters);
      
      CalculationStatusRoot status = (CalculationStatusRoot) createResponse.getData();
      String calculationId = status.getData().getCalculationid();
      System.out.println("Calculation Id: " + calculationId);
      
      // Get Calculation Request Status
      ApiResponse<CalculationStatusRoot> getStatus = null;
      while (getStatus == null || getStatus.getData().getData().getStatus() == StatusEnum.QUEUED
              || getStatus.getData().getData().getStatus() == StatusEnum.EXECUTING) {
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
        getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(calculationId);
      }
      
      System.out.println("Calculation Completed!!!");
      
      // Check for Calculation Units
      for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits().entrySet()) {
        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS) {
          ApiResponse<ObjectRoot> resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(calculationId, calculationUnitParameters.getKey());
          
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Succeeded!!!");
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Result");
          List<TableData> tables = null;
          try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(resultResponse.getData().getData());
            
            RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
            StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
            tables = stachExtension.convertToTable();
            
          } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
          }
          
          ObjectMapper mapper = new ObjectMapper();
          String json = mapper.writeValueAsString(tables);
          System.out.println(json); // Prints the result in 2D table format.
          // Uncomment the following line to generate an Excel file
          // generateExcel(tables);
        }
      }
    } catch (ApiException e) {
      handleException("VaultEngineExample#Main", e);
      return;
    }
  }
  
  private static void generateExcel(List<TableData> tableList) {
    for (TableData table : tableList) {
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
  
  private static class FdsApiClient extends ApiClient {
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