package hadriajmnz90;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseDriver;

import java.time.Duration;

public class PractiveSeleniumTest extends BaseDriver {

    public void practiceWaits(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("someId")));
        button.click();
    }

    public void practiceSelect(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement selector = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("someId")));
        Select vehicleOptions = new Select(selector);
        vehicleOptions.deselectAll();
        vehicleOptions.getFirstSelectedOption();
        vehicleOptions.selectByIndex(1);
        vehicleOptions.selectByVisibleText("Tesla");

        selector.getCssValue("font");
    }

    public void practiceWindows(){
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to("https://www.browserstack.com/");

        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://www.browserstack.com/");
    }
}
