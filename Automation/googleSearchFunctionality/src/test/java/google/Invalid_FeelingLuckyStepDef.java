package google;

import io.cucumber.java.en.When;

import java.io.IOException;

public class Invalid_FeelingLuckyStepDef {

    @When("User use invalid feeling lucky search with {string}")
    public void userUseInvalidFeelingLuckySearchWith(String invalidData) throws IOException {
        Valid_FeelingLuckyStepDef.userUseFealingLuckySearchWith(invalidData);

    }
}
