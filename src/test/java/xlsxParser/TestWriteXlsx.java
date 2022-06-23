package xlsxParser;

import automation.utils.SystemUtils;
import automation.utils.XlsxWriter;
import org.testng.annotations.Test;

/**
 * @author abrar
 * since 10/10/20
 */

public class TestWriteXlsx {
    static String FILE_NAME = SystemUtils.getPath("src", "main", "resources", "written_data.xlsx");

    @Test
    public void test() {
        XlsxWriter.writeToXlsx(FILE_NAME);
    }
}
