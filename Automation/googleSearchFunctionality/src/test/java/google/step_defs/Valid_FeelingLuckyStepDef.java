package google.step_defs;


import browser_actions.BrowserActions;
import credentials.ReadPropertiesFile;
import io.cucumber.java.en.When;

import java.io.IOException;

public class Valid_FeelingLuckyStepDef {
    static ReadPropertiesFile reader = new ReadPropertiesFile();
    static Boolean suggestionsFlag = null;

    @When("User use feeling lucky search with {string}")
    public static void userUseFealingLuckySearchWith(String searchData) throws IOException {
        NavigationStepDef.checkSearchEngine();
        try{
            LanguageHyperlinkStepDef.userSwitchGoogleTo("العربية");
        } catch (Exception ignored) {
        }
        BrowserActions.clear("name",reader.getG_Locator("SearchEngineName"));
        BrowserActions.input("name",reader.getG_Locator("SearchEngineName"),searchData);
        BrowserActions.click("xpath", reader.getG_Locator("FeelingLuckyButtonXpath"));
        suggestionsFlag = BrowserActions.click("xpath", reader.getG_Locator("SuggestionsFeelingLuckyButtonXpath"));
    }
}
