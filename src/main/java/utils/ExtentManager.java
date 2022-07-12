package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance(String fileName) {
        System.out.println(System.getProperty("user.dir"));
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Selenium Demo", "CopyRight Adrian Jimenez");
        return extent;
    }


    public static String screenshotPath;
    public static String screenshotName;

    public static void captureScreenshot() throws IOException {
        BaseDriver baseDriver = new BaseDriver();
        File scrFile = ((TakesScreenshot) baseDriver.driver).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/target/reports/" + screenshotName));
        screenshotPath = System.getProperty("user.dir") + "/target/reports/" + screenshotName;
    }
}