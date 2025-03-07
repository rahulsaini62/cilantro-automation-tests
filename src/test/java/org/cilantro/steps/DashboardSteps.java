package org.cilantro.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.cilantro.actions.DashboardActions;

public class DashboardSteps {

    private final DashboardActions dashboardActions;

    public DashboardSteps() {
        this.dashboardActions = new DashboardActions();
    }

    @Then("Verify cilantro dashboard should display.")
    public void verifyCilantroDashboardShouldDisplay() {
        dashboardActions.verifyCilantroDashboardShouldDisplay();
    }

    @And("User click on {string} tab under menu on dashboard page.")
    public void userClickOnTabUnderMenuOnDashboardPage(String tabName) {
        dashboardActions.clickOnGivenTabUnderMenu(tabName);
    }

    @And("User click on {string} tab under {string} tab under menu on dashboard page.")
    public void userClickOnTabUnderTabUnderMenuOnDashboardPage(String tabName, String arg1) {
        dashboardActions.clickOnGivenSubTabUnderMenu(tabName);
    }
}
