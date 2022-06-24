package bankguru.customer.create;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_01_Create_Customer_Verify_Name_Field extends BaseTest {

    WebDriver driver;

    @Parameters({"browserName", "browserVersion", "environmentName", "ipAddress", "port", "platform"})
    @BeforeClass
    public void setUp(
            String browserName,
            String browserVersion,
            String environmentName,
            String ipAddress,
            String port,
            String platform
    ) {

    }

    @Test
    public void TC_Empty_Name() {

    }

    @AfterClass
    public void tearDown() {
        closeBrowserAndKillProcess();
    }
}
