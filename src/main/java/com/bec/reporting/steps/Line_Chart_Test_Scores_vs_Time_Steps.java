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
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.PaginationUtility;
import com.bec.reporting.utils.UtilityMethods;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Line_Chart_Test_Scores_vs_Time_Steps {
	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);

	String tooltiptext, attr;

	/**
	 * This method used to verify the y-axis label as Test Scores , verify
	 * horizontal line sum as 11 and the lines lables
	 * 
	 * @throws Throwable
	 */
	@Then("^verify y-axis labeled with as Test Scores\\(%\\) and also verify (\\d+) horizontal lines, one for x axis line and rest label with (\\d+) throught (\\d+)$")
	public void verify_y_axis_labeled_with_as_Test_Scores_and_also_verify_horizontal_lines_one_for_x_axis_line_and_rest_label_with_throught(
			int arg1, int arg2, int arg3) throws Throwable {
		try {
			int range = 0;
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);
			Assert.assertTrue(homePage.yaxistexton_linechart.getText().equals("Test Scores (%)"));
			Assert.assertTrue(homePage.horizontalline_onlinechart.size() == 11);
			List<WebElement> list = homePage.yaxislabelsonhorizontalline_onlinechart;
			for (int i = 0; i < list.size(); i++) {
				Assert.assertTrue(Integer.parseInt(list.get(i).getText()) == range);
				range += 10;
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 31 and/or Scenario39_1_standard performance line chart Completed");
	}

	/**
	 * This method used to verify x-axis label on line chart test score over time
	 * and the names of test list on graph with elipsis and tooltip text.
	 * 
	 * @throws Throwable
	 */
	@Then("^verify x-axis labeled with 'Test Names' and show tooltip if elipsis in test names and if more than '(\\d+)' tests are there then paginator should be display$")
	public void verify_x_axis_labeled_with_Test_Names_and_show_tooltip_if_elipsis_in_test_names_and_if_more_than_tests_are_there_then_paginator_should_be_display(
			int arg1) throws Throwable {
		try {
			UtilityMethods.waitforpageload();
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);

			Assert.assertTrue(homePage.xaxistexton_linechart.getText().equals("Test Names"));
			PaginationUtility.methodOne();
			if (PaginationUtility.paginatorFound) {
				PaginationUtility.methodTwo();
				if (PaginationUtility.enabledLeftArrowFound) {
					do {
						PaginationUtility.methodThree();
						if (PaginationUtility.doneWithThreeCircle) {
							PaginationUtility.methodFour();
							Assert.assertTrue(homePage.testNamesonPerPage_onlinechart.size() <= 10);
							for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
								PaginationUtility.verifyTestNamesAndToolTipText(j);
							}
							UtilityMethods.scrollPageUp(Driver.webdriver, 2);
						} else {
							for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {

								PaginationUtility.methodFive(i);
								Assert.assertTrue(homePage.testNamesonPerPage_onlinechart.size() <= 10);
								for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
									PaginationUtility.verifyTestNamesAndToolTipText(j);
								}
								UtilityMethods.scrollPageUp(Driver.webdriver, 2);
							}
							PaginationUtility.doneWithThreeCircle = true;
						}
						PaginationUtility.methodSix();
					} while (!PaginationUtility.disableLeftArrowFound);
				} else {
					for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {

						PaginationUtility.methodFive(i);
						Assert.assertTrue(homePage.testNamesonPerPage_onlinechart.size() <= 10);
						for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
							PaginationUtility.verifyTestNamesAndToolTipText(j);
						}
						UtilityMethods.scrollPageUp(Driver.webdriver, 2);
					}
				}

			} else {
				Assert.assertTrue(homePage.testNamesonPerPage_onlinechart.size() <= 10);
				for (int i = homePage.testNamesonPerPage_onlinechart.size() - 1; i >= 0; i--) {
					PaginationUtility.verifyTestNamesAndToolTipText(i);
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 32 and/or Scenario39_2 standard performance line chart Completed");
	}

	/**
	 * This method is used to check on class context menu the test score cricle
	 * value with specified range value with the associated colour
	 * 
	 * @throws Throwable
	 */
	@Then("^verify The points on the chart should be circles that are color-coordinated according to achievement level$")
	public void verify_The_points_on_the_chart_should_be_circles_that_are_color_coordinated_according_to_achievement_level()
			throws Throwable {
		try {
			try {
				homePage.performanceovrtimeicon.click();
				Thread.sleep(1000);
				log.info("user clicked on SP under class context");
			} catch (Exception e) {
				log.info("user clicked on SP under student context");
			}
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);

			PaginationUtility.methodOne();
			if (PaginationUtility.paginatorFound) {
				PaginationUtility.methodTwo();
				if (PaginationUtility.enabledLeftArrowFound) {
					do {
						PaginationUtility.methodThree();
						if (PaginationUtility.doneWithThreeCircle) {
							PaginationUtility.methodFour();
							for (int j = 0; j < homePage.testNamesonPerPage_onlinechart.size(); j++) {
								UtilityMethods.verifyColorAndScoreOnLineChart(
										homePage.testScoreCircleClronPerPage_onlinechart.get(j),
										Integer.parseInt(homePage.testScoresonPerPage_onlinechart.get(j).getText()));
							}
						} else {
							for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {

								PaginationUtility.methodFive(i);
								for (int j = 0; j < homePage.testNamesonPerPage_onlinechart.size(); j++) {
									UtilityMethods.verifyColorAndScoreOnLineChart(
											homePage.testScoreCircleClronPerPage_onlinechart.get(j), Integer.parseInt(
													homePage.testScoresonPerPage_onlinechart.get(j).getText()));
								}
							}
							PaginationUtility.doneWithThreeCircle = true;
						}
						PaginationUtility.methodSix();
					} while (!PaginationUtility.disableLeftArrowFound);
				} else {
					for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {

						PaginationUtility.methodFive(i);
						for (int j = 0; j < homePage.testNamesonPerPage_onlinechart.size(); j++) {
							UtilityMethods.verifyColorAndScoreOnLineChart(
									homePage.testScoreCircleClronPerPage_onlinechart.get(j),
									Integer.parseInt(homePage.testScoresonPerPage_onlinechart.get(j).getText()));
						}
					}
				}
			} else {
				for (int i = 0; i < homePage.testNamesonPerPage_onlinechart.size(); i++) {
					UtilityMethods.verifyColorAndScoreOnLineChart(
							homePage.testScoreCircleClronPerPage_onlinechart.get(i),
							Integer.parseInt(homePage.testScoresonPerPage_onlinechart.get(i).getText()));
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 33 & 34 Completed");
	}

	/**
	 * This method is used to verify the tooltip's avg score and circle's avg score and also verify while clicking on outside the tooltip ,it should disappear
	 * @throws Throwable
	 */
	@Then("^Verify the overlay should be closed and other is open if click on other circle$")
	public void verify_the_overlay_should_be_closed_and_other_is_open_if_click_on_other_circle() throws Throwable {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);
			UtilityMethods.waitforcontextheadersaction();
			PaginationUtility.methodOne();
			if (PaginationUtility.paginatorFound) {
				PaginationUtility.methodTwo();
				if (PaginationUtility.enabledLeftArrowFound) {
					do {
						PaginationUtility.methodThree();
						if (PaginationUtility.doneWithThreeCircle) {
							PaginationUtility.methodFour();
							for (int j = 0; j < homePage.testScoresonPerPage_onlinechart.size(); j++) {
								PaginationUtility.verifyTestScorePerAndPerOnToolTip(j);
							}

						} else {
							for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
								PaginationUtility.methodFive(i);
								for (int j = 0; j < homePage.testScoresonPerPage_onlinechart.size(); j++) {
									PaginationUtility.verifyTestScorePerAndPerOnToolTip(j);
								}
							}
							PaginationUtility.doneWithThreeCircle = true;
						}
						PaginationUtility.methodSix();
					} while (!PaginationUtility.disableLeftArrowFound);
				} else {
					for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
						PaginationUtility.methodFive(i);
						for (int j = 0; j < homePage.testScoresonPerPage_onlinechart.size(); j++) {
							PaginationUtility.verifyTestScorePerAndPerOnToolTip(j);
						}
					}
				}

			} else {
				for (int j = 0; j < homePage.testScoresonPerPage_onlinechart.size(); j++) {
					PaginationUtility.verifyTestScorePerAndPerOnToolTip(j);
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 35 Completed");
	}
}
