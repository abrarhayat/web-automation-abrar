package webAppAssertion.postApp;

import automation.poms.postApp.FeedPage;
import automation.poms.postApp.LoginPage;
import automation.utils.Browser;
import automation.utils.Initiation;
import automation.utils.LoginUtils;
import automation.utils.WebActionUtils;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author abrar
 * since 1/16/21
 */
public class DeletePostTest extends Initiation {
    Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(DeletePostTest.class);
    FeedPage feedPage;

    @BeforeTest
    public void webDriverInit() {
        setUpWebDriver(browser);
        LoginPage loginPage = new LoginPage(driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails();
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));
    }

    @Test
    public void deletePost() {
        feedPage = new FeedPage(driver);
        int noOfPostsDelete = 2;
        feedPage.goToFeed();
        for (int deletionCountRound = 0; deletionCountRound < noOfPostsDelete; deletionCountRound += 2) {
            try {
                feedPage.deleteTwoPosts();
                assertThat(driver.getCurrentUrl().equals("http://localhost:3000/")).isTrue();
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("No more posts to delete!");
                break;
            }
        }
    }

    @AfterTest
    public void kill() {
        driver.close();
    }
}
