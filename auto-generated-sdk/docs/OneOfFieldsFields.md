

# OneOfFieldsFields

## oneOf schemas
* [SMBondFields](SMBondFields.md)
* [SMCustomCashFlowFields](SMCustomCashFlowFields.md)

## Example
```java
// Import classes:
import factset.analyticsapi.engines.models.OneOfFieldsFields;
import factset.analyticsapi.engines.models.SMBondFields;
import factset.analyticsapi.engines.models.SMCustomCashFlowFields;

public class Example {
    public static void main(String[] args) {
        OneOfFieldsFields exampleOneOfFieldsFields = new OneOfFieldsFields();

        // create a new SMBondFields
        SMBondFields exampleSMBondFields = new SMBondFields();
        // set OneOfFieldsFields to SMBondFields
        exampleOneOfFieldsFields.setActualInstance(exampleSMBondFields);
        // to get back the SMBondFields set earlier
        SMBondFields testSMBondFields = (SMBondFields) exampleOneOfFieldsFields.getActualInstance();

        // create a new SMCustomCashFlowFields
        SMCustomCashFlowFields exampleSMCustomCashFlowFields = new SMCustomCashFlowFields();
        // set OneOfFieldsFields to SMCustomCashFlowFields
        exampleOneOfFieldsFields.setActualInstance(exampleSMCustomCashFlowFields);
        // to get back the SMCustomCashFlowFields set earlier
        SMCustomCashFlowFields testSMCustomCashFlowFields = (SMCustomCashFlowFields) exampleOneOfFieldsFields.getActualInstance();
    }
}
```


