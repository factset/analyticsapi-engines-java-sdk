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
import factset.analyticsapi.engines.api.AxpOptimizerApi;
import factset.analyticsapi.engines.models.AxiomaEquityOptimizationParameters;
import factset.analyticsapi.engines.models.AxiomaEquityOptimizationParametersRoot;
import factset.analyticsapi.engines.models.AxiomaEquityOptimizerStrategy;
import factset.analyticsapi.engines.models.ObjectRoot;
import factset.analyticsapi.engines.models.Optimization;
import factset.analyticsapi.engines.models.OptimizerAccount;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerTradesList;

public class AxpInteractiveOptimizerEngineApiTests {
  private static ApiClient apiClient;
  private AxpOptimizerApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.OptimizerUsername, CommonParameters.OptimizerPassword);
  }

  @Before
  public void before() {
    apiInstance = new AxpOptimizerApi(apiClient);
  }

  public AxiomaEquityOptimizationParameters createUnitOptimization() throws ApiException {
    AxiomaEquityOptimizationParameters axpItem = new AxiomaEquityOptimizationParameters();
    OptimizerAccount accountId = new OptimizerAccount();
    accountId.setId(CommonParameters.AxiomaAccountId);

    Optimization optimization = new Optimization();
    optimization.setBacktestDate(CommonParameters.OptimizationDate);
    optimization.setRiskModelDate(CommonParameters.OptimizationDate);
    optimization.setCashflow(CommonParameters.OptimizationCashflow);

    AxiomaEquityOptimizerStrategy strategy = new AxiomaEquityOptimizerStrategy();
    strategy.setId(CommonParameters.SecondaryStrategyId);

    OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
    OptimizerTradesList tradesList = new OptimizerTradesList();
    tradesList.setIdentifierType(CommonParameters.TradesIdType);
    tradesList.setIncludeCash(CommonParameters.IncudeCash);
    optOutputTypes.setTrades(tradesList);

    axpItem.setAccount(accountId);
    axpItem.setOptimization(optimization);
    axpItem.setStrategy(strategy);
    axpItem.setOutputTypes(optOutputTypes);

    return axpItem;
  }

  @Test
	public void enginesApiGetOptimizationSuccess() throws ApiException, JsonProcessingException, InterruptedException{
	  ApiResponse<Object> response = null;
	  Map<String, List<String>> headers = null;	  
	  try {
		AxiomaEquityOptimizationParameters optimizationUnit = createUnitOptimization();
		AxiomaEquityOptimizationParametersRoot axpOptimizerParam = new AxiomaEquityOptimizationParametersRoot();
		axpOptimizerParam.setData(optimizationUnit);
		response = apiInstance.postAndOptimizeWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, axpOptimizerParam);
		headers = response.getHeaders();
	  } catch (ApiException e) {
	      CommonFunctions.handleException("EngineApi#runCalculation", e);
	  }

	  Assert.assertTrue("Create response status code should be 201 or 202",
	          response.getStatusCode() == 201 || response.getStatusCode() == 202);

	  Object resultObject = null;
	  // switch case on post response code
	  switch(response.getStatusCode()) {
	  case 201:
		resultObject = response;
		headers = response.getHeaders();
		break;
	  case 202:
		String[] locationList = headers.get("Location").get(0).split("/");
		String requestId = locationList[locationList.length - 2];
		do {
		  response = apiInstance.getOptimizationStatusByIdWithHttpInfo(requestId);
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
	  String[] location = headers.get("Location").get(0).split("/");
	  String id = location[location.length - 2];
	  ApiResponse<ObjectRoot> resultResponse = apiInstance.getOptimizationResultWithHttpInfo(id);
	  headers = resultResponse.getHeaders();
	  resultObject = resultResponse.getData();
	  Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
    Assert.assertTrue("Result response data should not be null.", resultObject != null);
	}

  @Test
  public void enginesApiDeleteOptimizationSuccess() throws ApiException{
    ApiResponse<Object> response = null;    
    try {
      AxiomaEquityOptimizationParameters optimizationUnit = createUnitOptimization();
      AxiomaEquityOptimizationParametersRoot axpOptimizerParam = new AxiomaEquityOptimizationParametersRoot();
      AxiomaEquityOptimizerStrategy strategy = new AxiomaEquityOptimizerStrategy();
      strategy.setId(CommonParameters.SecondaryStrategyId);
      optimizationUnit.setStrategy(strategy);
      axpOptimizerParam.setData(optimizationUnit);
      response = apiInstance.postAndOptimizeWithHttpInfo(CommonParameters.ZERO_DEADLINE_HEADER_VALUE, null, axpOptimizerParam);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runOptimization", e);
    }
    Assert.assertTrue("Create response status code should be 202 - Created.", response.getStatusCode() == 202);
    String[] locationList = response.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response optimization id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Void> deleteResponse = null;
    try {
      deleteResponse = apiInstance.cancelOptimizationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelOptimizationByIdWithHttpInfo", e);
    }
    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);	  
  }
}
