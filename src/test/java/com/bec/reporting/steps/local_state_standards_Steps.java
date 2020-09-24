package com.bec.reporting.steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.RosterTabUtilityMethods;
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

			String stname;
			int index_one, index_two, index_three;
			for (int i = 0; i < homePage.standardnameslist.size(); i++) {
				stname = homePage.standardnameslist.get(i).getText();
				index_one = stname.indexOf(".");
				index_two = stname.indexOf(".", index_one + 1);
				index_three = stname.indexOf(".", index_two + 1);

				Assert.assertTrue(UtilityMethods.isNumber(stname.substring(0, index_one).trim()));
				Assert.assertTrue(UtilityMethods.isAlphabate(stname.substring(index_one + 1, index_two).trim()));
				if (index_three == -1) {
					Assert.assertTrue(UtilityMethods.isNumber(stname.substring(index_two + 1).trim()));
				} else {
					Assert.assertTrue(UtilityMethods.isNumber(stname.substring(index_two + 1, index_three).trim()));
					Assert.assertTrue(UtilityMethods.isAlphabate(stname.substring(index_three + 1).trim()));
				}
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
			Thread.sleep(10000);
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
			Assert.assertTrue(homePage.chkbox_list_text_under_view_on_sta_filter.get(3).getText().startsWith("AZ"));
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

			String stname;
			int index_one, index_two, index_three;
			for (int i = 0; i < homePage.standardnameslist.size(); i++) {
				stname = homePage.standardnameslist.get(i).getText();
				index_one = stname.indexOf(".");
				index_two = stname.indexOf(".", index_one + 1);
				index_three = stname.indexOf(".", index_two + 1);

				Assert.assertTrue(UtilityMethods.isNumber(stname.substring(0, index_one).trim()));
				Assert.assertTrue(UtilityMethods.isAlphabate(stname.substring(index_one + 1, index_two).trim()));
				if (index_three == -1) {
					Assert.assertTrue(UtilityMethods.isNumber(stname.substring(index_two + 1).trim()));
				} else {
					Assert.assertTrue(UtilityMethods.isNumber(stname.substring(index_two + 1, index_three).trim()));
					Assert.assertTrue(UtilityMethods.isAlphabate(stname.substring(index_three + 1).trim()));
				}
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

	@Then("^Verify in roster tab persist student Grade while moving from district to school level reports$")
	public void verify_in_roster_tab_persist_student_Grade_while_moving_from_district_to_school_level_reports()
			throws Throwable {
		try {
			String grd_ch;
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			homePage.rostertab.click();
			Thread.sleep(1000);
			RosterTabUtilityMethods.select_Grade_In_Grades_DropDown("Grade 4");
			String grade = homePage.gradedropdownbtn.getText();
			grd_ch = grade.substring(grade.indexOf(" ") + 1);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			String grade_on_ch = UtilityMethods.getGradeNameonUI();
			Assert.assertTrue(grd_ch.equals(grade_on_ch));

			homePage.rostertab.click();
			Thread.sleep(1000);
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("School");

			// selecting custom School from dropdown
			for (int i = 1; i < homePage.schoollist.size(); i = i + 2) {
				if (homePage.schoollist.get(i).getText().equals("")) {
					UtilityMethods.scroll_Div(homePage.schoollist.get(i), 20);
					Thread.sleep(500);
				}
				homePage.schoollist.get(i).click();
				Thread.sleep(500);
			}
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			String sc_name = homePage.schooldropdownbtn.getText();
			String grade_one = homePage.gradedropdownbtn.getText();
			grd_ch = grade_one.substring(grade_one.indexOf(" ") + 1);
			Assert.assertTrue(grade_one.equals(grade));
			homePage.rosterapplybtn.click();
			Thread.sleep(500);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			grade_on_ch = UtilityMethods.getGradeNameonUI();
			Assert.assertTrue(grd_ch.equals(grade_on_ch));
			String sc_name_ch = UtilityMethods.getSchoolNameonUI();
			Assert.assertTrue(sc_name_ch.equals(sc_name));

			homePage.rostertab.click();
			Thread.sleep(1000);
			sc_name = RosterTabUtilityMethods.select_School_In_School_DropDown("Golden Oak Community School");
			String grade_two = homePage.gradedropdownbtn.getText();
			Assert.assertTrue(grade_two.equals(grade_one));
			grade_two = grade_two.substring(grade_two.indexOf(" ") + 1);
			homePage.rosterapplybtn.click();
			Thread.sleep(500);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

			grade_on_ch = UtilityMethods.getGradeNameonUI();
			Assert.assertTrue(grade_two.equals(grade_on_ch));
			sc_name_ch = UtilityMethods.getSchoolNameonUI();
			Assert.assertTrue(sc_name_ch.equals(sc_name));

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-9258 completed");
	}

	@Then("^Verify local state standards for school standards performance over time$")
	public void verify_local_state_standards_for_school_standards_performance_over_time() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			try {
				Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			} catch (Exception e) {
				Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			}
			homePage.rostertab.click();
			Thread.sleep(1000);
			RosterTabUtilityMethods.select_Grade_In_Grades_DropDown("Grade 1");
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.viewDropDownList.get(0).getText().startsWith("AZ"));
			homePage.viewDropDownList.get(0).click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			String stname;
			int index_one, index_two, index_three;
			for (int i = 0; i < homePage.standardnameslist.size(); i++) {
				stname = homePage.standardnameslist.get(i).getText();
				index_one = stname.indexOf(".");
				index_two = stname.indexOf(".", index_one + 1);
				index_three = stname.indexOf(".", index_two + 1);

				Assert.assertTrue(UtilityMethods.isNumber(stname.substring(0, index_one).trim()));
				Assert.assertTrue(UtilityMethods.isAlphabate(stname.substring(index_one + 1, index_two).trim()));
				if (index_three == -1) {
					Assert.assertTrue(UtilityMethods.isNumber(stname.substring(index_two + 1).trim()));
				} else {
					Assert.assertTrue(UtilityMethods.isNumber(stname.substring(index_two + 1, index_three).trim()));
					Assert.assertTrue(UtilityMethods.isAlphabate(stname.substring(index_three + 1).trim()));
				}
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
		log.info("Scenario BU-11660, BU-11666 ,BU-11795 ,BU-11798 completed");
	}

	@Then("^Verify local state standards for school standards single test analysis$")
	public void verify_local_state_standards_for_school_standards_single_test_analysis() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			try {
				Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			} catch (Exception e) {
				Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			}
			homePage.rostertab.click();
			Thread.sleep(1000);
			RosterTabUtilityMethods.select_Grade_In_Grades_DropDown("Grade 1");
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			homePage.filter_in_sta_for_district.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.chkbox_list_text_under_view_on_sta_filter.get(3).getText().startsWith("AZ"));
			homePage.cancel_button_on_filter_in_sta.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.view_text_in_sta_table_header.get(0).getText().startsWith("AZ"));

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-11669,BU-11558 completed");
	}

	@Then("^Verify local state standards for school standards summary$")
	public void verify_local_state_standards_for_school_standards_summary() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			try {
				Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			} catch (Exception e) {
				Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			}
			homePage.rostertab.click();
			Thread.sleep(1000);
			RosterTabUtilityMethods.select_Grade_In_Grades_DropDown("Grade 1");
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.viewDropDownList.get(0).getText().startsWith("AZ"));
			homePage.viewDropDownList.get(0).click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

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
		log.info("Scenario BU-11671,BU-11860 completed");
	}

	@Then("^Verify local state standards for school standards comparison$")
	public void verify_local_state_standards_for_school_standards_comparison() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			try {
				Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			} catch (Exception e) {
				Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			}
			homePage.rostertab.click();
			Thread.sleep(1000);
			RosterTabUtilityMethods.select_Grade_In_Grades_DropDown("Grade 1");
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.viewDropDownList.get(0).getText().startsWith("AZ"));
			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).click().build().perform();
			Thread.sleep(500);
			List<String> strand_names_on_sp = UtilityMethods.get_Strand_Names_From_Standard_Performance_Tabel();
			new Actions(Driver.webdriver).moveToElement(homePage.viewDropDown).build().perform();
			Thread.sleep(1000);
			String view_name = homePage.viewDropDownToolTipText.getText();
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			Thread.sleep(20000);
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
		log.info("Scenario BU-11673,BU-11862 completed");
	}

	@Then("^Verify In district context on custom selection of schools, the behaviour of school list in UI$")
	public void verify_In_district_context_on_custom_selection_of_schools_the_behaviour_of_school_list_in_UI()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.schoolListPageTooltipForClass.getText()
					.equals("Schools Assessed Online/Schools Rostered"));
			js.executeScript("arguments[0].click();", homePage.test_scores_btn);
			Thread.sleep(500);
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.schoolListPageTooltipForClass.getText()
					.equals("Schools Assessed Online/Schools Rostered"));
			homePage.rostertab.click();
			Thread.sleep(1000);
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("School");

			// selecting custom School from dropdown
			for (int i = 1; i < homePage.schoollist.size(); i = i + 2) {

				if (homePage.schoollist.get(i).getText().equals("")) {
					UtilityMethods.scroll_Div(homePage.schoollist.get(i), 20);
				}

				homePage.schoollist.get(i).click();
				Thread.sleep(500);
			}
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.schoolListPageTooltipForClass.getText()
					.equals("Schools Assessed Online/Schools Selected"));
			js.executeScript("arguments[0].click();", homePage.standardperformancebtn);
			Thread.sleep(500);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.schoolListPageTooltipForClass.getText()
					.equals("Schools Assessed Online/Schools Selected"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-11779 completed");
	}

	@Then("^Verify In school context on custom selection of classes, the behaviour of class list in UI$")
	public void verify_In_school_context_on_custom_selection_of_classes_the_behaviour_of_class_list_in_UI()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.classListPageTooltipForClass.getText().equals("Classes Assessed Online/Classes Rostered"));
			js.executeScript("arguments[0].click();", homePage.test_scores_btn);
			Thread.sleep(500);
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.classListPageTooltipForClass.getText().equals("Classes Assessed Online/Classes Rostered"));
			homePage.rostertab.click();
			Thread.sleep(1000);
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("Class");

			// selecting custom Class from dropdown
			for (int i = 1; i < homePage.classlist.size(); i = i + 2) {
				if (homePage.classlist.get(i).getText().equals("")) {
					UtilityMethods.scroll_Div(homePage.classlist.get(i), 20);
				}
				homePage.classlist.get(i).click();
				Thread.sleep(500);
			}
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.classListPageTooltipForClass.getText().equals("Classes Assessed Online/Classes Selected"));
			js.executeScript("arguments[0].click();", homePage.standardperformancebtn);
			Thread.sleep(500);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.classListPageTooltipForClass.getText().equals("Classes Assessed Online/Classes Selected"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-11780 completed");
	}

	@Then("^Verify In class context on custom selection of students, the behaviour of student list in UI$")
	public void verify_In_class_context_on_custom_selection_of_students_the_behaviour_of_student_list_in_UI()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.studentListPageTooltip.getText().equals("Students Assessed Online/Students Rostered"));
			js.executeScript("arguments[0].click();", homePage.test_scores_btn);
			Thread.sleep(500);
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.studentListPageTooltip.getText().equals("Students Assessed Online/Students Rostered"));
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(500);
			homePage.rostertab.click();
			Thread.sleep(1000);
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("Student");

			// selecting custom Student from dropdown
			for (int i = 1; i < homePage.studentlistwithall.size(); i = i + 2) {
				if (homePage.studentlistwithall.get(i).getText().equals("")) {
					UtilityMethods.scroll_Div(homePage.studentlistwithall.get(i), 20);
				}
				homePage.studentlistwithall.get(i).click();
				Thread.sleep(500);
			}
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.studentListPageTooltip.getText().equals("Students Assessed Online/Students Selected"));
			js.executeScript("arguments[0].click();", homePage.standardperformancebtn);
			Thread.sleep(500);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

			new Actions(Driver.webdriver).moveToElement(homePage.classORSchoolListPageHeadersList.get(0)).build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.studentListPageTooltip.getText().equals("Students Assessed Online/Students Selected"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-11781 completed");
	}
}
