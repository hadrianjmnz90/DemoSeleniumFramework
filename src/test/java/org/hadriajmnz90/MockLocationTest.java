package org.hadriajmnz90;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.emulation.Emulation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockLocationTest extends BaseDriver {

    private String sikuliImgFolderPath = System.getProperty("user.dir") + "\\src\\main\\java\\sikulipictures\\";

    @Test
    public void mockGeoLocationExecuteCDPCommand() throws FindFailed {
        double expectedLatitude = 21.8791566;
        double expectedLongitude = -102.3046049;
        Map coordinates = new HashMap() {{
            put("latitude", expectedLatitude);
            put("longitude", expectedLongitude);
            put("accuracy", 1);
        }};
        ((ChromeDriver) driver).executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.get("https://where-am-i.org/");
        clickAllowLocationButton();
        String actualLatitude = driver.findElement(By.id("latitude")).getText();
        String actualLongitude = driver.findElement(By.id("longitude")).getText();
        System.out.println("actualLatitude: " + actualLatitude + " actualLongitude: " + actualLongitude);
        Assert.assertEquals(String.valueOf(expectedLatitude), actualLatitude, "Latitudes Differ");
    }

    @Test()
    public void mockGeoLocationDevTools() throws FindFailed {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
                Optional.of(13.4501),
                Optional.of(1)));
        driver.get("https://my-location.org/");
        clickAllowLocationButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("address")), "Berlin"));
        String myLocation = driver.findElement(By.id("address")).getText();
        System.out.println("Your current location is: " + myLocation);
    }

    public void clickAllowLocationButton() throws FindFailed {
        Screen screen = new Screen();
        Pattern allowBtnPic = new Pattern(sikuliImgFolderPath + "allowButton.PNG");
        screen.wait(allowBtnPic, 5);
        screen.click(allowBtnPic);
    }
}
