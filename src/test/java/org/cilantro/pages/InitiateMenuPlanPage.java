package org.cilantro.pages;

import org.cilantro.builders.Locator;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import lombok.Getter;

@Getter

public class InitiateMenuPlanPage {
    private static final InitiateMenuPlanPage INITIATE_MENU_PLAN_PAGE = new InitiateMenuPlanPage();


    public static InitiateMenuPlanPage initiateMenuPlanPage() {
        return INITIATE_MENU_PLAN_PAGE;
    }

    private final Locator fromDate = Locator.buildLocator()
            .web(cssSelector("#simple-tabpanel-0  div:nth-child(1) > div > div > div > button"))
            .name("from Date")
            .build();

    private final Locator toDate = Locator.buildLocator()
            .web(cssSelector("#simple-tabpanel-0  div:nth-child(2) > div > div > div > button"))
            .name("To Date")
            .build();

    private final Locator rightArrowIcon = Locator.buildLocator()
            .web(cssSelector(".MuiPickersArrowSwitcher-nextIconButton.css-4m8ul0 > svg"))
            .name("Arrow Right Icon On Calender")
            .build();

    public final Locator getDateOptionLocator(String date) {
        return Locator.buildLocator()
                .web(xpath(String.format("//button[contains(text(), '%s')]", date)))
                .name(String.format("Date %s", date))
                .build();
    }

    public final Locator goButton = Locator.buildLocator()
            .web(cssSelector("button.primary-btn"))
            .name("Go Button")
            .build();

    private final Locator initiateMenuPlanLabels = Locator.buildLocator()
            .web(cssSelector(".MuiButtonBase-root.css-6p016c"))
            .name("Initiate menu Plan Labels")
            .build();

    private final Locator initiateMenuPlanFieldLabels = Locator.buildLocator()
            .web(cssSelector("button.flex.align-items-center.dropDownButton "))
            .name("Initiate menu Plan field Labels")
            .build();

    public final Locator getMealHeader(String mealName) {
        return Locator.buildLocator()
                .web(xpath(String.format("//button[text()='%s']", mealName)))
                .name(String.format("tab under Menu %s", mealName))
                .build();
    }

     private final Locator applyForAllDaysDrpdnUnderMeals = Locator.buildLocator()
             .web(cssSelector(".apply-all.css-1plsf6o > div.select-wrapper > div > div > div"))
             .name("Apply for all days dropdown")
             .build();

    private final Locator mealList = Locator.buildLocator()
            .web(cssSelector("div.ReactVirtualized__Grid li"))
            .name("Meal List")
            .build();

    private final Locator mealCheckBox = Locator.buildLocator()
            .web(cssSelector("div.ReactVirtualized__Grid li > div > span"))
            .name("Meal CheckBox")
            .build();

    private final Locator doneButton = Locator.buildLocator()
            .web(xpath("//button[text()='Done']"))
            .name("Done button")
            .build();

    private final Locator dishName = Locator.buildLocator()
            .web(cssSelector("strong.c-title-inner"))
            .name("Dish Name")
            .build();

    private final Locator colorCodes = Locator.buildLocator()
            .web(cssSelector("div.colorCode"))
            .name("Color Code")
            .build();

    private final Locator lastPlanDate = Locator.buildLocator()
            .web(cssSelector("div.lastPlan"))
            .name("Last Plan Date")
            .build();

    private final Locator foodCategoryLogo = Locator.buildLocator()
            .web(cssSelector("td:nth-child(n)  div.recipeTitle-wrapper.flex > div > span"))
            .name("Food Category Logo")
            .build();

    private final Locator closeButton = Locator.buildLocator()
            .web(cssSelector("button.close"))
            .name("Close Button")
            .build();

    private final Locator dishAlias = Locator.buildLocator()
            .web(xpath("//button[text()='Dish Alias']"))
            .name("Dish Alias Button")
            .build();

    private final Locator baseTxt = Locator.buildLocator()
            .web(xpath("//div[text()='Base']"))
            .name("Base txt")
            .build();

    private final Locator dishCategory = Locator.buildLocator()
            .web(xpath("(//div[@role='combobox' and contains(@class, 'MuiSelect-select')])[3]"))
            .name("Dish Category")
            .build();

    private final Locator dishCategorySearchBox = Locator.buildLocator()
            .web(cssSelector("input.css-1pk1fka"))
            .name("Dish Category")
            .build();

    private final Locator headerTab = Locator.buildLocator()
            .web(cssSelector("div.button-wrap.list.flex.align-items-center > button:nth-child(n)"))
            .name("Header Tab")
            .build();

    private final Locator publishBtn = Locator.buildLocator()
            .web(xpath("//button[text()=' Publish']"))
            .name("Publish Btn")
            .build();

    private final Locator modalHeading = Locator.buildLocator()
            .web(cssSelector("h3.modal-title"))
            .name("Modal Heading")
            .build();

    private final Locator modalSubheading = Locator.buildLocator()
            .web(cssSelector("div.modal-body"))
            .name("Modal Subheading")
            .build();

    private final Locator addButton = Locator.buildLocator()
            .web(cssSelector("button.add"))
            .name("Add Button")
            .build();

    private final Locator headingUnderDishDrpDn = Locator.buildLocator()
            .web(cssSelector(".shadow.css-1vfms6x > div > h6"))
            .name("Heading under dropdown")
            .build();

    private final Locator fromDateValue = Locator.buildLocator()
            .web(xpath("//input[@id=':r2:']"))
            .name("From Date Value")
            .build();

    private final Locator toDateValue = Locator.buildLocator()
            .web(xpath("//input[@id=':r4:']"))
            .name("To Date Value")
            .build();

    private final Locator fromDateValueOnInitiateMenuPage = Locator.buildLocator()
            .web(cssSelector("div.plansetup_dateRange__p\\+L8n > span:nth-child(2)"))
            .name("From Date Value On Initiate Menu Page")
            .build();

    private final Locator toDateValueOnInitiateMenuPage = Locator.buildLocator()
            .web(cssSelector("div.plansetup_dateRange__p\\+L8n > span:nth-child(3)"))
            .name("To Date Value On Initiate Menu Page")
            .build();

    private final Locator pageHeadersDropdown = Locator.buildLocator()
            .web(cssSelector("div.menulist"))
            .name("Page Header drop Down")
            .build();

    private final Locator pageHeading = Locator.buildLocator()
            .web(cssSelector("#root > div.content-wrapper > h1"))
            .name("Page Heading")
            .build();

    private final Locator clearMenuBtn = Locator.buildLocator()
            .web(cssSelector("div.right.flex.align-itmes-center > button"))
            .name("Clear Menu Btn")
            .build();

    private final Locator clearBtnForMeal = Locator.buildLocator()
            .web(cssSelector("div.right.flex.align-itmes-center > button"))
            .name("Clear Menu Btn")
            .build();

    private final Locator noBtnOnPublishModal = Locator.buildLocator()
            .web(xpath("//button[text()='No']"))
            .name("No Button On Publish Modal")
            .build();

    private final Locator yesBtnOnClearModal = Locator.buildLocator()
            .web(xpath("//button[text()='Yes, Clear']"))
            .name("Yes Button On Clear Modal")
            .build();

    private final Locator priceOnMealCardOnFoodProgram = Locator.buildLocator()
            .web(cssSelector("div.price"))
            .name("Price on Meal Card")
            .build();

    private final Locator copyBtnOnMealCard = Locator.buildLocator()
            .web(cssSelector("button.dishAlias"))
            .name("Copy button")
            .build();

    private final Locator daysAndDatesHeader = Locator.buildLocator()
            .web(xpath("(//h6[@class='MuiTypography-root MuiTypography-h6 table-title css-1rl0qlz'])[position() > 2]"))
            .name("Days And Dates Header")
            .build();

    private final Locator tableRow = Locator.buildLocator()
            .web(cssSelector("div.content > div > div > table > tbody:nth-child(n) > tr"))
            .name("Table Row")
            .build();

    private final Locator daysAndDatesHeaderForFoodProgram = Locator.buildLocator()
            .web(xpath("(//h6[@class='MuiTypography-root MuiTypography-h6 table-title css-1rl0qlz'])[position() > 3]"))
            .name("Days And Dates Header")
            .build();

    private final Locator clearAllMenuBtn = Locator.buildLocator()
            .web(xpath("//button[text()=' Clear All Menu']"))
            .name("Clear All Menu Btn")
            .build();

    private final Locator priceTagOnRegularMealCard = Locator.buildLocator()
            .web(cssSelector("strong.price"))
            .name("Price Tag On Regular meal card")
            .build();

    private final Locator deleteBtn = Locator.buildLocator()
            .web(cssSelector(".cmenu-btn.delete-btn"))
            .name("Delete Btn")
            .build();

    private final Locator particularDayMeals = Locator.buildLocator()
            .web(cssSelector("table > tbody:nth-child(2) > tr > td:nth-child(5) > div > div > div > div"))
            .name("Apply for all days dropdown")
            .build();

    private final Locator searchField = Locator.buildLocator()
            .web(cssSelector("input.MuiInputBase-input.css-aae3xl"))
            .name("Search")
            .build();

    private final Locator verifyBtnArea = Locator.buildLocator()
            .web(cssSelector("div.modal-footer.flex.justify-content-center"))
            .name("Btn Area")
            .build();

    private final Locator yesBtnOnModal = Locator.buildLocator()
            .web(xpath("//button[text()='Yes']"))
            .name("Yes Btn")
            .build();

    public final Locator getColorCode(String mealName) {
        return Locator.buildLocator()
                .web(xpath("//strong[@class='c-title-inner' and starts-with(text(),'"+mealName+"')]/../../..//div[@class='colorCode']"))
                .name(String.format("tab under Menu %s", mealName))
                .build();
    }
}
