@allure.label.layer:WEB
@allure.label.owner:rahul.saini
@allure.label.page:/{org}/{cilantro}/web
@allure.label.suite:Regression_Suite
@allure.label.subSuite:simulations
@SimulationPage


Feature: Simulation Feature
  This feature is having scenarios to test the simulation feature.

  Background:
    Given User hit the cbs app url "cilantro.app.url".
    And User login with username as "cilantro.username" and password as "cilantro.password" on cbs admin

  Scenario:Verify Initiate Menu Plan.
    When Verify cilantro dashboard should display.
    When User click on "Planning" tab under menu on dashboard page.
    And User click on "Initiate Menu Plan" tab under "Planning" tab under menu on dashboard page.

