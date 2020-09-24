package Api;

public class CommonParameters {

  // Set 'ANALYTICS_API_USERNAME_SERIAL' environment variable with username-serial
  // as value
  public static final String USERNAME = System.getenv("ANALYTICS_API_USERNAME_SERIAL");
  // Set 'ANALYTICS_API_PASSWORD' environment variable with the api key generated
  // on developer portal
  public static final String PASSWORD = System.getenv("ANALYTICS_API_PASSWORD");

  // Set 'ANALYTICS_API_URL' environment variable with api url as value
  public static final String BASEPATH = System.getenv("ANALYTICS_API_URL") != null
      && !System.getenv("ANALYTICS_API_URL").isEmpty() ? System.getenv("ANALYTICS_API_URL") : "https://api.factset.com";

  public static final String PA_DEFAULT_DOCUMENT = "PA_DOCUMENTS:DEFAULT";
  public static final String PA_BENCHMARK_SP500 = "BENCH:SP50";
  public static final String PA_BENCHMARK_R1000 = "BENCH:R.1000";
  public static final String SPAR_DEFAULT_DOCUMENT = "pmw_root:/spar_documents/Factset Default Document";
  public static final String SPAR_BENCHMARK_R1000 = "R.1000";
  public static final String SPAR_BENCHMARK_R2000 = "R.2000";
  public static final String SPAR_BENCHMARK_RUSSELL_P_R1000 = "RUSSELL_P:R.2000";
  public static final String SPAR_BENCHMARK_RUSSELL_PREFIX = "RUSSELL";
  public static final String SPAR_BENCHMARK_RUSSELL_RETURN = "GTR";
  public static final String VAULT_DEFAULT_DOCUMENT = "Client:/aapi/VAULT_QA_PI_DEFAULT_LOCKED";
  public static final String VAULT_DEFAULT_ACCOUNT = "CLIENT:/BISAM/REPOSITORY/QA/SMALL_PORT.ACCT";
  public static final String VAULT_START_DATE_FIRST = "20180101";
  public static final String VAULT_END_DATE_FIRST = "20180331";
  public static final String VAULT_FREQUENCY_DATE_MONTHLY = "Monthly";
  public static final String PUB_DEFAULT_DOCUMENT = "Client:/AAPI/Puma Test Doc.Pub_bridge_pdf";
  public static final String PUB_DEFAULT_ACCOUNT = "BENCH:SP50";
  public static final String DEFAULT_LOOKUP_DIRECTORY = "client:";
}
