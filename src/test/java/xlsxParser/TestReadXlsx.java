package xlsxParser;

import automation.utils.SystemUtils;
import automation.utils.XlsxReader;
import org.testng.annotations.Test;

/**
 * @author abrar
 * since 10/10/20
 */

public class TestReadXlsx {
    static String FILE_LOCATION = String.join(SystemUtils.getFileSeparator(),
            "src", "main", "resources", "data.xlsx");

    @Test
    public void test() {
        System.out.println(XlsxReader.getDataArrayFromXlsx(FILE_LOCATION));
    }
}
