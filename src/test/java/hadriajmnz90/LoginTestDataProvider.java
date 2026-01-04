package hadriajmnz90;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.saucedemo.HomePage;
import pages.saucedemo.LoginPage;
import utils.BaseDriver;

public class LoginTestDataProvider extends BaseDriver {

    LoginPage loginPage;
    HomePage homePage;

    @Test(dataProvider = "loginData")
    public void testLoginValidCredentials(String username, String password, boolean isLoginSuccessExpected) {
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        loginPage.enterCredentials(username, password);
        loginPage.clickLoginButton();
        if (isLoginSuccessExpected) {
            homePage.verifyHomePageIsDisplayed();
            log.info("Home Page Displayed");
        } else {
            log.info("Login error expected");
        }
    }

    @BeforeMethod
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", true},
                {"problem_user", "secret_sauce", true},
                {"error_user", "secret_", false}
        };
    }
}
