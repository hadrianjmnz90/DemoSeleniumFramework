package org.hadriajmnz90;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.saucedemo.HomePage;
import pages.saucedemo.LoginPage;
import utils.BaseDriver;

public class WaitsTest extends BaseDriver {

    LoginPage loginPage;

    @BeforeClass
    @Parameters("baseUrl")
    public void startDriver(String baseUrl) {
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
    }

    @Test
    @Parameters()
    public void testWaitMethodsInSelenium() {
       loginPage.waitMethodsSelenium();
    }
}
