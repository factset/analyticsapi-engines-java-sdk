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
import factset.analyticsapi.engines.api.BpmOptimizerApi;
import factset.analyticsapi.engines.models.BPMOptimizationParameters;
import factset.analyticsapi.engines.models.BPMOptimizationParametersRoot;
import factset.analyticsapi.engines.models.BPMOptimizerStrategy;
import factset.analyticsapi.engines.models.CalculationStatusRoot;
import factset.analyticsapi.engines.models.ObjectRoot;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerTradesList;
import factset.analyticsapi.engines.models.OptimizerTradesList.IdentifierTypeEnum;

public class BpmInteractiveOptimizerEngineExample {
  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "http://api.inhouse-cauth.factset.com";//"https://api.factset.com";
  private static final String USERNAME ="<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String BpmStrategyId = "CLIENT:/Aapi/BPMAPISIMPLE";
  //private static final String BpmSecondaryStrategyId = "CLIENT:/Analytics_api/Optimizers/BPMAPISIMPLE";
  private static final IdentifierTypeEnum TradesIdType = IdentifierTypeEnum.ASSET;
  private static final Boolean IncludeCash = false;
  private static final Integer DEADLINE_HEADER_VALUE = 20;
  private static BpmOptimizerApi apiInstance = new BpmOptimizerApi(getApiClient());

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      BPMOptimizationParameters bpmItem = new BPMOptimizationParameters();
      BPMOptimizerStrategy strategy = new BPMOptimizerStrategy();
      strategy.setId(BpmStrategyId);
      OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
      OptimizerTradesList tradesList = new OptimizerTradesList();
      tradesList.setIdentifierType(TradesIdType);
      tradesList.setIncludeCash(IncludeCash);
      optOutputTypes.setTrades(tradesList);

      bpmItem.setStrategy(strategy);
      bpmItem.setOutputTypes(optOutputTypes);
      BPMOptimizationParametersRoot bpmOptimizerParam = new BPMOptimizationParametersRoot();
      bpmOptimizerParam.setData(bpmItem);

      ApiResponse<Object> response = null;
      Map<String, List<String>> headers = null;

      response = apiInstance.postAndOptimizeWithHttpInfo(DEADLINE_HEADER_VALUE, null, bpmOptimizerParam);
      headers = response.getHeaders();

      ApiResponse<CalculationStatusRoot> getStatus = null;
      ApiResponse<ObjectRoot> resultResponse = null;
      Object result = null;

      switch(response.getStatusCode()) {
        case 201: // Calculation completed
          result = response;
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
      resultResponse = apiInstance.getOptimizationResultWithHttpInfo(id);
      headers = resultResponse.getHeaders();
      result = resultResponse.getData().getData();

      System.out.println("Calculation Completed!!!");
      List<TableData> tables = null;
      String jsonString = "";
      try {
        ObjectMapper mapper = new ObjectMapper();     
        jsonString = mapper.writeValueAsString(result);
        JsonNode jsonObject = mapper.readTree(jsonString);
        RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
        stachExtensionBuilder.addTable("data", jsonObject.get("trades"));//a)limited to only one case
        tables = stachExtensionBuilder.build().convertToTable();


      } catch(Exception e) {
        System.out.println(e.getMessage());
        e.getStackTrace();
      }
      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(tables);//mapper.writeValueAsString(tableDataList);
      System.out.println(json); // Prints the result in 2D table format.
      // Uncomment the following line to generate an Excel file
      // generateExcel(tableDataList); //my change

      /*Package result = builder.build();
      // To convert result to 2D tables.
      List<TableData> tables = convertToTableFormat(result);
      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(tables.get(0));
      System.out.println(json); // Prints the result in 2D table format.
      // Uncomment the following line to generate an Excel file
      // StachExtensions.generateExcel(result);*/
    } catch (ApiException e) {
      handleException("BpmOptimizerEngineExample#Main", e);
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
    protected void customizeClientBuilder(ClientBuilder clientBuilder) {
      // uncomment following settings when you want to use a proxy
      clientConfig.property( ClientProperties.PROXY_URI, "http://127.0.0.1:8866" );
      clientConfig.property(ClientProperties.REQUEST_ENTITY_PROCESSING, "BUFFERED");
      clientConfig.connectorProvider( new ApacheConnectorProvider() );
    }
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