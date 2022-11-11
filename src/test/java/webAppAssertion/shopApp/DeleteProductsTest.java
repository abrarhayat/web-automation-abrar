package webAppAssertion.shopApp;

import automation.poms.shopApp.AdminProductPage;
import automation.utils.Browser;
import automation.utils.AbstractTest;
import automation.utils.ShopAppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertEquals;


/**
 * @author abrar
 * since 9/9/20
 */

public class DeleteProductsTest extends AbstractTest {
    Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(DeleteProductsTest.class);

    @BeforeTest
    public void webDriverInit() {
        setUpWebDriver(browser);
        ShopAppUtils.login(driver);
    }

    @Test
    public void deleteProducts() {
        int noOfProductsToDelete = 4;
        for (int deletionCount = 0; deletionCount < noOfProductsToDelete; deletionCount++) {
            AdminProductPage adminProductPage = new AdminProductPage(AbstractTest.driver);
            adminProductPage.deleteFirstProduct();
            assertEquals(adminProductPage.getCurrentContext().concat("/admin/products"), driver.getCurrentUrl());
        }
    }

    @AfterTest
    public void kill() {
        ShopAppUtils.logout(driver);
    }
}
