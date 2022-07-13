package org.hadriajmnz90;


import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;
import utils.BaseDriver;

import java.util.HashMap;
import java.util.Map;

public class MockLocationTest extends BaseDriver {
    @Test
    public void mockGeoLocation_executeCDPCommand() throws FindFailed {
        ChromeDriver chromeDriver = (ChromeDriver) driver;
        Map coordinates = new HashMap() {{
            put("latitude", 32.746940);
            put("longitude", -97.092400);
            put("accuracy", 1);
        }};
        chromeDriver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        chromeDriver.get("https://where-am-i.org/");
        Screen screen = new Screen();
        Pattern allowBtnPic = new Pattern("sikulipictures/allowButton.PNG");
        screen.wait(allowBtnPic, 5);
        screen.click(allowBtnPic);
        System.out.println("end");
    }

    //  @Test
    public void mockGeoLocation_DevTools() {
        //     DevTools devTools = driver.getDevTools();
        //    devTools.createSession();
        //    devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043),
        //           Optional.of(13.4501),
        //          Optional.of(1)));
        //  driver.get("https://my-location.org/");
    }
}
