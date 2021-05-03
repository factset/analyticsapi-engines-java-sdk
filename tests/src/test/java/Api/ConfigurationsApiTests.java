package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class ConfigurationsApiTests {

  public static ApiClient apiClient;
  public ConfigurationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.VaultPubUsername, CommonParameters.VaultPubPassword);
  }

  @Before
  public void before() {
    apiInstance = new ConfigurationsApi(apiClient);
  }

  @Test
  public void getAllConfigurationsSuccess() throws ApiException {
    ApiResponse<VaultConfigurationSummaryRoot> getAllConfigurationsResponse = null;

    try {
      getAllConfigurationsResponse = apiInstance
          .getVaultConfigurationsWithHttpInfo(CommonParameters.VAULT_DEFAULT_ACCOUNT);

      Assert.assertTrue("Response should be 200 - Success", getAllConfigurationsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllConfigurationsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("VaultConfigurationsApi#getVaultConfigurationsWithHttpInfo", e);
    }
  }

  @Test
  public void getVaultConfigurationsByIdSuccess() throws ApiException {
    ApiResponse<VaultConfigurationSummaryRoot> getAllConfigurationsResponse = null;

    try {
      getAllConfigurationsResponse = apiInstance
          .getVaultConfigurationsWithHttpInfo(CommonParameters.VAULT_DEFAULT_ACCOUNT);

      Assert.assertTrue("Response should be 200 - Success", getAllConfigurationsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllConfigurationsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("VaultConfigurationsApi#getVaultConfigurationsWithHttpInfo", e);
    }

    ApiResponse<VaultConfigurationRoot> getConfigurationDetailsResponse = null;

    try {
      getConfigurationDetailsResponse = apiInstance.getVaultConfigurationByIdWithHttpInfo(
          getAllConfigurationsResponse.getData().getData().entrySet().iterator().next().getKey());

      Assert.assertTrue("Response should be 200 - Success", getConfigurationDetailsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getConfigurationDetailsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("VaultConfigurationsApi#getVaultConfigurationByIdWithHttpInfo", e);
    }
  }
}