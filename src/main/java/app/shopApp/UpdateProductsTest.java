package app.shopApp;

import automation.poms.shopApp.AdminProductPage;
import automation.poms.shopApp.LoginPage;
import automation.poms.shopApp.UpdateProductPage;
import automation.utils.Browser;
import automation.utils.Initiation;
import automation.utils.LoginUtils;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author abrar
 * since 7/15/22
 */

public class UpdateProductsTest extends Initiation {
    static Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(UpdateProductsTest.class);

    public static void main(String[] args) {
        setUpWebDriver(browser);
        LoginPage loginPage = new LoginPage(Initiation.driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails("data\\loginDetails.csv");
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));

        try {
            int noOfProductsToUpdate = 1;
            for (int updateCount = 0; updateCount < noOfProductsToUpdate; updateCount++) {
                AdminProductPage adminProductPage = new AdminProductPage(Initiation.driver);
                adminProductPage.clickEditButton(updateCount);
                UpdateProductPage updateProductPage = new UpdateProductPage(Initiation.driver);
                updateProductPage.updateProduct(" Updated",
                        "20", "data\\abandoned.jpg",
                        " Updated");
                log.info("Updated the products successfully!");
                if (!driver.getCurrentUrl().contains("admin/products"))
                    throw new Exception("Could not update the Products!");
            }
        } catch (Exception ex) {
            log.error(ex.toString());
        }

        loginPage = new LoginPage(Initiation.driver);
        loginPage.logout();
        driver.close();
    }
}
