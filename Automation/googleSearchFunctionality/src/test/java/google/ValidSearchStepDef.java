package google;

import browser_actions.BrowserActions;
import credentials.ReadPropertiesFile;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class ValidSearchStepDef {
    static ReadPropertiesFile reader = new ReadPropertiesFile();
    static Boolean suggestionsFlag = null;

    @When("User search for valid {string}")
    public static void userSearchForData(String searchData) throws IOException{
        NavigationStepDef.checkSearchEngine();
        try{
            LanguageHyperlinkStepDef.userSwitchGoogleTo("العربية");
        } catch (Exception ignored) {
        }
        BrowserActions.clear("name",reader.getG_Locator("SearchEngineName"));
        BrowserActions.input("name",reader.getG_Locator("SearchEngineName"),searchData);
        BrowserActions.click("xpath", reader.getG_Locator("SearchButtonXpath"));
        suggestionsFlag = BrowserActions.click("xpath", reader.getG_Locator("SuggestionsSearchButtonXpath"));
    }

    @Then("Page url changes")
    public void pageUrlChanged() throws IOException {
        Assert.assertNotEquals(reader.getUrl("Google"),BrowserActions.getDriver().getCurrentUrl());
    }

    @Then("{string} Appears in the search results")
    public void appearsInTheSearchResults(String validSearchData) throws IOException {
        List<WebElement> search_Results= BrowserActions.getDriver().findElements(By.xpath(reader.getG_Locator("G_ResultsXpath")));
        if(suggestionsFlag) {
            Assert.assertTrue(search_Results.get(0).getText().toLowerCase().contains(validSearchData.toLowerCase()));
        }
    }


}