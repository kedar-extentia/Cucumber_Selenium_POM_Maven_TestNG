package step_definitions;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.AutomationConstants;
import utils.BrowserFactory;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import static utils.AutomationConstants.BROWSER_TYPE;
import static utils.AutomationConstants.URL;

public class Hooks {
    public static WebDriver driver = BrowserFactory.getDriver();

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(BrowserFactory.class));

    @Before
    public static void openBrowser() throws MalformedURLException {
        LOGGER.info("Opening Browser.....");

//        System.setProperty("webdriver.chrome.driver", "C:\\MyWorkSpace\\COMMON_LIBS\\BrowserDrivers\\chromedriver.exe");
//        driver=new ChromeDriver();
        try {
            BrowserFactory.StartBrowser(BROWSER_TYPE, URL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        driver.get(AutomationConstants.URL);
//        driver.manage().deleteAllCookies();
//        driver.manage().window().maximize();

    }

    @After
    public static void closeBrowser() {
        System.out.println("Closing the browser.........");
        BrowserFactory.QuitBrowser();
//        driver.quit();
//        driver = null;
    }
    @BeforeStep
    public void BeforeEveryStep(Scenario scenario) {
        System.out.println("Before every step --------");
//        System.out.println("Before every step " + scenario.getId());

        //((PickleStep)((PickleStepTestStep)
    }

    @AfterStep
    public void AfterEveryStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
//        System.out.println("Before every step " + stepTestStep.getId());
        System.out.println("-------After every step");

    }


    /*    @After
     *//**
     * Embed a screenshot in test report if test is marked as failed
     */
     @After
    public void embedScreenshot(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
//                log.info
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
//            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }

        }

    }

}
