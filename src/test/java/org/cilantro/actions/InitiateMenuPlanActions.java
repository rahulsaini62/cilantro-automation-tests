package org.cilantro.actions;

import org.cilantro.enums.PlatformType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.cilantro.actions.CommonActions.sleep;
import static org.cilantro.actions.elements.ClickableActions.withMouse;
import static org.cilantro.actions.elements.ElementActions.onElement;
import static org.cilantro.actions.elements.ElementFinder.*;
import static org.cilantro.actions.elements.TextBoxActions.onTextBox;
import static org.cilantro.enums.WaitStrategy.VISIBLE;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.pages.InitiateMenuPlanPage.initiateMenuPlanPage;
import static org.cilantro.data.DataReader.loadSimulationProps;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class InitiateMenuPlanActions extends SharedActions {
    private final PlatformType platformType;
    List<String> selectedMeals = new ArrayList<>();
    static String actFromDate;
    static String actToDate;

    public InitiateMenuPlanActions() {
        this.platformType = getSession().getPlatformType();
    }

    public void selectDateOnCalender(String date) {
        waitForElementVisible(initiateMenuPlanPage().getDateOptionLocator(date));
        withMouse(initiateMenuPlanPage().getDateOptionLocator(date)).click();
    }

    public void clickOnRightArrowOnCalender() {
        withMouse(initiateMenuPlanPage().getRightArrowIcon()).doubleClick();
        withMouse(initiateMenuPlanPage().getRightArrowIcon()).doubleClick();
        sleep(2000);
    }

    public void clickOnGoButton() {
        actFromDate = onElement(initiateMenuPlanPage().getFromDateValue()).getAttribute("value");
        actToDate = onElement(initiateMenuPlanPage().getToDateValue()).getAttribute("value");
        onElement(initiateMenuPlanPage().getGoButton()).isDisplayed();
        onElement(initiateMenuPlanPage().getGoButton()).isEnabled();
        withMouse(initiateMenuPlanPage().getGoButton()).click();
    }

    public void clickOnFromDateCalenderBtn() {
        onElement(initiateMenuPlanPage().getFromDate()).isDisplayed();
        onElement(initiateMenuPlanPage().getFromDate()).isEnabled();
        withMouse(initiateMenuPlanPage().getFromDate()).click();
    }

    public void clickOnToDateCalenderBtn() {
        onElement(initiateMenuPlanPage().getToDate()).isDisplayed();
        onElement(initiateMenuPlanPage().getToDate()).isEnabled();
        withMouse(initiateMenuPlanPage().getToDate()).click();
        sleep(3000);
    }

    public void verifyFieldHeading() {
        List<String> actFieldHeadings = new ArrayList<>();
        finds(initiateMenuPlanPage().getInitiateMenuPlanLabels(), VISIBLE).forEach(
                webElement -> actFieldHeadings.add(webElement.getText()));
        List<String> expFieldLabels = loadSimulationProps().getInitiateMenuPlanLabels();
        Assert.assertEquals(actFieldHeadings, expFieldLabels);
    }

    public void verifyFieldLabels() {
        List<String> actInitiateFieldLabels = new ArrayList<>();
        finds(initiateMenuPlanPage().getInitiateMenuPlanFieldLabels(), VISIBLE).forEach(
                webElement -> actInitiateFieldLabels.add(webElement.getText()));
        List<String> expInitiateFieldLabels = loadSimulationProps().getInitiateMenuPlanFieldLabels();
        Assert.assertEquals(actInitiateFieldLabels, expInitiateFieldLabels);
    }

    public List<String> getSelectMeal() {
        waitForThePageLoader();
        withMouse(initiateMenuPlanPage().getApplyForAllDaysDrpdnUnderMeals()).click();
        verifyElementIsDisplayed(initiateMenuPlanPage().getMealCheckBox());
        List<WebElement> checkboxes = finds(initiateMenuPlanPage().getMealCheckBox(), VISIBLE);
        for (int i = 0; i < 4 && i < checkboxes.size(); i++) {
            if (!checkboxes.get(i).isSelected()) {
                checkboxes.get(i).click();
                String mealName = checkboxes.get(i).getText();
                selectedMeals.add(mealName + " (Kg)");
            }
        }
        return selectedMeals;
    }

    public void clickOnDoneBtn() {
        withMouse(initiateMenuPlanPage().getDoneButton()).click();
    }

    public void verifyDishNames() {
        List<String> actDishName = getDishName();
        assertTrue(selectedMeals.containsAll(actDishName));
    }


    public List<String> getDishName() {
        List<String> dishName = new ArrayList<>();
        finds(initiateMenuPlanPage().getDishName(), VISIBLE).forEach(webElement -> dishName.add(webElement.getText()));
        return dishName;
    }

    public void clickOnMeal(String mealName){
        withMouse(initiateMenuPlanPage().getMealHeader(mealName)).click();
    }

    public void verifyMealCardVisibility() throws ParseException {
        verifyElementIsDisplayed(initiateMenuPlanPage().getLastPlanDate());
        verifyElementIsDisplayed(initiateMenuPlanPage().getColorCode());
        verifyElementIsDisplayed(initiateMenuPlanPage().getFoodCategoryLogo());
        verifyElementIsDisplayed(initiateMenuPlanPage().getCloseButton());
        verifyElementIsDisplayed(initiateMenuPlanPage().getDishAlias());
        verifyElementIsDisplayed(initiateMenuPlanPage().getBaseTxt());
        verifyElementIsDisplayed(initiateMenuPlanPage().getDaysAndDatesHeader());
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM", Locale.ENGLISH);
        Date actualFromDate = dateFormat.parse(onElement(initiateMenuPlanPage().getDaysAndDatesHeader()).getText());
    }

    public void clickOnDishCategory() {
        onElement(initiateMenuPlanPage().getAddButton()).isDisplayed();
        withMouse(initiateMenuPlanPage().getDishCategory()).click();
    }

    public void enterDishNameOnSearchField(String dishName){
        onTextBox(initiateMenuPlanPage().getDishCategorySearchBox()).enterText(dishName);
        withMouse(initiateMenuPlanPage().getMealCheckBox()).click();
    }

    public void verifyHeaderTab() {
        List<String> actHeaderTab = new ArrayList<>();
        finds(initiateMenuPlanPage().getHeaderTab(), VISIBLE).forEach(
                webElement -> actHeaderTab.add(webElement.getText()));
        List<String> expHeaderTab = loadSimulationProps().getHeaderTabTxt();
        Assert.assertEquals(actHeaderTab, expHeaderTab);
    }

    public void clickOnPublishBtn(){
        waitForThePageLoader();
        onElement(initiateMenuPlanPage().getPublishBtn()).isEnabled();
        withMouse(initiateMenuPlanPage().getPublishBtn()).jsxClick();
    }

    public void verifyPublishModalTxt(){
        onElement(initiateMenuPlanPage().getModalHeading()).verifyText().isEqualTo(loadSimulationProps().getModalHeadingTxt());
        onElement(initiateMenuPlanPage().getModalSubheading()).verifyText().isEqualTo(loadSimulationProps().getModalSubheadingTxt());
    }

    public void verifyDateRange(){
        String expFromDate = onElement(initiateMenuPlanPage().getFromDateValueOnInitiateMenuPage()).getText();
        String expToDate = onElement(initiateMenuPlanPage().getToDateValueOnInitiateMenuPage()).getText();
        assertEquals(expFromDate,actFromDate);
        assertEquals(expToDate,actToDate);

    }
    public void verifyUi(){
        onElement(initiateMenuPlanPage().getPageHeading()).verifyText().isEqualTo(loadSimulationProps().getPageHeading());
        onElement(initiateMenuPlanPage().getClearMenuBtn()).isDisplayed();
        onElement(initiateMenuPlanPage().getClearMenuBtn()).isEnabled();
        List<String> actPageHeaderDrpdn = new ArrayList<>();
        finds(initiateMenuPlanPage().getPageHeadersDropdown(), VISIBLE).forEach(
                webElement -> actPageHeaderDrpdn.add(webElement.getText()));
        List<String> expPageHeaderDrpdn = loadSimulationProps().getPageHeaderDrpdn();
        Assert.assertEquals(actPageHeaderDrpdn, expPageHeaderDrpdn);
    }

    public void clickOnNoBtnOnPublishModal(){
        withMouse(initiateMenuPlanPage().getNoBtnOnPublishModal()).jsxClick();
    }

    public void clickOnClearMenuBtn(){
        withMouse(initiateMenuPlanPage().getClearMenuBtn()).jsxClick();
        withMouse(initiateMenuPlanPage().getYesBtnOnClearModal()).jsxClick();
    }

    public void clickOnAddButton(){
        sleep(3000);
        withMouse(initiateMenuPlanPage().getAddButton()).jsxClick();
    }

}
