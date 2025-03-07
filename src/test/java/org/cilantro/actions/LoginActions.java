package org.cilantro.actions;

import org.apache.logging.log4j.Logger;
import org.cilantro.enums.PlatformType;
import org.cilantro.pages.LoginPage;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.cilantro.actions.CommonActions.sleep;
import static org.cilantro.actions.drivers.DriverActions.withDriver;
import static org.cilantro.actions.drivers.NavigateActions.navigate;
import static org.cilantro.actions.elements.ClickableActions.withMouse;
import static org.cilantro.actions.elements.ElementActions.onElement;
import static org.cilantro.actions.elements.ElementFinder.*;
import static org.cilantro.actions.elements.TextBoxActions.onTextBox;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.pages.LoginPage.loginPage;
import static org.cilantro.utils.PropertiesUtil.getApplicationProps;

public class LoginActions extends SharedActions {
    private final PlatformType platformType;
    private static final Logger LOGGER = getLogger();

    public LoginActions() {

        this.platformType = getSession().getPlatformType();
    }

    public void enterInUsernameTxtBx(String userName) {
        onTextBox(LoginPage.loginPage()
                .getEmailTxt()).enterText(getApplicationProps(userName));
    }

    public void enterInPasswordTxtBx(String password) {
        onTextBox(loginPage().getPasswordTxt()).enterText(getApplicationProps(password));
    }

    public void populateLoginFormAndSignIn(final String userName, final String password) {
        enterInUsernameTxtBx(userName);
        withMouse(loginPage().getNextBtn()).click();
        sleep(2000);
        enterInPasswordTxtBx(password);
        sleep(1000);
        withMouse(loginPage().getSignInBtn()).click();
        sleep(2000);
        if (verifyElementIsDisplayed(loginPage().getStaySignInBtn())) {
            withMouse(loginPage().getStaySignInBtn()).click();
        }
//        sleep(2000);

//        withMouse(loginPage().getStaySignInBtn()).click();
//        waitForURLContains("code");
//        waitForElementVisible(commonPage().getPageLoader());
//        waitForElementInvisibility(commonPage().getPageLoader());
//        setTokenAndDeviceIdFromLocalStorage();
    }

    public void navigateToAppUrl(String appUrl) {
        navigate().to(getApplicationProps(appUrl));
    }

    public void loginWithGivenCred(final String userName, final String password) {
        populateLoginFormAndSignIn(userName, password);
//        waitForElementInvisibility(cbsMasterPage().getLoginSuccessPopup());
    }

    public void setTokenAndDeviceIdFromLocalStorage() {
        String localStorageData = withDriver().executeScript("return localStorage.getItem(arguments[0]);",
                "access_token");
        LOGGER.info("Access token value: {}", localStorageData);
        getSession().setSharedData("token", "Bearer" + " " + localStorageData);

    }

}