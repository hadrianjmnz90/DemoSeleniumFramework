package pages.automationpractice;

import links.Links;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

import static utils.BaseDriver.log;

public class HomePage {

    WebDriver driver;

    @FindBy(id = "email_create")
    private WebElement emailField;

    @FindBy(id = "SubmitCreate")
    private WebElement submitButton;

    @FindBy(id = "search_query_top")
    private WebElement searchProductBar;

    @FindBy(name = "submit_search")
    private WebElement searchProductButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void testNewWindowTab(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get(Links.pricesDropUrl);
        log.info("Title: " + driver.getTitle());
        driver.manage().window().minimize();
    }

    public void testWorkingInBothWindowTabs(){
        driver.manage().window().minimize();
        driver.switchTo().newWindow(WindowType.TAB).get(Links.myAccountUrl);
        System.out.println("Title: " + driver.getTitle());
        // Work In The New Window Or Tab
        emailField.sendKeys("hadrianjmnz@gmail.com");
        submitButton.click();

        Set<String> allWindowTabs= driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabs.iterator();
        String mainFirstWindow = iterate.next();

        // Switch & Work In The Main Window Or Tab
        driver.switchTo().window(mainFirstWindow);
        searchProductBar.sendKeys("Shirt");
        searchProductButton.click();
        log.info("Title: " + driver.getTitle());
    }
}