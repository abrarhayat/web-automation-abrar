package automation.poms.shopApp;

import automation.poms.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

/**
 * @author abrar
 * since 9/9/20
 */

public class AddProductPage extends AbstractPOM {

    public AddProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "title")
    WebElement title;

    @FindBy(id = "image")
    WebElement image;

    @FindBy(id = "price")
    WebElement price;

    @FindBy(id = "description")
    WebElement description;

    @FindBy(css = "button[class='btn btn-submit']")
    WebElement addProductButton;

    public void submitProduct(String title, String imageLocation, String price, String description) {
        File imageFile = new File(imageLocation);
        driver.get("http://localhost:3000/admin/add-product");
        this.title.sendKeys(title);
        this.image.sendKeys(imageFile.getAbsolutePath());
        this.price.sendKeys(price);
        this.description.sendKeys(description);
        this.addProductButton.click();
    }
}
