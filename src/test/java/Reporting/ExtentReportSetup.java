package Reporting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import UtilityLayer.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtentReportSetup {

    private static ExtentReports extent;
    private static final Logger logger = LogManager.getLogger(ExtentReportSetup.class);

    public static ExtentReports setupExtentReport() {
        // Load properties
        String environment = ConfigReader.getProperty("environment", "QA");
        String os = ConfigReader.getProperty("os", "Unknown OS");
        String user = ConfigReader.getProperty("user", "Unknown User");
        String javaVersion = ConfigReader.getProperty("java.version", System.getProperty("java.version"));
        String reportFolder = ConfigReader.getProperty("report.folder", "reports");
        String reportName = ConfigReader.getProperty("report.name", "ExtentReport");
        String reportTitle = ConfigReader.getProperty("report.title", "Automation Test Report");
        String documentTitle = ConfigReader.getProperty("report.documentTitle", "Extent Report");

        // Ensure report folder exists
        File folder = new File(reportFolder);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new RuntimeException("Failed to create report folder: " + reportFolder);
        }

        // Generate the report file path with a timestamp
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = reportFolder + "/" + reportName + "_" + date + ".html";

        // Log report setup details
        logger.info("Generating report at: " + reportPath);
        logger.info("Environment: " + environment);
        logger.info("OS: " + os + ", User: " + user);

        // Initialize the Spark Reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName(reportTitle);
        sparkReporter.config().setDocumentTitle(documentTitle);

        // Initialize ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system/environment information
        extent.setSystemInfo("Environment", environment);
        extent.setSystemInfo("OS", os);
        extent.setSystemInfo("Java Version", javaVersion);
        extent.setSystemInfo("User", user);

        return extent;
    }

    public static ExtentReports getExtent() {
        if (extent == null) {
            setupExtentReport();
        }
        return extent;
    }

    public static void closeReport() {
        if (extent != null) {
            extent.flush();
            logger.info("Report flushed successfully.");
            extent = null;
        }
    }
}
