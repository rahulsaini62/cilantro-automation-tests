package org.cilantro.actions;

import org.apache.logging.log4j.Logger;
import org.cilantro.builders.Locator;
import org.cilantro.enums.PlatformType;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.cilantro.actions.CommonActions.sleep;
import static org.cilantro.actions.drivers.DriverActions.withDriver;
import static org.cilantro.actions.elements.ClickableActions.withMouse;
import static org.cilantro.actions.elements.ElementActions.onElement;
import static org.cilantro.actions.elements.ElementFinder.*;
import static org.cilantro.actions.elements.TextBoxActions.onTextBox;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.pages.DashboardPage.commonPage;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class SharedActions {
    private final PlatformType platformType;
    private static final Logger LOGGER = getLogger();

    public SharedActions() {
        this.platformType = getSession().getPlatformType();
    }

    /**
     * Wait for the page loader.
     */
    public void waitForThePageLoader() {
        waitForElementInvisibility(commonPage().getPageLoader());
    }

    /**
     * Wait for table loader
     */
    public void waitForTheTableLoader() {
        //waitForElementVisible (commonPage ().getTableLoader ());
        waitForElementInvisibility(commonPage().getTableLoader());
    }

    /**
     * Send Keys to the text-field
     */
    public void sendKeys(Locator locator, String text) {
        waitForElementVisible(commonPage().getUserImage());
        withMouse(locator).click();
        withMouse(locator).clear();
        onTextBox(locator).enterText(text);
    }


    public void selectAllAndClearTxtBx(Locator locator) {
        String os = System.getProperty("os.name")
                .toLowerCase();
        Keys modifierKey;
        if (os.contains("mac")) {
            modifierKey = Keys.COMMAND;
        } else {
            modifierKey = Keys.CONTROL;
        }
        onTextBox(locator).enterText(Keys.chord(modifierKey, "a") + Keys.BACK_SPACE);
    }

    public <T> void verifyEquals(T actual, T expected) {
        assertEquals(actual, expected);
    }

    public <T> void verifyAllMatch(List<T> list, T condition) {
        assertTrue(list.stream()
                .allMatch(val -> val.equals(condition)));
    }

    public <T> void verifyContains(List<T> list, T condition) {
        assertTrue("Value not found in the list",list.stream()
                .anyMatch(val -> val.toString()
                        .contains(condition.toString())));
    }

    public <T> void verifyNotEquals(T actual, T expected) {
        assertNotEquals(expected, actual);
    }


    public static boolean verifyElementIsDisplayed(Locator locator) {
        return !finds(locator).isEmpty();
    }

    public void pasteInTxtBox(Locator locator) {
        String os = System.getProperty("os.name")
                .toLowerCase();
        Keys modifierKey;
        if (os.contains("mac")) {
            modifierKey = Keys.COMMAND;
        } else {
            modifierKey = Keys.CONTROL;
        }
        onTextBox(locator).enterText(Keys.chord(modifierKey, "v"));
    }

    public void verifyCopyAndPaste(final Locator locator, final String expected) {
        final String att1 = onElement(locator).getAttribute("value");
        LOGGER.info("Attribute value after entering text:{}", att1);
        onTextBox(locator).enterText(
                Keys.chord(Keys.CONTROL, "a") + Keys.chord(Keys.CONTROL, "c") + Keys.chord(Keys.BACK_SPACE));
        withDriver().waitUntil(driver -> {
            final String att2 = onElement(locator).getAttribute("value");
            return att2 == null || att2.trim()
                    .isEmpty();
        });
        final String att2 = onElement(locator).getAttribute("value");
        Assert.assertTrue(att2 == null || att2.trim()
                .isEmpty());
        sleep(100);
        pasteInTxtBox(locator);

        withDriver().waitUntil(driver -> {
            final String actualName = onElement(locator).getAttribute("value");
            return actualName != null && !actualName.trim()
                    .isEmpty();
        });

        final String actualName = onElement(locator).getAttribute("value");
        LOGGER.info("Attribute value after Pasting the copied text:{}", actualName);
        Assert.assertEquals(actualName, expected);
    }

    public void verifyCopyAndPasteForPhoneNumber(final Locator locator, String phoneNum) {
        final String att1 = onElement(locator).getAttribute("value");
        LOGGER.info("Attribute phone number after entering text:{}", att1);
        final int length = phoneNum.length();
        onTextBox(locator).enterText(Keys.chord(Keys.CONTROL, "a") + Keys.chord(Keys.CONTROL, "c"));
        for (int i = 0; i < length; i++) {
            onTextBox(locator).enterText(Keys.chord(Keys.BACK_SPACE));
            sleep(100);
        }
        final String att2 = onElement(locator).getAttribute("value");
        Assert.assertTrue(att2 == null || att2.trim()
                .isEmpty());
        sleep(200);
        pasteInTxtBox(locator);

        withDriver().waitUntil(driver -> {
            final String phNumber = onElement(locator).getAttribute("value");
            return phNumber != null && !phNumber.trim()
                    .isEmpty();
        });
        final String actuaNum = onElement(locator).getAttribute("value");
        LOGGER.info("Attribute phone number after Pasting the copied text:{}", actuaNum);
        Assert.assertEquals(actuaNum, "+1" + " " + phoneNum);
    }

    public void verifyElementAttributeNotEmpty(Locator locator, String attributeKey) {
        withDriver().waitUntil(driver -> {
            final String attributeValue = onElement(locator).getAttribute(attributeKey);
            return attributeValue != null && !attributeValue.isEmpty();
        });
    }


}
