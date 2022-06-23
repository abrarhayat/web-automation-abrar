package automation.utils;

import org.openqa.selenium.WebDriver;

import java.nio.file.FileSystems;

public class SystemUtils {
    public static void setupUpWebDriverPurgeHook(WebDriver driver) {
        Runtime.getRuntime().addShutdownHook(new Thread(driver::close));
    }

    public static String getFileSeparator() {
        return FileSystems.getDefault().getSeparator();
    }

    public static String getPath(String... subPaths) {
        return String.join(FileSystems.getDefault().getSeparator(), subPaths);
    }
}
