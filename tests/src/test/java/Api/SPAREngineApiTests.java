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

public class SPAREngineApiTests {

  private static ApiClient apiClient;
  private SparCalculationsApi sparCalculations;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
  }

  @Before
  public void before() {
    sparCalculations = new SparCalculationsApi(apiClient);
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
  public void enginesApiGetCalculationSuccess() throws ApiException {
    ApiResponse<Object> createResponse = null;
    
    try {
      String id = getComponentId();
      SPARCalculationParameters unit1 = createCalculationUnit(id);
      SPARCalculationParameters unit2 = createCalculationUnit(id);
      SPARCalculationParametersRoot sparCalcParamRoot = new SPARCalculationParametersRoot();
      sparCalcParamRoot.putDataItem("1", unit1);
      sparCalcParamRoot.putDataItem("2", unit2);
      createResponse = sparCalculations.postAndCalculateWithHttpInfo(null, null, sparCalcParamRoot);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#createWithHttpInfo", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<CalculationStatusRoot> getStatus = null;
    CalculationStatusRoot resultStatus = null;

    try {
      do {
        getStatus = sparCalculations.getCalculationStatusByIdWithHttpInfo(id);
        resultStatus = (CalculationStatusRoot)getStatus.getData();
        if(getStatus.getStatusCode() == 200)
          break;
        Assert.assertTrue("Response Data should not be null.", getStatus != null);
        Assert.assertTrue("Response Data should have calculation status as executing or queued.",
            resultStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
            || resultStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING);
        Assert.assertTrue("Response Data should have at least one calculation status as executing or queued or success.",
            resultStatus.getData().getUnits().values().stream()
            .filter(f -> f.getStatus() == CalculationUnitStatus.StatusEnum.EXECUTING
            || f.getStatus() == CalculationUnitStatus.StatusEnum.QUEUED
            || f.getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS)
            .count() > 0);

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
        resultStatus.getData().getStatus() == CalculationStatus.StatusEnum.COMPLETED);
    Assert.assertTrue("Response Data should have all calculations status as succeeded.", resultStatus.getData().getUnits()
        .values().stream().filter(f -> f.getStatus() != CalculationUnitStatus.StatusEnum.SUCCESS).count() == 0);
    Assert.assertTrue("Response Data should have all calculation results.",
        resultStatus.getData().getUnits().values().stream().filter(f -> f.getResult() == null).count() == 0);

    ApiResponse<ObjectRoot> resultResponse = null;
    Object result = null;

    for (CalculationUnitStatus calculationParameters : resultStatus.getData().getUnits().values()) {
      try {
        String[] location = calculationParameters.getResult().split("/");
        String calcId = location[location.length-4];
        String unitId = location[location.length-2];
        resultResponse = sparCalculations.getCalculationUnitResultByIdWithHttpInfo(calcId, unitId);
        result = ((ObjectRoot)resultResponse.getData()).getData();
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
      SPARCalculationParameters unit1 = createCalculationUnit(id);
      SPARCalculationParameters unit2 = createCalculationUnit(id);
      SPARCalculationParametersRoot sparCalcParamRoot = new SPARCalculationParametersRoot();
      SPARDateParameters dates = new SPARDateParameters();
      dates.setStartdate(CommonParameters.PA_START_DATE);
      dates.setEnddate(CommonParameters.PA_END_DATE);
      dates.setFrequency(CommonParameters.PA_FREQUENCY);
      unit2.setDates(dates);
      sparCalcParamRoot.putDataItem("1", unit1);
      sparCalcParamRoot.putDataItem("2", unit2);
      createResponse = sparCalculations.postAndCalculateWithHttpInfo(null, null, sparCalcParamRoot);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#createWithHttpInfo", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = sparCalculations.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
  }
}
