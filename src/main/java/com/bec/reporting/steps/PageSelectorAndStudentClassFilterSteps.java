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
 * FEB 04 2019		: BEC         : CREATED.
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
import org.openqa.selenium.interactions.Actions;
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
public class PageSelectorAndStudentClassFilterSteps {
	
	/**
	 *This is used to initialize webelement of the webpages 
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	
	/**
	 * This method used to check default selection of standard performance and class context selection
	 * @throws Throwable
	 */
	@When("^default Standards Performance button should be selected with the Class context$")
	public void default_Standards_Performance_button_should_be_selected_with_the_Class_context() throws Throwable {
		try {
			Verify.verify(homePage.activeclass.isEnabled());
			Verify.verify(homePage.activestandardperformancebtn.isEnabled());
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * Thi is used to check only one button will be select at a time.
	 * @throws Throwable
	 */
	@Then("^user selects Test Scores button within the page selector then also the context will remain same$")
	public void user_selects_Test_Scores_button_within_the_page_selector_then_also_the_context_will_remain_same() throws Throwable {
		try {
			homePage.testscoresbtn.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.activeclass.isEnabled());
			try {
			homePage.activestandardperformancebtn.isEnabled();
			CBTConfiguration.score = "fail";
			}
			catch(NoSuchElementException e) {
				System.out.println("Only one button is enable/selectable at a time");
				CBTConfiguration.score = "pass";
			}			
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 22 Completed");
	}
	
	/**
	 * This method is used to open reporting key 
	 * @throws Throwable
	 */
	@When("^User click on fly in key to open reporing key$")
	public void user_click_on_fly_in_key_to_open_reporing_key() throws Throwable {
	try {
		Actions action=new Actions(Driver.webdriver);
		action.moveToElement(homePage.footer).build().perform();
		Thread.sleep(1000);
		homePage.reportingkey.click();
	}catch (Exception e) {
		UtilityMethods.processException(e);
	}
	}

	/**
	 * This method used to verify the achievement levels with different colour on reporting key
	 * @throws Throwable
	 */
	@Then("^verify the achievement leves with different colour codes and click on fly in key to close it\\.$")
	public void verify_the_achievement_leves_with_different_colour_codes_and_click_on_fly_in_key_to_close_it() throws Throwable {
		try {
			Thread.sleep(500);
			Assert.assertTrue(homePage.achievmentlevelslabel.isDisplayed());
			Assert.assertTrue(homePage.alallwithgraycolor.isDisplayed());
			Assert.assertTrue(homePage.albelow40withredcolor.isDisplayed());
			Assert.assertTrue(homePage.al40_59withorangecolor.isDisplayed());
			Assert.assertTrue(homePage.al60_79withyellowcolor.isDisplayed());
			Assert.assertTrue(homePage.al80pluswithgreencolor.isDisplayed());
			homePage.reportingkey.click();
			CBTConfiguration.score="pass";
		}catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 23 Completed");
	}
	
	/**
	 *  This method is used to verify footer left hand side and right hand side text 
	 * @throws Throwable
	 */
	@Then("^Verify left side footer text and right side footer text$")
	public void verify_left_side_footer_text_and_right_side_footer_text() throws Throwable {
		try {
			Actions action=new Actions(Driver.webdriver);
			action.moveToElement(homePage.footer).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.footerleftside.getText().equals("© Benchmark Education Company LLC All rights reserved."));
			Assert.assertTrue(homePage.footerrightside.getText().equals("Privacy Policy"));
			String privacyUrl="http://help.benchmarkuniverse.com/bustudent/#Student%20Customer%20Support/Privacy%20Policy.htm";
			Assert.assertTrue(homePage.footerrightside.getAttribute("href").equals(privacyUrl));
			CBTConfiguration.score="pass";
		}catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 24 Completed");
	}
	

	/**
	 * This method is used to verify the heading on context header for standard performance & Test Score menu
	 * @throws Throwable
	 */
	@Then("^Verify labels on context header based on standard performance and test scores button$")
	public void verify_labels_on_context_header_based_on_standard_performance_and_test_scores_button() throws Throwable {
		try {
			homePage.studentmenu.click();
			Thread.sleep(500);
			List<WebElement> headerList = homePage.contextheadertextlist;
			Assert.assertTrue(headerList.get(0).getText().equals("Student:"));
			Assert.assertTrue(headerList.get(1).getText().equals("School:"));
			Assert.assertTrue(headerList.get(2).getText().equals("Class:"));
			Assert.assertTrue(headerList.get(3).getText().equals("Tests:"));
			Assert.assertTrue(headerList.get(4).getText().equals("Dates:"));
			homePage.classmenu.click();
			Thread.sleep(500);
			headerList = homePage.contextheadertextlist;
			Assert.assertTrue(headerList.get(0).getText().equals("Class:"));
			Assert.assertTrue(headerList.get(1).getText().equals("Grade:"));
			Assert.assertTrue(headerList.get(2).getText().equals("School:"));
			Assert.assertTrue(headerList.get(3).getText().equals("Tests:"));
			Assert.assertTrue(headerList.get(4).getText().equals("Dates:"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 25 Completed");
	}
	

	/**
	 * This method is used to check full text on elipsis content on context header as data.
	 * @param menuType
	 * @throws Throwable
	 */
	@Then("^verify if context header data have an elipsis then it should show full data in tooltip for \"([^\"]*)\"$")
	public void verify_if_context_header_data_have_an_elipsis_then_it_should_show_full_data_in_tooltip_for(String menuType) throws Throwable {
		try {
			WebElement menu=Driver.webdriver.findElement(By.xpath("//div[@class='single-filter-text' and contains(text(),'"+menuType+"')]/preceding-sibling::div"));
			menu.click();
			Thread.sleep(500);
			Actions action = new Actions(Driver.webdriver);
			String compareText;
			List<WebElement> dataList = homePage.contextheaderdatalist;
			for (int i = 0, tooltipcount = 0; i < dataList.size(); i++) {
				if (dataList.get(i).getText().contains("...")) {
					compareText = UtilityMethods.elipsisRemoval(dataList.get(i).getText());
					action.moveToElement(dataList.get(i)).build().perform();
					Assert.assertTrue(
							homePage.contextheadertooltiplist.get(tooltipcount).getText().contains(compareText));
					tooltipcount++;
					action.moveToElement(menu).build().perform();
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 26 Completed");
	}
	
	/**
	 * This method is used to verify when user click on any data on contxt header data then associated tab in universal selector should be popout.
	 * @param menuType
	 * @throws Throwable
	 */
	@Then("^Verify the tab opens when the user clicks on the data\\(label\\) having clickable links\\. for \"([^\"]*)\"$")
	public void verify_the_tab_opens_when_the_user_clicks_on_the_data_label_having_clickable_links_for(String menuType) throws Throwable {
		try {
			WebElement menu = Driver.webdriver
					.findElement(By.xpath("//div[@class='single-filter-text' and contains(text(),'" + menuType
							+ "')]/preceding-sibling::div"));
			menu.click();
			Thread.sleep(500);
			String headerText;
			Actions action = new Actions(Driver.webdriver);
			List<WebElement> headerList = homePage.contextheadertextlist;
			List<WebElement> dataList = homePage.contextheaderdatalist;
			for (int i = 0; i < dataList.size(); i++) {
				headerText = headerList.get(i).getText();
				dataList.get(i).click();
				Thread.sleep(500);
				if (headerText.equals("Class:") || headerText.equals("School:") || headerText.equals("Student:")) {
					Assert.assertTrue(homePage.rostertab.isDisplayed());
					Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
				} else if (headerText.equals("Tests:")) {
					Assert.assertTrue(homePage.testtab.isDisplayed());
					IWait.explicit_wait(Driver.webdriver, homePage.searchbarontesttab);
					Verify.verify(homePage.searchbarontesttab.isDisplayed());
				} else if (headerText.equals("Dates:")) {
					// This code is comment out because dates tab is disabled for this milestone
					/*
					 * Assert.assertTrue(homePage.datetab.isDisplayed());
					 * IWait.explicit_wait(Driver.webdriver, homePage.districtNameOnSliderMenu);
					 * Verify.verify(homePage.districtNameOnSliderMenu.isDisplayed());
					 */
				} else {
					System.out.println("Nothing should be verify while clicking on Grade");
				}
				action.moveToElement(homePage.overviewtext).click().build().perform();
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 27 Completed");
	}	
}
