package com.bec.reporting.steps;

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
import com.bec.reporting.utils.PaginationUtility_for_Universal_Tab;
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
	String admin_past_dist_banner_txt = "You are viewing reports in a past district term.";

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

			if (API_Connection.getUserRole().equals("Teacher")) {
				new Actions(Driver.webdriver).moveToElement(homePage.past_district_term_banner_click_here).click()
						.build().perform();
				Thread.sleep(500);
				Test_Status_Steps.verify_new_window_url(currentHandle, "help");
				Test_Status_Steps.close_all_new_window_url();
			}
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
			verify_past_district_term_banner_And_check_new_tab(admin_past_dist_banner_txt, currentHandle);

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12063 completed");
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
			verify_info_icon_and_tooltip_on_comparison();
			// ======================================================
			UtilityMethods.summary_tab_under_standard_performance();
			verify_info_icon_and_tooltip_on_comparison();
			// ======================================================
			UtilityMethods.comparison_Tab_under_Test_Score_Tab();
			verify_info_icon_and_tooltip_on_comparison();

			// ==============Student-Test Score/Comparison
			// Tab========================================
			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_test_score();
			verify_info_icon_and_tooltip_on_comparison();
			// ==============Student-Test Score/Overview
			// Tab========================================
			js.executeScript("arguments[0].click();", homePage.overviewtext);
			UtilityMethods.wait_For_Test_Score_Overview_Section_Load();
			verify_info_icon_and_tooltip_on_comparison();

			// ==============Student-Single Test score Analysis
			// Tab========================================
			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_info_icon_and_tooltip_on_comparison();

			// ==============Student-Standard Performance
			// Tab========================================
			js.executeScript("arguments[0].click();", homePage.standardperformancebtn);
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			verify_info_icon_and_tooltip_on_comparison();

			// ==============Student-Standard Performance
			// Tab/Comparison========================================

			UtilityMethods.comparison_under_standard_performance();
			verify_info_icon_and_tooltip_on_comparison();

			// ==============Student-Standard Performance
			// Tab/summary========================================

			UtilityMethods.summary_tab_under_standard_performance();
			verify_info_icon_and_tooltip_on_comparison();

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12072 completed");
	}

	public void verify_info_icon_and_tooltip_on_comparison() {
		try {
			Assert.assertTrue(homePage.info_icon_on_class_on_compare_chkbox.isDisplayed());
			homePage.info_icon_on_class_on_compare_chkbox.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.tooltip_of_info_icon_on_class_on_compare_chkbox.getText().equals(
					"Class comparison displays the average score for your current class roster in a past district term."));
			homePage.info_icon_on_class_on_compare_chkbox.click();
			Thread.sleep(500);
			try {
				Assert.assertTrue(homePage.tooltip_of_info_icon_on_class_on_compare_chkbox.getText().equals(
						"Class comparison displays the average score for your current class roster in a past district term."));
				UtilityMethods.processException(new Exception());
			} catch (Exception e) {
			}
		} catch (Exception e) {
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
}
