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


import com.factset.protobuf.stach.extensions.ColumnStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.StachExtensions;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PAEngineSingleUnitExample {
  
  private static FdsApiClient apiClient = null;

  private static String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  private static String COMPONENT_NAME = "Weights";
  private static String COMPONENT_CATEGORY = "Weights / Exposures";
  private static String COLUMN_NAME = "Port. Ending Weight";
  private static String COLUMN_CATEGORY = "Portfolio/Position Data";
  private static String COULMN_DIRECTORY = "Factset";
  private static String GROUP_NAME = "Economic Sector - FactSet";
  private static String GROUP_CATEGORY = "FactSet";
  private static String GROUP_DIRECTORY = "Factset";
  
  private static String CALCULATION_UNIT_ID = "1";
  
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
      accountPaIdentifier1.setHoldingsmode("B&H"); // It can be B&H, TBR, OMS or EXT
      paItem.addAccountsItem(accountPaIdentifier1);
      
      PAIdentifier benchmarkPaIdentifier = new PAIdentifier();
      benchmarkPaIdentifier.setId("BENCH:R.2000");
      benchmarkPaIdentifier.setHoldingsmode("B&H");
      paItem.addBenchmarksItem(benchmarkPaIdentifier);
      
      PADateParameters dateParameters = new PADateParameters();
      dateParameters.setStartdate("20180101");
      dateParameters.setEnddate("20181231");
      dateParameters.setFrequency("Monthly");
      paItem.setDates(dateParameters);
      
      // To add column overrides
      // PACalculationColumn column = new PACalculationColumn();
      // column.setId(getColumnId(COLUMN_NAME, COLUMN_CATEGORY, COULMN_DIRECTORY));
      // paItem.addColumnsItem(column);
      
      // To add group overrides
      // PACalculationGroup group = new PACalculationGroup();
      // group.setId(getGroupId(GROUP_NAME, GROUP_CATEGORY, GROUP_DIRECTORY));
      // paItem.addGroupsItem(group);
      
      // To add currency override
      // paItem.currencyisocode("USD");
      
      // To add component detail.
      // paItem.setComponentdetail("GROUPS"); // It can be GROUPS or TOTALS
      
      PACalculationParametersRoot calcParameters = new PACalculationParametersRoot();
      calcParameters.putDataItem(CALCULATION_UNIT_ID, paItem);
      
      // Run Calculation Request
      PaCalculationsApi apiInstance = new PaCalculationsApi(getApiClient());
      ApiResponse<Object> response = apiInstance.postAndCalculateWithHttpInfo(null, null, calcParameters);
      // Comment the above line and uncomment the below lines to add cache control configuration. Results are by default cached for 12 hours; Setting max-stale=300 will fetch a cached result which is 5 minutes older.
      // String cacheControlInput = "max-stale=300";
      // ApiResponse<Object> response = apiInstance.postAndCalculateWithHttpInfo(null, cacheControlInput, calcParameters);

      ApiResponse<CalculationStatusRoot> getStatus = null;
      Object result = null;
      switch (response.getStatusCode()) {
        case 200:
          System.out.println("Calculation failed!!!");
          CalculationUnitStatus calcUnitStatus = ((CalculationStatusRoot) response.getData()).getData().getUnits().get(CALCULATION_UNIT_ID);
          System.out.println("Status : " + calcUnitStatus.getStatus());
          System.out.println("Reason : " + calcUnitStatus.getErrors());
          System.exit(-1);
          break;
        case 201:
          System.out.println("Calculation successful!!!");
          result = ((ObjectRoot) response.getData()).getData();
          break;
        case 202:
          CalculationStatusRoot status = (CalculationStatusRoot) response.getData();
          String calculationId = status.getData().getCalculationid();
          
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
            getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(calculationId);
          }
          
          for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits().entrySet()) {
            if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS) {
              ApiResponse<ObjectRoot> resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(calculationId, calculationUnitParameters.getKey());
              result = resultResponse.getData().getData();
            }
          }
          break;
      }
      
      List<TableData> tables = null;
      try {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(result);
        
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
    } catch (ApiException e) {
      handleException("PAEngineExample#Main", e);
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
  
  private static String getColumnId(String columnName, String columnCategory, String directory) throws ApiException {
    ColumnsApi apiInstance = new ColumnsApi(getApiClient());
    ColumnSummaryRoot columns = apiInstance.getPAColumnsWithHttpInfo(columnName, columnCategory, directory).getData();
    String columnId = columns.getData().entrySet().stream()
            .filter(c -> c.getValue().getName().equals(columnName) && c.getValue().getCategory().equals(columnCategory) &&
                    c.getValue().getDirectory().equals(directory))
            .iterator().next().getKey();
    
    System.out.println(
            "ID of column with Name '" + columnName + "', category '" + columnCategory + "' and directory '" + directory + "'" + " : " + columnId);
    return columnId;
  }
  
  private static String getGroupId(String groupName, String groupCategory, String groupDirectory) throws ApiException {
    GroupsApi apiInstance = new GroupsApi(getApiClient());
    GroupRoot groups = apiInstance.getPAGroupsWithHttpInfo().getData();
    String groupId = groups.getData().entrySet().stream()
            .filter(c -> c.getValue().getName().equals(groupName) && c.getValue().getCategory().equals(groupCategory) &&
                    c.getValue().getDirectory().equals(groupDirectory))
            .iterator().next().getKey();
    
    System.out.println(
            "ID of group with Name '" + groupName + "', category '" + groupCategory + "' and directory '" + groupDirectory + "'" + " : " + groupId);
    return groupId;
  }
  
  private static FdsApiClient getApiClient() {
    if (apiClient != null) {
      return apiClient;
    }
    
    apiClient = new FdsApiClient();
    apiClient.setConnectTimeout(30000);
    apiClient.setReadTimeout(30000);
    apiClient.setBasePath(System.getenv("FACTSET_HOST"));
    apiClient.setUsername(System.getenv("FACTSET_USERNAME"));
    apiClient.setPassword(System.getenv("FACTSET_PASSWORD"));

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
  
  private static class FdsApiClient extends ApiClient {
    // Uncomment the below lines to use a proxy server
    /*@Override
    protected void customizeClientBuilder(ClientBuilder clientBuilder) {
      clientConfig.property( ClientProperties.PROXY_URI, "http://127.0.0.1:8888" );
      clientConfig.connectorProvider( new ApacheConnectorProvider() );
    }*/
  }
}