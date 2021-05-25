package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
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
import factset.analyticsapi.engines.models.CalculationMeta;
import factset.analyticsapi.engines.models.FICalculationParameters;
import factset.analyticsapi.engines.models.FICalculationParametersRoot;
import factset.analyticsapi.engines.models.FIJobSettings;
import factset.analyticsapi.engines.models.FISecurity;
import factset.analyticsapi.engines.models.ObjectRoot;
import factset.analyticsapi.engines.models.CalculationMeta.ContentorganizationEnum;
import factset.analyticsapi.engines.models.CalculationMeta.ContenttypeEnum;

public class FiInteractiveEngineExample {
  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = "https://api.factset.com";
  private static String USERNAME = "<username-serial>";
  private static String PASSWORD = "<apiKey>";
  
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
  
  private static Integer DEADLINE_HEADER_VALUE = 20;

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {    
    try{
      FiCalculationsApi apiInstance = new FiCalculationsApi(getApiClient());	
      FICalculationParameters calcParameters = new FICalculationParameters();
      
      FISecurity security1 = new FISecurity();
      security1.setCalcFromMethod(FI_CALC_FROM_METHOD);
      security1.setCalcFromValue(FI_CALC_FROM_VALUE);
      security1.setFace(FI_FACE_VALUE);
      security1.setSettlement(FI_SETTLEMENT);
      security1.setDiscountCurve(FI_DISCOUNT_CURVE);
      security1.setSymbol(FI_SYMBOL);
      calcParameters.addSecuritiesItem(security1);

      FISecurity security2 = new FISecurity();
      security2.setCalcFromMethod(FI_CALC_FROM_METHOD);
      security2.setCalcFromValue(FI_CALC_FROM_VALUE_2);
      security2.setFace(FI_FACE_VALUE_2);
      security2.setSettlement(FI_SETTLEMENT_2);
      security2.setDiscountCurve(FI_DISCOUNT_CURVE_2);
      security2.setSymbol(FI_SYMBOL_2);
      calcParameters.addSecuritiesItem(security2);

      ArrayList<String> calc = new ArrayList<String>();
      for(int i = 0; i < FI_CALCULATIONS.length ; i++)
        calc.add(FI_CALCULATIONS[i]);
      calcParameters.setCalculations(calc);

      FIJobSettings jobSettings = new FIJobSettings();
      jobSettings.setAsOfDate(FI_AS_OF_DATE);
      calcParameters.setJobSettings(jobSettings);

      FICalculationParametersRoot fiCalcParam = new FICalculationParametersRoot();
      fiCalcParam.data(calcParameters);
      
      CalculationMeta meta = new CalculationMeta();
      meta.contentorganization(ContentorganizationEnum.SIMPLIFIEDROW);
      meta.contenttype(ContenttypeEnum.JSON);
      fiCalcParam.meta(meta);

      ApiResponse<Object> response = apiInstance.postAndCalculateWithHttpInfo(DEADLINE_HEADER_VALUE, "max-stale=3600", fiCalcParam);
      Map<String, List<String>> headers = response.getHeaders();

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
            response = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
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
      ApiResponse<ObjectRoot> resultResponse = apiInstance.getCalculationResultWithHttpInfo(id);
      headers = resultResponse.getHeaders();
      result = resultResponse.getData().getData();

      System.out.println("Calculation Completed!!!");
      List<TableData> tables = null;
      try {
        ObjectMapper mapper = new ObjectMapper();     
        String jsonString = mapper.writeValueAsString(result);

        if(headers.get("content-type").get(0).toLowerCase().contains("column")) {
          // For row and simplified row organized formats
          ColumnStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getColumnOrganizedBuilder(StachVersion.V2);
          StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
          tables = stachExtension.convertToTable();                        
        }
        else {
          // For row and simplified row organized formats
          RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
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
    } catch (ApiException e) {
      handleException("FiEngineExample#Main", e);
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