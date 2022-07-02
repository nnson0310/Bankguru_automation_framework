package bankguru.customer.delete;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import commons.CommonText;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.*;
import utilities.FunctionHelper;

public class Delete_Customer_01_Verify_Customer_Id extends BaseTest {
    WebDriver driver;

    String menuSub, customerIdFieldName;
    String nonExistentCustomerId, validCustomerId;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
    DeleteCustomerPage deleteCustomerPage;
    CreateCustomerPage createCustomerPage;
    CreateCustomerSuccessPage createCustomerSuccessPage;

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

        log.info("Pre Condition - Login with share data - Step 01: Login with userId " + userId + " password " + password);
        loginPage.inputToUserIdTextbox(driver, userId);
        loginPage.inputToPasswordTextbox(driver, password);
        managerHomePage = loginPage.clickToLoginButton(driver);

        log.info("Pre Condition - Login with share data - Step 02: Verify that login successfully");
        verifyTrue(managerHomePage.isLoginSuccessTextDisplayed(driver));

        log.info("Pre Condition - Login with share data - Step 03: Navigate to new customer page");
        menuSub = CommonText.getCommonText().getNewCustomerMenuSub();
        createCustomerPage = managerHomePage.navigateToCreateCustomerPage(driver, menuSub);

        log.info("Pre Condition - Login with share data - Step 04: Create new customer");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "Son", "name");
        createCustomerPage.clickToGenderRadioButton(driver , "m");
        createCustomerPage.inputToDateOfBirthField(driver, FunctionHelper.getToday());
        createCustomerPage.inputToAddressField(driver, "120 Sydney");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "Ha Noi", "city");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "Yukawa", "state");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "444555", "pinno");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "0877654555", "telephoneno");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "son0993@gmail.com", "emailid");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "123456", "password");
        createCustomerPage.clickSubmitButton(driver);
        createCustomerSuccessPage = PageGeneratorManager.getPageGeneratorManager().getCreateCustomerSuccessPage(driver);

        log.info("Pre Condition - Login with share data - Step 05: Verify that new customer is created successfully");
        createCustomerSuccessPage.isRegisterSuccessMsgDisplayed(driver, "Customer Registered Successfully");
        validCustomerId = createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "Customer ID");

        log.info("Pre Condition - Login with share data - Step 03: Navigate to delete customer page");
        menuSub = CommonText.getCommonText().getDeleteCustomerMenuSub();
        deleteCustomerPage = createCustomerSuccessPage.navigateToDeleteCustomer(driver, menuSub);

        //test data
        customerIdFieldName = "cusid";

    }

    @Test(description = "Delete Customer - Verify that user can delete customer if customerId does exist")
    public void TC_01_Valid_Customer_Id() {
        log.info("TC_01_Valid_Customer_Id - Step 01: Input valid customer id = " + validCustomerId);
        deleteCustomerPage.inputToTextboxByDynamicLocator(driver, validCustomerId, customerIdFieldName);

        log.info("TC_01_Valid_Customer_Id - Step 02: Click submit button");
        deleteCustomerPage.clickToSubmitButton(driver);

        log.info("TC_01_Valid_Customer_Id - Step 03: Verify that pop up with message 'Do you really want to delete this Customer?' is displayed");
        verifyEquals(deleteCustomerPage.getPopupMessage(), "Do you really want to delete this Customer?");
        FunctionHelper.sleepInSeconds(1);

    }

    @Test(description = "Delete Customer - Verify that can not delete customer's id which does not exist")
    public void TC_02_Non_Existent_Customer_Id() {
        log.info("TC_02_Non_Existent_Customer_Id - Step 01: Input non-existent customerId = " + validCustomerId);
        deleteCustomerPage.inputToTextboxByDynamicLocator(driver, validCustomerId, customerIdFieldName);

        log.info("TC_02_Non_Existent_Customer_Id - Step 02: Click submit button");
        deleteCustomerPage.clickToSubmitButton(driver);

        log.info("TC_02_Non_Existent_Customer_Id - Step 03: Verify that pop up with message 'Do you really want to delete this Customer?' is displayed");
        verifyEquals(deleteCustomerPage.getPopupMessage(), "Do you really want to delete this Customer?");

        log.info("TC_02_Non_Existent_Customer_Id - Step 04: Verify that pop up with message 'Customer does not exist!!' is displayed");
        verifyEquals(deleteCustomerPage.getPopupMessage(), "Customer does not exist!!");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }
}
