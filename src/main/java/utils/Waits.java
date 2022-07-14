package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.BaseDriver.driver;

public class Waits {

    public static WebElement explicitWaitElementToBeClickable(int explicitWaitTime, By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime))
                .until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }

    public static void explicitWaitUntilTextPresentInElement(int explicitWaitTime, WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void explicitWaitUntilTitleIs(int explicitWaitTime, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime))
                .until(ExpectedConditions.titleIs(text));
    }

    public static void explicitWaitUntilElementNotDisplayed(int explicitWaitTime, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void fluentWaitUntilElementPresent(int withTimeout, int pollingEvery, By locator) {
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(withTimeout))
                .pollingEvery(Duration.ofSeconds(pollingEvery))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
