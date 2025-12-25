package org.hadriajmnz90;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.saucedemo.HomePage;
import pages.saucedemo.LoginPage;
import utils.BaseDriver;

import java.io.IOException;

public class BrokenLinksTest extends BaseDriver {

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

    @Test(dependsOnMethods = "testLoginValidCredentials")
    public void testBrokenLinks() throws IOException {
        homePage.getBrokenLinks();
    }

}
