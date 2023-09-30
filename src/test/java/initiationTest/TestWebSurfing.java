package initiationTest;

import automation.utils.Browser;
import automation.utils.AbstractTest;
import automation.utils.WebActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author abrar
 * since 9/9/20
 */

public class TestWebSurfing extends AbstractTest {

    @Test
    public void test(){
        setUpWebDriver(Browser.SAFARI);
        for(int i = 0; i < 1000; i++) {
            driver.get("https://abrarhayat.vercel.app");
            List<WebElement> elements = driver.findElements(By.cssSelector(".nav-link-custom"));
            elements.forEach(element -> {
                element.click();
                WebActionUtils.waitForVisibility(2);
            });
        }
        driver.close();
    }

//    @AfterTest
//    public void after() {
//        WebActionUtils.waitForVisibility();
//    }
}
