@Feature
Feature: Validate Test Status Section-Sprint 31

  @TestStatus1
  Scenario: Verify info icon text on summary
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    When User Click on Test Status tab within the District Context
    Then Verify info icon text on summary

  @TestStatus2
  Scenario: Verify summary and detail on test status
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    When User Click on Test Status tab within the District Context
    Then Verify summary and detail on test status

  @TestStatus3
  Scenario: Verify sorting order in summary
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    When User Click on Test Status tab within the District Context
    Then Verify sorting order in summary

  @TestStatus4
  Scenario: Verify sorting order in detail
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    When User Click on Test Status tab within the District Context
    Then Verify sorting order in detail
