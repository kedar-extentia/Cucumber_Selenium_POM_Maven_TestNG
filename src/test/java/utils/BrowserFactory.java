package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public abstract class BrowserFactory 
	{
	public static WebDriver driver;

        static Logger LOGGER = Logger.getLogger(BrowserFactory.class);
		
	public static WebDriver StartBrowser(String Browser, String URL) throws MalformedURLException, InterruptedException
	{
        if(driver==null||!isSessionActive())
        {
         driver = startRemoteWebBrowser(Browser,URL);
        }
		driver.manage().window().maximize();
        return driver;
	}

        public static WebDriver getDriver()
        {
            return driver;
        }

        public static boolean isSessionActive()
        {
            try {

                return driver.findElements(By.tagName("body")).size()>0;

            }
            catch(Exception e)
            {

            }
            return false;
        }


	public static void QuitBrowser() 
	{
	    try{
            driver.quit();
        }
        catch(NullPointerException e)
        {
            System.out.print("NullPointerException Caught");
        }
        //  WebDriver d = getDriver();
        driver=null;
	}
protected static WebDriver startRemoteWebBrowser(String browser, String URL)
{
    switch (browser){
        case "Firefox":

            System.out.println("local firefox started...");
            FirefoxProfile firefoxprofile = new FirefoxProfile();
            firefoxprofile.setAssumeUntrustedCertificateIssuer(true);
            firefoxprofile.setAcceptUntrustedCertificates(true);
            driver = new FirefoxDriver();
//            driver = new FirefoxDriver((Capabilities) firefoxprofile);
            break;

        case "chrome":
            System.out.println("local chrome started...");
            //System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
            //  driver=new ChromeDriver();
            System.setProperty("webdriver.chrome.driver", "C:\\MyWorkSpace\\COMMON_LIBS\\BrowserDrivers\\chromedriver.exe");
            driver=new ChromeDriver();
            break;

        default :
            throw new RuntimeException("Browser give "+browser+ " did not load..");

    }

    driver.get(URL);
    return driver;
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


}



