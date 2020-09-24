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

public class SPAREngineInteractiveApiTests {

  public static ApiClient apiClient;
  public SparCalculationsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new SparCalculationsApi(apiClient);
  }

  public ApiResponse<Object> runCalculation() throws ApiException {
    final SPARCalculationParameters parameters = new SPARCalculationParameters();

    final ComponentsApi componentsApi = new ComponentsApi(apiClient);
    final Map<String, ComponentSummary> components = componentsApi
        .getSPARComponents(CommonParameters.SPAR_DEFAULT_DOCUMENT);
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

    return apiInstance.runSPARCalculationWithHttpInfo(parameters);
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
        response = apiInstance.getSPARCalculationByIdWithHttpInfo(requestId);
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
