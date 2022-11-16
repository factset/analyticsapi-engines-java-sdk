package Api;

import factset.analyticsapi.engines.models.OptimizerTradesList.IdentifierTypeEnum;
import factset.analyticsapi.engines.models.QuantScreeningExpressionUniverse;

public class CommonParameters {

  // Add 'ANALYTICS_API_DEFAULT_QAR_USERNAME_SERIAL' environment variables with the respective username-serial as value
  public static String DefaultUsername = System.getenv("ANALYTICS_API_DEFAULT_QAR_USERNAME_SERIAL");

  // Add 'ANALYTICS_API_DEFAULT_QAR_PASSWORD' environment variables with the respective api key generated on developer portal
  public static String DefaultPassword = System.getenv("ANALYTICS_API_DEFAULT_QAR_PASSWORD");

  // Set 'ANALYTICS_API_URL' environment variable with api url as value
  public static final String BASEPATH = System.getenv("ANALYTICS_API_URL") != null
      && !System.getenv("ANALYTICS_API_URL").isEmpty() ? System.getenv("ANALYTICS_API_URL") : "https://api.factset.com";

  // PA values
  public static final String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  public static final String PA_BENCHMARK_SP500 = "BENCH:SP50";
  public static final String PA_BENCHMARK_R1000 = "BENCH:R.1000";
  public static final String PA_START_DATE = "-3M";
  public static final String PA_END_DATE = "0M";
  public static final String PA_FREQUENCY = "Single";

  // SPAR values
  public static final String SPAR_DEFAULT_DOCUMENT = "Client:/aapi/SPAR3_QA_TEST_DOCUMENT";
  public static final String SPAR_BENCHMARK_R1000 = "R.1000";
  public static final String SPAR_BENCHMARK_R2000 = "R.2000";
  public static final String SPAR_BENCHMARK_RUSSELL_P_R1000 = "RUSSELL_P:R.2000";
  public static final String SPAR_BENCHMARK_RUSSELL_PREFIX = "RUSSELL";
  public static final String SPAR_BENCHMARK_RUSSELL_RETURN = "GTR";

  // Vault values
  public static final String VAULT_DEFAULT_DOCUMENT = "Client:/aapi/VAULT_QA_PI_DEFAULT_LOCKED";
  public static final String VAULT_DEFAULT_ACCOUNT = "CLIENT:/BISAM/REPOSITORY/QA/SMALL_PORT.ACCT";
  public static final String VAULT_SECONDARY_ACCOUNT = "CLIENT:/BISAM/REPOSITORY/QA/GLOBAL.ACCT";
  public static final String VAULT_START_DATE_FIRST = "20180101";
  public static final String VAULT_END_DATE_FIRST = "20180329";
  public static final String VAULT_FREQUENCY_DATE_MONTHLY = "Monthly";

  // Pub values
  public static final String PUB_DEFAULT_DOCUMENT = "Client:/AAPI/Puma Test Doc.Pub_bridge_pdf";
  public static final String PUB_DEFAULT_ACCOUNT = "BENCH:SP50";
  public static final String DEFAULT_LOOKUP_DIRECTORY = "client:";

  // Lookups dates  
  public static final String DefaultPADatesAccount = "CLIENT:/BISAM/REPOSITORY/QA/SMALL_PORT.ACCT";
  public static final String DefaultVaultDatesAccount = "CLIENT:/BISAM/REPOSITORY/QA/GLOBAL.ACCT";

  // AXP values
  public static final String AxiomaAccountId = "BENCH:SP50";
  public static final String OptimizationDate = "09/01/2020";
  public static final String OptimizationCashflow = "0";
  public static final String StrategyId = "Client:/analytics_api/dbui_simple_strategy";
  public static final String SecondaryStrategyId = "Client:/Optimizer/TAXTEST";
  public static final IdentifierTypeEnum TradesIdType = IdentifierTypeEnum.SEDOLCHK;
  public static final Boolean IncludeCash = false;

  // FPO values
  public static final String FpoAccountId = "CLIENT:/FPO/1K_MAC_AMZN_AAPL.ACCT";
  public static final String FpoPaDocName = "CLIENT:/FPO/FPO_MASTER";
  public static final String FpoOptimizationDate = "0M";
  public static final String FpoSecondaryOptimizationDate = "-1M";
  public static final String FpoOptimizationStrategyId = "Client:/fpo/fpo-workflow";

  // FI values
  public static final String FICalcFromMethod = "Price";
  public static final Double FICalcFromValue = 108.40299;
  public static final Double FIFaceValue = (double) 100; 
  public static final String FISymbol = "3140JQHD";
  public static final String FISettlement = "20200922";
  public static final String FIDiscountCurve = "Government";
  public static final String FIAsOfDate = "20200922";
  public static final String FICalculations = "Effective Duration";

  // FIAB values
  public static final String FiabAccountId = "Client:/aapi/FIAB_TEST_HOLDINGS.ACCT";
  public static final String FiabDocument = "Client:/aapi/AAPI_FIAB_BASE_DOC";
  public static final String FiabDate = "20200618";
  public static final String FiabMsl = "CLIENT:$$MSL_AAPI_TESTING.OFDB";
  public static final String FiabFiSettingsDocument = "None";

  // BPM values
  public static final String BpmStrategyId = "CLIENT:/Aapi/BPMAPISIMPLE";
  public static final String BpmSecondaryStrategyId = "CLIENT:/Analytics_api/Optimizers/BPMAPISIMPLE";

  public static final Integer DEADLINE_HEADER_VALUE = 20;
  public static final Integer ZERO_DEADLINE_HEADER_VALUE = 0;
  public static final String ACCEPT_HEADER_VALUE = "gzip";

  // AFI values
  public static String AfiStrategyId = "CLIENT:/Analytics_api/AFIAPISIMPLE";
  public static IdentifierTypeEnum AfiTradesIdType = IdentifierTypeEnum.ASSET;
  public static Boolean AfiIncludeCash = false;

  // Quant values
  public static String QuantStartDate = "0";
  public static String QuantEndDate = "-5D";
  public static String QuantFrequency = "D";
  public static String QuantCalender = "FIVEDAY";
  public static String QuantUniverseExpr = "ISON_DOW";
  public static QuantScreeningExpressionUniverse.UniverseTypeEnum QuantUniverseType = QuantScreeningExpressionUniverse.UniverseTypeEnum.EQUITY;
  public static String QuantSecurityExpr = "TICKER";
  public static String QuantScreeningExpr = "P_PRICE";
  public static String QuantScreeningName = "Price (SCR)";
  public static String QuantFqlExpr = "P_PRICE";
  public static String QuantFqlName = "Price (SCR)";
  public static String QuantFqlExpr_Price_Range = "P_PRICE(#DATE,#DATE-5D,#FREQ)";
  public static String QuantFqlName_Price_Label = "Price";
}