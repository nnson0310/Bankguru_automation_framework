package commons;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    int retryTimes = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (counter < retryTimes) {
            counter++;
            return true;
        }
        return false;
    }
}
