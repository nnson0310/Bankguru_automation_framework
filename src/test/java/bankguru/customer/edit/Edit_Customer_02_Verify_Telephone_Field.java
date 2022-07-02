package bankguru.customer.edit;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import commons.CommonText;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageobjects.*;

public class Edit_Customer_02_Verify_Telephone_Field extends BaseTest {
    WebDriver driver;

    String menuSub, telephoneFieldName;
    String[] specialCharTelephones;

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

        log.info("Pre Condition - Login with share data - Step 01: Login with userId " + userId + " password " + password);
        loginPage.inputToUserIdTextbox(driver, userId);
        loginPage.inputToPasswordTextbox(driver, password);
        managerHomePage = loginPage.clickToLoginButton(driver);

        log.info("Pre Condition - Login with share data - Step 02: Verify that login successfully");
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

        telephoneFieldName = "telephoneno";

        //test data
        specialCharTelephones = new String[]{"888!@12", "@#11111", "876%#$99"};

    }

    @Test(description = "Edit Customer - Verify that telephone field can not be empty")
    public void TC_01_Empty_Telephone() {
        log.info("TC_01_Empty_Telephone - Step 01: Clear mobile number field");
        editCustomerFormPage.inputToTextboxByDynamicLocator(driver, "", telephoneFieldName);

        log.info("TC_01_Empty_Telephone - Step 02: Verify that message 'Mobile no must not be blank' is displayed");
        verifyTrue(editCustomerPage.isValidationErrorMessageDisplayed(driver, telephoneFieldName, "Mobile no must not be blank"));
    }

    @Test(description = "Edit Customer - Verify that telephone field can not contain special characters")
    public void TC_02_Contains_Special_Character() {

        for (String specialCharTelephone: specialCharTelephones) {
            log.info("TC_02_Contains_Special_Character - Step 01: Input telephone which contains special chars = " + specialCharTelephone);
            editCustomerFormPage.inputToTextboxByDynamicLocator(driver, specialCharTelephone, telephoneFieldName);

            log.info("TC_02_Contains_Special_Character - Step 02: Verify that message 'Special characters are not allowed' is displayed");
            verifyTrue(editCustomerPage.isValidationErrorMessageDisplayed(driver, telephoneFieldName, "Special characters are not allowed"));
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }
}
