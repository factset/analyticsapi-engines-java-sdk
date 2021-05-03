package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.FpoOptimizerApi;
import factset.analyticsapi.engines.models.FPOAccount;
import factset.analyticsapi.engines.models.FPOOptimizationParameters;
import factset.analyticsapi.engines.models.FPOOptimizationParametersRoot;
import factset.analyticsapi.engines.models.Optimization;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerStrategy;
import factset.analyticsapi.engines.models.OptimizerTradesList;
import factset.analyticsapi.engines.models.PaDoc;

public class FpoInteractiveOptimizerEngineApiTests {
  public static ApiClient apiClient;
  public FpoOptimizerApi apiInstance;
	
  @BeforeClass
  public static void beforeClass() throws ApiException {
	apiClient = CommonFunctions.buildApiClient(CommonParameters.OptimizerUsername, CommonParameters.OptimizerPassword);
  }
  
  @Before
  public void before() {
	apiInstance = new FpoOptimizerApi(apiClient);
  }

  public FPOOptimizationParameters createUnitOptimization() throws ApiException {//post body doesn't work, needs to be fixed/updated
	FPOOptimizationParameters fpoItem = new FPOOptimizationParameters();
	FPOAccount accountId = new FPOAccount();
	accountId.setId(CommonParameters.FpoAccountId);
	PaDoc padoc = new PaDoc();
	padoc.setId(CommonParameters.FpoPaDocName);
	accountId.setPaDocument(padoc);
	Optimization optimization = new Optimization();
	optimization.setBacktestDate(CommonParameters.OptimizationDate);
	optimization.setRiskModelDate(CommonParameters.OptimizationDate);
	optimization.setCashflow(CommonParameters.OptimizationCashflow);
	OptimizerStrategy strategy = new OptimizerStrategy();
	strategy.setId(CommonParameters.StrategyId);
	OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
	OptimizerTradesList tradesList = new OptimizerTradesList();
	tradesList.setIdentifierType(CommonParameters.TradesIdType);
	tradesList.setIncludeCash(CommonParameters.IncudeCash);
	optOutputTypes.setTrades(tradesList);
	  
	fpoItem.setAccount(accountId);
	fpoItem.setOptimization(optimization);
	fpoItem.setStrategy(strategy);
	fpoItem.setOutputTypes(optOutputTypes);
	  
	return fpoItem;
  }
	
  /*@Test
  public void enginesApiGetOptimizationSuccess() throws ApiException, JsonProcessingException, InterruptedException{
	ApiResponse<Object> response = null;
	//CalculationInfoRoot resultStatus = null;
	Map<String, List<String>> headers = null;
	FPOOptimizationParameters unit1 = null;
	FPOOptimizationParametersRoot fpoOptimizerParam = new FPOOptimizationParametersRoot();  
	try {
	  unit1 = createUnitOptimization();
	  fpoOptimizerParam.setData(unit1);
	  response = apiInstance.postAndOptimizeWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, fpoOptimizerParam);
	  headers = response.getHeaders();
	} catch (ApiException e) {
	  CommonFunctions.handleException("EngineApi#runCalculation", e);
	}
	  
	Assert.assertTrue("Create response status code should be 201 or 202",
	        response.getStatusCode() == 201 || response.getStatusCode() == 202);
	  
	Object resultObject = null;
	
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
	ApiResponse<ObjectRoot> resultResponse = apiInstance.getOptimizationResultWithHttpInfo(id, CommonParameters.ACCEPT_HEADER_VALUE);
	headers = resultResponse.getHeaders();
	resultObject = resultResponse.getData();
	CommonFunctions.checkResult(headers, resultObject);
  }
  */
  @Test
	public void enginesApiDeleteOptimizationSuccess() throws ApiException{
	  ApiResponse<Object> response = null;
	  FPOOptimizationParameters unit1 = null;
	  FPOOptimizationParametersRoot fpoOptimizerParam = new FPOOptimizationParametersRoot();
	  try {
		unit1 = createUnitOptimization();
		//must update to a different post body
		fpoOptimizerParam.setData(unit1);
		response = apiInstance.postAndOptimizeWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, fpoOptimizerParam);
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