package Api;

import org.junit.Assert;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class DatesApiTests {
  private static ApiClient apiClient;
  private DatesApi datesApiInstance;
  private ComponentsApi componentsApiInstance;
  String enddate = "-1d";
  String startdate = "-1M";
  String account = "CLIENT:Analytics_api/test_account_do_not_delete.acct";

  @Test
  public void convertPADateSuccess() throws ApiException {
    ApiResponse<ComponentSummaryRoot> getAllComponentsResponse = null;
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
    datesApiInstance = new DatesApi(apiClient);
    componentsApiInstance = new ComponentsApi(apiClient);

    try {
      getAllComponentsResponse = componentsApiInstance
          .getPAComponentsWithHttpInfo(CommonParameters.PA_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getAllComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllComponentsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getPAComponentsWithHttpInfo", e);
    }

    ApiResponse<DateParametersSummaryRoot> datesApiResponse = null;

    try {
      datesApiResponse = datesApiInstance.convertPADatesToAbsoluteFormatWithHttpInfo(enddate,
          getAllComponentsResponse.getData().getData().entrySet().iterator().next().getKey(), CommonParameters.DefaultPADatesAccount, startdate);

      Assert.assertTrue("Response should be 200 - Success", datesApiResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", datesApiResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("DatesApi#convertPADatesToAbsoluteFormatWithHttpInfo", e);
    }
  }

  @Test
  public void convertVaultDateSuccess() throws ApiException {
    ApiResponse<ComponentSummaryRoot> getAllComponentsResponse = null;
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
    datesApiInstance = new DatesApi(apiClient);
    componentsApiInstance = new ComponentsApi(apiClient);

    try {
      getAllComponentsResponse = componentsApiInstance
          .getVaultComponentsWithHttpInfo(CommonParameters.VAULT_DEFAULT_DOCUMENT);

      Assert.assertTrue("Response should be 200 - Success", getAllComponentsResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getAllComponentsResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("ComponentsApi#getVaultComponentsWithHttpInfo", e);
    }

    ApiResponse<DateParametersSummaryRoot> datesApiResponse = null;

    try {
      datesApiResponse = datesApiInstance.convertVaultDatesToAbsoluteFormatWithHttpInfo(enddate,
          getAllComponentsResponse.getData().getData().entrySet().iterator().next().getKey(), CommonParameters.DefaultVaultDatesAccount, startdate);

      Assert.assertTrue("Response should be 200 - Success", datesApiResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", datesApiResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("DatesApi#convertVaultDatesToAbsoluteFormatWithHttpInfo", e);
    }
  }
}