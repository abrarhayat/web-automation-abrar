package automation.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.joda.time.format.DateTimeFormat.forPattern;

/**
 * @author abrar
 * since 9/9/20
 */

public abstract class WebActionUtils {

    final static Logger log = LoggerFactory.getLogger(WebActionUtils.class);

    public static WebElement getElementById(WebDriver driver, String field) {
        return driver.findElement(By.id(field));
    }

    public static WebElement getElementByClassName(WebDriver driver, String field) {
        return driver.findElement(By.className(field));
    }

    public static void waitForVisibility(int seconds) {
        int milliSeconds = seconds * 1000;
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void waitForVisibility() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void switchToIframe(WebDriver driver) {
        WebElement modal = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(modal);
    }

    public static void selectFirstFromDropdown(String dropdownSelectId, WebDriver driver) {
        Select options = new Select(driver.findElement(By.id(dropdownSelectId)));
        List<WebElement> selections = options.getOptions();
        try {
            options.selectByVisibleText(selections.get(1).getText());
        } catch (IndexOutOfBoundsException ex) {
            waitForVisibility(2);
            options.selectByVisibleText(selections.get(0).getText());
        }
    }

    public static void switchToMainFrame(WebDriver driver) {
        driver.switchTo().parentFrame();
    }

    public static void switchToActiveFrame(WebDriver driver) {
        driver.switchTo().activeElement();
        waitForVisibility();
    }

    public static void selectAllCheckBoxes(WebDriver driver) {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type = 'checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                try {
                    checkbox.click();
                } catch (Exception ex) {
                    log.error("\n" + (ex.toString()));
                }
            }
        }
    }

    public static String getTextBetween(String textToSplit, String regex1, String regex2) {
        String[] filteredText1 = textToSplit.split(regex1);
        String[] filteredText2 = filteredText1[1].split(regex2);
        return filteredText2[0];
    }

    public static JavascriptExecutor getJSExecutor(WebDriver driver) {
        return (JavascriptExecutor) driver;
    }

    public static String generateRandomNumberWithDigits(int numOfDigits) {
        Random random = new Random();
        if (numOfDigits >= 1) {
            double mult = Math.pow(10, numOfDigits);
            int result = random.nextInt((int) mult);
            while (result < mult / 10) {
                result = random.nextInt((int) mult);
            }
            log.info("Generated Random Number:" + result);
            return Integer.toString(result);
        }
        log.info("Random Number could not be generated!");
        return null;
    }

    public static List<String> getAllTokensMatching(String regex, String text) {
        List<String> allTokens = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            allTokens.add(matcher.group());
        }
        return allTokens;
    }

    public static String getDateTime() {
        DateTimeFormatter dateTimeFormatter = forPattern("MM/dd/YYYY hh:mm:ss a z");
        DateTime localDateTime = DateTime.now();
        return dateTimeFormatter.print(localDateTime);
    }
}
