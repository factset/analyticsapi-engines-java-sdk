package Api;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class PAEngineApiTests {

  private static ApiClient apiClient;
  private PaCalculationsApi paCalculations;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    paCalculations = new PaCalculationsApi(apiClient);
  }

  private String getComponentId() throws ApiException {
    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> components = componentsApi.getPAComponents(CommonParameters.PA_DEFAULT_DOCUMENT).getData();	
    String componentId = components.entrySet().stream().findFirst().get().getKey();
    return componentId;
  }

  private PACalculationParameters createCalculationUnit(String componentId) {
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
  public void enginesApiGetCalculationSuccess() throws ApiException {
    ApiResponse<Object> createResponse = null;    
    try {
      String id = getComponentId();
      PACalculationParameters unit1 = createCalculationUnit(id);
      PACalculationParameters unit2 = createCalculationUnit(id);
      PACalculationParametersRoot paCalcParamRoot = new PACalculationParametersRoot();
      paCalcParamRoot.putDataItem("1", unit1);
      paCalcParamRoot.putDataItem("2", unit2);
      createResponse = paCalculations.postAndCalculateWithHttpInfo(null, null, paCalcParamRoot);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 200 or 202",
            createResponse.getStatusCode() == 200 || createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<CalculationStatusRoot> getStatus = null;
    CalculationStatusRoot status = null;

    try {        
      do {
        getStatus = paCalculations.getCalculationStatusByIdWithHttpInfo(id);
        status = getStatus.getData();
        if(getStatus.getStatusCode() == 200)// indicates calculation completion
          break;
        Assert.assertTrue("Response Data should not be null.", getStatus != null);
        Assert.assertTrue("Response Data should have calculation status as executing or queued.",
            status.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
            || status.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING);
        Assert.assertTrue("Response Data should have at least one calculation status as executing or queued or success.",
            status.getData().getUnits().values().stream().filter(f -> f.getStatus() == CalculationUnitStatus.StatusEnum.EXECUTING
            || f.getStatus() == CalculationUnitStatus.StatusEnum.QUEUED
            || f.getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS).count() > 0);

        if (getStatus.getHeaders().containsKey("cache-control")) {
          int maxAge = Integer.parseInt(getStatus.getHeaders().get("cache-control").get(0).split("=")[1]);
          try {
            System.out.println("\n **** Waiting for " + maxAge + " seconds **** \n");
            TimeUnit.SECONDS.sleep(maxAge);
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
        } else {
          int waitTimeInSeconds = 5;
          try {
            System.out.println("\n **** Waiting for " + waitTimeInSeconds + " seconds **** \n");
            TimeUnit.SECONDS.sleep(waitTimeInSeconds);
          } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
        }
      } while(getStatus.getStatusCode() == 202);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#getCalculationStatusByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Response Data should have calculation status as completed.",
        status.getData().getStatus() == CalculationStatus.StatusEnum.COMPLETED);
    Assert.assertTrue("Response Data should have all calculations status as succeeded.", status.getData().getUnits()
        .values().stream().filter(f -> f.getStatus() != CalculationUnitStatus.StatusEnum.SUCCESS).count() == 0);
    Assert.assertTrue("Response Data should have all calculation results.",
        status.getData().getUnits().values().stream().filter(f -> f.getResult() == null).count() == 0);

    ApiResponse<ObjectRoot> resultResponse = null;
    Object result = null;
    //Get Calculation Unit Result
    for (CalculationUnitStatus calculationParameters : status.getData().getUnits().values()) {
      try {
        String[] location = calculationParameters.getResult().split("/");
        String calcId = location[location.length-4];
        String unitId = location[location.length-2];

        resultResponse = paCalculations.getCalculationUnitResultByIdWithHttpInfo(calcId, unitId);
        result = resultResponse.getData().getData();
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
      }
      Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
      Assert.assertTrue("Result response data should not be null.", resultResponse.getData() != null);

      CalculationsHelper.validateCalculationResponse(resultResponse.getHeaders(), result);
    }
  }

  @Test
  public void enginesApiDeleteCalculationSuccess() throws ApiException {
    ApiResponse<Object> createResponse = null;    
    try {
      String id = getComponentId();
      PACalculationParameters unit1 = createCalculationUnit(id);
      PACalculationParameters unit2 = createCalculationUnit(id);
      PACalculationParametersRoot paCalcParamRoot = new PACalculationParametersRoot();
      PADateParameters dates = new PADateParameters();
      dates.setStartdate(CommonParameters.PA_START_DATE);
      dates.setEnddate(CommonParameters.PA_END_DATE);
      dates.setFrequency(CommonParameters.PA_FREQUENCY);
      unit2.setDates(dates);
      paCalcParamRoot.putDataItem("1", unit1);
      paCalcParamRoot.putDataItem("2", unit2);
      createResponse = paCalculations.postAndCalculateWithHttpInfo(null, null, paCalcParamRoot);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = paCalculations.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
  }
}
