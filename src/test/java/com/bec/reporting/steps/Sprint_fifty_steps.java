package com.bec.reporting.steps;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.API_Connection;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.IWait;
import com.bec.reporting.utils.PaginationUtility_for_Universal_Tab;
import com.bec.reporting.utils.RosterTabUtilityMethods;
import com.bec.reporting.utils.UtilityMethods;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sprint_fifty_steps {
	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;
	String tooltiptxt = "Test Status reports are not available for past district terms.";
	String past_dist_banner_txt = "You are viewing past district term reports for your current roster of students. For more information click here.";
	String txt_on_class_cmpr_under_sp = "Class comparison displays the average score for your current class roster in a past district term.";
	String txt_on_class_cmpr = "Displays the average score for your current class roster in a past district term.";

	@Then("^verify Test Status tab Inactive on viewing past district term reports$")
	public void verify_Test_Status_tab_Inactive_on_viewing_past_district_term_reports() throws Throwable {
		try {
			String currentHandle = Driver.webdriver.getWindowHandle();
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.test_status_btn.getAttribute("class").equals(""));
			Standard_Overview_Table_Steps.paginationontesttab();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			try {
				Assert.assertTrue(homePage.test_status_btn.getAttribute("class")
						.equals("past_district_term_test_status_inactive"));
			} catch (Exception e) {
				UtilityMethods.processException(new Exception());
			}

			new Actions(Driver.webdriver).moveToElement(homePage.test_status_btn).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.test_status_btn_tool_tip.getText().equals(tooltiptxt));
			verify_past_district_term_banner_And_check_new_tab(past_dist_banner_txt, currentHandle);

			UtilityMethods.comparison_under_standard_performance();
			verify_past_district_term_banner_And_check_new_tab(past_dist_banner_txt, currentHandle);

			UtilityMethods.groupin_tab_under_standard_performance();
			verify_past_district_term_banner_And_check_new_tab(past_dist_banner_txt, currentHandle);

			UtilityMethods.summary_tab_under_standard_performance();
			verify_past_district_term_banner_And_check_new_tab(past_dist_banner_txt, currentHandle);

			UtilityMethods.overview_tab_under_Test_Score_Tab();
			verify_past_district_term_banner_And_check_new_tab(past_dist_banner_txt, currentHandle);

			UtilityMethods.comparison_Tab_under_Test_Score_Tab();
			verify_past_district_term_banner_And_check_new_tab(past_dist_banner_txt, currentHandle);

			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_past_district_term_banner_And_check_new_tab(past_dist_banner_txt, currentHandle);

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-11988,BU-12062 completed");
	}

	public void verify_past_district_term_banner_And_check_new_tab(String banner_txt, String currentHandle) {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.past_district_term_banner).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.past_district_term_banner.getText().equals(banner_txt));

			new Actions(Driver.webdriver).moveToElement(homePage.past_district_term_banner_click_here).click().build()
					.perform();
			Thread.sleep(500);
			Test_Status_Steps.verify_new_window_url(currentHandle, "help");
			Test_Status_Steps.close_all_new_window_url();

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^verify admin notification banner only in Past District Term reports$")
	public void verify_admin_notification_banner_only_in_Past_District_Term_reports() throws Throwable {
		try {
			String currentHandle = Driver.webdriver.getWindowHandle();
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.test_status_btn.getAttribute("class").equals(""));
			Standard_Overview_Table_Steps.paginationontesttab();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			try {
				Assert.assertTrue(homePage.test_status_btn.getAttribute("class")
						.equals("past_district_term_test_status_inactive"));
			} catch (Exception e) {
				UtilityMethods.processException(new Exception());
			}
			new Actions(Driver.webdriver).moveToElement(homePage.test_status_btn).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.test_status_btn_tool_tip.getText().equals(tooltiptxt));
			verify_past_district_term_banner_And_check_new_tab(past_dist_banner_txt, currentHandle);

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12063,BU-13129 completed");
	}

	@Then("^verify Teacher viewing past term data for the currently rostered classes and or students of current term$")
	public void verify_Teacher_viewing_past_term_data_for_the_currently_rostered_classes_and_or_students_of_current_term()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.test_status_btn.getAttribute("class").equals(""));
			Standard_Overview_Table_Steps.paginationontesttab();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			try {
				Assert.assertTrue(homePage.test_status_btn.getAttribute("class")
						.equals("past_district_term_test_status_inactive"));
			} catch (Exception e) {
				UtilityMethods.processException(new Exception());
			}

			new Actions(Driver.webdriver).moveToElement(homePage.test_status_btn).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.test_status_btn_tool_tip.getText().equals(tooltiptxt));
			homePage.datetab.click();
			Thread.sleep(1000);
			homePage.districttermdropdownbtn.click();
			Thread.sleep(1000);
			Driver.webdriver.findElement(By.xpath(
					"//div[@class='menu-title' and contains(text(),'District Term')]/following-sibling::div//ul/li[contains(text(),'2017-2018')]"))
					.click();
			Thread.sleep(500);
			homePage.dateapplybtn.click();
			Thread.sleep(3000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.overviewtext.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12066 completed");
	}

	@Then("^verify  Default past term data Teacher viewing past term data for the currently rostered classes and or students of current term$")
	public void verify_Default_past_term_data_Teacher_viewing_past_term_data_for_the_currently_rostered_classes_and_or_students_of_current_term()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(
					homePage.test_status_btn.getAttribute("class").equals("past_district_term_test_status_inactive"));
			new Actions(Driver.webdriver).moveToElement(homePage.test_status_btn).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.test_status_btn_tool_tip.getText().equals(tooltiptxt));

			homePage.datetab.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.districttermdropdownbtn.getText().equals("2018-2019"));

			homePage.districttermdropdownbtn.click();
			Thread.sleep(1000);
			WebElement el = Driver.webdriver.findElement(By.xpath("//li[.='2018-2019']"));
			Assert.assertTrue(el.getAttribute("class").contains("selected"));

			Driver.webdriver.findElement(By.xpath("//li[.='2019-2020']")).click();
			Thread.sleep(500);
			homePage.dateapplybtn.click();
			Thread.sleep(3000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.overviewtext.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12067 completed");
	}

	@Then("^verify context header On viewing the past term reports$")
	public void verify_context_header_On_viewing_the_past_term_reports() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.test_status_btn.getAttribute("class").equals(""));
			Standard_Overview_Table_Steps.paginationontesttab();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			try {
				Assert.assertTrue(homePage.test_status_btn.getAttribute("class")
						.equals("past_district_term_test_status_inactive"));
			} catch (Exception e) {
				UtilityMethods.processException(new Exception());
			}

			new Actions(Driver.webdriver).moveToElement(homePage.test_status_btn).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.test_status_btn_tool_tip.getText().equals(tooltiptxt));

			Assert.assertTrue(homePage.info_icon_on_ch_for_tests.isDisplayed());
			Assert.assertTrue(homePage.info_icon_on_ch_for_dates.isDisplayed());

			homePage.info_icon_on_ch_for_tests.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.tooltip_of_info_icon_on_ch_for_tests.getText()
					.equals("Tests displayed are from past district term."));
			homePage.info_icon_on_ch_for_tests.click();
			Thread.sleep(500);
			try {
				Assert.assertTrue(homePage.tooltip_of_info_icon_on_ch_for_tests.getText()
						.equals("Tests displayed are from past district term."));
				UtilityMethods.processException(new Exception());
			} catch (Exception e) {
			}

			homePage.info_icon_on_ch_for_dates.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.tooltip_of_info_icon_on_ch_for_dates.getText()
					.equals("Dates displayed are from past district term."));
			homePage.info_icon_on_ch_for_dates.click();
			Thread.sleep(500);
			try {
				Assert.assertTrue(homePage.tooltip_of_info_icon_on_ch_for_dates.getText()
						.equals("Dates displayed are from past district term."));
				UtilityMethods.processException(new Exception());
			} catch (Exception e) {
			}

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12071 completed");
	}

	@Then("^verify class comparison with info icon on viewing the past term reports$")
	public void verify_class_comparison_with_info_icon_on_viewing_the_past_term_reports() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.test_status_btn.getAttribute("class").equals(""));
			Standard_Overview_Table_Steps.paginationontesttab();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			try {
				Assert.assertTrue(homePage.test_status_btn.getAttribute("class")
						.equals("past_district_term_test_status_inactive"));
			} catch (Exception e) {
				UtilityMethods.processException(new Exception());
			}
			// ======================================================
			UtilityMethods.comparison_under_standard_performance();
			verify_info_icon_and_tooltip_on_comparison(txt_on_class_cmpr_under_sp);
			// ======================================================
			UtilityMethods.summary_tab_under_standard_performance();
			verify_info_icon_and_tooltip_on_comparison(txt_on_class_cmpr);
			// ======================================================
			UtilityMethods.comparison_Tab_under_Test_Score_Tab();
			verify_info_icon_and_tooltip_on_comparison(txt_on_class_cmpr);

			// ==============Student-Test Score/Comparison
			// Tab========================================
			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_test_score();
			verify_info_icon_and_tooltip_on_comparison(txt_on_class_cmpr);
			// ==============Student-Test Score/Overview
			// Tab========================================
			js.executeScript("arguments[0].click();", homePage.overviewtext);
			UtilityMethods.wait_For_Test_Score_Overview_Section_Load();
			verify_info_icon_and_tooltip_on_comparison(txt_on_class_cmpr);

			// ==============Student-Single Test score Analysis
			// Tab========================================
			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_info_icon_and_tooltip_on_comparison(txt_on_class_cmpr);

			// ==============Student-Standard Performance
			// Tab========================================
			js.executeScript("arguments[0].click();", homePage.standardperformancebtn);
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			verify_info_icon_and_tooltip_on_comparison(txt_on_class_cmpr_under_sp);

			// ==============Student-Standard Performance
			// Tab/Comparison========================================

			UtilityMethods.comparison_under_standard_performance();
			verify_info_icon_and_tooltip_on_comparison(txt_on_class_cmpr_under_sp);

			// ==============Student-Standard Performance
			// Tab/summary========================================

			UtilityMethods.summary_tab_under_standard_performance();
			verify_info_icon_and_tooltip_on_comparison(txt_on_class_cmpr);

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12072 completed");
	}

	public void verify_info_icon_and_tooltip_on_comparison(String txt_to_be_verify) {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.contextheader_title_list.get(0)).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.info_icon_on_class_on_compare_chkbox.isDisplayed());
			homePage.info_icon_on_class_on_compare_chkbox.click();
			Thread.sleep(500);
			Assert.assertTrue(
					homePage.tooltip_of_info_icon_on_class_on_compare_chkbox.getText().equals(txt_to_be_verify));
			homePage.info_icon_on_class_on_compare_chkbox.click();
			Thread.sleep(500);
			try {
				Assert.assertTrue(
						homePage.tooltip_of_info_icon_on_class_on_compare_chkbox.getText().equals(txt_to_be_verify));
				UtilityMethods.processException(new Exception());
			} catch (Exception e) {
			}
		} catch (Exception e) {
			UtilityMethods.processException(new Exception());
		}
	}

	@Then("^verify Test tab and Date tab On viewing the past term reports$")
	public void verify_Test_tab_and_Date_tab_On_viewing_the_past_term_reports() throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.test_status_btn.getAttribute("class").equals(""));
			Standard_Overview_Table_Steps.paginationontesttab();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			try {
				Assert.assertTrue(homePage.test_status_btn.getAttribute("class")
						.equals("past_district_term_test_status_inactive"));
			} catch (Exception e) {
				UtilityMethods.processException(new Exception());
			}

			String datesonCH = homePage.datesoncontextheader.getText().trim();
			datesonCH = datesonCH.substring(6, 10) + "-" + datesonCH.substring(17);
			log.info("datesonCH :" + datesonCH);

			String syear = datesonCH.substring(0, datesonCH.indexOf("-"));
			String eyear = datesonCH.substring(datesonCH.indexOf("-") + 1);
			homePage.datetab.click();
			Thread.sleep(1000);
			String districtterm = homePage.districttermdropdownbtn.getText();
			districtterm = districtterm.substring(0, districtterm.indexOf(" "));

			/**
			 * Verifying District Term on context header by comparing dropdown text and
			 * context header values
			 */

			Assert.assertEquals(districtterm, datesonCH);
			homePage.datecancelbtn.click();
			Thread.sleep(500);

			homePage.testtab.click();
			Thread.sleep(1000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 6);
			Thread.sleep(500);

			// Create a Date list.
			List<String> earliest_date = new LinkedList<String>();
			List<String> latest_date = new LinkedList<String>();
			String s_year, e_year;
			// checking for paginator
			if (PaginationUtility_for_Universal_Tab.checkPaginator_on_test_tab()) {
				// this lool will execute for the no. of circle available on paginator
				for (int i = 0; i < homePage.testpaginationcirclelist.size(); i++) {
					PaginationUtility_for_Universal_Tab.click_On_Indexed_Circle_Of_Paginator(i);
					for (int j = 0; j < homePage.earliestdatelist.size(); j++) {
						if (homePage.earliestdatelist.get(j).getText().equals("")) {
							UtilityMethods.scroll_Div(homePage.earliestdatelist.get(j), 20);
						}
						s_year = homePage.earliestdatelist.get(j).getText();
						e_year = homePage.latestdatelist.get(j).getText();
						earliest_date.add(s_year.substring(s_year.lastIndexOf("/") + 1));
						latest_date.add(e_year.substring(e_year.lastIndexOf("/") + 1));
					}
				}
				// check for right arrow enabled and click on it and click on last circle from
				// left and validate
				do {
					if (PaginationUtility_for_Universal_Tab.check_Enabled_Right_Arrow_On_Paginator_On_Test_Tab()) {
						PaginationUtility_for_Universal_Tab.click_On_Enabled_Right_Arrow_Of_Paginator_On_Test_Tab();
						PaginationUtility_for_Universal_Tab.click_On_Last_Circle_Of_Paginator();
						for (int j = 0; j < homePage.earliestdatelist.size(); j++) {
							if (homePage.earliestdatelist.get(j).getText().equals("")) {
								UtilityMethods.scroll_Div(homePage.earliestdatelist.get(j), 20);
							}
							s_year = homePage.earliestdatelist.get(j).getText();
							e_year = homePage.latestdatelist.get(j).getText();
							earliest_date.add(s_year.substring(s_year.lastIndexOf("/") + 1));
							latest_date.add(e_year.substring(e_year.lastIndexOf("/") + 1));
						}
					}
				} while (PaginationUtility_for_Universal_Tab.check_Enabled_Right_Arrow_On_Paginator_On_Test_Tab());
			} else {
				// when paginator is not found
				for (int j = 0; j < homePage.earliestdatelist.size(); j++) {
					if (homePage.earliestdatelist.get(j).getText().equals("")) {
						UtilityMethods.scroll_Div(homePage.earliestdatelist.get(j), 20);
					}
					s_year = homePage.earliestdatelist.get(j).getText();
					e_year = homePage.latestdatelist.get(j).getText();
					earliest_date.add(s_year.substring(s_year.lastIndexOf("/") + 1));
					latest_date.add(e_year.substring(e_year.lastIndexOf("/") + 1));
				}
			}

			Assert.assertTrue(verify_years_in_date_range(earliest_date, syear, eyear));
			Assert.assertTrue(verify_years_in_date_range(latest_date, syear, eyear));
			earliest_date.clear();
			latest_date.clear();

			homePage.testcancelbtn.click();
			Thread.sleep(1000);
			UtilityMethods.select_specific_district_term("2019-2020");
			IWait.explicit_wait(Driver.webdriver, homePage.studentmenu);
			datesonCH = homePage.datesoncontextheader.getText().trim();
			datesonCH = datesonCH.substring(6, 10) + "-" + datesonCH.substring(17);
			log.info("datesonCH :" + datesonCH);

			syear = datesonCH.substring(0, datesonCH.indexOf("-"));
			eyear = datesonCH.substring(datesonCH.indexOf("-") + 1);

			homePage.testtab.click();
			Thread.sleep(1000);

			earliest_date = new LinkedList<String>();
			latest_date = new LinkedList<String>();

			// checking for paginator
			if (PaginationUtility_for_Universal_Tab.checkPaginator_on_test_tab()) {
				// this lool will execute for the no. of circle available on paginator
				for (int i = 0; i < homePage.testpaginationcirclelist.size(); i++) {
					PaginationUtility_for_Universal_Tab.click_On_Indexed_Circle_Of_Paginator(i);
					for (int j = 0; j < homePage.earliestdatelist.size(); j++) {
						if (homePage.earliestdatelist.get(j).getText().equals("")) {
							UtilityMethods.scroll_Div(homePage.earliestdatelist.get(j), 20);
						}
						s_year = homePage.earliestdatelist.get(j).getText();
						e_year = homePage.latestdatelist.get(j).getText();
						earliest_date.add(s_year.substring(s_year.lastIndexOf("/") + 1));
						latest_date.add(e_year.substring(e_year.lastIndexOf("/") + 1));
					}
				}
				// check for right arrow enabled and click on it and click on last circle from
				// left and validate
				do {
					if (PaginationUtility_for_Universal_Tab.check_Enabled_Right_Arrow_On_Paginator_On_Test_Tab()) {
						PaginationUtility_for_Universal_Tab.click_On_Enabled_Right_Arrow_Of_Paginator_On_Test_Tab();
						PaginationUtility_for_Universal_Tab.click_On_Last_Circle_Of_Paginator();
						for (int j = 0; j < homePage.earliestdatelist.size(); j++) {
							if (homePage.earliestdatelist.get(j).getText().equals("")) {
								UtilityMethods.scroll_Div(homePage.earliestdatelist.get(j), 20);
							}
							s_year = homePage.earliestdatelist.get(j).getText();
							e_year = homePage.latestdatelist.get(j).getText();
							earliest_date.add(s_year.substring(s_year.lastIndexOf("/") + 1));
							latest_date.add(e_year.substring(e_year.lastIndexOf("/") + 1));
						}
					}
				} while (PaginationUtility_for_Universal_Tab.check_Enabled_Right_Arrow_On_Paginator_On_Test_Tab());
			} else {
				// when paginator is not found
				for (int j = 0; j < homePage.earliestdatelist.size(); j++) {
					if (homePage.earliestdatelist.get(j).getText().equals("")) {
						UtilityMethods.scroll_Div(homePage.earliestdatelist.get(j), 20);
					}
					s_year = homePage.earliestdatelist.get(j).getText();
					e_year = homePage.latestdatelist.get(j).getText();
					earliest_date.add(s_year.substring(s_year.lastIndexOf("/") + 1));
					latest_date.add(e_year.substring(e_year.lastIndexOf("/") + 1));
				}
			}

			Assert.assertTrue(verify_years_in_date_range(earliest_date, syear, eyear));
			Assert.assertTrue(verify_years_in_date_range(latest_date, syear, eyear));

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12070 completed");
	}

	@Then("^verify the school admin current term and past term seeing the past district term reports with no data for your selection modal$")
	public void verify_the_school_admin_current_term_and_past_term_seeing_the_past_district_term_reports_with_no_data_for_your_selection_modal()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			Assert.assertTrue(homePage.test_status_btn.getAttribute("class").equals(""));
			Standard_Overview_Table_Steps.paginationontesttab();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			try {
				Assert.assertTrue(homePage.test_status_btn.getAttribute("class")
						.equals("past_district_term_test_status_inactive"));
			} catch (Exception e) {
				UtilityMethods.processException(new Exception());
			}

			new Actions(Driver.webdriver).moveToElement(homePage.test_status_btn).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.test_status_btn_tool_tip.getText().equals(tooltiptxt));
			homePage.rostertab.click();
			Thread.sleep(1000);
			String sc_name = "Golden Oak Community School";
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			Driver.webdriver.findElement(By.xpath("//li[.='" + sc_name + "']")).click();
			Thread.sleep(3000);
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			new Actions(Driver.webdriver).moveToElement(homePage.contextheader_text_list.get(2)).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(sc_name.equals(homePage.tooltipofschoolnameoncontextheader.getText()));
			UtilityMethods.select_specific_district_term("2017-2018");
			Thread.sleep(5000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-13126 completed");
	}

	@Then("^verify school admin only past term seeing the past district term reports with no data for your selection modal$")
	public void verify_school_admin_only_past_term_seeing_the_past_district_term_reports_with_no_data_for_your_selection_modal()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());
			try {
				Assert.assertTrue(homePage.test_status_btn.getAttribute("class")
						.equals("past_district_term_test_status_inactive"));
			} catch (Exception e) {
				UtilityMethods.processException(new Exception());
			}

			new Actions(Driver.webdriver).moveToElement(homePage.test_status_btn).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.test_status_btn_tool_tip.getText().equals(tooltiptxt));
			UtilityMethods.select_specific_district_term("2019-2020");
			Thread.sleep(8000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-13127 completed");
	}

	public boolean verify_years_in_date_range(List<String> date_list, String start_year, String end_year) {
		int ctr = 0;
		for (int i = 0; i < date_list.size(); i++) {
			if (!(date_list.get(i).equals(start_year) || date_list.get(i).equals(end_year))) {
				ctr++;
			}
		}
		if (ctr == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Then("^verify the pdt updates da selecting one school from roster and loading current term details on being in past term$")
	public void verify_the_pdt_updates_da_selecting_one_school_from_roster_and_loading_current_term_details_on_being_in_past_term()
			throws Throwable {
		try {
			String sc_name = "Golden Oak Community School";
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.test_status_btn.getAttribute("class").equals(""));
			List<String> ct_schoolList = new ArrayList<String>();
			List<String> ct_gradeList = new ArrayList<String>();
			List<String> ct_classList = new ArrayList<String>();
			List<String> ct_teacherList = new ArrayList<String>();
			homePage.rostertab.click();
			Thread.sleep(1000);

			// collecting schools for current term
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("School");
			int ct_schoolcount = homePage.schoollist.size();
			String scName;
			for (int i = 0; i < ct_schoolcount; i++) {
				scName = homePage.schoollist.get(i).getText();
				if (scName.equals("")) {
					UtilityMethods.scroll_Div(homePage.schoollist.get(i), 10);
				}
				ct_schoolList.add(homePage.schoollist.get(i).getText());
			}

			homePage.searchbaronschooldropdown.sendKeys(sc_name);
			Thread.sleep(500);
			Driver.webdriver.findElement(By.xpath("//li[.='" + sc_name + "']")).click();
			Thread.sleep(500);
			new Actions(Driver.webdriver).moveToElement(homePage.searchcancelonschooldropdown).click().build()
					.perform();
			Thread.sleep(500);

			homePage.schooldropdownbtn.click();
			Thread.sleep(5000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 5);
			Thread.sleep(500);
			// collecting grade for current term
			homePage.gradedropdownbtn.click();
			Thread.sleep(500);
			int ct_gradecount = homePage.gradelist.size();

			for (int i = 0; i < ct_gradecount; i++) {
				ct_gradeList.add(homePage.gradelist.get(i).getText());
			}
			homePage.gradedropdownbtn.click();
			Thread.sleep(500);
			// collecting teacher for current term
			homePage.teachersdropdownbtn.click();
			Thread.sleep(500);
			int ct_teachercount = homePage.teacherslist.size();

			for (int i = 0; i < ct_teachercount; i++) {
				ct_teacherList.add(homePage.teacherslist.get(i).getText());
			}
			homePage.teachersdropdownbtn.click();
			Thread.sleep(500);
			// collecting class for current term
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			int ct_classcount = homePage.classlist.size();

			for (int i = 0; i < ct_classcount; i++) {
				ct_classList.add(homePage.classlist.get(i).getText());
			}
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			homePage.rostercancelbtn.click();
			Thread.sleep(500);

			Standard_Overview_Table_Steps.paginationontesttab();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			try {
				Assert.assertTrue(homePage.test_status_btn.getAttribute("class")
						.equals("past_district_term_test_status_inactive"));
			} catch (Exception e) {
				UtilityMethods.processException(new Exception());
			}

			new Actions(Driver.webdriver).moveToElement(homePage.test_status_btn).click().build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.test_status_btn_tool_tip.getText().equals(tooltiptxt));

			List<String> pdt_schoolList = new ArrayList<String>();
			List<String> pdt_gradeList = new ArrayList<String>();
			List<String> pdt_classList = new ArrayList<String>();
			List<String> pdt_teacherList = new ArrayList<String>();
			homePage.rostertab.click();
			Thread.sleep(1000);

			// collecting schools for current term
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			RosterTabUtilityMethods.uncheck_check_All("School");
			int pdt_schoolcount = homePage.schoollist.size();
			for (int i = 0; i < pdt_schoolcount; i++) {
				scName = homePage.schoollist.get(i).getText();
				if (scName.equals("")) {
					UtilityMethods.scroll_Div(homePage.schoollist.get(i), 10);
				}
				pdt_schoolList.add(homePage.schoollist.get(i).getText());
			}
			Assert.assertTrue(ct_schoolcount == pdt_schoolcount);
			homePage.searchbaronschooldropdown.sendKeys(sc_name);
			Thread.sleep(500);
			Driver.webdriver.findElement(By.xpath("//li[.='" + sc_name + "']")).click();
			Thread.sleep(500);
			new Actions(Driver.webdriver).moveToElement(homePage.searchcancelonschooldropdown).click().build()
					.perform();
			Thread.sleep(500);

			homePage.schooldropdownbtn.click();
			Thread.sleep(5000);
			for (int i = 0; i < ct_schoolList.size(); i++) {
				Assert.assertTrue(ct_schoolList.get(i).equals(pdt_schoolList.get(i)));
			}

			UtilityMethods.scrollPageDown(Driver.webdriver, 5);
			Thread.sleep(500);
			// collecting grade for current term
			homePage.gradedropdownbtn.click();
			Thread.sleep(500);
			int pdt_gradecount = homePage.gradelist.size();

			for (int i = 0; i < pdt_gradecount; i++) {
				pdt_gradeList.add(homePage.gradelist.get(i).getText());
			}
			Assert.assertTrue(ct_gradecount == pdt_gradecount);
			homePage.gradedropdownbtn.click();
			Thread.sleep(500);
			for (int i = 0; i < ct_gradeList.size(); i++) {
				Assert.assertTrue(ct_gradeList.get(i).equals(pdt_gradeList.get(i)));
			}

			// collecting teacher for current term
			homePage.teachersdropdownbtn.click();
			Thread.sleep(500);
			int pdt_teachercount = homePage.teacherslist.size();

			for (int i = 0; i < pdt_teachercount; i++) {
				pdt_teacherList.add(homePage.teacherslist.get(i).getText());
			}
			Assert.assertTrue(ct_teachercount == pdt_teachercount);
			homePage.teachersdropdownbtn.click();
			Thread.sleep(500);
			for (int i = 0; i < ct_teacherList.size(); i++) {
				Assert.assertTrue(ct_teacherList.get(i).equals(pdt_teacherList.get(i)));
			}

			// collecting class for current term
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			int pdt_classcount = homePage.classlist.size();

			for (int i = 0; i < pdt_classcount; i++) {
				pdt_classList.add(homePage.classlist.get(i).getText());
			}
			Assert.assertTrue(ct_classcount == pdt_classcount);
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			for (int i = 0; i < ct_classList.size(); i++) {
				Assert.assertTrue(ct_classList.get(i).equals(pdt_classList.get(i)));
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-13211 completed");
	}

	@Then("^verify future date on test tab and context header and performance overtime graph being in standard performance$")
	public void verify_future_date_on_test_tab_and_context_header_and_performance_overtime_graph_being_in_standard_performance()
			throws Throwable {
		try {
			String roster_grade = "Grade 1";
			String test_name = "Benchmark Advance G1 U1 W2 Assessment";
			homePage.rostertab.click();
			Thread.sleep(1000);
			homePage.gradedropdownbtn.click();
			Thread.sleep(1000);
			Driver.webdriver.findElement(By.xpath("//li[.='" + roster_grade + "']")).click();
			Thread.sleep(1000);

			homePage.rosterapplybtn.click();

			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

			homePage.testtab.click();
			Thread.sleep(1000);
			homePage.allcheckbox_in_test_tab.click();
			Thread.sleep(1000);

			homePage.searchbarontesttab.sendKeys(test_name);

			Thread.sleep(500);
			homePage.testscheckboxlist.get(0).click();
			Thread.sleep(500);
			homePage.testapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();

			String future_date = API_Connection.getFutureDate();
			String sys_date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

			Date f_date = new SimpleDateFormat("MM/dd/yyyy").parse(future_date);

			Date s_date = new SimpleDateFormat("MM/dd/yyyy").parse(sys_date);

			if (s_date.before(f_date)) {
				future_date = sys_date;
			}

			verify_latest_date_test_tab_and_submit_date_context_header(future_date);
			homePage.schoolmenu.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_latest_date_test_tab_and_submit_date_context_header(future_date);
			homePage.classmenu.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_latest_date_test_tab_and_submit_date_context_header(future_date);
			homePage.studentmenu.click();
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			verify_latest_date_test_tab_and_submit_date_context_header(future_date);

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-13347_1 completed");
	}

	public void verify_latest_date_test_tab_and_submit_date_context_header(String future_date) {
		try {
			homePage.testtab.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.latestdatelist.get(0).getText().equals(future_date));
			homePage.testtab.click();
			Thread.sleep(500);

			Assert.assertTrue(UtilityMethods.getDatesonContextHeaderUI().equals(future_date));
			homePage.activePerformanceOverTimePage.click();
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			homePage.testScoreValueInCircle_onlinechart_pot.get(0).click();
			Thread.sleep(2000);
			Assert.assertTrue(
					homePage.submitted_date_on_test_score_value_tooltip.getText().equals("Submitted : " + future_date));
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^verify future date on test tab and context header and test score over time and detail in test score$")
	public void verify_future_date_on_test_tab_and_context_header_and_test_score_over_time_and_detail_in_test_score()
			throws Throwable {
		try {
			String roster_grade = "Grade 1";
			String test_name = "Benchmark Advance G1 U1 W2 Assessment";
			homePage.rostertab.click();
			Thread.sleep(1000);
			homePage.gradedropdownbtn.click();
			Thread.sleep(1000);
			Driver.webdriver.findElement(By.xpath("//li[.='" + roster_grade + "']")).click();
			Thread.sleep(1000);		
			homePage.rosterapplybtn.click();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			homePage.testtab.click();
			Thread.sleep(1000);
			homePage.allcheckbox_in_test_tab.click();
			Thread.sleep(1000);
			homePage.searchbarontesttab.sendKeys(test_name);
			Thread.sleep(500);
			homePage.testscheckboxlist.get(0).click();
			Thread.sleep(500);
			homePage.testapplybtn.click();			
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			String future_date = API_Connection.getFutureDate();
			String sys_date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

			Date f_date = new SimpleDateFormat("MM/dd/yyyy").parse(future_date);

			Date s_date = new SimpleDateFormat("MM/dd/yyyy").parse(sys_date);

			if (s_date.before(f_date)) {
				future_date = sys_date;
			}

			homePage.test_scores_btn.click();
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			verify_latest_data_test_tab_and_submit_data_context_header_in_test_score(future_date);
			homePage.schoolmenu.click();
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			verify_latest_data_test_tab_and_submit_data_context_header_in_test_score(future_date);
			homePage.classmenu.click();
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			verify_latest_data_test_tab_and_submit_data_context_header_in_test_score(future_date);
			homePage.studentmenu.click();
			homePage.testScoreValueInCircle_onlinechart_pot.get(0).click();
			Thread.sleep(2000);
			Assert.assertTrue(
					homePage.submitted_date_on_test_score_value_tooltip.getText().equals("Submitted : " + future_date));

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-13347_2 completed");
	}

	public void verify_latest_data_test_tab_and_submit_data_context_header_in_test_score(String future_date)
			throws Exception {
		try {
			homePage.testtab.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.latestdatelist.get(0).getText().equals(future_date));
			homePage.testtab.click();
			Thread.sleep(500);
			Assert.assertTrue(UtilityMethods.getDatesonContextHeaderUI().equals(future_date));
			Assert.assertTrue(homePage.submitteddateon_tsd.getText().equals("Submitted: " + future_date));
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}
}
