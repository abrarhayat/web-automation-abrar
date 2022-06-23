package automation.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abrar
 * since 9/9/20
 */

public class CSVReader {

    public static List<String> parseCSV(String csvFilePath) {
        List<String> dataList = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath).toAbsolutePath());
            CSVParser csvParser = new CSVParser(reader, CSVFormat.RFC4180);
            for (CSVRecord csvRecord : csvParser) {
                for (int recordCount = 0; recordCount <= csvRecord.size() - 1; recordCount++) {
                    if (!(csvRecord.get(recordCount).isEmpty())) {
                        System.out.println(csvRecord.get(recordCount));
                        dataList.add(csvRecord.get(recordCount));
                    }

                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }

    public static CSVParser getCSVParser(String csvFilePath, boolean withHeader) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath).toAbsolutePath());
            return withHeader
                    ? new CSVParser(reader, CSVFormat.EXCEL.withHeader())
                    : new CSVParser(reader, CSVFormat.EXCEL);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

