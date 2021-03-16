package webAppAssertion.shopApp;

import automation.poms.shopApp.AdminProductPage;
import automation.poms.shopApp.LoginPage;
import automation.utils.Browser;
import automation.utils.Initiation;
import automation.utils.LoginUtils;
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

public class DeleteProductsTest extends Initiation {
    Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(DeleteProductsTest.class);

    @BeforeTest
    public void webDriverInit() {
        setUpWebDriver(browser);
        LoginPage loginPage = new LoginPage(Initiation.driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails();
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));
    }

    @Test
    public void deleteProducts() {
        int noOfProductsToDelete = 4;
        for (int deletionCount = 0; deletionCount < noOfProductsToDelete; deletionCount++) {
            AdminProductPage adminProductPage = new AdminProductPage(Initiation.driver);
            adminProductPage.deleteFirstProduct();
            assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
        }
    }

    @AfterTest
    public void kill() {
        LoginPage loginPage = new LoginPage(Initiation.driver);
        loginPage.logout();
        driver.close();
    }
}
