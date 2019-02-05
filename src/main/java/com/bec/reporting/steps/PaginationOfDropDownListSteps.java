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
	static int listCount=0;
	
	/**
	 * This method used to click on roster tab and click on school,class and student drop down 
	 * @param rosterDropDown
	 * @throws Throwable
	 */
	@When("^user clicks on Roster tab and clicks on \"([^\"]*)\" dropdown list$")
	public void user_clicks_on_Roster_tab_and_clicks_on_dropdown_list(String rosterDropDown) throws Throwable {
		try {
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
			Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
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
	 * @param listSize
	 * @throws Throwable
	 */
	@Then("^Paginator will be displayed beneath the list if \"([^\"]*)\" returns more than (\\d+) items$")
	public void paginator_will_be_displayed_beneath_the_list_if_returns_more_than_items(String rosterDropDown, int listSize) throws Throwable {
		try {
			List<WebElement> itemlist=Driver.webdriver.findElements(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//div//li"));
			Assert.assertTrue(itemlist.size()<=listSize);
			if(itemlist.size()==listSize) {
				WebElement paginator=Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='selector-pagination']"));
				Assert.assertTrue(paginator.isDisplayed());
			//TODO need to verify pagination element here i.e. both arrows and circle
				WebElement leftArrow=Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//span[@class='scroll-left float-left scroll-disable']"));
				WebElement rightArrow=Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//span[@class='scroll-right scroll-active float-right']"));
				List<WebElement> circleList=Driver.webdriver.findElements(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div//div[@class='PaginationBubblesAndCount']//ul[@class='m-0 page-circle-list']//li"));
				do {
					for (int i = 0; i < circleList.size(); i++) {
						circleList.get(i).click();
						Thread.sleep(500);
					}
					rightArrow.click();
				}while(rightArrow.isEnabled());
			}
			CBTConfiguration.score = "pass";				
			if(itemlist.size()>listSize) {
				CBTConfiguration.score = "fail";				
				Assert.fail();
			}

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 14 Completed");
	}

	/**
	 * This method will count the total list items on each page 
	 * @param listItemSize
	 * @throws Throwable
	 */
	@When("^list items are more than (\\d+)$")
	public void list_items_are_more_than(int listItemSize) throws Throwable {
	}

	/**
	 * This method will show Arrows on both side
	 * @throws Throwable
	 */
	@Then("^arrows will appear in both side of circle in paginator$")
	public void arrows_will_appear_in_both_side_of_circle_in_paginator() throws Throwable {
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
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
			Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
			Thread.sleep(500);
			Driver.webdriver.findElement(By.xpath("//div[@class='menu-title' and contains(text(),'"+rosterDropDown+"')]/following-sibling::div/button"))
			.click();
			Thread.sleep(500);
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
				catch(org.openqa.selenium.NoSuchElementException e1) {
				}
			}
			CBTConfiguration.score = "pass";				
		} catch (Exception e) {
		UtilityMethods.processException(e);
	}
	log.info("Scenario 16 Completed");
	}

}
