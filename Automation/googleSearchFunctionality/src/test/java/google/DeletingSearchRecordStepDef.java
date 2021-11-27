package google;

import browser_actions.BrowserActions;
import credentials.ReadPropertiesFile;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class DeletingSearchRecordStepDef {
    ReadPropertiesFile reader = new ReadPropertiesFile();

    @Then("{string} should Display as the first suggestion")
    public void shouldDisplayAsTheFirstSuggestion(String searchData) throws IOException {
        Assert.assertTrue(searchData.toLowerCase().equalsIgnoreCase(BrowserActions.elementCreator("xpath",reader.getG_Locator("LatestSearchRecordTextXpath")).getText()));
    }

    @When("User clicks the remove button")
    public void userClicksTheRemoveButton() throws IOException {
        BrowserActions.click("xpath",reader.getG_Locator("LatestSearchRecordRemoveButtonXpath"));
    }

    @Then("{string} should not Display in the first suggestion")
    public void shouldNotDisplayInTheFirstSuggestion(String searchData) throws IOException {
        Assert.assertFalse(searchData.toLowerCase().equalsIgnoreCase(BrowserActions.elementCreator("xpath",reader.getG_Locator("LatestSearchRecordTextXpath")).getText()));
    }
}
