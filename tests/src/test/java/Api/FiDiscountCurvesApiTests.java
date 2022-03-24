package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class FiDiscountCurvesApiTests {

  private static ApiClient apiClient;
  private DiscountCurvesApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    apiInstance = new DiscountCurvesApi(apiClient);
  }

  @Test
  public void getAllFiDiscountCurvesSuccess() throws ApiException {
    ApiResponse<FIDiscountCurveInfoRoot> getAllFiDiscountCurvesResponse = null;

    try {
      getAllFiDiscountCurvesResponse = apiInstance.getAllFIDiscountCurvesWithHttpInfo(null);

      Assert.assertTrue("Response should be 200 - Success", getAllFiDiscountCurvesResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllFiDiscountCurvesResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("DiscountCurvesApi#getAllFIDiscountCurvesWithHttpInfo", e);
    }
  }
  
  @Test
  public void getAllFiDiscountCurves_Currency_INR_Success() throws ApiException {
    ApiResponse<FIDiscountCurveInfoRoot> getAllFiDiscountCurvesResponse = null;

    try {
      getAllFiDiscountCurvesResponse = apiInstance.getAllFIDiscountCurvesWithHttpInfo("INR");

      Assert.assertTrue("Response should be 200 - Success", getAllFiDiscountCurvesResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllFiDiscountCurvesResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("DiscountCurvesApi#getAllFIDiscountCurvesWithHttpInfo", e);
    }
  }
  
  @Test
  public void getAllFiDiscountCurves_Invalid_Currency() throws ApiException {
    try {
      apiInstance.getAllFIDiscountCurvesWithHttpInfo("InvalidCurrency");
    } catch (ApiException e) {
      Assert.assertTrue("Error getting discount curves for given currency", e.getCode() == 404);
    }
  }
}