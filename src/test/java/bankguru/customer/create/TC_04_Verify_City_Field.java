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

public class TC_04_Verify_City_Field extends BaseTest {
    WebDriver driver;

    PageGeneratorManager pageGeneratorManager;

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

    }

    @Test(description = "Verify that Name can not be empty")
    public void TC_Empty_Name() {

        log.info("TC_Empty_Name - Step 01: Navigate to add_new_customer page");
        createCustomerPage = managerHomePage.navigateToCreateCustomerPage(driver, menuSub);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        log.info("TC_Special_Chars_Name - Logout");
        createCustomerPage.clickToMenuSubByDynamicLocator(driver, "Log out");

        closeBrowserAndKillProcess();
    }
}
