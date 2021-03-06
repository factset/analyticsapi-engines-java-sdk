package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientProperties;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.models.CalculationMeta.ContentorganizationEnum;
import factset.analyticsapi.engines.models.CalculationMeta.ContenttypeEnum;
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

public class PAEngineExample {

  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = "https://api.factset.com";
  private static String USERNAME = "<username-serial>";
  private static String PASSWORD = "<apiKey>";
  
  private static String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  private static String COMPONENT_NAME = "Weights";
  private static String COMPONENT_CATEGORY = "Weights / Exposures";
  private static String COLUMN_NAME = "Port. Ending Weight";
  private static String COLUMN_CATEGORY = "Portfolio/Position Data";
  private static String COULMN_DIRECTORY = "Factset";
  private static String GROUP_NAME = "Economic Sector - FactSet";
  private static String GROUP_CATEGORY = "FactSet";
  private static String GROUP_DIRECTORY = "Factset";

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      // Build PA Calculation Parameters List

      // Get all component from PA_DEFAULT_DOCUMENT with Name COMPONENT_NAME & category COMPONENT_CATEGORY
      ComponentsApi componentsApi = new ComponentsApi(getApiClient());
      Map<String, ComponentSummary> components = componentsApi.getPAComponents(PA_DEFAULT_DOCUMENT).getData();
      String componentId = components.entrySet().stream()
          .filter(c -> c.getValue().getName().equals(COMPONENT_NAME) && c.getValue().getCategory().equals(COMPONENT_CATEGORY))
          .iterator().next().getKey();
      System.out.println(
          "ID of component with Name '" + COMPONENT_NAME + "' and category '" + COMPONENT_CATEGORY + "' : " + componentId);

      PACalculationParametersRoot calcParameters = new PACalculationParametersRoot();

      PACalculationParameters paItem = new PACalculationParameters();

      paItem.setComponentid(componentId);

      PAIdentifier accountPaIdentifier1 = new PAIdentifier();
      accountPaIdentifier1.setId("BENCH:SP50");
      accountPaIdentifier1.setHoldingsmode("B&H"); // It can be B&H, TBR, OMS or EXT
      paItem.addAccountsItem(accountPaIdentifier1);

      PAIdentifier accountPaIdentifier2 = new PAIdentifier();
      accountPaIdentifier2.setId("BENCH:R.2000");
      accountPaIdentifier2.setHoldingsmode("B&H");
      paItem.addAccountsItem(accountPaIdentifier2);

      PAIdentifier benchmarkPaIdentifier = new PAIdentifier();
      benchmarkPaIdentifier.setId("BENCH:R.2000");
      benchmarkPaIdentifier.setHoldingsmode("B&H");
      paItem.addBenchmarksItem(benchmarkPaIdentifier);

      PADateParameters dateParameters = new PADateParameters();
      dateParameters.setStartdate("20180101");
      dateParameters.setEnddate("20180331");
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
      
      calcParameters.putDataItem("1", paItem);
      calcParameters.putDataItem("2", paItem);
      
      CalculationMeta meta = new CalculationMeta();
      meta.contentorganization(ContentorganizationEnum.SIMPLIFIEDROW);
      meta.contenttype(ContenttypeEnum.JSON);
      calcParameters.setMeta(meta);

      // Run Calculation Request
      PaCalculationsApi apiInstance = new PaCalculationsApi(getApiClient());
      ApiResponse<Object> createResponse = null;

      createResponse = apiInstance.postAndCalculateWithHttpInfo(null, "max-stale=3600", calcParameters);

      String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
      String requestId = locationList[locationList.length - 2];
      System.out.println("Calculation Id: "+ requestId);
      
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
        getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
      }

      System.out.println("Calculation Completed!!!");

      // Check for Calculation Units
      for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits().entrySet()) {
        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS)
        {
          String[] location = calculationUnitParameters.getValue().getResult().split("/");
          String id = location[location.length - 4];
          String unitId = location[location.length - 2];
          ApiResponse<ObjectRoot> resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(id, unitId);

          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Succeeded!!!");
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Result");
          
          List<TableData> tables = null;
          try {
            ObjectMapper mapper = new ObjectMapper();     
            String jsonString = mapper.writeValueAsString(resultResponse.getData().getData());

            if(resultResponse.getHeaders().get("content-type").get(0).toLowerCase().contains("row")) {
              // For row and simplified row organized formats
              RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
              StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
              tables = stachExtension.convertToTable();              
            }
            else {
              // For column organized format
              ColumnStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getColumnOrganizedBuilder(StachVersion.V2);
              StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
              tables = stachExtension.convertToTable();              
            }        
          } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
          }

          ObjectMapper mapper = new ObjectMapper();
          String json = mapper.writeValueAsString(tables);
          System.out.println(json); // Prints the result in 2D table format.
          // Uncomment the following line to generate an Excel file
          // generateExcel(tables);
        }
        else{
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationUnitParameters.getValue().getErrors());
        }
      }
    } catch (ApiException e) {
      handleException("PAEngineExample#Main", e);
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
      clientConfig.property( ClientProperties.PROXY_URI, "http://127.0.0.1:8888" );
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