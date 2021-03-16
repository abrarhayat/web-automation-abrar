package csvParser;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.Test;

import static automation.utils.CSVReader.getCSVParser;
import static automation.utils.CSVReader.parseCSV;

/**
 * @author abrar
 * since 9/9/20
 */

public class ReadCSVTest {
    private static String CSV_FILE_PATH = "src\\main\\resources\\data.csv";

    @Test
    public void testReadCSV() {
        parseCSV(CSV_FILE_PATH);
    }

    @Test()
    public void testGetCSVParser() {
        CSVParser parser = getCSVParser(CSV_FILE_PATH, false);
        for (CSVRecord record : parser) {
            String column1 = record.get(0);
            String column2 = record.get(1);
            String column3 = record.get(2);
            String column4 = record.get(3);
            System.out.println(column1 + '\n' + column2 + '\n' + column3 + '\n' + column4 + '\n');
        }
    }
}
