package examples;

import java.util.List;
import java.util.Map;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

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

public class MultipleEnginesExample {
  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  private static final String VAULT_DEFAULT_DOCUMENT = "PA3_DOCUMENTS:DEFAULT";
  private static final String VAULT_DEFAULT_ACCOUNT = "Client:/analytics/data/US_MID_CAP_CORE.ACTM";
  private static final String SPAR_DEFAULT_DOCUMENT = "pmw_root:/spar_documents/Factset Default Document";

  public static void main(String[] args) throws InterruptedException {
    try {
      Calculation parameters = new Calculation();

      // Build PA Calculation Parameters List
      PACalculationParameters paItem = getPAItem();
      parameters.putPaItem("1", paItem);

      // Build Vault Calculation Parameters List
      VaultCalculationParameters vaultItem = getVaultItem();
      parameters.putVaultItem("2", vaultItem);

      // Build SPAR Calculation Parameters List
      SPARCalculationParameters sparItem = getSparItem();
      parameters.putSparItem("3", sparItem);

      // Run Calculation Request
      CalculationsApi apiInstance = new CalculationsApi(getApiClient());
      ApiResponse<Void> createResponse = null;

      createResponse = apiInstance.runCalculationWithHttpInfo(parameters);

      String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
      String requestId = locationList[locationList.length - 1];

      // Get Calculation Request Status
      ApiResponse<CalculationStatus> getStatus = null;

      while (getStatus == null || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
          || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING) {
        if (getStatus != null) {
          List<String> cacheControl = getStatus.getHeaders().get("Cache-Control");
          if (cacheControl != null) {
            int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
            System.out.println("Sleeping for: " + maxAge + " seconds");
            Thread.sleep(maxAge * 1000);
          } else {
            System.out.println("Sleeping for: 2 seconds");
            Thread.sleep(2 * 1000);
          }
        }
        getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
      }

      System.out.println("Calculation Completed!!!");

      // Check for Failed Calculations
      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getPa().entrySet()) {
        if (calculationParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.FAILED) {
          System.out.println("PA Calculation : " + calculationParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationParameters.getValue().getError());
          return;
        }
      }

      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getSpar().entrySet()) {
        if (calculationParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.FAILED) {
          System.out.println("SPAR Calculation : " + calculationParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationParameters.getValue().getError());
          return;
        }
      }

      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getVault().entrySet()) {
        if (calculationParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.FAILED) {
          System.out.println("Vault Calculation : " + calculationParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationParameters.getValue().getError());
          return;
        }
      }

      // Get Result of Successful Calculations
      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getPa().entrySet()) {
        printResult(calculationParameters);
      }

      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getSpar().entrySet()) {
        printResult(calculationParameters);
      }

      for (Map.Entry<String, CalculationUnitStatus> calculationParameters : getStatus.getData().getVault().entrySet()) {
        printResult(calculationParameters);
      }
    } catch (ApiException e) {
      handleException("MultipleEnginesExample#Main", e);
      return;
    }
  }

  private static void printResult(Map.Entry<String, CalculationUnitStatus> calculationParameters) throws ApiException {
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
      System.out.println(tables.get(0)); // Prints the result in 2D table format.
      // Uncomment the following line to generate an Excel file
      // StachExtensions.generateExcel(result);
    }
  }

  private static SPARCalculationParameters getSparItem() throws ApiException {
    SPARCalculationParameters sparItem = new SPARCalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(getApiClient());
    Map<String, ComponentSummary> components = componentsApi.getSPARComponents(SPAR_DEFAULT_DOCUMENT);
    String componentId = components.entrySet().iterator().next().getKey();
    System.out.println("ComponentID: " + componentId);
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

    return sparItem;
  }

  private static VaultCalculationParameters getVaultItem() throws ApiException {
    VaultCalculationParameters vaultItem = new VaultCalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(getApiClient());
    Map<String, ComponentSummary> components = componentsApi.getVaultComponents(VAULT_DEFAULT_DOCUMENT);
    String componentId = components.entrySet().iterator().next().getKey();
    System.out.println("ComponentID: " + componentId);
    vaultItem.setComponentid(componentId);

    ConfigurationsApi configurationsApi = new ConfigurationsApi(getApiClient());
    Map<String, VaultConfigurationSummary> configurationsMap = configurationsApi.getVaultConfigurations(VAULT_DEFAULT_ACCOUNT);
    String configurationId = configurationsMap.entrySet().iterator().next().getKey();
    System.out.println("Configuration ID: " + configurationId);
    vaultItem.setConfigid(configurationId);

    VaultIdentifier account = new VaultIdentifier();
    account.setId(VAULT_DEFAULT_ACCOUNT);
    vaultItem.setAccount(account);

    VaultDateParameters dateParameters = new VaultDateParameters();
    dateParameters.setStartdate("FIRST_REPOSITORY");
    dateParameters.setEnddate("LAST_REPOSITORY");
    dateParameters.setFrequency("Monthly");
    vaultItem.setDates(dateParameters);

    return vaultItem;
  }

  private static PACalculationParameters getPAItem() throws ApiException {
    PACalculationParameters paItem = new PACalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(getApiClient());
    Map<String, ComponentSummary> components = componentsApi.getPAComponents(PA_DEFAULT_DOCUMENT);
    String componentId = components.entrySet().iterator().next().getKey();
    System.out.println("ComponentID: " + componentId);
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

    return paItem;
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
