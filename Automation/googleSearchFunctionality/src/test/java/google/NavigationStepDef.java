package google;

import browser_actions.BrowserActions;
import credentials.ReadPropertiesFile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class NavigationStepDef {
    static ReadPropertiesFile reader = new ReadPropertiesFile();

    @Given("User has access to chrome browser")
    public void userHasAccessToChromeBrowser() {
        BrowserActions.newDriver("chrome");
    }


    @When("Navigate to {string} search engine")
    public void userNavigatesToGoogle(String website) throws Exception {
        BrowserActions.navigate(website);
        Assert.assertTrue(BrowserActions.verifyLink(reader.getUrl(website)));
    }

    @Then("Search textbox appears")
    public static void checkSearchEngine() throws IOException {
        Assert.assertTrue(BrowserActions.isElementPresent("name",reader.getG_Locator("SearchEngineName")),"Search Engine Not Found");
    }
}
