package webAppAssertion.shopApp;

import automation.poms.shopApp.AddProductPage;
import automation.utils.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Abrar Hayat
 * @since November 2022
 **/
public class SubmitProductValidationTest extends AbstractTest {
    Browser browser = Browser.CHROME;
    final static Logger log = LoggerFactory.getLogger(SubmitProductValidationTest.class);

    @BeforeTest
    public void webDriverInit() {
        setUpWebDriver(browser);
        ShopAppUtils.login(driver);
    }

    @Test
    public void testImageSizeValidation() throws IOException {
        submitMultipleProductsAndGenerateValidation("dataWithLargeImage.csv",
                "Maximum Image File Size Limit of 10.4 MB Exceeded!");
    }

    @Test
    public void testTitleMinValidation() throws IOException {
        submitProductAndGenerateValidation("dataWithSmallTitle.csv",
                "Title length must be in between 5 and 50!");
    }

    @Test
    public void testTitleMaxValidation() throws IOException {
        submitProductAndGenerateValidation("dataWithLargeTitle.csv",
                "Title length must be in between 5 and 50!");
    }

    @Test
    public void testDescriptionMinValidation() throws IOException {
        submitProductAndGenerateValidation("dataWithSmallDescription.csv",
                "Description length must be in between 5 and 400!");
    }

    @Test
    public void testDescriptionMaxValidation() throws IOException {
        submitProductAndGenerateValidation("dataWithLargeDescription.csv",
                "Description length must be in between 5 and 400!");
    }

    private void submitProductAndGenerateValidation(String fileName, String expectedValidationMessage) throws IOException {
        AddProductPage addProductPage = new AddProductPage(AbstractTest.driver);
        CSVRecord record = getFirstRecordFrom(fileName);
        assert record != null;
        WebActionUtils.waitForVisibility(5);
        addProductPage.submitProduct(record.get("title"), record.get("image"),
                record.get("price"), record.get("description"));
        WebActionUtils.waitForVisibility(5);
        assertThat(getValidationMessage(addProductPage)
                .equals(expectedValidationMessage)).isTrue();
    }

    private void submitMultipleProductsAndGenerateValidation(String fileName, String expectedValidationMessage) throws IOException {
        CSVParser parser = CSVReader.getCSVParser(SystemUtils.getPath("src", "main", "resources",
                fileName), true);
        AddProductPage addProductPage = new AddProductPage(AbstractTest.driver);
        parser.forEach(record -> {
            addProductPage.submitProduct(record.get("title"), record.get("image"),
                    record.get("price"), record.get("description"));
            WebActionUtils.waitForVisibility(5);
            assertThat(getValidationMessage(addProductPage)
                    .equals(expectedValidationMessage)).isTrue();

        });
    }

    private CSVRecord getFirstRecordFrom(String fileName) throws IOException {
        CSVRecord record;
        try {
            CSVParser parser = CSVReader.getCSVParser(SystemUtils.getPath("src", "main", "resources", fileName),
                    true);
            record = parser.getRecords().get(0); //some tests only need to one record
            return record;
        } catch (IOException ex) {
            log.error(ex.toString());
            throw ex;
        }
    }

    private String getValidationMessage(AddProductPage addProductPage) {
        String validationMessage = null;
        try {
            validationMessage = addProductPage.getOccurredValidation();
            log.info(validationMessage);
            return validationMessage;
        } catch (NoSuchElementException noSuchElementException) {
            log.error("Expected Validation did not work!");
        }
        assertThat(validationMessage != null).isTrue();
        return validationMessage;
    }

    @AfterTest
    public void kill() {
        ShopAppUtils.logout(driver);
    }
}
