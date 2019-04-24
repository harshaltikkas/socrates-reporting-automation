@Feature
Feature: Standard Overview Table and Chart
	
			##STORY 5a) Standards in Achievement Bands and Y axis / Calculating Cells
			##STORY 6c) Student and Class level and STORY 7d)
	#@Scenario36 @TC_0001 @TC_0002 @TC_0003 @TC_0005 @TC_0006 @TC_0007 @TC_0008 @TC_0009 @TC_00010
  Scenario: Verify The Y-axis UI representation
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Standard Performance tab within the Class Context
   		 ## TC-1
    Then The Y-axis of the table should be labeled as "Achievement Level" 
   		 ## TC-2
    #And The Y-axis should have the groupings strip of colours with the respective ranges in %
				##<40%” (red), "40-59%” (orange), "60-79%” (yellow) and "≥80%” (green)
				##TC-6
		#And user should able to see sort arrows with the red and green colours and when the user clicks on that arrow sorting should happen
				##e.g. 1)the colored triangles will flip to reflect the order of the Achievement Bands.
				##     2)the data also sorts within the respective groupings with the high range % at the top and low range % at the bottom.
				## TC-3,TC-5,TC-7,TC-8,TC-10, @5C-TC-20
		#When User Click on Standard Performance tab within the Class Context
				##5d-> TC 24,
		#Then click the standard and verify The colour should be changed to that of the achievement level where that standard is present
				## TC-9 ,Intermittently Failing , @5C-TC-19
		#When User Click on Standard Performance tab within the Student Context
		#Then The user should able to see the list of all the standards from different grades in the standards table
		
			##STORY 5b) Strand Averages and table X axis & 
			##STORY 5c) Cells by Student vs. Class Buttons	&
			##STORY 5d) Standards Table’s effect on Performance over Time Line Chart and Student List
 	#@Scenario37 @TC_0012 @TC_0013_DB @TC_0014 @TC_0015 @TC_0016 @TC_0017 @5C-TC-21 @5d-TC-22
 	#Scenario: Verify The X-Axis UI Representation 
    #Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    #When User Click on Standard Performance tab within the Class Context
	 		##TC-12 ,TC-13,5D->23
	 	#Then By default the first strands should be selected 
    #And The column headers should be populated as Strands as alphabetical order from left to right
    	##TC-14
    #And The X-axis should have the groupings strip of colours with the respective average scores in % range below their text as 'Avg.xx%'
 		#When User Click on Standard Performance tab within the Student Context
 			##5c-TC 21 
 		#Then first alphabetical student is the one picked when the teacher hits the student button
 			##5B->TC_0015 ,TC-16,TC-17
 		#And The User should be able to see the tool tip having full text of the strand.
 			##5D->TC 22
 		#And The User click on the strand in header then background of strand colour should be filled with achievement level colour
		
			##STORY 6a) Standard table / Performance over Time (Line Chart) relationship
 		#@Scenario38 @TC_0001 @TC_0002 @TC_0003
 	#Scenario: Verify Standard table related data on Performance over time chart
    #Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    #When User Click on Standard Performance tab within the Class Context
    #And Click on the icon to maximize the Chart
    	##TC-1 failing as info icon removed from UI on performance over time button
    #Then The user should be able to see the Chart header and the icon next to it can be selected and click on same icon to minimize the Chart
    	##TC-2
    #And Verify that the chart header should be the first alphabetical strand by defaultly  
    	##TC-3, Testing in Progress
    #And Select the Strand within the Strand header from the Standard table and selected strand becomes the header of the Line Chart
    
      ##STORY 6b) Axes on the Standards Line Chart And calculating Points
  	#@Scenario39 @TC_0006 @TC_0007 @TC_0008 @TC_0005_DB @TC_0009_DB 
 	#Scenario: Verify The X-axis and Y--axis of the Performance over time
   #Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
   #When User Click on Standard Performance tab within the Class Context
   #And Click on the icon to maximize the Chart
   #Then verify y-axis labeled with 'Test Scores(%)' and also verify 12 horizontal lines, one for x axis line and rest label with '0' throught '100'
   #And verify x-axis labeled with 'Test Names' and show tooltip if elipsis in test names and if more than '10' tests are there then paginator should be display
   #And verify The points on the chart should be circles that are color-coordinated according to achievement level
	
	   ##STORY 6f) Relationship between Line Chart for Test Scores at the Class Level and Student List module & STORY AND 7a) Standards and Test Score Detail
  	 ##STORY 6c) Student and Class level  	 	
  #@Scenario40 @TC_0029 @TC_0030_DB @TC_0031 @TC_0032  @TC_005 @TC_006 
 	#Scenario: Verify The appearance of the student list and the line chart, when viewing the class context for the Test Scores Overview
    #Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    #When User click on Class Context and Test Score button
    #Then line chart of Test score over time should be displayed to the right of it Student list should display
    #When User click on Student Context and Test Score button
    #Then line chart of Test score over time should be displayed of that student but Student list should not be displayed
    
      ##STORY 6d) Overlays  
  #@Scenario41 @TC_0015 @TC_0016 @TC_0017 @TC_0018
 	#Scenario: Verify Overlays Performance Over Time 
    #Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    #When User Click on Standard Performance tab within the Student Context
    #Then verify The points on the chart should be circles that are color-coordinated according to achievement level
    #When User Click on Standard Performance tab within the Class Context 
    #And Click on the icon to maximize the Chart
  	#Then User click on the circle within the line chart and should able to see the overlay of Tool tip which have following items
  		##i)Full name of the Test at the top 
  		##ii)Date range when the test was submitted 
  		##iii)Questions that contain the standard or strand: “Questions:” followed by each question number in a box.  
    	##Note: If a user selects one of these questions, a new browser tab opens the Student View of the Grading page with the appropriate question displayed
    	##The above note Requirement will complete in next milestone
    	########### Failing because , on UI ,page one,4th score from last is not visible.
   	#When User Click on Standard Performance tab within the Student Context 
   	#Then User click on the circle within the line chart and should able to see the overlay of Tool tip which have following items
   	
   	 	##STORY 7b) Achievement Filter and Sorting 	
  #@Scenario42 @TC_0015 @TC_0016 @TC_0017
 	#Scenario: Verify The top of the table contains colored sections that can be selected to filter which students are displayed in the chart & The blue line appearing under the selected section
    #Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    #When User Click on Standard Performance tab within the Class Context
    #Then different coloured strips should be displayed 
    ##e.g. The coloured strips are as follows:
     ##All” - default selection with a white strip 
     ##“<40%” with a red strip 
     ##“40-59%” with an orange strip 
     ##“60-79%” with a yellow strip 
     ##“≥80%” with a green strip
    #And click on different coloured strips,blue strip should be display under the clicked strip and the no. of student records with that colour should be display 
     	
  	##STORY 7a) Standards and Test Score Detail
  	#@Scenario43 @TC_001 @TC_002 @TC_004 @TC_007 @TC_008 @TC_009 @TC_0010 @TC_0011 @TC_0012 @TC_0013 @TC_0014 
	 	#Scenario: Verify Student List for Standard Overview tab when looking at a Class Context
    #Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    #When User Click on Standard Performance tab within the Class Context
  	#Then user should able to see Student List and the icon next to it can be selected to minimize or maximize the Student List window
  	#And Verify the left most column header should be 'Student (X)'
  	#And Verify the right most column header should be 'Score'
  	#And All the column headers should be sortable with ascending and descending order
  	#And select the student within the student list and Student context with information for that individual student should be shown
  	########## failing
  	#When Click or select strand and standard from the standard table
  	#Then The student list related to the selected standard within the perticular strand with the header and the subheader is displayed  	
  