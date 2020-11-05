@Feature
Feature: Single Test Analysis - Sprint 22

  @BE-1552 @BE-1553
  Scenario: Verify Key Toggle - Single Test Analysis
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then Verify the key toggle when user click on single test analysis tab and the other tab

  @BE-1554 @BE-1559 @BE-1560 @BE-1561 @BE-1562 @BE-1563 @BE-1569 @BE-1570 @BE-1571 @BE-1572
  Scenario: Verify The Default View Behaviour of the Single Test Analysis when the user clicks on the single test analysis tab
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then The Default View Behaviour of the Single Test Analysis when the user clicks on the single test analysis tab

  @BE-1555 @BE-1556
  Scenario: Verify Adding Teacher label to Context Header for the School Admins
    Given User is on portal's login screen with username as "school_admin_one" and password as "password" and usertype as "realm_one"
    Then veryfy Teacher label to Context Header for the School Admins for teacher "Ryan Whitaker"

  @BE-1568
  Scenario: Verify On clicking the individual question should switch to Question Filter report - Single Test Analysis
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify On clicking the individual question should switch to Question Filter report - Single Test Analysis
