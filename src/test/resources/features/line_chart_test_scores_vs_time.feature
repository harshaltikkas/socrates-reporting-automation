@Feature1
Feature: Line Chart Test Scores vs Time

  @Scenario31 @TC_0019
  Scenario: Verify the Y-axis label
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    Then verify y-axis labeled with 'Test Scores(%)' and also verify 12 horizontal lines, one for x axis line and rest label with '0' throught '100'

  @Scenario32 @TC_0020
  Scenario: Verify the X-axis label
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    Then verify x-axis labeled with 'Test Names' and show tooltip if elipsis in test names and if more than '10' tests are there then paginator should be display

  @Scenario33 @TC_0021
  Scenario: Verify the colours inside the circle within the line chart
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    Then verify The points on the chart should be circles that are color-coordinated according to achievement level

  @Scenario34 @TC_0024
  Scenario: Verify the Cutoffs range with colour for Achievement Level on line chart
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User click on Student Context
    Then Verify the Cutoffs range with colour for Achievement Level on line chart

  #This TC will fail because while traversing circle on after 10 circle overlay is not display on UI Properly
  @Scenario35 @TC_0028
  Scenario: Verify that how the overlay shown on the line chart can be closed
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User click on Student Context
    Then Verify the overlay should be closed and other is open if click on other circle
