package hadriajmnz90;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.saucedemo.HomePage;
import pages.saucedemo.LoginPage;
import utils.BaseDriver;

public class LoginTestDataProvider extends BaseDriver {

    LoginPage loginPage;
    HomePage homePage;

    @Test(dataProvider = "loginData")
    public void testLoginValidCredentials(String username, String password) {
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        loginPage.enterCredentials(username, password);
        loginPage.clickLoginButton();
        homePage.verifyHomePageIsDisplayed();
        log.info("Home Page Displayed");
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"error_user", "secret_sauce"}
        };
    }
}
