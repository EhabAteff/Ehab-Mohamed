package google.step_defs;

import browser_actions.BrowserActions;
import credentials.ReadPropertiesFile;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class Main_InterfaceStepDef {
    static ReadPropertiesFile reader = new ReadPropertiesFile();

    @When("User switches page language to English")
    public void userSwitchesPageLanguageToEnglish() throws InterruptedException {
        LanguageHyperlinkStepDef.userSwitchGoogleTo("English");
        Thread.sleep(1000);
    }

    @Then("Voice search button is displayed")
    public void voiceSearchButtonIsDisplayed() throws IOException {
        Assert.assertTrue(BrowserActions.isElementPresent("xpath",reader.getG_Locator("VoiceSearchButtonXpath")));
    }

    @Then("Magnifier icon is displayed")
    public void magnifierIconIsDisplayed() throws IOException {
        Assert.assertTrue(BrowserActions.isElementPresent("xpath",reader.getG_Locator("MagnifierSearchIconXpath")));
    }

}
