package app.shopApp;

import automation.poms.shopApp.AdminProductPage;
import automation.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static automation.utils.WebActionUtils.waitForVisibility;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author abrar
 * since 7/15/22
 */

public class DeleteProductsTest extends AbstractTest {
    static Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(DeleteProductsTest.class);

    public static void main(String[] args) {
        try {
            setUpWebDriver(browser);
            ShopAppUtils.login(driver);
            int noOfProductsToDelete = 4;
            for (int deletionCount = 0; deletionCount < noOfProductsToDelete; deletionCount++) {
                AdminProductPage adminProductPage = new AdminProductPage(AbstractTest.driver);
                adminProductPage.deleteFirstProduct();
                assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
                log.info("Deleted the products successfully!");
            }
            waitForVisibility();
            ShopAppUtils.logout(driver);
        } catch (Exception ex) {
            log.error(ex.toString());
        }
    }
}
