package initiationTest;

import automation.utils.Browser;
import automation.utils.Initiation;
import automation.utils.WebActionUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

/**
 * @author abrar
 * since 9/9/20
 */

public class TestWebSurfing extends Initiation {

    @Test
    public void test(){
        setUpWebDriver(Browser.CHROME);
        driver.get("https://www.google.com");
    }

    @AfterTest
    public void after() {
        WebActionUtils.waitForVisibility();
        driver.quit();
    }
}
