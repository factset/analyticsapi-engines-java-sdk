package examples;

import java.util.List;
import java.util.Map;

//import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.ClientProperties;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.StachExtensions.*;
import factset.analyticsapi.engines.models.CalculationStatus.StatusEnum;

import com.google.protobuf.util.JsonFormat;

import com.google.protobuf.InvalidProtocolBufferException;
import com.factset.protobuf.stach.PackageProto.Package.Builder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.factset.protobuf.stach.PackageProto.Package;

import static factset.analyticsapi.engines.StachExtensions.convertToTableFormat;

public class SPAREngineExample {

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
      Map<String, ComponentSummary> components = componentsApi.getSPARComponents(SPAR_DEFAULT_DOCUMENT);
      String componentId = components.entrySet().stream().filter(
              c -> c.getValue().getName().equals(COMPONENT_NAME) && c.getValue().getCategory().equals(COMPONENT_CATEGORY))
              .iterator().next().getKey();
      System.out.println("ID of component with Name '" + COMPONENT_NAME + "' and category '" + COMPONENT_CATEGORY
              + "' : " + componentId);

      Calculation parameters = new Calculation();

      SPARCalculationParameters sparItem = new SPARCalculationParameters();

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

      parameters.putSparItem("1", sparItem);

      // Run Calculation Request
      CalculationsApi apiInstance = new CalculationsApi(getApiClient());
      ApiResponse<Void> createResponse = null;

      createResponse = apiInstance.runCalculationWithHttpInfo(parameters);

      String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
      String requestId = locationList[locationList.length - 1];
      System.out.println("Calculation Id: "+ requestId);
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

      // Check for Calculation Units
      for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getSpar().entrySet()) {
        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS) {
          UtilityApi utilityApiInstance = new UtilityApi(apiClient);
          ApiResponse<String> resultResponse = utilityApiInstance
                .getByUrlWithHttpInfo(calculationUnitParameters.getValue().getResult());
              
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Succeeded!!!");
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Result");

          Builder builder = Package.newBuilder();
          try {
            JsonFormat.parser().ignoringUnknownFields().merge(resultResponse.getData(), builder);
          } catch (InvalidProtocolBufferException e) {
            System.out.println("Error while deserializing the response");
            e.printStackTrace();
          }

          Package result = builder.build();
          // To convert result to 2D tables.
          List<TableData> tables = convertToTableFormat(result);
          ObjectMapper mapper = new ObjectMapper();
          String json = mapper.writeValueAsString(tables.get(0));
          System.out.println(json);
        } else {
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationUnitParameters.getValue().getError());
        }
      }
    } catch (ApiException e) {
      handleException("SPAREngineExample#Main", e);
      return;
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
    System.out.println("Calculation Failed!!!");
    System.out.println("Status code: " + e.getCode());
    if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-datadirect-request-key")) {
      System.out.println("Request Key: " + e.getResponseHeaders().get("x-datadirect-request-key").get(0));
    }
    System.out.println("Reason: " + e.getResponseBody());
    e.printStackTrace();
  }
}