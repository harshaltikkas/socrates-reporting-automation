@smoketest
Feature: Staging bugs failed functionalities

  @BE-2473 @Bug-1
  Scenario: Verify STA reports navigating from Test Status report in student context
    Given User is on portal's login screen with username as "stg_sa_bug" and password as "password" and usertype as "realm_stg_onlyardata"
    When User Click on Test Status tab within the School Context
    Then Verify STA reports navigating from Test Status report in student context

  @Bug-2
  Scenario: Verify to click on the Class subway nav filter in test status
    Given User is on portal's login screen with username as "stg_da_bug" and password as "password" and usertype as "realm_stg_onlyardata"
    Then Verify to click on the Class subway nav filter in test status

  @Bug-3 @Bug-4
  Scenario: Verify no data avaliable for Test Scores tab
    Given User is on portal's login screen with username as "stg_da_bug" and password as "password" and usertype as "realm_stg_onlyardata"
    Then Verify no data avaliable for Test Scores tab
    Given User is on portal's login screen with username as "stg_da_bug" and password as "password" and usertype as "realm_stg_onlyardata"
    Then Verify no data avaliable for STA tab

  @Bug-5
  Scenario: Verify student analysis
    Given User is on portal's login screen with username as "stg_da_bug" and password as "password" and usertype as "realm_stg_onlyardata"
    Then Verify student analysis
