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
 * APR 28 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.steps;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.DatabaseConnection;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.IWait;
import com.bec.reporting.utils.PaginationUtility;
import com.bec.reporting.utils.UtilityMethods;
import com.google.common.base.Verify;

import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SprintSixAndSevenSteps {

	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	static String tooltipText = "Note: Average Score for all standards reports equals (earned points/total points)*100";
	JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;
	/**
	 * Verifying the comparison band for school and district in class context in
	 * test score page selector
	 * 
	 * @throws Throwable
	 */
	@Then("^Veriy the comparison band for school and district$")
	public void veriy_the_comparison_band_for_school_and_district() throws Throwable {
		try {
			String testName;
			Integer schoolId = 0;

			Map<Integer, Integer> ids = UtilityMethods.getSchoolIdAndClassId();
			for (Map.Entry<Integer, Integer> entry : ids.entrySet()) {
				schoolId = entry.getKey();
			}
			new Actions(Driver.webdriver).moveToElement(homePage.selectedTestName).build().perform();
			testName = homePage.tooltipofselectedTest.getText();

			homePage.compareschoollabel.click();
			Thread.sleep(1000);
			// verifying the school avg percentage along with the result
			Map<Integer, Integer> schoolResult = DatabaseConnection
					.getSchoolAvgInTSInClass(DatabaseConnection.conn, schoolId, testName);
			for (Map.Entry<Integer, Integer> entry : schoolResult.entrySet()) {
				Assert.assertTrue(homePage.schoolAvgScrInClassInTS.getText().equals(
						"School Average Scores: " + entry.getKey() + "% based on " + entry.getValue() + " results"));
			}
			// verifying the no. of result belong to avg range for school strips
			Map<String, Integer> schoolavgrange = DatabaseConnection.schoolAvgRange;

			for (Map.Entry<String, Integer> entry : schoolavgrange.entrySet()) {
				if (entry.getKey().equals("40-59"))
					Assert.assertTrue(Integer
							.parseInt(homePage.schoolcolouredStripOnStudentList.get(1).getText()) == entry.getValue());
				else if (entry.getKey().equals("80>"))
					Assert.assertTrue(Integer
							.parseInt(homePage.schoolcolouredStripOnStudentList.get(3).getText()) == entry.getValue());
				else if (entry.getKey().equals("<40"))
					Assert.assertTrue(Integer
							.parseInt(homePage.schoolcolouredStripOnStudentList.get(0).getText()) == entry.getValue());
				else if (entry.getKey().equals("60-70"))
					Assert.assertTrue(Integer
							.parseInt(homePage.schoolcolouredStripOnStudentList.get(2).getText()) == entry.getValue());
			}

			homePage.comparedistrictlabel.click();
			Thread.sleep(1000);
			Map<Integer, Integer> districtResult = new HashMap<>();

			int districtId = DatabaseConnection.getDistrictIdBySchoolId(DatabaseConnection.conn, schoolId);
			// verifying the district avg percentage along with the result
			districtResult = DatabaseConnection.getDistrictAvgInTSInClass(DatabaseConnection.conn,
					districtId, testName);
			for (Map.Entry<Integer, Integer> entry : districtResult.entrySet()) {
				Assert.assertTrue(homePage.districtAvgScrInClassInTS.getText().equals(
						"District Average Scores: " + entry.getKey() + "% based on " + entry.getValue() + " results"));
			}
			// verifying the no. of result belong to avg range for district strips
			Map<String, Integer> districtavgrange = DatabaseConnection.districtAvgRange;

			for (Map.Entry<String, Integer> entry : districtavgrange.entrySet()) {
				if (entry.getKey().equals("40-59"))
					Assert.assertTrue(Integer.parseInt(
							homePage.districtcolouredStripOnStudentList.get(1).getText()) == entry.getValue());
				else if (entry.getKey().equals("80>"))
					Assert.assertTrue(Integer.parseInt(
							homePage.districtcolouredStripOnStudentList.get(3).getText()) == entry.getValue());
				else if (entry.getKey().equals("<40"))
					Assert.assertTrue(Integer.parseInt(
							homePage.districtcolouredStripOnStudentList.get(0).getText()) == entry.getValue());
				else if (entry.getKey().equals("60-70"))
					Assert.assertTrue(Integer.parseInt(
							homePage.districtcolouredStripOnStudentList.get(2).getText()) == entry.getValue());
			}

			// now, unchecked both the checkbox one by one and verify that result should not
			// display
			homePage.compareschoollabel.click();
			Thread.sleep(500);
			String output = "";
			try {
				homePage.schoolAvgScrInClassInTS.isDisplayed();
				output = "fail";
			} catch (Exception e1) {
				output = "pass";
			}
			Thread.sleep(500);
			homePage.comparedistrictlabel.click();
			Thread.sleep(500);
			try {
				homePage.districtAvgScrInClassInTS.isDisplayed();
				output = "fail";
			} catch (Exception e1) {
				output = "pass";
			}
			CBTConfiguration.score = output;
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-649 completed");
	}

	@Then("^Verify the tooltipicon in performance over time line chart and student list$")
	public void verify_the_tooltipicon_in_performance_over_time_line_chart_and_student_list() throws Throwable {
		try {
			UtilityMethods.wait_For_Page_Section_Load();
		
			js.executeScript("arguments[0].click();", homePage.performanceovrtimeicon);
			Thread.sleep(1000);
			Assert.assertTrue(homePage.infoicononperformanceovrtimeheader.isDisplayed());
		
			js.executeScript("arguments[0].click();", homePage.infoicononperformanceovrtimeheader);
			
			Thread.sleep(1000);
			Assert.assertTrue(homePage.tooltip.getText().equals(tooltipText));
			Thread.sleep(1000);
			new Actions(Driver.webdriver).moveToElement(homePage.performanceovrtimeicon).click().build().perform();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.infoicononperformanceovrtimeheader.isDisplayed());
			js.executeScript("arguments[0].click();", homePage.infoicononperformanceovrtimeheader);
			Thread.sleep(1000);
			Assert.assertTrue(homePage.tooltip.getText().equals(tooltipText));

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Verify the tooltipicon in performance over time line chart$")
	public void verify_the_tooltipicon_in_performance_over_time_line_chart() throws Throwable {
		try {
			UtilityMethods.wait_For_Page_Section_Load();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.infoicononperformanceovrtimeheader.isDisplayed());
			Thread.sleep(1000);
			js.executeScript("arguments[0].click();", homePage.infoicononperformanceovrtimeheader);
			Thread.sleep(1000);
			Assert.assertTrue(homePage.tooltip.getText().equals(tooltipText));
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Verify the tooltipicon is not present$")
	public void verify_the_tooltipicon_is_not_present() throws Throwable {
		try {
			Assert.assertTrue(homePage.infoicononperformanceovrtimeheader.isDisplayed());
			CBTConfiguration.score = "fail";
		} catch (Exception e) {
			CBTConfiguration.score = "pass";
		}
		log.info("Scenario BE-653 completed");
	}
	
	/**
	 * This method is used to verify the diamond icon on testname over x-axis on performance over time line chart
	 * @throws Throwable
	 */
	@Then("^verify the diamond shape stroke on the x-axis and Color changes within the Line Charts$")
	public void verify_the_diamond_shape_stroke_on_the_x_axis_and_Color_changes_within_the_Line_Charts() throws Throwable {
		try {
			
			//this try will execute in class context ,standard performance scenario
			try {
				Thread.sleep(2000);
				homePage.performanceovrtimeicon.click();
				Thread.sleep(2000);
			}
			catch(Exception e) {}
			UtilityMethods.scrollPageDown(Driver.webdriver, 9);
			if (PaginationUtility.paginatorFound) {
				PaginationUtility.methodTwo();
				if (PaginationUtility.enabledLeftArrowFound) {
					do {
						PaginationUtility.methodThree();
						if (PaginationUtility.doneWithThreeCircle) {
							PaginationUtility.methodFour();
							
							for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
								Assert.assertTrue(homePage.diamondshapesymblonPerPage_onlinechart.get(j).isDisplayed());
								homePage.testScoreValueInCircle_onlinechart.get(j).click();
								Thread.sleep(3000);
								Assert.assertTrue(homePage.highlightedtestName_onlinechart.isDisplayed());
							}
							UtilityMethods.scrollPageUp(Driver.webdriver, 2);
							Thread.sleep(1000);
						} else {
							for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
								PaginationUtility.methodFive(i);
									for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
										Assert.assertTrue(homePage.diamondshapesymblonPerPage_onlinechart.get(j).isDisplayed());
										homePage.testScoreValueInCircle_onlinechart.get(j).click();
										Thread.sleep(3000);
										Assert.assertTrue(homePage.highlightedtestName_onlinechart.isDisplayed());
									}
									UtilityMethods.scrollPageUp(Driver.webdriver, 2);
									Thread.sleep(1000);
								}
							PaginationUtility.doneWithThreeCircle = true;
						}
						PaginationUtility.methodSix();
					} while (!PaginationUtility.disableLeftArrowFound);
				} else {
					for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
						PaginationUtility.methodFive(i);
						for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
							Assert.assertTrue(homePage.diamondshapesymblonPerPage_onlinechart.get(j).isDisplayed());
							homePage.testScoreValueInCircle_onlinechart.get(j).click();
							Thread.sleep(3000);
							Assert.assertTrue(homePage.highlightedtestName_onlinechart.isDisplayed());
						}
							UtilityMethods.scrollPageUp(Driver.webdriver, 2);
							Thread.sleep(1000);
					}
				}

			} else {
				for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
					Assert.assertTrue(homePage.diamondshapesymblonPerPage_onlinechart.get(j).isDisplayed());
					homePage.testScoreValueInCircle_onlinechart.get(j).click();
					Thread.sleep(2000);
					Assert.assertTrue(homePage.highlightedtestName_onlinechart.isDisplayed());
				}
			}

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-650 and 682 completed");
	}
	
	@Then("^verify the dotted line and triangle line on line chart based on selection of school and district and class checkbox$")
	public void verify_the_dotted_line_and_triangle_line_on_line_chart_based_on_selection_of_school_and_district_and_class_checkbox() throws Throwable {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			boolean isClassComparePresence=false;
			Thread.sleep(2000);
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
			Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
			Thread.sleep(500);
			homePage.schooldropdownbtn.click();
			Thread.sleep(2000);
			homePage.searchbaronschooldropdown.sendKeys("School aeGee8gu");
			new Actions(Driver.webdriver).moveToElement(homePage.schoollist.get(0)).click().build().perform();
			Thread.sleep(3000);
			homePage.classdropdownbtn.click();
			Thread.sleep(2000);
			new Actions(Driver.webdriver).moveToElement(homePage.classlist.get(1)).click().build().perform();
			Thread.sleep(3000);
			homePage.rosterapplybtn.click();
			Thread.sleep(3000);	
			new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
			UtilityMethods.wait_For_Page_Section_Load();
			int no = (int) (Math.random() * homePage.strandnameslist.size());
			homePage.strandnameslist.get(no).click();
			Thread.sleep(3000);
			
			WebElement el=Driver.webdriver.findElements(By.xpath(
					"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
							+ (no + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]")).get(0);
			new Actions(Driver.webdriver).moveToElement(el).build().perform();
			Thread.sleep(500);
			el.click();
			Thread.sleep(3000);
			try {
				//This will execute in case of SP in class context
			new Actions(Driver.webdriver).moveToElement(homePage.performanceovrtimeicon).click().build().perform();
			Thread.sleep(2000);
			
			}
			catch(Exception e) {}
			//selecting school,district,class checkbox
			UtilityMethods.scrollPageDown(Driver.webdriver, 8);
			try {
				homePage.compareclasslabel.click();
				Thread.sleep(1000);
				Assert.assertTrue(homePage.classpath.isDisplayed());
				Assert.assertTrue(homePage.classpath.getAttribute("stroke").equals("#00539b"));
				isClassComparePresence=true;
			}
			catch(Exception e) {
				log.info("Compare Class Checkbox is not on SP in Class Context");
			}
			Thread.sleep(1000);
			
			homePage.compareschoollabel.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.schoolpath.isDisplayed());
			Assert.assertTrue(homePage.schoolpath.getAttribute("stroke").equals("#00539b"));
			homePage.comparedistrictlabel.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.districtpath.isDisplayed());
			Assert.assertTrue(homePage.districtpath.getAttribute("stroke").equals("#00539b"));
			//De-selecting school and district and class if there.
			homePage.selectedcompareschoollabel.click();
			Thread.sleep(1000);
			try {
				Assert.assertTrue(homePage.schoolpath.isDisplayed());				
				log.error("School path is still displaying while de-selecting school checkbox ");
				UtilityMethods.processException(new Exception());
			}
			catch(Exception e) {
				log.info("School Path is not displaying while de-selecting School checkbox ");
			}

			homePage.selectedcomparedistrictlabel.click();
			Thread.sleep(1000);
			try {
				Assert.assertTrue(homePage.districtpath.isDisplayed());
				log.error("District path is still displaying while de-selecting District checkbox ");
				UtilityMethods.processException(new Exception());
			}
			catch(Exception e) {
				log.info("District Path is not displaying while de-selecting District checkbox ");
			}
			
			if (isClassComparePresence) {
				homePage.selectedcompareclasslabel.click();
				Thread.sleep(1000);
				try {
					Assert.assertTrue(homePage.classpath.isDisplayed());
					log.error("classpath is still displaying while de-selecting class checkbox ");
					UtilityMethods.processException(new Exception());
				} catch (Exception e) {
					log.info("classpath is not displaying while de-selecting class checkbox ");
				}
			}
			
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-666,685,677,686 completed");
	}
	
	@Then("^Veriy the Selection and Deselection of the Comparison options within the Line Charts for Particular Test$")
	public void veriy_the_Selection_and_Deselection_of_the_Comparison_options_within_the_Line_Charts_for_Particular_Test() throws Throwable {
		try {
			Thread.sleep(2000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			int no=(int)(Math.random()*homePage.testNamesonPerPage_onlinechart.size());
			new Actions(Driver.webdriver).moveToElement(homePage.testScoreValueInCircle_onlinechart.get(no)).click().build().perform();
			Thread.sleep(3000);
			homePage.compareschoollabel.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.schoolpath.isDisplayed());
			Assert.assertTrue(homePage.schoolpath.getAttribute("stroke").equals("#00539b"));
			homePage.comparedistrictlabel.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.districtpath.isDisplayed());
			Assert.assertTrue(homePage.districtpath.getAttribute("stroke").equals("#00539b"));
		
			//De-selecting school and district and class if there.
			homePage.selectedcompareschoollabel.click();
			Thread.sleep(1000);
			try {
				Assert.assertTrue(homePage.schoolpath.isDisplayed());				
				log.error("School path is still displaying while de-selecting school checkbox ");
				UtilityMethods.processException(new Exception());
			}
			catch(Exception e) {
				log.info("School Path is not displaying while de-selecting School checkbox ");
			}

			homePage.selectedcomparedistrictlabel.click();
			Thread.sleep(1000);
			try {
				Assert.assertTrue(homePage.districtpath.isDisplayed());
				log.error("District path is still displaying while de-selecting District checkbox ");
				UtilityMethods.processException(new Exception());
			}
			catch(Exception e) {
				log.info("District Path is not displaying while de-selecting District checkbox ");
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario  BE-678,689 completed");
	}
	
}
