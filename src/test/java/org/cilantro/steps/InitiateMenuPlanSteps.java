package org.cilantro.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.datafaker.Faker;
import org.cilantro.actions.InitiateMenuPlanActions;
import org.cilantro.actions.SharedActions;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.cilantro.actions.CommonActions.sleep;

public class InitiateMenuPlanSteps extends SharedActions {
    private final InitiateMenuPlanActions initiateMenuPlanActions;
    private final Faker faker = new Faker();


    public InitiateMenuPlanSteps(){
        initiateMenuPlanActions = new InitiateMenuPlanActions();
    }

    @And("User select {string} from date from future month of calender on initiate menu plan page.")
    public void userSelectFromDateFromFutureMonthOfCalenderOnInitiateMenuPlanPage(String arg0) {
        initiateMenuPlanActions.clickOnFromDateCalenderBtn();
        initiateMenuPlanActions.clickOnRightArrowOnCalender();
        initiateMenuPlanActions.selectDateOnCalender(arg0);
    }

    @And("User select {string} to date from future month of calender on initiate menu plan page.")
    public void userSelectToDateFromFutureMonthOfCalenderOnInitiateMenuPlanPage(String arg0) {
        initiateMenuPlanActions.clickOnToDateCalenderBtn();
        initiateMenuPlanActions.selectDateOnCalender(arg0);
    }

    @And("User click on go button on initiate menu plan page.")
    public void userClickOnGoButtonOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.clickOnGoButton();
    }

    @Then("Verify the labels on initiate menu plan page.")
    public void verifyTheLabelsOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.verifyFieldHeading();
        initiateMenuPlanActions.verifyFieldLabels();
    }

    @And("User click on apply for all days dropdown and select meals on initiate menu plan page.")
    public void userClickOnApplyForAllDaysDropdownAndSelectMealsOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.getSelectMeal();
        }

    @And("User click on done button on meal list on initiate menu plan page.")
    public void userClickOnDoneButtonOnMealListOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.clickOnDoneBtn();
    }

    @And("Verify selected dish is displayed on initiate menu plan page.")
    public void verifySelectedDishIsDisplayedOnInitiateMenuPlanPage()throws ParseException {
        waitForThePageLoader();
        initiateMenuPlanActions.verifyDishNames();
        initiateMenuPlanActions.verifyMealCardVisibility();
        initiateMenuPlanActions.verifyDateTimeAssertion();
    }

    @And("User click on {string} dropdown on initiate menu plan page.")
    public void userClickOnDropdownOnInitiateMenuPlanPage(String arg0) {
        initiateMenuPlanActions.clickOnMeal(arg0);
    }

    @And("User click on dish category dropdown on initiate menu plan page.")
    public void userClickOnDishCategoryDropdownOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.clickOnDishCategory();
    }

    @And("User enter {string} on dish category on initiate menu plan page.")
    public void userEnterOnDishCategoryOnInitiateMenuPlanPage(String arg0) {
        initiateMenuPlanActions.enterDishNameOnSearchField(arg0);
    }

    @And("Verify the header tab on initiate menu plan page.")
    public void verifyTheHeaderTabOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.verifyHeaderTab();
    }

    @And("Verify the publish button should clickable on initiate menu plan page.")
    public void verifyTheClickAbilityOfPublishButtonOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.clickOnPublishBtn();
    }

    @And("Verify the success modal of publish on initiate menu plan page.")
    public void verifyTheSuccessModalOfPublishOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.verifyPublishModalTxt();
    }

    @And("Verify date is displayed same as user selected on initiate menu plan page.")
    public void verifyDateIsDisplayedSameAsUserSelectedOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.verifyDateRange();
    }

    @And("Verify the ui of initiate menu plan page.")
    public void verifyTheUiOfInitiateMenuPlanPage() {
        initiateMenuPlanActions.verifyUi();
    }

    @And("User click on no button of publish modal on initiate menu plan page.")
    public void userClickOnNoButtonOfPublishModalOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.clickOnNoBtnOnPublishModal();
        initiateMenuPlanActions.clickOnClearMenuBtn();
    }

    @And("User click on add button on dish category on initiate menu plan page.")
    public void userClickOnAddButtonOnDishCategoryOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.clickOnAddButton();
    }

    @And("User click on {string} header on initiate menu plan page.")
    public void userClickOnHeaderOnInitiateMenuPlanPage(String arg0) {
        initiateMenuPlanActions.clickOnMeal(arg0);
    }

    @And("User click on food program under breakfast dropdown on initiate menu plan page.")
    public void userClickOnFoodProgramUnderBreakfastDropdownOnInitiateMenuPlanPage() {
        initiateMenuPlanActions.clickOnDishCategory();
    }

    @And("Verify selected dish is displayed for food program on initiate menu plan page.")
    public void verifySelectedDishIsDisplayedForFoodProgramOnInitiateMenuPlanPage() {
        waitForThePageLoader();
        initiateMenuPlanActions.verifyDishNames();
        initiateMenuPlanActions.verifyMealCardVisibility();
        initiateMenuPlanActions.verifyDateTimeAssertionForFoodProgram();
    }
}