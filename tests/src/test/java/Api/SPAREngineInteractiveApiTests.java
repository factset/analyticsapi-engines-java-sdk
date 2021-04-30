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

import com.factset.protobuf.stach.v2.PackageProto.Package.Builder;
import com.factset.protobuf.stach.v2.RowOrganizedProto.RowOrganizedPackage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.factset.protobuf.stach.v2.PackageProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto;
import com.factset.protobuf.stach.v2.PackageProto.Package;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;

public class SPAREngineInteractiveApiTests {

  public static ApiClient apiClient;
  public SparCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(Engine.SPAR);
  }

  @Before
  public void before() {
    apiInstance = new SparCalculationsApi(apiClient);
  }

  public ApiResponse<Object> runCalculation() throws ApiException {
    final SPARCalculationParameters parameters = new SPARCalculationParameters();

    final ComponentsApi componentsApi = new ComponentsApi(apiClient);
    final Map<String, ComponentSummary> components = ((ComponentSummaryRoot)componentsApi
        .getSPARComponents(CommonParameters.SPAR_DEFAULT_DOCUMENT)).getData();
    final String componentId = components.entrySet().stream().findFirst().get().getKey();
    parameters.setComponentid(componentId);

    final SPARIdentifier accountIdentifier1 = new SPARIdentifier();
    accountIdentifier1.setId(CommonParameters.SPAR_BENCHMARK_R1000);
    accountIdentifier1.setPrefix(CommonParameters.SPAR_BENCHMARK_RUSSELL_PREFIX);
    accountIdentifier1.setReturntype(CommonParameters.SPAR_BENCHMARK_RUSSELL_RETURN);
    parameters.addAccountsItem(accountIdentifier1);

    final SPARIdentifier accountIdentifier2 = new SPARIdentifier();
    accountIdentifier2.setId(CommonParameters.SPAR_BENCHMARK_RUSSELL_P_R1000);
    accountIdentifier2.setPrefix(CommonParameters.SPAR_BENCHMARK_RUSSELL_PREFIX);
    accountIdentifier2.setReturntype(CommonParameters.SPAR_BENCHMARK_RUSSELL_RETURN);
    parameters.addAccountsItem(accountIdentifier2);

    final SPARIdentifier benchmarkIdentifier = new SPARIdentifier();
    benchmarkIdentifier.setId(CommonParameters.SPAR_BENCHMARK_R2000);
    benchmarkIdentifier.setPrefix(CommonParameters.SPAR_BENCHMARK_RUSSELL_PREFIX);
    benchmarkIdentifier.setReturntype(CommonParameters.SPAR_BENCHMARK_RUSSELL_RETURN);
    parameters.setBenchmark(benchmarkIdentifier);
    SPARCalculationParametersRoot sparCalcParam = new SPARCalculationParametersRoot();
    sparCalcParam.putDataItem("1", parameters);
    return apiInstance.postAndCalculateWithHttpInfo(null, null, sparCalcParam);
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

    ApiResponse<StringRoot> resultResponse = null;
    Object resultObject = null;

    if(response.getStatusCode() == 200) {
      resultObject = response.getData();
      headers = response.getHeaders();
    }
    else if(response.getStatusCode() == 201) {
      String[] location = headers.get("Location").get(0).split("/");
      resultResponse = GetCalculationResult(location);
      resultObject = ((StringRoot)resultResponse.getData()).getData();
    }
    else if((response.getStatusCode() == 202)) { 
    	//while (response.getStatusCode() == 202) {
          String[] locationList = headers.get("Location").get(0).split("/");
          String requestId = locationList[locationList.length - 2];

          // Get Calculation Request Status
          ApiResponse<CalculationStatusRoot> resultStatusResponse =null;
          while (resultStatusResponse == null || resultStatusResponse.getStatusCode() == 202) {
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
        //}
        for(CalculationUnitStatus unitStatus : resultStatus.getData().getUnits().values()) {
          try {
      	    String[] location = unitStatus.getResult().split("/");
      	    resultResponse = GetCalculationResult(location);
      	    headers = resultResponse.getHeaders();
            resultObject = ((StringRoot)resultResponse.getData()).getData();
          } catch (ApiException e) {
      	      CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
      	  }      
       }
    }
    try {
      //ObjectMapper objMapper = new ObjectMapper();
      //String jsonStr = objMapper.writeValueAsString(resultObject);
      if(headers.get("content-type").get(0).toLowerCase().contains("row")) {
        RowOrganizedProto.RowOrganizedPackage.Builder builder = RowOrganizedProto.RowOrganizedPackage.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(resultObject.toString(), builder);
        RowOrganizedPackage result = builder.build();
        Assert.assertTrue("Response should be of RowOrganizedPackage type.", result instanceof RowOrganizedPackage);
      }
     else {
       PackageProto.Package.Builder builder = PackageProto.Package.newBuilder();
       JsonFormat.parser().ignoringUnknownFields().merge(resultObject.toString(), builder);
       PackageProto.Package result = (builder).build();
       Assert.assertTrue("Response should be of ColumnDataPackage type.", result instanceof PackageProto.Package);
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
