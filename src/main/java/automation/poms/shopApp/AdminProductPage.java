package automation.poms.shopApp;

import automation.poms.AbstractPOM;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author abrar
 * since 9/12/20
 */

public class AdminProductPage extends AbstractPOM {
    public AdminProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button[class='btn danger']")
    List<WebElement> productDeleteButtons;

    @FindBy(css = "a[class='btn btn-submit']")
    List<WebElement> updateButtons;

    int numProducts;

    public void deleteFirstProduct() {
        driver.get(CURRENT_CONTEXT + "/admin/products");
        int noOfProductsLeft = productDeleteButtons.size();
        if (noOfProductsLeft > 0) {
            try {
                productDeleteButtons.get(0).click();
            } catch (ElementClickInterceptedException exception) {
                deleteFirstProduct();
            }
        }
    }

    public void clickEditButton(int productIndex) {
        driver.get(CURRENT_CONTEXT + "/admin/products");
        numProducts = updateButtons.size();
        if (productIndex < numProducts) {
            updateButtons.get(productIndex).click();
        }
    }
}
