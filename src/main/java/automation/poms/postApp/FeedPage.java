package automation.poms.postApp;

import automation.poms.AbstractPOM;
import automation.utils.WebActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author abrar
 * since 1/14/21
 */

public class FeedPage extends AbstractPOM {

    public FeedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button[class='button button--accent button--raised']")
    private WebElement newPostButton;

    @FindBy(className = "button--danger")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "//article[1]/div[@class='post__actions' and 1]/button[" +
            "@class='button button--undefined button--flat' and 1]")
    private WebElement updateButton;

    public void goToFeed() {
        driver.get("http://localhost:3000");
        try {
            WebActionUtils.waitForVisibility(2);
            driver.findElement(By.cssSelector("button[class='button button--undefined button--raised']")).click();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            //exception.printStackTrace();
        }
    }

    public void createNewPost(String title, String imageFileName, String content) {
        newPostButton.click();
        PostCreateEditModal modal = new PostCreateEditModal(this.driver);
        modal.submitPost(title, imageFileName, content);
    }

    public void updatePost(String title, String imageFileName, String content) {
        updateButton.click();
        PostCreateEditModal modal = new PostCreateEditModal(this.driver);
        modal.submitPost(title, imageFileName, content);
    }

    public void deleteTwoPosts() {
        deleteButtons.get(0).click();
        WebActionUtils.waitForVisibility(2);
        deleteButtons.get(0).click();
        WebActionUtils.waitForVisibility(2);
        goToFeed();
    }
}
