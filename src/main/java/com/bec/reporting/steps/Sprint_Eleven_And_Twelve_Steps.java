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
 * AUG 01 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.steps;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.UtilityMethods;
import com.bec.reporting.utils.Driver.BrowserCleanup;
import com.google.common.collect.Ordering;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sprint_Eleven_And_Twelve_Steps {

	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);

	@Then("^verify Showing Window, Moving Student from one group to another group within the Grouping page$")
	public void verify_Showing_Window_Moving_Student_from_one_group_to_another_group_within_the_Grouping_page()
			throws Throwable {
		try {
			Thread.sleep(3000);
			new Actions(Driver.webdriver).moveToElement(homePage.groupingTab).click().build().perform();
			Thread.sleep(5000);
			Assert.assertTrue(homePage.groupingTabText.getText().equals("Edit Groups"));
			new Actions(Driver.webdriver).moveToElement(homePage.selectStrandsOnGroupingTab).click().build().perform();
			Thread.sleep(500);
			new Actions(Driver.webdriver).moveToElement(homePage.selectStrandsListInDropdownGroupingTab.get(0)).click()
					.build().perform();
			Thread.sleep(500);
			int length = homePage.selectStrandsListInDropdownGroupingTab.size();
			for (int i = 1; i < length; i = i + 2) {
				new Actions(Driver.webdriver).moveToElement(homePage.selectStrandsListInDropdownGroupingTab.get(i))
						.click().build().perform();
				Thread.sleep(500);
			}
			homePage.doneBtnOnSelectStrandOnGroupingTab.click();
			Thread.sleep(500);
			homePage.applyBtnOngroupingTab.click();
			Sprint_Eight_And_Tenth_Steps st = new Sprint_Eight_And_Tenth_Steps();
			st.wait_For_Strands_Text_After_Apply_BtnOn_GroupingTab();
			Thread.sleep(500);
			int ran_no = (int) (homePage.studentNamesOnGroupingTable.size() * Math.random());
			String name = homePage.studentNamesOnGroupingTable.get(ran_no).getText();
			new Actions(Driver.webdriver).moveToElement(homePage.studentNamesOnGroupingTable.get(ran_no)).build()
					.perform();
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			new Actions(Driver.webdriver).moveToElement(homePage.studentNamesOnGroupingTable.get(ran_no)).build()
					.perform();
			Thread.sleep(1000);
			homePage.visibleArrowListOnGroupingTable.get(ran_no).click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.movingTxtonModelOnGroupingTable.getText().contains(name));
			homePage.NoOfGrpsononModelOnGroupingTable.get(0).click();
			Thread.sleep(1000);
			homePage.moveBtnonModelOnGroupingTable.click();
			Thread.sleep(1000);
			WebElement el = Driver.webdriver.findElement(By.xpath(
					"//span[@class='bec_group_multi_list_Single_group_body_row_student_name_Name' and contains(text(),'"
							+ name + "')]"));
			new Actions(Driver.webdriver).moveToElement(el).build().perform();

			WebElement elFlag = Driver.webdriver.findElement(By.xpath(
					"//span[@class='bec_group_multi_list_Single_group_body_row_student_name_Name' and contains(text(),'"
							+ name
							+ "')]/ancestor::div[@class='bec_group_multi_list_Single_group_body_row_student_name']//i[contains(text(),'flag')]"));
			Assert.assertTrue(elFlag.isDisplayed());
			new Actions(Driver.webdriver).moveToElement(elFlag).build().perform();
			Thread.sleep(1500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		CBTConfiguration.score = "pass";
		log.info("Scenario BE-918,919 completed");
	}

	@Then("^verify to check the appearance and behaviour of the teacher dropdown within the Roster tab$")
	public void verify_to_check_the_appearance_and_behaviour_of_the_teacher_dropdown_within_the_Roster_tab()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.teachersdropdownbtn.isDisplayed());
			homePage.teachersdropdownbtn.click();
			Thread.sleep(1000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);
			Thread.sleep(500);
			String selectedTeacher = homePage.teacherslist.get(0).getText();
			homePage.teacherslist.get(0).click();
			Thread.sleep(3000);
			log.info("Selected Teacher is:" + selectedTeacher);

			homePage.classdropdownbtn.click();
			Thread.sleep(1000);
			String selectedClass = homePage.classlist.get(0).getText();
			homePage.classlist.get(0).click();
			Thread.sleep(3000);
			log.info("Selected Class is:" + selectedClass);
			homePage.rosterapplybtn.click();
			Thread.sleep(3000);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			UtilityMethods.wait_For_Context_Header_Section();
			Thread.sleep(1000);
			Assert.assertTrue(selectedTeacher.equals(UtilityMethods.getTeacherNameonUI()));
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		CBTConfiguration.score = "pass";
		log.info("Scenario BE-941 completed");
	}

	@Then("^verify School filter default view reports for the user School admin$")
	public void verify_School_filter_default_view_reports_for_the_user_School_admin() throws Throwable {
		try {
			Thread.sleep(2000);
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeAssessementsbtn.isDisplayed());
			Assert.assertTrue(homePage.activeClassListPage.isDisplayed());
			UtilityMethods.scrollPageDown(Driver.webdriver, 12);
			Thread.sleep(500);
			Assert.assertTrue(homePage.classORSchoolListPageHeadersList.get(0).getText().contains("Class"));
			Assert.assertTrue(homePage.classORSchoolListPageHeadersList.get(1).getText().equals("No. of Qns"));
			Assert.assertTrue(homePage.classORSchoolListPageHeadersList.get(2).getText().equals("% Students Complete"));
			Assert.assertTrue(homePage.classORSchoolListPageHeadersList.get(3).getText().equals("Score"));
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Assert.assertTrue(homePage.classListPageTooltipForClass.getText()
					.equals("Classes Assessed Online/Classes Rostered."));
			List<String> classList = new ArrayList<String>();
			for (int i = 0; i < homePage.classORSchoolNamesListInClassListPageHeaders.size(); i++) {
				classList.add(homePage.classORSchoolNamesListInClassListPageHeaders.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(classList));
			Thread.sleep(500);
			classList.clear();
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(2)).build()
					.perform();
			Assert.assertTrue(homePage.classORSchoolListPageTooltipForStuCmplt.getText()
					.equals("Students Assessed Online/Students Rostered."));
			// Verify the columns data from db in classList page TODO
			int score = 0;
			WebElement scoreElement = null;
			for (int j = 0; j < homePage.studentscorelistinstudentlist.size(); j++) {
				score = Integer.parseInt(homePage.studentscorelistinstudentlist.get(j).getText());
				scoreElement = homePage.studentscorelistinstudentlist.get(j);
				UtilityMethods.verifyColorAndScoreOnStudentList(scoreElement, score);
			}
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(1000);
			homePage.classmenu.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeAssessementsbtn.isDisplayed());
			Assert.assertTrue(homePage.activeStudentListPage.isDisplayed());
			homePage.studentmenu.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeAssessementsbtn.isDisplayed());
			Assert.assertTrue(homePage.activePerformanceOverTimePage.isDisplayed());
			homePage.rostertab.click();
			Thread.sleep(1000);
			homePage.gradedropdownbtn.click();
			Thread.sleep(500);
			homePage.gradelist.get(1).click();
			Thread.sleep(500);
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(500);
			homePage.teachersdropdownbtn.click();
			Thread.sleep(500);
			homePage.teacherslist.get(1).click();
			Thread.sleep(500);
			homePage.teachersdropdownbtn.click();
			Thread.sleep(500);
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(500);
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			homePage.classlist.get(1).click();
			Thread.sleep(500);
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(500);
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			homePage.studentlistondropdown.get(1).click();
			Thread.sleep(500);
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			homePage.rosterapplybtn.click();
			Thread.sleep(3000);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(500);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-944,945 ,949 a,950,951,952completed");
	}

	@Then("^verify District filter default view reports for the user District admin$")
	public void verify_District_filter_default_view_reports_for_the_user_District_admin() throws Throwable {
		try {
			Thread.sleep(2000);
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeAssessementsbtn.isDisplayed());
			Assert.assertTrue(homePage.activeSchoolListPage.isDisplayed());
			Assert.assertTrue(homePage.classORSchoolListPageHeadersList.get(0).getText().contains("School"));
			Assert.assertTrue(homePage.classORSchoolListPageHeadersList.get(1).getText().equals("No. of Qns"));
			Assert.assertTrue(homePage.classORSchoolListPageHeadersList.get(2).getText().equals("% Students Complete"));
			Assert.assertTrue(homePage.classORSchoolListPageHeadersList.get(3).getText().equals("Score"));

			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Assert.assertTrue(homePage.schoolListPageTooltipForClass.getText()
					.equals("Schools Assessed Online/Schools Rostered."));
			List<String> schoolList = new ArrayList<String>();
			for (int i = 0; i < homePage.classORSchoolNamesListInClassListPageHeaders.size(); i++) {
				schoolList.add(homePage.classORSchoolNamesListInClassListPageHeaders.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(schoolList));
			Thread.sleep(500);
			schoolList.clear();
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(2)).build()
					.perform();
			Assert.assertTrue(homePage.classORSchoolListPageTooltipForStuCmplt.getText()
					.equals("Students Assessed Online/Students Rostered."));
			// Verify the columns data from db in classList page TODO
			int score = 0;
			WebElement scoreElement = null;
			for (int j = 0; j < homePage.studentscorelistinstudentlist.size(); j++) {
				score = Integer.parseInt(homePage.studentscorelistinstudentlist.get(j).getText());
				scoreElement = homePage.studentscorelistinstudentlist.get(j);
				UtilityMethods.verifyColorAndScoreOnStudentList(scoreElement, score);
			}
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(1000);
			homePage.schoolmenu.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeAssessementsbtn.isDisplayed());
			Assert.assertTrue(homePage.activeClassListPage.isDisplayed());

			homePage.classmenu.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeAssessementsbtn.isDisplayed());
			Assert.assertTrue(homePage.activeStudentListPage.isDisplayed());
			homePage.studentmenu.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeAssessementsbtn.isDisplayed());
			Assert.assertTrue(homePage.activePerformanceOverTimePage.isDisplayed());
			// Need to revisit district login once it is added in UI
			/*
			 * homePage.rostertab.click(); Thread.sleep(1000);
			 * homePage.gradedropdownbtn.click();Thread.sleep(500);
			 * homePage.gradelist.get(1).click();Thread.sleep(500);
			 * UtilityMethods.scrollPageDown(Driver.webdriver,3);Thread.sleep(500);
			 * homePage.teachersdropdownbtn.click();Thread.sleep(500);
			 * homePage.teacherslist.get(1).click();Thread.sleep(500);
			 * homePage.teachersdropdownbtn.click();Thread.sleep(500);
			 * UtilityMethods.scrollPageDown(Driver.webdriver,3);Thread.sleep(500);
			 * homePage.classdropdownbtn.click();Thread.sleep(500);
			 * homePage.classlist.get(1).click();Thread.sleep(500);
			 * homePage.classdropdownbtn.click();Thread.sleep(500);
			 * UtilityMethods.scrollPageDown(Driver.webdriver,3);Thread.sleep(500);
			 * homePage.studentdropdownbtn.click();Thread.sleep(500);
			 * homePage.studentlistondropdown.get(1).click();Thread.sleep(500);
			 * homePage.studentdropdownbtn.click();Thread.sleep(500);
			 * homePage.rosterapplybtn.click(); Thread.sleep(3000);
			 * UtilityMethods.scrollPageUp(Driver.webdriver);Thread.sleep(500);
			 * UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			 * UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			 */
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-946 ,949 b,956,953,954,955 completed");
	}

	@Then("^verify No test data available for this selection screen on selection from the Roster tab$")
	public void verify_No_test_data_available_for_this_selection_screen_on_selection_from_the_Roster_tab()
			throws Throwable {
		try {
			homePage.rostertab.click();
			Thread.sleep(1000);
			homePage.gradedropdownbtn.click();
			Thread.sleep(500);
			homePage.gradelist.get(2).click();
			Thread.sleep(500);
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(500);
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			homePage.classlist.get(1).click();
			Thread.sleep(500);
			homePage.rosterapplybtn.click();
			Thread.sleep(3000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(500);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.overviewtext.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-983 completed");
	}

	@Then("^verify Screen shown to the user for any kind of Technical Error$")
	public void verify_Screen_shown_to_the_user_for_any_kind_of_Technical_Error() throws Throwable {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(500);
			Assert.assertTrue(homePage.nodatavailable.isDisplayed());
			Assert.assertTrue(homePage.takemeback.isDisplayed());
			homePage.takemeback.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.beclogo.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-1127 completed");
		new BrowserCleanup();
	}

	@Then("^verify No data screen for District term which has no test data within the date tab$")
	public void verify_No_data_screen_for_District_term_which_has_no_test_data_within_the_date_tab() throws Throwable {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);
			Thread.sleep(500);
			homePage.datetab.click();
			Thread.sleep(500);
			homePage.districttermdropdownbtn.click();
			Thread.sleep(500);
			homePage.districttermlist.get(1).click();
			Thread.sleep(500);
			homePage.dateapplybtn.click();
			Thread.sleep(3000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(3000);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(500);
			Assert.assertTrue(homePage.overviewtext.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-988 completed");
	}

	@Then("^verify Showing No data screen for Date range which has no test data within the date tab$")
	public void verify_Showing_No_data_screen_for_Date_range_which_has_no_test_data_within_the_date_tab()
			throws Throwable {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);
			Thread.sleep(500);
			homePage.datetab.click();
			Thread.sleep(500);
			homePage.daterangedropdownbtn.click();
			Thread.sleep(500);
			homePage.dateRangelist.get(0).click();
			Thread.sleep(500);
			homePage.dateapplybtn.click();
			Thread.sleep(3000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(3000);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(500);
			Assert.assertTrue(homePage.overviewtext.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-989 completed");
	}

	@Then("^verify No data screen for custom date selected which has no test data within the date tab$")
	public void verify_No_data_screen_for_custom_date_selected_which_has_no_test_data_within_the_date_tab()
			throws Throwable {
		try {
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);
			Thread.sleep(500);
			homePage.datetab.click();
			Thread.sleep(500);
			homePage.daterangedropdownbtn.click();
			Thread.sleep(500);
			homePage.dateRangelist.get(4).click();
			Thread.sleep(500);
			UtilityMethods.scrollPageDown(Driver.webdriver, 9);
			Thread.sleep(500);
			int num = (int) (homePage.activeDateRangelistoOnCalender.size() * Math.random());
			homePage.activeDateRangelistoOnCalender.get(num).click();
			Thread.sleep(500);
			homePage.edate.click();
			Thread.sleep(500);
			homePage.monthDropDown.click();
			Thread.sleep(500);
			homePage.monthDropDownList.get(1).click();
			Thread.sleep(500);
			num = (int) (homePage.activeDateRangelistoOnCalender.size() * Math.random());
			homePage.activeDateRangelistoOnCalender.get(num).click();
			Thread.sleep(500);
			homePage.dateapplybtn.click();
			Thread.sleep(3000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(3000);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(500);
			Assert.assertTrue(homePage.overviewtext.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-990 completed");
	}

	@Then("^verify Screen shown to the user if the standards are null the reporting db$")
	public void verify_Screen_shown_to_the_user_if_the_standards_are_null_the_reporting_db() throws Throwable {
		try {
			homePage.rostertab.click();
			Thread.sleep(1000);
			homePage.gradedropdownbtn.click();
			Thread.sleep(500);
			// check the list in Descending order
			List<String> gradeList = new ArrayList<String>();
			int length = homePage.gradelist.size();
			for (int i = 0; i < length; i++) {
				gradeList.add(homePage.gradelist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(gradeList));
			Thread.sleep(500);
			homePage.gradelist.get(1).click();
			Thread.sleep(500);
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(500);
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			homePage.classlist.get(3).click();
			Thread.sleep(500);
			homePage.rosterapplybtn.click();
			Thread.sleep(3000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(500);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.overviewtext.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-1130 completed");
	}

	@Then("^verify The Persistence across level and reports of Standard Performance Overview$")
	public void verify_The_Persistence_across_level_and_reports_of_Standard_Performance_Overview() throws Throwable {
		try {
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			int randNo=UtilityMethods.generateRandomNumberBySkippingIndex(homePage.strandnameslist.size(), 0);
			new Actions(Driver.webdriver).moveToElement(homePage.strandnameslist.get(randNo)).click().build()
			.perform();
			Thread.sleep(3000);
			List<WebElement> standardList=Driver.webdriver.findElements(By.xpath(
			"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
					+ (randNo + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
			new Actions(Driver.webdriver).moveToElement(standardList.get(0)).build().perform();Thread.sleep(500);
			new Actions(Driver.webdriver).moveToElement(standardList.get(0)).click().build().perform();
			Thread.sleep(5000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 8);
			Assert.assertTrue(homePage.noofquestionstext.isDisplayed());
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolNamesListInClassListPageHeaders.get(0)).click().build().perform();
			Thread.sleep(5000);
			UtilityMethods.scrollPageUp(Driver.webdriver);Thread.sleep(5000);
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());
			//verify class level based standard performance at DB level
			UtilityMethods.scrollPageDown(Driver.webdriver, 10);
			Assert.assertTrue(homePage.noofquestionstext.isDisplayed());
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolNamesListInClassListPageHeaders.get(0)).click().build().perform();
			Thread.sleep(5000);
			UtilityMethods.scrollPageUp(Driver.webdriver);Thread.sleep(500);
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-1184 completed");
	}
	//TODO---TC is failing because once click on class list 's class ... no data available popup is showing.
	@Then("^verify The Persistence across level and reports of Test Scores Overview$")
	public void verify_The_Persistence_across_level_and_reports_of_Test_Scores_Overview() throws Throwable {
		try{
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			homePage.testscoresbtn.click();Thread.sleep(3000);
			Assert.assertTrue(homePage.testscoreovertimelinechart.isDisplayed());
			UtilityMethods.scrollPageDown(Driver.webdriver, 7);
			Thread.sleep(3000);
			int randNo=UtilityMethods.generateRandomNumberBySkippingIndex(homePage.testNamesonPerPage_onlinechart.size(), homePage.testNamesonPerPage_onlinechart.size()-1);
			new Actions(Driver.webdriver).moveToElement(homePage.testScoreValueInCircle_onlinechart.get(randNo)).click().build().perform();
			Thread.sleep(3000);
			new Actions(Driver.webdriver).moveToElement(homePage.testNamesonPerPage_onlinechart.get(randNo)).build().perform();
			Thread.sleep(500);
			String testNameOnleftSide=homePage.testNametooltip_onlinechart.getText();
			Thread.sleep(500);
			new Actions(Driver.webdriver).moveToElement(homePage.testScoresPercentage).build().perform();
			Thread.sleep(500);
			new Actions(Driver.webdriver).moveToElement(homePage.testNameOnTestScoreDetail).build().perform();
			Thread.sleep(500);
			String testNameOnRightSide=homePage.tooltipOftestNameOnTestScoreDetail.getText();			
			Assert.assertTrue(testNameOnRightSide.equals(testNameOnleftSide));
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolNamesListInClassListPageHeaders.get(0)).click().build().perform();
			Thread.sleep(5000);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-1185 completed");
	}

}
