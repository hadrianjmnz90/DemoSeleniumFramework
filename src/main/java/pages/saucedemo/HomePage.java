package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By productsTitle = By.className("title");

    public void verifyHomePageIsDisplayed() {
        Assert.assertTrue(driver.findElement(productsTitle).isDisplayed(), " Element not shown in the website");
    }

}
