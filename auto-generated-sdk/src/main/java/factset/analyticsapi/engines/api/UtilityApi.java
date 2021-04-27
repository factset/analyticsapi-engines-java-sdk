package factset.analyticsapi.engines.api;

import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.Configuration;
import factset.analyticsapi.engines.Pair;

import javax.ws.rs.core.GenericType;


import java.net.URI;
import java.net.URISyntaxException;

@javax.annotation.Generated(value = "CustomJavaClientCodegen")
public class UtilityApi {
  private ApiClient apiClient;

  public UtilityApi() {
    this(Configuration.getDefaultApiClient());
  }

  public UtilityApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
  * Get by Url
  * This function can be used to fetch data from any Get endpoint.
  * @param url Url of the Get endpoint (required)
  * @return String
  * @throws ApiException if fails to make API call
  * @http.response.details
    <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Expected response once the request is successful. Response body will contain the data. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * Age - Standard HTTP header. Header will specify the age of columns list cached response. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 400 </td><td> Invalid identifier provided. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 401 </td><td> Missing or invalid authentication. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 403 </td><td> User is forbidden with current credentials </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 406 </td><td> Unsupported Accept header. Header needs to be set to application/json. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 500 </td><td> Server error. Log the X-DataDirect-Request-Key header to assist in troubleshooting. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 503 </td><td> Request timed out. Retry the request in sometime. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
    </table>
  */
  public String getByUrl(String url) throws ApiException {
    return getByUrlWithHttpInfo(url).getData();
  }
  
  /**
  * Get by Url
  * This function can be used to fetch data from any Get endpoint.
  * @param url Url of the Get endpoint (required)
  * @return ApiResponse&lt;String&gt;
  * @throws ApiException if fails to make API call
  * @http.response.details
    <table summary="Response Details" border="1">
       <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
       <tr><td> 200 </td><td> Expected response once the request is successful. Response body will contain the data. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * Age - Standard HTTP header. Header will specify the age of columns list cached response. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 400 </td><td> Invalid identifier provided. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 401 </td><td> Missing or invalid authentication. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 403 </td><td> User is forbidden with current credentials </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 406 </td><td> Unsupported Accept header. Header needs to be set to application/json. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 500 </td><td> Server error. Log the X-DataDirect-Request-Key header to assist in troubleshooting. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
       <tr><td> 503 </td><td> Request timed out. Retry the request in sometime. </td><td>  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  </td></tr>
    </table>
  */
  public ApiResponse<String> getByUrlWithHttpInfo(String url) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'url' is set
    if (url == null) {
      throw new ApiException(400, "Missing the required parameter 'url' when calling getByUrl");
    }
    
    // create path and map variables
    String localVarPath;
    try {
      localVarPath = new URI(url).getPath();
    } catch (URISyntaxException e) {
      throw new ApiException("Invalid 'url' provided when calling getByUrl");
    }

    // query params
    java.util.List<Pair> localVarQueryParams = new java.util.ArrayList<Pair>();
    java.util.Map<String, String> localVarHeaderParams = new java.util.HashMap<String, String>();
    java.util.Map<String, String> localVarCookieParams = new java.util.HashMap<String, String>();
    java.util.Map<String, Object> localVarFormParams = new java.util.HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "text/plain", "application/json", "text/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "Basic" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}