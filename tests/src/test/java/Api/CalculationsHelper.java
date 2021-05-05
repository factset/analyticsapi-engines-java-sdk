package Api;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.factset.protobuf.stach.v2.PackageProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto;
import com.factset.protobuf.stach.v2.RowOrganizedProto.RowOrganizedPackage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.util.JsonFormat;

public class CalculationsHelper {
  public static void validateCalculationResponse(Map<String, List<String>> headers, Object resultObject) {
    try {
      ObjectMapper mapper = new ObjectMapper();     
      String jsonString = mapper.writeValueAsString(resultObject);

      if(headers.get("content-type").get(0).toLowerCase().contains("column")) {
        PackageProto.Package.Builder builder = PackageProto.Package.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(jsonString, builder);
        PackageProto.Package resultBuilder = (builder).build();
        Assert.assertTrue("Response should have atleast one table.", resultBuilder.getTablesCount() > 0);
        Assert.assertTrue("Response should be of ColumnDataPackage type.", resultBuilder instanceof PackageProto.Package);
      }
      else {
        RowOrganizedProto.RowOrganizedPackage.Builder builder = RowOrganizedProto.RowOrganizedPackage.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(jsonString, builder);
        RowOrganizedProto.RowOrganizedPackage resultBuilder = builder.build();
        Assert.assertTrue("Response should have atleast one table.", resultBuilder.getTablesCount() > 0);
        Assert.assertTrue("Response should be of RowOrganizedPackage type.", resultBuilder instanceof RowOrganizedPackage);
      }        
    } catch(Exception e) {
      System.out.println(e.getMessage());
      e.getStackTrace();
    }
  }
}
