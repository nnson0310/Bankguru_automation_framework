package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageinterfaces.CreateCustomerPageUI;
import pageinterfaces.CreateCustomerSuccessPageUI;

public class CreateCustomerSuccessPage extends BasePage {
    WebDriver driver;

    public CreateCustomerSuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isRegisterSuccessMsgDisplayed(WebDriver driver, String msg) {
        waitForElementVisible(driver, CreateCustomerSuccessPageUI.CUSTOMER_REGISTER_SUCCESS_MSG, msg);
        return isElementDisplayed(driver, CreateCustomerSuccessPageUI.CUSTOMER_REGISTER_SUCCESS_MSG, msg);
    }

    public String getCustomerInfoByDynamicLocator(WebDriver driver, String rowName) {
        waitForElementVisible(driver, CreateCustomerSuccessPageUI.CREATED_CUSTOMER_INFO, rowName);
        return getElementText(driver, CreateCustomerSuccessPageUI.CREATED_CUSTOMER_INFO, rowName);
    }
}
