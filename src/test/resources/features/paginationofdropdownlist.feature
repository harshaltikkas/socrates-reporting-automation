@Feature1 
Feature: Pagination of Dropdown List

#  @Scenario14 @TC22 @TC24 @TC26
#  Scenario Outline: verifying list size and paginator features of Roster tab
#  Given User is on sso portal's home page
#	When user clicks on Roster tab and clicks on "<rostertabdropdownlist>" dropdown list
#	Then Paginator will be displayed beneath the list if "<rostertabdropdownlist>" returns more than "<itemssize>" items
#	When list items are more than 30
#	Then arrows will appear in both side of circle in paginator

#   Examples: 
#      |rostertabdropdownlist|	itemssize	|																												
#      | School   				  	|		10			|
#      | Class 				 			|		10			|
#      | Student	 						|		10			|	

#  @Scenario15 @TC23 @TC25 @TC27
      
    @Scenario16 @TC28
  Scenario Outline: verifying Pagination disappears when selection is made and the list is closed
  Given User is on sso portal's home page
	When user clicks on Roster tab and clicks on "<rostertabdropdownlist>" dropdown list and select the options from it and check pagination for "<itemssize>".
	Then Paginator will be disappears when selection is made and list is closed of "<rostertabdropdownlist>" and "<itemssize>".

   Examples: 
      |rostertabdropdownlist|	itemssize	|																												
      | School   				  	|		10			|
      | Class 				 			|		10			|
      | Student	 						|		10			|	
      
#   @Scenario17 @TC29 not in scope 
#  Scenario: Verifying the availability of the paginator option in Date tab.
      