package automation.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author abrar
 * since 9/9/20
 */

public abstract class AbstractPOM {
    protected WebDriver driver;
    protected final String CURRENT_CONTEXT = "http://localhost:3000";

    public AbstractPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCurrentContext() {
        return CURRENT_CONTEXT;
    }
}
