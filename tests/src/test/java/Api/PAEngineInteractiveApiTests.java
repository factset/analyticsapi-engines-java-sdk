package Api;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

import com.factset.protobuf.stach.v2.RowOrganizedProto.RowOrganizedPackage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.factset.protobuf.stach.v2.PackageProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;

public class PAEngineInteractiveApiTests {

  public static ApiClient apiClient;
  public PaCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(Engine.PA);
  }

  @Before
  public void before() {
    apiInstance = new PaCalculationsApi(apiClient);
  }

  public ApiResponse<Object> runCalculation() throws ApiException {
    PACalculationParameters paItem = new PACalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> components = ((ComponentSummaryRoot)componentsApi.getPAComponents(CommonParameters.PA_DEFAULT_DOCUMENT)).getData();
    String componentId = components.entrySet().stream().findFirst().get().getKey();
    paItem.setComponentid(componentId);

    PAIdentifier accountPaIdentifier1 = new PAIdentifier();
    accountPaIdentifier1.setId(CommonParameters.PA_BENCHMARK_SP500);
    paItem.addAccountsItem(accountPaIdentifier1);

    PAIdentifier accountPaIdentifier2 = new PAIdentifier();
    accountPaIdentifier2.setId(CommonParameters.PA_BENCHMARK_R1000);
    paItem.addAccountsItem(accountPaIdentifier2);

    PAIdentifier benchmarkPaIdentifier = new PAIdentifier();
    benchmarkPaIdentifier.setId(CommonParameters.PA_BENCHMARK_R1000);
    paItem.addBenchmarksItem(benchmarkPaIdentifier);

    PACalculationParametersRoot paCalcParamRoot = new PACalculationParametersRoot();
    paCalcParamRoot.putDataItem("1", paItem);
    return apiInstance.postAndCalculateWithHttpInfo(CommonParameters.DEADLINE_HEADER_VALUE, null, paCalcParamRoot);
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException {
    //ApiResponse<CalculationStatusRoot> response = null;
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

    ApiResponse<StringRoot> resultResponse = null;
    Object result = null;

    if(response.getStatusCode() == 200) {
      result = response.getData();
      headers = response.getHeaders();
    }
    else if(response.getStatusCode() == 201) {
      String[] location = headers.get("Location").get(0).split("/");
      resultResponse = GetCalculationResult(location);
      headers = resultResponse.getHeaders();
      result = ((StringRoot)resultResponse.getData()).getData();
    }
    else if (response.getStatusCode() == 202){ 
       
        String[] locationList = headers.get("Location").get(0).split("/");
        String requestId = locationList[locationList.length - 2];

        // Get Calculation Request Status
        ApiResponse<CalculationStatusRoot> resultStatusResponse =null;
        while (resultStatusResponse == null ||  resultStatusResponse.getStatusCode() == 202) {
          if (resultStatusResponse != null) {
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
         
         resultStatusResponse = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
         headers = resultStatusResponse.getHeaders();
         resultStatus = (CalculationStatusRoot)resultStatusResponse.getData();
         
         Assert.assertTrue("Get status response status code should be 200 or 202",
        		 resultStatusResponse.getStatusCode() == 200 || resultStatusResponse.getStatusCode() == 202);
        }
      
      for(CalculationUnitStatus unitStatus : resultStatus.getData().getUnits().values()) {
        String[] location = unitStatus.getResult().split("/");
        resultResponse = GetCalculationResult(location);
        headers = resultResponse.getHeaders();
        result = ((StringRoot)resultResponse.getData()).getData();
      }          
    }
    try {
        //ObjectMapper objMapper = new ObjectMapper();
    	//String jsonStr = objMapper.writeValueAsString(result);
    	if(headers.get("content-type").get(0).toLowerCase().contains("row")) {
    	  RowOrganizedProto.RowOrganizedPackage.Builder builder = RowOrganizedProto.RowOrganizedPackage.newBuilder();
          JsonFormat.parser().ignoringUnknownFields().merge(result.toString(), builder);
          RowOrganizedPackage resultPackage = builder.build();
          Assert.assertTrue("Response should be of RowOrganizedPackage type.", resultPackage instanceof RowOrganizedPackage);
    	}
    	else {
    	  PackageProto.Package.Builder builder = PackageProto.Package.newBuilder();
       	  JsonFormat.parser().ignoringUnknownFields().merge(result.toString(), builder);
       	  PackageProto.Package resultPackage = (builder).build();
          Assert.assertTrue("Response should be of ColumnDataPackage type.", resultPackage instanceof PackageProto.Package);
    	}
      } catch (InvalidProtocolBufferException e) {
          System.out.println("Error while deserializing the response");
          e.printStackTrace();
        }
  }
  
  public ApiResponse<StringRoot> GetCalculationResult(String[] location) throws ApiException {
	ApiResponse<StringRoot> resultResponse = null;
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
