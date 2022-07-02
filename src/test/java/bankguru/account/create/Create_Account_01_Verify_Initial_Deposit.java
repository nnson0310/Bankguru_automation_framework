package bankguru.account.create;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import commons.CommonText;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.*;

public class Create_Account_01_Verify_Initial_Deposit extends BaseTest {
    WebDriver driver;

    String menuSub, initialDepositFieldName;
    String[] charContainInitialDeposits, specialCharInitialDeposits;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
    CreateAccountPage createAccountPage;

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

        menuSub = CommonText.getCommonText().getNewAccountMenuSub();
        createAccountPage = managerHomePage.navigateToCreateAccountPage(driver, menuSub);

        initialDepositFieldName = "inideposit";

        //test data
        charContainInitialDeposits = new String[]{ "123ABC", "ABV445" };
        specialCharInitialDeposits = new String[]{ "123!@#", "4$%^44" };

    }

    @Test(description = "TC_01_Empty_Initial_Deposit - Verify that initial deposit field can not empty")
    public void TC_01_Empty_Initial_Deposit() {
        log.info("TC_01_Empty_Initial_Deposit - Step 01: Clear initial deposit field");
        createAccountPage.inputToTextboxByDynamicLocator(driver, "", initialDepositFieldName);

        log.info("TC_01_Empty_Initial_Deposit - Step 02: Press tab button");
        createAccountPage.pressTabButton(driver);

        log.info("TC_01_Empty_Initial_Deposit - Step 03: Verify that validation error message 'Initial Deposit must not be blank' is displayed");
        createAccountPage.isValidationErrorMessageDisplayed(driver, initialDepositFieldName, "Initial Deposit must not be blank");
    }

    @Test(description = "TC_02_Must_Be_Numeric - Verify that initial deposit field must be numeric")
    public void TC_02_Must_Be_Numeric() {
       for(String charContainInitialDeposit: charContainInitialDeposits) {
           log.info("TC_02_Must_Be_Numeric - Step 01: Enter initial deposit = " + charContainInitialDeposit);
           createAccountPage.inputToTextboxByDynamicLocator(driver, charContainInitialDeposit, initialDepositFieldName);

           log.info("TC_02_Must_Be_Numeric - Step 02: Verify that validation error message 'Characters are not allowed' is displayed");
           createAccountPage.isValidationErrorMessageDisplayed(driver, initialDepositFieldName, "Characters are not allowed");
       }
    }

    @Test(description = "TC_03_Contain_Special_Characters - Verify that initial deposit can not contain special chars")
    public void TC_03_Contain_Special_Characters() {
        for(String specialCharInitialDeposit: specialCharInitialDeposits) {
            log.info("TC_03_Contain_Special_Characters - Step 01: Enter initial deposit = " + specialCharInitialDeposit);
            createAccountPage.inputToTextboxByDynamicLocator(driver, specialCharInitialDeposit, initialDepositFieldName);

            log.info("TC_03_Contain_Special_Characters - Step 02: Verify that validation error message 'Special characters are not allowed' is displayed");
            createAccountPage.isValidationErrorMessageDisplayed(driver, initialDepositFieldName, "Special characters are not allowed");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }

}
