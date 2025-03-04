package org.cilantro.actions;

import org.apache.logging.log4j.Logger;
import org.cilantro.enums.PlatformType;
import org.testng.Assert;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.cilantro.actions.CommonActions.sleep;
import static org.cilantro.actions.elements.ClickableActions.withMouse;
import static org.cilantro.actions.elements.ElementActions.onElement;
import static org.cilantro.actions.elements.ElementFinder.waitForElementClickable;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.pages.DashboardPage.commonPage;

public class DashboardActions extends SharedActions {

    private final PlatformType platformType;
    private static final Logger LOGGER = getLogger();

    public DashboardActions() {
        this.platformType = getSession().getPlatformType();
    }


    public void verifyCilantroDashboardShouldDisplay() {
        Assert.assertTrue(verifyElementIsDisplayed(commonPage().getPageTitle()),
                "Cilantro Dashboard page is not displayed");
    }

    public void clickOnGivenTabUnderMenu(String tabName) {
        waitForThePageLoader();
        waitForElementClickable(commonPage().getHeaderTabUnderMenu(tabName));
        withMouse(commonPage().getHeaderTabUnderMenu(tabName)).click();
        sleep(1000);
    }

    public void clickOnGivenSubTabUnderMenu(String tabName) {
        waitForElementClickable(commonPage().getSubTabUnderMenu(tabName));
        withMouse(commonPage().getSubTabUnderMenu(tabName)).click();
        sleep(2000);
    }
}
