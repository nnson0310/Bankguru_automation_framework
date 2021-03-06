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

    public CreateCustomerSuccessPage getCreateCustomerSuccessPage(WebDriver driver) {
        return new CreateCustomerSuccessPage(driver);
    }

    public EditCustomerPage getEditCustomerPage(WebDriver driver) {
        return new EditCustomerPage(driver);
    }

    public EditCustomerFormPage getEditCustomerFormPage(WebDriver driver) {
        return new EditCustomerFormPage(driver);
    }

    public CreateAccountPage getCreateAccountPage(WebDriver driver) {
        return new CreateAccountPage(driver);
    }

    public DeleteCustomerPage getDeleteCustomerPage(WebDriver driver) {
        return new DeleteCustomerPage(driver);
    }

    public DepositPage getDepositPage(WebDriver driver) {
        return new DepositPage(driver);
    }

    public ChangePasswordPage getChangePasswordPage(WebDriver driver) {
        return new ChangePasswordPage(driver);
    }
}
