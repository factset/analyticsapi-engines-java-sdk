package Api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AccountsApiTests.class,
                      AfiInteractiveOptimizerEngineApiTests.class,
                      AxpInteractiveOptimizerEngineApiTests.class,
                      BenchmarksApiTests.class,
                      BpmInteractiveOptimizerEngineApiTests.class,
                      ColumnsApiTests.class,
                      ColumnStatisticsApiTests.class,
                      ComponentsApiTests.class,
                      ConfigurationsApiTests.class,
                      CurrenciesApiTests.class,
                      DatesApiTests.class,
                      DocumentsApiTests.class,
                      FiabInteractiveEngineApiTests.class,
                      FiInteractiveEngineApiTests.class,
                      FpoInteractiveOptimizerEngineApiTests.class,
                      FrequenciesApiTests.class,
                      GroupsApiTests.class,
                      PAEngineApiTests.class,
                      PAEngineInteractiveApiTests.class,
                      PubEngineApiTests.class,
                      PubEngineInteractiveApiTests.class,
                      QuantInteractiveEngineTests.class,
                      SPAREngineApiTests.class,
                      SPAREngineInteractiveApiTests.class,
                      StrategyDocumentsApiTests.class,
                      VaultEngineApiTests.class,
                      VaultEngineInteractiveApiTests.class })
public class ApiTestsRunner {
    /*
     * Purpose of this class is to run the test cases contained in the compiled java
     * classes listed above
     */
}
