package Api;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class ColumnsApiTests {
  public static ApiClient apiClient;
  public ColumnsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(Engine.PA);
  }

  @Before
  public void before() {
    apiInstance = new ColumnsApi(apiClient);
  }

  @Test
  public void getPAColumns() throws ApiException {
    ApiResponse<ColumnSummaryRoot> getPAColumnsResponse = null;

    try {
      getPAColumnsResponse = apiInstance.getPAColumnsWithHttpInfo(null, null, null);

      Assert.assertTrue("Response should be 200 - Success", getPAColumnsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getPAColumnsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("ColumnsApi#getPAColumnsWithHttpInfo", e);
    }
  }

  @Test
  public void getPAColumnById() throws ApiException {
    ApiResponse<ColumnSummaryRoot> getPAColumnsResponse = null;
    Map<String, ColumnSummary> paColumnData = null;

    try {
      getPAColumnsResponse = apiInstance.getPAColumnsWithHttpInfo(null, null, null);
      paColumnData = ((ColumnSummaryRoot)getPAColumnsResponse.getData()).getData();
      Assert.assertTrue("Response should be 200 - Success", getPAColumnsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getPAColumnsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("ColumnsApi#getPAColumnsWithHttpInfo", e);
    }

    ApiResponse<ColumnRoot> getPAColumnByIdResponse = null;

    try {
      getPAColumnByIdResponse = apiInstance
          .getPAColumnByIdWithHttpInfo(paColumnData.entrySet().iterator().next().getKey());

      Assert.assertTrue("Response should be 200 - Success", getPAColumnByIdResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getPAColumnByIdResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("ColumnsApi#getPAColumnByIdWithHttpInfo", e);
    }
  }
}