package Api;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class VaultEngineApiTests {

  private static ApiClient apiClient;
  private VaultCalculationsApi vaultCalculations;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.VaultPubUsername, CommonParameters.VaultPubPassword);
  }

  @Before
  public void before() {
    vaultCalculations = new VaultCalculationsApi(apiClient);
  }

  private String getComponentId() throws ApiException {
    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> components = ((ComponentSummaryRoot)componentsApi
        .getVaultComponents(CommonParameters.VAULT_DEFAULT_DOCUMENT)).getData();
    String componentId = components.entrySet().stream().findFirst().get().getKey();
    return componentId;
  }
  private String getConfigurationId() throws ApiException {
    ConfigurationsApi configurationsApi = new ConfigurationsApi(apiClient);
    Map<String, VaultConfigurationSummary> configurationsMap = ((VaultConfigurationSummaryRoot)configurationsApi
        .getVaultConfigurations(CommonParameters.VAULT_DEFAULT_ACCOUNT)).getData();
    String configurationId = configurationsMap.entrySet().stream().findFirst().get().getKey();
    return configurationId;
  }
  private VaultCalculationParameters createCalculationUnit(String compId, String configId) throws ApiException {
    VaultCalculationParameters vaultItem = new VaultCalculationParameters();
    vaultItem.setConfigid(configId);
    vaultItem.setComponentid(compId);
    VaultIdentifier account = new VaultIdentifier();
    account.setId(CommonParameters.VAULT_DEFAULT_ACCOUNT);
    vaultItem.setAccount(account);

    VaultDateParameters dateParameters = new VaultDateParameters();
    dateParameters.setStartdate(CommonParameters.VAULT_START_DATE_FIRST);
    dateParameters.setEnddate(CommonParameters.VAULT_END_DATE_FIRST);
    dateParameters.setFrequency(CommonParameters.VAULT_FREQUENCY_DATE_MONTHLY);
    vaultItem.setDates(dateParameters);

    return vaultItem;
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException {
    ApiResponse<Object> createResponse = null;    
    try {
      String id = getComponentId();
      String configId = getConfigurationId();
      VaultCalculationParameters unit1 = createCalculationUnit(id, configId);
      VaultCalculationParameters unit2 = createCalculationUnit(id, configId);
      VaultCalculationParametersRoot vaultCalcParamRoot = new VaultCalculationParametersRoot();
      vaultCalcParamRoot.putDataItem("1", unit1);
      vaultCalcParamRoot.putDataItem("2", unit2);
      createResponse = vaultCalculations.postAndCalculateWithHttpInfo(null, null, vaultCalcParamRoot);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#createWithHttpInfo", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<CalculationStatusRoot> getStatus = null;
    CalculationStatusRoot status = null;

    try {
      do {
        getStatus = vaultCalculations.getCalculationStatusByIdWithHttpInfo(id);
        status = (CalculationStatusRoot)getStatus.getData();
        if(getStatus.getStatusCode() == 200)
          break;
        Assert.assertTrue("Response Data should not be null.", getStatus != null);
        Assert.assertTrue("Response Data should have calculation status as executing or queued.",
            status.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
            || status.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING);
        Assert.assertTrue("Response Data should have at least one calculation status as executing or queued or success.",
        		status.getData().getUnits().values().stream()
                .filter(f -> f.getStatus() == CalculationUnitStatus.StatusEnum.EXECUTING
                    || f.getStatus() == CalculationUnitStatus.StatusEnum.QUEUED 
                    || f.getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS)
                .count() > 0);

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
      } while(getStatus.getStatusCode() == 202);

    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#getCalculationStatusByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Response Data should have calculation status as completed.",
        status.getData().getStatus() == CalculationStatus.StatusEnum.COMPLETED);
    Assert.assertTrue("Response Data should have all calculations status as succeeded.", status.getData().getUnits()
        .values().stream().filter(f -> f.getStatus() != CalculationUnitStatus.StatusEnum.SUCCESS).count() == 0);
    Assert.assertTrue("Response Data should have all calculation results.",
        status.getData().getUnits().values().stream().filter(f -> f.getResult() == null).count() == 0);

    ApiResponse<ObjectRoot> resultResponse = null;
    Object result = null;

    for (CalculationUnitStatus calculationParameters : status.getData().getUnits().values()) {
      try {
        String[] location = calculationParameters.getResult().split("/");
        String calcId = location[location.length-4];
        String unitId = location[location.length-2];

        resultResponse = vaultCalculations.getCalculationUnitResultByIdWithHttpInfo(calcId, unitId);
        result = ((ObjectRoot)resultResponse.getData()).getData();
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
      }

      Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
      Assert.assertTrue("Result response data should not be null.", resultResponse.getData() != null);
      CalculationsHelper.validateCalculationResponse(resultResponse.getHeaders(), result);
    }
  }

  @Test
  public void enginesApiDeleteCalculationSuccess() throws ApiException {
    ApiResponse<Object> createResponse = null;
    try {
      String id = getComponentId();
      String configId = getConfigurationId();
      VaultCalculationParameters unit1 = createCalculationUnit(id, configId);
      VaultCalculationParameters unit2 = createCalculationUnit(id, configId);
      VaultCalculationParametersRoot vaultCalcParamRoot = new VaultCalculationParametersRoot();
      VaultIdentifier accountId = new VaultIdentifier();
      accountId.setId(CommonParameters.VAULT_SECONDARY_ACCOUNT);
      unit2.setAccount(accountId);
      vaultCalcParamRoot.putDataItem("1", unit1);
      vaultCalcParamRoot.putDataItem("2", unit2);
      createResponse = vaultCalculations.postAndCalculateWithHttpInfo(null, null, vaultCalcParamRoot);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#createWithHttpInfo", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = vaultCalculations.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
  }
}
