package pageinterfaces;

public abstract class CreateCustomerPageUI {

    public static final String EMAIL_TEXTAREA = "css=textarea[name='addr']";
    public static final String EMAIL_FIELD_VALIDATION_ERROR_MSG = "xpath=//textarea[@name='%s']//following-sibling::label[text()='%s']";
}
