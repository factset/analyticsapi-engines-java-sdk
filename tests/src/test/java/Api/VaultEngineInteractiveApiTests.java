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
import com.factset.protobuf.stach.v2.PackageProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto.RowOrganizedPackage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.factset.protobuf.stach.v2.PackageProto.Package;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;

public class VaultEngineInteractiveApiTests {

  public static ApiClient apiClient;
  public VaultCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(Engine.Vault);
  }

  @Before
  public void before() {
    apiInstance = new VaultCalculationsApi(apiClient);
  }

  public ApiResponse<Object> runCalculation() throws ApiException {
    VaultCalculationParameters parameters = new VaultCalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> components = ((ComponentSummaryRoot)componentsApi
        .getVaultComponents(CommonParameters.VAULT_DEFAULT_DOCUMENT)).getData();
    String componentId = components.entrySet().stream().findFirst().get().getKey();
    parameters.setComponentid(componentId);

    ConfigurationsApi configurationsApi = new ConfigurationsApi(apiClient);
    Map<String, VaultConfigurationSummary> configurationsMap = ((VaultConfigurationSummaryRoot)configurationsApi
        .getVaultConfigurations(CommonParameters.VAULT_DEFAULT_ACCOUNT)).getData();
    String configurationId = configurationsMap.entrySet().stream().findFirst().get().getKey();
    parameters.setConfigid(configurationId);

    VaultIdentifier account = new VaultIdentifier();
    account.setId(CommonParameters.VAULT_DEFAULT_ACCOUNT);
    parameters.setAccount(account);

    VaultDateParameters dateParameters = new VaultDateParameters();
    dateParameters.setStartdate(CommonParameters.VAULT_START_DATE_FIRST);
    dateParameters.setEnddate(CommonParameters.VAULT_END_DATE_FIRST);
    dateParameters.setFrequency(CommonParameters.VAULT_FREQUENCY_DATE_MONTHLY);
    parameters.setDates(dateParameters);

    VaultCalculationParametersRoot vaultCalcParam = new VaultCalculationParametersRoot();
    vaultCalcParam.putDataItem("1", parameters);
    return apiInstance.postAndCalculateWithHttpInfo(null, null, vaultCalcParam);
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException, InterruptedException, JsonProcessingException {
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
      headers = response.getHeaders();
      resultObject = response.getData();
    }
    else if(response.getStatusCode() == 201) {
      String[] location = headers.get("Location").get(0).split("/");
      resultResponse = GetCalculationResult(location);
      resultObject = ((StringRoot)resultResponse.getData()).getData();
    }
    else if (response.getStatusCode() == 202) {
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
