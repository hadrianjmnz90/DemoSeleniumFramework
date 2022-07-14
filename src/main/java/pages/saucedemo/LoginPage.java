package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Waits;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By acceptedUsersHeader = By.xpath("//div[@id='login_credentials']/h4");
    private By mockElement = By.id("mockId");


    public void enterCredentials(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void waitMethodsSelenium() {
        Waits.explicitWaitElementToBeClickable(10, loginButton).click();
        Waits.explicitWaitUntilTextPresentInElement(
                5, driver.findElement(acceptedUsersHeader), "Accepted usernames are:");
        Waits.explicitWaitUntilTitleIs(10, "Swag Labs");
        Waits.explicitWaitUntilElementNotDisplayed(5, mockElement);
        Waits.fluentWaitUntilElementPresent(20, 2, mockElement);
    }
}
