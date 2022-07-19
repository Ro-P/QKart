package QKART_TESTNG;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ListenerClass implements ITestListener {
     

    public void onTestStart(ITestResult result) {

        QKART_Tests.takeScreenshot(QKART_Tests.driver, "TestStart",result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        QKART_Tests.takeScreenshot(QKART_Tests.driver, "TestSuccess",result.getName());
    }

    public void onTestFailure(ITestResult result) {
        QKART_Tests.takeScreenshot(QKART_Tests.driver, "TestFailure" ,result.getName());
    }

}
