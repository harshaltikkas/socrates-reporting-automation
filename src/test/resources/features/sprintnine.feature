@Feature1
Feature: Spint 9 User Stories

  ##BE-832,833,834
  ##NOTE: failing because of not able to get url of newly opened tab for validation
  #Scenario: Verify Showing grading page in new browser tab when clicked on the question number within the test overlay
  #Given User is on portal's login screen with username as "SarahMMerritt" and password as "password" and usertype as "realm1"
  #When User Click on Standard Performance tab within the Class Context
  #Then To check the grading page in new browser tab should open by clicking on the question number within the test overlay
  #When User Click on Standard Performance tab within the Student Context
  #Then To check the grading page in new browser tab should open by clicking on the question number within the test overlay
 
  ##BE-835,836 also performed DB validation for Grade DD (passed for UI and DB)
  Scenario: Verify Grade dropdown position and appearance within the Standard Performance Overview
    Given User is on portal's login screen with username as "Manis1981352610" and password as "password" and usertype as "sulphur"
    When User Click on Standard Performance tab within the Class Context
    Then verify the appearance within the standard performance overview
    When User click on Class Context and Test Score button
    Then verify the appearance within the standard performance overview
    When User click on Student Context and Test Score button
    Then verify the appearance within the standard performance overview
    When User Click on Standard Performance tab within the Student Context
    Then verify the appearance within the standard performance overview

  ## BE-837 UI Done and passed,838(DB pending for logic and data calcualting)
  Scenario: Verify Standards Question Filter dropdown label and position within Standard Performance Overview
    Given User is on portal's login screen with username as "Manis1981352610" and password as "password" and usertype as "sulphur"
    When User Click on Standard Performance tab within the Class Context
    Then verify Standards Question Filter dropdown label and position within Standard Performance Overview
    When User click on Class Context and Test Score button
    Then verify Standards Question Filter dropdown label and position within Standard Performance Overview
    When User click on Student Context and Test Score button
    Then verify Standards Question Filter dropdown label and position within Standard Performance Overview
    When User Click on Standard Performance tab within the Student Context
    Then verify Standards Question Filter dropdown label and position within Standard Performance Overview


  	
  ## BE-845,846,847,848,849
  Scenario: Verify PDF contents with different subscenarioes
   # Given User is on portal's login screen with username as "Manis1981352610" and password as "password" and usertype as "sulphur"
   # When User Click on Standard Performance tab within the Class Context
   # Then verify Context header content for the Standards Overview context selected shown in all PDF's
   # When User click on Class Context and Test Score button
   # Then verify Context header content for the Standards Overview context selected shown in all PDF's
   # When User click on Student Context and Test Score button
   # Then verify Context header content for the Standards Overview context selected shown in all PDF's
   # When User Click on Standard Performance tab within the Student Context


  ## Verifying View Texonomy on Standard Performance (passed for both UI and DB)
  Scenario: Verify View Texonomy on Standard Performance
    Given User is on portal's login screen with username as "Manis1981352610" and password as "password" and usertype as "sulphur"
    When User Click on Standard Performance tab within the Class Context
    Then verify View Texonomy on Standard Performance
    When User click on Class Context and Test Score button
    Then verify View Texonomy on Standard Performance
    When User click on Student Context and Test Score button
    Then verify View Texonomy on Standard Performance
    When User Click on Standard Performance tab within the Student Context
    Then verify View Texonomy on Standard Performance
