package app.shopApp;

import app.AppRunner;
import automation.poms.shopApp.AddProductPage;
import automation.poms.shopApp.LoginPage;
import automation.utils.*;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static automation.utils.WebActionUtils.waitForVisibility;

/**
 * @author abrar
 * since 7/15/22
 */

public class SubmitProductFromJsonTest extends Initiation {
    static Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(AppRunner.class);

    public static void main(String[] args) {
        setUpWebDriver(browser);

        LoginPage loginPage = new LoginPage(Initiation.driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails("data\\loginDetails.csv");
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));

        AddProductPage addProductPage = new AddProductPage(Initiation.driver);

        try {
            ParseBooksFromJSON.getBooks("data\\data.json").forEach(book -> {
                addProductPage.submitProduct(book.getTitle(),
                        book.getImageLocation(), book.getPrice(), book.getDescription());
                waitForVisibility(5);
            });
            if (!driver.getCurrentUrl().contains("admin/products"))
                throw new Exception("Failed to submit product!");
            log.info("Submitted the products successfully!");
        } catch (Exception ex) {
            log.error(ex.toString());
            try {
                log.error(driver.findElement(By.cssSelector("div[class='user-message user-message--error']")).getText());
            } catch (NoSuchElementException ex1) {
                log.error(ex1.toString());
            }
        }

        waitForVisibility();
        loginPage = new LoginPage(Initiation.driver);
        loginPage.logout();
        driver.close();
    }
}
