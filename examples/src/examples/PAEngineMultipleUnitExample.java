package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.ClientBuilder;

import com.factset.protobuf.stach.extensions.models.Row;
import com.factset.protobuf.stach.extensions.v2.StachUtilities;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Value;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientProperties;

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

public class PAEngineMultipleUnitExample {
  
  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = System.getenv("FACTSET_HOST");
  private static String USERNAME = System.getenv("FACTSET_USERNAME");
  private static String PASSWORD = System.getenv("FACTSET_PASSWORD");

  private static String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  private static String COMPONENT_NAME = "Weights";
  private static String COMPONENT_CATEGORY = "Weights / Exposures";
  private static String COLUMN_NAME = "Port. Ending Weight";
  private static String COLUMN_CATEGORY = "Portfolio/Position Data";
  private static String COULMN_DIRECTORY = "Factset";
  private static String GROUP_NAME = "Economic Sector - FactSet";
  private static String GROUP_CATEGORY = "FactSet";
  private static String GROUP_DIRECTORY = "Factset";
  private static String PRICING_SOURCE_NAME = "MSCI - Gross";
  private static String PRICING_SOURCE_CATEGORY = "MSCI";
  private static String PRICING_SOURCE_DIRECTORY = "Equity";
  
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
      
      // Get PA pricing sources with PricingSourceName, PricingSourceCategory & PricingSourceDirectory
      
      PricingSourcesApi pricingSourcesApi = new PricingSourcesApi(getApiClient());
      Map<String, PAPricingSource> pricingSources = pricingSourcesApi.getPAPricingSources(PRICING_SOURCE_NAME, PRICING_SOURCE_CATEGORY, PRICING_SOURCE_DIRECTORY).getData();
      String pricingSourceId = pricingSources.entrySet().stream().filter(
                      c -> c.getValue().getName().equals(PRICING_SOURCE_NAME) && c.getValue().getCategory().equals(PRICING_SOURCE_CATEGORY) && c.getValue().getDirectory().equals(PRICING_SOURCE_DIRECTORY))
              .iterator().next().getKey();
      System.out.println("ID of pricing source with Name '" + PRICING_SOURCE_NAME + "' and category '" + PRICING_SOURCE_CATEGORY
              + "' and directory '" + PRICING_SOURCE_DIRECTORY + "': " + pricingSourceId);
      
      PACalculationPricingSource pricingsource = new PACalculationPricingSource();
      pricingsource.setId(pricingSourceId);
      
      PACalculationDataSources datasources = new PACalculationDataSources();
      datasources.addPortfoliopricingsourcesItem(pricingsource);
      datasources.useportfoliopricingsourcesforbenchmark(true);
      paItem.setDatasources(datasources);
      
      calcParameters.putDataItem("1", paItem);
      calcParameters.putDataItem("2", paItem);
      
      // Run Calculation Request
      PaCalculationsApi apiInstance = new PaCalculationsApi(getApiClient());
      ApiResponse<Object> createResponse = null;
      
      createResponse = apiInstance.postAndCalculateWithHttpInfo(null, null, calcParameters);

      // Comment the above line and uncomment the below lines to add cache control configuration. Results are by default cached for 12 hours; Setting max-stale=300 will fetch a cached result which is at max 5 minutes older.
      // String cacheControlInput = "max-stale=300";
      // createResponse = apiInstance.postAndCalculateWithHttpInfo(null, cacheControlInput, calcParameters);
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
          for (TableData table : tables) {
            // Prints the results in 2D table format.
            List<Row> rows = table.getRows();
            String json = mapper.writeValueAsString(rows);
            System.out.println(json);

            // Prints the metadata
            if (table.getRawMetadata().size() > 0) System.out.println("Printing metadata...");
            for (Map.Entry<String, List<Value>> rawMetadata : table.getRawMetadata().entrySet()) {
              for (Value val : rawMetadata.getValue()) {
                System.out.println("  " + rawMetadata.getKey() + ": " + StachUtilities.valueToString(val));
              }
            }
          }
          // Uncomment the following line to generate an Excel file
          // generateExcel(tables);
        } else {
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationUnitParameters.getValue().getErrors());
        }
      }
    } catch (ApiException e) {
      handleException("PAEngineExample#Main", e);
    }catch (InvalidProtocolBufferException e) {
      System.out.println(e.getMessage());
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