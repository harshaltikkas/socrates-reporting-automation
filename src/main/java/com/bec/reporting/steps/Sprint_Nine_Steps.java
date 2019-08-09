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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.DatabaseConnection;
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
			Thread.sleep(3000);
			if (Standard_Overview_Table_Steps.underClassContext
					&& Standard_Overview_Table_Steps.performanceMenuClicked) {
				UtilityMethods.wait_For_Performance_Over_Time_Section_Load();
				new Actions(Driver.webdriver).moveToElement(homePage.performanceovrtimeicon).click().build().perform();
				Thread.sleep(2000);
			} else {
				log.info("This will execute with student context");
			}
			UtilityMethods.scrollPageDown(Driver.webdriver, 9);
			Thread.sleep(1000);
			int randomTestScoreIndex = (int) (Math.random() * homePage.testScoreValueInCircle_onlinechart.size());
			homePage.testScoreValueInCircle_onlinechart.get(randomTestScoreIndex).click();
			Thread.sleep(1000);
			int randomQuestionsIndex = (int) (Math.random() * homePage.questionlistontooltip.size());
			String question_No = homePage.questionlistontooltip.get(randomQuestionsIndex).getText();
			new Actions(Driver.webdriver).moveToElement(homePage.questionlistontooltip.get(randomQuestionsIndex))
					.click().build().perform();
			Thread.sleep(10000);
			Set<String> handles = Driver.webdriver.getWindowHandles();
			String currentHandle = Driver.webdriver.getWindowHandle();
			for (String handle : handles) {
				if (!handle.equals(currentHandle)) {
					Driver.webdriver.switchTo().window(handle);
				}
			}
			homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
			String newTabUrl = Driver.webdriver.getCurrentUrl();
			log.info("newTabUrl" + newTabUrl);
			Assert.assertTrue(newTabUrl.substring(newTabUrl.lastIndexOf("/") + 1).equals(question_No));
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
		Integer schoolId = 0, classId = 0;

		try {
			Map<Integer, Integer> ids = UtilityMethods.getSchoolIdAndClassId();
			for (Map.Entry<Integer, Integer> entry : ids.entrySet()) {
				schoolId = entry.getKey();
				classId = entry.getValue();
			}

			List<String> DBGradeList = DatabaseConnection.getGradeTexonomy(DatabaseConnection.conn, schoolId, classId);
			List<String> UIGradeList = new ArrayList<String>();

			if (Standard_Overview_Table_Steps.performanceMenuClicked
					&& Standard_Overview_Table_Steps.underClassContext) {
				Assert.assertTrue(homePage.gradeDropDown.isDisplayed());
				homePage.gradeDropDown.click();
				Thread.sleep(500);

				try {
					for (int i = 0; i < homePage.gradeList.size(); i++) {
						UIGradeList.add(homePage.gradeList.get(i).getText());
					}
				} catch (Exception e) {
					UIGradeList.add(homePage.gradeDropDown.getText());
				}

				for (int i = 0; i < homePage.gradeList.size(); i++) {
					Assert.assertTrue(DBGradeList.get(i).equals(UIGradeList.get(i)));
				}
				output = "pass";
			} else if (Standard_Overview_Table_Steps.testScoreMenuClicked) {
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
		Integer schoolId = 0, classId = 0;
		try {
			Map<Integer, Integer> ids = UtilityMethods.getSchoolIdAndClassId();
			for (Map.Entry<Integer, Integer> entry : ids.entrySet()) {
				schoolId = entry.getKey();
				classId = entry.getValue();
			}

			if (Standard_Overview_Table_Steps.performanceMenuClicked) {
				Assert.assertTrue(homePage.questionDropDown.isDisplayed());
				// DB validation here

				output = "pass";
			} else if (Standard_Overview_Table_Steps.testScoreMenuClicked) {
				try {
					Assert.assertTrue(homePage.questionDropDown.isDisplayed());
					UtilityMethods.processException(new Exception());
				} catch (Exception e) {
					output = "pass";
				}
			}

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		CBTConfiguration.score = output;
		log.info("Scenario BE-837 completed");
		Standard_Overview_Table_Steps.resetStatus();
	}

	@Then("^verify Context header content for the Standards Overview context selected shown in all PDF's$")
	public void verify_Context_header_content_for_the_Standards_Overview_context_selected_shown_in_all_PDF_s()
			throws Throwable {
		String output = "";
		String classN,grade,school,district,tests,dates,assessedWith,testDataAssessedForGrade;
		String studentN,teacherN;

		Integer schoolId=0,classId=0,studentId;

		try {
			Map<Integer,Integer> ids=UtilityMethods.getSchoolIdAndClassId();
			for(Map.Entry<Integer,Integer> entry:ids.entrySet()) {
				schoolId=entry.getKey();
				classId=entry.getValue();
			}
			
			classN=UtilityMethods.getClassNameonUI();
			school=UtilityMethods.getSchoolNameonUI();
			district=UtilityMethods.getDistrictNameonUI();
			tests=UtilityMethods.getTestsNameonUI();
			dates=UtilityMethods.getDatesonContextHeaderUI();
			assessedWith=UtilityMethods.getAssessedWithonUI();
			testDataAssessedForGrade=UtilityMethods.getTestDataAssessedForGradeonUI();
			if (Standard_Overview_Table_Steps.performanceMenuClicked
					&& Standard_Overview_Table_Steps.underClassContext) {
				Assert.assertTrue(homePage.printIcon.isDisplayed());
				//grade=UtilityMethods.getTestDataAssessedForGradeonUI();
				Assert.assertTrue(homePage.headerRowList.get(0).getText().contains(classN));
				Assert.assertTrue(homePage.headerRowList.get(1).getText().contains(school));
				Assert.assertTrue(homePage.headerRowList.get(2).getText().contains(district));
				Assert.assertTrue(homePage.headerRowList.get(3).getText().contains(tests));
				Assert.assertTrue(homePage.headerRowList.get(4).getText().contains(dates));
				Assert.assertTrue(homePage.headerRowList.get(5).getText().contains(assessedWith));
				Assert.assertTrue(homePage.headerRowList.get(6).getText().contains(testDataAssessedForGrade));	
				
				
				output = "pass";
			}
			else if (Standard_Overview_Table_Steps.performanceMenuClicked
					&& Standard_Overview_Table_Steps.underStudentContext) {
				Assert.assertTrue(homePage.printIcon.isDisplayed());
				studentId=UtilityMethods.getStudentId();
				grade=DatabaseConnection.getGradeTexonomyForStudent(DatabaseConnection.conn, schoolId, classId,studentId);
				
				output = "pass";
			}
			else if (Standard_Overview_Table_Steps.testScoreMenuClicked) {
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
		log.info("Scenario BE-845 completed");
		Standard_Overview_Table_Steps.resetStatus();
	}

	/**
	 * This method is used to verify the View Taxonomy on UI from DB as well
	 * 
	 * @throws Throwable
	 */
	@Then("^verify View Texonomy on Standard Performance$")
	public void verify_View_Texonomy_on_Standard_Performance() throws Throwable {
		String output = "", viewDDText = "";
		Integer schoolId = 0, classId = 0;
		try {
			Map<Integer, Integer> ids = UtilityMethods.getSchoolIdAndClassId();
			for (Map.Entry<Integer, Integer> entry : ids.entrySet()) {
				schoolId = entry.getKey();
				classId = entry.getValue();
			}

			if (Standard_Overview_Table_Steps.performanceMenuClicked) {
				Assert.assertTrue(homePage.viewDropDown.isDisplayed());
				if (homePage.viewDropDown.getText().contains("...")) {
					new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).build().perform();
					Thread.sleep(1000);
					viewDDText = homePage.viewDropDownToolTipText.getText();
				} else {
					viewDDText = homePage.viewDropDown.getText();
				}
				Assert.assertTrue(viewDDText
						.equals(DatabaseConnection.getViewTexonomy(DatabaseConnection.conn, schoolId, classId)));
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
