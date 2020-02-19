package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath= "//a[contains(text(),'Logout')]") public WebElement logoutButton;

    public void Logout(){
        Assert.assertTrue(logoutButton.isDisplayed());
        logoutButton.click();
    }
}
