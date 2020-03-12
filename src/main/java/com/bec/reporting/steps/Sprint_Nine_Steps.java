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
 * JUN 10 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.steps;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.API_Connection;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.UtilityMethods;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sprint_Nine_Steps {

	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;

	@Then("^To check the grading page in new browser tab should open by clicking on the question number within the test overlay$")
	public void to_check_the_grading_page_in_new_browser_tab_should_open_by_clicking_on_the_question_number_within_the_test_overlay()
			throws Throwable {
		try {
			if (Standard_Overview_Table_Steps.underClassContext
					&& Standard_Overview_Table_Steps.performanceMenuClicked) {
				UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
				new Actions(Driver.webdriver).moveToElement(homePage.performance_overtime_icon).click().build().perform();
				UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			} else {
				log.info("This will execute with student context");
			}

			String percentage = homePage.performanceovertimeheader.getText();
			percentage = percentage.substring(percentage.indexOf(":") + 2, percentage.indexOf("%"));
			String no_of_questions = homePage.performanceovertimeheader.getText();
			no_of_questions = no_of_questions.substring(no_of_questions.indexOf("on") + 2,
					no_of_questions.lastIndexOf(" "));
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);
			Thread.sleep(500);
			int randomTestScoreIndex = (int) (Math.random() * homePage.testScoreValueInCircle_onlinechart_pot.size());
			new Actions(Driver.webdriver)
					.moveToElement(homePage.testScoreValueInCircle_onlinechart_pot.get(randomTestScoreIndex)).click()
					.build().perform();
			Thread.sleep(500);
			Assert.assertTrue(no_of_questions.trim().equals(String.valueOf(homePage.questionlistontooltip.size())));
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		CBTConfiguration.score = "pass";
		log.info("Scenario BE-832,833,834 completed");
		Standard_Overview_Table_Steps.resetStatus();
	}

	/**
	 * This method is used to verify the Grade Taxonomy on UI from DB as well
	 * 
	 * @throws Throwable
	 */
	@Then("^verify the appearance within the standard performance overview$")
	public void verify_the_appearance_within_the_standard_performance_overview() throws Throwable {
		String output = "fail";
		try {
			if (Standard_Overview_Table_Steps.performanceMenuClicked
					&& Standard_Overview_Table_Steps.underDistrictContext) {
				Assert.assertTrue(homePage.gradeDropDown.isDisplayed());
				output = "pass";
			} else {
				try {
					Assert.assertTrue(homePage.gradeDropDown.isDisplayed());
					UtilityMethods.processException(new Exception());
				} catch (Exception e) {
					output = "pass";
				}
			}

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		CBTConfiguration.score = output;
		log.info("Scenario BE-835,836 completed");
		Standard_Overview_Table_Steps.resetStatus();
	}

	@Then("^verify Standards Question Filter dropdown label and position within Standard Performance Overview$")
	public void verify_Standards_Question_Filter_dropdown_label_and_position_within_Standard_Performance_Overview()
			throws Throwable {
		String output = "fail";
		try {

			if (Standard_Overview_Table_Steps.performanceMenuClicked) {
				Assert.assertTrue(homePage.questionDropDown.isDisplayed());
				output = "pass";
			} else if (Standard_Overview_Table_Steps.testScoreMenuClicked) {
				try {
					Assert.assertTrue(homePage.questionDropDown.isDisplayed());
					UtilityMethods.processException(new Exception());
				} catch (Exception e) {
					output = "pass";
				}
			}
			CBTConfiguration.score = output;
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		Standard_Overview_Table_Steps.resetStatus();
		log.info("Scenario BE-837 completed");
	}

	public void print_PDF(Robot robot) {
		try {
			Thread.sleep(10000);
			Actions builder = new Actions(Driver.webdriver);
			for (int i = 0; i < 5; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}
			Thread.sleep(500);
			builder.sendKeys(Keys.ARROW_UP).build().perform();

			Thread.sleep(5000);
			for (int i = 0; i < 6; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {
			log.error("Error in printing pdf file..");
			UtilityMethods.processException(e);
		}
	}

	@Then("^verify Context header content for the Standards Overview context selected shown in all PDF's$")
	public void verify_Context_header_content_for_the_Standards_Overview_context_selected_shown_in_all_PDF_s()
			throws Throwable {
		String output = "";
		try {
			// Robot robot = new Robot();

			String school, classN, district, tests, dates, assessedWith, teacherN, studentName;
			// String testDataAssessedForGrade, grade;

			if (Standard_Overview_Table_Steps.performanceMenuClicked
					&& Standard_Overview_Table_Steps.underClassContext) {

				classN = UtilityMethods.getClassNameonUI();
				school = UtilityMethods.getSchoolNameonUI();
				district = UtilityMethods.getDistrictNameonUI();
				tests = UtilityMethods.getTestsNameonUI();
				dates = UtilityMethods.getDatesonContextHeaderUI();
				assessedWith = UtilityMethods.getAssessedWithonUI();
				// testDataAssessedForGrade = UtilityMethods.getTestDataAssessedForGradeonUI();
				Assert.assertTrue(homePage.printIcon.isDisplayed());

				homePage.printIcon.click();
				// TODO logic for print pdf then open and verify the headers value in pdf file
				// with ui
				// print_PDF(robot);

				Assert.assertTrue(homePage.headerRowList.get(0).getText().contains(classN));
				Assert.assertTrue(homePage.headerRowList.get(1).getText().contains(school));
				Assert.assertTrue(homePage.headerRowList.get(2).getText().contains(district));
				Assert.assertTrue(homePage.headerRowList.get(3).getText().contains(tests));
				Assert.assertTrue(homePage.headerRowList.get(4).getText().contains(dates));
				Assert.assertTrue(homePage.headerRowList.get(5).getText().contains(assessedWith));
				// Assert.assertTrue(homePage.headerRowList.get(6).getText().contains(testDataAssessedForGrade));

				Thread.sleep(500);

				homePage.activePerformanceOverTimePage.click();
				UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
				// grade = UtilityMethods.getTestDataAssessedForGradeonUI();
				teacherN = UtilityMethods.getTeacherNameonUI();

				Assert.assertTrue(homePage.printIcon.isDisplayed());
				Assert.assertTrue(homePage.headerRowList.get(0).getText().contains(classN));
				Assert.assertTrue(homePage.headerRowList.get(1).getText().contains(teacherN));
				// Assert.assertTrue(homePage.headerRowList.get(2).getText().contains(grade));
				Assert.assertTrue(homePage.headerRowList.get(3).getText().contains(school));
				Assert.assertTrue(homePage.headerRowList.get(4).getText().contains(district));
				Assert.assertTrue(homePage.headerRowList.get(5).getText().contains(tests));
				Assert.assertTrue(homePage.headerRowList.get(6).getText().contains(dates));
				Assert.assertTrue(homePage.headerRowList.get(7).getText().contains(assessedWith));

				output = "pass";
			} else if (Standard_Overview_Table_Steps.performanceMenuClicked
					&& Standard_Overview_Table_Steps.underStudentContext) {
				studentName = UtilityMethods.getStudentNameonUI();
				teacherN = UtilityMethods.getTeacherNameonUI();
				classN = UtilityMethods.getClassNameonUI();
				school = UtilityMethods.getSchoolNameonUI();

				Assert.assertTrue(homePage.printIcon.isDisplayed());

				Assert.assertTrue(homePage.headerRowList.get(0).getText().contains(studentName));
				Assert.assertTrue(homePage.headerRowList.get(1).getText().contains(classN));
				Assert.assertTrue(homePage.headerRowList.get(2).getText().contains(teacherN));
				Assert.assertTrue(homePage.headerRowList.get(3).getText().contains(school));
				output = "pass";
			} else if (Standard_Overview_Table_Steps.testScoreMenuClicked
					&& Standard_Overview_Table_Steps.underStudentContext) {

				studentName = UtilityMethods.getStudentNameonUI();
				teacherN = UtilityMethods.getTeacherNameonUI();
				classN = UtilityMethods.getClassNameonUI();
				school = UtilityMethods.getSchoolNameonUI();
				district = UtilityMethods.getDistrictNameonUI();
				tests = UtilityMethods.getTestsNameonUI();
				dates = UtilityMethods.getDatesonContextHeaderUI();

				Assert.assertTrue(homePage.printIcon.isDisplayed());
				Assert.assertTrue(homePage.headerRowList.get(0).getText().contains(studentName));
				Assert.assertTrue(homePage.headerRowList.get(1).getText().contains(classN));
				Assert.assertTrue(homePage.headerRowList.get(2).getText().contains(teacherN));
				Assert.assertTrue(homePage.headerRowList.get(4).getText().contains(school));
				Assert.assertTrue(homePage.headerRowList.get(5).getText().contains(district));
				Assert.assertTrue(homePage.headerRowList.get(6).getText().contains(tests));
				Assert.assertTrue(homePage.headerRowList.get(7).getText().contains(dates));

				output = "pass";
			}

			else if (Standard_Overview_Table_Steps.testScoreMenuClicked
					&& Standard_Overview_Table_Steps.underClassContext) {
				studentName = UtilityMethods.getStudentNameonUI();
				teacherN = UtilityMethods.getTeacherNameonUI();
				classN = UtilityMethods.getClassNameonUI();
				school = UtilityMethods.getSchoolNameonUI();
				district = UtilityMethods.getDistrictNameonUI();
				tests = UtilityMethods.getTestsNameonUI();
				dates = UtilityMethods.getDatesonContextHeaderUI();

				Assert.assertTrue(homePage.printIcon.isDisplayed());
				Assert.assertTrue(homePage.headerRowList.get(0).getText().contains(classN));
				Assert.assertTrue(homePage.headerRowList.get(1).getText().contains(teacherN));
				Assert.assertTrue(homePage.headerRowList.get(3).getText().contains(school));
				Assert.assertTrue(homePage.headerRowList.get(4).getText().contains(district));
				Assert.assertTrue(homePage.headerRowList.get(5).getText().contains(tests));
				Assert.assertTrue(homePage.headerRowList.get(6).getText().contains(dates));

				Assert.assertTrue(homePage.Test_score_detail_printIcon.isDisplayed());
				Assert.assertTrue(homePage.test_score_detail_headerRowList.get(0).getText().contains(classN));
				Assert.assertTrue(homePage.test_score_detail_headerRowList.get(1).getText().contains(teacherN));
				Assert.assertTrue(homePage.test_score_detail_headerRowList.get(3).getText().contains(school));
				Assert.assertTrue(homePage.test_score_detail_headerRowList.get(4).getText().contains(district));
				Assert.assertTrue(homePage.test_score_detail_headerRowList.get(5).getText().contains(tests));
				Assert.assertTrue(homePage.test_score_detail_headerRowList.get(6).getText().contains(dates));

				output = "pass";
			}

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		CBTConfiguration.score = output;
		log.info("Scenario BE-845 completed");
		Standard_Overview_Table_Steps.resetStatus();
	}

	/**
	 * This method is used to verify the View Taxonomy on UI
	 * 
	 * @throws Throwable
	 */
	@Then("^verify View Texonomy on Standard Performance$")
	public void verify_View_Texonomy_on_Standard_Performance() throws Throwable {
		String output = "fail", viewDDText = "";
		try {
			if (Standard_Overview_Table_Steps.performanceMenuClicked) {
				Assert.assertTrue(homePage.viewDropDown.isDisplayed());
				if (homePage.viewDropDown.getText().contains("...")) {
					new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).build().perform();
					Thread.sleep(1000);
					viewDDText = homePage.viewDropDownToolTipText.getText();
				} else {
					viewDDText = homePage.viewDropDown.getText();
				}
				Assert.assertTrue(API_Connection.getViewDetails().containsValue(viewDDText));
				output = "pass";
			} else if (Standard_Overview_Table_Steps.testScoreMenuClicked) {
				try {
					Assert.assertTrue(homePage.viewDropDown.isDisplayed());
					UtilityMethods.processException(new Exception());
				} catch (Exception e) {
					output = "pass";
				}
			}

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		CBTConfiguration.score = output;
		log.info("Scenario View Texonomy display and validation completed");
		Standard_Overview_Table_Steps.resetStatus();
	}

}
