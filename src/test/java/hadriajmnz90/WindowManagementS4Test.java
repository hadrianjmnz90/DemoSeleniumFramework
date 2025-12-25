package hadriajmnz90;

import links.Links;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.automationpractice.HomePage;
import utils.BaseDriver;

public class WindowManagementS4Test extends BaseDriver {

    HomePage homePage;

    @BeforeClass
    public void startDriver() {
        driver.get(Links.automationPracticeBaseUrl);
        homePage = new HomePage(driver);
    }

    @Test
    public void testWindowHandling(){
        homePage.testNewWindowTab();
    }

    @Test
    public void testWorkingInBothWindowsTabs(){
        homePage.testWorkingInBothWindowTabs();
    }
}
