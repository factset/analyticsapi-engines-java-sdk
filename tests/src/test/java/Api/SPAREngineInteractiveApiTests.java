package Api;

import java.util.LinkedHashMap;
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

public class SPAREngineInteractiveApiTests {

  private static ApiClient apiClient;
  private SparCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    apiInstance = new SparCalculationsApi(apiClient);
  }

  private String getComponentId() throws ApiException {
    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> components = ((ComponentSummaryRoot)componentsApi
        .getSPARComponents(CommonParameters.SPAR_DEFAULT_DOCUMENT)).getData();
    String componentId = components.entrySet().stream().findFirst().get().getKey();
    return componentId;
  }

  private SPARCalculationParameters createCalculationUnit(String componentId) {
    SPARCalculationParameters sparItem = new SPARCalculationParameters();
    sparItem.setComponentid(componentId);
    SPARIdentifier accountIdentifier1 = new SPARIdentifier();
    accountIdentifier1.setId(CommonParameters.SPAR_BENCHMARK_R1000);
    accountIdentifier1.setPrefix(CommonParameters.SPAR_BENCHMARK_RUSSELL_PREFIX);
    accountIdentifier1.setReturntype(CommonParameters.SPAR_BENCHMARK_RUSSELL_RETURN);
    sparItem.addAccountsItem(accountIdentifier1);

    SPARIdentifier accountIdentifier2 = new SPARIdentifier();
    accountIdentifier2.setId(CommonParameters.SPAR_BENCHMARK_RUSSELL_P_R1000);
    accountIdentifier2.setPrefix(CommonParameters.SPAR_BENCHMARK_RUSSELL_PREFIX);
    accountIdentifier2.setReturntype(CommonParameters.SPAR_BENCHMARK_RUSSELL_RETURN);
    sparItem.addAccountsItem(accountIdentifier2);

    SPARIdentifier benchmarkIdentifier = new SPARIdentifier();
    benchmarkIdentifier.setId(CommonParameters.SPAR_BENCHMARK_R2000);
    benchmarkIdentifier.setPrefix(CommonParameters.SPAR_BENCHMARK_RUSSELL_PREFIX);
    benchmarkIdentifier.setReturntype(CommonParameters.SPAR_BENCHMARK_RUSSELL_RETURN);
    sparItem.setBenchmark(benchmarkIdentifier);
    return sparItem;
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException {
    ApiResponse<Object> response = null;
    CalculationStatusRoot resultStatus = null;
    Map<String, List<String>> headers = null;    
    try {
      String id = getComponentId();
      SPARCalculationParameters calculationUnit = createCalculationUnit(id);
      SPARCalculationParametersRoot sparCalcParamRoot = new SPARCalculationParametersRoot();
      sparCalcParamRoot.putDataItem("1", calculationUnit);
      response = apiInstance.postAndCalculateWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, sparCalcParamRoot);
      headers = response.getHeaders();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 201 or 202",
        response.getStatusCode() == 201 || response.getStatusCode() == 202);

    ApiResponse<ObjectRoot> resultResponse = null;
    Object resultObject = null;

    switch(response.getStatusCode()) {
      case 201:
        resultObject = ((ObjectRoot)response.getData()).getData();
        headers = response.getHeaders();
        CalculationsHelper.validateCalculationResponse(headers, resultObject);
        break;
      case 202:
        String[] locationList = headers.get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 2];
        // Get Calculation Request Status
        ApiResponse<CalculationStatusRoot> resultStatusResponse =null;
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
          resultObject = ((ObjectRoot)resultResponse.getData()).getData();
        }
        CalculationsHelper.validateCalculationResponse(headers, resultObject);
        break;
    }
  }

  private ApiResponse<ObjectRoot> GetCalculationResult(String[] location) throws ApiException {
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
