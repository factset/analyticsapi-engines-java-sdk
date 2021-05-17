package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.PubCalculationsApi;
import factset.analyticsapi.engines.models.CalculationStatusRoot;
import factset.analyticsapi.engines.models.CalculationUnitStatus;
import factset.analyticsapi.engines.models.ObjectRoot;
import factset.analyticsapi.engines.models.PubCalculationParameters;
import factset.analyticsapi.engines.models.PubCalculationParametersRoot;
import factset.analyticsapi.engines.models.PubDateParameters;
import factset.analyticsapi.engines.models.PubIdentifier;
import factset.analyticsapi.engines.models.CalculationStatus.StatusEnum;

public class PubEngineInteractiveExample {
  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String PUB_DEFAULT_DOCUMENT = "Client:/AAPI/Puma Test Doc.Pub_bridge_pdf";
  private static final String PUB_DEFAULT_ACCOUNT = "BENCH:SP50";

  public static void main(final String[] args) throws InterruptedException, JsonProcessingException {
    try {
      // Build Pub Calculation Parameters List

      final PubCalculationParametersRoot parameters = new PubCalculationParametersRoot();

      final PubCalculationParameters pubItem = new PubCalculationParameters();
      Map<String, List<String>> headers = null;
      pubItem.setDocument(PUB_DEFAULT_DOCUMENT);

      final PubIdentifier account = new PubIdentifier();
      account.setId(PUB_DEFAULT_ACCOUNT);
      pubItem.setAccount(account);

      final PubDateParameters dateParameters = new PubDateParameters();
      dateParameters.setStartdate("-1M");
      dateParameters.setEnddate("0M");
      pubItem.setDates(dateParameters);

      parameters.putDataItem("1", pubItem);

      // Run Calculation Request
      final PubCalculationsApi apiInstance = new PubCalculationsApi(getApiClient());
      ApiResponse<Object> createResponse = null;

      createResponse = apiInstance.postAndCalculateWithHttpInfo(null, null, parameters);


      // Get Calculation Request Status
      ApiResponse<CalculationStatusRoot> getStatus = null;

      ApiResponse<File> resultResponse = null;
      Object result = null;
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
          headers = getStatus.getHeaders();
        }
        for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits().entrySet()) {
          if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS)
          {
            String[] location = calculationUnitParameters.getValue().getResult().split("/");
            String id = location[location.length - 4];
            String unitId = location[location.length - 2];
            resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(id, unitId);
            result = resultResponse.getData();
            headers = resultResponse.getHeaders();
          }}
      }
      else if(createResponse.getStatusCode() == 201) {
        result = ((ObjectRoot)createResponse.getData()).getData();
        headers = createResponse.getHeaders();
      }

      System.out.println("Calculation Completed!!!");

      // Check for Calculation Units
      for (final Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits()
          .entrySet()) {//need to run and check if this logic still works with v3
        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS) {
          final Client httpClient = apiClient.getHttpClient();
          final WebTarget target = httpClient.target(calculationUnitParameters.getValue().getResult());
          final Response response = target.request().accept("application/pdf")
              .header("Authorization",
                  "Basic " + new String(Base64.encodeBase64((USERNAME + ":" + PASSWORD).getBytes())))
              .get(Response.class);

          if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            InputStream inputStream = response.readEntity(InputStream.class);
            File outputPDF = new File("output.pdf");
            try {

              byte[] byteArray = IOUtils.toByteArray(inputStream);
              FileOutputStream fileOutputStream = new FileOutputStream(outputPDF);
              fileOutputStream.write(byteArray);
              fileOutputStream.flush();
              fileOutputStream.close();
            } catch (Exception e) {
              e.getMessage();
            }

            IOUtils.closeQuietly(inputStream);

            System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Succeeded!!!");
            System.out.println("Result file output.pdf");
          }

        } else {
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationUnitParameters.getValue().getErrors());
        }
      }
    } catch (final ApiException e) {
      handleException("VaultEngineExample#Main", e);
      return;
    }
  }

  private static class FdsApiClient extends ApiClient {
    // Uncomment the below lines to use a proxy server
    /*
     * @Override protected void performAdditionalClientConfiguration(ClientConfig
     * clientConfig) { clientConfig.property( ClientProperties.PROXY_URI,
     * "<proxyUrl>" ); clientConfig.connectorProvider( new ApacheConnectorProvider()
     * ); }
     */
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