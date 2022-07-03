package envfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.FunctionHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class GridEnvFactory {

    WebDriver driver;

    private String browserName, ipAddress, port;

    public GridEnvFactory(String browserName, String ipAddress, String port) {
        this.browserName = browserName;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public WebDriver getDriver() {
        DesiredCapabilities capabilities = null;
        BROWSER_NAME browser = BROWSER_NAME.valueOf(browserName.toUpperCase());

        if (browser == BROWSER_NAME.FIREFOX) {
            WebDriverManager.firefoxdriver().setup();

            capabilities = DesiredCapabilities.firefox();
            // Comment below code if running with docker compose
            capabilities.setAcceptInsecureCerts(true);
            capabilities.setBrowserName(String.valueOf(BROWSER_NAME.FIREFOX).toLowerCase());
            capabilities.setPlatform(Platform.ANY);
            capabilities.setVersion("latest");

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.merge(capabilities);
        } else if (browser == BROWSER_NAME.CHROME) {
            WebDriverManager.chromedriver().setup();

            capabilities = DesiredCapabilities.chrome();
            // Comment below code if running with docker compose
            capabilities.setAcceptInsecureCerts(true);
            capabilities.setBrowserName(String.valueOf(BROWSER_NAME.CHROME).toLowerCase());
            capabilities.setPlatform(Platform.ANY);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(capabilities);
        } else if (browser == BROWSER_NAME.OPERA) {
            WebDriverManager.operadriver().setup();

            capabilities = DesiredCapabilities.operaBlink();
            capabilities.setAcceptInsecureCerts(true);
            capabilities.setBrowserName(String.valueOf(BROWSER_NAME.OPERA).toLowerCase());
            capabilities.setPlatform(Platform.ANY);

            OperaOptions operaOptions = new OperaOptions();
            operaOptions.merge(capabilities);
        } else {
            throw new RuntimeException("Browser name is invalid");
        }

        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, port)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
