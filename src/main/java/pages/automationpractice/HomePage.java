package pages.automationpractice;

import links.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.Iterator;
import java.util.Set;

public class HomePage {

    WebDriver driver;

    private By emailField = By.id("email_create");
    private By submitButton = By.id("SubmitCreate");
    private By searchProductBar = By.id("search_query_top");
    private By searchProductButton = By.name("submit_search");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void testNewWindowTab(){
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get(Links.pricesDropUrl);
        System.out.println("Title: " + driver.getTitle());
        driver.manage().window().minimize();
    }

    public void testWorkingInBothWindowTabs(){
        driver.manage().window().minimize();
        driver.switchTo().newWindow(WindowType.TAB).get(Links.myAccountUrl);
        System.out.println("Title: " + driver.getTitle());
        // Work In The New Window Or Tab
        driver.findElement(emailField).sendKeys("hadrianjmnz@gmail.com");
        driver.findElement(submitButton).click();

        Set<String> allWindowTabs= driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabs.iterator();
        String mainFirstWindow = iterate.next();

        // Switch & Work In The Main Window Or Tab
        driver.switchTo().window(mainFirstWindow);
        driver.findElement(searchProductBar).sendKeys("Shirt");
        driver.findElement(searchProductButton).click();
        System.out.println("Title: " + driver.getTitle());
    }
}