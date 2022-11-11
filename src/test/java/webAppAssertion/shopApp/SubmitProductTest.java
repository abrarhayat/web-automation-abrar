package webAppAssertion.shopApp;

import automation.poms.shopApp.AddProductPage;
import automation.utils.*;
import org.apache.commons.csv.CSVParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author abrar
 * since 9/9/20
 */

public class SubmitProductTest extends AbstractTest {
    Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(SubmitProductTest.class);

    @BeforeTest
    public void webDriverInit() {
        setUpWebDriver(browser);
        ShopAppUtils.login(driver);
    }

    @Test
    public void testSubmitFromJSON() throws IOException, ParseException {
        AddProductPage addProductPage = new AddProductPage(AbstractTest.driver);
        ParseBooksFromJSON.getBooks(SystemUtils.getPath("src", "main", "resources", "data.json"))
                .forEach(book -> {
                    addProductPage.submitProduct(book.getTitle(),
                            book.getImageLocation(), book.getPrice(), book.getDescription());
                    WebActionUtils.waitForVisibility(5);
                    assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
                });
    }

    @Test
    public void testSubmitFromCSV() throws IOException {
            CSVParser parser = CSVReader.getCSVParser(SystemUtils.getPath("src", "main", "resources", "data.csv"),
                    true);
            AddProductPage addProductPage = new AddProductPage(AbstractTest.driver);
            parser.forEach(record -> {
                addProductPage.submitProduct(record.get("title"), record.get("image"),
                        record.get("price"), record.get("description"));
                WebActionUtils.waitForVisibility(5);
                assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
            });
    }

    @AfterTest
    public void kill() {
        ShopAppUtils.logout(driver);
    }
}
