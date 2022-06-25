package bankguru.customer.create;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageinterfaces.CommonText;
import pageobjects.CreateCustomerPage;
import pageobjects.LoginPage;
import pageobjects.ManagerHomePage;
import pageobjects.PageGeneratorManager;

public class TC_01_Verify_Name_Field extends BaseTest {

    WebDriver driver;

    PageGeneratorManager pageGeneratorManager;
    private String[] numericNames, specialCharNames;

    String menuSub;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
    CreateCustomerPage createCustomerPage;

    @Parameters({"browserName", "browserVersion", "environmentName", "ipAddress", "port", "platform"})
    @BeforeClass(description = "Create customer - TC_01_Verify_Name_Field")
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

        //test data
        numericNames = new String[]{"33333", "hoa567"};
        specialCharNames = new String[]{"name!@#", "**^%"};
    }

    @Test(description = "Verify that Name can not be empty")
    public void TC_Empty_Name() {

        log.info("TC_Empty_Name - Step 01: Navigate to add_new_customer page");
        createCustomerPage = managerHomePage.navigateToCreateCustomerPage(driver, menuSub);

        log.info("TC_Empty_Name - Step 02: Clear customer name field");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "", "name");

        log.info("TC_Empty_Name - Step 03: Press TAB button");
        createCustomerPage.pressTabButton(driver);

        log.info("TC_Empty_Name - Step 04: Verify that error message \"Customer name must not be blank\" is displayed");
        verifyTrue(createCustomerPage.isValidationErrorMessageDisplayed(driver, "Customer name must not be blank"));
    }

    @Test(description = "Verify that Name can not be numeric")
    public void TC_Numeric_Name() {

        for(String numericName : numericNames) {
            log.info("TC_Numeric_Name - Step 01: Enter numeric value in Name field with value = " + numericName);
            createCustomerPage.inputToTextboxByDynamicLocator(driver, numericName, "name");

            log.info("TC_Numeric_Name - Step 02: Verify that error message \"Numbers are not allowed\" is displayed");
            verifyTrue(createCustomerPage.isValidationErrorMessageDisplayed(driver, "Numbers are not allowed"));
        }
    }

    @Test(description = "Verify that Name can not contain special characters")
    public void TC_Special_Chars_Name() {

        for(String specialCharName : specialCharNames) {
            log.info("TC_Special_Chars_Name - Step 01: Enter special chars in Name field with value = " + specialCharName);
            createCustomerPage.inputToTextboxByDynamicLocator(driver, specialCharName, "name");

            log.info("TC_Special_Chars_Name - Step 02: Verify that error message \"Special characters are not allowed\" is displayed");
            verifyTrue(createCustomerPage.isValidationErrorMessageDisplayed(driver, "Special characters are not allowed"));
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }
}
