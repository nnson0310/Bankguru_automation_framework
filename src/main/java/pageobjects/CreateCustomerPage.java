package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class CreateCustomerPage extends BasePage {
    WebDriver driver;

    public CreateCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public CreateCustomerPage navigateToCreateCustomerPage(WebDriver driver, String menuSub) {
        navigateToMenuSubByDynamicLocator(driver, menuSub);
        return PageGeneratorManager.getPageGeneratorManager().getCreateCustomerPage(driver);
    }
}
