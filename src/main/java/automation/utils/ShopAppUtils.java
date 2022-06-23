package automation.utils;

import automation.poms.shopApp.LoginPage;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.WebDriver;

public class ShopAppUtils {

    public static void login(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails(SystemUtils.getPath("data", "loginDetails.csv"));
        assert loginDetails != null;
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));
    }

    public static void logout(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logout();
    }
}
