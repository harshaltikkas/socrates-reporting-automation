@Feature
Feature: Validate local/state standards UI functionality

  @BU-9234 @BU-9236
  Scenario: Verify Local/State Standards - CLASS: Standards Performance Over Time Table & Graph
    Given User is on portal's login screen with username as "ls_teacher_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for class standards performance over time

  @BU-9226 @BU-9217 
  Scenario: Verify Local/State Standards - CLASS/STUDENT: Standards Comparison
    Given User is on portal's login screen with username as "ls_teacher_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for class and or student standards comparison
    When User Click on Standard Performance tab within the Student Context
    Then Verify local state standards for class and or student standards comparison

  @BU-9227
  Scenario: Verify Local/State Standards - CLASS: Standards Grouping
    Given User is on portal's login screen with username as "ls_teacher_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for class standards grouping

  @BU-9220 @BU-9212
  Scenario: Verify Local/State Standards - CLASS/STUDENT: Standards Summary
    Given User is on portal's login screen with username as "ls_teacher_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for class and or student standards summary
    When User Click on Standard Performance tab within the Student Context
    Then Verify local state standards for class and or student standards summary

  @BU-9218 @BU-9222
  Scenario: Verify Local/State Standards - CLASS/STUDENT: Standards Standards Single Test Analysis
    Given User is on portal's login screen with username as "ls_teacher_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for class and or student standards single test analysis
    When User Click on Standard Performance tab within the Student Context
    Then Verify local state standards for class and or student standards single test analysis

  @BU-9232 @BU-9238
  Scenario: Verify Local/State Standards - STUDENT: Standards Performance Over Time Table & Graph
    Given User is on portal's login screen with username as "ls_teacher_one" and password as "password" and usertype as "realm_ls_dodea"
    When User Click on Standard Performance tab within the Student Context
    Then Verify local state standards for student standards performance over time
