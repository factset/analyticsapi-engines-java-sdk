package Api;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.v2.*;
import factset.analyticsapi.engines.v2.api.*;
import factset.analyticsapi.engines.v2.models.*;

public class GroupsApiTests {

  public static ApiClient apiClient;
  public GroupsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new GroupsApi(apiClient);
  }

  @Test
  public void getAllGroupsSuccess() throws ApiException {
    ApiResponse<Map<String, Group>> getAllGroupsResponse = null;

    try {
      getAllGroupsResponse = apiInstance.getPAGroupsWithHttpInfo();

      Assert.assertTrue("Response should be 200 - Success", getAllGroupsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllGroupsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("GroupsApi#getPAGroupsWithHttpInfo", e);
    }
  }
}