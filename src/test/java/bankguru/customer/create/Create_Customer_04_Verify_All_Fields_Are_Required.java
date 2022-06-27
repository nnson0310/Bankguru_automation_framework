package bankguru.customer.create;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import commons.CommonText;
import pageobjects.CreateCustomerPage;
import pageobjects.LoginPage;
import pageobjects.ManagerHomePage;
import pageobjects.PageGeneratorManager;
import utilities.DataFaker;

public class Create_Customer_04_Verify_All_Fields_Are_Required extends BaseTest {
    WebDriver driver;

    String menuSub, customerNameInput, customerName;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
    CreateCustomerPage createCustomerPage;

    @Parameters({"browserName", "browserVersion", "environmentName", "ipAddress", "port", "platform"})
    @BeforeClass(description = "Create_Customer_04_Verify_All_Fields_Are_Required")
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

        menuSub = CommonText.getCommonText().getNewCustomerMenuSub();
        customerNameInput = "name";

        //test data
        customerName = DataFaker.getDataFaker().generateFullName();

    }

    @Test(description = "Click submit button when all fields are empty")
    public void TC_01_All_Fields_Are_Empty() {

        log.info("TC_01_All_Fields_Are_Empty - Step 01: Navigate to add_new_customer page");
        createCustomerPage = managerHomePage.navigateToCreateCustomerPage(driver, menuSub);

        log.info("TC_01_All_Fields_Are_Empty - Step 02: Click submit button");
        createCustomerPage.clickSubmitButton(driver);

        log.info("TC_01_All_Fields_Are_Empty - Step 03: Verify that alert will be displayed");
        verifyEquals(createCustomerPage.getAlertValidationMessage(driver), "please fill all fields");

    }

    @Test(description = "Click submit button when all fields are empty except customer name")
    public void TC_02_All_Empty_Except_Customer_Name() {

        log.info("TC_02_All_Empty_Except_Customer_Name - Step 01: Enter value into customer name field");
        createCustomerPage.inputToTextboxByDynamicLocator(driver,  customerName, customerNameInput);

        log.info("TC_02_All_Empty_Except_Customer_Name - Step 02: Click submit button");
        createCustomerPage.clickSubmitButton(driver);

        log.info("TC_02_All_Empty_Except_Customer_Name - Step 03: Verify that alert will be displayed");
        verifyEquals(createCustomerPage.getAlertValidationMessage(driver), "please fill all fields");

        log.info("TC_02_All_Empty_Except_Customer_Name - Step 04: Logout");
        createCustomerPage.clickLogoutMenuSub(driver, "Log out");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        closeBrowserAndKillProcess();
    }

}
