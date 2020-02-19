package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
//import pages.BasePage;
import pages.LoginPage;
import utils.BrowserFactory;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.AutomationConstants.*;
import static utils.BrowserFactory.driver;

public class login {

    @Given("I launch URL")
    public void iLaunchURL() throws Throwable{
//        BrowserFactory.StartBrowser(BROWSER_TYPE, URL );
//        driver.get(URL);
        System.out.println("By I Launch Login URL step ----into Launch URL");

    }

    @When("I login OrangeHRM")
    public void iLoginOrangeHRM(){
        LoginPage page = new LoginPage(driver);
        page.Login(USERNAME, PASSWORD);
        page.ClickLogin();
    }


    @When("I Login Gmail {string} and {string}")
    public void iLoginGmail(String arg0) throws InterruptedException {
        sleep(3000);
        try {
            driver.findElement(By.xpath("//div[contains(text(),'Email or phone')]")).clear();
            driver.findElement(By.xpath("//div[contains(text(),'Email or phone')]")).sendKeys(arg0);
//            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys(PASSWORD, Keys.ENTER);
//            Thread.sleep(10000);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gb_rc']")).isDisplayed());

        } catch (NullPointerException e){

            System.out.println("NullPointerException thrown!");
        }
    }


    @And("I login OrangeHRM with ([^\"]*) and ([^\"]*)")
    public void iLoginOrangeHRMWithUsernameAndPassword(String user, String psw) {
        LoginPage page = new LoginPage(driver);
        page.Login(user, psw);
        page.ClickLogin();

    }


}
