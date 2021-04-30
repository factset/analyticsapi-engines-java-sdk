package Api;

import org.junit.Assert;
import org.junit.Test;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.StrategyDocumentsApi;
import factset.analyticsapi.engines.models.DocumentDirectoriesRoot;

public class StrategyDocumentsApiTests {
  public static ApiClient apiClient;
  public static StrategyDocumentsApi apiInstance;
  
  @Test
  public void getAxiomaEquityStrategyDocumentsListSuccess() throws ApiException{
	ApiResponse<DocumentDirectoriesRoot> getAxiomaEquityStrategyDocumentListResponse = null;
	apiClient = CommonFunctions.buildApiClient(Engine.AXP);
	apiInstance = new StrategyDocumentsApi(apiClient);
	  
	try {
	  getAxiomaEquityStrategyDocumentListResponse = apiInstance.getAxiomaEquityStrategyDocumentsWithHttpInfo(CommonParameters.DEFAULT_LOOKUP_DIRECTORY);
	  Assert.assertTrue("Response should be 200 - Success", getAxiomaEquityStrategyDocumentListResponse.getStatusCode() == 200);
	  Assert.assertTrue("Response data should not be null.", getAxiomaEquityStrategyDocumentListResponse.getData() != null);  
	} catch (ApiException e) {
	    CommonFunctions.handleException("StrategyDocumentsApi#getAxiomaEquityStrategyDocumentsWithHttpInfo", e);
	}
  }
  
  @Test
  public void getAxiomaFIStrategyDocumentsListSuccess() throws ApiException{
	ApiResponse<DocumentDirectoriesRoot> getAxiomaFIStrategyDocumentsListResponse = null;
	apiClient = CommonFunctions.buildApiClient(Engine.AFI);
	apiInstance = new StrategyDocumentsApi(apiClient);
	  
	try {
		getAxiomaFIStrategyDocumentsListResponse = apiInstance.getAxiomaFIStrategyDocumentsWithHttpInfo(CommonParameters.DEFAULT_LOOKUP_DIRECTORY);
	  Assert.assertTrue("Response should be 200 - Success", getAxiomaFIStrategyDocumentsListResponse.getStatusCode() == 200);
	  Assert.assertTrue("Response data should not be null.", getAxiomaFIStrategyDocumentsListResponse.getData() != null);  
	} catch (ApiException e) {
	    CommonFunctions.handleException("StrategyDocumentsApi#getAxiomaFIStrategyDocumentsWithHttpInfo", e);
	}
  }
  
  @Test
  public void getBarraStrategyDocumentsListSuccess() throws ApiException{
	ApiResponse<DocumentDirectoriesRoot> getBarraStrategyDocumentsListResponse = null;
	apiClient = CommonFunctions.buildApiClient(Engine.BPM);
	apiInstance = new StrategyDocumentsApi(apiClient);
	  
	try {
		getBarraStrategyDocumentsListResponse = apiInstance.getBarraStrategyDocumentsWithHttpInfo(CommonParameters.DEFAULT_LOOKUP_DIRECTORY);
	  Assert.assertTrue("Response should be 200 - Success", getBarraStrategyDocumentsListResponse.getStatusCode() == 200);
	  Assert.assertTrue("Response data should not be null.", getBarraStrategyDocumentsListResponse.getData() != null);  
	} catch (ApiException e) {
	    CommonFunctions.handleException("StrategyDocumentsApi#getBarraStrategyDocumentsWithHttpInfo", e);
	}
  }
  
  @Test
  public void getFPOStrategyDocumentsListSuccess() throws ApiException{
	ApiResponse<DocumentDirectoriesRoot> getFPOStrategyDocumentsListResponse = null;
	apiClient = CommonFunctions.buildApiClient(Engine.FPO);
	apiInstance = new StrategyDocumentsApi(apiClient);
	  
	try {
		getFPOStrategyDocumentsListResponse = apiInstance.getFPOStrategyDocumentsWithHttpInfo(CommonParameters.DEFAULT_LOOKUP_DIRECTORY);
	  Assert.assertTrue("Response should be 200 - Success", getFPOStrategyDocumentsListResponse.getStatusCode() == 200);
	  Assert.assertTrue("Response data should not be null.", getFPOStrategyDocumentsListResponse.getData() != null);  
    } catch (ApiException e) {
	    CommonFunctions.handleException("StrategyDocumentsApi#getFPOStrategyDocumentsWithHttpInfo", e);
    }
  }
  
  @Test
  public void getNorthfieldStrategyDocumentsListSuccess() throws ApiException{
	ApiResponse<DocumentDirectoriesRoot> getNorthfieldStrategyDocumentsListResponse = null;
	apiClient = CommonFunctions.buildApiClient(Engine.NPO);
	apiInstance = new StrategyDocumentsApi(apiClient);
	  
	try {
		getNorthfieldStrategyDocumentsListResponse = apiInstance.getNorthfieldStrategyDocumentsWithHttpInfo(CommonParameters.DEFAULT_LOOKUP_DIRECTORY);
	  Assert.assertTrue("Response should be 200 - Success", getNorthfieldStrategyDocumentsListResponse.getStatusCode() == 200);
	  Assert.assertTrue("Response data should not be null.", getNorthfieldStrategyDocumentsListResponse.getData() != null);  
	} catch (ApiException e) {
	    CommonFunctions.handleException("StrategyDocumentsApi#getNorthfieldStrategyDocumentsWithHttpInfo", e);
	}
 }
}
