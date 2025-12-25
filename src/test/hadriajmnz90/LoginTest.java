package org.hadriajmnz90;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.saucedemo.HomePage;
import pages.saucedemo.LoginPage;
import utils.BaseDriver;
import utils.VisualValidation;

import java.io.IOException;

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
    public void testLoginValidCredentials(String username, String password) throws IOException {
        loginPage.enterCredentials(username, password);
        loginPage.clickLoginButton();
        homePage.verifyHomePageIsDisplayed();
        log.info("Home Page Displayed");
  //      driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // To capture baseline (only once) -- comment later
     //   VisualValidation.captureBaseline(driver, "testLoginValidCredentials");
        VisualValidation.validateElementScreenshot(driver, "testLoginValidCredentials");
    }
}
