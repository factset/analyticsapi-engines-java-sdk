package Api;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.factset.protobuf.stach.v2.PackageProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto.RowOrganizedPackage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.AxpOptimizerApi;
import factset.analyticsapi.engines.models.AxiomaEquityOptimizationParameters;
import factset.analyticsapi.engines.models.AxiomaEquityOptimizationParametersRoot;
import factset.analyticsapi.engines.models.AxiomaEquityOptimizerStrategy;
import factset.analyticsapi.engines.models.CalculationInfoRoot;
import factset.analyticsapi.engines.models.Optimization;
import factset.analyticsapi.engines.models.OptimizerAccount;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerTradesList;
import factset.analyticsapi.engines.models.StringRoot;

public class AxpOptimizerEngineApiTests {
	public static ApiClient apiClient;
	public AxpOptimizerApi apiInstance;
	
	@BeforeClass
	public static void beforeClass() throws ApiException {
	  apiClient = CommonFunctions.buildApiClient(Engine.AXP);
	}

	@Before
	public void before() {
		apiInstance = new AxpOptimizerApi(apiClient);
	}
	
	public ApiResponse<Object> runOptimization() throws ApiException {
	  AxiomaEquityOptimizationParameters axpItem = new AxiomaEquityOptimizationParameters();
	  OptimizerAccount accountId = new OptimizerAccount();
	  accountId.setId(CommonParameters.AxiomaAccountId);
	  Optimization optimization = new Optimization();
	  optimization.setBacktestDate(CommonParameters.OptimizationDate);
	  optimization.setRiskModelDate(CommonParameters.OptimizationDate);
	  optimization.setCashflow(CommonParameters.OptimizationCashflow);
	  AxiomaEquityOptimizerStrategy strategy = new AxiomaEquityOptimizerStrategy();
	  strategy.setId(CommonParameters.StrategyId);
	  OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
	  OptimizerTradesList tradesList = new OptimizerTradesList();
	  tradesList.setIdentifierType(CommonParameters.TradesIdType);
	  tradesList.setIncludeCash(CommonParameters.IncudeCash);
	  optOutputTypes.setTrades(tradesList);
	  
	  axpItem.setAccount(accountId);
	  axpItem.setOptimization(optimization);
	  axpItem.setStrategy(strategy);
	  axpItem.setOutputTypes(optOutputTypes);
	  
	  AxiomaEquityOptimizationParametersRoot axpOptimizerParam = new AxiomaEquityOptimizationParametersRoot();
	  axpOptimizerParam.data(axpItem);
	  return apiInstance.postAndOptimizeWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, axpOptimizerParam);
	}
	
	@Test
	public void enginesApiGetOptimizationSuccess() throws ApiException, JsonProcessingException, InterruptedException{
	  ApiResponse<Object> response = null;
	  //CalculationInfoRoot resultStatus = null;
	  Map<String, List<String>> headers = null;
	  
	  try {
		response = runOptimization();
		headers = response.getHeaders();
	  } catch (ApiException e) {
	      CommonFunctions.handleException("EngineApi#runCalculation", e);
	  }
	  
	  Assert.assertTrue("Create response status code should be 201 or 202",
	          response.getStatusCode() == 201 || response.getStatusCode() == 202);
	  
	  Object resultObject = null;
	  
	  if(response.getStatusCode() == 201) {
		resultObject = response;
		headers = response.getHeaders();
	  }
	  else if(response.getStatusCode() == 202) {
	  	String[] locationList = headers.get("Location").get(0).split("/");
	    String requestId = locationList[locationList.length - 2];
	      
	    while (response == null ||  response.getStatusCode() == 202) {
	      if (response != null) {
	        List<String> cacheControl = headers.get("Cache-Control");
	        if (cacheControl != null) {
	          int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
	          System.out.println("Sleeping for: " + maxAge + " seconds");
	          Thread.sleep(maxAge * 1000L);
	        } else {
	            System.out.println("Sleeping for: 2 seconds");
	            Thread.sleep(2 * 1000L);
	        }
	      }	         
	      response = apiInstance.getOptimizationStatusByIdWithHttpInfo(requestId);
	      headers = response.getHeaders();
	      Assert.assertTrue("Get status response status code should be 201 or 202",
	        	response.getStatusCode() == 201 || response.getStatusCode() == 202);
	    }
	    String[] location = headers.get("Location").get(0).split("/");
		String calcId = location[location.length-2];
		ApiResponse<StringRoot> resultResponse = apiInstance.getOptimizationResultWithHttpInfo(calcId);
		headers = resultResponse.getHeaders();
		resultObject = resultResponse.getData();
	  }
	  
	  try {
	    ObjectMapper objMapper = new ObjectMapper();
	    String jsonStr = objMapper.writeValueAsString(resultObject);
	    if(headers.get("content-type").get(0).toLowerCase().contains("row")) {
	      RowOrganizedProto.RowOrganizedPackage.Builder builder = RowOrganizedProto.RowOrganizedPackage.newBuilder();
	      JsonFormat.parser().ignoringUnknownFields().merge(jsonStr, builder);
	      RowOrganizedPackage result = builder.build();
	      Assert.assertTrue("Response should be of RowOrganizedPackage type.", result instanceof RowOrganizedPackage);
	    }
	    else {
	      PackageProto.Package.Builder builder = PackageProto.Package.newBuilder();
	      JsonFormat.parser().ignoringUnknownFields().merge(jsonStr, builder);
	      PackageProto.Package result = (builder).build();
	      Assert.assertTrue("Response should be of ColumnDataPackage type.", result instanceof PackageProto.Package);
	    }
	  } catch (InvalidProtocolBufferException e) {
	    System.out.println("Error while deserializing the response");
	    e.printStackTrace();
	  } 
	}
}
