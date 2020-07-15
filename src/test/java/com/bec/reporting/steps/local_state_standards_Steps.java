package com.bec.reporting.steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.UtilityMethods;

import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class local_state_standards_Steps {
	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	public static JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;

	@Then("^Verify local state standards for class standards performance over time$")
	public void verify_local_state_standards_for_class_standards_performance_over_time() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());

			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.viewDropDownList.get(0).getText().startsWith("AZ"));

			homePage.viewDropDownList.get(1).click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).click().build().perform();
			Thread.sleep(500);
			homePage.viewDropDownList.get(0).click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

			List<String> before_dot = new LinkedList<String>();
			List<String> between_dot = new LinkedList<String>();
			List<String> after_dot = new LinkedList<String>();
			String stname;
			for (int i = 0; i < homePage.standardnameslist.size(); i++) {
				stname = homePage.standardnameslist.get(i).getText();
				before_dot.add(stname.substring(0, stname.indexOf(".")).trim());
				between_dot.add(stname.substring(stname.indexOf(".") + 1, stname.lastIndexOf(".")).trim());
				after_dot.add(stname.substring(stname.lastIndexOf(".") + 1).trim());
			}

			for (int i = 0; i < homePage.standardnameslist.size(); i++) {
				Assert.assertTrue(UtilityMethods.isNumber(before_dot.get(i)));
				Assert.assertTrue(UtilityMethods.isAlphabate(between_dot.get(i)));
				Assert.assertTrue(UtilityMethods.isNumber(after_dot.get(i)));
			}

			new Actions(Driver.webdriver).moveToElement(homePage.standardnameslist.get(0)).click().build().perform();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			new Actions(Driver.webdriver).moveToElement(homePage.standardnameslist.get(0)).build().perform();
			Thread.sleep(500);
			String st_def = homePage.stranddefinitionlist.get(0).getText();
			String st_desc = homePage.standarddescriptionlist.get(0).getText();
			st_desc = st_desc.substring(0, st_desc.indexOf(":") - 1) + st_desc.substring(st_desc.indexOf(":"));
			new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
			Thread.sleep(500);
			// verifying header and subheader with stadard details from sp table on student
			// list

			Assert.assertTrue(homePage.pot_header.getText().equals(st_def));
			Assert.assertTrue(homePage.pot_subheader.getText().equals(st_desc));
			// verifying header and subheader with stadard details from sp table on POT
			// graph

			js.executeScript("arguments[0].click();", homePage.performance_overtime_icon);
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			Assert.assertTrue(homePage.pot_header.getText().equals(st_def));
			Assert.assertTrue(homePage.pot_subheader.getText().equals(st_desc));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-9234,9236 completed");
	}

	@Then("^Verify local state standards for class and or student standards comparison$")
	public void verify_local_state_standards_for_class_and_or_student_standards_comparison() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			List<String> strand_names_on_sp = UtilityMethods.get_Strand_Names_From_Standard_Performance_Tabel();
			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).build().perform();
			Thread.sleep(1000);
			String view_name = homePage.viewDropDownToolTipText.getText();
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			Thread.sleep(25000);
			Assert.assertTrue(homePage.dropDowns_on_edit_standards_on_pop_up.get(0).getText().equals(view_name));
			for (int i = 0; i < strand_names_on_sp.size(); i++) {
				Assert.assertTrue(strand_names_on_sp.get(i)
						.equals(homePage.strands_name_list_on_grouping_or_comparison_popup.get(i).getText()));
			}
			js.executeScript("arguments[0].click();", homePage.applyBtnOnstandardTab);
			Thread.sleep(500);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();

			List<String> strand_names_on_comparison = UtilityMethods.get_Strand_Names_From_Comparison_Tabel();
			for (int i = 0; i < strand_names_on_sp.size(); i++) {
				Assert.assertTrue(strand_names_on_sp.get(i).equals(strand_names_on_comparison.get(i)));
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-9226,BU-9217 completed");
	}

	@Then("^Verify local state standards for class standards grouping$")
	public void verify_local_state_standards_for_class_standards_grouping() throws Throwable {
		try {
			String note_for_less_than_two_students = "Note: A group cannot be made with less than 2 students based on the selection(s) above.";
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			List<String> strand_names_on_sp = UtilityMethods.get_Strand_Names_From_Standard_Performance_Tabel();
			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).build().perform();
			Thread.sleep(1000);
			String view_name = homePage.viewDropDownToolTipText.getText();
			js.executeScript("arguments[0].click();", homePage.groupingTab);
			Thread.sleep(12000);
			Assert.assertTrue(homePage.testAssessedForGradeGroupingTab.isDisplayed());
			Assert.assertTrue(homePage.view_txt_on_GroupingTab_popup.getText().equals(view_name));
			for (int i = 0; i < strand_names_on_sp.size(); i++) {
				Assert.assertTrue(strand_names_on_sp.get(i)
						.equals(homePage.strands_name_list_on_grouping_or_comparison_popup.get(i).getText()));
			}
			try {
				Assert.assertTrue(
						homePage.alert_txt_on_grouping_tab_pop_up.getText().equals(note_for_less_than_two_students));
			} catch (Exception e) {
				js.executeScript("arguments[0].click();", homePage.applyBtnOngroupingTab);
				UtilityMethods.wait_For_Strands_Text_After_Apply_BtnOn_GroupingTab();
				Thread.sleep(500);
				List<String> strand_names_on_grouping = UtilityMethods.get_Strand_Names_From_Grouping_Tabel();
				for (int i = 0; i < strand_names_on_sp.size(); i++) {
					Assert.assertTrue(strand_names_on_sp.get(i).equals(strand_names_on_grouping.get(i)));
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-9227 completed");
	}

	@Then("^Verify local state standards for class and or student standards summary$")
	public void verify_local_state_standards_for_class_and_or_student_standards_summary() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Set<String> standard_names_list = UtilityMethods.get_Standard_Names_From_Standard_Performance_Tabel();
			js.executeScript("arguments[0].click();", homePage.summarytab);
			UtilityMethods.wait_For_Summary_Tab_Section_Load();
			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown_on_summary).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.viewDropDown_on_summary.getText().startsWith("AZ"));

			// Sorting HashSet using List
			List<String> list = new ArrayList<String>(standard_names_list);
			Collections.sort(list);

			List<String> st_names_list_on_summary_tab = UtilityMethods.get_Standard_Names_From_Summary_Tabel();

			for (int i = 0; i < list.size(); i++) {
				Assert.assertTrue(list.get(i).equals(st_names_list_on_summary_tab.get(i)));
			}

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-9220,BU-9212 completed");
	}

	@Then("^Verify local state standards for class and or student standards single test analysis$")
	public void verify_local_state_standards_for_class_and_or_student_standards_single_test_analysis()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			homePage.filter_in_sta_for_district.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.chkbox_list_text_under_view_on_sta_filter.get(1).getText().startsWith("AZ"));
			homePage.cancel_button_on_filter_in_sta.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.view_text_in_sta_table_header.get(0).getText().startsWith("AZ"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-9218,BU-9222 completed");
	}

	@Then("^Verify local state standards for student standards performance over time$")
	public void verify_local_state_standards_for_student_standards_performance_over_time() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());

			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.viewDropDownList.get(0).getText().startsWith("AZ"));

			homePage.viewDropDownList.get(1).click();
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();

			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).click().build().perform();
			Thread.sleep(500);
			homePage.viewDropDownList.get(0).click();
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();

			List<String> before_dot = new LinkedList<String>();
			List<String> between_dot = new LinkedList<String>();
			List<String> after_dot = new LinkedList<String>();
			String stname;
			for (int i = 0; i < homePage.standardnameslist.size(); i++) {
				stname = homePage.standardnameslist.get(i).getText();
				before_dot.add(stname.substring(0, stname.indexOf(".")).trim());
				between_dot.add(stname.substring(stname.indexOf(".") + 1, stname.lastIndexOf(".")).trim());
				after_dot.add(stname.substring(stname.lastIndexOf(".") + 1).trim());
			}

			for (int i = 0; i < homePage.standardnameslist.size(); i++) {
				Assert.assertTrue(UtilityMethods.isNumber(before_dot.get(i)));
				Assert.assertTrue(UtilityMethods.isAlphabate(between_dot.get(i)));
				Assert.assertTrue(UtilityMethods.isNumber(after_dot.get(i)));
			}

			new Actions(Driver.webdriver).moveToElement(homePage.standardnameslist.get(0)).build().perform();
			Thread.sleep(500);
			String st_def = homePage.stranddefinitionlist.get(0).getText();
			String st_desc = homePage.standarddescriptionlist.get(0).getText();
			st_desc = st_desc.substring(0, st_desc.indexOf(":") - 1) + st_desc.substring(st_desc.indexOf(":"));
			new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
			Thread.sleep(500);

			new Actions(Driver.webdriver).moveToElement(homePage.standardnameslist.get(0)).click().build().perform();
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			// verifying header and subheader with stadard details from sp table on POT
			// graph
			Assert.assertTrue(homePage.pot_header.getText().equals(st_def));
			Assert.assertTrue(homePage.pot_subheader.getText().equals(st_desc));

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-9232,BU-9238 completed");
	}
}
