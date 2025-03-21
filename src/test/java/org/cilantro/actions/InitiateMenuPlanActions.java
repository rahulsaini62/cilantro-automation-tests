package org.cilantro.actions;

import org.cilantro.enums.PlatformType;
import org.cilantro.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.cilantro.actions.CommonActions.sleep;
import static org.cilantro.actions.elements.ClickableActions.withMouse;
import static org.cilantro.actions.elements.ElementActions.onElement;
import static org.cilantro.actions.elements.ElementFinder.*;
import static org.cilantro.actions.elements.TextBoxActions.onTextBox;
import static org.cilantro.enums.WaitStrategy.VISIBLE;
import static org.cilantro.manager.ParallelSession.getSession;
import static org.cilantro.pages.InitiateMenuPlanPage.initiateMenuPlanPage;
import static org.cilantro.data.DataReader.loadSimulationProps;
import static org.junit.Assert.*;


public class InitiateMenuPlanActions extends SharedActions {
    private final PlatformType platformType;
    List<String> selectedMeals = new ArrayList<>();
    static String expFromDate;
    static String expToDate;

    SoftAssert softAssert = new SoftAssert();

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
        expFromDate = onElement(initiateMenuPlanPage().getFromDateValue()).getAttribute("value");
        expToDate = onElement(initiateMenuPlanPage().getToDateValue()).getAttribute("value");
//        onElement(initiateMenuPlanPage().getGoButton()).isDisplayed();
//        onElement(initiateMenuPlanPage().getGoButton()).isEnabled();
        sleep(1000);
        withMouse(initiateMenuPlanPage().getGoButton()).click();
        sleep(1000);
        waitForThePageLoader();
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
        softAssert.assertTrue(selectedMeals.containsAll(actDishName));
    }

    public void verifyDishNamesForTwoGood() {
        List<String> actDishName = getDishNameForTwoGood();
        softAssert.assertTrue(selectedMeals.containsAll(actDishName));
    }


    public List<String> getDishName() {
        List<String> dishName = new ArrayList<>();
        finds(initiateMenuPlanPage().getDishName(), VISIBLE).forEach(webElement -> dishName.add(webElement.getText()));
        return dishName;
    }

    public List<String> getDishNameForTwoGood() {
        List<String> dishName = new ArrayList<>();
        finds(initiateMenuPlanPage().getDishNameForTwoGood(), VISIBLE).forEach(webElement -> dishName.add(webElement.getText()));
        return dishName;
    }

    public void clickOnMeal(String mealName) {
        withMouse(initiateMenuPlanPage().getMealHeader(mealName)).click();
    }

    public void verifyMealCardVisibility() {
        verifyElementIsDisplayed(initiateMenuPlanPage().getLastPlanDate());
        verifyElementIsDisplayed(initiateMenuPlanPage().getColorCodes());
        verifyElementIsDisplayed(initiateMenuPlanPage().getFoodCategoryLogo());
        verifyElementIsDisplayed(initiateMenuPlanPage().getCloseButton());
        verifyElementIsDisplayed(initiateMenuPlanPage().getDishAlias());
        verifyElementIsDisplayed(initiateMenuPlanPage().getBaseTxt());
        verifyElementIsDisplayed(initiateMenuPlanPage().getDaysAndDatesHeader());
        verifyElementIsDisplayed(initiateMenuPlanPage().getPriceTagOnRegularMealCard());
    }

    public void verifyDateTimeAssertion() {
        List<String> dateValues = new ArrayList<>();
        finds(initiateMenuPlanPage().getDaysAndDatesHeader(), WaitStrategy.VISIBLE).forEach(webElement -> dateValues.add(webElement.getText()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy");
        int year = 2025;
        LocalDate startDate = LocalDate.parse(expFromDate, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        LocalDate endDate = LocalDate.parse(expToDate, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        for (String dateString : dateValues) {
            String fullDateString = dateString + " " + year;
            LocalDate date = LocalDate.parse(fullDateString, formatter);
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                System.out.println(dateString + " is within the range.");
            } else {
                System.out.println(dateString + " is NOT within the range.");
            }
        }
    }

    public void clickOnDishCategory() {
        onElement(initiateMenuPlanPage().getAddButton()).isDisplayed();
        withMouse(initiateMenuPlanPage().getDishCategory()).click();
        verifyElementIsDisplayed(initiateMenuPlanPage().getDeleteBtn());
    }

    public void enterDishNameOnSearchField(String dishName) {
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

    public void clickOnPublishBtn() {
        waitForThePageLoader();
        onElement(initiateMenuPlanPage().getPublishBtn()).isEnabled();
        withMouse(initiateMenuPlanPage().getPublishBtn()).jsxClick();
    }

    public void verifyPublishModalTxt() {
        onElement(initiateMenuPlanPage().getModalHeading()).verifyText().isEqualTo(loadSimulationProps().getModalHeadingTxt());
        onElement(initiateMenuPlanPage().getModalSubheading()).verifyText().isEqualTo(loadSimulationProps().getModalSubheadingTxt());
    }

    public void verifyDateRange() {
        String actFromDate = onElement(initiateMenuPlanPage().getFromDateValueOnInitiateMenuPage()).getText();
        String actToDate = onElement(initiateMenuPlanPage().getToDateValueOnInitiateMenuPage()).getText();
        assertEquals(actFromDate, expFromDate);
        assertEquals(actToDate, expToDate);

    }

    public void verifyUi() {
        onElement(initiateMenuPlanPage().getPageHeading()).verifyText().isEqualTo(loadSimulationProps().getPageHeading());
        onElement(initiateMenuPlanPage().getClearMenuBtn()).isDisplayed();
        onElement(initiateMenuPlanPage().getClearMenuBtn()).isEnabled();
        List<String> actPageHeaderDrpdn = new ArrayList<>();
        finds(initiateMenuPlanPage().getPageHeadersDropdown(), VISIBLE).forEach(
                webElement -> actPageHeaderDrpdn.add(webElement.getText()));
        List<String> expPageHeaderDrpdn = loadSimulationProps().getPageHeaderDrpdn();
        Assert.assertEquals(actPageHeaderDrpdn, expPageHeaderDrpdn);
        String searchField = onElement(initiateMenuPlanPage().getSearchField()).getAttribute("placeholder");
        assertEquals(searchField, "Search Dishes/Items");
    }

    public void clickOnNoBtnOnPublishModal() {
        withMouse(initiateMenuPlanPage().getNoBtnOnPublishModal()).jsxClick();
    }

    public void clickOnClearMenuBtn() {
        withMouse(initiateMenuPlanPage().getClearMenuBtn()).jsxClick();
        withMouse(initiateMenuPlanPage().getYesBtnOnClearModal()).jsxClick();
    }

    public void clickOnAddButton() {
        List<WebElement> rowsBefore = finds(initiateMenuPlanPage().getTableRow());
        int rowCountBefore = rowsBefore.size();
        sleep(3000);
        withMouse(initiateMenuPlanPage().getAddButton()).jsxClick();
        waitForThePageLoader();
        List<WebElement> rowsAfter = finds(initiateMenuPlanPage().getTableRow());
        int rowCountAfter = rowsAfter.size();
        if (rowCountAfter > rowCountBefore) {
            System.out.println("Row successfully added!");
        } else {
            System.out.println("Row was NOT added!");
        }
    }

    public void verifyDateTimeAssertionForFoodProgram() {
        List<String> dateValues = new ArrayList<>();
        finds(initiateMenuPlanPage().getDaysAndDatesHeaderForFoodProgram(), WaitStrategy.VISIBLE).forEach(webElement -> dateValues.add(webElement.getText()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy");
        int year = 2025;
        LocalDate startDate = LocalDate.parse(expFromDate, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        LocalDate endDate = LocalDate.parse(expToDate, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        for (String dateString : dateValues) {
            String fullDateString = dateString + " " + year;
            LocalDate date = LocalDate.parse(fullDateString, formatter);
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                System.out.println(dateString + " is within the range.");
            } else {
                System.out.println(dateString + " is NOT within the range.");
            }
        }
    }

    public void clickOnClearAllMenuBtn() {
        withMouse(initiateMenuPlanPage().getClearAllMenuBtn()).jsxClick();
        withMouse(initiateMenuPlanPage().getYesBtnOnClearModal()).jsxClick();
    }

    public List<String> getSelectMealForParticularDay() {
        waitForThePageLoader();
        withMouse(initiateMenuPlanPage().getParticularDayMeals()).click();
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

    public void verifyRemoveCategoriesModal() {
        List<WebElement> rowsBefore = finds(initiateMenuPlanPage().getTableRow());
        int rowCountBefore = rowsBefore.size();
        withMouse(initiateMenuPlanPage().getDeleteBtn()).jsxClick();
        onElement(initiateMenuPlanPage().getModalHeading()).verifyText().isEqualTo(loadSimulationProps().getRemoveCategoryHeading());
        onElement(initiateMenuPlanPage().getModalSubheading()).verifyText().isEqualTo(loadSimulationProps().getRemoveCategorySubheading());
        onElement(initiateMenuPlanPage().getVerifyBtnArea()).isDisplayed();
        withMouse(initiateMenuPlanPage().getYesBtnOnModal()).jsxClick();
        waitForThePageLoader();
        List<WebElement> rowsAfter = finds(initiateMenuPlanPage().getTableRow());
        int rowCountAfter = rowsAfter.size();
        if (rowCountAfter < rowCountBefore) {
            System.out.println("Row successfully Deleted!");
        } else {
            System.out.println("Row was NOT Deleted!");
        }
    }

    public void searchCharLimitValidation(String dishName) {
        WebElement rows = find(initiateMenuPlanPage().getTableRow());
        enterTextIntoSearchField(dishName);
        if (dishName.length() < 3) {
            softAssert.assertFalse(rows.isDisplayed());
        } else {
            softAssert.assertTrue(rows.isDisplayed());
        }
        clearSearchTextBox();
    }

    public void enterTextIntoSearchField(String text) {
        withMouse(initiateMenuPlanPage().getSearchDish()).jsxClick();
        onTextBox(initiateMenuPlanPage().getSearchField()).enterText(text);
    }

    public void clearSearchTextBox() {
        selectAllAndClearTxtBx(initiateMenuPlanPage().getSearchField());
        sleep(1000);
    }

    public void enterTextIntoGroupNameField(String text) {
        withMouse(initiateMenuPlanPage().getSearchField()).click();
        onTextBox(initiateMenuPlanPage().getSearchField()).enterText(text);
    }

    public void VerifySelectedMealColors() {
        Map<String, String> expectedColors = new HashMap<>();
        expectedColors.put("Achari Baingan", "rgba(192, 119, 21, 1)");
        expectedColors.put("Achari Aloo", "rgba(255, 228, 115, 1)");
        expectedColors.put("Aam Panna", "rgba(55, 186, 49, 1)");
        expectedColors.put("65 Marination", "rgba(0, 0, 0, 0)");
        for (String meal : selectedMeals) {
            String expectedName = meal.replaceAll("\\(Kg\\)", "").trim();
            List<WebElement> colorElement = finds(initiateMenuPlanPage().getColorCode(expectedName), WaitStrategy.VISIBLE);
            for (int i = 0; i < colorElement.size(); i++) {
                String name = colorElement.get(i).getCssValue("background-color");
                Assert.assertEquals(name, expectedColors.get(expectedName));
            }
        }
    }

}
