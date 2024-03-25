package Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import factset.analyticsapi.engines.ApiClient;
import factset.analyticsapi.engines.ApiException;
import factset.analyticsapi.engines.ApiResponse;
import factset.analyticsapi.engines.api.QuantCalculationsApi;
import factset.analyticsapi.engines.models.*;
import factset.analyticsapi.engines.models.QuantCalculationMeta.FormatEnum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class QuantInteractiveEngineTests {
    private static ApiClient apiClient;
    private QuantCalculationsApi apiInstance;
    private static int pageNumber;

    @BeforeClass
    public static void beforeClass() throws ApiException {
        apiClient = CommonFunctions.buildApiClient(CommonParameters.DefaultUsername, CommonParameters.DefaultPassword);
        pageNumber = 1;
    }

    @Before
    public void before() {
        apiInstance = new QuantCalculationsApi(apiClient);
    }

    private QuantCalculationParameters enginesApi_createUnitCalculation(boolean expectedWarningsInResponse) throws ApiException {

        QuantCalculationParameters quantItem = new QuantCalculationParameters();

        QuantFdsDate fdsDate = new QuantFdsDate();
        fdsDate.setStartDate(CommonParameters.QuantStartDate);
        fdsDate.setEndDate(CommonParameters.QuantEndDate);
        fdsDate.setFrequency(CommonParameters.QuantFrequency);
        fdsDate.setCalendar(CommonParameters.QuantCalender);
        fdsDate.setSource(QuantFdsDate.SourceEnum.FDSDATE);
        
        OneOfQuantDates dates = new OneOfQuantDates(fdsDate);

        QuantScreeningExpressionUniverse screeningExpressionUniverse = new QuantScreeningExpressionUniverse();
        screeningExpressionUniverse.setUniverseExpr(CommonParameters.QuantUniverseExpr);
        screeningExpressionUniverse.setUniverseType(CommonParameters.QuantUniverseType);
        screeningExpressionUniverse.setSecurityExpr(CommonParameters.QuantSecurityExpr);
        screeningExpressionUniverse.setSource(QuantScreeningExpressionUniverse.SourceEnum.SCREENINGEXPRESSIONUNIVERSE);
        
        OneOfQuantUniverse universe = new OneOfQuantUniverse(screeningExpressionUniverse);

        QuantScreeningExpression screeningExpression = new QuantScreeningExpression();

        String expr = expectedWarningsInResponse ? CommonParameters.QuantInvalidScreeningExpr : CommonParameters.QuantScreeningExpr;
        screeningExpression.expr(expr);
        screeningExpression.name(CommonParameters.QuantScreeningName);
        screeningExpression.setSource(QuantScreeningExpression.SourceEnum.SCREENINGEXPRESSION);

        QuantFqlExpression fqlExpression = new QuantFqlExpression();
        fqlExpression.expr(CommonParameters.QuantFqlExpr);
        fqlExpression.name(CommonParameters.QuantFqlName);
        fqlExpression.setSource(QuantFqlExpression.SourceEnum.FQLEXPRESSION);
        
        List<OneOfQuantFormulas> formulas = new ArrayList<OneOfQuantFormulas>();
        formulas.add(new OneOfQuantFormulas(screeningExpression));
        formulas.add(new OneOfQuantFormulas(fqlExpression));

        quantItem.setDates(dates);
        quantItem.setUniverse(universe);
        quantItem.setFormulas(formulas);

        return quantItem;
    }

    private ApiResponse<File> GetCalculationResult(String[] location) throws ApiException {
        ApiResponse<File> resultResponse = null;
        try {
            String calcId = location[location.length-4];
            String unitId = location[location.length-2];
            resultResponse = apiInstance.getCalculationUnitResultByIdWithHttpInfo(calcId, unitId);
        } catch (ApiException e) {
            CommonFunctions.handleException("EngineApi#getByUrlWithHttpInfo", e);
        }
        return resultResponse;
    }
     
    private QuantCalculationParameters enginesApi_isArrayReturnType_createUnitCalculation() throws ApiException {
        QuantCalculationParameters quantItem = new QuantCalculationParameters();

        QuantFdsDate fdsDate = new QuantFdsDate();
        fdsDate.setStartDate(CommonParameters.QuantStartDate);
        fdsDate.setEndDate(CommonParameters.QuantEndDate);
        fdsDate.setFrequency(CommonParameters.QuantFrequency);
        fdsDate.setCalendar(CommonParameters.QuantCalender);
        fdsDate.setSource(QuantFdsDate.SourceEnum.FDSDATE);

        OneOfQuantDates dates = new OneOfQuantDates(fdsDate);

        List<String> identifiers = new ArrayList<String>();
        identifiers.add("03748R74");
        identifiers.add("S8112735");

        QuantIdentifierUniverse identifierUniverse = new QuantIdentifierUniverse();
        identifierUniverse.setUniverseType(QuantIdentifierUniverse.UniverseTypeEnum.EQUITY);
        identifierUniverse.setIdentifiers(identifiers);
        identifierUniverse.setSource(QuantIdentifierUniverse.SourceEnum.IDENTIFIERUNIVERSE);

        OneOfQuantUniverse universe = new OneOfQuantUniverse(identifierUniverse);

        QuantScreeningExpression screeningExpression = new QuantScreeningExpression();
        screeningExpression.setExpr(CommonParameters.QuantScreeningExpr);
        screeningExpression.setName(CommonParameters.QuantScreeningName);
        screeningExpression.setSource(QuantScreeningExpression.SourceEnum.SCREENINGEXPRESSION);

        QuantFqlExpression fqlExpression = new QuantFqlExpression();
        fqlExpression.setExpr(CommonParameters.QuantFqlExpr);
        fqlExpression.setName(CommonParameters.QuantFqlName);
        fqlExpression.setSource(QuantFqlExpression.SourceEnum.FQLEXPRESSION);

        QuantFqlExpression fqlExpression_isArrayReturnType = new QuantFqlExpression();
        fqlExpression_isArrayReturnType.setExpr(CommonParameters.QuantFqlExpr_Price_Range);
        fqlExpression_isArrayReturnType.setName(CommonParameters.QuantFqlName_Price_Label);
        fqlExpression_isArrayReturnType.setIsArrayReturnType(true);
        fqlExpression_isArrayReturnType.setSource(QuantFqlExpression.SourceEnum.FQLEXPRESSION);

        List<OneOfQuantFormulas> formulas = new ArrayList<OneOfQuantFormulas>();
        formulas.add(new OneOfQuantFormulas(screeningExpression));
        formulas.add(new OneOfQuantFormulas(fqlExpression));
        formulas.add(new OneOfQuantFormulas(fqlExpression_isArrayReturnType));

        quantItem.setDates(dates);
        quantItem.setUniverse(universe);
        quantItem.setFormulas(formulas);

        return quantItem;
    }
    
    private void ProcessCalculations(QuantCalculationParameters calculationUnit) throws ApiException, JsonProcessingException, InterruptedException {
    	ApiResponse<Object> response = null;
        CalculationStatusRoot resultStatus = null;
        Map<String, List<String>> headers = null;
        try {
        	
            QuantCalculationParametersRoot parameters = new QuantCalculationParametersRoot();
            
            QuantCalculationMeta meta = new QuantCalculationMeta();
            meta.setFormat(FormatEnum.FEATHER);
            parameters.setMeta(meta);
            
            parameters.putDataItem("1", calculationUnit);           
            response = apiInstance.postAndCalculateWithHttpInfo("max-stale=0", parameters);
            headers = response.getHeaders();
        } catch (ApiException e) {
            CommonFunctions.handleException("EngineApi#runCalculation", e);
        }

        Assert.assertTrue("Create response status code should be 201 or 202",
                response.getStatusCode() == 201 || response.getStatusCode() == 202);
        ApiResponse<File> resultResponse = null;
        File resultObject = null;

        switch(response.getStatusCode()) {
            case 201:
                resultObject = (File)response.getData();
                Assert.assertTrue("Result response data should not be null.", resultObject != null);
                break;
            case 202:
                String[] locationList = headers.get("Location").get(0).split("/");
                String requestId = locationList[locationList.length - 2];
                ApiResponse<CalculationStatusRoot> resultStatusResponse =null;
                do {
                    resultStatusResponse = apiInstance.getCalculationStatusByIdWithHttpInfo(requestId);
                    headers = resultStatusResponse.getHeaders();
                    resultStatus = (CalculationStatusRoot)resultStatusResponse.getData();
                    Assert.assertTrue("Get status response status code should be 200 or 202",
                            resultStatusResponse.getStatusCode() == 200 || resultStatusResponse.getStatusCode() == 202);
                    int waitTimeInSeconds = 10;
                    try {
                        System.out.println("\n **** Waiting for " + waitTimeInSeconds + " seconds **** \n");
                        TimeUnit.SECONDS.sleep(waitTimeInSeconds);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                } while(resultStatusResponse.getStatusCode() == 202);
                for(CalculationUnitStatus unitStatus : resultStatus.getData().getUnits().values()) {
                    if(unitStatus.getWarnings()!=null){
                        Assert.assertTrue("unitStatus with warnings should have a reason", (!unitStatus.getWarnings().get(0).isEmpty()));
                    }
                    String[] location = unitStatus.getResult().split("/");
                    resultResponse = GetCalculationResult(location);
                    headers = resultResponse.getHeaders();
                    resultObject = resultResponse.getData();
                }
                Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
                Assert.assertTrue("Result response data should not be null.", resultObject != null);
                break;
        }
    }   
    
    @Test
    public void enginesApi_isArrayReturnType_GetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException {
    	try {
            QuantCalculationParameters calculationUnit = enginesApi_isArrayReturnType_createUnitCalculation();
            ProcessCalculations(calculationUnit);
        } catch (ApiException e) {
            CommonFunctions.handleException("EngineApi#runCalculation", e);
        }
    }

    @Test
    public void enginesApiGetCalculationSuccess() throws ApiException, JsonProcessingException, InterruptedException {
    	try {
            QuantCalculationParameters calculationUnit = enginesApi_createUnitCalculation(false);
            ProcessCalculations(calculationUnit);
        } catch (ApiException e) {
            CommonFunctions.handleException("EngineApi#runCalculation", e);
        }
    }

    @Test
    public void enginesApiGetCalculationSuccessWithwarnings() throws ApiException, JsonProcessingException, InterruptedException {
        try {
            QuantCalculationParameters calculationUnit = enginesApi_createUnitCalculation(true);
            ProcessCalculations(calculationUnit);
        } catch (ApiException e) {
            CommonFunctions.handleException("EngineApi#runCalculation", e);
        }
    }

    @Test
    public void enginesApiGetAllCalculationsSuccess() throws ApiException
    {
  	  ApiResponse<CalculationsSummaryRoot> resultResponse = null;
  	  try {
  	        resultResponse = apiInstance.getAllCalculationsWithHttpInfo(pageNumber);
  	      } catch (ApiException e) {
  	        CommonFunctions.handleException("EngineApi#getAllCalculationsWithHttpInfo", e);
  	      }
  	      Assert.assertTrue("Result response status code should be 200 - OK.", resultResponse.getStatusCode() == 200);
  	      Assert.assertTrue("Result response data should not be null.", resultResponse.getData() != null);
    }
}
