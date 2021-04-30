package Api;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class CurrenciesApiTests {

  public static ApiClient apiClient;
  public CurrenciesApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(Engine.PA);
  }

  @Before
  public void before() {
    apiInstance = new CurrenciesApi(apiClient);
  }

  @Test
  public void getAllPACurrenciesSuccess() throws ApiException {
    ApiResponse<CurrencyRoot> getAllCurrenciesResponse = null;

    try {
      getAllCurrenciesResponse = apiInstance.getCurrenciesWithHttpInfo();

      Assert.assertTrue("Response should be 200 - Success", getAllCurrenciesResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllCurrenciesResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("CurrenciesApi#getPACurrenciesWithHttpInfo", e);
    }
  }
}