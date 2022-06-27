package pageobjects;

import commons.BasePage;
import org.openqa.selenium.Alert;
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

    public void clickSubmitButton(WebDriver driver) {
        waitForElementClickable(driver, CreateCustomerPageUI.SUBMIT_BUTTON);
        clickToElement(driver, CreateCustomerPageUI.SUBMIT_BUTTON);
    }

    public String getAlertValidationMessage(WebDriver driver) {
        Alert alert = waitForAlertPresent(driver);
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public void clickLogoutMenuSub(WebDriver driver, String menuSub) {
        clickToMenuSubByDynamicLocator(driver, menuSub);
        Alert alert = waitForAlertPresent(driver);
        alert.accept();
    }

    public void checkGenderRadio(WebDriver driver, String gender) {
        waitForElementClickable(driver, CreateCustomerPageUI.GENDER_RADIO_BUTTON, gender);
        checkCheckboxOrRadio(driver, CreateCustomerPageUI.GENDER_RADIO_BUTTON, gender);
    }

    public void inputToDateOfBirthField(WebDriver driver, String dateOfBirth) {
        waitForElementVisible(driver, CreateCustomerPageUI.DATE_OF_BIRTH_TEXTBOX);
        changeAttribute(driver, CreateCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, "type", "text");
        sendKeyToElement(driver, CreateCustomerPageUI.DATE_OF_BIRTH_TEXTBOX, dateOfBirth);
    }
}
