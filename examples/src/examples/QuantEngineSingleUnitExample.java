package examples;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.factset.protobuf.stach.extensions.ColumnStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensions;
import com.factset.protobuf.stach.extensions.models.Row;
import com.factset.protobuf.stach.extensions.v2.StachUtilities;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.protobuf.Value;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.QuantCalculationsApi;

import static factset.analyticsapi.engines.models.QuantScreeningExpressionUniverse.*;

public class QuantEngineSingleUnitExample {
  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = "https://api.factset.com";
  private static String USERNAME = "<username-serial>";
  private static String PASSWORD = "<apiKey>";
  
  private static String QUANT_START_DATE = "0";
  private static String QUANT_END_DATE = "-5D";
  private static String QUANT_FREQUENCY = "D";
  private static String QUANT_CALENDAR = "FIVEDAY";
  private static String UNIVERSE_EXPR = "ISON_DOW";
  private static UniverseTypeEnum UNIVERSE_TYPE = UniverseTypeEnum.EQUITY;
  private static String SECURITY_EXPR = "TICKER";
  private static String SCREENING_EXPR = "P_PRICE";
  private static String SCREENING_NAME = "Price (SCR)";
  private static String FQL_EXPR = "P_PRICE";
  private static String FQL_NAME = "Price (SCR)";
  private static String CALCULATION_UNIT_ID = "1";
  
  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      QuantCalculationsApi apiInstance = new QuantCalculationsApi(getApiClient());
      QuantCalculationParameters quantItem = new QuantCalculationParameters();
      
      QuantFdsDate fdsDate = new QuantFdsDate();
      fdsDate.startDate(QUANT_START_DATE);
      fdsDate.endDate(QUANT_END_DATE);
      fdsDate.frequency(QUANT_FREQUENCY);
      fdsDate.calendar(QUANT_CALENDAR);
      
      QuantScreeningExpressionUniverse screeningExpressionUniverse = new QuantScreeningExpressionUniverse();
      screeningExpressionUniverse.universeExpr(UNIVERSE_EXPR);
      screeningExpressionUniverse.universeType(UNIVERSE_TYPE);
      screeningExpressionUniverse.securityExpr(SECURITY_EXPR);
      
      QuantScreeningExpression screeningExpression = new QuantScreeningExpression();
      screeningExpression.expr(SCREENING_EXPR);
      screeningExpression.name(SCREENING_NAME);
      
      QuantFqlExpression fqlExpression = new QuantFqlExpression();
      fqlExpression.expr(FQL_EXPR);
      fqlExpression.name(FQL_NAME);
      
      quantItem.fdsDate(fdsDate);
      quantItem.addScreeningExpressionItem(screeningExpression);
      quantItem.addFqlExpressionItem(fqlExpression);
      quantItem.screeningExpressionUniverse(screeningExpressionUniverse);
      
      QuantCalculationParametersRoot quantCalculationParam = new QuantCalculationParametersRoot();
      quantCalculationParam.putDataItem("1", quantItem);
      ApiResponse<Object> response = apiInstance.postAndCalculateWithHttpInfo(null, quantCalculationParam);
      
      ApiResponse<CalculationStatusRoot> getStatus = null;
      File result = null;
      switch (response.getStatusCode()) {
        case 200:
          System.out.println("Calculation failed!!!");
          CalculationUnitStatus calcUnitStatus = ((CalculationStatusRoot) response.getData()).getData().getUnits().get(CALCULATION_UNIT_ID);
          System.out.println("Status : " + calcUnitStatus.getStatus());
          System.out.println("Reason : " + calcUnitStatus.getErrors());
          System.exit(-1);
          break;
        case 201:
          result = (File) response.getData();
          outputCalculationResult(result);
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
              ApiResponse<File> resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(calculationId, calculationUnitParameters.getKey());
              result = resultResponse.getData();
              System.out.println("Calculation Data");
              outputCalculationResult(result);
              resultResponse = apiInstance.getCalculationUnitInfoByIdWithHttpInfo(calculationId, calculationUnitParameters.getKey());
              result = resultResponse.getData();
              System.out.println("Calculation Info");
              outputCalculationResult(result);
            }
          }
          break;
      }
      System.out.println("Calculation Completed!!!");
    } catch (ApiException e) {
      handleException("QuantInteractiveEngineExample#Main", e);
    }
  }
  
  private static void SaveCalculationResult(File result, String output_filename) {
    result.renameTo(new File("output-" + output_filename + ".txt")); // Ensure that file with the same name does not exist in the location
    System.out.println("Result file : output-" + output_filename + ".txt");
  }
  
  private static void outputCalculationResult(File result) {
    try {
      JsonReader reader = new JsonReader(new FileReader(result));
      HashMap<String, Object> map = new Gson().fromJson(reader, HashMap.class);
      
      List<TableData> tables = null;
      
      ObjectMapper mapper = new ObjectMapper();
      String jsonString = mapper.writeValueAsString(map.get("data"));
      
      // For column organized format
      ColumnStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getColumnOrganizedBuilder(StachVersion.V2);
      StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
      tables = stachExtension.convertToTable();


      // Prints the results in 2D table format.
      for (TableData table : tables) {
        List<Row> rows = table.getRows();
        String json = mapper.writeValueAsString(rows);
        System.out.println(json);
      }
      // Prints the metadata
      for (TableData table : tables) {
        if (table.getMetadata().size() > 0) System.out.println("Printing metadata...");
        for (Map.Entry<String, List<Value>> rawMetadata : table.getRawMetadata().entrySet()) {
          for (Value val : rawMetadata.getValue()) {
            System.out.println("  " + rawMetadata.getKey() + ": " + StachUtilities.valueToString(val));
          }
        }
      }
      // Uncomment the following line to generate an Excel file
      // generateExcel(tables);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
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
    System.err.println("Exception when calling " + method);
    if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-datadirect-request-key")) {
      System.out.println("x-datadirect-request-key: " + e.getResponseHeaders().get("x-datadirect-request-key").get(0));
    }
    System.out.println("Status code: " + e.getCode());
    System.out.println("Reason: " + e.getClientErrorResponse());
    e.printStackTrace();
  }
}