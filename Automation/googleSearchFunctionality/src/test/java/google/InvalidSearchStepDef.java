package google;

import browser_actions.BrowserActions;
import credentials.ReadPropertiesFile;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class InvalidSearchStepDef {
    static ReadPropertiesFile reader = new ReadPropertiesFile();

    @When("Search for invalid {string}")
    public void searchForInvalid(String invalidSearchData) throws IOException{
        ValidSearchStepDef.userSearchForData(invalidSearchData);
    }

    @Then("No action is taken if {string} is empty")
    public void noActionIsTakenIfIsEmpty(String invalidSearchData) throws IOException {
        if (invalidSearchData.length() == 0) {
            Assert.assertTrue(BrowserActions.isElementPresent("xpath", reader.getG_Locator("SearchButtonXpath")));
            Assert.assertEquals(reader.getUrl("Google"), BrowserActions.getDriver().getCurrentUrl());
        }
    }

    @Then("should not match any results if {string} has a value")
    public void shouldNotMatchAnyResults(String invalidSearchData) throws IOException {
        if (invalidSearchData.length() != 0) {
            Assert.assertNotEquals(reader.getUrl("Google"), BrowserActions.getDriver().getCurrentUrl());
            WebElement search_Results= BrowserActions.getDriver().findElement(By.xpath(reader.getG_Locator("G_InvalidResultsXpath")));
            Assert.assertTrue(search_Results.getText().toLowerCase().contains("لم ينجح بحثك عن "+invalidSearchData.toLowerCase()));
        }
    }

}
