package Runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/feature", // Path to your feature files
    glue = "stepDefination", // Path to your step definitions package
    plugin = {"pretty", "json:target/cucumber-reports/cucumber.json", "junit:target/cucumber-reports/cucumber.xml"}, // Reporting plugins
    tags = "@smoke", // You can specify tags to run specific tests
    monochrome = true, 
    dryRun = false
)
public class RunCucumberTest {

  
}
	