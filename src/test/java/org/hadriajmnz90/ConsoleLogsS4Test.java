package org.hadriajmnz90;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.log.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.saucedemo.HomePage;
import pages.saucedemo.LoginPage;
import utils.BaseDriver;

public class ConsoleLogsS4Test extends BaseDriver {

    @Test
    @Parameters("baseUrl")
    public void testLoginValidCredentials(String baseUrl) {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());
        // Add A Listener For The Logs
        devTools.addListener(Log.entryAdded(), logEntry -> {
            System.out.println("----------");
            System.out.println("Level: " + logEntry.getLevel());
            System.out.println("Text: " + logEntry.getText());
            System.out.println("Broken URL: " + logEntry.getUrl());
        });
        driver.get(baseUrl);
    }
}
