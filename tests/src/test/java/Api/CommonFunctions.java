package Api;

import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientProperties;

import factset.analyticsapi.engines.*;

public class CommonFunctions {

  public static ApiClient buildApiClient(Engine engine) throws ApiException {
    ApiClient apiClient = null;
    CommonParameters.fillCredentials();
    String userName = CommonParameters.Credentials.get(engine).getKey();
    String password = CommonParameters.Credentials.get(engine).getValue();
    
    if(userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
      apiClient = new ApiClient();
      apiClient.setConnectTimeout(30000);
      apiClient.setReadTimeout(30000);
      apiClient.setBasePath(CommonParameters.BASEPATH);
      apiClient.setUsername(userName);//(CommonParameters.USERNAME);
      apiClient.setPassword(password);//(CommonParameters.PASSWORD);

    } else {
      throw new ApiException(
          "Please set ANALYTICS_API_DEFAULT_USERNAME_SERIAL and ANALYTICS_API_DEFAULT_PASSWORD environment variables.");
    }

    return apiClient;
  }

  public static void handleException(String method, ApiException e) throws ApiException {
    System.err.println("Exception when calling " + method);
    if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-factset-api-request-key")) {
      System.out.println("Request Id: " + e.getResponseHeaders().get("x-factset-api-request-key").get(0));
    }
    System.out.println("Status code: " + e.getCode());
    System.out.println("Reason: " + e.getClientErrorResponse());
    e.printStackTrace();
    throw e;
  }
}

class FdsApiClient extends ApiClient
{
   @Override
   protected void customizeClientBuilder(ClientBuilder clientBuilder) {
    // uncomment following settings as needed
     clientConfig.property( ClientProperties.PROXY_URI, "http://127.0.0.1:8866" );
     clientConfig.property(ClientProperties.REQUEST_ENTITY_PROCESSING, "BUFFERED");
     clientConfig.connectorProvider( new ApacheConnectorProvider() );
  }
}