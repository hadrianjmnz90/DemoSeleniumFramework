package org.hadriajmnz90;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.network.Network;
import org.openqa.selenium.devtools.v103.network.model.ConnectionType;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BaseDriver;

import java.util.Optional;

public class EmulateNetworkConditionsTest extends BaseDriver {
    DevTools devTools;

    @Test
    @Parameters("baseUrl")
    public void emulateCellConnectionTest(String baseUrl) {
        long startTime = System.currentTimeMillis();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(
                false,
                150,
                2500,
                2000,
                Optional.of(ConnectionType.CELLULAR4G)));
        driver.get(baseUrl);
        System.out.println("Enable Slow Network: " + driver.getTitle());
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }

    @Test
    @Parameters("baseUrl")
    public void doNotEnableCellNetwork(String baseUrl) {
        long startTime = System.currentTimeMillis();
        driver.get(baseUrl);
        System.out.println("Do Not Enable Network: " + driver.getTitle());
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }
}
