package register.pages;

import base.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import utils.logs.LogForTest;

import java.util.List;

public class LandingInterPage extends BasePage {

    @FindBy(xpath = "//div[@id='slide-01']//a[@class='land-btn land-btn-orange']")
    private WebElement GIRLS_BUTTON;
    @FindBy(xpath = "//div[@id='slide-02-f']//a[@class='land-btn land-btn-orange']")
    private WebElement DARK_BUTTON;
    @FindBy(xpath = "//div[@id='slide-03-f']//a[@class='land-btn land-btn-orange']")
    private WebElement DARK_HAIR_BUTTON;
    @FindBy(xpath = "//div[@id='slide-04-f']//a[@class='land-btn land-btn-orange']")
    private WebElement WITH_FORM_BUTTON;
    @FindBy(xpath = "//h3[text()='Для просмотра результатов зарегистрируйтесь на сайте']")
    private WebElement REGISTER_TITLE;
    @FindBy(xpath = "//input[@placeholder='Ваше имя']")
    private WebElement NAME_FIELD;
    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement EMAIL_FIELD;
    @FindBy(xpath = "//select[@name='gender']")
    private WebElement GENDER_DROP_DOWN;
    @FindBy(xpath = "//select[@name='gender']/option")
    private List<WebElement> GENDER_OPTIONS;
    @FindBy(xpath = "//select[@name='gender']/option")
    private WebElement GENDER_OPTION;
    @FindBy(xpath = "//select[@name='age']")
    private WebElement AGE_DROP_DOWN;
    @FindBy(xpath = "//select[@name='age']/option")
    private List<WebElement> AGE_OPTIONS;
    @FindBy(xpath = "//select[@name='age']/option")
    private WebElement AGE_OPTION;
    @FindBy(xpath = "//button[@class='land-btn-submit land-btn']")
    private WebElement REGISTER_BUTTON;

    public LandingInterPage(EventFiringWebDriver driver) { super(driver); }

    public void clickButtonWithTextGirls() {
        LogForTest.info("Click button with text 'Девушки'");
        waitForElement(GIRLS_BUTTON, "Button with text 'Девушки'");
        try {
            GIRLS_BUTTON.click();
        } catch (Exception e) {
            Assert.fail("Can't click on button\n" + e.getMessage());
        }
    }

    public void clickButtonWithTextDark() {
        LogForTest.info("Click button with text 'Темные'");
        waitForElement(DARK_BUTTON, "Button with text 'Темные'");
        try {
            DARK_BUTTON.click();
        } catch (Exception e) {
            Assert.fail("Can't click on button\n" + e.getMessage());
        }
    }

    public void clickButtonWithTextDarkHair() {
        LogForTest.info("Click button with text 'Темные'");
        waitForElement(DARK_HAIR_BUTTON, "Button with text 'Темные'");
        try {
            DARK_HAIR_BUTTON.click();
        } catch (Exception e) {
            Assert.fail("Can't click on button\n" + e.getMessage());
        }
    }

    public void clickButtonWithTextWithForm() {
        LogForTest.info("Click button with text 'С формами'");
        waitForElement(WITH_FORM_BUTTON, "Button with text 'С формами'");
        try {
            WITH_FORM_BUTTON.click();
        } catch (Exception e) {
            Assert.fail("Can't click on button\n" + e.getMessage());
        }
    }

    public boolean isRegisterTitlePresent() {
        waitForElement(REGISTER_TITLE, "Register title");
        return REGISTER_TITLE.isDisplayed();
    }

    public void inputName(String name) {
        LogForTest.info("Input user name in user name field");
        try {
            NAME_FIELD.sendKeys(name);
        } catch (Exception e) {
            Assert.fail("Can't input user name" + e.getMessage());
        }
    }

    public void inputGeneratedEmail() {
        LogForTest.info("Input email in email address field");
        try {
            EMAIL_FIELD.sendKeys(generateEmail());
        } catch (Exception e) {
            Assert.fail("Can't input email" + e.getMessage());
        }
    }

    private void openGenderDropDown() {
        try {
            GENDER_DROP_DOWN.click();
        } catch (Exception e) {
            Assert.fail("Can't open gender drop down" + e.getMessage());
        }
    }

    private void openAgeDropDown() {
        try {
            AGE_DROP_DOWN.click();
        } catch (Exception e) {
            Assert.fail("Can't open age drop down" + e.getMessage());
        }
    }

    public void selectGenderFromDropDown(String gender) {
        LogForTest.info("Select gender from gender pop up");
        openGenderDropDown();
        if (GENDER_OPTIONS.size() == 0) {
            Assert.fail("Can't select gender option " + gender + ":\n Genders list is empty");
        }
        for (WebElement GENDER_OPTIONS1 : GENDER_OPTIONS) {
            if (GENDER_OPTIONS1.getText().equals(gender)) {
                try {
                    GENDER_OPTIONS1.click();
                } catch (Exception e) {
                    Assert.fail("Can' click gender option " + e.getMessage());
                }
                if (!GENDER_OPTION.getText().contains(gender)) {
                    return;
                }
                return;
            }
        }
    }

    public void selectAgeFromDropDown(String age) {
        LogForTest.info("Select age from age pop up");
        openAgeDropDown();
        if (AGE_OPTIONS.size() == 0) {
            Assert.fail("Can't select age option " + age + ":\n Age list is empty");
        }
        for (WebElement AGE_OPTIONS1 : AGE_OPTIONS) {
            if (AGE_OPTIONS1.getText().replaceAll(" лет", "").replace(" года", "")
                    .replace(" год", "").equals(age)) {
                try {
                    AGE_OPTIONS1.click();
                } catch (Exception e) {
                    Assert.fail("Can' click age option " + e.getMessage());
                }
                if (!GENDER_OPTION.getText().contains(age)) {
                    return;
                }
                return;
            }
        }
    }

    public void clickRegisterButton() {
        LogForTest.info("Click 'Зарегистрироваться' button");
        try {
            REGISTER_BUTTON.click();
        } catch (Exception e) {
            Assert.fail("Can't click register button" + e.getMessage());
        }
    }

    @Override
    public String getCurrentUrl() {
        LogForTest.info("Checking current url");
        return driver.getCurrentUrl().split("/")[2];
    }
}
