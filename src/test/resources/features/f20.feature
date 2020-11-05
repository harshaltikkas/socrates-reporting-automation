@Feature
Feature: Validate Past District Term - sprint 50 & 55

  @BU-11988 @BU-12062 @BU-16064
  Scenario: verify Test Status tab Inactive on viewing past district term reports
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then verify Test Status tab Inactive on viewing past district term reports

  @BU-12063 @BU-13129
  Scenario: verify Admin Notification Banner - Only in Past District Term reports
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify admin notification banner only in Past District Term reports
    Given User is on portal's login screen with username as "school_admin_one" and password as "password" and usertype as "realm_one"
    Then verify admin notification banner only in Past District Term reports
    Given User is on portal's login screen with username as "teacher_one" and password as "password" and usertype as "realm_one"
    Then verify admin notification banner only in Past District Term reports

  @BU-12066
  Scenario: verify Default current term data - Teacher viewing past term data for the currently rostered classes/students of current term
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then verify Teacher viewing past term data for the currently rostered classes and or students of current term

  @BU-12067
  Scenario: verify Default past term data - Teacher viewing past term data for the currently rostered classes/students of current term
    Given User is on portal's login screen with username as "teacher_six" and password as "password" and usertype as "realm_three"
    Then verify  Default past term data Teacher viewing past term data for the currently rostered classes and or students of current term

  @BU-12070
  Scenario: verify Test tab and Date tab - On viewing the past term reports
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then verify Test tab and Date tab On viewing the past term reports

  @BU-12071
  Scenario: verify Context header - On viewing the past term reports
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then verify context header On viewing the past term reports

  @BU-12072 @BU-13132
  Scenario: verify Class Comparison with info icon - On viewing the past term reports
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then verify class comparison with info icon on viewing the past term reports

  @BU-13126
  Scenario: verify the School admin current term and past term seeing the past district term reports With No data for your selection modal
    Given User is on portal's login screen with username as "school_admin_two" and password as "password" and usertype as "realm_one"
    Then verify the school admin current term and past term seeing the past district term reports with no data for your selection modal

	##Failing as, while selecting current term,and apply.. it is not showing no data popup
  @BU-13127
  Scenario: verify School admin only past term seeing the past district term reports With No data for your selection modal
    Given User is on portal's login screen with username as "pdt_school_admin_one" and password as "password" and usertype as "realm_three"
    Then verify school admin only past term seeing the past district term reports with no data for your selection modal

  @BU-13214 @BU-13211
  Scenario: verify PDT Updates - SA/DA Selecting any one school from roster and loading current term details on being in past term
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify the pdt updates da selecting one school from roster and loading current term details on being in past term

  @BU-13347_1
  Scenario: verify Any test having Future date then converted to todays system date and displayed in the UI at all the levels
    Given User is on portal's login screen with username as "ls_da_one" and password as "password" and usertype as "realm_ls_dodea"
    Then verify future date on test tab and context header and performance overtime graph being in standard performance

  @BU-13347_2
  Scenario: verify Any test having Future date then converted to todays system date and displayed in test score 
    Given User is on portal's login screen with username as "ls_da_one" and password as "password" and usertype as "realm_ls_dodea"
    Then verify future date on test tab and context header and test score over time and detail in test score