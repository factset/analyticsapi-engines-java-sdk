package examples;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
  private static final String BASE_PATH = "https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String FiabAccountId = "Client:/aapi/FIAB_TEST_HOLDINGS.ACCT";
  private static final String FiabDocument = "Client:/aapi/AAPI_FIAB_BASE_DOC";
  private static final String FiabDate = "20200618";
  private static final String FiabMsl = "CLIENT:$$MSL_AAPI_TESTING.OFDB";
  private static final String FiabFiSettingsDocument = "None";
  private static FiabCalculationsApi apiInstance = new FiabCalculationsApi(getApiClient());

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      final FIABCalculationParameters parameters = new FIABCalculationParameters();

      final FIABIdentifier fiabID = new FIABIdentifier();
      fiabID.setId(FiabAccountId);
      parameters.setAccount(fiabID);
      final FIABDateParameters fiabDateParam = new FIABDateParameters();
      fiabDateParam.setEnddate(FiabDate);
      fiabDateParam.setStartdate(FiabDate);
      parameters.setDates(fiabDateParam);
      parameters.setFiabdocument(FiabDocument);
      parameters.setFisettingsdocument(FiabFiSettingsDocument);
      parameters.setMsl(FiabMsl);

      ApiResponse<Void> response = null;
      Map<String, List<String>> headers = null;

      response = apiInstance.runCalculationWithHttpInfo(parameters);
      headers = response.getHeaders();

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
      handleException("BpmOptimizerEngineExample#Main", e);
    }    
  }

  private static class FdsApiClient extends ApiClient
  {
    // Uncomment the below lines to use a proxy server
    /*@Override
    protected void performAdditionalClientConfiguration(ClientConfig clientConfig) {
    clientConfig.property( ClientProperties.PROXY_URI, "<proxyUrl>" );
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