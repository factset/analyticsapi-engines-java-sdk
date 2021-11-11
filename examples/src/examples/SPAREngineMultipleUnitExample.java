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

public class SPAREngineMultipleUnitExample {
  
  private static FdsApiClient apiClient = null;

  private static String SPAR_DEFAULT_DOCUMENT = "pmw_root:/spar_documents/Factset Default Document";
  private static String COMPONENT_NAME = "Returns Table";
  private static String COMPONENT_CATEGORY = "Raw Data / Returns";
  
  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      // Build SPAR Calculation Parameters List
      
      // Get all component from SPAR_DEFAULT_DOCUMENT with Name COMPONENT_NAME & category COMPONENT_CATEGORY
      ComponentsApi componentsApi = new ComponentsApi(getApiClient());
      Map<String, ComponentSummary> components = componentsApi.getSPARComponents(SPAR_DEFAULT_DOCUMENT).getData();
      String componentId = components.entrySet().stream().filter(
              c -> c.getValue().getName().equals(COMPONENT_NAME) && c.getValue().getCategory().equals(COMPONENT_CATEGORY))
              .iterator().next().getKey();
      System.out.println("ID of component with Name '" + COMPONENT_NAME + "' and category '" + COMPONENT_CATEGORY
              + "' : " + componentId);
      
      SPARCalculationParametersRoot calcParameters = new SPARCalculationParametersRoot();
      
      SPARCalculationParameters sparItem = new SPARCalculationParameters();
      
      sparItem.setComponentid(componentId);
      
      SPARIdentifier accountIdentifier1 = new SPARIdentifier();
      accountIdentifier1.setId("R.1000");
      accountIdentifier1.setPrefix("RUSSELL");
      accountIdentifier1.setReturntype("GTR");
      sparItem.addAccountsItem(accountIdentifier1);
      
      SPARIdentifier accountIdentifier2 = new SPARIdentifier();
      accountIdentifier2.setId("RUSSELL_P:R.2000");
      accountIdentifier2.setPrefix("RUSSELL");
      accountIdentifier2.setReturntype("GTR");
      sparItem.addAccountsItem(accountIdentifier2);
      
      SPARIdentifier benchmarkIdentifier = new SPARIdentifier();
      benchmarkIdentifier.setId("R.2000");
      benchmarkIdentifier.setPrefix("RUSSELL");
      benchmarkIdentifier.setReturntype("GTR");
      sparItem.setBenchmark(benchmarkIdentifier);
      
      SPARDateParameters dateParameters = new SPARDateParameters();
      dateParameters.setStartdate("20180101");
      dateParameters.setEnddate("20181231");
      dateParameters.setFrequency("Monthly");
      sparItem.setDates(dateParameters);
      
      calcParameters.putDataItem("1", sparItem);
      calcParameters.putDataItem("2", sparItem);
      
      // Run Calculation Request
      SparCalculationsApi apiInstance = new SparCalculationsApi(getApiClient());
      
      ApiResponse<Object> createResponse = apiInstance.postAndCalculateWithHttpInfo(null, null, calcParameters);
      //Comment the above line and uncomment the below lines to add cache control configuration. Results are by default cached for 12 hours; Setting max-stale=300 will fetch a cached result which is 5 minutes older.
      //String cache_control="max-stale=0";
      //ApiResponse<Object> createResponse = apiInstance.postAndCalculateWithHttpInfo(null, cache_control, calcParameters);
      
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
      handleException("SPAREngineExample#Main", e);
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
    apiClient.setBasePath(System.getenv("FACTSET_HOST"));
    apiClient.setUsername(System.getenv("FACTSET_USERNAME"));
    apiClient.setPassword(System.getenv("FACTSET_PASSWORD"));

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