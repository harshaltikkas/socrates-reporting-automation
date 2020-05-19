package com.bec.reporting.steps;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;

import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.RosterTabUtilityMethods;
import com.bec.reporting.utils.UtilityMethods;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Validate_Persistence_on_different_switching_steps {
	/**
	 * This is used to initialize webelement of the webpages
	 */
	static HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	public static JavascriptExecutor jse = (JavascriptExecutor) Driver.webdriver;

	@Then("^Verify Test Scores Comparison Persistence on switching to all other levels$")
	public void verify_Test_Scores_Comparison_Persistence_on_switching_to_all_other_levels() throws Throwable {
		try {
			String grade_no_on_ch, test_name_on_ch;
			homePage.rostertab.click();
			Thread.sleep(1000);
			String grade = homePage.gradedropdownbtn.getText();
			grade = grade.substring(grade.indexOf(" ") + 1);
			homePage.rostercancelbtn.click();
			Thread.sleep(500);
			String testname = UtilityMethods.getRandomTest_From_Test_Tab();
			log.info("Selected Test in Test Tab :" + testname);
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			grade_no_on_ch = UtilityMethods.getGradeNameonUI();
			Assert.assertTrue(grade.equals(grade_no_on_ch));
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			Assert.assertTrue(testname.equals(test_name_on_ch));

			jse.executeScript("arguments[0].click();", homePage.testscoresbtn);
			UtilityMethods.wait_For_Test_Score_Detail_Section();

			jse.executeScript("arguments[0].click();", homePage.comparisontab);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load();
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(0).getText().equals("District"));
			homePage.compare_cb_list_on_comparison_tab.get(0).click();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.compare_list_of_avg_on_comparison_tab.get(0).getText().equals("District Average"));

			jse.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load();
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			Assert.assertTrue(testname.equals(test_name_on_ch));

			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(0).getText().equals("School"));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(1).getText().equals("District"));

			homePage.compare_cb_list_on_comparison_tab.get(0).click();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.compare_list_of_avg_on_comparison_tab.get(0).getText().equals("District Average"));
			Assert.assertTrue(homePage.compare_list_of_avg_on_comparison_tab.get(1).getText().equals("School Average"));

			jse.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load();
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			Assert.assertTrue(testname.equals(test_name_on_ch));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(0).getText().equals("Class"));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(1).getText().equals("School"));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(2).getText().equals("District"));

			homePage.compare_cb_list_on_comparison_tab.get(0).click();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.compare_list_of_avg_on_comparison_tab.get(0).getText().equals("District Average"));
			Assert.assertTrue(homePage.compare_list_of_avg_on_comparison_tab.get(1).getText().equals("School Average"));
			Assert.assertTrue(homePage.compare_list_of_avg_on_comparison_tab.get(2).getText().equals("Class Average"));

			jse.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load();
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			Assert.assertTrue(testname.equals(test_name_on_ch));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(0).getText().equals("Class"));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(1).getText().equals("School"));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(2).getText().equals("District"));
			Assert.assertTrue(
					homePage.compare_list_of_avg_on_comparison_tab.get(0).getText().equals("District Average"));
			Assert.assertTrue(homePage.compare_list_of_avg_on_comparison_tab.get(1).getText().equals("School Average"));
			Assert.assertTrue(homePage.compare_list_of_avg_on_comparison_tab.get(2).getText().equals("Class Average"));

			jse.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load();
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			Assert.assertTrue(testname.equals(test_name_on_ch));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(0).getText().equals("Class"));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(1).getText().equals("School"));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(2).getText().equals("District"));
			Assert.assertTrue(
					homePage.compare_list_of_avg_on_comparison_tab.get(0).getText().equals("District Average"));
			Assert.assertTrue(homePage.compare_list_of_avg_on_comparison_tab.get(1).getText().equals("School Average"));
			Assert.assertTrue(homePage.compare_list_of_avg_on_comparison_tab.get(2).getText().equals("Class Average"));

			jse.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load();
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			Assert.assertTrue(testname.equals(test_name_on_ch));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(0).getText().equals("School"));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(1).getText().equals("District"));
			Assert.assertTrue(
					homePage.compare_list_of_avg_on_comparison_tab.get(0).getText().equals("District Average"));
			Assert.assertTrue(homePage.compare_list_of_avg_on_comparison_tab.get(1).getText().equals("School Average"));

			jse.executeScript("arguments[0].click();", homePage.districtmenu);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load();
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			Assert.assertTrue(testname.equals(test_name_on_ch));
			Assert.assertTrue(homePage.compare_cb_list_on_comparison_tab.get(0).getText().equals("District"));
			Assert.assertTrue(
					homePage.compare_list_of_avg_on_comparison_tab.get(0).getText().equals("District Average"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-2277 completed");
	}

	@Then("^Verify Single Test Analysis Persistence on switching to all other levels through subway nav filters$")
	public void verify_Single_Test_Analysis_Persistence_on_switching_to_all_other_levels_through_subway_nav_filters()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			String test_name_on_ch;
			String testname = UtilityMethods.getRandomTest_From_Test_Tab();
			log.info("Selected Test in Test Tab :" + testname);
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			jse.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			Assert.assertTrue(testname.equals(test_name_on_ch));

			// BE-2274
			homePage.filter_in_sta_for_district.click();
			Thread.sleep(1000);
			homePage.all_chkbox_on_filter_in_sta.click();
			Thread.sleep(500);
			String selectedViewName = homePage.chkbox_list_text_under_view_on_sta_filter.get(1).getText();
			homePage.chkbox_list_text_under_view_on_sta_filter.get(1).click();
			Thread.sleep(500);
			log.info("selectedViewName:" + selectedViewName);
			homePage.apply_button_on_filter_in_sta.click();
			Thread.sleep(3000);
			Assert.assertTrue(homePage.view_text_in_sta_table_header.getText().equals(selectedViewName));

			homePage.schoolmenu.click();
			UtilityMethods.wait_For_STA_Section_Load();
			Thread.sleep(1000);
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			log.info("Test Name on CH on school menu:" + test_name_on_ch);
			Assert.assertTrue(testname.equals(test_name_on_ch));

			Assert.assertTrue(homePage.view_text_in_sta_table_header.getText().equals(selectedViewName));
			homePage.filter_in_sta_for_district.click();
			Thread.sleep(1000);
			Assert.assertTrue(
					homePage.chkbox_list_under_view_on_sta_filter.get(1).getAttribute("class").contains("Selected"));
			homePage.cancel_button_on_filter_in_sta.click();
			Thread.sleep(1000);

			Assert.assertTrue(homePage.compare_cb_list_on_sta.get(0).getText().equals("District"));
			homePage.compare_cb_list_on_sta.get(0).click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(0).getText().equals("School"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(1).getText().equals("District"));

			homePage.classmenu.click();
			UtilityMethods.wait_For_STA_Section_Load();
			Thread.sleep(1000);
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			log.info("Test Name on CH on class menu:" + test_name_on_ch);
			Assert.assertTrue(testname.equals(test_name_on_ch));
			Assert.assertTrue(homePage.compare_cb_list_on_sta.get(0).getText().equals("School"));
			Assert.assertTrue(homePage.compare_cb_list_on_sta.get(1).getText().equals("District"));
			homePage.compare_cb_list_on_sta.get(0).click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(0).getText().equals("Class"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(1).getText().equals("School"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(2).getText().equals("District"));

			homePage.studentmenu.click();
			UtilityMethods.wait_For_STA_Section_Load();
			Thread.sleep(1000);
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			log.info("Test Name on CH on student menu:" + test_name_on_ch);
			Assert.assertTrue(testname.equals(test_name_on_ch));
			Assert.assertTrue(homePage.compare_cb_list_on_sta.get(0).getText().equals("Class"));
			Assert.assertTrue(homePage.compare_cb_list_on_sta.get(1).getText().equals("School"));
			Assert.assertTrue(homePage.compare_cb_list_on_sta.get(2).getText().equals("District"));
			homePage.compare_cb_list_on_sta.get(0).click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(0).getText().equals("Student"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(1).getText().equals("Class"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(2).getText().equals("School"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(3).getText().equals("District"));

			homePage.classmenu.click();
			UtilityMethods.wait_For_STA_Section_Load();
			Thread.sleep(1000);
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			log.info("Test Name on CH on class menu:" + test_name_on_ch);
			Assert.assertTrue(testname.equals(test_name_on_ch));
			Assert.assertTrue(homePage.compare_cb_list_on_sta.get(0).getText().equals("School"));
			Assert.assertTrue(homePage.compare_cb_list_on_sta.get(1).getText().equals("District"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(0).getText().equals("Class"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(1).getText().equals("School"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(2).getText().equals("District"));

			homePage.schoolmenu.click();
			UtilityMethods.wait_For_STA_Section_Load();
			Thread.sleep(1000);
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			log.info("Test Name on CH on school menu:" + test_name_on_ch);
			Assert.assertTrue(testname.equals(test_name_on_ch));

			Assert.assertTrue(homePage.compare_cb_list_on_sta.get(0).getText().equals("District"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(0).getText().equals("School"));
			Assert.assertTrue(homePage.compared_columns_list_on_table_on_sta.get(1).getText().equals("District"));

			homePage.districtmenu.click();
			UtilityMethods.wait_For_STA_Section_Load();
			Thread.sleep(1000);
			test_name_on_ch = UtilityMethods.getTestsNameonUI();
			log.info("Test Name on CH on district menu:" + test_name_on_ch);
			Assert.assertTrue(testname.equals(test_name_on_ch));

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-2276 completed");
	}

	@When("^User click on Roster tab and select single School and click apply$")
	public void user_click_on_Roster_tab_and_select_single_School_and_click_apply() throws Throwable {
		try {
			homePage.rostertab.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.select_School_In_School_DropDown("Golden Oak Community School");
			Thread.sleep(500);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Verify User is switched to School and Class subway is enabled and Student subway is disabled$")
	public void verify_User_is_switched_to_School_and_Class_subway_is_enabled_and_Student_subway_is_disabled()
			throws Throwable {
		try {

			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			Assert.assertTrue(homePage.classmenu.isDisplayed());

			try {
				homePage.studentmenu.click();
				Assert.assertTrue(homePage.activestudentmenu.isDisplayed());
				UtilityMethods.processException(new Exception());
			} catch (Exception e) {
				log.info("Student Subway filter is disabled");
			}

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}

	}

	@Then("^Navigate back to District and click on Roster tab and select multiple Schools and click apply$")
	public void navigate_back_to_District_and_click_on_Roster_tab_and_select_multiple_Schools_and_click_apply()
			throws Throwable {
		try {
			homePage.districtmenu.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			Thread.sleep(500);
			homePage.rostertab.click();
			Thread.sleep(500);
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("School");
			homePage.searchbaronschooldropdown.sendKeys("Golden Oak Community School");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Golden Oak Community School']").click();
			homePage.searchcancelonschooldropdown.click();
			Thread.sleep(500);
			homePage.searchbaronschooldropdown.sendKeys("Fair Oaks Ranch Community School");
			Driver.webdriver.findElementByXPath("//li[.='Fair Oaks Ranch Community School']").click();
			Thread.sleep(500);
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Verify User is in District context and School subway is enabled and Class and Student subway are disabled$")
	public void verify_User_is_in_District_context_and_School_subway_is_enabled_and_Class_and_Student_subway_are_disabled()
			throws Throwable {
		try {

			Assert.assertTrue(homePage.schoolmenu.isDisplayed());
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			try {
				homePage.classmenu.click();
				Assert.assertTrue(homePage.activeclassmenu.isDisplayed());
				homePage.studentmenu.click();
				Assert.assertTrue(homePage.activestudentmenu.isDisplayed());
				UtilityMethods.processException(new Exception());
			} catch (Exception e) {
				log.info("Class Subway filter is disabled");
				log.info("Student Subway filter is disabled");
			}

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@When("^User click on Roster tab and select a School and one Teacher and one Class and click apply$")
	public void user_click_on_Roster_tab_and_select_a_School_and_one_Teacher_and_one_Class_and_click_apply()
			throws Throwable {
		try {
			homePage.rostertab.click();
			Thread.sleep(500);
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("School");
			RosterTabUtilityMethods.uncheck_check_All("School");
			homePage.searchbaronschooldropdown.sendKeys("Golden Oak Community School");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Golden Oak Community School']").click();
			Thread.sleep(500);
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.wait_For_Refresh_Icon(homePage.schoolRefreshIcon, "School");
			Assert.assertTrue(homePage.teachersdropdownbtn.isDisplayed());
			RosterTabUtilityMethods.select_Teacher_In_Teacher_DropDown("John Stephenson");
			Assert.assertTrue(homePage.classdropdownbtn.isDisplayed());
			RosterTabUtilityMethods.select_Class_In_Class_DropDown("Grade 6 - Jones - 0");
			UtilityMethods.scrollPageDown(Driver.webdriver, 4);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			UtilityMethods.scrollPageUp(Driver.webdriver, 4);

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Navigate back to District and click on Roster tab and select a School and multiple Teachers and Classes and click apply$")
	public void navigate_back_to_District_and_click_on_Roster_tab_and_select_a_School_and_multiple_Teachers_and_Classes_and_click_apply()
			throws Throwable {
		try {
			homePage.districtmenu.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			homePage.rostertab.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.select_School_In_School_DropDown("Golden Oak Community School");
			homePage.teachersdropdownbtn.click();
			RosterTabUtilityMethods.uncheck_check_All("Teacher");
			homePage.searchbaronteacherdropdown.sendKeys("Michael Segura");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Michael Segura']").click();
			Thread.sleep(500);
			homePage.searchcancelonteacherdropdown.click();
			Thread.sleep(500);
			homePage.searchbaronteacherdropdown.sendKeys("John Stephenson");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='John Stephenson']").click();
			Thread.sleep(500);
			UtilityMethods.scrollPageDown(Driver.webdriver, 4);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			UtilityMethods.scrollPageUp(Driver.webdriver, 4);

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Navigate back to District and click on Roster tab and select a School and Class and single Student and click apply$")
	public void navigate_back_to_District_and_click_on_Roster_tab_and_select_a_School_and_Class_and_single_Student_and_click_apply()
			throws Throwable {
		try {
			homePage.districtmenu.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			homePage.rostertab.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.select_School_In_School_DropDown("Golden Oak Community School");
			RosterTabUtilityMethods.select_Class_In_Class_DropDown("Grade 6 - Jones - 0");
			RosterTabUtilityMethods.select_Student_In_Student_DropDown("Terri Alexander");
			UtilityMethods.scrollPageDown(Driver.webdriver, 4);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			UtilityMethods.scrollPageUp(Driver.webdriver, 4);

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Verify User is switched to Student and School and Class subway filters are enabled$")
	public void verify_User_is_switched_to_Student_and_School_and_Class_subway_filters_are_enabled() throws Throwable {
		try {
			Assert.assertTrue(homePage.schoolmenu.isDisplayed());
			Assert.assertTrue(homePage.classmenu.isDisplayed());
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Navigate back to District and click on Roster tab and select a School and a Class and multiple Students and click apply$")
	public void navigate_back_to_District_and_click_on_Roster_tab_and_select_a_School_and_a_Class_and_multiple_Students_and_click_apply()
			throws Throwable {
		try {
			homePage.districtmenu.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			homePage.rostertab.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.select_School_In_School_DropDown("Golden Oak Community School");
			RosterTabUtilityMethods.select_Class_In_Class_DropDown("Grade 6 - Jones - 0");
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("Student");
			Thread.sleep(500);
			homePage.searchbaronstudentdropdown.sendKeys("Terri Alexander");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Terri Alexander']").click();
			Thread.sleep(500);
			homePage.searchcancelonstudentdropdown.click();
			Thread.sleep(500);
			homePage.searchbaronstudentdropdown.sendKeys("Melissa Gagner");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Melissa Gagner']").click();
			Thread.sleep(500);
			homePage.searchcancelonstudentdropdown.click();
			Thread.sleep(500);
			homePage.searchbaronstudentdropdown.sendKeys("Ann Galyean");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Ann Galyean']").click();
			Thread.sleep(500);
			UtilityMethods.scrollPageDown(Driver.webdriver, 4);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			UtilityMethods.scrollPageUp(Driver.webdriver, 4);

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Verify User is switched to Class and School and Student subway filters are enabled$")
	public void verify_User_is_switched_to_Class_and_School_and_Student_subway_filters_are_enabled() throws Throwable {
		try {
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());
			Assert.assertTrue(homePage.schoolmenu.isDisplayed());
			Assert.assertTrue(homePage.studentmenu.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-2257,2258,2259,2261,2262,2263");
	}

	@When("^User click on Roster tab and select one Teacher and one Class and click apply$")
	public void user_click_on_Roster_tab_and_select_one_Teacher_and_one_Class_and_click_apply() throws Throwable {
		try {
			homePage.rostertab.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.select_Teacher_In_Teacher_DropDown("Travis Bruce");
			RosterTabUtilityMethods.select_Class_In_Class_DropDown("Grade 6 - Hines - 0");
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Navigate back to School and click on Roster tab and select multiple Teachers and Classes and click apply$")
	public void navigate_back_to_School_and_click_on_Roster_tab_and_select_multiple_Teachers_and_Classes_and_click_apply()
			throws Throwable {
		try {
			homePage.schoolmenu.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			homePage.rostertab.click();
			Thread.sleep(500);
			homePage.teachersdropdownbtn.click();
			RosterTabUtilityMethods.uncheck_check_All("Teacher");
			Thread.sleep(500);
			homePage.searchbaronteacherdropdown.sendKeys("Travis Bruce");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Travis Bruce']").click();
			Thread.sleep(500);
			homePage.searchcancelonteacherdropdown.click();
			Thread.sleep(500);
			homePage.searchbaronteacherdropdown.sendKeys("Randy Grant");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Randy Grant']").click();
			Thread.sleep(500);
			UtilityMethods.scrollPageDown(Driver.webdriver, 4);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			UtilityMethods.scrollPageUp(Driver.webdriver, 4);

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@When("^User click on Roster tab and select a Class and single Student and click apply$")
	public void user_click_on_Roster_tab_and_select_a_Class_and_single_Student_and_click_apply() throws Throwable {
		try {
			homePage.rostertab.click();
			Thread.sleep(500);
			homePage.teachersdropdownbtn.click();
			RosterTabUtilityMethods.uncheck_check_All("Teacher");
			Thread.sleep(500);
			RosterTabUtilityMethods.select_Class_In_Class_DropDown("Grade 6 - Hines - 0");
			RosterTabUtilityMethods.select_Student_In_Student_DropDown("Paul Blair");
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Navigate back to School and click on Roster tab and select a Class and multiple Students and click apply$")
	public void navigate_back_to_School_and_click_on_Roster_tab_and_select_a_Class_and_multiple_Students_and_click_apply()
			throws Throwable {
		try {
			homePage.schoolmenu.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			homePage.rostertab.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.select_Class_In_Class_DropDown("Grade 6 - Hines - 0");
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("Student");
			Thread.sleep(500);
			homePage.searchbaronstudentdropdown.sendKeys("Patricia Balcom");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Patricia Balcom']").click();
			Thread.sleep(500);
			homePage.searchcancelonstudentdropdown.click();
			Thread.sleep(500);
			homePage.searchbaronstudentdropdown.sendKeys("Paul Blair");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Paul Blair']").click();
			Thread.sleep(500);
			homePage.searchcancelonstudentdropdown.click();
			Thread.sleep(500);
			homePage.searchbaronstudentdropdown.sendKeys("Daniel Castillo");
			Thread.sleep(500);
			Driver.webdriver.findElementByXPath("//li[.='Daniel Castillo']").click();
			Thread.sleep(500);
			homePage.studentdropdownbtn.click();
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^Verify the persistence by selecting data in Roster tab filters of STA$")
	public void verify_the_persistence_by_selecting_data_in_Roster_tab_filters_of_STA() throws Throwable {
		try {
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			jse.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			homePage.rostertab.click();
			Thread.sleep(1000);
			String scName,grade,teacherName,className,student_name;
			String scName_on_ch,grade_name_on_ch,teacher_name_on_ch,class_name_on_ch,student_name_on_ch;			
			
			scName=RosterTabUtilityMethods.select_School_In_School_DropDown("Golden Oak Community School");
			grade=RosterTabUtilityMethods.select_Grade_In_Grades_DropDown("Grade 4");
			teacherName=RosterTabUtilityMethods.select_Teacher_In_Teacher_DropDown("Anastasia Clarke");
			className=RosterTabUtilityMethods.select_Class_In_Class_DropDown("Grade 4 - McCarthy - 0");
			UtilityMethods.scrollPageDown(Driver.webdriver, 7);Thread.sleep(500);
			student_name=RosterTabUtilityMethods.select_Student_In_Student_DropDown("Wendy Allen");
			homePage.rosterapplybtn.click();
			UtilityMethods.scrollPageUp(Driver.webdriver);
			UtilityMethods.wait_For_STA_Section_Load();
			grade=grade.substring(grade.indexOf(" ")+1);
			scName_on_ch=UtilityMethods.getSchoolNameonUI();
			teacher_name_on_ch=UtilityMethods.getTeacherNameonUI();			
			grade_name_on_ch=UtilityMethods.getGradeNameonUI();
			class_name_on_ch=UtilityMethods.getClassNameonUI();
			student_name_on_ch=UtilityMethods.getStudentNameonUI();
			
			Assert.assertTrue(student_name.equals(student_name_on_ch));
			Assert.assertTrue(scName_on_ch.equals(scName));
			Assert.assertTrue(grade.equals(grade_name_on_ch));
			Assert.assertTrue(teacherName.equals(teacher_name_on_ch));
			Assert.assertTrue(className.equals(class_name_on_ch));			
			log.info("Roster Tab value matched with context header data on student context");
			homePage.classmenu.click();Thread.sleep(500);
			UtilityMethods.wait_For_STA_Section_Load();
			scName_on_ch=UtilityMethods.getSchoolNameonUI();
			teacher_name_on_ch=UtilityMethods.getTeacherNameonUI();			
			grade_name_on_ch=UtilityMethods.getGradeNameonUI();
			class_name_on_ch=UtilityMethods.getClassNameonUI();
			Assert.assertTrue(scName_on_ch.equals(scName));
			Assert.assertTrue(grade.equals(grade_name_on_ch));
			Assert.assertTrue(teacherName.equals(teacher_name_on_ch));
			Assert.assertTrue(className.equals(class_name_on_ch));
			log.info("Roster Tab value matched with context header data on class context");
			homePage.schoolmenu.click();Thread.sleep(500);
			UtilityMethods.wait_For_STA_Section_Load();
			scName_on_ch=UtilityMethods.getSchoolNameonUI();
			grade_name_on_ch=UtilityMethods.getGradeNameonUI();			
			Assert.assertTrue(scName_on_ch.equals(scName));
			Assert.assertTrue(grade.equals(grade_name_on_ch));
			log.info("Roster Tab value matched with context header data on school context");
			homePage.districtmenu.click();Thread.sleep(500);
			UtilityMethods.wait_For_STA_Section_Load();
			grade_name_on_ch=UtilityMethods.getGradeNameonUI();			
			Assert.assertTrue(grade.equals(grade_name_on_ch));
			log.info("Roster Tab value matched with context header data on school context");
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		
	}

}
