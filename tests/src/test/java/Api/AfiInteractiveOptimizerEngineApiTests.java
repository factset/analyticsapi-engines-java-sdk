package Api;

import java.util.List;
import java.util.Map;
import factset.analyticsapi.engines.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.AfiOptimizerApi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static Api.CommonParameters.DEADLINE_HEADER_VALUE;

public class AfiInteractiveOptimizerEngineApiTests {
    private static ApiClient apiClient;
    private AfiOptimizerApi apiInstance;

    @BeforeClass
    public static void beforeClass() throws ApiException {
        apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
    }

    @Before
    public void before() {
        apiInstance = new AfiOptimizerApi(apiClient);
    }

    private AFIOptimizationParameters createUnitOptimization() throws ApiException {
        AFIOptimizationParameters afiItem = new AFIOptimizationParameters();

        AFIOptimizerStrategy strategy = new AFIOptimizerStrategy();
        strategy.setId(CommonParameters.AfiStrategyId);

        OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
        OptimizerTradesList tradesList = new OptimizerTradesList();
        tradesList.setIdentifierType(CommonParameters.AfiTradesIdType);
        tradesList.setIncludeCash(CommonParameters.AfiIncludeCash);
        optOutputTypes.setTrades(tradesList);

        afiItem.setStrategy(strategy);
        afiItem.setOutputTypes(optOutputTypes);

        return afiItem;
    }

    @Test
    public void enginesApiGetOptimizationSuccess() throws ApiException, JsonProcessingException, InterruptedException{
        ApiResponse<Object> response = null;
        Map<String, List<String>> headers = null;
        try {
            AFIOptimizationParameters optimizationUnit = createUnitOptimization();
            AFIOptimizationParametersRoot afiOptimizerParam = new AFIOptimizationParametersRoot();
            afiOptimizerParam.setData(optimizationUnit);
            response = apiInstance.postAndOptimizeWithHttpInfo(DEADLINE_HEADER_VALUE, "max-stale=0", afiOptimizerParam);
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
        ApiResponse<ObjectRoot> resultResponse = apiInstance.getOptimizationResultWithHttpInfo(id);
        headers = resultResponse.getHeaders();
        resultObject = resultResponse.getData();
        Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
        Assert.assertTrue("Result response data should not be null.", resultObject != null);
    }

}
