package browserdriverfactory;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Collections;

public class ChromeDriverFactory implements BrowserFactory{

    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        //use chromeOptions class to setup before running testcases
        //such as set language, disable notifications
        chromeOptions.addArguments("--lang=vi");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-geolocation");
        chromeOptions.addExtensions(new File(GlobalConstants.getGlobalConstants().getBrowserExtensionPath() + "adblock.crx"));
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        return new ChromeDriver(chromeOptions);
    }
}
