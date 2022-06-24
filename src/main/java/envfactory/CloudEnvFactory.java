package envfactory;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CloudEnvFactory {

    WebDriver driver;

    private String browserName, browserVersion, platform;

    public CloudEnvFactory(String browserName, String browserVersion, String platform) {
        this.browserName = browserName;
        this.platform = platform;
        this.browserVersion = browserVersion;
    }

    public WebDriver getDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", browserVersion);
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("name", "Run on Saucelabs on " + platform + " and on " + browserName);
        capabilities.setCapability("sauce:options", sauceOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getCloudUrl()), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}
