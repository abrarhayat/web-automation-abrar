package automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author abrar
 * since 9/9/20
 */

public abstract class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected static WebDriver driver;


    protected static void setUpWebDriver(Browser browser) {
        switch (browser) {
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
        SystemUtils.setupUpWebDriverPurgeHook(driver);
    }

    private static void setUpChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        log.info("Created an Instance of Chrome Driver.");
    }

    private static void setUpGeckoDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        log.info("Created an Instance of Firefox Driver.");
    }

    private static void setUpPhantomJSDriver() {
        WebDriverManager.phantomjs().setup();
        driver = new PhantomJSDriver();
    }

    public static String getOperatingSystem() {
        return System.getProperty("os.name").toLowerCase().split(" ")[0];
    }
}
