package automation.poms.shopApp;

import automation.poms.AbstractPOM;
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

    @FindBy(css = "button[class='btn']")
    WebElement loginButton;

    @FindBy(id = "logout")
    WebElement logoutButton;

    public void login(String emailAddress, String passwordText) {
        driver.get("http://localhost:3000/login");
        email.sendKeys(emailAddress);
        password.sendKeys(passwordText);
        loginButton.click();
    }

    public void logout() {
        logoutButton.click();
    }
}
