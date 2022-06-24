package bankguru;

import commons.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageobjects.*;
import serverconfig.ServerConfig;
import utilities.DataFaker;

public class Pre_Condition_Register_Email_And_Login extends BaseTest {

    WebDriver driver;

    ServerConfig serverconfig;

    RegisterPage registerPage;
    AccessDetailPage accessDetailPage;
    LoginPage loginPage;
    ManagerHomePage managerHomePage;

    @Parameters({"registerUrl", "browserName", "browserVersion", "environmentName", "ipAddress", "port", "platform"})
    @BeforeTest(description = "Register email and login to user page")
    public void setUp(
            String registerUrl,
            String browserName,
            String browserVersion,
            String environmentName,
            String ipAddress,
            String port,
            String platform
    ) {
        driver = getBrowserDriver(registerUrl, browserName, browserVersion, environmentName, ipAddress, port, platform);
        registerPage = PageGeneratorManager.getPageGeneratorManager().getRegisterPage(driver);

        String email = DataFaker.getDataFaker().generateEmail();

        log.info("Register Email - Input email address");
        registerPage.inputToEmailTextbox(driver, email);

        log.info("Register Email - Click submit button");
        accessDetailPage = registerPage.clickToSubmitButton(driver);

        log.info("Register Email - Get userId and password");
        String userId = accessDetailPage.getUserId(driver);
        String password = accessDetailPage.getPassword(driver);

        String serverName = System.getProperty("server");
        ConfigFactory.setProperty("server", serverName);
        serverconfig = ConfigFactory.create(ServerConfig.class);

        log.info("Register - Redirect to login page");
        loginPage = accessDetailPage.redirectToLoginPage(driver, serverconfig.url());

        log.info("Login - Input userID: " + userId);
        loginPage.inputToUserIdTextbox(driver, userId);

        log.info("Login - Input password: " + password);
        loginPage.inputToPasswordTextbox(driver, password);

        log.info("Login - Input userID");
        managerHomePage = loginPage.clickToLoginButton(driver);

    }

}
