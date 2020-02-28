package base.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BasePage {

    protected EventFiringWebDriver driver;

    public BasePage(EventFiringWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElement(WebElement element, String elementDescription) {
        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            Assert.fail("Timeout Exception: Can't wait for " + elementDescription + "\n" + e.getMessage());
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    //generate user test email: CurrentDateAndTime "yyyyMMddHHmm" + "@testmail.com"
    public String generateEmail() {
        String fn = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return fn + "@testmail.com";
    }
}
