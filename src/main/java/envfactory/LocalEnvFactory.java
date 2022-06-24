package envfactory;

import browserdriverfactory.*;
import org.openqa.selenium.WebDriver;

public class LocalEnvFactory {

    WebDriver driver;

    private String browserName;

    public LocalEnvFactory(String browserName) {
        this.browserName = browserName;
    }

    public WebDriver getDriver() {
        BROWSER_NAME browser = BROWSER_NAME.valueOf(browserName.toUpperCase());

        if (browser == BROWSER_NAME.FIREFOX) {
            driver = new FirefoxDriverFactory().getBrowserDriver();
        } else if (browser == BROWSER_NAME.CHROME) {
            driver = new ChromeDriverFactory().getBrowserDriver();
        } else if (browser == BROWSER_NAME.EDGE) {
            driver =  new EdgeDriverFactory().getBrowserDriver();
        } else if(browser == BROWSER_NAME.OPERA) {
            driver = new OperaDriverFactory().getBrowserDriver();
        }
        else if (browser == BROWSER_NAME.H_FIREFOX) {
            driver = new HFirefoxDriverFactory().getBrowserDriver();
        }
        else if (browser == BROWSER_NAME.H_CHROME) {
            driver = new HChromeDriverFactory().getBrowserDriver();
        }
        else {
            throw new RuntimeException("Browser name is invalid");
        }

        return driver;
    }

}
