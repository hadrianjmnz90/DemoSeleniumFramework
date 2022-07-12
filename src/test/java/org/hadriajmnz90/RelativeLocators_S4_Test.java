package org.hadriajmnz90;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.opensourcedemo.LoginPage;
import utils.BaseDriver;

public class RelativeLocators_S4_Test extends BaseDriver {

    LoginPage loginPage;

    @BeforeClass
    @Parameters("baseUrl")
    public void startDriver(String baseUrl) {
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testGetCredentialsOnScreen() {
        loginPage.getCredentialsTextWithRelativeLocators();
    }

    @Test
    public void testGetAllSocialMedia() {
        loginPage.getAllSocialMediaTextWithRelativeLocators();
    }
}
