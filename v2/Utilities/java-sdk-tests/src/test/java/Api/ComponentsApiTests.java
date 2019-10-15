package Api;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.v2.*;
import factset.analyticsapi.engines.v2.api.*;
import factset.analyticsapi.engines.v2.models.*;

public class ComponentsApiTests {
  public static ApiClient apiClient;
  public ComponentsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new ComponentsApi(apiClient);
  }

  /*************************************************************************/
  /********************* PA Components Test Cases ***********************/
  /*************************************************************************/

  @Test
  public void getAllPAComponentsSuccess() throws ApiException {
    ApiResponse<Map<String, ComponentSummary>> getAllComponentsResponse = null;

    try {
      getAllComponentsResponse = apiInstance.getPAComponentsWithHttpInfo(CommonParameters.PA_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getAllComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllComponentsResponse.getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getPAComponentsWithHttpInfo", e);
    }
  }

  @Test
  public void getPAComponentByIdSuccess() throws ApiException {
    ApiResponse<Map<String, ComponentSummary>> getAllComponentsResponse = null;

    try {
      getAllComponentsResponse = apiInstance.getPAComponentsWithHttpInfo(CommonParameters.PA_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getAllComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllComponentsResponse.getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getPAComponentsWithHttpInfo", e);
    }

    ApiResponse<PAComponent> getByIdResponse = null;

    try {
      getByIdResponse = apiInstance
          .getPAComponentByIdWithHttpInfo(getAllComponentsResponse.getData().entrySet().iterator().next().getKey());

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
    ApiResponse<Map<String, ComponentSummary>> getVaultComponentsResponse = null;

    try {
      getVaultComponentsResponse = apiInstance
          .getVaultComponentsWithHttpInfo(CommonParameters.VAULT_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getVaultComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getVaultComponentsResponse.getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getVaultComponentsWithHttpInfo", e);
    }
  }

  @Test
  public void getByVaultComponentIdSuccess() throws ApiException {
    ApiResponse<Map<String, ComponentSummary>> getVaultComponentsResponse = null;

    try {
      getVaultComponentsResponse = apiInstance
          .getVaultComponentsWithHttpInfo(CommonParameters.VAULT_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getVaultComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getVaultComponentsResponse.getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getVaultComponentsWithHttpInfo", e);
    }

    ApiResponse<VaultComponent> getVaultByIdResponse = null;

    try {
      getVaultByIdResponse = apiInstance.getVaultComponentByIdWithHttpInfo(
          getVaultComponentsResponse.getData().entrySet().iterator().next().getKey());

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
    ApiResponse<Map<String, ComponentSummary>> getSPARComponentsResponse = null;

    try {
      getSPARComponentsResponse = apiInstance.getSPARComponentsWithHttpInfo(CommonParameters.SPAR_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getSPARComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getSPARComponentsResponse.getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getSPARComponentsWithHttpInfo", e);
    }
  }

}