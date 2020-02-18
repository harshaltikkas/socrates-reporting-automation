@Feature
Feature: Spint 9 User Stories

  ##BE-832,833,834
  Scenario: Verify Showing grading page in new browser tab when clicked on the question number within the test overlay
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    When User Click on Standard Performance tab within the Class Context
    Then To check the grading page in new browser tab should open by clicking on the question number within the test overlay
    When User Click on Standard Performance tab within the Student Context
    Then To check the grading page in new browser tab should open by clicking on the question number within the test overlay

  ##BE-835,836
  Scenario: Verify Grade dropdown position and appearance within the Standard Performance Overview
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    When User Click on Standard Performance tab within the District Context
    Then verify the appearance within the standard performance overview
    When User Click on Standard Performance tab within the Class Context
    Then verify the appearance within the standard performance overview
    When User click on Class Context and Test Score button
    Then verify the appearance within the standard performance overview
    When User click on Student Context and Test Score button
    Then verify the appearance within the standard performance overview
    When User Click on Standard Performance tab within the Student Context
    Then verify the appearance within the standard performance overview

  ## BE-837,838
  Scenario: Verify Standards Question Filter dropdown label and position within Standard Performance Overview
    Given User is on portal's login screen with username as "teacher_one" and password as "password" and usertype as "realm_one"
    When User Click on Standard Performance tab within the Class Context
    Then verify Standards Question Filter dropdown label and position within Standard Performance Overview
    When User click on Class Context and Test Score button
    Then verify Standards Question Filter dropdown label and position within Standard Performance Overview
    When User click on Student Context and Test Score button
    Then verify Standards Question Filter dropdown label and position within Standard Performance Overview
    When User Click on Standard Performance tab within the Student Context
    Then verify Standards Question Filter dropdown label and position within Standard Performance Overview

  ###Need to implement logic for print pdf then open and verify the headers value in pdf file with ui
  ## BE-845,846,847,848,849
  #Scenario: Verify PDF contents with different subscenarioes
  #Given User is on portal's login screen with username as "teacher_one" and password as "password" and usertype as "realm_one"
  #When User Click on Standard Performance tab within the Class Context
  #Then verify Context header content for the Standards Overview context selected shown in all PDF's
  #When User click on Class Context and Test Score button
  #Then verify Context header content for the Standards Overview context selected shown in all PDF's
  #When User click on Student Context and Test Score button
  #Then verify Context header content for the Standards Overview context selected shown in all PDF's
  #When User Click on Standard Performance tab within the Student Context
  #Then verify Context header content for the Standards Overview context selected shown in all PDF's
  ## Verifying View Texonomy on Standard Performance
  
  Scenario: Verify View Texonomy on Standard Performance
    Given User is on portal's login screen with username as "teacher_one" and password as "password" and usertype as "realm_one"
    When User Click on Standard Performance tab within the Class Context
    Then verify View Texonomy on Standard Performance
    When User click on Class Context and Test Score button
    Then verify View Texonomy on Standard Performance
    When User click on Student Context and Test Score button
    Then verify View Texonomy on Standard Performance
    When User Click on Standard Performance tab within the Student Context
    Then verify View Texonomy on Standard Performance
