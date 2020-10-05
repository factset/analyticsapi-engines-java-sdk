package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

//import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.ClientProperties;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.models.CalculationStatus.StatusEnum;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.IOUtils;

public class PubEngineExample {

  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String PUB_DEFAULT_DOCUMENT = "Client:/AAPI/Puma Test Doc.Pub_bridge_pdf";
  private static final String PUB_DEFAULT_ACCOUNT = "BENCH:SP50";

  public static void main(final String[] args) throws InterruptedException, JsonProcessingException {
    try {
      // Build Pub Calculation Parameters List

      final Calculation parameters = new Calculation();

      final PubCalculationParameters pubItem = new PubCalculationParameters();

      pubItem.setDocument(PUB_DEFAULT_DOCUMENT);

      final PubIdentifier account = new PubIdentifier();
      account.setId(PUB_DEFAULT_ACCOUNT);
      pubItem.setAccount(account);

      final PubDateParameters dateParameters = new PubDateParameters();
      dateParameters.setStartdate("-1M");
      dateParameters.setEnddate("0M");
      pubItem.setDates(dateParameters);

      parameters.putPubItem("1", pubItem);

      // Run Calculation Request
      final CalculationsApi apiInstance = new CalculationsApi(getApiClient());
      ApiResponse<Void> createResponse = null;

      createResponse = apiInstance.runCalculationWithHttpInfo(parameters);

      final String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
      final String requestId = locationList[locationList.length - 1];
      System.out.println("Calculation Id: " + requestId);
      // Get Calculation Request Status
      ApiResponse<CalculationStatus> getStatus = null;

      while (getStatus == null || getStatus.getData().getStatus() == StatusEnum.QUEUED
          || getStatus.getData().getStatus() == StatusEnum.EXECUTING) {
        if (getStatus != null) {
          final List<String> cacheControl = getStatus.getHeaders().get("Cache-Control");
          if (cacheControl != null) {
            final int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
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
      for (final Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getPub()
          .entrySet()) {
        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS) {
          final Client httpClient = apiClient.getHttpClient();
          final WebTarget target = httpClient.target(calculationUnitParameters.getValue().getResult());
          final Response resultResponse = target.request().accept("application/pdf")
              .header("Authorization",
                  "Basic " + new String(Base64.encodeBase64((USERNAME + ":" + PASSWORD).getBytes())))
              .get(Response.class);

          if (resultResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            InputStream inputStream = resultResponse.readEntity(InputStream.class);
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
          System.out.println("Error message : " + calculationUnitParameters.getValue().getError());
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
    System.out.println("Reason: " + e.getResponseBody());
    e.printStackTrace();
  }
}