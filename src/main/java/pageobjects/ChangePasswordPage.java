package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class ChangePasswordPage extends BasePage {
    WebDriver driver;

    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
    }
}
