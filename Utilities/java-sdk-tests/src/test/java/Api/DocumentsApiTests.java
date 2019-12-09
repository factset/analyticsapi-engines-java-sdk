package Api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;

public class DocumentsApiTests {

  public static ApiClient apiClient;
  public static DocumentsApi apiInstance;

  @BeforeClass
  public static void beforeClass() throws ApiException {
    apiClient = CommonFunctions.buildApiClient();
  }

  @Before
  public void before() {
    apiInstance = new DocumentsApi(apiClient);
  }

  @Test
  public void getPA3DocumentListSuccess() throws ApiException {
    ApiResponse<DocumentDirectories> getDocumentListResponse = null;

    try {
      getDocumentListResponse = apiInstance.getPA3DocumentsWithHttpInfo(CommonParameters.DEFAULT_LOOKUP_DIRECTORY);

      Assert.assertTrue("Response should be 200 - Success", getDocumentListResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getDocumentListResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("DocumentsApi#getPA3DocumentsWithHttpInfo", e);
    }
  }

  @Test
  public void getVaultDocumentListSuccess() throws ApiException {
    ApiResponse<DocumentDirectories> getDocumentListResponse = null;

    try {
      getDocumentListResponse = apiInstance.getVaultDocumentsWithHttpInfo(CommonParameters.DEFAULT_LOOKUP_DIRECTORY);

      Assert.assertTrue("Response should be 200 - Success", getDocumentListResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getDocumentListResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("DocumentsApi#getVaultDocumentsWithHttpInfo", e);
    }
  }

  @Test
  public void getSPARDocumentListSuccess() throws ApiException {
    ApiResponse<DocumentDirectories> getDocumentListResponse = null;

    try {
      getDocumentListResponse = apiInstance.getSPAR3DocumentsWithHttpInfo(CommonParameters.DEFAULT_LOOKUP_DIRECTORY);

      Assert.assertTrue("Response should be 200 - Success", getDocumentListResponse.getStatusCode() == 200);
      Assert.assertTrue("Response data should not be null.", getDocumentListResponse.getData() != null);
    } catch (ApiException e) {
      CommonFunctions.handleException("DocumentsApi#getSPAR3DocumentsWithHttpInfo", e);
    }
  }

}