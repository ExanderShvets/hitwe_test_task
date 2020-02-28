package register.tests;

import base.tests.BasicTest;
import org.testng.annotations.*;
import register.pages.ProfilePage;
import utils.logs.LogForTest;
import org.testng.Assert;
import register.pages.LandingInterPage;

/**
 * Created by Exander on 27/02/2020.
 */

public class SingUpAndUploadPhotoTest extends BasicTest {
    private LandingInterPage landingInterPage;
    private ProfilePage profilePage;

    @Override
    public void initPages() {
        landingInterPage = new LandingInterPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Override
    @BeforeClass
    @Parameters("browser")
    public void prepareTest(@Optional("CH") String browser) {
        super.prepareTest(browser);
    }

    @Test (priority = 1)
    public void singUpTest() {
        LogForTest.LOGGER.info("Start singUpTest");
        gotoPage(startUrl);
        landingInterPage.clickButtonWithTextGirls();
        landingInterPage.clickButtonWithTextDark();
        landingInterPage.clickButtonWithTextDarkHair();
        landingInterPage.clickButtonWithTextWithForm();
        Assert.assertTrue(landingInterPage.isRegisterTitlePresent());
        landingInterPage.inputName("Selenium_Test");
        landingInterPage.inputGeneratedEmail();
        landingInterPage.selectGenderFromDropDown("Парень"); // You can change input to "Девушка"
        landingInterPage.selectAgeFromDropDown("25"); // Put any valid age
        landingInterPage.clickRegisterButton();
        Assert.assertEquals(landingInterPage.getCurrentUrl(), "hitwe.com");
    }

    @Test (priority = 2)
    public void uploadPhotoTest() {
        LogForTest.LOGGER.info("Start uploadPhotoTest");
        profilePage.closeAdvPopUp();
        profilePage.addPhotoFromPC();
        Assert.assertTrue(profilePage.isPhotoUploaded());
        profilePage.clickGoButton();
    }
}

