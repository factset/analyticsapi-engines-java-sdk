package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.BenchmarksApi;
import factset.analyticsapi.engines.models.SPARBenchmarkRoot;

public class BenchmarksApiTests {
	public static ApiClient apiClient;
	public BenchmarksApi apiInstance;
	
	@BeforeClass
	public static void beforeClass() throws ApiException {
	  apiClient = CommonFunctions.buildApiClient(Engine.SPAR);
	}

	@Before
	public void before() {
	  apiInstance = new BenchmarksApi(apiClient);
	}
	
	@Test
	public void getSPARBenchmarksByIdSuccess() throws ApiException{
	  ApiResponse<SPARBenchmarkRoot> getSparBenchmarkByIdInfo = null;
	  try {
		getSparBenchmarkByIdInfo = apiInstance.getSPARBenchmarkByIdWithHttpInfo(CommonParameters.SPAR_BENCHMARK_R1000);
		  
		Assert.assertTrue("Response should be 200 - Success", getSparBenchmarkByIdInfo.getStatusCode() == 200);
	    Assert.assertTrue("Response data should not be null.", getSparBenchmarkByIdInfo.getData() != null);
	  }
	  catch(ApiException e) {
		  CommonFunctions.handleException("BenchmarksApi#getSPARBenchmarkByIdWithHttpInfo", e);
	  }
	}
}
