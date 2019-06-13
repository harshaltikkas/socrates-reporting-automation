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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
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
			Verify.verify(homePage.activeclassmenu.isEnabled());
			Verify.verify(homePage.activestandardperformancebtn.isEnabled());
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This is used to check only one button will be select at a time.
	 * @throws Throwable
	 */
	@Then("^user selects Test Scores button within the page selector then also the context will remain same$")
	public void user_selects_Test_Scores_button_within_the_page_selector_then_also_the_context_will_remain_same() throws Throwable {
		try {
			JavascriptExecutor jse2 = (JavascriptExecutor)Driver.webdriver;
			//Thread.sleep(10000);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			UtilityMethods.waitforcontextheadersaction();
			jse2.executeScript("arguments[0].click();", homePage.testscoresbtn);
			Thread.sleep(3000);
			Assert.assertTrue(homePage.activeclassmenu.getAttribute("class").contains("active"));
			try {
				Assert.assertEquals(false,homePage.activestandardperformancebtn.isDisplayed());
			    CBTConfiguration.score = "fail";
			}
			catch(Exception e) {
				log.info("Only one button is enable/selectable at a time");
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
			Standard_Overview_Table_Steps.resetStatus();
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
			Standard_Overview_Table_Steps.resetStatus();
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
			UtilityMethods.waitforpageload();

			List<WebElement> headerList = homePage.contextheadertextlist;
			if (Standard_Overview_Table_Steps.underStudentContext
					&& (Standard_Overview_Table_Steps.performanceMenuClicked
							|| Standard_Overview_Table_Steps.testScoreMenuClicked)) {
				Assert.assertTrue(headerList.get(0).getText().equals("Student:"));
				Assert.assertTrue(headerList.get(1).getText().equals("Class:"));
				Assert.assertTrue(headerList.get(2).getText().equals("School:"));
				Assert.assertTrue(headerList.get(3).getText().equals("Tests:"));
				Assert.assertTrue(headerList.get(4).getText().equals("Dates:"));
			}
			else if (Standard_Overview_Table_Steps.underClassContext && (Standard_Overview_Table_Steps.performanceMenuClicked
					|| Standard_Overview_Table_Steps.testScoreMenuClicked)) {
				Assert.assertTrue(headerList.get(0).getText().equals("Class:"));
				Assert.assertTrue(headerList.get(1).getText().equals("School:"));
				Assert.assertTrue(headerList.get(2).getText().equals("Tests:"));
				Assert.assertTrue(headerList.get(3).getText().equals("Dates:"));
			}
			
			String compareText,tooltiptext;
			for (int i = 0,tooltipcount=0; i < homePage.contextheaderdatalist.size(); i++) {
				if (homePage.contextheaderdatalist.get(i).getText().contains("...")) {
					if (Standard_Overview_Table_Steps.underClassContext && (Standard_Overview_Table_Steps.performanceMenuClicked
							|| Standard_Overview_Table_Steps.testScoreMenuClicked)) {
						Thread.sleep(5000);						
					}

					new Actions(Driver.webdriver).moveToElement(homePage.contextheaderdatalist.get(i)).perform();
					tooltiptext=homePage.contextheadertooltiplist.get(tooltipcount).getText();
					new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
					Thread.sleep(1000);
					compareText = UtilityMethods.elipsisRemoval(homePage.contextheaderdatalist.get(i).getText());
					Assert.assertTrue(tooltiptext.contains(compareText));
					Thread.sleep(500);
					tooltipcount++;
				}
			}
			
			String headerText;
			for (int i = 0; i < homePage.contextheaderdatalist.size(); i++) {
				headerText = homePage.contextheadertextlist.get(i).getText();
				new Actions(Driver.webdriver).moveToElement(homePage.contextheaderdatalist.get(i)).click().build().perform();;
				Thread.sleep(500);
				if (headerText.equals("Class:") || headerText.equals("School:") || headerText.equals("Student:")) {
					Assert.assertTrue(homePage.rostertab.isDisplayed());
					Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
				} else if (headerText.equals("Tests:")) {
					Assert.assertTrue(homePage.testtab.isDisplayed());
					Verify.verify(homePage.searchbarontesttab.isDisplayed());
				} else if (headerText.equals("Dates:") && Standard_Overview_Table_Steps.underClassContext) {
					  Assert.assertTrue(homePage.datetab.isDisplayed());
					  Verify.verify(homePage.districtNameOnSliderMenu.isDisplayed());					 
				} else {
					log.info("Nothing should be verify while clicking on Grade");
				}
				new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).click().build().perform();
			}
			Standard_Overview_Table_Steps.underClassContext=false;
			Standard_Overview_Table_Steps.underStudentContext=false;
			Standard_Overview_Table_Steps.testScoreMenuClicked=false;
			Standard_Overview_Table_Steps.performanceMenuClicked=false;
			
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 25 & 26 & 27 Completed");
	}

}
