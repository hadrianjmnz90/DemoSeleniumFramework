package utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseDriver {
    public static Logger log = LogManager.getLogger(BaseDriver.class);

    protected static WebDriver driver;

    private String log4jPath = System.getProperty("user.dir") + "\\src\\";

    @BeforeSuite
    @Parameters({"printLogs"})
    public void setLoggingProperties(boolean printLogs){
        if (printLogs) {
            BasicConfigurator.configure();
            PropertyConfigurator.configure(log4jPath + "log4j.properties");
        }
        System.out.println("Cleaning Extent Report folder...");
        File file = new File(System.getProperty("user.dir") + "\\target\\reports");
        String[] myFiles;
        if (file.isDirectory()) {
            myFiles = file.list();
            for (int i = 0; i < myFiles.length; i++) {
                File myFile = new File(file, myFiles[i]);
                myFile.delete();
            }
        }
    }

    @BeforeTest
    @Parameters({"browser", "pageLoadTimeout"})
    public void setDriver(String browser, int pageLoadTimeout) {
        log.info("browser: " + browser);
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(setChromeOptions());
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

    @AfterTest
    public void endTest() {
        driver.quit();
        log.info("Test Session Closed");
    }

    public static ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("translate.enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        return options;
    }
}
