package automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author abrar
 * since 10/10/20
 */

public class XlsxWriter {
    public static void writeToXlsx(String fileName) {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Persons");
        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);


        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Name");

        headerCell = header.createCell(1);
        headerCell.setCellValue("Age");

        Row row1 = sheet.createRow(1);
        Cell cell10 = row1.createCell(0);
        Cell cell11 = row1.createCell(1);

        cell10.setCellValue("John Smith");
        cell11.setCellValue(25);

        Row row2 = sheet.createRow(2);
        Cell cell20 = row2.createCell(0);
        Cell cell21 = row2.createCell(1);

        cell20.setCellValue("Tim Burton");
        cell21.setCellValue(20);

        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
