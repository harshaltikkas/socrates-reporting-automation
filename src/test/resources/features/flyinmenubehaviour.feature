@Feature1
Feature: Fly In Menu Behaviour
												##story 1b)
  @Scenario1 @TC2
  Scenario Outline: Arrows to Open and Close the Tab
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on open arrows of "<universaltablist>" tab within the Universal Selector Tab
    Then User should be able to open the slider for the "<universaltablist>" Tab and able to see the close arrows
    When User Click on close arrows for "<universaltablist>" tab within the Universal Selector Tab
    Then User should be able to close the slider for the "<universaltablist>" Tab and able to see the open arrows

    Examples: 
      | universaltablist |
      | Roster           |
      | Test             |

  @Scenario2 @TC3
  Scenario Outline: Roster Tab shows the dropdown list of Schools and Classes and Students to apply filters.
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Roster tab within the Universal Selector Tab
    Then User should be able to select School "<schoolname>" and Class "<classname>" and Student "<studentname>" drop downlist and click on apply filter button

    Examples: 
      | schoolname              | classname                       | studentname   |
      | Steiner Academy Bristol | Grade 2 Language Arts 10        | All           |
      | The Advance School      | English/Language Arts-Gr. 5 - A | John Bradford |

  @Scenario3 @TC4
  Scenario: Verify to close the Fly-In menu of the Roster Tab.
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Roster tab within the Universal Selector Tab
    Then User should be able to click on cancel button to close the Roster Tab.

  @Scenario4 @TC5
  Scenario Outline: Test Tab shows the list of all tests and allows an individual test and or multiple tests
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Test tab within the Universal Selector Tab
    Then User should be able to select "<testtype>" Test and click on apply filter button

    Examples: 
      | testtype |
      | single   |
      | multiple |

  @Scenario5 @TC6
  Scenario: Verify to close the Fly-In menu of the Test Tab.
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Test tab within the Universal Selector Tab
    Then User should be able to click on cancel button to close the Test Tab.

##  @Scenario6 @TC7
##  Scenario: Date Tab allows to select District Term
##    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
##    When User Click on Date tab within the Universal Selector Tab
##    Then User should be able to select district term and click on apply filter button

##  @Scenario7 @TC8
##  Scenario: Verify to close the Fly-In menu of the Date Tab.
##    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
##    When User Click on Date tab within the Universal Selector Tab
##    Then User should be able to click on cancel button to close the Date Tab.

  @Scenario8 @TC9
  Scenario Outline: Showing tool tip when tab hovered over
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User mousehover on "<universaltablistone>" tab within the Universal Selector Tab
    Then "<universaltablistone>" tab show tool tip "<tooltiptext>"

    Examples: 
      | universaltablistone | tooltiptext                        |
      | Roster              | Select Student, Class, Grade, etc. |
      | Test                | Select Test(s)                     |
##      | Date             | Select Date(District Term)         |
