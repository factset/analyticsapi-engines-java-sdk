package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.ClientBuilder;

import factset.analyticsapi.engines.models.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.AxpOptimizerApi;
import factset.analyticsapi.engines.models.OptimizerTradesList.IdentifierTypeEnum;

public class AxpInteractiveOptimizerEngineExample {
  private static FdsApiClient apiClient = null;
  private static String AXIOMA_ACCOUNT_ID = "BENCH:SP50";
  private static String OPTIMIZATION_DATE = "09/01/2020";
  private static String OPTIMIZATION_CASHFLOW = "0";
  private static String STRATEGY_ID = "Client:/Optimizer/TAXTEST";
  private static IdentifierTypeEnum TRADES_ID_TYPE = IdentifierTypeEnum.SEDOLCHK;
  private static Boolean INCLUDE_CASH = false;
  private static Boolean EXCLUDE_ZERO = false;
  
  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      AxpOptimizerApi apiInstance = new AxpOptimizerApi(getApiClient());
      AxiomaEquityOptimizationParameters axpItem = new AxiomaEquityOptimizationParameters();
      OptimizerAccount accountId = new OptimizerAccount();
      accountId.setId(AXIOMA_ACCOUNT_ID);
      
      Optimization optimization = new Optimization();
      optimization.setBacktestDate(OPTIMIZATION_DATE);
      optimization.setRiskModelDate(OPTIMIZATION_DATE);
      optimization.setCashflow(OPTIMIZATION_CASHFLOW);
      
      AxiomaEquityOptimizerStrategy strategy = new AxiomaEquityOptimizerStrategy();
      strategy.setId(STRATEGY_ID);
      
      OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
      OptimizerTradesList tradesList = new OptimizerTradesList();
      tradesList.setIdentifierType(TRADES_ID_TYPE);
      tradesList.setIncludeCash(INCLUDE_CASH);
      optOutputTypes.setTrades(tradesList);
      
      // OptimizerOptimalHoldings optimal = new OptimizerOptimalHoldings();
      // optimal.setIdentifierType(OptimizerOptimalHoldings.IdentifierTypeEnum.ASSET);
      // optimal.setIncludeCash(INCLUDE_CASH);
      // optimal.setExcludeZero(EXCLUDE_ZERO);
      // optOutputTypes.setOptimal(optimal); 
      
      axpItem.setAccount(accountId);
      axpItem.setOptimization(optimization);
      axpItem.setStrategy(strategy);
      axpItem.setOutputTypes(optOutputTypes);
      AxiomaEquityOptimizationParametersRoot axpOptimizerParam = new AxiomaEquityOptimizationParametersRoot();
      axpOptimizerParam.setData(axpItem);
      
      ApiResponse<Object> response = apiInstance.postAndOptimizeWithHttpInfo(null, null, axpOptimizerParam);
      //Comment the above line and uncomment the below lines to add cache control configuration. Results are by default cached for 12 hours; Setting max-stale=300 will fetch a cached result which is 5 minutes older.
      //String cacheControlInput="max-stale=300";
      //ApiResponse<Object> response = apiInstance.postAndOptimizeWithHttpInfo(null, cacheControlInput, axpOptimizerParam);
      Map<String, List<String>> headers = response.getHeaders();
      
      Object result = null;
      switch (response.getStatusCode()) {
        case 201: // Calculation completed
          System.out.println("Calculation successful!!!");
          result = ((ObjectRoot) response.getData()).getData();
          break;
        case 202:
          CalculationInfoRoot status = (CalculationInfoRoot) response.getData();
          String calculationId = status.getData().getCalculationId();
          do {
            response = apiInstance.getOptimizationStatusByIdWithHttpInfo(calculationId);
            headers = response.getHeaders();
            
            List<String> cacheControl = headers.get("Cache-Control");
            if (cacheControl != null) {
              int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
              System.out.println("Sleeping for: " + maxAge + " seconds");
              Thread.sleep(maxAge * 1000L);
            } else {
              System.out.println("Sleeping for: 2 seconds");
              Thread.sleep(2 * 1000L);
            }
          } while (response.getStatusCode() == 202);
          
          System.out.println("Calculation successful!!!");
          // Get Calculation Result
          ApiResponse<ObjectRoot> resultResponse = apiInstance.getOptimizationResultWithHttpInfo(calculationId);
          result = resultResponse.getData().getData();
          break;
      }
      
      List<TableData> tables = null;
      try {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(result);
        JsonNode jsonObject = mapper.readTree(jsonString);
        
        RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
        stachExtensionBuilder.addTable("tradesTable", jsonObject.get("trades"));
        // stachExtensionBuilder.addTable("optimalTable", jsonObject.get("optimal"));
        tables = stachExtensionBuilder.build().convertToTable();
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
      handleException("AxpOptimizerEngineExample#Main", e);
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
}