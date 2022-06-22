package app.shopApp;

import automation.poms.shopApp.AdminProductPage;
import automation.poms.shopApp.UpdateProductPage;
import automation.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author abrar
 * since 7/15/22
 */

public class UpdateProductsTest extends Initiation {
    static Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(UpdateProductsTest.class);

    public static void main(String[] args) {
        try {
            setUpWebDriver(browser);
            ShopAppUtils.login(driver);
            int noOfProductsToUpdate = 1;
            for (int updateCount = 0; updateCount < noOfProductsToUpdate; updateCount++) {
                AdminProductPage adminProductPage = new AdminProductPage(Initiation.driver);
                adminProductPage.clickEditButton(updateCount);
                UpdateProductPage updateProductPage = new UpdateProductPage(Initiation.driver);
                updateProductPage.updateProduct(" Updated",
                        "20", String.join(SystemUtils.getFileSeparator(), "data", "abandoned.jpg"),
                        " Updated");
                assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
                log.info("Updated the products successfully!");
                ShopAppUtils.logout(driver);
            }
        } catch (Exception ex) {
            log.error(ex.toString());
        }
    }
}
