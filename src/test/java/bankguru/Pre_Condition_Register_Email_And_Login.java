package bankguru;

import commons.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageobjects.*;
import serverconfig.InvalidServerNameException;
import serverconfig.ServerConfig;
import utilities.DataFaker;

public class Pre_Condition_Register_Email_And_Login extends BaseTest {

    WebDriver driver;

//    public static String email, userId, password, url;
    public static String userId = "mngr417606";
    public static String password = "ErugYpE";
    public static String url = "https://demo.guru99.com/v4";

    RegisterPage registerPage;
    AccessDetailPage accessDetailPage;
    LoginPage loginPage;
    ManagerHomePage managerHomePage;

//    @Parameters({"registerUrl", "browserName", "browserVersion", "environmentName", "ipAddress", "port", "platform"})
//    @BeforeTest(description = "Register email and login")
//    public void setUp(
//            String registerUrl,
//            @Optional("firefox") String browserName,
//            @Optional("latest") String browserVersion,
//            @Optional("local") String environmentName,
//            @Optional("localhost") String ipAddress,
//            @Optional("4444") String port,
//            @Optional("Windows 10") String platform
//    ) {
//        driver = getBrowserDriver(registerUrl, browserName, browserVersion, environmentName, ipAddress, port, platform);
//        registerPage = PageGeneratorManager.getPageGeneratorManager().getRegisterPage(driver);
//
//        email = DataFaker.getDataFaker().generateEmail();
//
//        log.info("Register Email - Step 01: Input email address " + email);
//        registerPage.inputToEmailTextbox(driver, email);
//
//        log.info("Register Email - Step 02: Click submit button");
//        accessDetailPage = registerPage.clickToSubmitButton(driver);
//
//        log.info("Register Email - Get userId " + userId + " and password " + password);
//        userId = accessDetailPage.getUserId(driver);
//        password = accessDetailPage.getPassword(driver);
//
//        //get server name from command line and check if server name is invalid
//        String serverName = System.getProperty("server");
//        if (serverName == null || serverName.equals("")) {
//            serverName = "product";
//        }
//        ConfigFactory.setProperty("server", serverName);
//        ServerConfig serverconfig = ConfigFactory.bankguru.account.create(ServerConfig.class);
//        if (serverconfig.url() == null) {
//            throw new InvalidServerNameException(serverName);
//        }
//
//        url = serverconfig.url();
//
//        closeBrowserAndKillProcess();
//    }

}
