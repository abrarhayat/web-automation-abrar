package automation.utils;

import org.openqa.selenium.WebDriver;

public class SystemUtils {
    public static void setupUpWebDriverPurgeHook(WebDriver driver) {
        Runtime.getRuntime().addShutdownHook(new Thread(driver::close));
    }
}
