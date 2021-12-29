

# OneOfQuantDates

## oneOf schemas
* [QuantDateList](QuantDateList.md)
* [QuantFdsDate](QuantFdsDate.md)

## Example
```java
// Import classes:
import factset.analyticsapi.engines.models.OneOfQuantDates;
import factset.analyticsapi.engines.models.QuantDateList;
import factset.analyticsapi.engines.models.QuantFdsDate;

public class Example {
    public static void main(String[] args) {
        OneOfQuantDates exampleOneOfQuantDates = new OneOfQuantDates();

        // create a new QuantDateList
        QuantDateList exampleQuantDateList = new QuantDateList();
        // set OneOfQuantDates to QuantDateList
        exampleOneOfQuantDates.setActualInstance(exampleQuantDateList);
        // to get back the QuantDateList set earlier
        QuantDateList testQuantDateList = (QuantDateList) exampleOneOfQuantDates.getActualInstance();

        // create a new QuantFdsDate
        QuantFdsDate exampleQuantFdsDate = new QuantFdsDate();
        // set OneOfQuantDates to QuantFdsDate
        exampleOneOfQuantDates.setActualInstance(exampleQuantFdsDate);
        // to get back the QuantFdsDate set earlier
        QuantFdsDate testQuantFdsDate = (QuantFdsDate) exampleOneOfQuantDates.getActualInstance();
    }
}
```


