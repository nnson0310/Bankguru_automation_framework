package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageinterfaces.LoginPageUI;

public class LoginPage extends BasePage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToUserIdTextbox(WebDriver driver, String userId) {
        waitForElementVisible(driver, LoginPageUI.USERID_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.USERID_TEXTBOX, userId);
    }

    public void inputToPasswordTextbox(WebDriver driver, String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public ManagerHomePage clickToLoginButton(WebDriver driver) {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getPageGeneratorManager().getManagerHomePage(driver);
    }
}
