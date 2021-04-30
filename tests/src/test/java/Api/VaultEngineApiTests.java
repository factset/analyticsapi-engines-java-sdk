package Api;

import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.factset.protobuf.stach.v2.PackageProto.Package;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;

public class VaultEngineApiTests {

  public static ApiClient apiClient;
  public VaultCalculationsApi vaultCalculations;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient(Engine.Vault);
  }

  @Before
  public void before() {
	  vaultCalculations = new VaultCalculationsApi(apiClient);
  }

  public ApiResponse<Object> runCalculation() throws ApiException {
    VaultCalculationParameters vaultItem = new VaultCalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> components = ((ComponentSummaryRoot)componentsApi
        .getVaultComponents(CommonParameters.VAULT_DEFAULT_DOCUMENT)).getData();
    String componentId = components.entrySet().stream().findFirst().get().getKey();
    vaultItem.setComponentid(componentId);

    ConfigurationsApi configurationsApi = new ConfigurationsApi(apiClient);
    Map<String, VaultConfigurationSummary> configurationsMap = ((VaultConfigurationSummaryRoot)configurationsApi
        .getVaultConfigurations(CommonParameters.VAULT_DEFAULT_ACCOUNT)).getData();
    String configurationId = configurationsMap.entrySet().stream().findFirst().get().getKey();
    vaultItem.setConfigid(configurationId);

    VaultIdentifier account = new VaultIdentifier();
    account.setId(CommonParameters.VAULT_DEFAULT_ACCOUNT);
    vaultItem.setAccount(account);

    VaultDateParameters dateParameters = new VaultDateParameters();
    dateParameters.setStartdate(CommonParameters.VAULT_START_DATE_FIRST);
    dateParameters.setEnddate(CommonParameters.VAULT_END_DATE_FIRST);
    dateParameters.setFrequency(CommonParameters.VAULT_FREQUENCY_DATE_MONTHLY);
    vaultItem.setDates(dateParameters);

    VaultCalculationParametersRoot parameters = new VaultCalculationParametersRoot();
    parameters.putDataItem("1", vaultItem);
    parameters.putDataItem("2", vaultItem);

    return vaultCalculations.postAndCalculateWithHttpInfo(null, null, parameters);
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException {
    ApiResponse<Object> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#createWithHttpInfo", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<CalculationStatusRoot> getStatus = null;
    CalculationStatusRoot status = null;

    while (getStatus == null || getStatus.getStatusCode() == 202) { //|| getStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
        //|| getStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING) {
      if (getStatus != null) {
        Assert.assertTrue("Response Data should not be null.", getStatus != null);
        Assert.assertTrue("Response Data should have calculation status as executing or queued.",
        		status.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
                || status.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING);
        Assert.assertTrue("Response Data should have at least one calculation status as executing or queued.",
        		status.getData().getUnits().values().stream()
                .filter(f -> f.getStatus() == CalculationUnitStatus.StatusEnum.EXECUTING
                    || f.getStatus() == CalculationUnitStatus.StatusEnum.QUEUED)
                .count() > 0);

        Assert.assertTrue("Response Data should not have all calculation results.",
        		status.getData().getUnits().values().stream().filter(f -> f.getResult() == null).count() > 0);

        if (getStatus.getHeaders().containsKey("cache-control")) {
          int maxAge = Integer.parseInt(getStatus.getHeaders().get("cache-control").get(0).split("=")[1]);
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
      try {
        getStatus = vaultCalculations.getCalculationStatusByIdWithHttpInfo(id);
        status = (CalculationStatusRoot)getStatus.getData();
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getCalculationStatusByIdWithHttpInfo", e);
      }
    }

    Assert.assertTrue("Response Data should have calculation status as completed.",
    		status.getData().getStatus() == CalculationStatus.StatusEnum.COMPLETED);
    Assert.assertTrue("Response Data should have all calculations status as succeeded.", status.getData().getUnits()
        .values().stream().filter(f -> f.getStatus() != CalculationUnitStatus.StatusEnum.SUCCESS).count() == 0);
    Assert.assertTrue("Response Data should have all calculation results.",
    		status.getData().getUnits().values().stream().filter(f -> f.getResult() == null).count() == 0);

    ApiResponse<StringRoot> resultResponse = null;
    Object result = null;

    for (CalculationUnitStatus calculationParameters : status.getData().getUnits().values()) {
      try {
    	  String[] location = calculationParameters.getResult().split("/");
    	  String calcId = location[location.length-4];
    	  String unitId = location[location.length-2];
    	  
    	  resultResponse = vaultCalculations.getCalculationUnitResultByIdWithHttpInfo(calcId, unitId);
    	  result = ((StringRoot)resultResponse.getData()).getData();
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
      }

      Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
      Assert.assertTrue("Result response data should not be null.", resultResponse.getData() != null);

      try {
        ObjectMapper mapper = new ObjectMapper();   	
      	String jsonString = mapper.writeValueAsString(result);

      	if(resultResponse.getHeaders().get("content-type").get(0).toLowerCase().contains("row")) {
          RowOrganizedProto.RowOrganizedPackage.Builder builder = RowOrganizedProto.RowOrganizedPackage.newBuilder();
          JsonFormat.parser().ignoringUnknownFields().merge(jsonString, builder);
          RowOrganizedPackage resultBuilder = builder.build();
          Assert.assertTrue("Response should be of RowOrganizedPackage type.", resultBuilder instanceof RowOrganizedPackage);
        }
      	else {
      	  PackageProto.Package.Builder builder = PackageProto.Package.newBuilder();
       	  JsonFormat.parser().ignoringUnknownFields().merge(jsonString, builder);
       	  PackageProto.Package resultBuilder = (builder).build();
          Assert.assertTrue("Response should be of ColumnDataPackage type.", resultBuilder instanceof PackageProto.Package);
        }        
        } catch (InvalidProtocolBufferException e) {
          System.out.println("Error while deserializing the response");
          e.printStackTrace();
        } catch(Exception e) {
      	  System.out.println(e.getMessage());
      	  e.printStackTrace();
        }
    }
  }

  @Test
  public void enginesApiDeleteCalculationSuccess() throws ApiException {
    ApiResponse<Object> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#createWithHttpInfo", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 2];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = vaultCalculations.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
  }

  /*@Test
  public void getAllOutStandingRequestsSuccess() throws ApiException {
    ApiResponse<Void> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#createWithHttpInfo", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 1];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Map<String, CalculationStatusSummary>> getAllOutstandingRequestsResponse = null;

    try {
      getAllOutstandingRequestsResponse = apiInstance.getCalculationStatusSummariesWithHttpInfo();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#getCalculationStatusSummariesWithHttpInfo", e);
    }

    Assert.assertTrue("Response should be 200 - Success.", getAllOutstandingRequestsResponse.getStatusCode() == 200);
    Assert.assertTrue("Respose data should not be null.", getAllOutstandingRequestsResponse.getData() != null);
    Assert.assertTrue("Response data does not include the created calculation.",
        getAllOutstandingRequestsResponse.getData().containsKey(id));

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = apiInstance.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
  }*/
}
