@Feature
Feature: Customize Achievement Levels in Reports - sprint 51 & 52

  @BU-12347
  Scenario: verify Custom Achievement Levels - Single Test Analysis Reports Table for Student, Class, School, and District Levels
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify custom achievement levels on single test analysis reports table for different levels

  @BU-12348
  Scenario: verify Custom Achievement Levels - Grouping modal and reports Table for Class Level
    Given User is on portal's login screen with username as "teacher_two" and password as "password" and usertype as "realm_one"
    Then verify custom achievement levels on grouping modal and reports table for class level

  @BU-12349
  Scenario: verify Custom Achievement Levels - Comparison modal and reports Table for Student, Class, School and District Level
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify custom achievement levels on comparison modal and reports table for different levels

  @BU-12485
  Scenario: verify Custom Achievement Levels - Test Scores Over Time line chart for the Student, Class, School, and District Levels
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify custom achievement levels on test scores over time line chart for different levels

  @BU-12486
  Scenario: verify Custom Achievement Levels - Revise Key for Student, Class, School, and District Levels in all contexts throughout the eAR
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify custom achievement levels on key toggle for different levels

  @BU-12532
  Scenario: verify Custom Achievement Levels - Standards Summary Report for the Student, Class, School, and District Levels
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify custom achievement levels on standard summary report for different levels
