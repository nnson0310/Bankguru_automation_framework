package pageobjects;

import commons.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import pageinterfaces.DeleteCustomerPageUI;

public class DeleteCustomerPage extends BasePage {

    WebDriver driver;

    public DeleteCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToSubmitButton(WebDriver driver) {
        waitForElementVisible(driver, DeleteCustomerPageUI.SUBMIT_BUTTON);
        clickToElement(driver, DeleteCustomerPageUI.SUBMIT_BUTTON);
    }

    public String getPopupMessage() {
        Alert alert = waitForAlertPresent(driver);
        String alertMessage = alert.getText();
        alert.accept();
        return alertMessage;
    }
}
