package webAppAssertion.shopApp;

import automation.poms.shopApp.AdminProductPage;
import automation.poms.shopApp.UpdateProductPage;
import automation.utils.Browser;
import automation.utils.AbstractTest;
import automation.utils.ShopAppUtils;
import automation.utils.SystemUtils;
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

public class UpdateProductsTest extends AbstractTest {
    Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(UpdateProductsTest.class);

    @BeforeTest
    public void webDriverInit() {
        setUpWebDriver(browser);
        ShopAppUtils.login(driver);
    }

    @Test
    public void updateProducts() {
        int noOfProductsToUpdate = 1;
        for (int updateCount = 0; updateCount < noOfProductsToUpdate; updateCount++) {
            AdminProductPage adminProductPage = new AdminProductPage(AbstractTest.driver);
            adminProductPage.clickEditButton(updateCount);
            UpdateProductPage updateProductPage = new UpdateProductPage(AbstractTest.driver);
            updateProductPage.updateProduct(" Updated",
                    "20", SystemUtils.getPath("src", "main", "resources", "images", "abandoned.jpg"),
                    " Updated");
            assertThat(driver.getCurrentUrl().contains("admin/products")).isTrue();
        }
    }

    @AfterTest
    public void kill() {
        ShopAppUtils.logout(driver);
    }
}
