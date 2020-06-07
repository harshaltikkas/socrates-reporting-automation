@Feature
Feature: Sprint37 Scenarios 

  @BE-2370
  Scenario: Verify School dropdown is displayed for Teacher with rostered to multiple Schools
    Given User is on portal's login screen with username as "teacher_five" and password as "password" and usertype as "realm_test_tab_pagination"
    Then Verify School dropdown is displayed in Roster tab

  @BE-2371
  Scenario: Verify School dropdown is not displayed for Teacher with rostered to multiple Schools
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then Verify School dropdown is not displayed in Roster tab
