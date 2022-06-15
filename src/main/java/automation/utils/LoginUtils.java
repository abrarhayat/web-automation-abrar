package automation.utils;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoginUtils {
    private static final Logger log = LoggerFactory.getLogger("GetLoginDetails");

    public static CSVRecord getLoginDetails() {
        try {
            CSVParser parser = CSVReader.getCSVParser("src\\main\\resources\\loginDetails.csv", true);
            assert parser != null;
            return parser.getRecords().get(0);
        } catch (IOException ioException) {
            log.error(ioException.toString());
            return null;
        }
    }

    public static CSVRecord getLoginDetails(String loginDetailsDir) {
        try {
            CSVParser parser = CSVReader.getCSVParser(loginDetailsDir, true);
            assert parser != null;
            return parser.getRecords().get(0);
        } catch (IOException ioException) {
            log.error(ioException.toString());
            return null;
        }
    }
}
