import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class CustomRetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetries = 3;

    @Override
    public boolean retry(ITestResult result) {
        RetryTest retryTestAnnotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(RetryTest.class);
        if (retryTestAnnotation != null) {
            maxRetries = retryTestAnnotation.maxRetries();
        }

        if (retryCount < maxRetries) {
            retryCount++;
            return true;
        }
        return false;
    }
}
