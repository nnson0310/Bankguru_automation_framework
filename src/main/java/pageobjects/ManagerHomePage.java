package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageinterfaces.ManagerPageUI;

public class ManagerHomePage extends BasePage {
    WebDriver driver;

    public ManagerHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginSuccessTextDisplayed(WebDriver driver) {
        waitForElementVisible(driver, ManagerPageUI.LOGIN_SUCCESS_TEXT);
        return isElementDisplayed(driver, ManagerPageUI.LOGIN_SUCCESS_TEXT);
    }

    public CreateCustomerPage navigateToCreateCustomerPage(WebDriver driver, String menuSub) {
        clickToMenuSubByDynamicLocator(driver, menuSub);
        return PageGeneratorManager.getPageGeneratorManager().getCreateCustomerPage(driver);
    }
}
