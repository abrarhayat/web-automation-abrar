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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author abrar
 * since 1/14/21
 */
public class CreateNewPostTest extends Initiation {

    Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(CreateNewPostTest.class);
    FeedPage feedPage;

    @BeforeTest
    public void webDriverInit() {
        setUpWebDriver(browser);
        LoginPage loginPage = new LoginPage(driver);
        CSVRecord loginDetails = LoginUtils.getLoginDetails();
        loginPage.login(loginDetails.get("email"), loginDetails.get("password"));
    }

    @Test
    public void createPost() {
        List<String> images = new ArrayList<>(Arrays.asList(
                "src/main/resources/images/abandoned.jpg", "src/main/resources/images/the-forest.jpg",
                "src/main/resources/images/the-light2.jpg"));
        feedPage = new FeedPage(driver);
        int noOfPostsToCreate = 4;
        int imageCount = 0;
        feedPage.goToFeed();
        for (int count = 0; count < noOfPostsToCreate; count++) {
            if (imageCount > 2) {
                imageCount = 0;
            }
            feedPage.createNewPost("Test Title" + count, images.get(imageCount),
                    "Test Content" + count);
            WebActionUtils.waitForVisibility(7);
            assertThat(driver.getCurrentUrl().equals("http://localhost:3000/")).isTrue();
            imageCount++;
        }

    }

    @AfterTest
    public void kill() {
        driver.close();
    }
}
