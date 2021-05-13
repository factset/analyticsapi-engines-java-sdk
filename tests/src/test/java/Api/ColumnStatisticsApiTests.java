package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.ColumnStatisticsApi;
import factset.analyticsapi.engines.models.ColumnStatisticRoot;

public class ColumnStatisticsApiTests {
  private static ApiClient apiClient;
  private ColumnStatisticsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    apiInstance = new ColumnStatisticsApi(apiClient);
  }

  @Test
  public void getAllPAColumnStatistics() throws ApiException {
    ApiResponse<ColumnStatisticRoot> getPAColumnStatisticsResponse = null;

    try {
      getPAColumnStatisticsResponse = apiInstance.getPAColumnStatisticsWithHttpInfo();

      Assert.assertTrue("Response should be 200 - Success", getPAColumnStatisticsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getPAColumnStatisticsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("ColumnStatisticsApi#getPAColumnStatisticsResponse", e);
    }
  }
}