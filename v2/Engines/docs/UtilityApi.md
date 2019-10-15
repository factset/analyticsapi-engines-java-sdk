# UtilityApi

All URIs are relative to *https://api.factset.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getByUrl**](UtilityApi.md#getByUrl) | **GET** {url} | Get by url

## getByUrl

> String getByUrl(url)

Get by Url

This function can be used to fetch data from any Get endpoint.

### Example

```java
import factset.analyticsapi.engines.v2.ApiClient;
import factset.analyticsapi.engines.v2.ApiException;
import factset.analyticsapi.engines.v2.Configuration;
import factset.analyticsapi.engines.v2.auth.*;
import factset.analyticsapi.engines.v2.models.*;
import factset.analyticsapi.engines.v2.UtilityApi;

public class Example {

    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.factset.com");
        
        // Configure HTTP basic authorization: Basic
        HttpBasicAuth Basic = (HttpBasicAuth) defaultClient.getAuthentication("Basic");
        Basic.setUsername("YOUR USERNAME");
        Basic.setPassword("YOUR PASSWORD");

        UtilityApi apiInstance = new UtilityApi(defaultClient);
        String url = "url_example"; // String | url of the Get endpoint 
        
        String result = null;
        try {
            result = apiInstance.getByUrl(url);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling #getByUrl");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
**url** | String | url of the Get endpoint | |

### Return type

String

### Authorization

[Basic](../README.md#Basic)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Expected response once the request is successful. Response body will contain the data. |  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * Age - Standard HTTP header. Header will specify the age of columns list cached response. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  |
| **400** | Invalid identifier provided. |  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  |
| **401** | Missing or invalid authentication. |  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  |
| **403** | User is forbidden with current credentials |  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  |
| **406** | Unsupported Accept header. Header needs to be set to application/json. |  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  |
| **500** | Server error. Log the X-DataDirect-Request-Key header to assist in troubleshooting. |  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  |
| **503** | Request timed out. Retry the request in sometime. |  * X-FactSet-Api-Request-Key - Key to uniquely identify an Analytics API request. Only available after successful authentication. <br>  * X-DataDirect-Request-Key - FactSet's request key header. <br>  |
