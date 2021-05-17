package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
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

public class PAEngineExample {

  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "http://api.inhouse-cauth.factset.com";//"https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  private static final String COMPONENT_NAME = "Weights";
  private static final String COMPONENT_CATEGORY = "Weights / Exposures";

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

      PACalculationParametersRoot parameters = new PACalculationParametersRoot();

      PACalculationParameters paItem = new PACalculationParameters();

      paItem.setComponentid(componentId);

      PAIdentifier accountPaIdentifier1 = new PAIdentifier();
      accountPaIdentifier1.setId("BENCH:SP50");
      paItem.addAccountsItem(accountPaIdentifier1);

      PAIdentifier accountPaIdentifier2 = new PAIdentifier();
      accountPaIdentifier2.setId("BENCH:R.2000");
      paItem.addAccountsItem(accountPaIdentifier2);

      PAIdentifier benchmarkPaIdentifier = new PAIdentifier();
      benchmarkPaIdentifier.setId("BENCH:R.2000");
      paItem.addBenchmarksItem(benchmarkPaIdentifier);

      PADateParameters dateParameters = new PADateParameters();
      dateParameters.setStartdate("20180101");
      dateParameters.setEnddate("20181231");
      dateParameters.setFrequency("Monthly");
      paItem.setDates(dateParameters);

      parameters.putDataItem("1", paItem);
      parameters.putDataItem("2", paItem);

      // Run Calculation Request
      PaCalculationsApi apiInstance = new PaCalculationsApi(getApiClient());
      ApiResponse<Object> createResponse = null;

      createResponse = apiInstance.postAndCalculateWithHttpInfo(null, null, parameters);

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
          List<TableData> tableDataList = null;
          try {
            ObjectMapper mapper = new ObjectMapper();     
            String jsonString = mapper.writeValueAsString(resultResponse.getData().getData());
            
            if(resultResponse.getHeaders().get("content-type").get(0).toLowerCase().contains("row")) {
              RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
              StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
              tableDataList = stachExtension.convertToTable();              
            }
            else {
              ColumnStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getColumnOrganizedBuilder(StachVersion.V2);
              StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
              tableDataList = stachExtension.convertToTable();              
            }        
          } catch(Exception e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
          }
          
          ObjectMapper mapper = new ObjectMapper();
          String json = mapper.writeValueAsString(tableDataList);
          System.out.println(json); // Prints the result in 2D table format.
          // Uncomment the following line to generate an Excel file
          // generateExcel(tableDataList); //my change
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
    System.out.println("Calculation Failed!!!");
    System.out.println("Status code: " + e.getCode());
    if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-datadirect-request-key")) {
      System.out.println("Request Key: " + e.getResponseHeaders().get("x-datadirect-request-key").get(0));
    }
    System.out.println("Reason: " + e.getClientErrorResponse());
    e.printStackTrace();
  }
}