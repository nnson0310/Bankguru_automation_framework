package pageobjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

    private static PageGeneratorManager pageGeneratorManager;

    private PageGeneratorManager() {

    }

    public static PageGeneratorManager getPageGeneratorManager() {
        if (pageGeneratorManager == null) {
            return new PageGeneratorManager();
        }
        return pageGeneratorManager;
    }

    public RegisterPage getRegisterPage(WebDriver driver) {
        return new RegisterPage(driver);
    }

    public LoginPage getLoginPage(WebDriver driver) {
        return new LoginPage(driver);
    }

    public AccessDetailPage getAccessDetailPage(WebDriver driver) {
        return new AccessDetailPage(driver);
    }

    public ManagerHomePage getManagerHomePage(WebDriver driver) {
        return new ManagerHomePage(driver);
    }
    public CreateCustomerPage getCreateCustomerPage(WebDriver driver) {
        return new CreateCustomerPage(driver);
    }

}
