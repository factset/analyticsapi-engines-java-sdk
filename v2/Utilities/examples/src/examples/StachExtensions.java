package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.factset.protobuf.stach.NullValues;
import com.factset.protobuf.stach.PackageProto.Package;
import com.factset.protobuf.stach.table.DataTypeProto.DataType;
import com.factset.protobuf.stach.table.SeriesDataProto.SeriesData;
import com.factset.protobuf.stach.table.SeriesDefinitionProto.SeriesDefinition;
import com.factset.protobuf.stach.table.TableProto.Table;

import com.google.protobuf.Duration;
import com.google.protobuf.Timestamp;

public class StachExtensions {
  // The purpose of this class is to provide the helper methods for converting
  // stach to Tabular format.

  public static void generateExcel(Package packageObj) {
    for (TableData table : convertToTableFormat(packageObj)) {
      writeDataToExcel(table, UUID.randomUUID().toString() + ".xlsx");
    }
  }

  public static List<TableData> convertToTableFormat(Package packageObj) {
    List<TableData> tables = new ArrayList<TableData>();
    for (String primaryTableId : packageObj.getPrimaryTableIdsList()) {
      tables.add(generateTable(packageObj, primaryTableId));
    }
    return tables;
  }

  private static TableData generateTable(Package packageObj, String primaryTableId) {
    Map<String, Table> tablesMap = packageObj.getTablesMap();
    Table primaryTable = tablesMap.get(primaryTableId);
    String headerId = primaryTable.getDefinition().getHeaderTableId();
    Table headerTable = tablesMap.get(headerId);
    int headerRowCount = headerTable.getData().getRowsCount();
    int rowsCount = primaryTable.getData().getRowsCount();

    TableData table = new TableData();

    // Construct the column headers by considering dimension columns and header
    // rows.
    List<SeriesDefinition> headerTableSeriesDefinitions = headerTable.getDefinition().getColumnsList();
    List<SeriesDefinition> primaryTableSeriesDefinitions = primaryTable.getDefinition().getColumnsList();

    Map<String, SeriesData> headerTableColumns = headerTable.getData().getColumnsMap();
    Map<String, SeriesData> primaryTableColumns = primaryTable.getData().getColumnsMap();

    for (SeriesDefinition headerTableseriesDefinition : headerTableSeriesDefinitions) {
      Row headerRow = new Row();
      for (SeriesDefinition primaryTableSeriesDefinition : primaryTableSeriesDefinitions) {
        if (primaryTableSeriesDefinition.getIsDimension()) {
          headerRow.Cells.add(primaryTableSeriesDefinition.getDescription());
        }
      }

      String headerColumnId = headerTableseriesDefinition.getId();
      String nullFormat = headerTableseriesDefinition.getFormat().getNullFormat();
      for (int i = 0; i < headerRowCount; i++) {
        headerRow.Cells.add(SeriesDataHelper.getValueHelper(headerTableColumns.get(headerColumnId),
            headerTableseriesDefinition.getType(), i, nullFormat).toString());
      }
      table.Rows.add(headerRow);
    }

    // Construct the column data
    for (int i = 0; i < rowsCount; i++) {
      Row dataRow = new Row();
      for (SeriesDefinition primaryTableSeriesDefinition : primaryTableSeriesDefinitions) {
        String nullFormat = primaryTableSeriesDefinition.getFormat().getNullFormat();
        String primaryTableColumnId = primaryTableSeriesDefinition.getId();
        dataRow.Cells.add(SeriesDataHelper.getValueHelper(primaryTableColumns.get(primaryTableColumnId),
            primaryTableSeriesDefinition.getType(), i, nullFormat).toString());
      }
      table.Rows.add(dataRow);
    }
    return table;
  }

  private static void writeDataToExcel(TableData table, String fileLocation) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("PA Report");

    int rowsSize = table.Rows.size();
    for (int rowIndex = 0; rowIndex < rowsSize; rowIndex++) {
      XSSFRow xsswRow = sheet.createRow(rowIndex);
      List<String> cells = table.Rows.get(rowIndex).Cells;
      for (int cellIndex = 0; cellIndex < cells.size(); cellIndex++) {
        XSSFCell xssfCell = xsswRow.createCell(cellIndex);
        xssfCell.setCellValue(cells.get(cellIndex));
      }
    }

    try {
      FileOutputStream out = new FileOutputStream(new File(fileLocation));
      workbook.write(out);
      out.close();
      workbook.close();
    } catch (Exception e) {
      System.err.println("Failed to write data to excel");
      e.printStackTrace();
    }
  }
}

class SeriesDataHelper {
  public static Object getValueHelper(SeriesData seriesData, DataType dataType, int index, String nullFormat) {
    if (dataType == DataType.STRING) {
      String value = seriesData.getStringArray().getValues(index);
      return NullValues.STRING.equals(value) ? nullFormat : value;
    } else if (dataType == DataType.DOUBLE) {
      double value = seriesData.getDoubleArray().getValues(index);
      return Double.isNaN(value) ? nullFormat : value;
    } else if (dataType == DataType.BOOL) {
      return seriesData.getBoolArray().getValues(index);
    } else if (dataType == DataType.DURATION) {
      Duration value = seriesData.getDurationArray().getValues(index);
      return NullValues.DURATION.equals(value) ? nullFormat : value;
    } else if (dataType == DataType.FLOAT) {
      float value = seriesData.getFloatArray().getValues(index);
      return Float.isNaN(value) ? nullFormat : value;
    } else if (dataType == DataType.INT32) {
      int value = seriesData.getInt32Array().getValues(index);
      return NullValues.INT32 == value ? nullFormat : value;
    } else if (dataType == DataType.INT64) {
      long value = seriesData.getInt64Array().getValues(index);
      return NullValues.INT64 == value ? nullFormat : value;
    } else if (dataType == DataType.TIMESTAMP) {
      Timestamp value = seriesData.getTimestampArray().getValues(index);
      return NullValues.TIMESTAMP.equals(value) ? nullFormat : value;
    } else {
      throw new NotImplementedException(dataType + " is not implemented");
    }
  }
}

class TableData {
  List<Row> Rows = new ArrayList<Row>();

  public String toString() {
    return Rows.toString();
  }
}

class Row {
  List<String> Cells = new ArrayList<String>();

  public String toString() {
    return Cells.toString();
  }
}