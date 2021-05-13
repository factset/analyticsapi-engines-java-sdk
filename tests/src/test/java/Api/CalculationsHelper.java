package Api;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.factset.protobuf.stach.extensions.ColumnStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.RowStachExtensionBuilder;
import com.factset.protobuf.stach.extensions.StachExtensionFactory;
import com.factset.protobuf.stach.extensions.StachExtensions;
import com.factset.protobuf.stach.extensions.models.StachVersion;
import com.factset.protobuf.stach.extensions.models.TableData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalculationsHelper {
  public static void validateCalculationResponse(Map<String, List<String>> headers, Object resultObject) {
    try {
      ObjectMapper mapper = new ObjectMapper();     
      String jsonString = mapper.writeValueAsString(resultObject);
      List<TableData> tableDataList = null;
      if(headers.get("content-type").get(0).toLowerCase().contains("column")) {
        ColumnStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getColumnOrganizedBuilder(StachVersion.V2);
        StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
        tableDataList = stachExtension.convertToTable();        
      }
      else {
        RowStachExtensionBuilder stachExtensionBuilder = StachExtensionFactory.getRowOrganizedBuilder(StachVersion.V2);
        StachExtensions stachExtension = stachExtensionBuilder.setPackage(jsonString).build();
        tableDataList = stachExtension.convertToTable();
      }

      Assert.assertTrue("Response should have atleast one table.", !tableDataList.isEmpty());
      Assert.assertTrue("Response should be of TableData type.", tableDataList.get(0) instanceof TableData);
      Assert.assertTrue("Response should have a table with atleast one row", tableDataList.get(0).getRows().size() > 0);
    } catch(Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
