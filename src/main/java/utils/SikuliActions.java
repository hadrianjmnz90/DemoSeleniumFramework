package utils;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliActions {
    private static String sikuliImgFolderPath = System.getProperty("user.dir") + "\\src\\main\\java\\sikulipictures\\";

    public static void clickAllowLocationButton() throws FindFailed {
        Screen screen = new Screen();
        Pattern allowBtnPic = new Pattern(sikuliImgFolderPath + "allowButton.PNG");
        screen.wait(allowBtnPic, 5);
        screen.click(allowBtnPic);
    }
}
