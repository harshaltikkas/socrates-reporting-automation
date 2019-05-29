@Feature1
Feature: Pagination of Dropdown List

  ##story 1d)
  @Scenario14 @TC22 @TC24 @TC26 @TC38 @TC41
  Scenario Outline: verifying list size and paginator features of Roster tab
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When user clicks on Roster tab and clicks on "<rostertabdropdownlist>" dropdown list,list items are more than 30
    Then Paginator will be displayed beneath the list if "<rostertabdropdownlist>" returns more than "<itemssizeperpage>" items and arrows will appear in both side of circle in paginator

    Examples: 
      | rostertabdropdownlist | itemssizeperpage |
      | School                |               10 |
      | Class                 |               10 |
      | Student               |               10 |

  @Scenario15 @TC23 @TC25 @TC27
  Scenario: verifying list size and paginator features of Test tab
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When User Click on Test tab within the Universal Selector Tab
    Then Paginator will be displayed,test list items per page should be 10 and totalitemlist more than 30 items, arrows will appear in both side of circle in paginator

  @Scenario16 @TC28
  Scenario Outline: verifying Pagination disappears when selection is made and the list is closed
    Given User is on portal's login screen with username as "superadmin" and password as "password" and usertype as "techsupport"
    When user clicks on Roster tab and clicks on "<rostertabdropdownlist>" dropdown list and select the options from it and check pagination for "<itemssize>".
    Then Paginator will be disappears when selection is made and list is closed of "<rostertabdropdownlist>" and "<itemssize>".

    Examples: 
      | rostertabdropdownlist | itemssize |
      | School                |        10 |
      | Class                 |        10 |
      | Student               |        10 |
