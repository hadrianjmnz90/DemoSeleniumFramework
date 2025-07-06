package utils;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VisualValidation {

    private static final String BASELINE_PATH = "screenshots/baseline/";
    private static final String ACTUAL_PATH = "screenshots/actual/";
    private static final String DIFF_PATH = "screenshots/diff/";

    public static void validateElementScreenshot(WebDriver driver, String imageName) throws IOException {
        new File(BASELINE_PATH).mkdirs();
        new File(ACTUAL_PATH).mkdirs();
        new File(DIFF_PATH).mkdirs();
        // Take element screenshot with AShot
        Screenshot screenshot = new AShot()
                .takeScreenshot(driver);
        BufferedImage actualImage = screenshot.getImage();

        File actualFile = new File(ACTUAL_PATH + imageName + ".png");
        ImageIO.write(actualImage, "png", actualFile);

        File baselineFile = new File(BASELINE_PATH + imageName + ".png");
        if (!baselineFile.exists()) {
            throw new IOException("Baseline image not found: " + baselineFile.getPath());
        }
        BufferedImage expectedImage = ImageIO.read(baselineFile);

        File diffFile = new File(DIFF_PATH + imageName + "_diff.png");
        ImageComparison imageComparison = new ImageComparison(expectedImage, actualImage, diffFile);
        imageComparison.setRectangleLineWidth(5);
        ImageComparisonResult result = imageComparison.compareImages();

        if (result.getImageComparisonState() != ImageComparisonState.MATCH) {
            throw new AssertionError("Visual difference found! See diff image: " + diffFile.getPath());
        }
    }

    public static void captureBaseline(WebDriver driver,  String imageName) throws IOException {
        new File(BASELINE_PATH).mkdirs();
        new File(ACTUAL_PATH).mkdirs();
        new File(DIFF_PATH).mkdirs();
        Screenshot screenshot = new AShot()
                .takeScreenshot(driver);
        BufferedImage baselineImage = screenshot.getImage();
        File baselineFile = new File(BASELINE_PATH + imageName + ".png");
        ImageIO.write(baselineImage, "png", baselineFile);
        System.out.println("Baseline image saved to: " + baselineFile.getPath());
    }
}
