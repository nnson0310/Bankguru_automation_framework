package bankguru.deposit;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import commons.CommonText;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageobjects.*;

public class Deposit_01_Verify_Account extends BaseTest {
    WebDriver driver;

    String menuSub;
    String customerId, initialDeposit, accountType;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
    CreateAccountPage createAccountPage;
    DepositPage depositPage;

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

        log.info("Pre condition - Login with share data - Step 01: Login with userId = " + userId + " and password =" + password);
        loginPage.inputToUserIdTextbox(driver, userId);
        loginPage.inputToPasswordTextbox(driver, password);
        managerHomePage = loginPage.clickToLoginButton(driver);

        log.info("Pre condition - Login with share data - Step 02: Verify that login successfully");
        verifyTrue(managerHomePage.isLoginSuccessTextDisplayed(driver));

        menuSub = CommonText.getCommonText().getNewAccountMenuSub();

        log.info("Pre condition - Create new account - Step 01: Navigate to new account page");
        createAccountPage = managerHomePage.navigateToCreateAccountPage(driver, menuSub);

        customerId = "68818";
        initialDeposit = "10000000";
        accountType = "Current";

        log.info("Pre condition - Create new account - Step 02: Input customer id = " + customerId);
        createAccountPage.inputToTextboxByDynamicLocator(driver, customerId, "cusid");

        log.info("Pre condition - Create new account - Step 03: Select account type = " + accountType);
        createAccountPage.selectAccountType(driver, accountType);

        log.info("Pre condition - Create new account - Step 04: Input initial deposit = " + initialDeposit);
        createAccountPage.inputToTextboxByDynamicLocator(driver, initialDeposit, "inideposit");

        log.info("Pre condition - Create new account - Step 04: Input initial deposit = " + initialDeposit);
        createAccountPage.clickToSubmitButton(driver);

        log.info("Pre condition - Create new account - Step 05: Verify that 'Account Generated Successfully!!!' is displayed ");
        verifyTrue(createAccountPage.isCreateAccountSuccessMessageDisplayed(driver, "Account Generated Successfully!!!"));

        log.info("Pre condition - Create new account - Step 06: Navigate to deposit page");
        depositPage = managerHomePage.navigateToDepositPage(driver, menuSub);

    }

}
