package Api;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.v2.*;
import factset.analyticsapi.engines.v2.api.*;
import factset.analyticsapi.engines.v2.models.*;

import com.factset.protobuf.stach.PackageProto.Package.Builder;
import com.factset.protobuf.stach.PackageProto.Package;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;

public class MultipleEnginesApiTests {

  public static ApiClient apiClient;
  public CalculationsApi apiInstance;
  public UtilityApi utilityApiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new CalculationsApi(apiClient);
    utilityApiInstance = new UtilityApi(apiClient);
  }

  public ApiResponse<Void> runCalculation() throws ApiException {
    Calculation parameters = new Calculation();

    PACalculationParameters paItem = new PACalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> paComponents = componentsApi.getPAComponents(CommonParameters.PA_DEFAULT_DOCUMENT);
    paItem.setComponentid(paComponents.entrySet().stream().findFirst().get().getKey());

    PAIdentifier accountPaIdentifier = new PAIdentifier();
    accountPaIdentifier.setId(CommonParameters.PA_BENCHMARK_SP500);
    paItem.addAccountsItem(accountPaIdentifier);

    PAIdentifier benchmarkPaIdentifier = new PAIdentifier();
    benchmarkPaIdentifier.setId(CommonParameters.PA_BENCHMARK_R1000);
    paItem.addBenchmarksItem(benchmarkPaIdentifier);

    parameters.putPaItem("1", paItem);

    SPARCalculationParameters sparItem = new SPARCalculationParameters();

    Map<String, ComponentSummary> sparComponents = componentsApi
        .getSPARComponents(CommonParameters.SPAR_DEFAULT_DOCUMENT);
    sparItem.setComponentid(sparComponents.entrySet().stream().findFirst().get().getKey());

    SPARIdentifier accountSparIdentifier = new SPARIdentifier();
    accountSparIdentifier.setId(CommonParameters.SPAR_BENCHMARK_R1000);
    accountSparIdentifier.setPrefix(CommonParameters.SPAR_BENCHMARK_RUSSELL_PREFIX);
    accountSparIdentifier.setReturntype(CommonParameters.SPAR_BENCHMARK_RUSSELL_RETURN);
    sparItem.addAccountsItem(accountSparIdentifier);

    SPARIdentifier benchmarkIdentifier = new SPARIdentifier();
    benchmarkIdentifier.setId(CommonParameters.SPAR_BENCHMARK_R2000);
    benchmarkIdentifier.setPrefix(CommonParameters.SPAR_BENCHMARK_RUSSELL_PREFIX);
    benchmarkIdentifier.setReturntype(CommonParameters.SPAR_BENCHMARK_RUSSELL_RETURN);
    sparItem.setBenchmark(benchmarkIdentifier);

    parameters.putSparItem("2", sparItem);

    VaultCalculationParameters vaultItem = new VaultCalculationParameters();

    Map<String, ComponentSummary> vaultComponents = componentsApi
        .getVaultComponents(CommonParameters.VAULT_DEFAULT_DOCUMENT);
    vaultItem.setComponentid(vaultComponents.entrySet().stream().findFirst().get().getKey());

    ConfigurationsApi configurationsApi = new ConfigurationsApi(apiClient);
    Map<String, VaultConfigurationSummary> configurationsMap = configurationsApi
        .getVaultConfigurations(CommonParameters.VAULT_DEFAULT_ACCOUNT);
    vaultItem.setConfigid(configurationsMap.entrySet().stream().findFirst().get().getKey());

    VaultIdentifier account = new VaultIdentifier();
    account.setId(CommonParameters.VAULT_DEFAULT_ACCOUNT);
    vaultItem.setAccount(account);

    VaultDateParameters dateParameters = new VaultDateParameters();
    dateParameters.setStartdate(CommonParameters.VAULT_START_DATE_FIRST);
    dateParameters.setEnddate(CommonParameters.VAULT_END_DATE_FIRST);
    dateParameters.setFrequency(CommonParameters.VAULT_FREQUENCY_DATE_MONTHLY);
    vaultItem.setDates(dateParameters);

    parameters.putVaultItem("3", vaultItem);

    return apiInstance.runCalculationWithHttpInfo(parameters);
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException {
    ApiResponse<Void> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 1];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<CalculationStatus> getStatus = null;

    while (getStatus == null || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
        || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING) {
      if (getStatus != null) {
        Assert.assertTrue("Response Data should not be null.", getStatus != null);
        Assert.assertTrue("Response Data should have calculation status as executing or queued.",
            getStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
                || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING);

        if (getStatus.getHeaders().containsKey("cache-control")) {
          int maxAge = Integer.parseInt(getStatus.getHeaders().get("cache-control").get(0).split("=")[1]);
          try {
            System.out.println("\n **** Waiting for " + maxAge + " seconds **** \n");
            TimeUnit.SECONDS.sleep(maxAge);
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
        } else {
          int waitTimeInSeconds = 5;
          try {
            System.out.println("\n **** Waiting for " + waitTimeInSeconds + " seconds **** \n");
            TimeUnit.SECONDS.sleep(waitTimeInSeconds);
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
        }
      }
      try {
        getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(id);
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getCalculationStatusByIdWithHttpInfo", e);
      }
    }

    Assert.assertTrue("Response Data should have calculation status as completed.",
        getStatus.getData().getStatus() == CalculationStatus.StatusEnum.COMPLETED);
    Assert.assertTrue("Response Data should have all PA calculations status as succeeded.", getStatus.getData().getPa()
        .values().stream().filter(f -> f.getStatus() != CalculationUnitStatus.StatusEnum.SUCCESS).count() == 0);
    Assert.assertTrue("Response Data should have all Spar calculations status as succeeded.", getStatus.getData()
        .getSpar().values().stream().filter(f -> f.getStatus() != CalculationUnitStatus.StatusEnum.SUCCESS).count() == 0);
    Assert.assertTrue("Response Data should have all Vault calculations status as succeeded.", getStatus.getData()
        .getVault().values().stream().filter(f -> f.getStatus() != CalculationUnitStatus.StatusEnum.SUCCESS).count() == 0);
    Assert.assertTrue("Response Data should have all PA calculation results.",
        getStatus.getData().getPa().values().stream().filter(f -> f.getResult() == null).count() == 0);
    Assert.assertTrue("Response Data should have all SPAR calculation results.",
        getStatus.getData().getSpar().values().stream().filter(f -> f.getResult() == null).count() == 0);
    Assert.assertTrue("Response Data should have all Vault calculation results.",
        getStatus.getData().getVault().values().stream().filter(f -> f.getResult() == null).count() == 0);

    for (CalculationUnitStatus calculationParameters : getStatus.getData().getPa().values()) {
      GetPackage(calculationParameters);
    }

    for (CalculationUnitStatus calculationParameters : getStatus.getData().getSpar().values()) {
      GetPackage(calculationParameters);
    }

    for (CalculationUnitStatus calculationParameters : getStatus.getData().getVault().values()) {
      GetPackage(calculationParameters);
    }
  }

  private Package GetPackage(CalculationUnitStatus calculationParameters) throws ApiException {
    ApiResponse<String> resultResponse = null;
    try {
      resultResponse = utilityApiInstance.getByUrlWithHttpInfo(calculationParameters.getResult());
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
    }

    Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
    Assert.assertTrue("Result response data should not be null.", resultResponse.getData() != null);

    Builder builder = Package.newBuilder();
    try {
      JsonFormat.parser().ignoringUnknownFields().merge(resultResponse.getData(), builder);
    } catch (InvalidProtocolBufferException e) {
      System.out.println("Error while deserializing the response");
      e.printStackTrace();
    }
    Package result = (Package) builder.build();
    Assert.assertTrue("Response should be of Package type.", result instanceof Package);
    return result;
  }

  @Test
  public void enginesApiDeleteCalculationSuccess() throws ApiException {
    ApiResponse<Void> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 1];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = apiInstance.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
  }

  @Test
  public void getAllOutStandingRequestsSuccess() throws ApiException {
    ApiResponse<Void> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 1];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Map<String, CalculationStatusSummary>> getAllOutstandingRequestsResponse = null;

    try {
      getAllOutstandingRequestsResponse = apiInstance.getCalculationStatusSummariesWithHttpInfo();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#getCalculationStatusSummariesWithHttpInfo", e);
    }

    Assert.assertTrue("Response should be 200 - Success.", getAllOutstandingRequestsResponse.getStatusCode() == 200);
    Assert.assertTrue("Respose data should not be null.", getAllOutstandingRequestsResponse.getData() != null);
    Assert.assertTrue("Response data does not include the created calculation.",
        getAllOutstandingRequestsResponse.getData().containsKey(id));

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = apiInstance.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
  }
}
