package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest
{
    @Test
    public void launchGoogle()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hejimene\\IdeaProjects\\SampleTest\\src\\main\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        System.out.println(  driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google");
        driver.quit();
    }
}
