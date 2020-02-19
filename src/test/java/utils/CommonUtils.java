package utils;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {
    protected static WebDriver driver = BrowserFactory.getDriver();

    private static WebDriverWait wait = new WebDriverWait(driver, 30);
    static Logger LOGGER = Logger.getLogger(CommonUtils.class);

    public static void selectFromDropDown1(By by, String text) {
        Select sel = new Select(driver.findElement(by));
        sel.selectByVisibleText(text);

    }

    public static void selectFromDropDown(By by, int index) {
        Select sel = new Select(driver.findElement(by));
        sel.selectByIndex(index);

    }


    public static boolean isElementPresent(By element) {
        try {
            return driver.findElement(element).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public static void waitElementPresent(By element) {
        int counter = 0;

        while (counter <= AutomationConstants.DEFAULT_WAIT_SECONDS) {
            try {
//                wait.until(ExpectedConditions.presenceOfElementLocated(element));
                if (driver.findElement(element).isDisplayed())
                    return;
                else {
                    counter++;
                    System.out.println("Waiting for a sec...");
                    sleep(1);
                }
            } catch (Exception e) {
                //
            }
        }
}
    public static void waitWhileElementPresent(By element) {
        int counter = 0;

        while (counter <= AutomationConstants.DEFAULT_WAIT_SECONDS) {
            try {
//                wait.until(ExpectedConditions.presenceOfElementLocated(element));
                if (driver.findElement(element).isDisplayed())
                {
                    counter++;
                    System.out.println("Waiting for a sec...");
                    sleep(1);
                }
                else {
                    return;
                }
            } catch (Exception e) {
                //
                return;
            }
        }
    }
    public static void waitAndClickOnLink(String link)
    {
        waitElementPresent(By.linkText(link));
        driver.findElement(By.linkText(link)).click();
    }

    public static boolean isTextPresent(String text) {
        return getVisibleText().contains(text);
    }

    public static String getVisibleText() {
        driver = BrowserFactory.getDriver();
        return driver.findElement(By.tagName("body")).getText();
    }

    public static void selectCheckBox(By by, boolean b) {
        driver = BrowserFactory.getDriver();
        if (b) {
            if (!driver.findElement(by).isSelected()) {
                driver.findElement(by).click();
            }
        } else {
            if (driver.findElement(by).isSelected()) {
                driver.findElement(by).click();
            }
        }
        sleep(5);
    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i * 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void setTestEnv(String testEnv, String checkoutNewOld) {

        if(checkoutNewOld.equalsIgnoreCase("new"))
            driver.findElement(By.linkText("enable new checkout")).click();
        else
            driver.findElement(By.linkText("enable old checkout")).click();
        driver.findElement(By.linkText(testEnv)).click();
        driver.findElement(By.linkText("Go to homepage")).click();
    }

    public static void waitForText(String text)
    {
        WebDriverWait webDriverWait = new WebDriverWait(driver,AutomationConstants.MAX_TIMEOUTS);
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.tagName("body")),text));
    }

    public static boolean clickIfPresent(By by) {
        if(isElementPresent(by))
        {
            driver.findElement(by).click();
            return true;
        }


        return false;
    }

    public static void waitForTextOnElement(By element, String text) {
        int counter = 0;
        while (counter <= AutomationConstants.DEFAULT_WAIT_SECONDS) {
            try {
//                wait.until(ExpectedConditions.presenceOfElementLocated(element));
                if (driver.findElement(element).getText().equalsIgnoreCase(text))
                    return;
                else {
                    counter++;
                    System.out.println("Waiting for a sec...");
                    sleep(1);
                }
            } catch (NoSuchElementException e) {
                //
            }
        }
    }
}
