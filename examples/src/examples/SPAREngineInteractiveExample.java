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

//import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.ClientProperties;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;
//import factset.analyticsapi.engines.StachExtensions.*;

import com.google.protobuf.util.JsonFormat;

import com.google.protobuf.InvalidProtocolBufferException;
import com.factset.protobuf.stach.extensions.ColumnStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.StachExtensions;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
//import com.factset.protobuf.stach.PackageProto.Package.Builder;
import com.factset.protobuf.stach.v2.PackageProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto.RowOrganizedPackage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.factset.protobuf.stach.PackageProto.Package;

//import static factset.analyticsapi.engines.StachExtensions.convertToTableFormat;

public class SPAREngineInteractiveExample {
  
  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String SPAR_DEFAULT_DOCUMENT = "pmw_root:/spar_documents/Factset Default Document";
  private static final String COMPONENT_NAME = "Returns Table";
  private static final String COMPONENT_CATEGORY = "Raw Data / Returns";

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

      SPARCalculationParameters sparItem = new SPARCalculationParameters();
      SPARCalculationParametersRoot parameters = new SPARCalculationParametersRoot();
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
      parameters.putDataItem("1", sparItem);

      // Run Calculation Request
      SparCalculationsApi apiInstance = new SparCalculationsApi(getApiClient());
      ApiResponse<Object> response = null;
      Map<String, List<String>> headers = null;
      
      response = apiInstance.postAndCalculateWithHttpInfo(0, "max-stale=0", parameters);
      headers = response.getHeaders();
      
      ApiResponse<CalculationStatusRoot> getStatus = null;
      ApiResponse<ObjectRoot> resultResponse = null;
      Object result = null;
      if(response.getStatusCode() == 202) {
        String[] locationList = response.getHeaders().get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 2];
  
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
          getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
          headers = getStatus.getHeaders();
        }
        for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits().entrySet()) {
          if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS)
          {
            String[] location = calculationUnitParameters.getValue().getResult().split("/");
            String id = location[location.length - 4];
            String unitId = location[location.length - 2];
            resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(id, unitId);
            result = resultResponse.getData().getData();
            headers = resultResponse.getHeaders();
          }}
      }
      else if(response.getStatusCode() == 201) {
        result = ((ObjectRoot)response.getData()).getData();
        headers = response.getHeaders();
      }

      System.out.println("Calculation Completed!!!");
      List<TableData> tableDataList = null;
      try {
        ObjectMapper mapper = new ObjectMapper();     
        String jsonString = mapper.writeValueAsString(result);

        if(headers.get("content-type").get(0).toLowerCase().contains("row")) {
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
      // generateExcel(tableDataList);
    } catch (ApiException e) {
      handleException("SPAREngineExample#Main", e);
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