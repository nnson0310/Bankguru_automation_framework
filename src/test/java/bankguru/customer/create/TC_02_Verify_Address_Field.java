package bankguru.customer.create;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageinterfaces.CommonText;
import pageobjects.CreateCustomerPage;
import pageobjects.LoginPage;
import pageobjects.ManagerHomePage;
import pageobjects.PageGeneratorManager;

public class TC_02_Verify_Address_Field extends BaseTest {
    WebDriver driver;

    PageGeneratorManager pageGeneratorManager;

    String menuSub, inputName;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
    CreateCustomerPage createCustomerPage;

    @Parameters({"browserName", "browserVersion", "environmentName", "ipAddress", "port", "platform"})
    @BeforeClass(description = "Create customer - TC_02_Verify_Address_Field")
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
        inputName = "addr";

    }

    @Test(description = "Verify that Address can not be empty")
    public void TC_Empty_Address() {

        log.info("TC_Empty_Address - Step 01: Navigate to add_new_customer page");
        createCustomerPage = managerHomePage.navigateToCreateCustomerPage(driver, menuSub);

        log.info("TC_Empty_Address - Step 02: Clear address field");
        createCustomerPage.inputToAddressField(driver, "");

        log.info("TC_Empty_Address - Step 03: Press TAB button");
        createCustomerPage.pressTabButton(driver);

        log.info("TC_Empty_Address - Step 04: Verify that error message \"Address Field must not be blank\" is displayed");
        verifyTrue(createCustomerPage.isAddressFieldValidationErrorMessageDisplayed(driver, inputName, "Address Field must not be blank"));

    }

    @Test(description = "Verify that Address's first char can not be blank space")
    public void TC_First_Char_Blank_Space_Address() {

        log.info("TC_First_Char_Blank_Space_Address - Step 01: Clear address field");
        createCustomerPage.inputToAddressField(driver, "");

        log.info("TC_First_Char_Blank_Space_Address - Step 02: Press space button");
        createCustomerPage.pressSpaceButton(driver);

        log.info("TC_First_Char_Blank_Space_Address - Step 03: Verify that error message \"First character can not have space\" is displayed");
        verifyTrue(createCustomerPage.isAddressFieldValidationErrorMessageDisplayed(driver, inputName, "First character can not have space"));

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }
}
