package pageinterfaces;

public abstract class CreateCustomerPageUI {

    public static final String EMAIL_TEXTAREA = "css=textarea[name='addr']";
    public static final String EMAIL_FIELD_VALIDATION_ERROR_MSG = "xpath=//textarea[@name='%s']//following-sibling::label[text()='%s']";
    public static final String SUBMIT_BUTTON = "css=input[name='sub']";
    public static final String GENDER_RADIO_BUTTON = "xpath=//input[@type='radio' and @value='%s']";
    public static final String DATE_OF_BIRTH_TEXTBOX = "css=input#dob";
}
