package pageinterfaces;

public abstract class AccessDetailPageUI {

    public static final String USERID_ROW = "xpath=//table//td[contains(text(), 'User ID')]//following-sibling::td";
    public static final String PASSWORD_ROW = "xpath=//table//td[contains(text(), 'Password')]//following-sibling::td";
}
