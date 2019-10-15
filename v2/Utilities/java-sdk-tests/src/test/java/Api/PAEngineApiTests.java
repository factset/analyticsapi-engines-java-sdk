package Api;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.v2.*;
import factset.analyticsapi.engines.v2.api.*;
import factset.analyticsapi.engines.v2.models.*;

import com.factset.protobuf.stach.PackageProto.Package.Builder;
import com.factset.protobuf.stach.PackageProto.Package;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;

public class PAEngineApiTests {

  public static ApiClient apiClient;
  public CalculationsApi apiInstance;
  public UtilityApi utilityApiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new CalculationsApi(apiClient);
    utilityApiInstance = new UtilityApi(apiClient);
  }

  public ApiResponse<Void> runCalculation() throws ApiException {
    PACalculationParameters paItem = new PACalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(apiClient);
    Map<String, ComponentSummary> components = componentsApi.getPAComponents(CommonParameters.PA_DEFAULT_DOCUMENT);
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

    Calculation parameters = new Calculation();
    parameters.putPaItem("1", paItem);

    return apiInstance.runCalculationWithHttpInfo(parameters);
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException {
    ApiResponse<Void> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 1];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<CalculationStatus> getStatus = null;

    while (getStatus == null || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
        || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING) {
      if (getStatus != null) {
        Assert.assertTrue("Response Data should not be null.", getStatus != null);
        Assert.assertTrue("Response Data should have calculation status as executing or queued.",
            getStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
                || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING);
        Assert.assertTrue("Response Data should have at least one calculation status as executing or queued.",
            getStatus.getData().getPa().values().stream().filter(f -> f.getStatus() == CalculationUnitStatus.StatusEnum.EXECUTING
                || f.getStatus() == CalculationUnitStatus.StatusEnum.QUEUED).count() > 0);

        Assert.assertTrue("Response Data should not have all calculation results.",
            getStatus.getData().getPa().values().stream().filter(f -> f.getResult() == null).count() > 0);

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
        getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(id);
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getCalculationStatusByIdWithHttpInfo", e);
      }
    }

    Assert.assertTrue("Response Data should have calculation status as completed.",
        getStatus.getData().getStatus() == CalculationStatus.StatusEnum.COMPLETED);
    Assert.assertTrue("Response Data should have all calculations status as succeeded.", getStatus.getData().getPa()
        .values().stream().filter(f -> f.getStatus() != CalculationUnitStatus.StatusEnum.SUCCESS).count() == 0);
    Assert.assertTrue("Response Data should have all calculation results.",
        getStatus.getData().getPa().values().stream().filter(f -> f.getResult() == null).count() == 0);

    ApiResponse<String> resultResponse = null;

    for (CalculationUnitStatus calculationParameters : getStatus.getData().getPa().values()) {
      try {
        resultResponse = utilityApiInstance.getByUrlWithHttpInfo(calculationParameters.getResult());
      } catch (ApiException e) {
        CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
      }

      Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
      Assert.assertTrue("Result response data should not be null.", resultResponse.getData() != null);

      Builder builder = Package.newBuilder();
      try {
        JsonFormat.parser().ignoringUnknownFields().merge(resultResponse.getData(), builder);
      } catch (InvalidProtocolBufferException e) {
        System.out.println("Error while deserializing the response");
        e.printStackTrace();
      }
      Package result = (Package) builder.build();
      Assert.assertTrue("Response should be of Package type.", result instanceof Package);
    }
  }

  @Test
  public void enginesApiDeleteCalculationSuccess() throws ApiException {
    ApiResponse<Void> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 202 - Created.", createResponse.getStatusCode() == 202);

    String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
    String id = locationList[locationList.length - 1];

    Assert.assertTrue("Create response calculation id should be present.", id != null && id.trim().length() > 0);

    ApiResponse<Void> deleteResponse = null;

    try {
      deleteResponse = apiInstance.cancelCalculationByIdWithHttpInfo(id);
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#cancelCalculationByIdWithHttpInfo", e);
    }

    Assert.assertTrue("Delete response status code should be 204 - No Content.", deleteResponse.getStatusCode() == 204);
    Assert.assertTrue("Response data should be null.", deleteResponse.getData() == null);
  }

  @Test
  public void getAllOutStandingRequestsSuccess() throws ApiException {
    ApiResponse<Void> createResponse = null;

    try {
      createResponse = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
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
  }
}
