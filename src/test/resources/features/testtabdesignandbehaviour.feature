@Feature1
Feature: Test Tab Design and Behaviour

  @Scenario18 @TC2
  Scenario: Verify that the checkbox for ALL option
    Given User is on sso portal's home page
    When User Click on Test tab within the Universal Selector Tab and click on 'All Checkbox'
    Then verify all the tests within that test list must be selected.
