package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageinterfaces.EditCustomerPageUI;

public class EditCustomerPage extends BasePage {

    WebDriver driver;

    public EditCustomerPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickSubmitButton(WebDriver driver) {
        waitForElementVisible(driver, EditCustomerPageUI.SUBMIT_BUTTON);
        clickToElement(driver, EditCustomerPageUI.SUBMIT_BUTTON);
    }
}
