package app.shopApp;

import app.AppRunner;
import automation.poms.shopApp.AddProductPage;
import automation.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static automation.utils.WebActionUtils.waitForVisibility;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author abrar
 * since 7/15/22
 */

public class SubmitProductFromJsonTest extends AbstractTest {
    static Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(AppRunner.class);

    public static void main(String[] args) {
        try {
            setUpWebDriver(browser);
            ShopAppUtils.login(driver);
            AddProductPage addProductPage = new AddProductPage(AbstractTest.driver);
            ParseBooksFromJSON.getBooks(SystemUtils.getPath("data", "data.json"))
                    .forEach(book -> {
                        addProductPage.submitProduct(book.getTitle(),
                                book.getImageLocation(), book.getPrice(), book.getDescription());
                        waitForVisibility(5);
                    });
            assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
            log.info("Submitted the products successfully!");
            waitForVisibility();
            ShopAppUtils.logout(driver);
        } catch (Exception ex) {
            log.error(ex.toString());
            try {
                log.error(driver.findElement(By.cssSelector("div[class='user-message user-message--error']")).getText());
            } catch (NoSuchElementException ex1) {
                log.error(ex1.toString());
            }
        }
    }
}
