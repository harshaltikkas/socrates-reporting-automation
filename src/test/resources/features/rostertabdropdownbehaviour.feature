@Feature
Feature: Roster Tab Dropdown Behaviour

  ##story 1c)
  
  #  @Scenario9 @TC12
  ##Data incomplete we need role of teacher for single and multiple class allocation.
  ##  @SmokeTests
  ##  Scenario Outline: Single or Multiple Selection of class Dropdown
  ##    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
  ##    When within the Universal Selector Tab "<usertype>" with "<singleormultipleclass>" selection of class click on roster tab
  ##    Then by default "All" student should be selected and "<usertype>" with "<singleormultipleclass>" should be able to select Student drop downlist
  ##    And verify class list and school list and student lastname starts in alpha order
  ##  Examples:
  ##      | 	usertype  				|	singleormultipleclass	|
  ##      | Student  				  |												|
  ##      | Teacher  				  |		single							|
  ##      | Teacher  		 			|		multiple						|
  ##      | School Admin 	 		|												|
  ##      | District Admin 		|												|
  
 ##story Loading Icon
  @Scenario10 @TC16
  Scenario: Verify that the selected option should appear at the top of the list and the user should select one option from the dropdown list at a time.
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    ##TC-13a
    Then User Click on Roster tab within the Universal Selector Tab and bydefault first alpha school and first alpha class and 'all' student are selected
    ##13b
    And User Click on Roster tab within the Universal Selector Tab and the School and Class names and the student last names should be displayed in alphabetical ascending order within their respective dropdowns
    ##TC-16
    And Verify selected option should appear at the top of the list

  @Scenario12 @TC18
  Scenario: Verify the search bar not scrollable and has the "X" to cancel searched option and search icon to search the option within School and class and student dropdown
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Roster tab within the Universal Selector Tab
    Then Searches anything what’s being typed in showing "x" to cancel and displays them as options to select from below the search bar and filters the list.

  @Scenario13 @TC19
  Scenario: Verify that the All option displayed in first position among the optins in studentlist untill the user select the single student name as option
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Roster tab within the Universal Selector Tab
    Then "All" option should be display at first position in list and can not be scrollable

  ##S6 & S7 , BE-630,660,659
  Scenario: Verify Selection/Deselection of Multiple Students from Student dropdown List within Roster tab
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Roster tab within the Universal Selector Tab
    Then Veriy the previously selected students should be on top in student dropdown
