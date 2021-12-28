

# DummyUniverse

## oneOf schemas
* [QuantIdentifierUniverse](QuantIdentifierUniverse.md)
* [QuantScreeningExpressionUniverse](QuantScreeningExpressionUniverse.md)
* [QuantUniversalScreenUniverse](QuantUniversalScreenUniverse.md)

## Example
```java
// Import classes:
import factset.analyticsapi.engines.models.DummyUniverse;
import factset.analyticsapi.engines.models.QuantIdentifierUniverse;
import factset.analyticsapi.engines.models.QuantScreeningExpressionUniverse;
import factset.analyticsapi.engines.models.QuantUniversalScreenUniverse;

public class Example {
    public static void main(String[] args) {
        DummyUniverse exampleDummyUniverse = new DummyUniverse();

        // create a new QuantIdentifierUniverse
        QuantIdentifierUniverse exampleQuantIdentifierUniverse = new QuantIdentifierUniverse();
        // set DummyUniverse to QuantIdentifierUniverse
        exampleDummyUniverse.setActualInstance(exampleQuantIdentifierUniverse);
        // to get back the QuantIdentifierUniverse set earlier
        QuantIdentifierUniverse testQuantIdentifierUniverse = (QuantIdentifierUniverse) exampleDummyUniverse.getActualInstance();

        // create a new QuantScreeningExpressionUniverse
        QuantScreeningExpressionUniverse exampleQuantScreeningExpressionUniverse = new QuantScreeningExpressionUniverse();
        // set DummyUniverse to QuantScreeningExpressionUniverse
        exampleDummyUniverse.setActualInstance(exampleQuantScreeningExpressionUniverse);
        // to get back the QuantScreeningExpressionUniverse set earlier
        QuantScreeningExpressionUniverse testQuantScreeningExpressionUniverse = (QuantScreeningExpressionUniverse) exampleDummyUniverse.getActualInstance();

        // create a new QuantUniversalScreenUniverse
        QuantUniversalScreenUniverse exampleQuantUniversalScreenUniverse = new QuantUniversalScreenUniverse();
        // set DummyUniverse to QuantUniversalScreenUniverse
        exampleDummyUniverse.setActualInstance(exampleQuantUniversalScreenUniverse);
        // to get back the QuantUniversalScreenUniverse set earlier
        QuantUniversalScreenUniverse testQuantUniversalScreenUniverse = (QuantUniversalScreenUniverse) exampleDummyUniverse.getActualInstance();
    }
}
```


