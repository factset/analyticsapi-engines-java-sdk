package examples;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.factset.protobuf.stach.extensions.ColumnStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensions;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import factset.analyticsapi.engines.models.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.QuantCalculationsApi;

import static factset.analyticsapi.engines.models.QuantScreeningExpressionUniverse.*;

public class QuantInteractiveEngineFeatherExample {
    private static FdsApiClient apiClient = null;
    private static String BASE_PATH = "https://api.factset.com";
    private static String USERNAME = "<username-serial>";
    private static String PASSWORD = "<apiKey>";

    private static String QUANT_START_DATE = "0";
    private static String QUANT_END_DATE = "-5D";
    private static String QUANT_FREQUENCY = "D";
    private static String QUANT_CALENDAR = "FIVEDAY";
    private static String UNIVERSE_EXPR = "ISON_DOW";
    private static UniverseTypeEnum UNIVERSE_TYPE = UniverseTypeEnum.EQUITY;
    private static String SECURITY_EXPR = "TICKER";
    private static String SCREENING_EXPR = "P_PRICE";
    private static String SCREENING_NAME = "Price (SCR)";
    private static String FQL_EXPR = "P_PRICE";
    private static String FQL_NAME = "Price (SCR)";
    private static String CALCULATION_UNIT_ID = "1";

    private static Integer DEADLINE_HEADER_VALUE = null;

    public static void main(String[] args) throws InterruptedException, JsonProcessingException {
        try {
            QuantCalculationsApi apiInstance = new QuantCalculationsApi(getApiClient());
            QuantCalculationParameters quantItem = new QuantCalculationParameters();

            QuantFdsDate fdsDate = new QuantFdsDate();
            fdsDate.startDate(QUANT_START_DATE);
            fdsDate.endDate(QUANT_END_DATE);
            fdsDate.frequency(QUANT_FREQUENCY);
            fdsDate.calendar(QUANT_CALENDAR);

            QuantScreeningExpressionUniverse screeningExpressionUniverse = new QuantScreeningExpressionUniverse();
            screeningExpressionUniverse.universeExpr(UNIVERSE_EXPR);
            screeningExpressionUniverse.universeType(UNIVERSE_TYPE);
            screeningExpressionUniverse.securityExpr(SECURITY_EXPR);

            QuantScreeningExpression screeningExpression = new QuantScreeningExpression();
            screeningExpression.expr(SCREENING_EXPR);
            screeningExpression.name(SCREENING_NAME);

            QuantFqlExpression fqlExpression = new QuantFqlExpression();
            fqlExpression.expr(FQL_EXPR);
            fqlExpression.name(FQL_NAME);

            quantItem.fdsDate(fdsDate);
            quantItem.addScreeningExpressionItem(screeningExpression);
            quantItem.addFqlExpressionItem(fqlExpression);
            quantItem.screeningExpressionUniverse(screeningExpressionUniverse);

            QuantCalculationParametersRoot quantCalculationParam = new QuantCalculationParametersRoot();
            quantCalculationParam.putDataItem("1", quantItem);
            QuantCalculationMeta quantCalculationMeta = new QuantCalculationMeta();
            quantCalculationMeta.format(QuantCalculationMeta.FormatEnum.FEATHER);
            quantCalculationParam.meta(quantCalculationMeta);
            ApiResponse<Object> response = apiInstance.postAndCalculateWithHttpInfo(null, quantCalculationParam);
            Map<String, List<String>> headers = response.getHeaders();

            ApiResponse<CalculationStatusRoot> getStatus = null;
            File result = null;
            switch(response.getStatusCode()) {
                case 200:
                    System.out.println("Calculation failed!!!");
                    CalculationUnitStatus calcUnitStatus = ((CalculationStatusRoot)response.getData()).getData().getUnits().get(CALCULATION_UNIT_ID);
                    System.out.println("Status : " + calcUnitStatus.getStatus());
                    System.out.println("Reason : " + calcUnitStatus.getErrors());
                    System.exit(-1);
                    break;
                case 201:
                    result = (File)response.getData();
                    headers = response.getHeaders();
                    outputCalculationResult(result, "data");
                    break;
                case 202:
                    String[] locationList = response.getHeaders().get("Location").get(0).split("/");
                    String requestId = locationList[locationList.length - 2];
                    // Get Calculation Request Status
                    while (getStatus == null || getStatus.getStatusCode() == 202) {
                        if (getStatus != null) {
                            List<String> cacheControl = getStatus.getHeaders().get("Cache-Control");
                            if (cacheControl != null) {
                                int maxAge = Integer.parseInt(cacheControl.get(0).replace("max-age=", ""));
                                System.out.println("Sleeping for: " + maxAge + " seconds");
                                Thread.sleep(maxAge * 1000L);
                            } else {
                                System.out.println("Sleeping for: 2 seconds");
                                Thread.sleep(2 * 1000L);
                            }
                        }
                        getStatus = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
                        headers = getStatus.getHeaders();
                    }
                    for (Map.Entry<String, CalculationUnitStatus> calculationUnitParameters : getStatus.getData().getData().getUnits().entrySet()) {
                        if (calculationUnitParameters.getValue().getStatus() == CalculationUnitStatus.StatusEnum.SUCCESS)
                        {
                            String[] location = calculationUnitParameters.getValue().getResult().split("/");
                            String id = location[location.length - 4];
                            String unitId = location[location.length - 2];
                            ApiResponse<File> resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(id, unitId);
                            result =  resultResponse.getData();
                            System.out.println("Calculation Data");
                            outputCalculationResult(result, "data");
                            resultResponse = apiInstance.getCalculationUnitInfoByIdWithHttpInfo(id, unitId);
                            result =  resultResponse.getData();
                            System.out.println("Calculation Info");
                            outputCalculationResult(result, "info");
                        }
                    }
                    break;
            }
            System.out.println("Calculation Completed!!!");
        } catch (ApiException e) {
            handleException("QuantInteractiveEngineFeatherExample#Main", e);
        }
    }

    private static void outputCalculationResult(File result, String output_filename) {
        result.renameTo(new File("output-" + output_filename + ".ftr")); // Ensure that file with the same name does not exist in the location
        System.out.println("Result file : output-" + output_filename + ".ftr");
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