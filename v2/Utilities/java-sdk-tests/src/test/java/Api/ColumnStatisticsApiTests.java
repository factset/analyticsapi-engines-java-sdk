package Api;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.v2.ApiClient;
import factset.analyticsapi.engines.v2.ApiException;
import factset.analyticsapi.engines.v2.ApiResponse;
import factset.analyticsapi.engines.v2.api.ColumnStatisticsApi;
import factset.analyticsapi.engines.v2.models.ColumnStatistic;

public class ColumnStatisticsApiTests {
  public static ApiClient apiClient;
  public ColumnStatisticsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new ColumnStatisticsApi(apiClient);
  }

  @Test
  public void getAllPAColumnStatistics() throws ApiException {
    ApiResponse<Map<String, ColumnStatistic>> getPAColumnStatisticsResponse = null;

    try {
      getPAColumnStatisticsResponse = apiInstance.getPAColumnStatisticsWithHttpInfo();

      Assert.assertTrue("Response should be 200 - Success", getPAColumnStatisticsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getPAColumnStatisticsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("ColumnStatisticsApi#getPAColumnStatisticsResponse", e);
    }
  }
}