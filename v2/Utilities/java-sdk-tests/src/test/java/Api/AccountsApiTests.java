package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.v2.*;
import factset.analyticsapi.engines.v2.api.*;
import factset.analyticsapi.engines.v2.models.*;

public class AccountsApiTests {

  public static ApiClient apiClient;
  public AccountsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new AccountsApi(apiClient);
  }

  @Test
  public void getAccountListSuccess() throws ApiException {
    ApiResponse<AccountDirectories> getAccountListResponse = null;

    try {
      getAccountListResponse = apiInstance.getAccountsWithHttpInfo(CommonParameters.DEFAULT_LOOKUP_DIRECTORY);

      Assert.assertTrue("Response should be 200 - Success", getAccountListResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAccountListResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("AccountsApi#getAccountsWithHttpInfo", e);
    }

  }
}