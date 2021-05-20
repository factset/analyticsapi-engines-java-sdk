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

public class PAEngineInteractiveExample {

  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = "https://api.factset.com";
  private static String USERNAME = "<username-serial>";
  private static String PASSWORD = "<apiKey>";
  private static String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  private static String COMPONENT_NAME = "Weights";
  private static String COMPONENT_CATEGORY = "Weights / Exposures";
  private static String COLUMN_NAME = "Ending Duration";
  private static String COLUMN_CATEGORY = "DDS";
  private static String COULMN_DIRECTORY = "Client";
  private static String GROUP_NAME = "Average Life";
  private static String GROUP_CATEGORY = "Average Life";
  private static String GROUP_DIRECTORY = "Factset";
  private static Integer DEADLINE_HEADER_VALUE = 20;

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      // Build PA Calculation Parameters List

      // Get all component from PA_DEFAULT_DOCUMENT with Name COMPONENT_NAME &
      // category COMPONENT_CATEGORY
      ComponentsApi componentsApi = new ComponentsApi(getApiClient());
      Map<String, ComponentSummary> components = componentsApi.getPAComponents(PA_DEFAULT_DOCUMENT).getData();
      String componentId = components.entrySet().stream().filter(
          c -> c.getValue().getName().equals(COMPONENT_NAME) && c.getValue().getCategory().equals(COMPONENT_CATEGORY))
          .iterator().next().getKey();
      System.out.println("ID of component with Name '" + COMPONENT_NAME + "' and category '" + COMPONENT_CATEGORY
          + "' : " + componentId);

      PACalculationParameters paItem = new PACalculationParameters();

      paItem.setComponentid(componentId);

      PAIdentifier accountPaIdentifier1 = new PAIdentifier();
      accountPaIdentifier1.setId("BENCH:SP50");
      paItem.addAccountsItem(accountPaIdentifier1);

      PAIdentifier benchmarkPaIdentifier = new PAIdentifier();
      benchmarkPaIdentifier.setId("BENCH:R.2000");
      paItem.addBenchmarksItem(benchmarkPaIdentifier);

      PADateParameters dateParameters = new PADateParameters();
      dateParameters.setStartdate("20180101");
      dateParameters.setEnddate("20181231");
      dateParameters.setFrequency("Monthly");
      paItem.setDates(dateParameters);

      PACalculationColumn column = new PACalculationColumn();
      column.setId(getColumnId(COLUMN_NAME, COLUMN_CATEGORY, COULMN_DIRECTORY));
      paItem.addColumnsItem(column);

      PACalculationGroup group = new PACalculationGroup();
      group.setId(getGroupId(GROUP_NAME, GROUP_CATEGORY, GROUP_DIRECTORY));
      paItem.addGroupsItem(group);

      PACalculationParametersRoot calcParameters = new PACalculationParametersRoot();
      calcParameters.putDataItem("1", paItem);

      CalculationMeta meta = new CalculationMeta();
      meta.contentorganization(ContentorganizationEnum.COLUMN);
      meta.contenttype(ContenttypeEnum.JSON);
      calcParameters.setMeta(meta);

      // Run Calculation Request
      PaCalculationsApi apiInstance = new PaCalculationsApi(getApiClient());

      ApiResponse<Object> response = apiInstance.postAndCalculateWithHttpInfo(DEADLINE_HEADER_VALUE, "max-stale=3600", calcParameters);
      Map<String, List<String>> headers = response.getHeaders();
      ApiResponse<CalculationStatusRoot> getStatus = null;
      ApiResponse<ObjectRoot> resultResponse = null;
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
            resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(id, unitId);
            result = resultResponse.getData().getData();
            headers = resultResponse.getHeaders();
          }}
      }
      else if (response.getStatusCode() == 201) {
        result = ((ObjectRoot)response.getData()).getData();
        headers = response.getHeaders();
      }
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
      handleException("PAEngineExample#Main", e);
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

  private static String getColumnId(String columnName, String columnCategory, String directory) throws ApiException {
    ColumnsApi apiInstance = new ColumnsApi(getApiClient());
    ColumnSummaryRoot columns = apiInstance.getPAColumnsWithHttpInfo(columnName, columnCategory, directory).getData();
    String columnId = columns.getData().entrySet().stream()
        .filter(c -> c.getValue().getName().equals(columnName) && c.getValue().getCategory().equals(columnCategory) &&
            c.getValue().getDirectory().equals(directory))
        .iterator().next().getKey();
    return columnId;
  }

  private static String getGroupId(String groupName, String groupCategory, String groupDirectory) throws ApiException {
    GroupsApi apiInstance = new GroupsApi(getApiClient());
    GroupRoot groups = apiInstance.getPAGroupsWithHttpInfo().getData();
    String groupId = groups.getData().entrySet().stream()
        .filter(c -> c.getValue().getName().equals(groupName) && c.getValue().getCategory().equals(groupCategory) &&
            c.getValue().getDirectory().equals(groupDirectory))
        .iterator().next().getKey();
    return groupId;
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
    System.err.println("Exception when calling " + method);
    if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-datadirect-request-key")) {
      System.out.println("x-datadirect-request-key: " + e.getResponseHeaders().get("x-datadirect-request-key").get(0));
    }
    System.out.println("Status code: " + e.getCode());
    System.out.println("Reason: " + e.getClientErrorResponse());
    e.printStackTrace();
  }
}