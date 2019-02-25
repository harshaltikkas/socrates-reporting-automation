@Feature1 
Feature: Roster Tab Dropdown Behaviour

#  @Scenario9 @TC12
#Data incomplete, we need role of teacher for single and multiple class allocation.
#  @SmokeTests
#  Scenario Outline: Single or Multiple Selection of class Dropdown
#  Given User is on portal's login screen with username as "superadmin" and password as "password" 
#    When within the Universal Selector Tab "<usertype>" with "<singleormultipleclass>" selection of class click on roster tab 
#    Then by default "All" student should be selected and "<usertype>" with "<singleormultipleclass>" should be able to select Student drop downlist
#    And verify class list and school list and student lastname starts in alpha order 

#  Examples: 
#      | 	usertype  				|	singleormultipleclass	|
#      | Student  				  |												|																														
#      | Teacher  				  |		single							|
#      | Teacher  		 			|		multiple						|
#      | School Admin 	 		|												|
#      | District Admin 		|												|

#This will fail because UI is not implemented as requirement.
  @Scenario10 @TC16
  Scenario: Verify that the selected option should appear at the top of the list and the user should select one option from the dropdown list at a time. 
  Given User is on portal's login screen with username as "superadmin" and password as "password" 
    When User Click on Roster tab within the Universal Selector Tab and select options from dropdown
    Then Verify selected option should appear at the top of the list.

#  @Scenario11 @TC17 UI not ready
#  Scenario: Verify if the School option is not selected then Student and Class dropdown list should be gray out and not selectable. 
    
  @Scenario12 @TC18
  Scenario: Verify the search bar not scrollable and has the "X" to cancel searched option and search icon to search the option within School, class and student dropdown. 
  Given User is on portal's login screen with username as "superadmin" and password as "password" 
    When User Click on Roster tab within the Universal Selector Tab
    Then Searches anything what’s being typed in showing "x" to cancel and displays them as options to select from below the search bar and filters the list.
  
  @Scenario13 @TC19
  Scenario: Verify that the "All" option displayed in first position among the optins in studentlist untill the user select the single student name as option. 
  Given User is on portal's login screen with username as "superadmin" and password as "password" 
    When User Click on Roster tab within the Universal Selector Tab
    Then "All" option should be display at first position in list and can not be scrollable   
    