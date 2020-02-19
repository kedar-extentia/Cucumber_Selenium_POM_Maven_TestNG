package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import utils.AutomationConstants;
import utils.BrowserFactory;
import utils.CommonUtils;
import utils.CreatePrettyReport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "step_definitions",
//        format = {"pretty","html:target/whisper-html-report","json:target/whisper_report.json"},
//        plugin = {"pretty:reports/prettyReport.txt", "html:reports/cucumber-Report.html", "json:reports/cucumber.json", "rerun:reports/rerun.txt", "junit:reports/junit-report.xml", "testng:reports/testng-output.xml" },
        plugin = {
                "pretty", "html:reports/cucumberHtmlReport",
//                "html:target/cucumberHtmlReport",     //  for html result
                "pretty:reports/cucumber-json-report.json",   // for json result
                "json:reports/cucumber.json",
                "html:reports/site/cucumber-pretty"
        },
        tags = {"@smoke"}
)
public class RunTest extends AbstractTestNGCucumberTests{
    static WebDriver driver;
    public static boolean initialiseOnce = false;
    public static CreatePrettyReport pr = null;
    protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(RunTest.class);
    @BeforeClass
    public static void start()
    {
        try {
            BrowserFactory.StartBrowser(AutomationConstants.BROWSER_TYPE, AutomationConstants.URL);
            driver = BrowserFactory.driver;
            driver.manage().timeouts().implicitlyWait(AutomationConstants.MAX_TIMEOUTS, TimeUnit.SECONDS);
            assertTrue(CommonUtils.isTextPresent("OrangeHRM"));
            System.out.println(" home page loaded....");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(AutomationConstants.local_reports)
            generatePrettyReportOnTheGo();

//        addShutdownHook();
    }

    @AfterClass
    public static void stop()
    {
        BrowserFactory.QuitBrowser();
    }

    public static class ScreenShotRemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {
        public ScreenShotRemoteWebDriver(URL url, DesiredCapabilities dc) {
            super(url, dc);
        }


        public <X> X getScreenshotAs(OutputType<X> target)
                throws WebDriverException {
            if ((Boolean) getCapabilities().getCapability(CapabilityType.TAKES_SCREENSHOT)) {
                return target.convertFromBase64Png(execute(DriverCommand.SCREENSHOT).getValue().toString());
            }
            return null;
        }
    }

    private static void addShutdownHook() {
        if(!initialiseOnce){
            logger.info("Initialise shutdown hook");
            initialiseOnce = true;
            //This should only run when JVM shutsdown
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    if(driver != null){
                        logger.info("Shutting down jvm");
                        if(pr!=null){
                            //give time to generate pretty report
                            try {
                                Thread.sleep(15000);
                            } catch (InterruptedException e) {

//                                e.printStackTrace();
                            }
                        }
                        try{
                            driver.quit();
                        }catch(Exception e){
                        }
                        logger.info("Shutdown complete");
                    }
                }
            });
        }
    }

    /**
     * This will generate pretty report on the go
     */
    private static void generatePrettyReportOnTheGo() {
        String generateReport = "yes";
        if(pr == null && generateReport.equals("yes")){
//            logger.info("Will Create Pretty Report On The Go");
            pr = new CreatePrettyReport();
            pr.monitorFolder(null);
        }

    }
}
