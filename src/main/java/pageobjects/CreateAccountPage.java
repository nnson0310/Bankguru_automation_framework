package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageinterfaces.DepositPageUI;

public class CreateAccountPage extends BasePage {
    WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectAccountType(WebDriver driver, String accountType) {
        waitForElementClickable(driver, DepositPageUI.ACCOUNT_TYPE_SELECT);
        selectItemInDropDown(driver, DepositPageUI.ACCOUNT_TYPE_SELECT, accountType);
    }

    public void clickToSubmitButton(WebDriver driver) {
        waitForElementClickable(driver, DepositPageUI.SUBMIT_BUTTON);
        clickToElement(driver, DepositPageUI.SUBMIT_BUTTON);
    }

    public boolean isCreateAccountSuccessMessageDisplayed(WebDriver driver, String message) {
        waitForElementVisible(driver, DepositPageUI.CREATE_ACCOUNT_SUCCESS_MESSAGE, message);
        return isElementDisplayed(driver, DepositPageUI.CREATE_ACCOUNT_SUCCESS_MESSAGE, message);
    }
}
