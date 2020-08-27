@Feature
Feature: Validate Past District Term - sprint 50

  @BU-11988 @BU-12062 @BU-16064
  Scenario: verify Test Status tab Inactive on viewing past district term reports
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then verify Test Status tab Inactive on viewing past district term reports

  @BU-12063
  Scenario: verify Admin Notification Banner - Only in Past District Term reports
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify admin notification banner only in Past District Term reports
    Given User is on portal's login screen with username as "school_admin_one" and password as "password" and usertype as "realm_one"
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

  @BU-12072
  Scenario: verify Class Comparison with info icon - On viewing the past term reports
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then verify class comparison with info icon on viewing the past term reports
