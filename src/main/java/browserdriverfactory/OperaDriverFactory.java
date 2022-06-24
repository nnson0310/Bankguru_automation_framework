package browserdriverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class OperaDriverFactory implements BrowserFactory{

    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.operadriver().setup();

        OperaOptions operaOptions = new OperaOptions();

        return new OperaDriver(operaOptions);
    }
}
