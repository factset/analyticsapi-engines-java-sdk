package Api;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

import com.fasterxml.jackson.core.JsonProcessingException;

public class VaultEngineInteractiveApiTests {

  private static ApiClient apiClient;
  private VaultCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.VaultPubUsername, CommonParameters.VaultPubPassword);
  }

  @Before
  public void before() {
    apiInstance = new VaultCalculationsApi(apiClient);
  }

  public String getComponentId() throws ApiException {
    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> components = ((ComponentSummaryRoot)componentsApi
        .getVaultComponents(CommonParameters.VAULT_DEFAULT_DOCUMENT)).getData();
    String componentId = components.entrySet().stream().findFirst().get().getKey();
    return componentId;
  }
  public String getConfigurationId() throws ApiException {
    ConfigurationsApi configurationsApi = new ConfigurationsApi(apiClient);
    Map<String, VaultConfigurationSummary> configurationsMap = ((VaultConfigurationSummaryRoot)configurationsApi
        .getVaultConfigurations(CommonParameters.VAULT_DEFAULT_ACCOUNT)).getData();
    String configurationId = configurationsMap.entrySet().stream().findFirst().get().getKey();
    return configurationId;
  }
  public VaultCalculationParameters createCalculationUnit(String compId, String configId) throws ApiException {
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
  public void enginesApiGetCalculationSuccess() throws ApiException, InterruptedException, JsonProcessingException {
    ApiResponse<Object> response = null;
    CalculationStatusRoot resultStatus = null;
    Map<String, List<String>> headers = null;    
    try {
      String id = getComponentId();
      String configId = getConfigurationId();
      VaultCalculationParameters calculationUnit = createCalculationUnit(id, configId);
      VaultCalculationParametersRoot vaultCalcParamRoot = new VaultCalculationParametersRoot();
      vaultCalcParamRoot.putDataItem("1", calculationUnit);
      response = apiInstance.postAndCalculateWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, vaultCalcParamRoot);
      headers = response.getHeaders();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 201 or 202",
        response.getStatusCode() == 201 || response.getStatusCode() == 202);

    ApiResponse<ObjectRoot> resultResponse = null;
    Object resultObject = null;

    switch(response.getStatusCode()) {
      case 201:
        resultObject = ((ObjectRoot)response.getData()).getData();
        headers = response.getHeaders();
        CalculationsHelper.validateCalculationResponse(headers, resultObject);
        break;
      case 202:
        String[] locationList = headers.get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 2];
        // Get Calculation Request Status
        ApiResponse<CalculationStatusRoot> resultStatusResponse = null;
        do {
          resultStatusResponse = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
          headers = resultStatusResponse.getHeaders();
          resultStatus = (CalculationStatusRoot)resultStatusResponse.getData();
          Assert.assertTrue("Get status response status code should be 200 or 202",
              resultStatusResponse.getStatusCode() == 200 || resultStatusResponse.getStatusCode() == 202);
          List<String> cacheControl = headers.get("Cache-Control");
          if (cacheControl != null) {
            int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
            System.out.println("Sleeping for: " + maxAge + " seconds");
            Thread.sleep(maxAge * 1000L);
          } else {
            System.out.println("Sleeping for: 2 seconds");
            Thread.sleep(2 * 1000L);
          }
        } while(resultStatusResponse.getStatusCode() == 202);
        for(CalculationUnitStatus unitStatus : resultStatus.getData().getUnits().values()) {
          String[] location = unitStatus.getResult().split("/");
          resultResponse = GetCalculationResult(location);
          headers = resultResponse.getHeaders();
          resultObject = ((ObjectRoot)resultResponse.getData()).getData();
        }
        CalculationsHelper.validateCalculationResponse(headers, resultObject);
        break;
    }
  }

  public ApiResponse<ObjectRoot> GetCalculationResult(String[] location) throws ApiException {
    ApiResponse<ObjectRoot> resultResponse = null;
    try {	  
      String calcId = location[location.length-4];
      String unitId = location[location.length-2];        	  
      resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(calcId, unitId);      
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
    }
    return resultResponse;  
  }

}
