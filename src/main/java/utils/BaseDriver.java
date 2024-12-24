package utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseDriver {
    public static Logger log = LogManager.getLogger(BaseDriver.class);

    protected static WebDriver driver;
    private String log4jPath = System.getProperty("user.dir") + "\\src\\";


    @BeforeSuite
    @Parameters({"browser", "implicitlyWait", "pageLoadTimeout", "printLogs"})
    public void setDriver(String browser, int implicitlyWait, int pageLoadTimeout, boolean printLogs) {
        if (printLogs) {
            BasicConfigurator.configure();
            PropertyConfigurator.configure(log4jPath + "log4j.properties");
        }
        log.info("browser: " + browser);
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new WebDriverException("browser selected not supported in this version");
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().window().maximize();
        log.info("Browser Launched");
    }

    @AfterSuite
    public void endTest() {
        driver.quit();
        log.info("Test Session Closed");
    }
}
