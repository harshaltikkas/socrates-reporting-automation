@Feature
Feature: Custom Test Handling in Reporting - sprint 54

  @BU-12959
  Scenario: verify Test tab Context header - Custom Test Handling in Reporting for Deleting Question from any test or assessment
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then Test tab context header custom test handling in reporting for deleting question from any test or assessment

	#Failing because of on STA,custom deleted test info icon not available
  @BU-12961 
  Scenario: verify STA reports - Custom Test Handling in Reporting for Deleting Question from any test/assessment
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify STA reports custom test handling in reporting for deleting question from any test or assessment

  @BU-12963
  Scenario: verify Standard Performance Overtime Line Graph - Custom Test Handling in Reporting for Deleting Question from any test or assessment
    Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
    Then verify standard performance overtime line graph - custom test handling in reporting for deleting question from any test or assessment

 ## This below scenarios are not in scope 
 # @BU-12967 @BU-12970 
 # Scenario: verify SUMMARY - Summary layout with landing page - Summary Reports behaviour
 #   Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
 #   Then verify summary layout with landing page

 # @BU-12968
 # Scenario: verify SUMMARY - no data screens within widgets for summary reports
 #   Given User is on portal's login screen with username as "district_admin_one" and password as "password" and usertype as "realm_one"
 #   Then verify summary no data screens within widgets for summary reports
