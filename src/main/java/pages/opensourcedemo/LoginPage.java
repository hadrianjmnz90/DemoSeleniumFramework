package pages.opensourcedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By logInPanelHeading = By.id("logInPanelHeading");
    private By socialMediaImage = By.tagName("img");
    private By footer = By.id("footer");
    private By credentialsLabel = By.tagName("span");

    public void getCredentialsTextWithRelativeLocators() {
        WebElement loginPanel = driver.findElement(logInPanelHeading);
        WebElement credentials = driver.findElement(with(
                credentialsLabel).above(loginPanel));
        System.out.println(credentials.getText());
    }

    public void getAllSocialMediaTextWithRelativeLocators() {
        List<WebElement> allSocialMedia = driver.findElements(with(
                socialMediaImage)
                .near(footer));

        for (WebElement socialMedia : allSocialMedia) {
            System.out.println(socialMedia.getAttribute("alt"));
        }
    }
}