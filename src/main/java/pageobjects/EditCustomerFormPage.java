package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageinterfaces.CommonUI;
import pageinterfaces.EditCustomerFormPageUI;

public class EditCustomerFormPage extends BasePage {

    WebDriver driver;

    public EditCustomerFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEditCustomerHeadingDisplayed(WebDriver driver) {
        waitForElementVisible(driver, EditCustomerFormPageUI.EDIT_CUSTOMER_HEADING);
        return isElementDisplayed(driver, EditCustomerFormPageUI.EDIT_CUSTOMER_HEADING);
    }

    public String getFieldValueByDynamicLocator(WebDriver driver, String attributeName, String inputName) {
        waitForElementVisible(driver, CommonUI.DYNAMIC_TEXTBOX, inputName);
        return getAttributeValue(driver, CommonUI.DYNAMIC_TEXTBOX, attributeName, inputName);
    }
}
