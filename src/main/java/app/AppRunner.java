package app;

import automation.poms.shopApp.LoginPage;
import automation.utils.Browser;
import automation.utils.Initiation;
import automation.utils.LoginUtils;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppRunner extends Initiation {
    static Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(AppRunner.class);
    public static void main(String[] args) {
        setUpWebDriver(browser);
        LoginPage loginPage = new LoginPage(Initiation.driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails();
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));
    }
}
