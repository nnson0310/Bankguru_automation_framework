package pageinterfaces;

public class CommonUI {
    public static final String MENU_SUB_NAV = "xpath=//ul[@class='menusubnav']//a[text()='%s']";
    public static final String VALIDATION_ERROR_MESSAGE = "xpath=//table//input[@name='name']//following-sibling::label[text()='%s']";
    public static final String DYNAMIC_TEXTBOX = "xpath=//table//input[@name='%s']";
}
