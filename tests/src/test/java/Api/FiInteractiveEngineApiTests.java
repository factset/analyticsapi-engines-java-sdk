package Api;

import java.util.ArrayList;
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
import factset.analyticsapi.engines.api.FiCalculationsApi;
import factset.analyticsapi.engines.models.FICalculationParameters;
import factset.analyticsapi.engines.models.FICalculationParametersRoot;
import factset.analyticsapi.engines.models.FIJobSettings;
import factset.analyticsapi.engines.models.FISecurity;
import factset.analyticsapi.engines.models.ObjectRoot;


public class FiInteractiveEngineApiTests {
  private static ApiClient apiClient;
  private FiCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.OptimizerUsername, CommonParameters.OptimizerPassword);
  }

  @Before
  public void before() {
    apiInstance = new FiCalculationsApi(apiClient);
  }

  private FICalculationParameters createUnitCalculation() throws ApiException {
    final FICalculationParameters parameters = new FICalculationParameters();

    final FISecurity securities = new FISecurity();
    securities.setCalcFromMethod(CommonParameters.FICalcFromMethod);
    securities.setCalcFromValue(CommonParameters.FICalcFromValue);
    securities.setFace(CommonParameters.FIFaceValue);
    securities.setSettlement(CommonParameters.FISettlement);
    securities.setDiscountCurve(CommonParameters.FIDiscountCurve);
    securities.setSymbol(CommonParameters.FISymbol);
    parameters.addSecuritiesItem(securities);

    ArrayList<String> calc = new ArrayList<String>();
    calc.add(CommonParameters.FICalculations);
    parameters.setCalculations(calc);

    final FIJobSettings jobSettings = new FIJobSettings();
    jobSettings.setAsOfDate(CommonParameters.FIAsOfDate);
    parameters.setJobSettings(jobSettings);

    FICalculationParametersRoot fiCalcParam = new FICalculationParametersRoot();
    fiCalcParam.data(parameters);
    return parameters;
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException{
    ApiResponse<Object> response = null;
    Map<String, List<String>> headers = null;    
    try {
      FICalculationParameters calculationUnit = createUnitCalculation();
      FICalculationParametersRoot fiCalcParam = new FICalculationParametersRoot();
      fiCalcParam.setData(calculationUnit);
      response = apiInstance.postAndCalculateWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, fiCalcParam);
      headers = response.getHeaders();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 201 or 202",
        response.getStatusCode() == 201 || response.getStatusCode() == 202);
    Object resultObject = null;

    switch(response.getStatusCode()) {
      case 201: // Calculation completed
        resultObject = response;
        headers = response.getHeaders();
        break;
      case 202:
        String[] locationList = headers.get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 2];
        do {
          response = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
          headers = response.getHeaders();
          Assert.assertTrue("Get status response status code should be 201 or 202",
              response.getStatusCode() == 201 || response.getStatusCode() == 202);
          List<String> cacheControl = headers.get("Cache-Control");
          if (cacheControl != null) {
            int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
            System.out.println("Sleeping for: " + maxAge + " seconds");
            Thread.sleep(maxAge * 1000L);
          } else {
            System.out.println("Sleeping for: 2 seconds");
            Thread.sleep(2 * 1000L);
          }
        } while(response.getStatusCode() == 202);
        break;
    }
    // Get Calculation Result
    String[] location = headers.get("Location").get(0).split("/");
    String id = location[location.length - 2];
    ApiResponse<ObjectRoot> resultResponse = apiInstance.getCalculationResultWithHttpInfo(id);
    headers = resultResponse.getHeaders();
    resultObject = resultResponse.getData().getData();
    CalculationsHelper.validateCalculationResponse(headers, resultObject);
  }

  /*@Test
  public void enginesApiDeleteCalculationSuccess() throws ApiException{
    ApiResponse<Object> response = null;    
    try {
      FICalculationParametersRoot fiCalcParam = new FICalculationParametersRoot();
      FICalculationParameters calculationUnit = createUnitCalculation();
      calculationUnit.getCalculations().add("Security Name");
      fiCalcParam.setData(calculationUnit);
      response = apiInstance.postAndCalculateWithHttpInfo(CommonParameters.ZERO_DEADLINE_HEADER_VALUE, null, fiCalcParam);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }
    //Assert.assertTrue("Create response status code should be 202 - Created.", response.getStatusCode() == 202);
    String[] locationList = response.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Void> deleteResponse = null;
    try {
      deleteResponse = apiInstance.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    } catch (Exception e) {
      System.out.println(e.getStackTrace());
    }

    //Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    //Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);	  
  }*/
}
