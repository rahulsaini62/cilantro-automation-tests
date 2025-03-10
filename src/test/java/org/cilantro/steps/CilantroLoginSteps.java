package org.cilantro.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.cilantro.actions.LoginActions;

public class CilantroLoginSteps {

    private final LoginActions    loginActions;

    public CilantroLoginSteps() {
        this.loginActions = new LoginActions ();
    }

    @Given ("User hit the cilantro app url {string}.")
    public void userHitTheCilantroAppUrl (final String appUrl) {
        this.loginActions.navigateToAppUrl (appUrl);
    }

    @Then ("User login with username as {string} and password as {string} on cilantro")
    public void userLoginWithUsernameAsAndPasswordAsOnCilantro (final String username, final String password) {
        this.loginActions.loginWithGivenCred (username, password);

    }
}
