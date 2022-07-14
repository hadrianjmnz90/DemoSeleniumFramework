package utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseDriver {
    public static Logger log = LogManager.getLogger(BaseDriver.class);

    protected static WebDriver driver;

    private String driverPath = System.getProperty("user.dir") + "\\src\\main\\drivers\\";
    private String log4jPath = System.getProperty("user.dir") + "\\src\\";


    @BeforeSuite
    @Parameters({"browser", "implicitlyWait", "pageLoadTimeout", "printLogs"})
    public void setDriver(String browser, int implicitlyWait, int pageLoadTimeout, boolean printLogs) {
        if (printLogs) {
            BasicConfigurator.configure();
            PropertyConfigurator.configure(log4jPath + "log4j.properties");
        }
        System.out.println("browser:" + browser);
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");
             driver = new EdgeDriver();
        } else {
            throw new WebDriverException("browser selected not supported in this version");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        driver.manage().window().maximize();
        log.info("Browser Launched");
    }

    @AfterSuite
    public void endTest() {
        driver.quit();
    }
}
