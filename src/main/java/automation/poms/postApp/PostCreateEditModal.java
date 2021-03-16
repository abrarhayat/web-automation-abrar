package automation.poms.postApp;

import automation.poms.AbstractPOM;
import automation.utils.WebActionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

/**
 * @author abrar
 * since 1/14/21
 */
public class PostCreateEditModal extends AbstractPOM {

    public PostCreateEditModal(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "title")
    private WebElement title;

    @FindBy(id = "image")
    private WebElement image;

    @FindBy(id = "content")
    private WebElement content;

    @FindBy(css = "button[class='button button--undefined button--raised']")
    private WebElement acceptButton;

    public void submitPost(String title, String imageFileName, String content) {
        File imageFile = new File(imageFileName);
        this.title.sendKeys(title);
        this.image.sendKeys(imageFile.getAbsolutePath());
        this.content.sendKeys(content);
        this.acceptButton.click();
        WebActionUtils.waitForVisibility(2);
    }
}
