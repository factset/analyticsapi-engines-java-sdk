package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.client.ClientBuilder;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.AfiOptimizerApi;
import factset.analyticsapi.engines.models.AFIOptimizationParameters;
import factset.analyticsapi.engines.models.AFIOptimizationParametersRoot;
import factset.analyticsapi.engines.models.AFIOptimizerStrategy;
import factset.analyticsapi.engines.models.ObjectRoot;
import factset.analyticsapi.engines.models.OptimizerOutputTypes;
import factset.analyticsapi.engines.models.OptimizerTradesList;
import factset.analyticsapi.engines.models.OptimizerTradesList.IdentifierTypeEnum;

public class AfiInteractiveOptimizerEngineExample {
    private static FdsApiClient apiClient = null;
    private static String BASE_PATH = "https://api.factset.com";
    private static String USERNAME = "<username-serial>";
    private static String PASSWORD = "<apiKey>";

    private static String STRATEGY_ID = "CLIENT:/Analytics_api/AFIAPISIMPLE";
    private static IdentifierTypeEnum TRADES_ID_TYPE = IdentifierTypeEnum.ASSET;
    private static Boolean INCLUDE_CASH = false;

    public static void main(String[] args) throws InterruptedException, JsonProcessingException {
        try{
            AfiOptimizerApi apiInstance = new AfiOptimizerApi(getApiClient());
            AFIOptimizationParameters afiItem = new AFIOptimizationParameters();

            AFIOptimizerStrategy strategy = new AFIOptimizerStrategy();
            strategy.setId(STRATEGY_ID);

            OptimizerOutputTypes optOutputTypes = new OptimizerOutputTypes();
            OptimizerTradesList tradesList = new OptimizerTradesList();
            tradesList.setIdentifierType(TRADES_ID_TYPE);
            tradesList.setIncludeCash(INCLUDE_CASH);
            optOutputTypes.setTrades(tradesList);

            afiItem.setStrategy(strategy);
            afiItem.setOutputTypes(optOutputTypes);

            AFIOptimizationParametersRoot afiOptimizerParam = new AFIOptimizationParametersRoot();
            afiOptimizerParam.setData(afiItem);

            ApiResponse<Object> response = apiInstance.postAndOptimizeWithHttpInfo(null, null, afiOptimizerParam);
            Map<String, List<String>> headers = response.getHeaders();

            Object result = null;
            switch(response.getStatusCode()) {
                case 201: // Calculation completed
                    System.out.println("Calculation successful!!!");
                    result = ((ObjectRoot)response.getData()).getData();
                    headers = response.getHeaders();
                    break;
                case 202:
                    String requestId = headers.get("X-Factset-Api-Calculation-Id").get(0);
                    do {
                        response = apiInstance.getOptimizationStatusByIdWithHttpInfo(requestId);
                        headers = response.getHeaders();

                        List<String> cacheControl = headers.get("Cache-Control");
                        if (cacheControl != null) {
                            int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
                            System.out.println("Sleeping for: " + maxAge + " seconds");
                            Thread.sleep(maxAge * 1000L);
                        } else {
                            System.out.println("Sleeping for: 2 seconds");
                            Thread.sleep(2 * 1000L);
                        }
                    } while(response.getStatusCode() == 202);

                    System.out.println("Calculation successful!!!");
                    // Get Calculation Result
                    String id = headers.get("X-Factset-Api-Calculation-Id").get(0);
                    ApiResponse<ObjectRoot> resultResponse = apiInstance.getOptimizationResultWithHttpInfo(id);
                    result = resultResponse.getData().getData();
                    break;
            }

            List<TableData> tables = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(result);
                JsonNode jsonObject = mapper.readTree(jsonString);

                RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
                stachExtensionBuilder.addTable("tradesTable", jsonObject.get("trades"));
                // stachExtensionBuilder.addTable("optimalTable", jsonObject.get("optimal"));
                tables = stachExtensionBuilder.build().convertToTable();
            } catch(Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(tables);
            System.out.println(json); // Prints the result in 2D table format.
            // Uncomment the following line to generate an Excel file
            // generateExcel(tables);
        } catch (ApiException e) {
            handleException("AfiOptimizerEngineExample#Main", e);
        }
    }

    private static void generateExcel(List<TableData> tableList) {
        for(TableData table : tableList) {
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

    private static class FdsApiClient extends ApiClient
    {
        // Uncomment the below lines to use a proxy server
    /*@Override
    protected void customizeClientBuilder(ClientBuilder clientBuilder) {
      clientConfig.property( ClientProperties.PROXY_URI, "http://127.0.0.1:8866" );
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
        System.err.println("Exception when calling " + method);
        if (e.getResponseHeaders() != null && e.getResponseHeaders().containsKey("x-datadirect-request-key")) {
            System.out.println("x-datadirect-request-key: " + e.getResponseHeaders().get("x-datadirect-request-key").get(0));
        }
        System.out.println("Status code: " + e.getCode());
        System.out.println("Reason: " + e.getClientErrorResponse());
        e.printStackTrace();
    }
}