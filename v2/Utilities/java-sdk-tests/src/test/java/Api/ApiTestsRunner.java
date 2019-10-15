package Api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AccountsApiTests.class, ColumnsApiTests.class, ColumnStatisticsApiTests.class,
    ComponentsApiTests.class, ConfigurationsApiTests.class, CurrenciesApiTests.class, DatesApiTests.class,
    DocumentsApiTests.class, FrequenciesApiTests.class , GroupsApiTests.class, PAEngineApiTests.class, SPAREngineApiTests.class,
    VaultEngineApiTests.class })
public class ApiTestsRunner {
  /*
   * Purpose of this class is to run the test cases contained in the compiled java
   * classes listed above
   */
}
