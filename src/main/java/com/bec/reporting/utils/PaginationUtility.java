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
 * June 01 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PaginationUtility {
	
	public static WebElement enabledLeftArrow = null,disableLeftArrow=null;
	public static boolean doneWithThreeCircle = false;
	public static boolean disableLeftArrowFound = false;
	public static boolean enabledLeftArrowFound = false;
	public static boolean paginatorFound = false;
	public static List<WebElement> circleList = null;
	public static boolean toolTipDisplayAfterClosing = false;
	public static boolean disableRightArrowFound = false;
	public static boolean enabledRightArrowFound = false;
	public static WebElement enabledRightArrow = null;
	public static WebElement rightArrowEnable = null;
	public static List<WebElement> elList;
	public static List<String> testNamesList = new ArrayList<>();
	public static int avgPerOnSubHeading = 0, totalQuestionCount = 0, tsum = 0, TestCount = 0;
	public static List<Model> lm = new ArrayList<>();

	
	static HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);

	public static boolean checkPaginator_on_tsot() {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.paginator_onlinechart_tsot).build().perform();
			circleList = homePage.paginationcirclelist_onlinechart;
			paginatorFound = true;
		} catch (Exception e) {
			log.info("Paginator Not Found");
		}
		return paginatorFound;
	}
	
	public static boolean checkPaginator_on_pot() {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.paginator_onlinechart_pot).build().perform();
			circleList = homePage.paginationcirclelist_onlinechart;
			paginatorFound = true;
		} catch (Exception e) {
			log.info("Paginator Not Found");
		}
		return paginatorFound;
	}
	
	public static boolean checkPaginator() {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.paginator_onlinechart).build().perform();
			circleList = homePage.paginationcirclelist_onlinechart;
			paginatorFound = true;
		} catch (Exception e) {
			log.info("Paginator Not Found");
		}
		return paginatorFound;
	}

	public static boolean check_Enabled_Left_Arrow_on_Paginator_on_tsot() {
		try {
		//	circleList = homePage.paginationcirclelist_onlinechart;
			enabledLeftArrow = homePage.enabledleftarrow_onlinechart;
			enabledLeftArrow.isDisplayed();
			enabledLeftArrowFound = true;
		} catch (Exception e) {
			log.info("Enabled Left Arrow on Paginator is not found");
			enabledLeftArrowFound = false;
		}
		return enabledLeftArrowFound;
	}

	public static boolean check_Disabled_Left_Arrow_on_Paginator() {
		try {
			disableLeftArrow = homePage.disabledleftarrow_onlinechart_on_tsot;
			disableLeftArrow.isDisplayed();
			disableLeftArrowFound = true;
			Thread.sleep(500);
			System.out.println("1");
		} catch (Exception e) {
			log.info("Disabled Left Arrow on Paginator is not found");
		}
		return disableLeftArrowFound;
	}

	public static void clicking_on_first_circle_of_paginator() {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			Thread.sleep(500);
			circleList.get(0).click();
			Thread.sleep(1000);
			UtilityMethods.scrollPageUp(Driver.webdriver, 2);Thread.sleep(500);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
	}

	public static void clicking_on_indexed_circle_of_paginator(int index) {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			Thread.sleep(500);
			circleList.get(index).click();
			Thread.sleep(1000);
			UtilityMethods.scrollPageUp(Driver.webdriver, 2);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
	}

	public static void clicking_on_enabled_left_Arrow_of_paginator() {
		try {
			enabledLeftArrow.click();
		} catch (Exception e) {
			log.error("Enabled Left arrow is not found");			
		}
	}

	public static void verifyTestNamesAndToolTipText(int index) {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.testScoresPercentage).build().perform();
			String testName = UtilityMethods
					.elipsisRemoval(homePage.testNamesonPerPage_onlinechart.get(index).getText());
			new Actions(Driver.webdriver).moveToElement(homePage.testNamesonPerPage_onlinechart.get(index)).build()
					.perform();
			String tooltiptext = homePage.testNametooltip_onlinechart.getText();
			Assert.assertTrue(tooltiptext.contains(testName));
			Thread.sleep(500);
			new Actions(Driver.webdriver).moveToElement(homePage.testScoresPercentage).build().perform();
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
	}

	public static void verifyTestScorePerAndPerOnToolTip(int index) {

		try {
			new Actions(Driver.webdriver).moveToElement(homePage.testScoreCircleClronPerPage_onlinechart.get(index))
					.click().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.tooltiprowpercent_onlinechart.getText()
					.contains(homePage.testScoresonPerPage_onlinechart.get(index).getText()));
			Thread.sleep(500);

			new Actions(Driver.webdriver).moveToElement(homePage.testNamesonPerPage_onlinechart.get(index)).click()
					.build().perform();
		} catch (Exception e) {
			try {
				UtilityMethods.scrollPageDown(Driver.webdriver, 2);
				Thread.sleep(500);
				new Actions(Driver.webdriver).moveToElement(homePage.testNamesLabelOnLineChart).click().build()
						.perform();
				UtilityMethods.scrollPageUp(Driver.webdriver, 2);
				Thread.sleep(500);
			} catch (Exception e1) {
				try {
					Thread.sleep(500);
					new Actions(Driver.webdriver).moveToElement(homePage.testScoresPercentage).click().build()
							.perform();
				} catch (InterruptedException e2) {
				}
			}
		}
	}

	public static void verifyToolTipDatawithTestScoreCircle(int index, Integer schoolId, Integer classId) {
		String toolTipTextofTest;
		List<Model> lm = new ArrayList<Model>();
		Integer testScoreAvg = 0;
		float classAvgScrInClassInTS = 0.0f;
		try {
			homePage.testScoreValueInCircle_onlinechart.get(index).click();
			Thread.sleep(3000);
			new Actions(Driver.webdriver).moveToElement(homePage.testNamesonPerPage_onlinechart.get(index)).build()
					.perform();
			toolTipTextofTest = homePage.testNametooltip_onlinechart.getText();

			testScoreAvg = DatabaseConnection.getTestScoreAvgInTSInClassContext(DatabaseConnection.conn,
					schoolId, classId, toolTipTextofTest);
			Assert.assertTrue(
					Integer.parseInt(homePage.testScoreValueInCircle_onlinechart.get(index).getText()) == testScoreAvg);

			new Actions(Driver.webdriver).moveToElement(homePage.testNameOnTestScoreDetail).build().perform();

			Assert.assertTrue(homePage.tooltipOftestNameOnTestScoreDetail.getText().equals(toolTipTextofTest));
			new Actions(Driver.webdriver).moveToElement(homePage.testscoredetail).build().perform();
			String submittedDateText = homePage.selectedTestSubmittedDate.getText();
			UtilityMethods.checkDateFormat(submittedDateText.substring(11, 21));
			try {
				UtilityMethods.checkDateFormat(submittedDateText.substring(24));
			} catch (Exception e) {
			}

			// DB code for check the student list, Verifying UI Content in Student List with
			// DB data
			lm = DatabaseConnection.getStudentDetailListInTSInClass(DatabaseConnection.conn, schoolId,
					classId, toolTipTextofTest);
			Iterator<Model> iterator = lm.iterator();
			int occurance = 0;
			while (iterator.hasNext()) {
				Model m = (Model) iterator.next();
				Assert.assertTrue(
						m.getStudent_name().equals(homePage.studentnameslistinstudentlist.get(occurance).getText()));
				Assert.assertTrue(new SimpleDateFormat("MM/dd/yyyy").format(m.getMaxDate())
						.equals(homePage.noofquestionsorsubmitdatelistinstudentlist.get(occurance).getText()));
				Assert.assertTrue(m.getStudent_score_avg() == Integer
						.parseInt(homePage.scorelistinstudentlist.get(occurance).getText()));
				classAvgScrInClassInTS += m.getStudent_score_avg();
				UtilityMethods.verifyColorAndScoreOnStudentList(homePage.scorelistinstudentlist.get(occurance),
						m.getStudent_score_avg());
				occurance++;
			}
			Assert.assertTrue(homePage.classAvgScrInClassInTS.getText()
					.equals("Class Average Scores: " + Math.round((float) (classAvgScrInClassInTS / lm.size()))
							+ "% based on " + lm.size() + " results"));
			classAvgScrInClassInTS = 0.0f;
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
	}

	public static void verifyStudentListNotDisplayWithStudentContext(int index, Integer schoolId, Integer classId,
			Integer studentId) {
		String testName, submittedDateText;
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.testNamesonPerPage_onlinechart.get(index)).build()
					.perform();
			testName = homePage.testNametooltip_onlinechart.getText();
			Integer test_score = DatabaseConnection.getStudentScoreAvgInTSInStudentContext(
					DatabaseConnection.conn, schoolId, classId, studentId, testName);
			Assert.assertTrue(
					Integer.parseInt(homePage.testScoreValueInCircle_onlinechart.get(index).getText()) == test_score);
			homePage.testScoreValueInCircle_onlinechart.get(index).click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.testnameontooltip.getText().equals(testName));
			submittedDateText = homePage.submitteddateontooltip.getText();
			UtilityMethods.checkDateFormat(submittedDateText.substring(12, 22));
			try {
				UtilityMethods.checkDateFormat(submittedDateText.substring(24));
			} catch (Exception e) {
			}
			Assert.assertTrue(homePage.studentscoreleftcontentontt.getText().equals("Student Score"));
			Assert.assertTrue(homePage.studentscorerightcontentontt.getText().equals(test_score + "%"));
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			Thread.sleep(1000);
			new Actions(Driver.webdriver).moveToElement(homePage.testNamesLabelOnLineChart).click().build().perform();
			UtilityMethods.scrollPageUp(Driver.webdriver, 2);
			Thread.sleep(1000);
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
	}

	public static void verifyToolTipDetailsonLineChart(int index) {
		try {
			String tooltiptext, submittedDateText;
		
			new Actions(Driver.webdriver).moveToElement(homePage.testNamesonPerPage_onlinechart.get(index)).build()
					.perform();
			tooltiptext = homePage.testNametooltip_onlinechart.getText();
			homePage.testScoreValueInCircle_onlinechart.get(index).click();
			Thread.sleep(1500);
			Assert.assertTrue(homePage.testnameontooltip.getText().equals(tooltiptext));
			submittedDateText = homePage.submitteddateontooltip.getText();
			UtilityMethods.checkDateFormat(submittedDateText.substring(12, 22));
			try {
				UtilityMethods.checkDateFormat(submittedDateText.substring(24));
			} catch (Exception e) {
			}
			Assert.assertTrue(homePage.questionlistarea.isDisplayed());
			try {
				new Actions(Driver.webdriver).moveToElement(homePage.testScoresPercentage).click().build().perform();
			} catch (Exception e) {
				try {
					new Actions(Driver.webdriver).moveToElement(homePage.testNamesonPerPage_onlinechart.get(index))
							.click().build().perform();
				} catch (Exception e1) {
					new Actions(Driver.webdriver).moveToElement(homePage.hundredtextontsot).click().build().perform();
				}
			}
			Thread.sleep(1000);
			try {
				Assert.assertTrue(homePage.testnameontooltip.getText().equals(tooltiptext));
				toolTipDisplayAfterClosing = true;
			} catch (Exception e) {
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
	}
	
	public static void studentListMethodOne() {
		try {
			homePage.paginator_onstudentlist.isDisplayed();
			paginatorFound = true;
		} catch (Exception e) {
			log.info("Paginator Not Found");
		}
	}

	public static void studentListMethodTwo() {
		try {
			circleList = homePage.studentlistpaginationcirclelist;
			enabledRightArrow = homePage.studentlistenabledrightarrow;
			enabledRightArrow.isDisplayed();
			enabledRightArrowFound = true;
		} catch (Exception e) {
			log.info("Enabled Right Arrow on Paginator is not found");
		}
	}

	public static void studentListMethodThree() {
		try {
			homePage.studentlistdisabledrightarrow.isDisplayed();
			disableRightArrowFound = true;
		} catch (Exception e) {
			log.info("Disabled Right Arrow on Paginator is not found");
		}
	}

	public static void studentListMethodFour() {
		UtilityMethods.scrollPageDown(Driver.webdriver, 10);
		try {
			Thread.sleep(1000);
			circleList.get(2).click();
			Thread.sleep(1000);
			UtilityMethods.scrollPageUp(Driver.webdriver);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
	}

	public static void studentListMethodFive(int index) {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 10);
			Thread.sleep(1000);
			circleList.get(index).click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
	}

	public static void studentListMethodSix() {
		try {
			enabledRightArrow.click();
			Thread.sleep(500);
			enabledLeftArrow = homePage.studentlistenabledleftarrow;
			enabledLeftArrow.isDisplayed();
		} catch (Exception e) {
			log.error(e.getMessage());
			UtilityMethods.processException(e);
		}
	}
	
	
	public static void strandListMethodOne() {
		try {
		new Actions(Driver.webdriver).moveToElement(homePage.strandnameslist.get(homePage.strandnameslist.size() - 1))
				.click().build().perform();
		Thread.sleep(3000);
		new Actions(Driver.webdriver).moveToElement(homePage.reddownarrow).click().build().perform();
		Thread.sleep(2000);
		elList = Driver.webdriver.findElements(By.xpath(
				"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
						+ ((homePage.strandnameslist.size() - 1) + 2)
						+ "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
	}
		catch(Exception e) {
			UtilityMethods.processException(e);
		}
	}
	
	public static void checkTestScoreDetails(Integer schoolId,Integer classId,String standardId) {
		try {
		UtilityMethods.scrollPageUp(Driver.webdriver, 2);
		for (int j = 0; j < testNamesList.size(); j++) {
			lm = DatabaseConnection.getTestScoreDetailsInSPInClassContext(DatabaseConnection.conn, schoolId, classId, testNamesList.get(j),
					standardId);
			Iterator<Model> iterator = lm.iterator();
			while (iterator.hasNext()) {
				TestCount++;
				Model m = (Model) iterator.next();
				tsum += m.getAvg_per();
				Assert.assertTrue(testNamesList.get(j).contains(m.getComponent_title()));
				Assert.assertTrue(homePage.testScoreValueInCircle_onlinechart.get(j).getText()
						.equals(String.valueOf(m.getAvg_per())));
				Thread.sleep(1000);
				new Actions(Driver.webdriver).moveToElement(homePage.testScoreValueInCircle_onlinechart.get(j)).click()
						.build().perform();
				Thread.sleep(2000);
				Assert.assertTrue(homePage.testnameontooltip.getText().equals(m.getComponent_title()));
				for (int i = 0; i < homePage.questionlistontooltip.size(); i++) {
					Assert.assertTrue(homePage.questionlistontooltip.get(i).getText()
							.equals(String.valueOf(m.getQuestion_no().get(i))));
					totalQuestionCount++;
				}
				String submittedDateText = homePage.submitteddateontooltip.getText();
				Assert.assertTrue(new SimpleDateFormat("MM/dd/yyyy").format(m.getMinDate())
						.equals(submittedDateText.substring(12, 22)));
				Assert.assertTrue(new SimpleDateFormat("MM/dd/yyyy").format(m.getMaxDate())
						.equals(submittedDateText.substring(24)));
				new Actions(Driver.webdriver).moveToElement(homePage.testScoresPercentage).click().build().perform();
				Thread.sleep(1000);
			}
		}
		UtilityMethods.scrollPageDown(Driver.webdriver, 2);
	}
		catch(Exception e) {
			UtilityMethods.processException(e);
		}
	}
	
}
