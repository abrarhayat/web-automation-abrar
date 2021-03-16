package automation.poms.postApp;

import automation.poms.AbstractPOM;
import automation.utils.WebActionUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author abrar
 * since 9/25/20
 */

public class LoginPage extends AbstractPOM {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(css = "button[type='submit']")
    WebElement loginButton;

    public void login(String emailAddress, String passwordText) {
        driver.get("http://localhost:3000");
        email.sendKeys(emailAddress);
        password.sendKeys(passwordText);
        loginButton.click();
        WebActionUtils.waitForVisibility(2);
    }
}
