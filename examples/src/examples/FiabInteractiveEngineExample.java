package examples;

import java.util.List;
import java.util.Map;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientProperties;

import com.fasterxml.jackson.core.JsonProcessingException;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.FiabCalculationsApi;
import factset.analyticsapi.engines.models.FIABCalculationParameters;
import factset.analyticsapi.engines.models.FIABCalculationStatus;
import factset.analyticsapi.engines.models.FIABDateParameters;
import factset.analyticsapi.engines.models.FIABIdentifier;

public class FiabInteractiveEngineExample {
  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = "https://api.factset.com";
  private static String USERNAME = "<username-serial>";
  private static String PASSWORD = "<apiKey>";
  private static String FIAB_ACCOUNT_ID = "Client:/aapi/FIAB_TEST_HOLDINGS.ACCT";
  private static String FIAB_DOCUMENT = "Client:/aapi/AAPI_FIAB_BASE_DOC";
  private static String FIAB_DATE = "20200618";
  private static String FIAB_MSL = "CLIENT:$$MSL_AAPI_TESTING.OFDB";
  private static String FIAB_SETTINGS_DOCUMENT = "None";
  private static FiabCalculationsApi apiInstance = new FiabCalculationsApi(getApiClient());

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      FIABCalculationParameters calcParameters = new FIABCalculationParameters();

      FIABIdentifier fiabID = new FIABIdentifier();
      fiabID.setId(FIAB_ACCOUNT_ID);
      calcParameters.setAccount(fiabID);
      FIABDateParameters fiabDateParam = new FIABDateParameters();
      fiabDateParam.setEnddate(FIAB_DATE);
      fiabDateParam.setStartdate(FIAB_DATE);
      calcParameters.setDates(fiabDateParam);
      calcParameters.setFiabdocument(FIAB_DOCUMENT);
      calcParameters.setFisettingsdocument(FIAB_SETTINGS_DOCUMENT);
      calcParameters.setMsl(FIAB_MSL);

      ApiResponse<Void> response = apiInstance.runCalculationWithHttpInfo(calcParameters);
      Map<String, List<String>> headers = response.getHeaders();

      ApiResponse<FIABCalculationStatus> resultStatus = null;
      String[] locationList = headers.get("Location").get(0).split("/");
      String requestId = locationList[locationList.length - 1];

      do {
        resultStatus = apiInstance.getCalculationByIdWithHttpInfo(requestId);
        List<String> cacheControl = headers.get("Cache-Control");
        if (cacheControl != null) {
          int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
          System.out.println("Sleeping for: " + maxAge + " seconds");
          Thread.sleep(maxAge * 1000L);
        } else {
          System.out.println("Sleeping for: 2 seconds");
          Thread.sleep(2 * 1000L);
        }     
      } while(resultStatus.getStatusCode() == 202);
      System.out.println(resultStatus.getData());
    } catch (ApiException e) {
      handleException("FiabEngineExample#Main", e);
    }    
  }

  private static class FdsApiClient extends ApiClient
  {
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