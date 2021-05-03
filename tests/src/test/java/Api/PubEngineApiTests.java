package Api;

import java.io.File;
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
  public PubCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.VaultPubUsername, CommonParameters.VaultPubPassword);
  }

  @Before
  public void before() {
    apiInstance = new PubCalculationsApi(apiClient);
  }

  public PubCalculationParameters createUnitCalculation() throws ApiException {
    PubCalculationParameters pubItem = new PubCalculationParameters();
    
    pubItem.setDocument(CommonParameters.PUB_DEFAULT_DOCUMENT);
    
    PubIdentifier accountIdentifier = new PubIdentifier();
    accountIdentifier.setId(CommonParameters.PUB_DEFAULT_ACCOUNT);
    pubItem.setAccount(accountIdentifier);

    PubDateParameters dateParameters = new PubDateParameters();
    dateParameters.setStartdate("-1M");
    dateParameters.setEnddate("0M");
    pubItem.setDates(dateParameters);
   
    return pubItem;
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException {
    ApiResponse<Object> createResponse = null;
    PubCalculationParameters unit1 = null;
    PubCalculationParameters unit2 = null;
    PubCalculationParametersRoot parameters = new PubCalculationParametersRoot();
    try {
      unit1 = createUnitCalculation();
      unit2 = createUnitCalculation();
      parameters.putDataItem("1", unit1);
      parameters.putDataItem("2", unit2);
      createResponse = apiInstance.postAndCalculateWithHttpInfo(null, null, parameters);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<CalculationStatusRoot> getStatus = null;
    CalculationStatusRoot resultStatus = null;

    try {
      do {
    	getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(id);
        resultStatus = (CalculationStatusRoot) getStatus.getData();
        if(getStatus.getStatusCode() == 200)
        	break;
        Assert.assertTrue("Response Data should not be null.", getStatus != null);
        Assert.assertTrue("Response Data should have calculation status as executing or queued.",
        		resultStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
                || resultStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING);
        Assert.assertTrue("Response Data should have at least one calculation status as executing or queued.",
        		resultStatus.getData().getUnits().values().stream().filter(f -> f.getStatus() == CalculationUnitStatus.StatusEnum.EXECUTING
                || f.getStatus() == CalculationUnitStatus.StatusEnum.QUEUED).count() > 0);

        Assert.assertTrue("Response Data should not have all calculation results.",
        		resultStatus.getData().getUnits().values().stream().filter(f -> f.getResult() == null).count() > 0);

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

    ApiResponse<File> resultResponse = null;
    File result = null;

    for (CalculationUnitStatus calculationParameters : resultStatus.getData().getUnits().values()) {
      try {
    	String[] location = calculationParameters.getResult().split("/");
      	String calcId = location[location.length-4];
      	String unitId = location[location.length-2];

      	resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(calcId, unitId);
        result = (resultResponse.getData());
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
      }

      Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
      Assert.assertTrue("Result response data should not be null.", result != null);
    }
  }

  @Test
  public void enginesApiDeleteCalculationSuccess() throws ApiException {
    ApiResponse<Object> createResponse = null;
    PubCalculationParameters unit1 = null;
    PubCalculationParameters unit2 = null;
    PubCalculationParametersRoot parameters = new PubCalculationParametersRoot();
    try {
      unit1 = createUnitCalculation();
      unit2 = createUnitCalculation();
      PubDateParameters dateParameters = new PubDateParameters();
      dateParameters.setStartdate("-2M");
      dateParameters.setEnddate("0M");
      unit2.setDates(dateParameters);
      parameters.putDataItem("1", unit1);
      parameters.putDataItem("2", unit2);
      createResponse = apiInstance.postAndCalculateWithHttpInfo(null, null, parameters);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

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
}
