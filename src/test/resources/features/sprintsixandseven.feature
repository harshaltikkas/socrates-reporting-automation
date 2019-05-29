@Feature1
Feature: Sprint 6 and Sprint 7 Stories

  ##Note: BE-630,659,660,661 all are already covered previously
  ## BE-649,662
  Scenario: Verify comparison band in class context in test score page selector
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User click on Class Context and Test Score button
    ##Failing for default school as the school avg is not matched with UI
    Then Veriy the comparison band for school and district

  ##BE-653,BE-681,654,
  Scenario: Verify tooltip icon and text of tooltip icon beside avg info
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Standard Performance tab within the Class Context
    Then Verify the tooltipicon in performance over time line chart and student list
    When User Click on Standard Performance tab within the Student Context
    Then Verify the tooltipicon in performance over time line chart
    When User click on Class Context and Test Score button
    Then Verify the tooltipicon is not present
    When User click on Student Context and Test Score button
    Then Verify the tooltipicon is not present

  ##BE-650,682,683,684
  ## This is failing because of test score label xpath changed on UI
  Scenario: Verify The diamond shape stroke on the x-axis and Color changes within the Line Charts
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Standard Performance tab within the Class Context
    Then verify the diamond shape stroke on the x-axis and Color changes within the Line Charts
    When User Click on Standard Performance tab within the Student Context
    Then verify the diamond shape stroke on the x-axis and Color changes within the Line Charts
    When User click on Class Context and Test Score button
    Then verify the diamond shape stroke on the x-axis and Color changes within the Line Charts
    When User click on Student Context and Test Score button
    Then verify the diamond shape stroke on the x-axis and Color changes within the Line Charts

  ## BE-666,685,677,686
  ##failing as, multiple xpath are available for schoolpath and districtpath
  Scenario: Verify Selection and Deselection of the Comparison options within the Line Charts for Particular Strand and standard
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Standard Performance tab within the Class Context
    Then verify the dotted line and triangle line on line chart based on selection of school and district and class checkbox
    When User Click on Standard Performance tab within the Student Context
    Then verify the dotted line and triangle line on line chart based on selection of school and district and class checkbox

  ## BE-678,689
  Scenario: Verify Selection and Deselection of the Comparison options within the Line Charts for Particular Test
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User click on Class Context and Test Score button
    Then Veriy the Selection and Deselection of the Comparison options within the Line Charts for Particular Test
