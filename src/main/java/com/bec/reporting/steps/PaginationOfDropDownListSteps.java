/*
 * Copyright Benchmark Education Company
 *
 * (C) Copyright BEC - All rights reserved.
 *
 * NOTICE:  All information contained herein or attendant here to is,
 *          and remains, the property of Benchmark.  Many of the
 *          intellectual and technical concepts contained herein are
 *          proprietary to Benchmark. Any dissemination of this
 *          information or reproduction of this material is strictly
 *          forbidden unless prior written permission is obtained
 *          from Benchmark.
 *
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 * Revision History
 * ========================================================================
 * DATE				: PROGRAMMER  : DESCRIPTION
 * ========================================================================
 * JAN 21 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.steps;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.IWait;
import com.bec.reporting.utils.UtilityMethods;
import com.google.common.base.Verify;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaginationOfDropDownListSteps {
	
	/**
	 *This is used to initialize webelement of the webpages 
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	static int listCount=0,AllPagetotalListItem=0;
	int countPerPage = 0, pageCount = 0,totalCount=0;
	
	/**
	 * This method used to click on roster tab and click on school,class and student drop down 
	 * @param rosterDropDown
	 * @param totalListItem
	 * @throws Throwable
	 */
	@When("^user clicks on Roster tab and clicks on \"([^\"]*)\" dropdown list,list items are more than (\\d+)$")
	public void user_clicks_on_Roster_tab_and_clicks_on_dropdown_list_list_items_are_more_than(String rosterDropDown, int totalListItem) throws Throwable {
		try {
			UtilityMethods.wait_For_Context_Header_Section();
			AllPagetotalListItem=totalListItem;
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.studentTitleOnSliderMenu);
			Verify.verify(homePage.studentTitleOnSliderMenu.isDisplayed());
			Thread.sleep(500);
			Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div/button"))
			.click();
			Thread.sleep(500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method used to verify display paginator in school,class and student dropdown with LHS and RHS arrows and circles 
	 * @param rosterDropDown
	 * @param itemssizeperpage
	 * @throws Throwable
	 */
	@Then("^Paginator will be displayed beneath the list if \"([^\"]*)\" returns more than \"([^\"]*)\" items and arrows will appear in both side of circle in paginator$")
	public void paginator_will_be_displayed_beneath_the_list_if_returns_more_than_items_and_arrows_will_appear_in_both_side_of_circle_in_paginator(String rosterDropDown, String itemssizeperpage) throws Throwable {
		try {
			checkPagination(rosterDropDown,itemssizeperpage);
			CBTConfiguration.score = "pass";
			Standard_Overview_Table_Steps.resetStatus();
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 14 Completed");
	}

	/**
	 * This method used to verify display paginator in test tab with LHS and RHS arrows and circles 
	 * @param listItemPerPage
	 * @param itemLimitForPagination
	 * @throws Throwable
	 */
	@Then("^Paginator will be displayed,test list items per page should be (\\d+) and totalitemlist more than (\\d+) items, arrows will appear in both side of circle in paginator$")
	public void paginator_will_be_displayed_test_list_items_per_page_should_be_and_totalitemlist_more_than_items_arrows_will_appear_in_both_side_of_circle_in_paginator(int listItemPerPage, int itemLimitForPagination) throws Throwable {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);
			int pageCount=0;
			boolean doneWithThreeCircle = false, disableRightArrowFound = false, enabledRightArrowFound = false,
					paginatorFound = false;
			WebElement  enabledRightArrow = null,enabledLeftArrow=null;
			List<WebElement> circleList = null;
			try {
				homePage.testpaginator.isDisplayed();
				paginatorFound=true;
			}
			catch(Exception e) {
				log.info("Paginator Not Found");
			}
			if (paginatorFound) {
				try {
					circleList=homePage.testpaginationcirclelist;
					enabledRightArrow=homePage.testenabledrightarrow;
					enabledRightArrow.isDisplayed();
					enabledRightArrowFound=true;
				}
				catch(Exception e) {
					log.info("Enabled Right Arrow on Paginator is not found");
				}
				if (enabledRightArrowFound) {
					do {
						try {
							homePage.testdisabledrightarrow.isDisplayed();
							disableRightArrowFound=true;
						}
						catch(Exception e) {
							log.info("Disabled Right Arrow on Paginator is not found");
						}
						if(doneWithThreeCircle) {
							circleList.get(2).click();
							Thread.sleep(500);
							pageCount++;
							countPerPage = homePage.testscheckboxlist.size();
							totalCount+=countPerPage;
							log.info(" list size on page:"+pageCount+" is "+countPerPage);
							Assert.assertTrue(countPerPage<=listItemPerPage);
						}
						else {
						for (int i = 0; i < circleList.size(); i++) {
							circleList.get(i).click();
							Thread.sleep(500);
							pageCount++;
							countPerPage = homePage.testscheckboxlist.size();
							totalCount+=countPerPage;
							log.info(" list size on page:"+pageCount+" is "+countPerPage);
							Assert.assertTrue(countPerPage<=listItemPerPage);
						}
						doneWithThreeCircle=true;
						}
						try {
						enabledRightArrow.click();
						Thread.sleep(500);
						enabledLeftArrow=homePage.testenabledleftarrow;
						enabledLeftArrow.isDisplayed();
						}
						catch(Exception e) {}
					}
					while(!disableRightArrowFound);
				} 
				else {
					for (int i = 0; i < circleList.size(); i++) {
						circleList.get(i).click();
						Thread.sleep(500);
						pageCount++;
						countPerPage = homePage.testscheckboxlist.size();
						totalCount+=countPerPage;
						log.info(" list size on page:"+pageCount+" is "+countPerPage);
						Assert.assertTrue(countPerPage<=listItemPerPage);

					}
				}
				paginatorFound=false;
			}
			else {
				pageCount++;
				countPerPage = homePage.testscheckboxlist.size();
				totalCount+=countPerPage;
				log.info(" list size on page:"+pageCount+" is "+countPerPage);
				Assert.assertTrue(countPerPage<=listItemPerPage);

			}
			UtilityMethods.scrollPageUp(Driver.webdriver);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 15 Completed");
	}
	
	/**
	 * This method click on roster tab,select options from school,class and student dropdown 
	 * @param rosterDropDown
	 * @param itemsize
	 * @throws Throwable
	 */
	@When("^user clicks on Roster tab and clicks on \"([^\"]*)\" dropdown list and select the options from it and check pagination for \"([^\"]*)\"\\.$")
	public void user_clicks_on_Roster_tab_and_clicks_on_dropdown_list_and_select_the_options_from_it_and_check_pagination_for(String rosterDropDown, String itemsize) throws Throwable {
		try {
			UtilityMethods.wait_For_Context_Header_Section();
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.studentTitleOnSliderMenu);
			Verify.verify(homePage.studentTitleOnSliderMenu.isDisplayed());
			Thread.sleep(500);
			WebElement ddbtn=Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div/button"));
			if(!ddbtn.getText().contains("...")) {
				ddbtn.click();
				Thread.sleep(1000);
				List<WebElement> list=Driver.webdriver.findElements(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//li"));
				listCount=list.size();
				if(listCount>=Integer.parseInt(itemsize)) {
					WebElement paginator=Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='selector-pagination']"));
					Verify.verify(paginator.isDisplayed());
				}
				int selectedItemfromList=(int) (Math.random()*listCount);
				UtilityMethods.scrollPageDown(Driver.webdriver,selectedItemfromList);
				Thread.sleep(500);
				list.get(selectedItemfromList).click();	
				Thread.sleep(500);				
			}

	} catch (Exception e) {
		UtilityMethods.processException(e);
	}
 }

	/**
	 * This method validate whether paginator not display once option selected from dropdown.
	 * @param rosterDropDown
	 * @param itemsize
	 * @throws Throwable
	 */
	@Then("^Paginator will be disappears when selection is made and list is closed of \"([^\"]*)\" and \"([^\"]*)\"\\.$")
	public void paginator_will_be_disappears_when_selection_is_made_and_list_is_closed_of_and(String rosterDropDown, String itemsize) throws Throwable {
		try {
			if(listCount>=Integer.parseInt(itemsize)) {
				try {
				Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='selector-pagination']")).isDisplayed();
				}
				catch(NoSuchElementException e1) {
				}
			}
			CBTConfiguration.score = "pass";				
		} catch (Exception e) {
		UtilityMethods.processException(e);
	}
	log.info("Scenario 16 Completed");
	}
	
	/**
	 * This method called when checkpagination calling from the scenario method
	 * @param rosterDropDown
	 * @param itemssizeperpage
	 * @throws InterruptedException
	 */
	public void checkPagination(String rosterDropDown,String itemssizeperpage) throws InterruptedException {
		UtilityMethods.scrollPageDown(Driver.webdriver, 6);
		boolean doneWithThreeCircle = false, disableRightArrowFound = false, enabledRightArrowFound = false,
				paginatorFound = false;
		WebElement enabledRightArrow = null,paginator=null,enabledLeftArrow=null;
		List<WebElement> circleList = null,listItems;
		try {
			paginator=Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='selector-pagination']"));
			paginator.isDisplayed();
			paginatorFound = true;
		} catch (Exception e) {
 			log.info("Paginator Not Found on "+rosterDropDown);
		}
		if (paginatorFound) {
			try {
				circleList = Driver.webdriver.findElements(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='PaginationBubblesAndCount']//ul/li"));;
				enabledRightArrow =Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//span[@class='scroll-right scroll-active float-right']/i"));
				enabledRightArrowFound = true;
			} catch (Exception e) {
				log.info("Enabled Right Arrow on Paginator is not found");
			}
			if (enabledRightArrowFound) {
				do {
					try {
						Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//span[@class='scroll-right float-right scroll-disable']/i")).isDisplayed();
						disableRightArrowFound = true;
					} catch (Exception e) {
						log.info("Disabled Right Arrow on Paginator is not found");
					}
					if (doneWithThreeCircle) {
						circleList.get(2).click();
						Thread.sleep(500);
						pageCount++;
						listItems=Driver.webdriver.findElements(By.xpath(
								"//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//div//li"));
						countPerPage = listItems.size();
						totalCount+=countPerPage;
						log.info(rosterDropDown+" list size on page:"+pageCount+" is "+countPerPage);
						Assert.assertTrue(countPerPage<=Integer.parseInt(itemssizeperpage));
					} else {
						for (int i = 0; i < circleList.size(); i++) {
							circleList.get(i).click();
							Thread.sleep(500);
							pageCount++;
							listItems=Driver.webdriver.findElements(By.xpath(
									"//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//div//li"));
							countPerPage = listItems.size();
							totalCount+=countPerPage;
							log.info(rosterDropDown+" list size on page:"+pageCount+" is "+countPerPage);
							Assert.assertTrue(countPerPage<=Integer.parseInt(itemssizeperpage));
						}
						doneWithThreeCircle = true;
					}
					try {
						enabledRightArrow.click();
						Thread.sleep(500);
						enabledLeftArrow =Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//span[@class='scroll-left float-left scroll-active ']"));
						Assert.assertTrue(enabledLeftArrow.isDisplayed());
					} catch (Exception e) {
					}
				} while (!disableRightArrowFound);
			} else {
				for (int i = 0; i < circleList.size(); i++) {
					circleList.get(i).click();
					Thread.sleep(500);
					listItems=Driver.webdriver.findElements(By.xpath(
							"//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//div//li"));
					countPerPage = listItems.size();
					log.info(rosterDropDown+" list size on page:"+pageCount+" is "+countPerPage);
					Assert.assertTrue(countPerPage<=Integer.parseInt(itemssizeperpage));
				}
			}

		} else {
			pageCount++;
			listItems=Driver.webdriver.findElements(By.xpath(
					"//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//div//li"));
			countPerPage = listItems.size();
			log.info(rosterDropDown+" list size on page:"+pageCount+" is "+countPerPage);
			Assert.assertTrue(countPerPage<=Integer.parseInt(itemssizeperpage));
		}
		if(paginatorFound) {
			Assert.assertTrue(AllPagetotalListItem<=totalCount);
			totalCount=0;
		}
		UtilityMethods.scrollPageUp(Driver.webdriver);
	}

}
