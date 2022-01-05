package Api;

import java.util.List;
import java.util.Map;

import org.junit.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.FpoOptimizerApi;
import factset.analyticsapi.engines.models.FPOAccount;
import factset.analyticsapi.engines.models.FPOOptimizationParameters;
import factset.analyticsapi.engines.models.FPOOptimizationParametersRoot;
import factset.analyticsapi.engines.models.ObjectRoot;
import factset.analyticsapi.engines.models.Optimization;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerStrategy;
import factset.analyticsapi.engines.models.OptimizerTradesList;
import factset.analyticsapi.engines.models.PaDoc;

public class FpoInteractiveOptimizerEngineApiTests {
  private static ApiClient apiClient;
  private FpoOptimizerApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    apiInstance = new FpoOptimizerApi(apiClient);
  }

  private FPOOptimizationParameters createUnitOptimization() throws ApiException {
    FPOOptimizationParameters fpoItem = new FPOOptimizationParameters();
    FPOAccount accountId = new FPOAccount();
    accountId.setId(CommonParameters.FpoAccountId);
    PaDoc padoc = new PaDoc();
    padoc.setId(CommonParameters.FpoPaDocName);
    accountId.setPaDocument(padoc);
    Optimization optimization = new Optimization();
    optimization.setBacktestDate(CommonParameters.FpoOptimizationDate);
    optimization.setRiskModelDate(CommonParameters.FpoOptimizationDate);
    optimization.setCashflow(CommonParameters.OptimizationCashflow);
    OptimizerStrategy strategy = new OptimizerStrategy();
    strategy.setId(CommonParameters.FpoOptimizationStrategyId);
    OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
    OptimizerTradesList tradesList = new OptimizerTradesList();
    tradesList.setIdentifierType(CommonParameters.TradesIdType);
    tradesList.setIncludeCash(CommonParameters.IncludeCash);
    optOutputTypes.setTrades(tradesList);

    fpoItem.setAccount(accountId);
    fpoItem.setOptimization(optimization);
    fpoItem.setStrategy(strategy);
    fpoItem.setOutputTypes(optOutputTypes);

    return fpoItem;
  }

  @Test
  public void enginesApiGetOptimizationSuccess() throws ApiException, JsonProcessingException, InterruptedException{
    ApiResponse<Object> response = null;
    Map<String, List<String>> headers = null;      
    try {
      FPOOptimizationParameters optimizationUnit = createUnitOptimization();
      FPOOptimizationParametersRoot fpoOptimizerParam = new FPOOptimizationParametersRoot();
      fpoOptimizerParam.setData(optimizationUnit);
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
          response = apiInstance.getOptimizationStatusByIdWithHttpInfo(requestId);
        } while(response.getStatusCode() == 202);
        break;
    }
    headers = response.getHeaders();
    String[] location = headers.get("Location").get(0).split("/");
    String id = location[location.length - 2];
    ApiResponse<ObjectRoot> resultResponse = apiInstance.getOptimizationResultWithHttpInfo(id, CommonParameters.ACCEPT_HEADER_VALUE);
    headers = resultResponse.getHeaders();
    resultObject = resultResponse.getData();
    Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
    Assert.assertTrue("Result response data should not be null.", resultObject != null);
  }

  /*@Test
  public void enginesApiDeleteOptimizationSuccess() throws ApiException{
    ApiResponse<Object> response = null;    
    try {
      FPOOptimizationParameters optimizationUnit = createUnitOptimization();
      FPOOptimizationParametersRoot fpoOptimizerParam = new FPOOptimizationParametersRoot();
      Optimization optimization = new Optimization();
      optimization.setBacktestDate(CommonParameters.FpoSecondaryOptimizationDate);
      optimization.setRiskModelDate(CommonParameters.FpoSecondaryOptimizationDate);
      optimization.setCashflow(CommonParameters.OptimizationCashflow);
      optimizationUnit.setOptimization(optimization);
      fpoOptimizerParam.setData(optimizationUnit);
      response = apiInstance.postAndOptimizeWithHttpInfo(CommonParameters.ZERO_DEADLINE_HEADER_VALUE, null, fpoOptimizerParam);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runOptimization", e);
    }
    //Assert.assertTrue("Create response status code should be 202 - Created.", response.getStatusCode() == 202);
    String[] locationList = response.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response optimization id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Void> deleteResponse = null;
    try {
      deleteResponse = apiInstance.cancelOptimizationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelOptimizationByIdWithHttpInfo", e);
    }

    //Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    //Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);	  
  }*/
}