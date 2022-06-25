package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageinterfaces.CreateCustomerPageUI;

public class CreateCustomerPage extends BasePage {
    WebDriver driver;

    public CreateCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public CreateCustomerPage navigateToCreateCustomerPage(WebDriver driver, String menuSub) {
        clickToMenuSubByDynamicLocator(driver, menuSub);
        return PageGeneratorManager.getPageGeneratorManager().getCreateCustomerPage(driver);
    }

    public void inputToAddressField(WebDriver driver, String value) {
        waitForElementVisible(driver, CreateCustomerPageUI.EMAIL_TEXTAREA);
        sendKeyToElement(driver, CreateCustomerPageUI.EMAIL_TEXTAREA, value);
    }

    public boolean isAddressFieldValidationErrorMessageDisplayed(WebDriver driver, String inputName, String errorMessage) {
        waitForElementVisible(driver, CreateCustomerPageUI.EMAIL_FIELD_VALIDATION_ERROR_MSG, inputName, errorMessage);
        return isElementDisplayed(driver, CreateCustomerPageUI.EMAIL_FIELD_VALIDATION_ERROR_MSG, inputName, errorMessage);
    }
}
