@Feature
Feature: Sprint 11 & Sprint 12 & Sprint 14 User Stories
		##BE-918,919
   Scenario: Verify Showing Window, Moving Student from one group to another group within the Grouping page 
   Given User is on portal's login screen with username as "AliceTPitts" and password as "password" and usertype as "realm1"
   Then verify Showing Window, Moving Student from one group to another group within the Grouping page
   
   ##BE-941 
   Scenario: Verify to check the appearance and behaviour of the teacher dropdown within the Roster tab 
   Given User is on portal's login screen with username as "JacquelineEIkard" and password as "password" and usertype as "realm1"
   When User Click on Roster tab within the Universal Selector Tab
   Then verify to check the appearance and behaviour of the teacher dropdown within the Roster tab 
   
   ##BE-944,945 ,949 a,950,951 DB pending,952
   #Scenario: Verify School filter default view reports for the user School admin 
   #Given User is on portal's login screen with username as "SarahMMerritt" and password as "password" and usertype as "realm1"
   #Then verify School filter default view reports for the user School admin 
   
  ##BE-946 ,949 b,956,953,954,955,
  Scenario: Verify District filter default view reports for the user District admin
  Given User is on portal's login screen with username as "JacquelineEIkard" and password as "password" and usertype as "realm1"
  Then verify District filter default view reports for the user District admin 
      
   ##BE-983 
   Scenario: Verify No test data available for this selection screen on selection from the Roster tab 
   Given User is on portal's login screen with username as "DanielleSPollard" and password as "password" and usertype as "realm1"
   Then verify No test data available for this selection screen on selection from the Roster tab
   
    ##BE-989 
   Scenario: Verify Showing No data screen for Date range which has no test data within the date tab
   Given User is on portal's login screen with username as "DanielleSPollard" and password as "password" and usertype as "realm1"
   Then verify Showing No data screen for Date range which has no test data within the date tab
   
    ##BE-988 
   Scenario: Verify No data screen for District term which has no test data within the date tab 
   Given User is on portal's login screen with username as "DanielleSPollard" and password as "password" and usertype as "realm1"
   Then verify No data screen for District term which has no test data within the date tab 
   
    ##BE-990
   Scenario: Verify No data screen for custom date selected which has no test data within the date tab  
   Given User is on portal's login screen with username as "DanielleSPollard" and password as "password" and usertype as "realm1"
   Then verify No data screen for custom date selected which has no test data within the date tab  
   
    ##BE-1130
   Scenario: Verify Screen shown to the user if the standards are null the reporting db  
   Given User is on portal's login screen with username as "ShadOSmith" and password as "password" and usertype as "realm1"
   Then verify Screen shown to the user if the standards are null the reporting db
   
  ##BE-1127
   Scenario: Verify Screen shown to the user for any kind of Technical Error 
   ##Teacher
   Given User is on portal's login screen with username as "DebraKAlbritton" and password as "password" and usertype as "realm2"
   Then verify Screen shown to the user for any kind of Technical Error
    ##School Admin- failing as not able to login
   ##Given User is on portal's login screen with username as "ViolaDLivingston" and password as "password" and usertype as "realm1"
   ##Then verify Screen shown to the user for any kind of Technical Error
    ##District Admin
   Given User is on portal's login screen with username as "dev_districtadmin1" and password as "password" and usertype as "dev"
   Then verify Screen shown to the user for any kind of Technical Error
   
   