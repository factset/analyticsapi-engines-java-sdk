package examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientProperties;

import com.fasterxml.jackson.core.JsonProcessingException;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.PubCalculationsApi;
import factset.analyticsapi.engines.models.CalculationStatusRoot;
import factset.analyticsapi.engines.models.CalculationUnitStatus;
import factset.analyticsapi.engines.models.PubCalculationParameters;
import factset.analyticsapi.engines.models.PubCalculationParametersRoot;
import factset.analyticsapi.engines.models.PubDateParameters;
import factset.analyticsapi.engines.models.PubIdentifier;

public class PubEngineSingleUnitExample {
  private static FdsApiClient apiClient = null;

  private static String PUB_DEFAULT_DOCUMENT = "Client:/AAPI/Puma Test Doc.Pub_bridge_pdf";
  private static String PUB_DEFAULT_ACCOUNT = "BENCH:SP50";
  
  public static void main(String[] args) throws InterruptedException, JsonProcessingException, FileNotFoundException {
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
      
      // Run Calculation Request
      PubCalculationsApi apiInstance = new PubCalculationsApi(getApiClient());
      ApiResponse<Object> createResponse = apiInstance.postAndCalculateWithHttpInfo(null, null, calcParameters);
      //Comment the above line and uncomment the below lines to add cache control configuration. Results are by default cached for 12 hours; Setting max-stale=300 will fetch a cached result which is 5 minutes older.
      //String cacheControlInput="max-stale=300";
      //ApiResponse<Object> createResponse = apiInstance.postAndCalculateWithHttpInfo(null, cacheControlInput, calcParameters);

      //Get Calculation Request Status
      ApiResponse<CalculationStatusRoot> getStatus = null;
      File result = null;
      switch (createResponse.getStatusCode()) {
        case 200:
          System.out.println("Calculation failed!!!");
          CalculationUnitStatus calcUnitStatus = ((CalculationStatusRoot) createResponse.getData()).getData().getUnits().get("1");
          System.out.println("Status : " + calcUnitStatus.getStatus());
          System.out.println("Reason : " + calcUnitStatus.getErrors());
          System.exit(-1);
        case 201:
          result = (File) createResponse.getData();
        case 202:
          CalculationStatusRoot status = (CalculationStatusRoot) createResponse.getData();
          String calculationId = status.getData().getCalculationid();
          System.out.println("Calculation Id: " + calculationId);
          
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
            }
          }
      }
      System.out.println("Calculation Completed!!!");
      
      result.renameTo(new File("output.pdf")); // Ensure that file with the same name does not exist in the location
      System.out.println("Result file : output.pdf");
    } catch (ApiException e) {
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
    apiClient.setBasePath(System.getenv("FACTSET_HOST"));
    apiClient.setUsername(System.getenv("FACTSET_USERNAME"));
    apiClient.setPassword(System.getenv("FACTSET_PASSWORD"));

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