package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.factset.protobuf.stach.extensions.ColumnStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.StachExtensions;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
import com.factset.protobuf.stach.v2.PackageProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto.RowOrganizedPackage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.util.JsonFormat;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.FpoOptimizerApi;
import factset.analyticsapi.engines.models.CalculationStatusRoot;
import factset.analyticsapi.engines.models.FPOAccount;
import factset.analyticsapi.engines.models.FPOOptimizationParameters;
import factset.analyticsapi.engines.models.FPOOptimizationParametersRoot;
import factset.analyticsapi.engines.models.ObjectRoot;
import factset.analyticsapi.engines.models.Optimization;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerStrategy;
import factset.analyticsapi.engines.models.OptimizerTradesList;
import factset.analyticsapi.engines.models.PaDoc;
import factset.analyticsapi.engines.models.OptimizerTradesList.IdentifierTypeEnum;

public class FpoInteractiveOptimizerEngineExample {
  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String FpoAccountId = "CLIENT:/FPO/1K_MAC_AMZN_AAPL.ACCT";
  private static final String FpoPaDocName = "CLIENT:/FPO/FPO_MASTER";
  private static final String FpoOptimizationDate = "0M";
  private static final String StrategyId = "Client:/analytics_api/dbui_simple_strategy";
  private static final IdentifierTypeEnum TradesIdType = IdentifierTypeEnum.ASSET;
  private static final Boolean IncudeCash = false;
  private static final String OptimizationCashflow = "0";
  private static final Integer DEADLINE_HEADER_VALUE = 20;
  public static final String ACCEPT_HEADER_VALUE = "gzip";
  private static FpoOptimizerApi apiInstance = new FpoOptimizerApi(getApiClient());

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      FPOOptimizationParameters fpoItem = new FPOOptimizationParameters();
      FPOAccount accountId = new FPOAccount();
      accountId.setId(FpoAccountId);
      PaDoc padoc = new PaDoc();
      padoc.setId(FpoPaDocName);
      accountId.setPaDocument(padoc);
      Optimization optimization = new Optimization();
      optimization.setBacktestDate(FpoOptimizationDate);
      optimization.setRiskModelDate(FpoOptimizationDate);
      optimization.setCashflow(OptimizationCashflow);
      OptimizerStrategy strategy = new OptimizerStrategy();
      strategy.setId(StrategyId);
      OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
      OptimizerTradesList tradesList = new OptimizerTradesList();
      tradesList.setIdentifierType(TradesIdType);
      tradesList.setIncludeCash(IncudeCash);
      optOutputTypes.setTrades(tradesList);

      fpoItem.setAccount(accountId);
      fpoItem.setOptimization(optimization);
      fpoItem.setStrategy(strategy);
      fpoItem.setOutputTypes(optOutputTypes);
      FPOOptimizationParametersRoot fpoOptimizerParam = new FPOOptimizationParametersRoot();
      fpoOptimizerParam.setData(fpoItem);

      ApiResponse<Object> response = null;
      Map<String, List<String>> headers = null;
      response = apiInstance.postAndOptimizeWithHttpInfo(DEADLINE_HEADER_VALUE, null, fpoOptimizerParam);
      headers = response.getHeaders();

      ApiResponse<CalculationStatusRoot> getStatus = null;
      ApiResponse<ObjectRoot> resultResponse = null;
      Object result = null;

      switch(response.getStatusCode()) {
        case 201: // Calculation completed
          result = ((ObjectRoot)response.getData()).getData();
          headers = response.getHeaders();
          break;
        case 202:
          String[] locationList = headers.get("Location").get(0).split("/");
          String requestId = locationList[locationList.length - 2];
          do {
            response = apiInstance.getOptimizationStatusByIdWithHttpInfo(requestId);
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
          } while(response.getStatusCode() == 202);
          break;
      }

   // Get Calculation Result
      String[] location = headers.get("Location").get(0).split("/");
      String id = location[location.length - 2];
      resultResponse = apiInstance.getOptimizationResultWithHttpInfo(id, ACCEPT_HEADER_VALUE);
      headers = resultResponse.getHeaders();
      result = resultResponse.getData().getData();

      System.out.println("Calculation Completed!!!");
      List<TableData> tableDataList = null;
      String jsonString = "";
      try {
        ObjectMapper mapper = new ObjectMapper();     
        jsonString = mapper.writeValueAsString(result);
        JsonNode jsonObject = mapper.readTree(jsonString);
        RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
        stachExtensionBuilder.addTable("data", jsonObject.get("trades"));//a)limited to only one case
        tableDataList = stachExtensionBuilder.build().convertToTable();
        /*if(headers.get("content-type").get(0).toLowerCase().contains("row")) {
          RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
          StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
          tableDataList = stachExtension.convertToTable();              
        }
        else {
          ColumnStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getColumnOrganizedBuilder(StachVersion.V2);
          StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
          tableDataList = stachExtension.convertToTable();             
        }*/   
      } catch(Exception e) {
        System.out.println(e.getMessage());
        e.getStackTrace();
      }

      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(tableDataList);//mapper.writeValueAsString(tableDataList);
      System.out.println(json); // Prints the result in 2D table format.
      // Uncomment the following line to generate an Excel file
      // generateExcel(tableDataList); //my change

    } catch (ApiException e) {
      handleException("FpoOptimizerEngineExample#Main", e);
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
    //@Override
    //protected void performAdditionalClientConfiguration(ClientConfig clientConfig) {
    //clientConfig.property( ClientProperties.PROXY_URI, "<proxyUrl>" );
    //clientConfig.connectorProvider( new ApacheConnectorProvider() );
    //}
   /* protected void customizeClientBuilder(ClientBuilder clientBuilder) {
      // uncomment following settings when you want to use a proxy
      clientConfig.property( ClientProperties.PROXY_URI, "http://127.0.0.1:8866" );
      clientConfig.property(ClientProperties.REQUEST_ENTITY_PROCESSING, "BUFFERED");
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