package pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By productsTitle = By.className("title");
    private By linksTag = By.tagName("a");

    public void verifyHomePageIsDisplayed() {
        Assert.assertTrue(driver.findElement(productsTitle).isDisplayed(), " Element not shown in the website");
    }

    public void getBrokenLinks() throws IOException {
        List<WebElement> siteLinks = driver.findElements(linksTag);
        for (int i = 0; i < siteLinks.size(); i++) {
            WebElement element = siteLinks.get(i);
            String url = element.getAttribute("href");
            System.out.println("url- " + url);
            if (url != null){
                verifyLinks(url);
            }
        }
    }

    public void verifyLinks(String url) throws IOException {
        URL link = new URL(url);
        HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
        System.out.println(httpConn.getResponseCode());
        if (httpConn.getResponseCode() != 200){
            System.out.println("Broken Link");
        }
    }

}
