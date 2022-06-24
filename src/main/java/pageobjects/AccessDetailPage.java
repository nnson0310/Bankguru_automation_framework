package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageinterfaces.AccessDetailPageUI;

public class AccessDetailPage extends BasePage {
    WebDriver driver;

    public AccessDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUserId(WebDriver driver) {
        waitForElementVisible(driver, AccessDetailPageUI.USERID_ROW);
        return getElementText(driver, AccessDetailPageUI.USERID_ROW);
    }

    public String getPassword(WebDriver driver) {
        waitForElementVisible(driver, AccessDetailPageUI.PASSWORD_ROW);
        return getElementText(driver, AccessDetailPageUI.PASSWORD_ROW);
    }

    public LoginPage redirectToLoginPage(WebDriver driver, String url) {
        redirectToPage(driver, url);
        return PageGeneratorManager.getPageGeneratorManager().getLoginPage(driver);
    }
}
