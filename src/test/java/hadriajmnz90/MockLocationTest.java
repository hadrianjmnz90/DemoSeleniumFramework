package hadriajmnz90;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v143.emulation.Emulation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BaseDriver;
import utils.SikuliActions;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockLocationTest extends BaseDriver {

    @Test
    @Parameters({"whereIAmSiteUrl", })
    public void mockGeoLocationExecuteCDPCommand(String url) throws FindFailed {
        double expectedLatitude = 21.8791566;
        double expectedLongitude = -102.3046049;
        Map coordinates = new HashMap() {{
            put("latitude", expectedLatitude);
            put("longitude", expectedLongitude);
            put("accuracy", 1);
        }};
        ((ChromeDriver) driver).executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.get(url);
        SikuliActions.clickAllowLocationButton();
        String actualLatitude = driver.findElement(By.id("latitude")).getText();
        String actualLongitude = driver.findElement(By.id("longitude")).getText();
        System.out.println("actualLatitude: " + actualLatitude + " actualLongitude: " + actualLongitude);
        Assert.assertEquals(String.valueOf(expectedLatitude), actualLatitude, "Latitudes Differ");
    }

    @Test()
    @Parameters("myLocationUrl")
    public void mockGeoLocationDevTools(String url) throws FindFailed {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(
                Optional.<Number>of(52.5043),   // latitude
                Optional.<Number>of(13.4501),   // longitude
                Optional.<Number>of(1),         // accuracy
                Optional.empty(),               // altitude
                Optional.empty(),               // altitudeAccuracy
                Optional.empty(),               // heading
                Optional.empty()                // speed
        ));
        driver.get(url);
        SikuliActions.clickAllowLocationButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("address")), "Berlin"));
        String myLocation = driver.findElement(By.id("address")).getText();
        System.out.println("Your current location is: " + myLocation);
    }
}
