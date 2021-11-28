package google.step_defs;

import browser_actions.BrowserActions;
import credentials.ReadPropertiesFile;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class LanguageHyperlinkStepDef {
    static ReadPropertiesFile reader = new ReadPropertiesFile();

    @Then("Hyperlink is Displayed")
    public void hyperlinkIsDisplayed() {
        Assert.assertNotEquals(BrowserActions.isElementPresent("linkText","English"),BrowserActions.isElementPresent("linkText","العربية"));
    }

    @When("User Switch Google to {string}")
    public static void userSwitchGoogleTo(String language) {
        try{
            BrowserActions.click("linkText",language);
        } catch (Exception ignored) {
        }
    }

    @Then("Google is Displayed in {string}")
    public void googleIsDisplayedIn(String language) {
        if (!language.equals("English")) {
            Assert.assertTrue(BrowserActions.isElementPresent("linkText","لمحة"));
        }else{
            Assert.assertTrue(BrowserActions.isElementPresent("linkText","About"));
        }
    }

    @Then("Search features does not change")
    public void searchFeaturesDoesNotChange() throws IOException {
        try{
            BrowserActions.click("linkText","English");
        } catch (Exception ignored) {
        }
        List<WebElement> englishSearch_Features= BrowserActions.getDriver().findElements(By.xpath(reader.getG_Locator("SearchFeaturesXpath")));
        try{
            BrowserActions.click("linkText","العربية");
        } catch (Exception e) {
            Assert.fail();
        }
        List<WebElement> arabicSearch_Features= BrowserActions.getDriver().findElements(By.xpath(reader.getG_Locator("SearchFeaturesXpath")));
        Assert.assertEquals(englishSearch_Features.size(),arabicSearch_Features.size());
    }
}
