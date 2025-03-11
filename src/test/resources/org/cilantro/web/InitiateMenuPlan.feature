@allure.label.layer:WEB
@allure.label.owner:rahul.saini
@allure.label.page:/{org}/{cilantro}/web
@allure.label.suite:Regression_Suite
@allure.label.subSuite:simulations
#@SimulationPage
Feature: Simulation Feature
  This feature is having scenarios to test the simulation feature.

  Background:
    Given User hit the cilantro app url "cilantro.app.url".
    And User login with username as "cilantro.username" and password as "cilantro.password" on cilantro

  Scenario:Verify Regular Initiate Menu Plan.
    When Verify cilantro dashboard should display.
    When User click on "Planning" tab under menu on dashboard page.
    And User click on "Initiate Menu Plan" tab under "Planning" tab under menu on dashboard page.
    And User select "21" from date from future month of calender on initiate menu plan page.
    And User select "27" to date from future month of calender on initiate menu plan page.
    And User click on go button on initiate menu plan page.
    Then Verify the labels on initiate menu plan page.
    And Verify the ui of initiate menu plan page.
    And Verify date is displayed same as user selected on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed on initiate menu plan page.
    And  Verify dishes have different color on initiate menu plan page.

    And User click on "Lunch" dropdown on initiate menu plan page.
    And User click on dish category dropdown on initiate menu plan page.
    And User enter "Base" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed on initiate menu plan page.
    And User click on add button on dish category on initiate menu plan page.
    And User click on dish category dropdown on initiate menu plan page.
    And User enter "Accompaniments" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed on initiate menu plan page.

    And Verify remove categories modal when user click on delete icon under dish category on initiate menu plan page.
    And User click on "Snacks" dropdown on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed on initiate menu plan page.
    And User click on "Dinner" dropdown on initiate menu plan page.
    And User click on dish category dropdown on initiate menu plan page.
    And User enter "Base" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed on initiate menu plan page.
    And User click on add button on dish category on initiate menu plan page.
    And User click on dish category dropdown on initiate menu plan page.
    And User enter "Accompaniments" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed on initiate menu plan page.
    And Verify the header tab on initiate menu plan page.
    And Verify the publish button should clickable on initiate menu plan page.
    And Verify the success modal of publish on initiate menu plan page.
    And User click on no button of publish modal on initiate menu plan page.


  Scenario:Verify Food Program Initiate Menu Plan.
    When Verify cilantro dashboard should display.
    When User click on "Planning" tab under menu on dashboard page.
    And User click on "Initiate Menu Plan" tab under "Planning" tab under menu on dashboard page.
    And User select "21" from date from future month of calender on initiate menu plan page.
    And User select "27" to date from future month of calender on initiate menu plan page.
    And User click on go button on initiate menu plan page.
    And Verify the ui of initiate menu plan page.
    And User click on "Food Program" header on initiate menu plan page.
    And User click on food program under breakfast dropdown on initiate menu plan page.
    And User enter "Choix" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed for food program on initiate menu plan page.
    And User click on "Lunch" dropdown on initiate menu plan page.
    And User click on dish category dropdown on initiate menu plan page.
    And User enter "Choix" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed for food program on initiate menu plan page.
    And User click on "Snacks" dropdown on initiate menu plan page.
    And User click on dish category dropdown on initiate menu plan page.
    And User enter "Choix" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed for food program on initiate menu plan page.
    And User click on "Dinner" dropdown on initiate menu plan page.
    And User click on dish category dropdown on initiate menu plan page.
    And User enter "Choix" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And Verify selected dish is displayed for food program on initiate menu plan page.

  Scenario:Verify Food Program Category Initiate Menu Plan.
    When Verify cilantro dashboard should display.
    When User click on "Planning" tab under menu on dashboard page.
    And User click on "Initiate Menu Plan" tab under "Planning" tab under menu on dashboard page.
    And User select "14" from date from future month of calender on initiate menu plan page.
    And User select "20" to date from future month of calender on initiate menu plan page.
    And User click on go button on initiate menu plan page.
    And Verify the ui of initiate menu plan page.
    And User click on "Food Program" header on initiate menu plan page.
    And User click on food program under breakfast dropdown on initiate menu plan page.
    And User enter "Add On" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    Then User click on apply for all days dropdown and select meals on initiate menu plan page.

  Scenario:Verify Food Program Meal card on Initiate Menu Plan.
    When Verify cilantro dashboard should display.
    When User click on "Planning" tab under menu on dashboard page.
    And User click on "Initiate Menu Plan" tab under "Planning" tab under menu on dashboard page.
    And User select "14" from date from future month of calender on initiate menu plan page.
    And User select "20" to date from future month of calender on initiate menu plan page.
    And User click on go button on initiate menu plan page.
    And Verify the ui of initiate menu plan page.
    And User click on "Food Program" header on initiate menu plan page.
    And User click on food program under breakfast dropdown on initiate menu plan page.
    And User enter "Two Good" on dish category on initiate menu plan page.
    And User click on done button on meal list on initiate menu plan page.
    And User click on apply for all days dropdown and select meals on initiate menu plan page.
    And Verify selected dish is displayed for food program on initiate menu plan page.

  Scenario:Verify that the minimum length character in search input field on initiate menu plan page.
    When Verify cilantro dashboard should display.
    When User click on "Planning" tab under menu on dashboard page.
    And User click on "Initiate Menu Plan" tab under "Planning" tab under menu on dashboard page.
    And User select "14" from date from future month of calender on initiate menu plan page.
    And User select "20" to date from future month of calender on initiate menu plan page.
    And User click on go button on initiate menu plan page.
    Then Verify that the minimum length character in search input field on initiate menu plan page.