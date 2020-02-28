package register.pages;

import base.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import utils.logs.LogForTest;

import java.io.File;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//a[@class='interstial-close']")
    private WebElement CLOSE_CROSS_BUTTON;
    @FindBy(xpath = "//div[contains(@class,'prof_add_avatar')]")
    private WebElement ADD_PHOTO_BUTTON;
    @FindBy(xpath = "//a[contains(@class,'add-ava-go-btn')]")
    private WebElement ADD_AVA_GO_BUTTON;

    private File file = new File("src/main/resources/userPhotos/3.jpg"); //Available photos: "1.jpg" and "2.jpg" too

    public ProfilePage(EventFiringWebDriver driver) {
        super(driver);
    }

    public void closeAdvPopUp() {
        LogForTest.info("Closing adv pop up");
        try {
            CLOSE_CROSS_BUTTON.click();
        } catch (Exception e) {
            Assert.fail("Can't close pop up\n" + e.getMessage());
        }
    }

    private void clickAddPhotoButton() {
        try {
            ADD_PHOTO_BUTTON.click();
        } catch (Exception e) {
            Assert.fail("Can't click add photo button\n" + e.getMessage());
        }
    }

    public void addPhotoFromPC() {
        LogForTest.info("Adding photo from PC");
        clickAddPhotoButton();
        driver.findElement(By.xpath("//input[@id='photo']")).sendKeys(file.getAbsolutePath());
    }

    public boolean isPhotoUploaded() {
        LogForTest.info("Checking if photo uploaded");
        waitForElement(ADD_AVA_GO_BUTTON, "Button is present if photo uploaded");
        return ADD_AVA_GO_BUTTON.isDisplayed();
    }

    public void clickGoButton() {
        LogForTest.info("Click 'Go' button");
        try {
            ADD_AVA_GO_BUTTON.click();
        } catch (Exception e) {
            Assert.fail("Can't click go button\n" + e.getMessage());
        }
    }
}
