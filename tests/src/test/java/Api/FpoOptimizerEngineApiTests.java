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
import factset.analyticsapi.engines.api.FpoOptimizerApi;
import factset.analyticsapi.engines.models.AxiomaEquityOptimizationParameters;
import factset.analyticsapi.engines.models.AxiomaEquityOptimizationParametersRoot;
import factset.analyticsapi.engines.models.AxiomaEquityOptimizerStrategy;
import factset.analyticsapi.engines.models.CalculationInfoRoot;
import factset.analyticsapi.engines.models.FPOAccount;
import factset.analyticsapi.engines.models.FPOOptimizationParameters;
import factset.analyticsapi.engines.models.FPOOptimizationParametersRoot;
import factset.analyticsapi.engines.models.Optimization;
import factset.analyticsapi.engines.models.OptimizerAccount;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerStrategy;
import factset.analyticsapi.engines.models.OptimizerTradesList;
import factset.analyticsapi.engines.models.PaDoc;
import factset.analyticsapi.engines.models.StringRoot;

public class FpoOptimizerEngineApiTests {
  public static ApiClient apiClient;
  public FpoOptimizerApi apiInstance;
	
  @BeforeClass
  public static void beforeClass() throws ApiException {
	apiClient = CommonFunctions.buildApiClient(Engine.FPO);
  }
  
  @Before
  public void before() {
	apiInstance = new FpoOptimizerApi(apiClient);
  }

  public ApiResponse<Object> runOptimization() throws ApiException {//post body doesn't work, needs to be fixed/updated
	FPOOptimizationParameters fpoItem = new FPOOptimizationParameters();
	FPOAccount accountId = new FPOAccount();
	accountId.setId(CommonParameters.FpoAccountId);
	PaDoc padoc = new PaDoc();
	padoc.setId(CommonParameters.FpoPaDocName);
	accountId.setPaDocument(padoc);
	Optimization optimization = new Optimization();
	optimization.setBacktestDate(CommonParameters.OptimizationDate);
	optimization.setRiskModelDate(CommonParameters.OptimizationDate);
	optimization.setCashflow(CommonParameters.OptimizationCashflow);
	OptimizerStrategy strategy = new OptimizerStrategy();
	strategy.setId(CommonParameters.StrategyId);
	OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
	OptimizerTradesList tradesList = new OptimizerTradesList();
	tradesList.setIdentifierType(CommonParameters.TradesIdType);
	tradesList.setIncludeCash(CommonParameters.IncudeCash);
	optOutputTypes.setTrades(tradesList);
	  
	fpoItem.setAccount(accountId);
	fpoItem.setOptimization(optimization);
	fpoItem.setStrategy(strategy);
	fpoItem.setOutputTypes(optOutputTypes);
	  
	FPOOptimizationParametersRoot fpoOptimizerParam = new FPOOptimizationParametersRoot();
	fpoOptimizerParam.data(fpoItem);
	return apiInstance.postAndOptimizeWithHttpInfo(null, null, fpoOptimizerParam);
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
	  ApiResponse<StringRoot> resultResponse = apiInstance.getOptimizationResultWithHttpInfo(calcId, null);
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