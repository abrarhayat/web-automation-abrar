package app.shopApp;

import automation.poms.shopApp.AdminProductPage;
import automation.poms.shopApp.LoginPage;
import automation.utils.Browser;
import automation.utils.Initiation;
import automation.utils.LoginUtils;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static automation.utils.WebActionUtils.waitForVisibility;

/**
 * @author abrar
 * since 7/15/22
 */

public class DeleteProductsTest extends Initiation {
    static Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(DeleteProductsTest.class);

    public static void main(String[] args) {
        setUpWebDriver(browser);
        LoginPage loginPage = new LoginPage(Initiation.driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails("data\\loginDetails.csv");
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));

        int noOfProductsToDelete = 4;
        for (int deletionCount = 0; deletionCount < noOfProductsToDelete; deletionCount++) {
            AdminProductPage adminProductPage = new AdminProductPage(Initiation.driver);
            adminProductPage.deleteFirstProduct();
            try {
                if (!driver.getCurrentUrl().contains("admin/products")) throw new Exception("Error Deleting Product!");
            } catch (Exception ex) {
                log.error(ex.toString());
            }
            log.info("Deleted the products successfully!");
        }

        waitForVisibility();
        loginPage = new LoginPage(Initiation.driver);
        loginPage.logout();
        driver.close();
    }
}
