package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
//import org.glassfish.jersey.client.ClientConfig;
//import org.glassfish.jersey.client.ClientProperties;

import factset.analyticsapi.engines.*;
import factset.analyticsapi.engines.api.*;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.StachExtensions.*;

import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.InvalidProtocolBufferException;
import com.factset.protobuf.stach.PackageProto.Package.Builder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.factset.protobuf.stach.PackageProto.Package;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static factset.analyticsapi.engines.StachExtensions.convertToTableFormat;

public class MultipleEnginesExample {

  private static FdsApiClient apiClient = null;
  private static final String BASE_PATH = "https://api.factset.com";
  private static final String USERNAME = "<username-serial>";
  private static final String PASSWORD = "<apiKey>";
  private static final String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  private static final String VAULT_DEFAULT_DOCUMENT = "PA3_DOCUMENTS:DEFAULT";
  private static final String VAULT_DEFAULT_ACCOUNT = "Client:/analytics/data/US_MID_CAP_CORE.ACTM";
  private static final String SPAR_DEFAULT_DOCUMENT = "pmw_root:/spar_documents/Factset Default Document";

  public static void main(String[] args) throws InterruptedException, JsonProcessingException {
    try {
      Calculation parameters = new Calculation();

      // Build PA Calculation Parameters List
      PACalculationParameters paItem = getPAItem();
      parameters.putPaItem("1", paItem);

      // Build Vault Calculation Parameters List
      VaultCalculationParameters vaultItem = getVaultItem();
      parameters.putVaultItem("2", vaultItem);

      // Build SPAR Calculation Parameters List
      SPARCalculationParameters sparItem = getSparItem();
      parameters.putSparItem("3", sparItem);

      // Run Calculation Request
      CalculationsApi apiInstance = new CalculationsApi(getApiClient());
      ApiResponse<Void> createResponse = null;

      createResponse = apiInstance.runCalculationWithHttpInfo(parameters);

      String[] locationList = createResponse.getHeaders().get("Location").get(0).split("/");
      String requestId = locationList[locationList.length - 1];
      System.out.println("Calculation Id: "+ requestId);
      // Get Calculation Request Status
      ApiResponse<CalculationStatus> getStatus = null;

      while (getStatus == null || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.QUEUED
              || getStatus.getData().getStatus() == CalculationStatus.StatusEnum.EXECUTING) {
        if (getStatus != null) {
          List<String> cacheControl = getStatus.getHeaders().get("Cache-Control");
          if (cacheControl != null) {
            int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
            System.out.println("Sleeping for: " + maxAge + " seconds");
            Thread.sleep(maxAge * 1000);
          } else {
            System.out.println("Sleeping for: 2 seconds");
            Thread.sleep(2 * 1000);
          }
        }
        getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
      }

      System.out.println("Calculation Completed!!!");

      // Check for Calculation Units 
      for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getPa().entrySet()) {
        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS){
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Succeeded!!!");
          printResult(calculationUnitParameters);
        }
        else{
          System.out.println("Calculation Unit Id: " + calculationUnitParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationUnitParameters.getValue().getError());
        }
      }

      for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getSpar().entrySet()) {
        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS){
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Succeeded!!!");
          printResult(calculationUnitParameters);
        } 
        else{
          System.out.println("Calculation Unit Id: " + calculationUnitParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationUnitParameters.getValue().getError());
        }
      }

      for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getVault().entrySet()) {
        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS){
          System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Succeeded!!!");
          printResult(calculationUnitParameters);
        }
        else{
          System.out.println("Calculation Unit Id: " + calculationUnitParameters.getKey() + " Failed!!!");
          System.out.println("Error message : " + calculationUnitParameters.getValue().getError());
        }
      }

    } catch (ApiException e) {
      handleException("MultipleEnginesExample#Main", e);
      return;
    }
 }

  private static void printResult(Map.Entry<String, CalculationUnitStatus> calculationUnitParameters) throws ApiException, JsonProcessingException {
    if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS) {
      UtilityApi utilityApiInstance = new UtilityApi(apiClient);
      ApiResponse<String> resultResponse = utilityApiInstance
        .getByUrlWithHttpInfo(calculationUnitParameters.getValue().getResult());
      
      System.out.println("Calculation Unit Id : " + calculationUnitParameters.getKey() + " Result");

      Builder builder = Package.newBuilder();
      try {
        JsonFormat.parser().ignoringUnknownFields().merge(resultResponse.getData(), builder);
      } catch (InvalidProtocolBufferException e) {
        System.out.println("Error while deserializing the response");
        e.printStackTrace();
      }

      Package result = builder.build();
      // To convert result to 2D tables.
      List<TableData> tables = convertToTableFormat(result);
      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(tables.get(0));
      System.out.println(json); // Prints the result in 2D table format.
      // Uncomment the following line to generate an Excel file
      // generateExcel(result);
    }
  }

  private static void generateExcel(Package packageObj) {
    for (TableData table : convertToTableFormat(packageObj)) {
      writeDataToExcel(table, UUID.randomUUID().toString() + ".xlsv");
    }
  }

  private static void writeDataToExcel(TableData table, String fileLocation) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Calculation Report");
    int rowsSize = table.getRows().size();
    for (int rowIndex = 0; rowIndex < rowsSize; rowIndex++) {
      XSSFRow xsswRow = sheet.createRow(rowIndex);
      List<String> cells = table.getRows().get(rowIndex).getCells();
      for (int cellIndex = 0; cellIndex < cells.size(); cellIndex++) {
        XSSFCell xssfCell = xsswRow.createCell(cellIndex);
        xssfCell.setCellValue(cells.get(cellIndex));
      }
    }
    try {
      FileOutputStream fileStream = new FileOutputStream(new File(fileLocation));
      workbook.write(fileStream);
      fileStream.close();
      workbook.close();
    } catch (Exception e) {
      System.err.println("Failed to write data to excel");
      e.printStackTrace();
    }
  }

  private static SPARCalculationParameters getSparItem() throws ApiException {
    SPARCalculationParameters sparItem = new SPARCalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(getApiClient());
    Map<String, ComponentSummary> components = componentsApi.getSPARComponents(SPAR_DEFAULT_DOCUMENT);
    String componentId = components.entrySet().iterator().next().getKey();
    System.out.println("SPAR Component Id: " + componentId);
    sparItem.setComponentid(componentId);

    SPARIdentifier accountIdentifier1 = new SPARIdentifier();
    accountIdentifier1.setId("R.1000");
    accountIdentifier1.setPrefix("RUSSELL");
    accountIdentifier1.setReturntype("GTR");
    sparItem.addAccountsItem(accountIdentifier1);

    SPARIdentifier accountIdentifier2 = new SPARIdentifier();
    accountIdentifier2.setId("RUSSELL_P:R.2000");
    accountIdentifier2.setPrefix("RUSSELL");
    accountIdentifier2.setReturntype("GTR");
    sparItem.addAccountsItem(accountIdentifier2);

    SPARIdentifier benchmarkIdentifier = new SPARIdentifier();
    benchmarkIdentifier.setId("R.2000");
    benchmarkIdentifier.setPrefix("RUSSELL");
    benchmarkIdentifier.setReturntype("GTR");
    sparItem.setBenchmark(benchmarkIdentifier);

    return sparItem;
  }

  private static VaultCalculationParameters getVaultItem() throws ApiException {
    VaultCalculationParameters vaultItem = new VaultCalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(getApiClient());
    Map<String, ComponentSummary> components = componentsApi.getVaultComponents(VAULT_DEFAULT_DOCUMENT);
    String componentId = components.entrySet().iterator().next().getKey();
    System.out.println("Vault Component Id: " + componentId);
    vaultItem.setComponentid(componentId);

    ConfigurationsApi configurationsApi = new ConfigurationsApi(getApiClient());
    Map<String, VaultConfigurationSummary> configurationsMap = configurationsApi.getVaultConfigurations(VAULT_DEFAULT_ACCOUNT);
    String configurationId = configurationsMap.entrySet().iterator().next().getKey();
    vaultItem.setConfigid(configurationId);

    VaultIdentifier account = new VaultIdentifier();
    account.setId(VAULT_DEFAULT_ACCOUNT);
    vaultItem.setAccount(account);

    VaultDateParameters dateParameters = new VaultDateParameters();
    dateParameters.setStartdate("FIRST_REPOSITORY");
    dateParameters.setEnddate("LAST_REPOSITORY");
    dateParameters.setFrequency("Monthly");
    vaultItem.setDates(dateParameters);

    return vaultItem;
  }

  private static PACalculationParameters getPAItem() throws ApiException {
    PACalculationParameters paItem = new PACalculationParameters();

    ComponentsApi componentsApi = new ComponentsApi(getApiClient());
    Map<String, ComponentSummary> components = componentsApi.getPAComponents(PA_DEFAULT_DOCUMENT);
    String componentId = components.entrySet().iterator().next().getKey();
    System.out.println("PA Component Id: " + componentId);
    paItem.setComponentid(componentId);

    PAIdentifier accountPaIdentifier1 = new PAIdentifier();
    accountPaIdentifier1.setId("BENCH:SP50");
    paItem.addAccountsItem(accountPaIdentifier1);

    PAIdentifier accountPaIdentifier2 = new PAIdentifier();
    accountPaIdentifier2.setId("BENCH:R.2000");
    paItem.addAccountsItem(accountPaIdentifier2);

    PAIdentifier benchmarkPaIdentifier = new PAIdentifier();
    benchmarkPaIdentifier.setId("BENCH:R.2000");
    paItem.addBenchmarksItem(benchmarkPaIdentifier);

    return paItem;
  }
  
  private static class FdsApiClient extends ApiClient
  {
    // Uncomment the below lines to use a proxy server
    /*@Override
    protected void performAdditionalClientConfiguration(ClientConfig clientConfig) {
	  clientConfig.property( ClientProperties.PROXY_URI, "<proxyUrl>" );
	  clientConfig.connectorProvider( new ApacheConnectorProvider() );
    }*/
  }

  private static FdsApiClient getApiClient() {
    if (apiClient != null) {
      return apiClient;
    }

    apiClient = new FdsApiClient();
    apiClient.setConnectTimeout(30000);
    apiClient.setReadTimeout(30000);
    apiClient.setBasePath(BASE_PATH);
    apiClient.setUsername(USERNAME);
    apiClient.setPassword(PASSWORD);

    return apiClient;
  }

  private static void handleException(String method, ApiException e) {
    System.out.println("Calculation Failed!!!");
    System.out.println("Status code: " + e.getCode());
    if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-datadirect-request-key")) {
      System.out.println("Request Key: " + e.getResponseHeaders().get("x-datadirect-request-key").get(0));
    }
    System.out.println("Reason: " + e.getResponseBody());
    e.printStackTrace();
  }
}