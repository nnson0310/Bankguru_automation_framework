package commons;

import lombok.Getter;

@Getter
public class CommonText {
    private static CommonText commonText;

    private CommonText() {

    }

    public synchronized static CommonText getCommonText() {
        if (commonText == null) {
            return new CommonText();
        }
        return commonText;
    }

    private final String newCustomerMenuSub = "New Customer";
    private final String editCustomerMenuSub = "Edit Customer";
    private final String deleteCustomerMenuSub = "Delete Customer";
    private final String newAccountMenuSub = "New Account";
    private final String editAccountMenuSub = "Edit Account";
    private final String deleteAccountMenuSub = "Delete Account";
    private final String depositMenuSub = "Deposit";
    private final String withdrawalMenuSub = "Withdrawal";
    private final String fundTransferMenuSub = "Fund Transfer";
    private final String changePasswordMenuSub = "Change Password";
    private final String balanceEnquiryMenuSub = "Balance Enquiry";
    private final String miniStatementMenuSub = "Mini Statement";
    private final String customisedStatementMenuSub = "Customised Statement";
}
