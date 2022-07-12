package org.example;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseDriver;

public class LoginTest extends BaseDriver {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeClass
    @Parameters("baseUrl")
    public void startDriver(String baseUrl) {
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    @Parameters({"username", "password"})
    public void testLoginValidCredentials(String username, String password) {
        loginPage.enterCredentials(username, password);
        loginPage.clickLoginButton();
        homePage.verifyHomePageIsDisplayed();
        log.info("Home Page Displayed");
    }
}
