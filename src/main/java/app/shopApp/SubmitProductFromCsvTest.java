package app.shopApp;

import automation.poms.shopApp.AddProductPage;
import automation.utils.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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

public class SubmitProductFromCsvTest extends Initiation {
    static Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(SubmitProductFromCsvTest.class);

    public static void main(String[] args) {
        try {
            setUpWebDriver(browser);
            ShopAppUtils.login(driver);
            CSVParser parser = CSVReader.getCSVParser("data\\data.csv", true);
            AddProductPage addProductPage = new AddProductPage(Initiation.driver);
            assert parser != null;
            for (CSVRecord record : parser) {
                addProductPage.submitProduct(record.get("title"), record.get("image"), record.get("price"),
                        record.get("description"));
                WebActionUtils.waitForVisibility(5);
                assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
                log.info("Submitted the products successfully!");
            }
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
