package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class PricingSourcesApiTests {

  private static ApiClient apiClient;
  private PricingSourcesApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    apiInstance = new PricingSourcesApi(apiClient);
  }

  @Test
  public void getPAPricingSourcesSuccess() throws ApiException {
    ApiResponse<PAPricingSourceRoot> getPAPricingSourcesResponse = null;

    try {
    	getPAPricingSourcesResponse = apiInstance.getPAPricingSourcesWithHttpInfo(null,null,null);
      
      Assert.assertTrue("Response should be 200 - Success", getPAPricingSourcesResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getPAPricingSourcesResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("GroupsApi#getPAPricingSourcesWithHttpInfo", e);
    }
  }
}