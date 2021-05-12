package Api;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.PubCalculationsApi;
import factset.analyticsapi.engines.models.CalculationStatusRoot;
import factset.analyticsapi.engines.models.CalculationUnitStatus;
import factset.analyticsapi.engines.models.PubCalculationParameters;
import factset.analyticsapi.engines.models.PubCalculationParametersRoot;
import factset.analyticsapi.engines.models.PubDateParameters;
import factset.analyticsapi.engines.models.PubIdentifier;

public class PubEngineInteractiveApiTests {
  private static ApiClient apiClient;
  private PubCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(CommonParameters.VaultPubUsername, CommonParameters.VaultPubPassword);
  }

  @Before
  public void before() {
    apiInstance = new PubCalculationsApi(apiClient);
  }

  private PubCalculationParameters createUnitCalculation() throws ApiException {
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
  public void enginesApiGetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException {
    ApiResponse<Object> response = null;
    CalculationStatusRoot resultStatus = null;
    Map<String, List<String>> headers = null;    
    try {
      PubCalculationParameters calculationUnit = createUnitCalculation();
      PubCalculationParametersRoot parameters = new PubCalculationParametersRoot();
      parameters.putDataItem("1", calculationUnit);
      response = apiInstance.postAndCalculateWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, parameters);
      headers = response.getHeaders();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 201 or 202",
        response.getStatusCode() == 201 || response.getStatusCode() == 202);
    ApiResponse<File> resultResponse = null;
    File resultObject = null;

    switch(response.getStatusCode()) {
      case 201:
        resultObject = (File)response.getData();
        Assert.assertTrue("Result response data should not be null.", resultObject != null);
        break;
      case 202:
        String[] locationList = headers.get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 2];
        ApiResponse<CalculationStatusRoot> resultStatusResponse =null;
        do {
          resultStatusResponse = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
          headers = resultStatusResponse.getHeaders();
          resultStatus = (CalculationStatusRoot)resultStatusResponse.getData();
          Assert.assertTrue("Get status response status code should be 200 or 202",
              resultStatusResponse.getStatusCode() == 200 || resultStatusResponse.getStatusCode() == 202);
          if (headers.containsKey("cache-control")) {
            int maxAge = Integer.parseInt(headers.get("cache-control").get(0).split("=")[1]);
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
        } while(resultStatusResponse.getStatusCode() == 202);
        for(CalculationUnitStatus unitStatus : resultStatus.getData().getUnits().values()) {
          String[] location = unitStatus.getResult().split("/");
          resultResponse = GetCalculationResult(location);
          headers = resultResponse.getHeaders();
          resultObject = resultResponse.getData();         
        }
        Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
        Assert.assertTrue("Result response data should not be null.", resultObject != null);
        break;
    }
  }

  private ApiResponse<File> GetCalculationResult(String[] location) throws ApiException {
    ApiResponse<File> resultResponse = null;
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
