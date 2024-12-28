package UtilityLayer;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
        }
        return extentReports;
    }

    public static void createTest(String testName) {
        extentTest.set(getExtentReports().createTest(testName));
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void flushReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
