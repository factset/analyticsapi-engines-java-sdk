package Api;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.v2.*;
import factset.analyticsapi.engines.v2.api.*;
import factset.analyticsapi.engines.v2.models.*;

public class DatesApiTests {
  public static ApiClient apiClient;
  public DatesApi datesApiInstance;
  public ComponentsApi componentsApiInstance;
  String enddate = "-1d";
  String startdate = "-1M";
  String account = "CLIENT:Analytics_api/test_account_do_not_delete.acct";

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    datesApiInstance = new DatesApi(apiClient);
    componentsApiInstance = new ComponentsApi(apiClient);
  }

  @Test
  public void convertPADateSuccess() throws ApiException {
    ApiResponse<Map<String, ComponentSummary>> getAllComponentsResponse = null;

    try {
      getAllComponentsResponse = componentsApiInstance
          .getPAComponentsWithHttpInfo(CommonParameters.PA_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getAllComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllComponentsResponse.getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getPAComponentsWithHttpInfo", e);
    }

    ApiResponse<DateParametersSummary> datesApiResponse = null;

    try {
      datesApiResponse = datesApiInstance.convertPADatesToAbsoluteFormatWithHttpInfo(enddate,
          getAllComponentsResponse.getData().entrySet().iterator().next().getKey(), account, startdate);

      Assert.assertTrue("Response should be 200 - Success", datesApiResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", datesApiResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("DatesApi#convertPADatesToAbsoluteFormatWithHttpInfo", e);
    }
  }

  @Test
  public void convertVaultDateSuccess() throws ApiException {
    ApiResponse<Map<String, ComponentSummary>> getAllComponentsResponse = null;

    try {
      getAllComponentsResponse = componentsApiInstance
          .getVaultComponentsWithHttpInfo(CommonParameters.VAULT_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getAllComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllComponentsResponse.getData().size() != 0);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getVaultComponentsWithHttpInfo", e);
    }

    ApiResponse<DateParametersSummary> datesApiResponse = null;

    try {
      datesApiResponse = datesApiInstance.convertVaultDatesToAbsoluteFormatWithHttpInfo(enddate,
          getAllComponentsResponse.getData().entrySet().iterator().next().getKey(), account, startdate);

      Assert.assertTrue("Response should be 200 - Success", datesApiResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", datesApiResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("DatesApi#convertVaultDatesToAbsoluteFormatWithHttpInfo", e);
    }
  }
}