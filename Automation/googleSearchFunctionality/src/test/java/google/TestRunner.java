package google;

import browser_actions.BrowserActions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;

@CucumberOptions
(
		monochrome = true,
		features={"./src/test/java/google/feature_files"},
		glue={"google/step_defs"},
		tags = "@Run"

)

public class TestRunner extends AbstractTestNGCucumberTests {
	@AfterTest
	public void quitSession()
	{
		BrowserActions.closeBrowser();
	}
}