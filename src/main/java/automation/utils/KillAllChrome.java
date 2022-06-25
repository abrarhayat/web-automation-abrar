package automation.utils;

import static automation.utils.AbstractTest.getOperatingSystem;

/**
 * @author abrar
 * since 12/6/20
 */
public class KillAllChrome {
    public static void main(String[] args) {
        try {
            if (getOperatingSystem().equals("linux")) {
                Runtime.getRuntime().exec("pkill -9 chrome");
            } else if (getOperatingSystem().equals("windows")) {
                Runtime.getRuntime().exec("taskkill /im chrome.exe /f /t");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
