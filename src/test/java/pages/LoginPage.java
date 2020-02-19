package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "txtUsername") public WebElement userName;
    @FindBy(id= "txtPassword") public WebElement passWord;
    @FindBy(id= "btnLogin") public WebElement loginButton;
    @FindBy(id= "logInPanelHeading") public WebElement loginPanel;


    public void Login(String user, String psw){
//        Assert.assertTrue(loginPanel.isDisplayed());
        userName.sendKeys(user);
        passWord.sendKeys(psw);
    }

    public void ClickLogin(){
        loginButton.click();
    }
}
