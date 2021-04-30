package Api;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import factset.analyticsapi.engines.api.PubCalculationsApi;
import factset.analyticsapi.engines.models.CalculationStatusRoot;
import factset.analyticsapi.engines.models.CalculationUnitStatus;
import factset.analyticsapi.engines.models.PubCalculationParameters;
import factset.analyticsapi.engines.models.PubCalculationParametersRoot;
import factset.analyticsapi.engines.models.PubDateParameters;
import factset.analyticsapi.engines.models.PubIdentifier;

public class PubEngineInteractiveApiTests {
	public static ApiClient apiClient;
	public PubCalculationsApi apiInstance;
	
	@BeforeClass
	public static void beforeClass() throws ApiException {
	  apiClient = CommonFunctions.buildApiClient(Engine.Pub);
	}
	
	@Before
	public void before() {
	  apiInstance = new PubCalculationsApi(apiClient);
	}
	
	public ApiResponse<Object> runCalculation() throws ApiException {
	  PubCalculationParameters pubItem = new PubCalculationParameters();
	    
	  pubItem.setDocument(CommonParameters.PUB_DEFAULT_DOCUMENT);
	    
	  PubIdentifier accountIdentifier = new PubIdentifier();
	  accountIdentifier.setId(CommonParameters.PUB_DEFAULT_ACCOUNT);
	  pubItem.setAccount(accountIdentifier);

	  PubDateParameters dateParameters = new PubDateParameters();
	  dateParameters.setStartdate("-1M");
	  dateParameters.setEnddate("0M");
	  pubItem.setDates(dateParameters);

	  PubCalculationParametersRoot parameters = new PubCalculationParametersRoot();
	  parameters.putDataItem("1", pubItem);
	  
	  return apiInstance.postAndCalculateWithHttpInfo(null, null, parameters);
	}
	
	@Test
	public void enginesApiGetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException {
	  ApiResponse<Object> response = null;
	  CalculationStatusRoot resultStatus = null;
	  Map<String, List<String>> headers = null;
	  try {
	    response = runCalculation();
	    headers = response.getHeaders();
	  } catch (ApiException e) {
	    CommonFunctions.handleException("EngineApi#runCalculation", e);
	  }

	  Assert.assertTrue("Create response status code should be 201 or 202 or 200",
	      response.getStatusCode() == 201 || response.getStatusCode() == 202 || response.getStatusCode() == 200);
      ApiResponse<String> resultResponse = null;
	  Object resultObject = null;

	  if(response.getStatusCode() == 200) {
	    resultObject = response.getData();
	    headers = response.getHeaders();
	  }
	  else if(response.getStatusCode() == 201) {
	    String[] location = headers.get("Location").get(0).split("/");
	    resultResponse = GetCalculationResult(location);
	    resultObject = ((String)resultResponse.getData());
	  }
	  else if(response.getStatusCode() == 202){ 
		String[] locationList = headers.get("Location").get(0).split("/");
	    String requestId = locationList[locationList.length - 2];
	    ApiResponse<CalculationStatusRoot> resultStatusResponse =null;
	  	while ( resultStatusResponse == null || resultStatusResponse.getStatusCode() == 202) {      
	      // Get Calculation Request Status
	  	  if(resultStatusResponse != null) {
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
	  	  }
	      resultStatusResponse = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
	      headers = resultStatusResponse.getHeaders();
	      resultStatus = (CalculationStatusRoot)resultStatusResponse.getData();
	      Assert.assertTrue("Get status response status code should be 200 or 202",
	    		  resultStatusResponse.getStatusCode() == 200 || resultStatusResponse.getStatusCode() == 202);	        
	    }
	  	for(CalculationUnitStatus unitStatus : resultStatus.getData().getUnits().values()) {
	   	     try {
	   	       String[] location = unitStatus.getResult().split("/");
	   	       resultResponse = GetCalculationResult(location);
	   	       headers = resultResponse.getHeaders();
	   	       resultObject = ((String)resultResponse.getData());
	   	     } catch (ApiException e) {
	   	       CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
	   	     }      
	   	}	   
	  }
	  Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
      Assert.assertTrue("Result response data should not be null.", resultObject != null);
	 /*try {
	   ObjectMapper objMapper = new ObjectMapper();
	   String jsonStr = objMapper.writeValueAsString(resultObject);
	   if(resultResponse.getHeaders().get("content-type").get(0).toLowerCase().contains("row")) {
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
	 }*/
    }
	  
	public ApiResponse<String> GetCalculationResult(String[] location) throws ApiException {
	  ApiResponse<String> resultResponse = null;
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
