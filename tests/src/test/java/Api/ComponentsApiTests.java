package Api;

import org.junit.Assert;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class ComponentsApiTests {
  public static ApiClient apiClient;
  public ComponentsApi apiInstance;

  /*************************************************************************/
  /********************* PA Components Test Cases ***********************/
  /*************************************************************************/

  @Test
  public void getAllPAComponentsSuccess() throws ApiException {
    ApiResponse<ComponentSummaryRoot> getAllComponentsResponse = null;
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
    apiInstance = new ComponentsApi(apiClient);

    try {
      getAllComponentsResponse = apiInstance.getPAComponentsWithHttpInfo(CommonParameters.PA_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getAllComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllComponentsResponse.getData().getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getPAComponentsWithHttpInfo", e);
    }
  }

  @Test
  public void getPAComponentByIdSuccess() throws ApiException {
    ApiResponse<ComponentSummaryRoot> getAllComponentsResponse = null;
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
    apiInstance = new ComponentsApi(apiClient);

    try {
      getAllComponentsResponse = apiInstance.getPAComponentsWithHttpInfo(CommonParameters.PA_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getAllComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllComponentsResponse.getData().getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getPAComponentsWithHttpInfo", e);
    }

    ApiResponse<PAComponentRoot> getByIdResponse = null;

    try {
      getByIdResponse = apiInstance
          .getPAComponentByIdWithHttpInfo(getAllComponentsResponse.getData().getData().entrySet().iterator().next().getKey());

      Assert.assertTrue("Response should be 200 - Success", getByIdResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getByIdResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getPAComponentByIdWithHttpInfo", e);
    }
  }

  /*************************************************************************/
  /********************* Vault Components Test Cases ***********************/
  /*************************************************************************/

  @Test
  public void getAllVaultComponentsSuccess() throws ApiException {
    ApiResponse<ComponentSummaryRoot> getVaultComponentsResponse = null;
    apiClient = CommonFunctions.buildApiClient(CommonParameters.VaultPubUsername, CommonParameters.VaultPubPassword);
    apiInstance = new ComponentsApi(apiClient);

    try {
      getVaultComponentsResponse = apiInstance
          .getVaultComponentsWithHttpInfo(CommonParameters.VAULT_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getVaultComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getVaultComponentsResponse.getData().getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getVaultComponentsWithHttpInfo", e);
    }
  }

  @Test
  public void getByVaultComponentIdSuccess() throws ApiException {
    ApiResponse<ComponentSummaryRoot> getVaultComponentsResponse = null;
    apiClient = CommonFunctions.buildApiClient(CommonParameters.VaultPubUsername, CommonParameters.VaultPubPassword);
    apiInstance = new ComponentsApi(apiClient);

    try {
      getVaultComponentsResponse = apiInstance
          .getVaultComponentsWithHttpInfo(CommonParameters.VAULT_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getVaultComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getVaultComponentsResponse.getData().getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getVaultComponentsWithHttpInfo", e);
    }

    ApiResponse<VaultComponentRoot> getVaultByIdResponse = null;

    try {
      getVaultByIdResponse = apiInstance.getVaultComponentByIdWithHttpInfo(
          getVaultComponentsResponse.getData().getData().entrySet().iterator().next().getKey());

      Assert.assertTrue("Response should be 200 - Success", getVaultByIdResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getVaultByIdResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getVaultComponentByIdWithHttpInfo", e);
    }
  }

  /*************************************************************************/
  /********************* SPAR Components Test Cases ***********************/
  /*************************************************************************/

  @Test
  public void getAllSPARComponentsSuccess() throws ApiException {
    ApiResponse<ComponentSummaryRoot> getSPARComponentsResponse = null;
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
    apiInstance = new ComponentsApi(apiClient);

    try {
      getSPARComponentsResponse = apiInstance.getSPARComponentsWithHttpInfo(CommonParameters.SPAR_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getSPARComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getSPARComponentsResponse.getData().getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getSPARComponentsWithHttpInfo", e);
    }
  }

}