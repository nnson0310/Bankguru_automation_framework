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

public class Create_Customer_03_Verify_Pin_Field extends BaseTest {
    WebDriver driver;

    String menuSub, inputName;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
    CreateCustomerPage createCustomerPage;

    private String[] characterPins, digitPins, specialCharPins;

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

        menuSub = CommonText.getCommonText().getNewCustomerMenuSub();
        inputName = "pinno";

        //test data
        characterPins = new String[]{"PIN", "12PIN"};
        digitPins = new String[]{"123", "12"};
        specialCharPins = new String[]{"!@123", "!!#@%"};

    }

    @Test(description = "Verify that Pin field must be numeric")
    public void TC_01_Must_Be_Numeric() {

        log.info("TC_01_Must_Be_Numeric - Step 01: Navigate to add_new_customer page");
        createCustomerPage = managerHomePage.navigateToCreateCustomerPage(driver, menuSub);

        for(String characterPin : characterPins) {
            log.info("TC_01_Must_Be_Numeric - Step 02: Enter characters value into Pin field = " + characterPin);
            createCustomerPage.inputToTextboxByDynamicLocator(driver, characterPin, inputName);

            log.info("TC_01_Must_Be_Numeric - Step 03: Verify that error message \"Characters are not allowed\" is displayed");
            verifyTrue(createCustomerPage.isValidationErrorMessageDisplayed(driver, inputName,"Characters are not allowed"));
        }

    }

    @Test(description = "Verify that Pin field can not be empty")
    public void TC_02_Empty_Pin() {

        log.info("TC_02_Empty_Pin - Step 01: Do not enter any value into pin field");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "", inputName);

        log.info("TC_02_Empty_Pin - Step 02: Press TAB button");
        createCustomerPage.pressTabButton(driver);

        log.info("TC_02_Empty_Pin - Step 03: Verify that error message \"PIN Code must not be blank\" is displayed");
        verifyTrue(createCustomerPage.isValidationErrorMessageDisplayed(driver, inputName,"PIN Code must not be blank"));

    }

    @Test(description = "Verify that Pin field must contains 6 digits")
    public void TC_03_Must_Contains_Six_Digits() {

        for(String digitPin : digitPins) {
            log.info("TC_03_Must_Contains_Six_Digits - Step 01: Enter pin contains less than or more than 6 digits = " + digitPin);
            createCustomerPage.inputToTextboxByDynamicLocator(driver, digitPin, inputName);

            log.info("TC_03_Must_Contains_Six_Digits - Step 02: Verify that error message \"PIN Code must have 6 Digits\" is displayed");
            verifyTrue(createCustomerPage.isValidationErrorMessageDisplayed(driver, inputName,"PIN Code must have 6 Digits"));
        }

    }

    @Test(description = "Verify that Pin field can not contain special chars")
    public void TC_04_Special_Chars() {

        for(String specialCharPin : specialCharPins) {
            log.info("TC_04_Special_Chars - Step 01: Enter special chars into Pin field = " + specialCharPin);
            createCustomerPage.inputToTextboxByDynamicLocator(driver, specialCharPin, inputName);

            log.info("TC_04_Special_Chars - Step 02: Verify that error message \"Special characters are not allowed\" is displayed");
            verifyTrue(createCustomerPage.isValidationErrorMessageDisplayed(driver, inputName,"Special characters are not allowed"));
        }

    }

    @Test(description = "Verify that Pin field can not contain blank space as first char")
    public void TC_05_Bank_Space_As_First_Char() {

        log.info("TC_05_Bank_Space_As_First_Char - Step 01: Clear value of Pin field");
        createCustomerPage.inputToTextboxByDynamicLocator(driver, "", inputName);

        log.info("TC_05_Bank_Space_As_First_Char - Step 02: Enter blank space as first char into Pin field");
        createCustomerPage.pressSpaceButton(driver);

        log.info("TC_05_Bank_Space_As_First_Char - Step 02: Verify that error message \"First character can not have space\" is displayed");
        verifyTrue(createCustomerPage.isValidationErrorMessageDisplayed(driver, inputName,"First character can not have space"));

        log.info("TC_05_Bank_Space_As_First_Char - Step 03: Logout");
        createCustomerPage.clickLogoutMenuSub(driver, "Log out");

    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }
}
