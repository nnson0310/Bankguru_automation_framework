package bankguru.customer.create;

import bankguru.Pre_Condition_Register_Email_And_Login;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import commons.CommonText;
import pageobjects.*;
import utilities.DataFaker;
import utilities.FunctionHelper;

import java.text.ParseException;
import java.util.Date;

public class Create_Customer_05_Valid_Customer_Info extends BaseTest {
    WebDriver driver;

    String menuSub;

    public String customerId, customerName, customerPass;
    public String gender, genderValue, dateOfBirth, address, city, state, pin, mobileNumber, email;

    LoginPage loginPage;
    ManagerHomePage managerHomePage;
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

        log.info("Pre condition - Login with share data - Step 01: Login with userId = " + userId + " password " + password);
        loginPage.inputToUserIdTextbox(driver, userId);
        loginPage.inputToPasswordTextbox(driver, password);
        managerHomePage = loginPage.clickToLoginButton(driver);

        log.info("Pre condition - Login with share data - Step 02: Verify that login successfully");
        verifyTrue(managerHomePage.isLoginSuccessTextDisplayed(driver));

        menuSub = CommonText.getCommonText().getNewCustomerMenuSub();

        //test data
        customerName = DataFaker.getDataFaker().generateFullName();
        genderValue = "m";
        gender = "male";
        dateOfBirth = FunctionHelper.getToday();
        address = "12 Ho Chi Minh City";
        city = "Sydney";
        state = "Khanh Hoa";
        pin = "333444";
        mobileNumber = "0977045663";
        email = DataFaker.getDataFaker().generateEmail();
        customerPass = "123456";

    }

    @Test(description = "Click submit button when all fields are empty")
    public void TC_01_Create_Customer_Success() throws ParseException {

        log.info("TC_01_Create_Customer_Success - Step 01: Navigate to add_new_customer page");
        createCustomerPage = managerHomePage.navigateToCreateCustomerPage(driver, menuSub);

        log.info("TC_01_Create_Customer_Success - Step 02: Enter Customer Name = " + customerName);
        createCustomerPage.inputToTextboxByDynamicLocator(driver, customerName, "name");

        log.info("TC_01_Create_Customer_Success - Step 03: Select gender = " + genderValue);
        createCustomerPage.clickToGenderRadioButton(driver, genderValue);

        log.info("TC_01_Create_Customer_Success - Step 04: Enter Date of birth = " + dateOfBirth);
        createCustomerPage.inputToDateOfBirthField(driver, dateOfBirth);

        log.info("TC_01_Create_Customer_Success - Step 05: Enter address = " + address);
        createCustomerPage.inputToAddressField(driver, address);

        log.info("TC_01_Create_Customer_Success - Step 06: Enter city = " + city);
        createCustomerPage.inputToTextboxByDynamicLocator(driver, city, "city");

        log.info("TC_01_Create_Customer_Success - Step 07: Enter state = " + state);
        createCustomerPage.inputToTextboxByDynamicLocator(driver, state, "state");

        log.info("TC_01_Create_Customer_Success - Step 08: Enter pin = " + pin);
        createCustomerPage.inputToTextboxByDynamicLocator(driver, pin, "pinno");

        log.info("TC_01_Create_Customer_Success - Step 09: Enter mobile number = " + mobileNumber);
        createCustomerPage.inputToTextboxByDynamicLocator(driver, mobileNumber, "telephoneno");

        log.info("TC_01_Create_Customer_Success - Step 10: Enter email = " + email);
        createCustomerPage.inputToTextboxByDynamicLocator(driver, email, "emailid");

        log.info("TC_01_Create_Customer_Success - Step 11: Enter password = " + customerPass);
        createCustomerPage.inputToTextboxByDynamicLocator(driver, customerPass, "password");

        log.info("TC_01_Create_Customer_Success - Step 12: Click submit button");
        createCustomerPage.clickSubmitButton(driver);

        createCustomerSuccessPage = PageGeneratorManager.getPageGeneratorManager().getCreateCustomerSuccessPage(driver);

        log.info("TC_01_Create_Customer_Success - Step 13: Verify that customer is created successfully");
        verifyTrue(createCustomerSuccessPage.isRegisterSuccessMsgDisplayed(driver, "Customer Registered Successfully"));

        log.info("TC_01_Create_Customer_Success - Step 14: Verify that customer name = " + customerName + " is the same");
        verifyEquals(createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "Customer Name"), customerName);

        log.info("TC_01_Create_Customer_Success - Step 15: Verify that gender = " + gender + " is the same");
        verifyEquals(createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "Gender"), gender);

        dateOfBirth = FunctionHelper.getTodayByFormat(new Date(dateOfBirth), "yyyy-MM-dd");
        log.info("TC_01_Create_Customer_Success - Step 16: Verify that birthdate = " + dateOfBirth + " is the same");
        verifyEquals(createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "Birthdate"), dateOfBirth);

        log.info("TC_01_Create_Customer_Success - Step 17: Verify that address = " + address + " is the same");
        verifyEquals(createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "Address"), address);

        log.info("TC_01_Create_Customer_Success - Step 18: Verify that city = " + city + " is the same");
        verifyEquals(createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "City"), city);

        log.info("TC_01_Create_Customer_Success - Step 19: Verify that state = " + state + " is the same");
        verifyEquals(createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "State"), state);

        log.info("TC_01_Create_Customer_Success - Step 20: Verify that pin = " + pin + " is the same");
        verifyEquals(createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "Pin"), pin);

        log.info("TC_01_Create_Customer_Success - Step 21: Verify that mobile number = " + mobileNumber + " is the same");
        verifyEquals(createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "Mobile No."), mobileNumber);

        log.info("TC_01_Create_Customer_Success - Step 22: Verify that email = " + email + " is the same");
        verifyEquals(createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "Email"), email);

        customerId = createCustomerSuccessPage.getCustomerInfoByDynamicLocator(driver, "Customer ID");

        log.info("TC_01_Create_Customer_Success - Step 23: Logout");
        createCustomerPage.clickLogoutMenuSub(driver, "Log out");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserAndKillProcess();
    }

}
