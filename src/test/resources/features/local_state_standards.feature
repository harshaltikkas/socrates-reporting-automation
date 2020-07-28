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

  @BU-9258
  Scenario: Verify In Roster tab, Persist Student Grade While Moving from District to School Level Reports
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then Verify in roster tab persist student Grade while moving from district to school level reports

  @BU-11660 @BU-11666 @BU-11795 @BU-11798
  Scenario: Verify Local/State Standards - SCHOOL/DISTRICT: Standards Performance Over Time Table & Graph
    Given User is on portal's login screen with username as "ls_sa_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for school standards performance over time
    Given User is on portal's login screen with username as "ls_da_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for school standards performance over time

  @BU-11671 @BU-11860
  Scenario: Verify Local/State Standards - SCHOOL/DISTRICT: Standards Summary
    Given User is on portal's login screen with username as "ls_sa_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for school standards summary
    Given User is on portal's login screen with username as "ls_da_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for school standards summary

  @BU-11673 @BU-11862
  Scenario: Verify Local/State Standards - SCHOOL/DISTRICT: Standards Comparison
    Given User is on portal's login screen with username as "ls_sa_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for school standards comparison
    Given User is on portal's login screen with username as "ls_da_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for school standards comparison

  @BU-11669 @BU-11858
  Scenario: Verify Local/State Standards - SCHOOL/DISTRICT: Standards Standards Single Test Analysis
    Given User is on portal's login screen with username as "ls_sa_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for school standards single test analysis
    Given User is on portal's login screen with username as "ls_da_one" and password as "password" and usertype as "realm_ls_dodea"
    Then Verify local state standards for school standards single test analysis

  @BU-11779
  Scenario: Verify In district context on custom selection of schools, the behaviour of school list in UI
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then Verify In district context on custom selection of schools, the behaviour of school list in UI   

  @BU-11780
  Scenario: Verify In school context on custom selection of classes, the behaviour of class list in UI
    Given User is on portal's login screen with username as "school_admin_one" and password as "password" and usertype as "realm_one"
    Then Verify In school context on custom selection of classes, the behaviour of class list in UI

  @BU-11781
  Scenario: Verify In class context on custom selection of students, the behaviour of student list in UI
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then Verify In class context on custom selection of students, the behaviour of student list in UI
