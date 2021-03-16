package automation.poms.shopApp;

import automation.poms.AbstractPOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import static automation.utils.WebActionUtils.waitForVisibility;

/**
 * @author abrar
 * since 9/12/20
 */

public class UpdateProductPage extends AbstractPOM {
    public UpdateProductPage(WebDriver driver) {
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
    WebElement updateProductButton;

    public void updateProduct(String updatedTitle, String updatedPrice,
                              String updatedImageLocation, String updatedDescription) {
        File updatedImage = new File(updatedImageLocation);
        waitForVisibility(2);
        String previousTitle = this.title.getAttribute("value");
        String previousDesc = this.description.getText();
        this.title.clear();
        this.title.sendKeys(previousTitle + updatedTitle);
        this.price.clear();
        this.price.sendKeys(updatedPrice);
        this.image.sendKeys(updatedImage.getAbsolutePath());
        this.description.clear();
        this.description.sendKeys(previousDesc + updatedDescription);
        this.updateProductButton.click();
    }
}
