package Api;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.FiabCalculationsApi;
import factset.analyticsapi.engines.models.FIABCalculationParameters;
import factset.analyticsapi.engines.models.FIABCalculationStatus;
import factset.analyticsapi.engines.models.FIABCalculationStatusSummary;
import factset.analyticsapi.engines.models.FIABDateParameters;
import factset.analyticsapi.engines.models.FIABIdentifier;

public class FiabInteractiveEngineApiTests {
  private static ApiClient apiClient;
  private FiabCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    apiInstance = new FiabCalculationsApi(apiClient);
  }

  private ApiResponse<Void> runCalculation() throws ApiException {
    final FIABCalculationParameters parameters = new FIABCalculationParameters();

    final FIABIdentifier fiabID = new FIABIdentifier();
    fiabID.setId(CommonParameters.FiabAccountId);
    parameters.setAccount(fiabID);
    final FIABDateParameters fiabDateParam = new FIABDateParameters();
    fiabDateParam.setEnddate(CommonParameters.FiabDate);
    fiabDateParam.setStartdate(CommonParameters.FiabDate);
    parameters.setDates(fiabDateParam);
    parameters.setFiabdocument(CommonParameters.FiabDocument);
    parameters.setFisettingsdocument(CommonParameters.FiabFiSettingsDocument);
    parameters.setMsl(CommonParameters.FiabMsl);   

    return apiInstance.runCalculationWithHttpInfo(parameters);
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException{
    ApiResponse<Void> response = null;
    Map<String, List<String>> headers = null;

    try {
      response = runCalculation();
      headers = response.getHeaders();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202", response.getStatusCode() == 202);

    ApiResponse<FIABCalculationStatus> resultStatus = null;
    String[] locationList = headers.get("Location").get(0).split("/");
    String requestId = locationList[locationList.length - 1];

    do {
      resultStatus = apiInstance.getCalculationByIdWithHttpInfo(requestId);
      List<String> cacheControl = headers.get("Cache-Control");
      if (cacheControl != null) {
        int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
        System.out.println("Sleeping for: " + maxAge + " seconds");
        Thread.sleep(maxAge * 1000L);
      } else {
        System.out.println("Sleeping for: 2 seconds");
        Thread.sleep(2 * 1000L);
      }	    
    }	while(resultStatus.getStatusCode() == 202); 

    Assert.assertTrue("Result Status returned should be 200", resultStatus.getStatusCode() == 200);
  }

  @Test
  public void getAllOutStandingRequestsSuccess() throws ApiException {
    ApiResponse<Void> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);
    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 1];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);
    ApiResponse<java.util.Map<String, FIABCalculationStatusSummary>> getFIABCalculationStatusSummary = null;

    try {

      getFIABCalculationStatusSummary = apiInstance.getCalculationStatusSummariesWithHttpInfo();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#getCalculationStatusSummariesWithHttpInfo", e);
    }

    Assert.assertTrue("Response should be 200 - Success.", getFIABCalculationStatusSummary.getStatusCode() == 200);
    Assert.assertTrue("Response data should not be null.", getFIABCalculationStatusSummary.getData() != null);
    Assert.assertTrue("Response data does not include the created calculation.",
        getFIABCalculationStatusSummary.getData().containsKey(id));	  
  }
}

