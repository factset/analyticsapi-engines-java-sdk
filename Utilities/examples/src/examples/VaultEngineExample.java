package examples;

import java.util.List;
import java.util.Map;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.models.CalculationStatus.StatusEnum;

import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;
import com.factset.protobuf.stach.PackageProto.Package.Builder;
import com.factset.protobuf.stach.PackageProto.Package;

public class FdsApiClient extends ApiClient
{
  protected void performAdditionalClientConfiguration(ClientConfig clientConfig) {
    // uncomment following settings as needed
    // clientConfig.property( ClientProperties.PROXY_URI, "<proxyUrl>" );
    // clientConfig.connectorProvider( new ApacheConnectorProvider() );
  }
}

public class VaultEngineExample {
  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String VAULT_DEFAULT_DOCUMENT = "PA3_DOCUMENTS:DEFAULT";
  private static final String VAULT_DEFAULT_ACCOUNT = "Client:/analytics/data/US_MID_CAP_CORE.ACTM";
  private static final String COMPONENT_NAME = "Weights";
  private static final String COMPONENT_CATEGORY = "General / Positioning";

  public static void main(String[] args) throws InterruptedException {
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
      System.out.println("Configuration ID: " + configurationId);

      Calculation parameters = new Calculation();

      VaultCalculationParameters vaultItem = new VaultCalculationParameters();

      vaultItem.setComponentid(componentId);
      vaultItem.setConfigid(configurationId);

      VaultIdentifier account = new VaultIdentifier();
      account.setId(VAULT_DEFAULT_ACCOUNT);
      vaultItem.setAccount(account);

      VaultDateParameters dateParameters = new VaultDateParameters();
      dateParameters.setStartdate("20190830");
      dateParameters.setEnddate("20190905");
      dateParameters.setFrequency("Monthly");
      vaultItem.setDates(dateParameters);

      parameters.putVaultItem("1", vaultItem);

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
      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getVault().entrySet()) {
        if (calculationParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.FAILED) {
          System.out.println("CalculationId : " + calculationParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationParameters.getValue().getError());
          return;
        }
      }

      // Get Result of Successful Caculations
      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getVault().entrySet()) {
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

          Package result = (Package) builder.build();
          // To convert result to 2D tables.
          List<TableData> tables = StachExtensions.convertToTableFormat(result);
          System.out.println(tables.get(0)); // Prints the result in 2D table format.
          // Uncomment the following line to generate an Excel file
          // StachExtensions.generateExcel(result);
        }
      }
    } catch (ApiException e) {
      handleException("VaultEngineExample#Main", e);
      return;
    }
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
