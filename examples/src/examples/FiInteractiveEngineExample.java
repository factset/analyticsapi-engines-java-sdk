package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.ClientBuilder;

import com.factset.protobuf.stach.extensions.models.Row;
import com.factset.protobuf.stach.extensions.v2.StachUtilities;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Value;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.models.FIMarketEnvironment.RatePathEnum;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientProperties;

import com.factset.protobuf.stach.extensions.ColumnStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.StachExtensions;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.FiCalculationsApi;


public class FiInteractiveEngineExample {
  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = System.getenv("FACTSET_HOST");
  private static String USERNAME = System.getenv("FACTSET_USERNAME");
  private static String PASSWORD = System.getenv("FACTSET_PASSWORD");

  private static String FI_CALC_FROM_METHOD = "Price";
  private static Double FI_CALC_FROM_VALUE = 108.40299;
  private static Double FI_CALC_FROM_VALUE_2 = 100.285;
  private static Double FI_FACE_VALUE = (double) 100;
  private static Double FI_FACE_VALUE_2 = 10000.0;
  private static String FI_SYMBOL = "3140JQHD";
  private static String FI_SYMBOL_2 = "912828ZG8";
  private static String FI_SETTLEMENT = "20200922";
  private static String FI_SETTLEMENT_2 = "20201202";
  private static String FI_DISCOUNT_CURVE = "Government";
  private static String FI_DISCOUNT_CURVE_2 = "UST";
  private static String FI_AS_OF_DATE = "20200922";
  private static String[] FI_CALCULATIONS = {"Effective Duration", "Partial Duration", "Security Type", "Effective Convexity", "CF Coupon"};
  
  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      FiCalculationsApi apiInstance = new FiCalculationsApi(getApiClient());
      FICalculationParameters calcParameters = new FICalculationParameters();
      
      FIBankLoans fibankloans = new FIBankLoans();
      fibankloans.ignoreSinkingFund(true);
      
      FIMunicipalBonds fimunicipalbonds = new FIMunicipalBonds();
      fimunicipalbonds.ignoreSinkingFund(true);
      
      FISecurity security1 = new FISecurity();
      security1.setCalcFromMethod(FI_CALC_FROM_METHOD);
      security1.setCalcFromValue(FI_CALC_FROM_VALUE);
      security1.setFace(FI_FACE_VALUE);
      security1.setSettlement(FI_SETTLEMENT);
      security1.setDiscountCurve(FI_DISCOUNT_CURVE);
      security1.setSymbol(FI_SYMBOL);
      security1.setBankLoans(fibankloans);
      security1.setMunicipalBonds(fimunicipalbonds);
      calcParameters.addSecuritiesItem(security1);
      
      FISecurity security2 = new FISecurity();
      security2.setCalcFromMethod(FI_CALC_FROM_METHOD);
      security2.setCalcFromValue(FI_CALC_FROM_VALUE_2);
      security2.setFace(FI_FACE_VALUE_2);
      security2.setSettlement(FI_SETTLEMENT_2);
      security2.setDiscountCurve(FI_DISCOUNT_CURVE_2);
      security2.setSymbol(FI_SYMBOL_2);
      security2.setBankLoans(fibankloans);
      security2.setMunicipalBonds(fimunicipalbonds);
      calcParameters.addSecuritiesItem(security2);
      
      ArrayList<String> calc = new ArrayList<String>();
      for (int i = 0; i < FI_CALCULATIONS.length; i++)
        calc.add(FI_CALCULATIONS[i]);
      calcParameters.setCalculations(calc);
      
      FIMarketEnvironment fiMarketEnvironment = new FIMarketEnvironment();
      fiMarketEnvironment.ratePath(RatePathEnum.FLAT_FORWARD);
      
      FIMunicipalBondsForJobSettings fimunicipalbondsforjobsettings = new FIMunicipalBondsForJobSettings();
      fimunicipalbondsforjobsettings.ignoreSinkingFund(true);
      
      FIJobSettings jobSettings = new FIJobSettings();
      jobSettings.setAsOfDate(FI_AS_OF_DATE);
      jobSettings.marketEnvironment(fiMarketEnvironment);
      jobSettings.setBankLoans(fibankloans);
      jobSettings.setMunicipalBonds(fimunicipalbondsforjobsettings);
      calcParameters.setJobSettings(jobSettings);
      
      FICalculationParametersRoot fiCalcParam = new FICalculationParametersRoot();
      fiCalcParam.data(calcParameters);
      
      ApiResponse<Object> response = apiInstance.postAndCalculateWithHttpInfo(null, null, fiCalcParam);
      // Comment the above line and uncomment the below lines to add cache control configuration. Results are by default cached for 12 hours; Setting max-stale=300 will fetch a cached result which is at max 5 minutes older.
      // String cacheControlInput = "max-stale=300";
      // ApiResponse<Object> response = apiInstance.postAndCalculateWithHttpInfo(null, cacheControlInput, fiCalcParam);
      Map<String, List<String>> headers = response.getHeaders();
      
      Object result = null;
      switch (response.getStatusCode()) {
        case 201: // Calculation completed
          result = ((ObjectRoot) response.getData()).getData();
          break;
        case 202:
          CalculationInfoRoot status = (CalculationInfoRoot) response.getData();
          String calculationId = status.getData().getCalculationId();
          do {
            response = apiInstance.getCalculationStatusByIdWithHttpInfo(calculationId);
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
          // Get Calculation Result
          ApiResponse<ObjectRoot> resultResponse = apiInstance.getCalculationResultWithHttpInfo(calculationId);
          result = resultResponse.getData().getData();
          break;
      }
      
      System.out.println("Calculation Completed!!!");
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
    } catch (ApiException e) {
      handleException("FiEngineExample#Main", e);
    } catch (InvalidProtocolBufferException e) {
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
    /* protected void customizeClientBuilder(ClientBuilder clientBuilder) {
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