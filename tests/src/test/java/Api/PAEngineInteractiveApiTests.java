package Api;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

import com.fasterxml.jackson.core.JsonProcessingException;

public class PAEngineInteractiveApiTests {

  private static ApiClient apiClient;
  private PaCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    apiInstance = new PaCalculationsApi(apiClient);
  }

  public String getComponentId() throws ApiException {
    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> components = componentsApi.getPAComponents(CommonParameters.PA_DEFAULT_DOCUMENT).getData();	
    String componentId = components.entrySet().stream().findFirst().get().getKey();
    return componentId;
  }

  public PACalculationParameters createCalculationUnit(String componentId) {
    PACalculationParameters paItem = new PACalculationParameters();
    paItem.setComponentid(componentId);
    PAIdentifier accountPaIdentifier1 = new PAIdentifier();
    accountPaIdentifier1.setId(CommonParameters.PA_BENCHMARK_SP500);
    paItem.addAccountsItem(accountPaIdentifier1);

    PAIdentifier accountPaIdentifier2 = new PAIdentifier();
    accountPaIdentifier2.setId(CommonParameters.PA_BENCHMARK_R1000);
    paItem.addAccountsItem(accountPaIdentifier2);

    PAIdentifier benchmarkPaIdentifier = new PAIdentifier();
    benchmarkPaIdentifier.setId(CommonParameters.PA_BENCHMARK_R1000);
    paItem.addBenchmarksItem(benchmarkPaIdentifier);
    return paItem;
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException {
    ApiResponse<Object> response = null;
    CalculationStatusRoot resultStatus = null;
    Map<String, List<String>> headers = null;    

    try {
      String id = getComponentId();
      PACalculationParameters calculationUnit = createCalculationUnit(id);
      PACalculationParametersRoot paCalcParamRoot = new PACalculationParametersRoot();
      paCalcParamRoot.putDataItem("1", calculationUnit);
      response = apiInstance.postAndCalculateWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, paCalcParamRoot);
      headers = response.getHeaders();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 201 or 202",
        response.getStatusCode() == 201 || response.getStatusCode() == 202);

    ApiResponse<ObjectRoot> resultResponse = null;
    Object result = null;

    switch(response.getStatusCode()) {
      case 201:// Calculation completed
        result = ((ObjectRoot)response.getData()).getData();
        headers = response.getHeaders();
        CalculationsHelper.validateCalculationResponse(headers, result);
        break;
      case 202:
        String[] locationList = headers.get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 2];
        // Get Calculation Request Status
        ApiResponse<CalculationStatusRoot> resultStatusResponse = null;
        do {
          resultStatusResponse = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
          headers = resultStatusResponse.getHeaders();
          resultStatus = (CalculationStatusRoot)resultStatusResponse.getData();
          Assert.assertTrue("Get status response status code should be 200 or 202",
              resultStatusResponse.getStatusCode() == 200 || resultStatusResponse.getStatusCode() == 202);
          List<String> cacheControl = headers.get("Cache-Control");
          if (cacheControl != null) {
            int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
            System.out.println("Sleeping for: " + maxAge + " seconds");
            Thread.sleep(maxAge * 1000L);
          } else {
            System.out.println("Sleeping for: 2 seconds");
            Thread.sleep(2 * 1000L);
          }
        } while(resultStatusResponse.getStatusCode() == 202);

        for(CalculationUnitStatus unitStatus : resultStatus.getData().getUnits().values()) {
          String[] location = unitStatus.getResult().split("/");
          resultResponse = GetCalculationResult(location);
          headers = resultResponse.getHeaders();
          result = ((ObjectRoot)resultResponse.getData()).getData();
        }
        CalculationsHelper.validateCalculationResponse(headers, result);
        break;
    }
  }

  public ApiResponse<ObjectRoot> GetCalculationResult(String[] location) throws ApiException {
    ApiResponse<ObjectRoot> resultResponse = null;
    try {	  
      String calcId = location[location.length-4];
      String unitId = location[location.length-2];        	  
      resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(calcId, unitId);      
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
    }
    return resultResponse;  
  }
}
