package stepDefination;

import UtilityLayer.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void setUp() {
		// Start the Extent Reports for the current scenario
		ExtentManager.createTest("Test Scenario");
	}

	@After
	public void tearDown() {
		// Flush the reports after the scenario finishes
		ExtentManager.flushReports();
	}
}
