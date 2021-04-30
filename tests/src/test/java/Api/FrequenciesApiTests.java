package Api;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class FrequenciesApiTests {
    
    public static ApiClient apiClient;
    public FrequenciesApi apiInstance;
    
    /*@BeforeClass
    public static void beforeClass() throws ApiException {
      apiClient = CommonFunctions.buildApiClient();
    }

    @Before
    public void before() {
      apiInstance = new FrequenciesApi(apiClient);
    }    */
    
    @Test
    public void getPAFrequencies() throws ApiException {
        ApiResponse<FrequencyRoot> getPAFrequenciesResponse = null;
        apiClient = CommonFunctions.buildApiClient(Engine.PA);
        apiInstance = new FrequenciesApi(apiClient);
        try {
          getPAFrequenciesResponse = apiInstance.getPAFrequenciesWithHttpInfo();
          
          Assert.assertTrue("Response should be 200 - Success", getPAFrequenciesResponse.getStatusCode() == 200);
          Assert.assertTrue("Response data should not be null.", getPAFrequenciesResponse.getData() != null);
        } catch(ApiException e) {
          CommonFunctions.handleException("FrequenciesApi#getPAFrequenciesWithHttpInfo", e);
        }
    }
    
    @Test
    public void getVaultFrequencies() throws ApiException {
        ApiResponse<FrequencyRoot> getVaultFrequenciesResponse = null;
        apiClient = CommonFunctions.buildApiClient(Engine.Vault);
        apiInstance = new FrequenciesApi(apiClient);
        try {
          getVaultFrequenciesResponse = apiInstance.getVaultFrequenciesWithHttpInfo();
          
          Assert.assertTrue("Response should be 200 - Success", getVaultFrequenciesResponse.getStatusCode() == 200);
          Assert.assertTrue("Response data should not be null.", getVaultFrequenciesResponse.getData() != null);
        } catch(ApiException e) {
          CommonFunctions.handleException("FrequenciesApi#getVaultFrequenciesWithHttpInfo", e);
        }
    }
    
    @Test
    public void getSPARFrequencies() throws ApiException {
        ApiResponse<FrequencyRoot> getSPARFrequenciesResponse = null;
        apiClient = CommonFunctions.buildApiClient(Engine.SPAR);
        apiInstance = new FrequenciesApi(apiClient);
        try {
          getSPARFrequenciesResponse = apiInstance.getSPARFrequenciesWithHttpInfo();
          
          Assert.assertTrue("Response should be 200 - Success", getSPARFrequenciesResponse.getStatusCode() == 200);
          Assert.assertTrue("Response data should not be null.", getSPARFrequenciesResponse.getData() != null);
        } catch(ApiException e) {
          CommonFunctions.handleException("FrequenciesApi#getSPARFrequenciesWithHttpInfo", e);
        }
    }
}
