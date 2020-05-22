package examples;

import java.util.List;
import java.util.Map;

//import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.ClientProperties;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.models.CalculationStatus.StatusEnum;

import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;
import com.factset.protobuf.stach.PackageProto.Package.Builder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.factset.protobuf.stach.PackageProto.Package;

public class PAEngineExample {
	
  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "https://api.factset.com";
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
      Map<String, ComponentSummary> components = componentsApi.getPAComponents(PA_DEFAULT_DOCUMENT);
      String componentId = components.entrySet().stream()
          .filter(c -> c.getValue().getName().equals(COMPONENT_NAME) && c.getValue().getCategory().equals(COMPONENT_CATEGORY))
          .iterator().next().getKey();
      System.out.println(
          "ID of component with Name '" + COMPONENT_NAME + "' and category '" + COMPONENT_CATEGORY + "' : " + componentId);

      Calculation parameters = new Calculation();

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

      parameters.putPaItem("1", paItem);

      // Run Calculation Request
      CalculationsApi apiInstance = new CalculationsApi(getApiClient());
      ApiResponse<Void> createResponse = null;

      createResponse = apiInstance.runCalculationWithHttpInfo(parameters);

      String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
      String requestId = locationList[locationList.length - 1];

      // Get Calculation Request Status
      ApiResponse<CalculationStatus> getStatus = null;

      while (getStatus == null || getStatus.getData().getStatus() == StatusEnum.QUEUED
          || getStatus.getData().getStatus() == StatusEnum.EXECUTING) {
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

      // Check for Failed Calculations
      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getPa().entrySet()) {
        if (calculationParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.FAILED) {
          System.out.println("CalculationId : " + calculationParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationParameters.getValue().getError());
          return;
        }
      }

      // Get Result of Successful Calculations
      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getPa().entrySet()) {
        if (calculationParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS) {
          UtilityApi utilityApiInstance = new UtilityApi(apiClient);
          ApiResponse<String> resultResponse = utilityApiInstance
              .getByUrlWithHttpInfo(calculationParameters.getValue().getResult());
          System.out.println("CalculationId : " + calculationParameters.getKey() + " Succeeded!!!");
          System.out.println("CalculationId : " + calculationParameters.getKey() + " Result");

          Builder builder = Package.newBuilder();
          try {
            JsonFormat.parser().ignoringUnknownFields().merge(resultResponse.getData(), builder);
          } catch (InvalidProtocolBufferException e) {
            System.out.println("Error while deserializing the response");
            e.printStackTrace();
          }

          Package result = builder.build();
          // To convert result to 2D tables.
          List<TableData> tables = StachExtensions.convertToTableFormat(result);
          ObjectMapper mapper = new ObjectMapper();
          String json = mapper.writeValueAsString(tables.get(0));
          System.out.println(json); // Prints the result in 2D table format.
          // Uncomment the following line to generate an Excel file
          // StachExtensions.generateExcel(result);
        }
      }
    } catch (ApiException e) {
      handleException("PAEngineExample#Main", e);
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
    System.out.println("Reason: " + e.getResponseBody());
    e.printStackTrace();
  }
}
