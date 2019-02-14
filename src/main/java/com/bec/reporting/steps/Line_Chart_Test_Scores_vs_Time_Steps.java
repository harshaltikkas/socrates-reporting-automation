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
 * FEB 11 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.steps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.UtilityMethods;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Line_Chart_Test_Scores_vs_Time_Steps {
	/**
	 *This is used to initialize webelement of the webpages 
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	
	String tooltiptext,attr;
	/**
	 * This method used to verify the y-axis label as Test Scores , verify horizonatal line sum as 11 and the lines lables 
	 * @throws Throwable
	 */
	@Then("^verify y-axis labeled with 'Test Scores\\(%\\)' and also verify (\\d+) horizontal lines, one for x axis line and rest label with '(\\d+)' throught '(\\d+)'$")
	public void verify_y_axis_labeled_with_Test_Scores_and_also_verify_horizontal_lines_one_for_x_axis_line_and_rest_label_with_throught(int arg1, int arg2, int arg3) throws Throwable {
		try {			
			int range=0;
			Assert.assertTrue(homePage.yaxistexton_linecharttestscorevstime.getText().equals("Test Scores (%)"));
			Assert.assertTrue(homePage.horizontalline_onlinecharttestscorevstime.size()==11);
			List<WebElement> list=homePage.yaxislabelsonhorizontalline_onlinecharttestscorevstime;
			for (int i = 0; i < list.size(); i++) {
				Assert.assertTrue(Integer.parseInt(list.get(i).getText())==range);
				range+=10;
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 31 Completed");
	}
	
	/**
	 *This method used to verify x-axis label on line chart test score over time and the names of test list on graph with elipsis and tooltip text. 
	 * @throws Throwable
	 */
	@Then("^verify x-axis labeled with 'Test Names' and show tooltip if elipsis in test names and if more than '(\\d+)' tests are there then paginator should be display$")
	public void verify_x_axis_labeled_with_Test_Names_and_show_tooltip_if_elipsis_in_test_names_and_if_more_than_tests_are_there_then_paginator_should_be_display(int arg1) throws Throwable {
		try {
			int testNamesSizeOnPerPage=0;
			String testName;
			Actions action=new Actions(Driver.webdriver);
			WebElement enabledRightArrow = null;
			boolean doneWithThreeCircle = false, disableRightArrowFound = false, enabledRightArrowFound = false,
					paginatorFound = false;
			Assert.assertTrue(homePage.xaxistexton_linecharttestscorevstime.getText().equals("Test Names"));
			List<WebElement> circleList = null;
			try {
				action.moveToElement(homePage.paginator_onlinecharttestscorevstime).build().perform();
				paginatorFound = true;
			} catch (Exception e) {
				System.out.println("Paginator Not Found");
			}
			if (paginatorFound) {
				try {
					circleList = homePage.paginationcirclelist_onlinecharttestscorevstime;
					enabledRightArrow = homePage.enabledrightarrow_onlinecharttestscorevstime;
					enabledRightArrow.isDisplayed();
					enabledRightArrowFound = true;
				} catch (Exception e) {
					System.out.println("Enabled Right Arrow on Paginator is not found");
				}
				if (enabledRightArrowFound) {
					do {
						try {
							homePage.disabledrightarrow_onlinecharttestscorevstime.isDisplayed();
							disableRightArrowFound = true;
						} catch (Exception e) {
							System.out.println("Disabled Right Arrow on Paginator is not found");
						}
						if (doneWithThreeCircle) {
							attr = circleList.get(2).getAttribute("style");
							if (attr.equals("")) {
								circleList.get(2).click();
								Thread.sleep(500);
								testNamesSizeOnPerPage = homePage.testNamesonPerPage_onlinecharttestscorevstime.size();
								Assert.assertTrue(testNamesSizeOnPerPage <= 10);
								for (int j = 0; j < testNamesSizeOnPerPage; j++) {
									testName = UtilityMethods.elipsisRemoval(
											homePage.testNamesonPerPage_onlinecharttestscorevstime.get(j).getText());
									action.moveToElement(homePage.testNamesonPerPage_onlinecharttestscorevstime.get(j))
											.build().perform();
									tooltiptext = homePage.tooltip_onlinecharttestscorevstime.getText();
									Assert.assertTrue(tooltiptext.contains(testName));
									action.moveToElement(homePage.overviewtext).build().perform();
								}
							}
						} else {
							for (int i = 0; i < circleList.size(); i++) {
								attr = circleList.get(i).getAttribute("style");
								if (attr.equals("")) {
									circleList.get(i).click();
									Thread.sleep(500);
									testNamesSizeOnPerPage = homePage.testNamesonPerPage_onlinecharttestscorevstime.size();
									Assert.assertTrue(testNamesSizeOnPerPage <= 10);
									for (int j = 0; j < testNamesSizeOnPerPage; j++) {
										testName = UtilityMethods.elipsisRemoval(
												homePage.testNamesonPerPage_onlinecharttestscorevstime.get(j).getText());
										action.moveToElement(homePage.testNamesonPerPage_onlinecharttestscorevstime.get(j))
												.build().perform();
										tooltiptext = homePage.tooltip_onlinecharttestscorevstime.getText();
										Assert.assertTrue(tooltiptext.contains(testName));
										action.moveToElement(homePage.overviewtext).build().perform();
									}
								}
							}
							doneWithThreeCircle = true;
						}
						try {
							enabledRightArrow.click();
						} catch (Exception e) {
						}
					} while (!disableRightArrowFound);
				} else {
					for (int i = 0; i < circleList.size(); i++) {
						attr = circleList.get(i).getAttribute("style");
						if (attr.equals("")) {
							circleList.get(i).click();
							Thread.sleep(500);
							testNamesSizeOnPerPage = homePage.testNamesonPerPage_onlinecharttestscorevstime.size();
							Assert.assertTrue(testNamesSizeOnPerPage <= 10);
							for (int j = 0; j < testNamesSizeOnPerPage; j++) {
								testName = UtilityMethods.elipsisRemoval(
										homePage.testNamesonPerPage_onlinecharttestscorevstime.get(j).getText());
								action.moveToElement(homePage.testNamesonPerPage_onlinecharttestscorevstime.get(j))
										.build().perform();
								tooltiptext = homePage.tooltip_onlinecharttestscorevstime.getText();
								Assert.assertTrue(tooltiptext.contains(testName));
								action.moveToElement(homePage.overviewtext).build().perform();
							}
						}
					}
				}

			} else {
				testNamesSizeOnPerPage=homePage.testNamesonPerPage_onlinecharttestscorevstime.size();
				Assert.assertTrue(testNamesSizeOnPerPage<=10);
				for (int i = 0; i < testNamesSizeOnPerPage; i++) {
					testName = UtilityMethods.elipsisRemoval(
							homePage.testNamesonPerPage_onlinecharttestscorevstime.get(i).getText());
					action.moveToElement(homePage.testNamesonPerPage_onlinecharttestscorevstime.get(i))
							.build().perform();
					tooltiptext = homePage.tooltip_onlinecharttestscorevstime.getText();
					Assert.assertTrue(tooltiptext.contains(testName));
					action.moveToElement(homePage.overviewtext).build().perform();
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 32 Completed");
	}
	
	/**
	 * This method is used to check on class context menu the test score cricle value with specified range value with the associated colour 
	 * @throws Throwable
	 */
	@Then("^verify The points on the chart should be circles that are color-coordinated according to achievement level$")
	public void verify_The_points_on_the_chart_should_be_circles_that_are_color_coordinated_according_to_achievement_level() throws Throwable {
		try {			
			int testScoreSizeOnPerPage=0;
			Actions action=new Actions(Driver.webdriver);
			WebElement enabledRightArrow = null;
			boolean doneWithThreeCircle = false, disableRightArrowFound = false, enabledRightArrowFound = false,
					paginatorFound = false;
			List<WebElement> circleList = null;
			try {
				action.moveToElement(homePage.paginator_onlinecharttestscorevstime).build().perform();
				paginatorFound = true;
			} catch (Exception e) {
				System.out.println("Paginator Not Found");
			}
			if (paginatorFound) {
				try {
					circleList = homePage.paginationcirclelist_onlinecharttestscorevstime;
					enabledRightArrow = homePage.enabledrightarrow_onlinecharttestscorevstime;
					enabledRightArrow.isDisplayed();
					enabledRightArrowFound = true;
				} catch (Exception e) {
					System.out.println("Enabled Right Arrow on Paginator is not found");
				}
				if (enabledRightArrowFound) {
					do {
						try {
							homePage.disabledrightarrow_onlinecharttestscorevstime.isDisplayed();
							disableRightArrowFound = true;
						} catch (Exception e) {
							System.out.println("Disabled Right Arrow on Paginator is not found");
						}
						if (doneWithThreeCircle) {
							attr = circleList.get(2).getAttribute("style");
							if (attr.equals("")) {
								circleList.get(2).click();
								Thread.sleep(500);
								testScoreSizeOnPerPage = homePage.testNamesonPerPage_onlinecharttestscorevstime.size();
								for (int j = 0; j < testScoreSizeOnPerPage; j++) {
									UtilityMethods.verifyColorAndScore(homePage.testScoreCircleClronPerPage_onlinecharttestscorevstime.get(j), Integer.parseInt(homePage.testScoresonPerPage_onlinecharttestscorevstime.get(j).getText()));
								}
							}
						} else {
							for (int i = 0; i < circleList.size(); i++) {
								attr = circleList.get(i).getAttribute("style");
								if (attr.equals("")) {
									circleList.get(i).click();
									Thread.sleep(500);
									testScoreSizeOnPerPage = homePage.testNamesonPerPage_onlinecharttestscorevstime.size();
									for (int j = 0; j < testScoreSizeOnPerPage; j++) {
										UtilityMethods.verifyColorAndScore(homePage.testScoreCircleClronPerPage_onlinecharttestscorevstime.get(j), Integer.parseInt(homePage.testScoresonPerPage_onlinecharttestscorevstime.get(j).getText()));
									}
								}
							}
							doneWithThreeCircle = true;
						}
						try {
							enabledRightArrow.click();
						} catch (Exception e) {
						}
					} while (!disableRightArrowFound);
				} else {
					for (int i = 0; i < circleList.size(); i++) {
						attr = circleList.get(i).getAttribute("style");
						if (attr.equals("")) {
							circleList.get(i).click();
							Thread.sleep(500);
							testScoreSizeOnPerPage = homePage.testScoresonPerPage_onlinecharttestscorevstime.size();
							for (int j = 0; j < testScoreSizeOnPerPage; j++) {
								UtilityMethods.verifyColorAndScore(homePage.testScoreCircleClronPerPage_onlinecharttestscorevstime.get(j), Integer.parseInt(homePage.testScoresonPerPage_onlinecharttestscorevstime.get(j).getText()));
							}
						}
					}
				}

			} else {
				testScoreSizeOnPerPage=homePage.testNamesonPerPage_onlinecharttestscorevstime.size();
				for (int i = 0; i < testScoreSizeOnPerPage; i++) {
					UtilityMethods.verifyColorAndScore(homePage.testScoreCircleClronPerPage_onlinecharttestscorevstime.get(i), Integer.parseInt(homePage.testScoresonPerPage_onlinecharttestscorevstime.get(i).getText()));
				}
			}
			
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 33 Completed");
	}
	
	/**
	 * This method is used to click class context
	 * @throws Throwable
	 */
	@When("^User click on Student Context$")
	public void user_click_on_Student_Context() throws Throwable {
	   try {
		   homePage.studentmenu.click();
		   Thread.sleep(500);
	   } catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method is used to check on student context menu the test score cricle value with specified range value with the associated colour 
	 * @throws Throwable
	 */
	@Then("^Verify the Cutoffs range with colour for Achievement Level on line chart$")
	public void verify_the_Cutoffs_range_with_colour_for_Achievement_Level_on_line_chart() throws Throwable {
	   try {
		 	for (int j = 0; j < homePage.testScoresonPerPage_onlinecharttestscorevstime.size(); j++) {
				UtilityMethods.verifyColorAndScore(homePage.testScoreCircleClronPerPage_onlinecharttestscorevstime.get(j), Integer.parseInt(homePage.testScoresonPerPage_onlinecharttestscorevstime.get(j).getText()));
			}
	   } catch (Exception e) {
			UtilityMethods.processException(e);
		}
	   log.info("Scenario 34 Completed");
	}
	
	@Then("^Verify the overlay should be closed and other is open if click on other circle$")
	public void verify_the_overlay_should_be_closed_and_other_is_open_if_click_on_other_circle() throws Throwable {
		try {
			Actions action=new Actions(Driver.webdriver);
		 	for (int j = 0; j < homePage.testScoresonPerPage_onlinecharttestscorevstime.size(); j++) {
		 		action.moveToElement(homePage.testScoreCircleClronPerPage_onlinecharttestscorevstime.get(j)).click().perform();
		 		Thread.sleep(500);
		 		Assert.assertTrue(homePage.tooltiprowpercent_onlinecharttestscorevstime.getText().contains(homePage.testScoresonPerPage_onlinecharttestscorevstime.get(j).getText()));
		 		action.moveToElement(homePage.overviewtext).click().perform();
		 	}
	   } catch (Exception e) {
			UtilityMethods.processException(e);
		}
	   log.info("Scenario 35 Completed");
	}
}
	
