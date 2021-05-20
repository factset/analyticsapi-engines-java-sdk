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

public class PubEngineInteractiveExample {
  private static FdsApiClient apiClient = null;
  private static String BASE_PATH = "https://api.factset.com";
  private static String USERNAME = "<username-serial>";
  private static String PASSWORD = "<apiKey>";
  private static String PUB_DEFAULT_DOCUMENT = "Client:/AAPI/Puma Test Doc.Pub_bridge_pdf";
  private static String PUB_DEFAULT_ACCOUNT = "BENCH:SP50";
  private static Integer DEADLINE_HEADER_VALUE = 20;

  public static void main(String[] args) throws InterruptedException, JsonProcessingException, FileNotFoundException {
    try {
      // Build Pub Calculation Parameters List

      PubCalculationParametersRoot calcParameters = new PubCalculationParametersRoot();

      PubCalculationParameters pubItem = new PubCalculationParameters();
      pubItem.setDocument(PUB_DEFAULT_DOCUMENT);

      PubIdentifier account = new PubIdentifier();
      account.setId(PUB_DEFAULT_ACCOUNT);
      pubItem.setAccount(account);

      PubDateParameters dateParameters = new PubDateParameters();
      dateParameters.setStartdate("-1M");
      dateParameters.setEnddate("0M");
      pubItem.setDates(dateParameters);

      calcParameters.putDataItem("1", pubItem);

      // Run Calculation Request
      PubCalculationsApi apiInstance = new PubCalculationsApi(getApiClient());

      ApiResponse<Object> createResponse = apiInstance.postAndCalculateWithHttpInfo(DEADLINE_HEADER_VALUE, "max-stale=3600", calcParameters);


      // Get Calculation Request Status
      ApiResponse<CalculationStatusRoot> getStatus = null;
      File result = null;
      if(createResponse.getStatusCode() == 202) {
        String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 2];
        System.out.println("Calculation Id: " + requestId);
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
        }
        for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits().entrySet()) {
          if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS)
          {
            String[] location = calculationUnitParameters.getValue().getResult().split("/");
            String id = location[location.length - 4];
            String unitId = location[location.length - 2];
            ApiResponse<File> resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(id, unitId);
            result = resultResponse.getData();
          }}
      }
      else if(createResponse.getStatusCode() == 201) {
        result = (File)createResponse.getData();
      }

      System.out.println("Calculation Completed!!!");

      result.renameTo(new File("output.pdf"));//ensure that file with the same name does not exist in the location

      System.out.println("Result file output.pdf");

    } catch (ApiException e) {
      handleException("PubEngineExample#Main", e);
      return;
    }
  }

  private static class FdsApiClient extends ApiClient {
    /*protected void customizeClientBuilder(ClientBuilder clientBuilder) {
      // uncomment following settings when you want to use a proxy
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