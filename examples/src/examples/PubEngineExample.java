package examples;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.models.CalculationStatus.StatusEnum;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientProperties;

public class PubEngineExample {
  
  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = "https://api.factset.com";
  private static String USERNAME = "<username-serial>";
  private static String PASSWORD = "<apiKey>";
  
  private static String PUB_DEFAULT_DOCUMENT = "Client:/AAPI/Puma Test Doc.Pub_bridge_pdf";
  private static String PUB_DEFAULT_ACCOUNT = "BENCH:SP50";
  
  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      // Build Pub Calculation Parameters List
      
      PubCalculationParametersRoot calcParameters = new PubCalculationParametersRoot();
      PubCalculationParameters pubItem = new PubCalculationParameters();
      
      pubItem.setDocument(PUB_DEFAULT_DOCUMENT);
      
      PubIdentifier account = new PubIdentifier();
      account.setId(PUB_DEFAULT_ACCOUNT);
      account.setHoldingsmode("B&H"); // It can be B&H, TBR, OMS or EXT
      pubItem.setAccount(account);
      
      PubDateParameters dateParameters = new PubDateParameters();
      dateParameters.setStartdate("-1M");
      dateParameters.setEnddate("0M");
      pubItem.setDates(dateParameters);
      
      calcParameters.putDataItem("1", pubItem);
      calcParameters.putDataItem("2", pubItem);
      
      // Run Calculation Request
      PubCalculationsApi apiInstance = new PubCalculationsApi(getApiClient());
      
      ApiResponse<Object> createResponse = apiInstance.postAndCalculateWithHttpInfo(null, null, calcParameters);
      
      CalculationStatusRoot status = (CalculationStatusRoot) createResponse.getData();
      String requestId = status.getData().getCalculationid();
      
      System.out.println("Calculation Id: " + requestId);
      
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
      for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits()
              .entrySet()) {
        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS) {
          ApiResponse<File> resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(requestId, calculationUnitParameters.getKey());
          
          File result = resultResponse.getData();
          result.renameTo(new File("output-" + calculationUnitParameters.getKey() + ".pdf"));
          System.out.println("Result file : output-" + calculationUnitParameters.getKey() + ".pdf");
        } else {
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationUnitParameters.getValue().getErrors());
        }
      }
    } catch (final ApiException e) {
      handleException("PubEngineExample#Main", e);
      return;
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
  
  private static void handleException(final String method, final ApiException e) {
    System.out.println("Calculation Failed!!!");
    System.out.println("Status code: " + e.getCode());
    if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-datadirect-request-key")) {
      System.out.println("Request Key: " + e.getResponseHeaders().get("x-datadirect-request-key").get(0));
    }
    System.out.println("Reason: " + e.getClientErrorResponse());
    e.printStackTrace();
  }
}