package automation.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author abrar
 * since 9/9/20
 */

public class CSVWriter {
    private static final String CSV_FILE_PATH = String.join(SystemUtils.getFileSeparator(),
            "src", "main", "resources", "written_data.csv");

    public static void writeToCSV(String filePath, ArrayList<String> records) {
        try {
            String destinationDir = "new_test_dir" + new DateTime() +
                    filePath.substring(0, filePath.lastIndexOf(SystemUtils.getFileSeparator()));
            System.out.println("Result destination directory: " + destinationDir);
            File destination = new File(destinationDir);
            //making the destination dir
            destination.mkdirs();
            //making the csv file to fill
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath).toAbsolutePath());
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (int iteratorCount = 0; iteratorCount <= records.size() - 1; iteratorCount++) {
                csvPrinter.printRecord(records.get(iteratorCount));
            }
            csvPrinter.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeSingleLineToCSV(String filePath, String singleLine) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath).toAbsolutePath());

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            csvPrinter.printRecord(singleLine);
            csvPrinter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testCSVWriter() {
        ArrayList<String> test = new ArrayList<String>();
        test.add("Test0");
        test.add("Test1");
        writeToCSV(CSV_FILE_PATH, test);
    }

    @Test
    public void makeDirTest() {
        File f = new File("new_test_dir" + new DateTime());
        f.mkdir();
    }
}
