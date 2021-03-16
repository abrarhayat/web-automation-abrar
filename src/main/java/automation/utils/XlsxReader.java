package automation.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author abrar
 * since 10/9/20
 */

public class XlsxReader {

    public static Map<Integer, List<Object>> getDataArrayFromXlsx(String fileName) {
        try {
            FileInputStream file = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);

            //this hashmap will contain all the date in each row for that index
            Map<Integer, List<Object>> data = new HashMap<>();
            int currentRow = 0;
            for (Row row : sheet) {
                /* since we are saving in an ArrayList of Type Object we need to
                cast when we are retrieving them */
                data.put(currentRow, new ArrayList<Object>());
                for (Cell cell : row) {
                    System.out.println("Current Cell Value:" + cell);
                    System.out.println("Current Cell Type" + cell.getCellType());
                    int currentCellType = cell.getCellType();
                    switch (currentCellType) {
                        case Cell.CELL_TYPE_STRING: {
                            data.get(currentRow).add(cell.toString());
                            break;
                        }
                        case Cell.CELL_TYPE_NUMERIC: {
                            data.get(currentRow).add(Double.parseDouble(cell.toString()));
                            break;
                        }
                        case Cell.CELL_TYPE_BOOLEAN: {
                            data.get(currentRow).add(Boolean.getBoolean(cell.toString()));
                            break;
                        }
                        case Cell.CELL_TYPE_BLANK: {
                            data.get(currentRow).add("");
                            break;
                        }
                        default: {
                            data.get(currentRow).add(cell.toString());
                        }
                    }
                }
                currentRow++;
            }
            return data;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
