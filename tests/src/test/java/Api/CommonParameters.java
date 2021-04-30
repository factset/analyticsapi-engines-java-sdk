package Api;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.util.Pair;

import factset.analyticsapi.engines.models.OptimizerTradesList.IdentifierTypeEnum;
import factset.analyticsapi.engines.models.PaDoc;

enum Engine{
	PA,
	SPAR,
	Vault,
	Pub,
	AXP,
	FI,
	FIAB,
	FPO,
	AFI,
	NPO,
	BPM
}
public class CommonParameters {

  // Add 'ANALYTICS_API_DEFAULT_USERNAME_SERIAL' and 'ANALYTICS_API_VAULT_PUB_USERNAME_SERIAL' environment variables with the respective username-serial as value
  public static String DefaultUsername = System.getenv("ANALYTICS_API_DEFAULT_USERNAME_SERIAL");
  public static String VaultPubUsername = System.getenv("ANALYTICS_API_VAULT_PUB_USERNAME_SERIAL");
  public static String OptimizerUsername = System.getenv("ANALYTICS_API_OPTIMIZER_USERNAME_SERIAL");

  // Add 'ANALYTICS_API_DEFAULT_PASSWORD' and 'ANALYTICS_API_VAULT_PUB_PASSWORD' environment variables with the respective api key generated on developer portal
  public static String DefaultPassword =System.getenv("ANALYTICS_API_DEFAULT_PASSWORD");
  public static String VaultPubPassword = System.getenv("ANALYTICS_API_VAULT_PUB_PASSWORD");
  public static String OptimizerPassword = System.getenv("ANALYTICS_API_OPTIMIZER_PASSWORD");
  
  public static Map<Engine, Pair<String,String>> Credentials = new HashMap<Engine, Pair<String, String>>();
    
  private static Map<Engine, Pair<String,String>> setCredentials(Engine engine, Pair<String, String> username) {
	  Credentials.put(engine, username);
	  return Credentials;
  }
  public static void fillCredentials() {
	  setCredentials(Engine.PA, new Pair<>(DefaultUsername, DefaultPassword));
	  setCredentials(Engine.SPAR,new Pair<>(DefaultUsername, DefaultPassword));
	  setCredentials(Engine.Vault, new Pair<>(VaultPubUsername, VaultPubPassword));
	  setCredentials(Engine.Pub, new Pair<>(VaultPubUsername, VaultPubPassword));
	  setCredentials(Engine.AXP, new Pair<>(OptimizerUsername, OptimizerPassword));
	  setCredentials(Engine.FPO, new Pair<>(OptimizerUsername, OptimizerPassword));
	  setCredentials(Engine.AFI, new Pair<>(OptimizerUsername, OptimizerPassword));
	  setCredentials(Engine.NPO, new Pair<>(OptimizerUsername, OptimizerPassword));
	  setCredentials(Engine.BPM, new Pair<>(OptimizerUsername, OptimizerPassword));
  }
  
  // Set 'ANALYTICS_API_URL' environment variable with api url as value
  public static final String BASEPATH = "http://api.inhouse-cauth.factset.com";//System.getenv("ANALYTICS_API_URL") != null
      //&& !System.getenv("ANALYTICS_API_URL").isEmpty() ? System.getenv("ANALYTICS_API_URL") : "https://api.factset.com";

  public static final String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  public static final String PA_BENCHMARK_SP500 = "BENCH:SP50";
  public static final String PA_BENCHMARK_R1000 = "BENCH:R.1000";
  public static final String SPAR_DEFAULT_DOCUMENT = "Client:/aapi/SPAR3_QA_TEST_DOCUMENT";
  public static final String SPAR_BENCHMARK_R1000 = "R.1000";
  public static final String SPAR_BENCHMARK_R2000 = "R.2000";
  public static final String SPAR_BENCHMARK_RUSSELL_P_R1000 = "RUSSELL_P:R.2000";
  public static final String SPAR_BENCHMARK_RUSSELL_PREFIX = "RUSSELL";
  public static final String SPAR_BENCHMARK_RUSSELL_RETURN = "GTR";
  public static final String VAULT_DEFAULT_DOCUMENT = "Client:/aapi/VAULT_QA_PI_DEFAULT_LOCKED";
  public static final String VAULT_DEFAULT_ACCOUNT = "CLIENT:/BISAM/REPOSITORY/QA/SMALL_PORT.ACCT";
  public static final String VAULT_START_DATE_FIRST = "20180101";
  public static final String VAULT_END_DATE_FIRST = "20180329";
  public static final String VAULT_FREQUENCY_DATE_MONTHLY = "Monthly";
  public static final String PUB_DEFAULT_DOCUMENT = "Client:/AAPI/Puma Test Doc.Pub_bridge_pdf";
  public static final String PUB_DEFAULT_ACCOUNT = "BENCH:SP50";
  public static final String DEFAULT_LOOKUP_DIRECTORY = "client:";
  public static final Integer DEADLINE_HEADER_VALUE = 20;
  public static final String CACHE_CONTROL_HEADER = "max-age:300";
  public static final String ACCEPT_HEADER_VALUE = "gzip";
  public static final String DefaultPADatesAccount = "CLIENT:/AAPI/STATIC_HOLDINGS_20191205.ACCT";
  public static final String DefaultVaultDatesAccount = "CLIENT:/BISAM/REPOSITORY/QA/GLOBAL.ACCT";
  public static final String AxiomaAccountId = "CLIENT:/OPTIMIZER/IBM.ACCT";
  public static final String OptimizationDate = "09/01/2020";
  public static final String OptimizationCashflow = "0";
  public static final String StrategyId = "Client:/Optimizer/CN_TEST";
  public static final IdentifierTypeEnum TradesIdType = IdentifierTypeEnum.SEDOLCHK;
  public static final Boolean IncudeCash = false;
  public static final String FpoAccountId = "PERSONAL:ROBINHOODPORTFOLIO.ACCT";
  public static final String FpoPaDocName = "PERSONAL:FPO_MASTER";
}