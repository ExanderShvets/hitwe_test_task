package base.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.logs.LogForTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.DriverListener;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


/**
 * Created by Exander on 26/02/2020.
 */


public abstract class BasicTest {
    public static EventFiringWebDriver driver;
    protected static String startUrl = "https://hitwe.com/landing/inter2?p=15276";
    public static int testStepCount = 1;
    private static String TRACE = "";

    public abstract void initPages();

    @BeforeSuite
    @Parameters("browser")
    public void prepareTest(String browser) {
        LogForTest.resetLogLists();
        if (browser.equalsIgnoreCase("CH")) {
            LogForTest.LOGGER.info("SERVICE MESSAGE: Chrome webdriver would be used");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            LogForTest.LOGGER.info("SERVICE MESSAGE: Set driver options");
            ChromeOptions co = new ChromeOptions();
            co.setHeadless(false); //change to true to enable headless mode
            co.addArguments("--window-size=1920,1080");
            co.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            co.addArguments("--no-sandbox"); // Bypass OS security model
            co.setExperimentalOption("w3c", false);
            LogForTest.LOGGER.info("SERVICE MESSAGE: Set driver Desired capabilities");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability(ChromeOptions.CAPABILITY, co);
            LogForTest.LOGGER.info("SERVICE MESSAGE: Switch on driver Logging, set levels");
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
            LogForTest.LOGGER.info("SERVICE MESSAGE: Init driver");
            WebDriver webDriver = new ChromeDriver(cap);
            driver = new EventFiringWebDriver(webDriver);

        } else if (browser.equalsIgnoreCase("FF")) {
            LogForTest.LOGGER.info("SERVICE MESSAGE: Firefox webdriver would be used");
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            LogForTest.LOGGER.info("SERVICE MESSAGE: Set driver options");
            FirefoxOptions co = new FirefoxOptions();
            co.setHeadless(false); //change to true to enable headless mode
            co.addArguments("--window-size=1920,1080");
            co.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            co.addArguments("--no-sandbox"); // Bypass OS security model
            LogForTest.LOGGER.info("SERVICE MESSAGE: Set driver Desired capabilities");
            DesiredCapabilities cap = DesiredCapabilities.firefox();
            cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, co);
            LogForTest.LOGGER.info("SERVICE MESSAGE: Switch on driver Logging, set levels");
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
            LogForTest.LOGGER.info("SERVICE MESSAGE: Init driver");
            WebDriver webDriver = new FirefoxDriver(cap);
            driver = new EventFiringWebDriver(webDriver);
        }

        try {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            TRACE = TRACE + String.valueOf(e).split("\n")[0].split(": ")[1];
        }
        driver.register(new DriverListener("#FFFF00 ", 1, 1500, TimeUnit.MILLISECONDS));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @BeforeMethod
    public void initTest() {
        testStepCount = 1;
    }

    @AfterSuite
    public void closeBrowser() {
        takeScreenshot();
        driver.quit();
    }

    private void takeScreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("target\\screenshot_" + this.getClass().getName() + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoPage(String url) {
        try {
            String currentUrl = driver.getCurrentUrl().split("#")[0];
            if (!driver.getCurrentUrl().equals(url)) {
                driver.get(url);
                Thread.sleep(2000);
                if (driver.getCurrentUrl().equals(currentUrl)) {
                    driver.get(url);
                }
            } else {
                LogForTest.info("Already on " + url);
            }
            initPages();
        } catch (WebDriverException e) {
            Assert.assertTrue(false, "Timeout loading page");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

