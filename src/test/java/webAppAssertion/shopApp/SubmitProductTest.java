package webAppAssertion.shopApp;

import automation.poms.shopApp.AddProductPage;
import automation.poms.shopApp.LoginPage;
import automation.utils.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author abrar
 * since 9/9/20
 */

public class SubmitProductTest extends Initiation {
    Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(SubmitProductTest.class);

    @BeforeTest
    public void webDriverInit() {
        setUpWebDriver(browser);
        LoginPage loginPage = new LoginPage(Initiation.driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails();
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));
    }

    @Test
    public void testSubmitFromJSON() {
        AddProductPage addProductPage = new AddProductPage(Initiation.driver);
        ParseBooksFromJSON.getBooks("src/main/resources/data.json").forEach(book -> {
            addProductPage.submitProduct(book.getTitle(),
                    book.getImageLocation(), book.getPrice(), book.getDescription());
            WebActionUtils.waitForVisibility(5);
            assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
        });
    }

    @Test
    public void testSubmitFromCSV() {
        try {
            CSVParser parser = CSVReader.getCSVParser("src/main/resources/data.csv", true);
            AddProductPage addProductPage = new AddProductPage(Initiation.driver);
            parser.forEach(record -> {
                addProductPage.submitProduct(record.get("title"), record.get("image"),
                        record.get("price"), record.get("description"));
                WebActionUtils.waitForVisibility(5);
                assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
            });
        } catch (NullPointerException ex) {
            log.error(ex.toString());
        }
    }

    @AfterTest
    public void kill() {
        LoginPage loginPage = new LoginPage(Initiation.driver);
        loginPage.logout();
        driver.close();
    }
}
