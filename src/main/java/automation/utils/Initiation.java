package automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author abrar
 * since 9/9/20
 */

public abstract class Initiation {

    private static final Logger log = LoggerFactory.getLogger(Initiation.class);
    protected static WebDriver driver;


    protected static void setUpWebDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                setUpChromeDriver();
                break;

            case FIREFOX:
                setUpGeckoDriver();
                break;

            case PHANTOMJS:
                setUpPhantomJSDriver();
                break;

            default:
                setUpChromeDriver();
                break;

        }
    }

    private static void setUpChromeDriver() {
        String operatingSystem = getOperatingSystem();
        System.out.println(operatingSystem);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");

        switch (operatingSystem) {
            case "windows":
                System.setProperty(Constants.CHROME_DRIVER_SYSTEM_PROPERTY, Constants.CHROME_DRIVER_WINDOWS_DIR);
                break;
            case "linux":
                System.setProperty(Constants.CHROME_DRIVER_SYSTEM_PROPERTY, Constants.CHROME_DRIVER_LINUX_DIR);
        }
        driver = new ChromeDriver(chromeOptions);
        log.info("Created an Instance of Chrome Driver.");
    }

    private static void setUpGeckoDriver() {
        String operatingSystem = getOperatingSystem();
        System.out.println(operatingSystem);

        switch (operatingSystem) {
            case "windows":
                System.setProperty(Constants.FIREFOX_DRIVER_SYSTEM_PROPERTY, Constants.FIREFOX_DRIVER_WINDOWS_DIR);
                break;
            case "linux":
                System.setProperty(Constants.FIREFOX_DRIVER_SYSTEM_PROPERTY, Constants.FIREFOX_DRIVER_LINUX_DIR);
        }
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        log.info("Created an Instance of Firefox Driver.");
    }

    private static void setUpPhantomJSDriver() {

    }

    public static String getOperatingSystem() {
        return System.getProperty("os.name").toLowerCase().split(" ")[0];
    }
}
