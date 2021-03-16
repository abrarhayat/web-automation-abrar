package automation.utils;

/**
 * @author abrar
 * since 9/9/20
 */

abstract class Constants {

    //Windows Web Driver Directories
    final static String CHROME_DRIVER_WINDOWS_DIR = "webDriverExecutables/chrome/chromedriver_win32/chromedriver.exe";
    final static String FIREFOX_DRIVER_WINDOWS_DIR = "webDriverExecutables/firefox/windows/geckodriver.exe";

    //Linux Web Driver Directories
    final static String CHROME_DRIVER_LINUX_DIR = "webDriverExecutables/chrome/chromedriver_linux64/chromedriver";
    final static String FIREFOX_DRIVER_LINUX_DIR = "webDriverExecutables/firefox/linux/geckodriver";

    final static String CHROME_DRIVER_SYSTEM_PROPERTY = "webdriver.chrome.driver";
    final static String FIREFOX_DRIVER_SYSTEM_PROPERTY = "webdriver.gecko.driver";

}
