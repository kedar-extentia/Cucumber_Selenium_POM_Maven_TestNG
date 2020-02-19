package step_definitions;

import io.cucumber.java.en.And;
import pages.DashboardPage;

import static utils.BrowserFactory.driver;

public class dashboard {

    @And("I logout")
    public void iLogout() {
        DashboardPage page = new DashboardPage(driver);
        page.logoutButton.click();
        System.out.println("USER IS LOGGED OUT------------");
    }
}
