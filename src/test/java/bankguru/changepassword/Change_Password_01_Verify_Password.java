package bankguru.changepassword;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import commons.CommonText;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.*;

public class Change_Password_01_Verify_Password extends BaseTest {
    WebDriver driver;

    String menuSub;
    String oldPasswordFieldName, newPasswordFieldName, confirmPasswordFieldName;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
    ChangePasswordPage changePasswordPage;

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

        menuSub = CommonText.getCommonText().getChangePasswordMenuSub();

        log.info("Pre condition - Login with share data - Step 03: Navigate to new account page");
        changePasswordPage = managerHomePage.navigateToChangePasswordPage(driver, menuSub);

        oldPasswordFieldName = "oldpassword";
        newPasswordFieldName = "newpassword";
        confirmPasswordFieldName = "confirmpassword";

    }

    @Test(description = "TC_01_Empty_Old_Password - Verify that old password can not be empty")
    public void TC_01_Empty_Old_Password() {

        log.info("TC_01_Empty_Old_Password - Step 01: Clear old password field");
        changePasswordPage.inputToTextboxByDynamicLocator(driver, "", oldPasswordFieldName);

        log.info("TC_01_Empty_Old_Password - Step 02: Press Tab button");
        changePasswordPage.pressTabButton(driver);

        log.info("TC_01_Empty_Old_Password - Step 03: Verify that validation error message 'Old Password must not be blank' id displayed ");
        verifyFalse(changePasswordPage.isValidationErrorMessageDisplayed(driver, oldPasswordFieldName, "Old Password must not be blank"));
    }

//    @Test(description = "TC_02_Empty_New_Password - Verify that new password can not be empty")
//    public void TC_02_Empty_New_Password() {
//
//        log.info("TC_02_Empty_New_Password - Step 01: Clear new password field");
//        changePasswordPage.inputToTextboxByDynamicLocator(driver, "", newPasswordFieldName);
//
//        log.info("TC_02_Empty_New_Password - Step 02: Press Tab button");
//        changePasswordPage.pressTabButton(driver);
//
//        log.info("TC_02_Empty_New_Password - Step 03: Verify that validation error message 'New Password must not be blank' id displayed ");
//        verifyTrue(changePasswordPage.isValidationErrorMessageDisplayed(driver, newPasswordFieldName, "New Password must not be blank"));
//    }
//
//    @Test(description = "TC_03_Must_Have_Numeric_Value - Verify that new password must have at least one numeric value")
//    public void TC_03_Must_Have_Numeric_Value() {
//
//        String newPassword = "haha!@#";
//        log.info("TC_03_Must_Have_Numeric_Value - Step 01: Input new password = " + newPassword);
//        changePasswordPage.inputToTextboxByDynamicLocator(driver, newPassword, newPasswordFieldName);
//
//        log.info("TC_03_Must_Have_Numeric_Value - Step 03: Verify that validation error message 'Enter at-least one numeric value' id displayed ");
//        verifyTrue(changePasswordPage.isValidationErrorMessageDisplayed(driver, newPasswordFieldName, "Enter at-least one numeric value"));
//    }
//
//    @Test(description = "TC_04_Have_Password_String - Verify that new password can not have 'password' string")
//    public void TC_04_Have_Password_String() {
//
//        String newPassword = "password123@";
//
//        log.info("TC_04_Have_Password_String - Step 01: Input new password = " + newPassword);
//        changePasswordPage.inputToTextboxByDynamicLocator(driver, newPassword, newPasswordFieldName);
//
//        log.info("TC_04_Have_Password_String - Step 03: Verify that validation error message 'Choose a difficult Password' id displayed ");
//        verifyTrue(changePasswordPage.isValidationErrorMessageDisplayed(driver, newPasswordFieldName, "Choose a difficult Password"));
//    }
//
//    @Test(description = "TC_05_Confirm_Password_Unmatch - Verify that password and confirm_password have to be the same")
//    public void TC_05_Confirm_Password_Unmatch() {
//
//        String newPassword = "haha123!";
//        String confirmPassword = "haha123@";
//
//        log.info("TC_05_Confirm_Password_Unmatch - Step 01: Input new password = " + newPassword);
//        changePasswordPage.inputToTextboxByDynamicLocator(driver, newPassword, newPasswordFieldName);
//
//        log.info("TC_05_Confirm_Password_Unmatch - Step 02: Input confirm password = " + confirmPassword);
//        changePasswordPage.inputToTextboxByDynamicLocator(driver, confirmPassword, confirmPasswordFieldName);
//
//        log.info("TC_05_Confirm_Password_Unmatch - Step 03: Verify that validation error message 'Passwords do not Match' id displayed ");
//        verifyTrue(changePasswordPage.isValidationErrorMessageDisplayed(driver, confirmPasswordFieldName, "Passwords do not Match"));
//    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }

}
