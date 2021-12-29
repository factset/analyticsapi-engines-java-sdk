

# OneOfQuantFormulasArray

## oneOf schemas
* [QuantAllUniversalScreenParameters](QuantAllUniversalScreenParameters.md)
* [QuantFqlExpression](QuantFqlExpression.md)
* [QuantScreeningExpression](QuantScreeningExpression.md)
* [QuantUniversalScreenParameter](QuantUniversalScreenParameter.md)

## Example
```java
// Import classes:
import factset.analyticsapi.engines.models.OneOfQuantFormulasArray;
import factset.analyticsapi.engines.models.QuantAllUniversalScreenParameters;
import factset.analyticsapi.engines.models.QuantFqlExpression;
import factset.analyticsapi.engines.models.QuantScreeningExpression;
import factset.analyticsapi.engines.models.QuantUniversalScreenParameter;

public class Example {
    public static void main(String[] args) {
        OneOfQuantFormulasArray exampleOneOfQuantFormulasArray = new OneOfQuantFormulasArray();

        // create a new QuantAllUniversalScreenParameters
        QuantAllUniversalScreenParameters exampleQuantAllUniversalScreenParameters = new QuantAllUniversalScreenParameters();
        // set OneOfQuantFormulasArray to QuantAllUniversalScreenParameters
        exampleOneOfQuantFormulasArray.setActualInstance(exampleQuantAllUniversalScreenParameters);
        // to get back the QuantAllUniversalScreenParameters set earlier
        QuantAllUniversalScreenParameters testQuantAllUniversalScreenParameters = (QuantAllUniversalScreenParameters) exampleOneOfQuantFormulasArray.getActualInstance();

        // create a new QuantFqlExpression
        QuantFqlExpression exampleQuantFqlExpression = new QuantFqlExpression();
        // set OneOfQuantFormulasArray to QuantFqlExpression
        exampleOneOfQuantFormulasArray.setActualInstance(exampleQuantFqlExpression);
        // to get back the QuantFqlExpression set earlier
        QuantFqlExpression testQuantFqlExpression = (QuantFqlExpression) exampleOneOfQuantFormulasArray.getActualInstance();

        // create a new QuantScreeningExpression
        QuantScreeningExpression exampleQuantScreeningExpression = new QuantScreeningExpression();
        // set OneOfQuantFormulasArray to QuantScreeningExpression
        exampleOneOfQuantFormulasArray.setActualInstance(exampleQuantScreeningExpression);
        // to get back the QuantScreeningExpression set earlier
        QuantScreeningExpression testQuantScreeningExpression = (QuantScreeningExpression) exampleOneOfQuantFormulasArray.getActualInstance();

        // create a new QuantUniversalScreenParameter
        QuantUniversalScreenParameter exampleQuantUniversalScreenParameter = new QuantUniversalScreenParameter();
        // set OneOfQuantFormulasArray to QuantUniversalScreenParameter
        exampleOneOfQuantFormulasArray.setActualInstance(exampleQuantUniversalScreenParameter);
        // to get back the QuantUniversalScreenParameter set earlier
        QuantUniversalScreenParameter testQuantUniversalScreenParameter = (QuantUniversalScreenParameter) exampleOneOfQuantFormulasArray.getActualInstance();
    }
}
```


