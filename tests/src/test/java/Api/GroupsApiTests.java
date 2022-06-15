package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class GroupsApiTests {

  private static ApiClient apiClient;
  private GroupsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    apiInstance = new GroupsApi(apiClient);
  }

  @Test
  public void getAllGroupsSuccess() throws ApiException {
    ApiResponse<GroupRoot> getAllGroupsResponse = null;

    try {
      getAllGroupsResponse = apiInstance.getPAGroupsWithHttpInfo();

      Assert.assertTrue("Response should be 200 - Success", getAllGroupsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllGroupsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("GroupsApi#getPAGroupsWithHttpInfo", e);
    }
  }
  
  @Test
  public void getAllGroupingFrequenciesSuccess() throws ApiException {
    ApiResponse<FrequencyRoot> getAllGroupingFrequenciesResponse = null;

    try {
      getAllGroupingFrequenciesResponse = apiInstance.getPAGroupingFrequenciesWithHttpInfo();

      Assert.assertTrue("Response should be 200 - Success", getAllGroupingFrequenciesResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllGroupingFrequenciesResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("GroupsApi#getPAGroupingFrequenciesWithHttpInfo", e);
    }
  }
}