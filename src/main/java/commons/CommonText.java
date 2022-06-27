package commons;

import lombok.Getter;

@Getter
public class CommonText {
    private static CommonText commonText;

    private CommonText() {

    }

    public static CommonText getCommonText() {
        if (commonText == null) {
            return new CommonText();
        }
        return commonText;
    }

    private final String newCustomerMenuSub = "New Customer";
    private final String editCustomerMenuSub = "Edit Customer";

}
