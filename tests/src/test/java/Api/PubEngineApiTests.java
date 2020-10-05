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

public class PubEngineApiTests {

  public static ApiClient apiClient;
  public CalculationsApi apiInstance;
  public UtilityApi utilityApiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new CalculationsApi(apiClient);
    utilityApiInstance = new UtilityApi(apiClient);
  }

  public ApiResponse<Void> runCalculation() throws ApiException {
    PubCalculationParameters pubItem = new PubCalculationParameters();
    
    pubItem.setDocument(CommonParameters.PUB_DEFAULT_DOCUMENT);
    
    PubIdentifier accountIdentifier = new PubIdentifier();
    accountIdentifier.setId(CommonParameters.PUB_DEFAULT_ACCOUNT);
    pubItem.setAccount(accountIdentifier);

    PubDateParameters dateParameters = new PubDateParameters();
    dateParameters.setStartdate("-1M");
    dateParameters.setEnddate("0M");
    pubItem.setDates(dateParameters);

    Calculation parameters = new Calculation();
    parameters.putPubItem("1", pubItem);

    return apiInstance.runCalculationWithHttpInfo(parameters);
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException {
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

    ApiResponse<CalculationStatus> getStatus = null;

    while (getStatus == null || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
        || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING) {
      if (getStatus != null) {
        Assert.assertTrue("Response Data should not be null.", getStatus != null);
        Assert.assertTrue("Response Data should have calculation status as executing or queued.",
            getStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
                || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING);
        Assert.assertTrue("Response Data should have at least one calculation status as executing or queued.",
            getStatus.getData().getPub().values().stream().filter(f -> f.getStatus() == CalculationUnitStatus.StatusEnum.EXECUTING
                || f.getStatus() == CalculationUnitStatus.StatusEnum.QUEUED).count() > 0);

        Assert.assertTrue("Response Data should not have all calculation results.",
            getStatus.getData().getPub().values().stream().filter(f -> f.getResult() == null).count() > 0);

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
      }
      try {
        getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(id);
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getCalculationStatusByIdWithHttpInfo", e);
      }
    }

    Assert.assertTrue("Response Data should have calculation status as completed.",
        getStatus.getData().getStatus() == CalculationStatus.StatusEnum.COMPLETED);
    Assert.assertTrue("Response Data should have all calculations status as succeeded.", getStatus.getData().getPub()
        .values().stream().filter(f -> f.getStatus() != CalculationUnitStatus.StatusEnum.SUCCESS).count() == 0);
    Assert.assertTrue("Response Data should have all calculation results.",
        getStatus.getData().getPub().values().stream().filter(f -> f.getResult() == null).count() == 0);

    ApiResponse<String> resultResponse = null;

    for (CalculationUnitStatus calculationParameters : getStatus.getData().getPub().values()) {
      try {
        resultResponse = utilityApiInstance.getByUrlWithHttpInfo(calculationParameters.getResult());
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
      }

      Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
      Assert.assertTrue("Result response data should not be null.", resultResponse.getData() != null);
    }
  }

  @Test
  public void enginesApiDeleteCalculationSuccess() throws ApiException {
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

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = apiInstance.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
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

    ApiResponse<Map<String, CalculationStatusSummary>> getAllOutstandingRequestsResponse = null;

    try {
      getAllOutstandingRequestsResponse = apiInstance.getCalculationStatusSummariesWithHttpInfo();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#getCalculationStatusSummariesWithHttpInfo", e);
    }

    Assert.assertTrue("Response should be 200 - Success.", getAllOutstandingRequestsResponse.getStatusCode() == 200);
    Assert.assertTrue("Respose data should not be null.", getAllOutstandingRequestsResponse.getData() != null);
    Assert.assertTrue("Response data does not include the created calculation.",
        getAllOutstandingRequestsResponse.getData().containsKey(id));

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = apiInstance.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
  }
}
