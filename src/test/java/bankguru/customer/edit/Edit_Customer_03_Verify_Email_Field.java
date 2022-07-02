package bankguru.customer.edit;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import commons.CommonText;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.*;

public class Edit_Customer_03_Verify_Email_Field extends BaseTest {
    WebDriver driver;

    String menuSub, emailFieldName;
    String[] invalidEmails;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
    EditCustomerPage editCustomerPage;
    EditCustomerFormPage editCustomerFormPage;

    @Parameters({"browserName", "browserVersion", "environmentName", "ipAddress", "port", "platform"})
    @BeforeClass
    public void setUp(
            @Optional("firefox") String browserName,
            @Optional("latest") String browserVersion,
            @Optional("local") String environmentName,
            @Optional("localhost") String ipAddress,
            @Optional("4444") String port,
            @Optional("Windows 10") String platform
    ) {

        driver = getBrowserDriver(Pre_Condition_Register_Email_And_Login.url, browserName, browserVersion, environmentName, ipAddress, port, platform);
        loginPage = PageGeneratorManager.getPageGeneratorManager().getLoginPage(driver);

        String userId = Pre_Condition_Register_Email_And_Login.userId;
        String password = Pre_Condition_Register_Email_And_Login.password;

        log.info("Pre condition - Login with share data - Step 01: Login with userId " + userId + " password " + password);
        loginPage.inputToUserIdTextbox(driver, userId);
        loginPage.inputToPasswordTextbox(driver, password);
        managerHomePage = loginPage.clickToLoginButton(driver);

        log.info("Pre condition - Login with share data - Step 02: Verify that login successfully");
        verifyTrue(managerHomePage.isLoginSuccessTextDisplayed(driver));

        menuSub = CommonText.getCommonText().getEditCustomerMenuSub();
        editCustomerPage = managerHomePage.navigateToEditCustomer(driver, menuSub);

        log.info("Pre Condition - Login with share data - Step 04: Input valid customer id");
        String validCustomerId = "68818";
        String customerIdFieldName = "cusid";
        editCustomerPage.inputToTextboxByDynamicLocator(driver, validCustomerId, customerIdFieldName);

        log.info("Pre Condition - Login with share data - Step 05: Click submit button");
        editCustomerPage.clickSubmitButton(driver);
        editCustomerFormPage = PageGeneratorManager.getPageGeneratorManager().getEditCustomerFormPage(driver);

        log.info("Pre Condition - Login with share data - Step 06: Verify that edit customer is displayed");
        verifyTrue(editCustomerFormPage.isEditCustomerHeadingDisplayed(driver));

        emailFieldName = "emailid";
        //test data
        invalidEmails = new String[]{"son99@gmail", "son99", "son99@", "@gmail.com", "son99@.com", "son99gmail.com"};

    }

    @Test(description = "Edit Customer - Verify that email field can not be empty")
    public void TC_01_Empty_Email() {
        log.info("TC_01_Empty_Email - Step 01: Clear email field");
        editCustomerFormPage.inputToTextboxByDynamicLocator(driver, "", emailFieldName);

        log.info("TC_01_Empty_Email - Step 02: Verify that message 'Email-ID must not be blank' is displayed");
        verifyTrue(editCustomerPage.isValidationErrorMessageDisplayed(driver, emailFieldName, "Email-ID must not be blank"));
    }

    @Test(description = "Edit Customer - Verify that email must be correct format")
    public void TC_02_Invalid_Email() {
        for (String invalidEmail: invalidEmails) {
            log.info("TC_02_Invalid_Email - Step 01: Input email = " + invalidEmail);
            editCustomerFormPage.inputToTextboxByDynamicLocator(driver, invalidEmail, emailFieldName);

            log.info("TC_02_Invalid_Email - Step 02: Verify that message 'Email-ID is not valid' is displayed");
            verifyTrue(editCustomerPage.isValidationErrorMessageDisplayed(driver, emailFieldName, "Email-ID is not valid"));
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }
}
