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


import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;
import com.factset.protobuf.stach.PackageProto.Package.Builder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.factset.protobuf.stach.PackageProto.Package;

import static factset.analyticsapi.engines.StachExtensions.convertToTableFormat;

public class VaultEngineInteractiveExample {

  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String VAULT_DEFAULT_DOCUMENT = "Client:/aapi/VAULT_QA_PI_DEFAULT_LOCKED";
  private static final String VAULT_DEFAULT_ACCOUNT = "CLIENT:/BISAM/REPOSITORY/QA/SMALL_PORT.ACCT";
  private static final String COMPONENT_NAME = "Average\r\nWeight";
  private static final String COMPONENT_CATEGORY = "Performance / 4 Tiles Calculate";

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      // Build Vault Calculation Parameters List

      // Get all component from VAULT_DEFAULT_DOCUMENT with Name COMPONENT_NAME & category COMPONENT_CATEGORY
      ComponentsApi componentsApi = new ComponentsApi(getApiClient());
      Map<String, ComponentSummary> components = componentsApi.getVaultComponents(VAULT_DEFAULT_DOCUMENT);
      String componentId = components.entrySet().stream().filter(
              c -> c.getValue().getName().equals(COMPONENT_NAME) && c.getValue().getCategory().equals(COMPONENT_CATEGORY))
              .iterator().next().getKey();
      System.out.println("ID of component with Name '" + COMPONENT_NAME + "' and category '" + COMPONENT_CATEGORY
              + "' : " + componentId);

      ConfigurationsApi configurationsApi = new ConfigurationsApi(getApiClient());
      Map<String, VaultConfigurationSummary> configurationsMap = configurationsApi
              .getVaultConfigurations(VAULT_DEFAULT_ACCOUNT);
      String configurationId = configurationsMap.entrySet().iterator().next().getKey();

      VaultCalculationParameters vaultItem = new VaultCalculationParameters();

      vaultItem.setComponentid(componentId);
      vaultItem.setConfigid(configurationId);

      VaultIdentifier account = new VaultIdentifier();
      account.setId(VAULT_DEFAULT_ACCOUNT);
      vaultItem.setAccount(account);

      VaultDateParameters dateParameters = new VaultDateParameters();
      dateParameters.setStartdate("20180101");
      dateParameters.setEnddate("20180331");
      dateParameters.setFrequency("Monthly");
      vaultItem.setDates(dateParameters);

      // Run Calculation Request
      VaultCalculationsApi apiInstance = new VaultCalculationsApi(getApiClient());
      ApiResponse<Object> response = null;

      response = apiInstance.runVaultCalculationWithHttpInfo(vaultItem);

      if (response.getStatusCode() == 202) {
        String[] locationList = response.getHeaders().get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 1];

        // Get Calculation Request Status

        while (response == null || response.getStatusCode() == 202) {
          if (response != null) {
            List<String> cacheControl = response.getHeaders().get("Cache-Control");
            if (cacheControl != null) {
              int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
              System.out.println("Sleeping for: " + maxAge + " seconds");
              Thread.sleep(maxAge * 1000L);
            } else {
              System.out.println("Sleeping for: 2 seconds");
              Thread.sleep(2 * 1000L);
            }
          }
          response = apiInstance.getVaultCalculationByIdWithHttpInfo(requestId);
        }
      }

      System.out.println("Calculation Completed!!!");

      if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {
        // Get Result of Successful Calculations
        Builder builder = Package.newBuilder();
        try {
          ObjectMapper objMapper = new ObjectMapper();
          String jsonStr = objMapper.writeValueAsString(response.getData());
          JsonFormat.parser().ignoringUnknownFields().merge(jsonStr, builder);
        } catch (InvalidProtocolBufferException e) {
          System.out.println("Error while deserializing the response");
          e.printStackTrace();
        }

        Package result = builder.build();
        // To convert result to 2D tables.
        List<TableData> tables = convertToTableFormat(result);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(tables.get(0));
        System.out.println(json); // Prints the result in 2D table format.
        // Uncomment the following line to generate an Excel file
        // StachExtensions.generateExcel(result);
      }
    } catch (ApiException e) {
      handleException("VaultEngineExample#Main", e);
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