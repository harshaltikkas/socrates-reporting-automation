@Feature1
Feature: Page Selector and Class Student Filter

#This TC1 will fail because the default selection is not set as performance selector button.
  @Scenario22 @TC1 @TC3
  Scenario: Verify by default Standards Performance button should be selected with the Class context from the Student/Class Filter.
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When default Standards Performance button should be selected with the Class context
    Then user selects Test Scores button within the page selector then also the context will remain same

  @Scenario23 @TC9 
  Scenario: Verify user can able to open & close reporting key which includes Achievement levels with colour coding in a single row centered on the page.
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User click on fly in key to open reporing key
    Then verify the achievement leves with different colour codes and click on fly in key to close it.

  @Scenario24 @TC6 @TC7  @TC8
  Scenario: Verify left side footer text and right side footer text 
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    Then Verify left side footer text and right side footer text 

  @Scenario25 @TC_0001
  Scenario: Verify position of the Context Header tab position and the Verify the Labels within it.
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    Then Verify labels on context header based on standard performance and test scores button 

  @Scenario26 @TC_0004
  Scenario Outline: Verify the changes happened, when the user hover over the data(If it has an ellipsis).
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    Then verify if context header data have an elipsis then it should show full data in tooltip for "<menutype>"

		Examples: 
      | menutype |
      | Student  |
      | Class    |

  @Scenario27 @TC_0005 
  Scenario Outline: Verify the tab opens, when the user clicks on the data(label) having clickable links on context menu.
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    Then Verify the tab opens when the user clicks on the data(label) having clickable links for "<menutype>"

		Examples: 
      | menutype |
      | Student  |
      | Class    |