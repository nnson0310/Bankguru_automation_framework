package pageinterfaces;

public abstract class CreateCustomerSuccessPageUI {
    public static final String CUSTOMER_REGISTER_SUCCESS_MSG = "xpath=//p[@class='heading3' and contains(text(), '%s')]";
    public static final String CREATED_CUSTOMER_INFO = "xpath=//table//td[text()='%s']//following-sibling::td";
}
