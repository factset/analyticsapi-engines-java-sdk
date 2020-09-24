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

import com.factset.protobuf.stach.PackageProto.Package.Builder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.factset.protobuf.stach.PackageProto.Package;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;

public class PAEngineInteractiveApiTests {

  public static ApiClient apiClient;
  public PaCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new PaCalculationsApi(apiClient);
  }

  public ApiResponse<Object> runCalculation() throws ApiException {
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

    return apiInstance.runPACalculationWithHttpInfo(paItem);
  }

  @Test
  public void enginesApiGetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException {
    ApiResponse<Object> response = null;

    try {
      response = runCalculation();
    } catch (ApiException e) {
      CommonFunctions.handleException("EngineApi#runCalculation", e);
    }

    Assert.assertTrue("Create response status code should be 201 or 202",
        response.getStatusCode() == 201 || response.getStatusCode() == 202);

    if (response.getStatusCode() == 202) {
      String[] locationList = response.getHeaders().get("Location").get(0).split("/");
      String requestId = locationList[locationList.length - 1];

      // Get Calculation Request Status

      while (response == null || response.getStatusCode() == 202) {
        if (response != null) {
          List<String> cacheControl = response.getHeaders().get("Cache-Control");
          if (cacheControl != null) {
            int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
            System.out.println("Sleeping for: " + maxAge + " seconds");
            Thread.sleep(maxAge * 1000L);
          } else {
            System.out.println("Sleeping for: 2 seconds");
            Thread.sleep(2 * 1000L);
          }
        }
        response = apiInstance.getPACalculationByIdWithHttpInfo(requestId);
        Assert.assertTrue("Get status response status code should be 200 or 202",
            response.getStatusCode() == 200 || response.getStatusCode() == 202);
      }
    }

    Builder builder = Package.newBuilder();
    try {
      ObjectMapper objMapper = new ObjectMapper();
      String jsonStr = objMapper.writeValueAsString(response.getData());
      JsonFormat.parser().ignoringUnknownFields().merge(jsonStr, builder);
    } catch (InvalidProtocolBufferException e) {
      System.out.println("Error while deserializing the response");
      e.printStackTrace();
    }
    Package result = (Package) builder.build();
    Assert.assertTrue("Response should be of Package type.", result instanceof Package);
  }
}
