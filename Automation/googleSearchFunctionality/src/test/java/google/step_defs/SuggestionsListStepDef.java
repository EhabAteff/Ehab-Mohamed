package google.step_defs;

import browser_actions.BrowserActions;
import credentials.ReadPropertiesFile;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class SuggestionsListStepDef {
    static ReadPropertiesFile reader = new ReadPropertiesFile();

    @When("User enters {string} in search box")
    public void userEntersInSearchbox(String searchData) throws IOException {
        NavigationStepDef.checkSearchEngine();
        try{
            LanguageHyperlinkStepDef.userSwitchGoogleTo("العربية");
        } catch (Exception ignored) {
        }
        BrowserActions.input("name",reader.getG_Locator("SearchEngineName"),searchData);
    }

    @Then("Search Suggestions list Displays")
    public void searchSuggestionsDisplays() throws IOException {
        Assert.assertTrue(BrowserActions.isElementPresent("xpath",reader.getG_Locator("SuggestionsListXpath")));
    }
}
