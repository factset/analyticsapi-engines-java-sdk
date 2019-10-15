package Api;

import factset.analyticsapi.engines.v2.*;

public class CommonFunctions {

  public static ApiClient buildApiClient() throws ApiException {
    ApiClient apiClient = null;

    if (CommonParameters.USERNAME != null && !CommonParameters.USERNAME.isEmpty() && CommonParameters.PASSWORD != null
        && !CommonParameters.PASSWORD.isEmpty()) {

      apiClient = new ApiClient();
      apiClient.setConnectTimeout(30000);
      apiClient.setReadTimeout(30000);
      apiClient.setBasePath(CommonParameters.BASEPATH);
      apiClient.setUsername(CommonParameters.USERNAME);
      apiClient.setPassword(CommonParameters.PASSWORD);

    } else {
      throw new ApiException(
          "Please set ANALYTICS_API_USERNAME_SERIAL and ANALYTICS_API_PASSWORD environment variables.");
    }

    return apiClient;
  }

  public static void handleException(String method, ApiException e) throws ApiException {
    System.err.println("Exception when calling " + method);
    if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-factset-api-request-key")) {
      System.out.println("Request Id: " + e.getResponseHeaders().get("x-factset-api-request-key").get(0));
    }
    System.out.println("Status code: " + e.getCode());
    System.out.println("Reason: " + e.getResponseBody());
    e.printStackTrace();
    throw e;
  }
}