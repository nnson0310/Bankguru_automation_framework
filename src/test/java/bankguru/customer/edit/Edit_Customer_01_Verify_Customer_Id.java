package bankguru.customer.edit;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import commons.CommonText;
import pageobjects.*;

public class Edit_Customer_01_Verify_Customer_Id extends BaseTest {
    WebDriver driver;

    String menuSub, customerIdField, customerNameField, emailField;
    String[] numericCustomerIds, specialCharCustomerIds;
    String validCustomerId, registeredCustomerName, registeredCustomerEmail;

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
        customerIdField = "cusid";
        customerNameField = "name";
        emailField = "emailid";

        //test data
        numericCustomerIds = new String[]{"1234AC", "Acc123"};
        specialCharCustomerIds = new String[]{"123@#", "!@3#"};
        validCustomerId = "68818";
        registeredCustomerName = "huong";
        registeredCustomerEmail = "saota@gmail.com";

    }

    @Test(description = "Edit Customer - Verify that customer id can not be null")
    public void TC_01_Empty_Customer_Id() {

        log.info("TC_01_Empty_Customer_Id - Step 01: Navigate to edit customer page");
        editCustomerPage = managerHomePage.navigateToEditCustomer(driver, menuSub);

        log.info("TC_01_Empty_Customer_Id - Step 02: Clear customer id field");
        editCustomerPage.inputToTextboxByDynamicLocator(driver, "", customerIdField);

        log.info("TC_01_Empty_Customer_Id - Step 03: Press TAB button");
        editCustomerPage.pressTabButton(driver);

        log.info("TC_01_Empty_Customer_Id - Step 04: Verify that 'Customer ID is required' message is displayed");
        editCustomerPage.isValidationErrorMessageDisplayed(driver, customerIdField, "Customer ID is required");

    }

    @Test(description = "Edit Customer - Verify that customer id must be numeric")
    public void TC_02_Numeric_Customer_Id() {

        for(String numericCustomerId : numericCustomerIds) {
            log.info("TC_02_Numeric_Customer_Id - Step 01: Enter alphabetic chars in Customer Id field with value = " + numericCustomerId);
            editCustomerPage.inputToTextboxByDynamicLocator(driver, numericCustomerId, customerIdField);

            log.info("TC_02_Numeric_Customer_Id - Step 02: Verify that error message \"Characters are not allowed\" is displayed");
            verifyTrue(editCustomerPage.isValidationErrorMessageDisplayed(driver, customerIdField,"Characters are not allowed"));
        }

    }

    @Test(description = "Edit Customer - Verify that customer id can not contain special chars")
    public void TC_03_Special_Chars_Customer_Id() {

        for(String specialCharCustomerId : specialCharCustomerIds) {
            log.info("TC_03_Special_Chars_Customer_Id - Step 01: Enter special chars in Customer Id field with value = " + specialCharCustomerId);
            editCustomerPage.inputToTextboxByDynamicLocator(driver, specialCharCustomerId, customerIdField);

            log.info("TC_03_Special_Chars_Customer_Id - Step 02: Verify that error message \"Special characters are not allowed\" is displayed");
            verifyTrue(editCustomerPage.isValidationErrorMessageDisplayed(driver, customerIdField,"Special characters are not allowed"));
        }

    }

    @Test(description = "Edit Customer - Enter valid customer id")
    public void TC_04_Valid_Customer_Id() {

        log.info("TC_04_Valid_Customer_Id - Step 01: Enter valid customer id = " + validCustomerId);
        editCustomerPage.inputToTextboxByDynamicLocator(driver, validCustomerId, customerIdField);

        log.info("TC_04_Valid_Customer_Id - Step 02: Click submit button");
        editCustomerPage.clickSubmitButton(driver);
        editCustomerFormPage = PageGeneratorManager.getPageGeneratorManager().getEditCustomerFormPage(driver);

        log.info("TC_04_Valid_Customer_Id - Step 03: Verify that edit customer page is displayed");
        verifyTrue(editCustomerFormPage.isEditCustomerHeadingDisplayed(driver));

        log.info("TC_04_Valid_Customer_Id - Step 04: Verify that customer name = " + registeredCustomerName);
        verifyEquals(editCustomerFormPage.getFieldValueByDynamicLocator(driver, "value", customerNameField), registeredCustomerName);

        log.info("TC_05_Valid_Customer_Id - Step 04: Verify that customer email = " + registeredCustomerEmail);
        verifyEquals(editCustomerFormPage.getFieldValueByDynamicLocator(driver, "value", emailField), registeredCustomerEmail);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }
}
