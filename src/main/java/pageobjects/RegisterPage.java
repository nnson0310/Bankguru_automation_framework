package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageinterfaces.RegisterPageUI;

public class RegisterPage extends BasePage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToEmailTextbox(WebDriver driver, String email) {
        waitForAllElementVisible(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.EMAIL_ID_TEXTBOX, email);
    }

    public AccessDetailPage clickToSubmitButton(WebDriver driver) {
        waitForElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
        clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
        return PageGeneratorManager.getPageGeneratorManager().getAccessDetailPage(driver);
    }
}
