package webAppAssertion.shopApp;

import automation.poms.shopApp.AdminProductPage;
import automation.poms.shopApp.LoginPage;
import automation.poms.shopApp.UpdateProductPage;
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
 * since 9/12/20
 */

public class UpdateProductsTest extends Initiation {
    Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(UpdateProductsTest.class);

    @BeforeTest
    public void webDriverInit() {
        setUpWebDriver(browser);
        LoginPage loginPage = new LoginPage(Initiation.driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails();
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));
    }

    @Test
    public void updateProducts() {
        int noOfProductsToUpdate = 1;
        for (int updateCount = 0; updateCount < noOfProductsToUpdate; updateCount++) {
            AdminProductPage adminProductPage = new AdminProductPage(Initiation.driver);
            adminProductPage.clickEditButton(updateCount);
            UpdateProductPage updateProductPage = new UpdateProductPage(Initiation.driver);
            updateProductPage.updateProduct(" Updated",
                    "20", "src/main/resources/images/abandoned.jpg",
                    " Updated");
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
