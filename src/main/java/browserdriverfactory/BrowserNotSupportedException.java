package browserdriverfactory;

import commons.GlobalConstants;

public class BrowserNotSupportedException extends IllegalStateException {

    public BrowserNotSupportedException(String browserName) {
        super(String.format("%s is not supported on " + GlobalConstants.getGlobalConstants().getOsName(), browserName));
    }
}
