package com.bec.reporting.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.IWait;
import com.bec.reporting.utils.UtilityMethods;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Custom_Test_Handling_Steps {
	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	static JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;

	@Then("^Test tab context header custom test handling in reporting for deleting question from any test or assessment$")
	public void test_tab_context_header_custom_test_handling_in_reporting_for_deleting_question_from_any_test_or_assessment()
			throws Throwable {
		try {
			UtilityMethods.select_Specific_Grade_From_Roster("Grade 4");
			String selectedTestName;
			homePage.testtab.click();
			Thread.sleep(1000);
			homePage.allcheckbox_in_test_tab.click();
			Thread.sleep(500);
			homePage.searchbarontesttab.sendKeys("custom");
			Thread.sleep(500);
			homePage.testscheckboxlist.get(0).click();
			Thread.sleep(500);
			selectedTestName = homePage.testnameslist_on_test_tab.get(0).getText();
			log.info("Selected Test :" + selectedTestName);
			new Actions(Driver.webdriver).moveToElement(homePage.testapplybtn).click().build().perform();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_custom_test_on_test_tab(selectedTestName);

			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_custom_test_on_test_tab(selectedTestName);

			js.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_custom_test_on_test_tab(selectedTestName);

			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			verify_custom_test_on_test_tab(selectedTestName);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12959 completed");
	}

	private void verify_custom_test_on_test_tab(String selectedTestName) {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.testsNameoncontextheader).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(selectedTestName.equals(homePage.tooltipoftestnameoncontextheader.getText()));
			Assert.assertTrue(homePage.tooltipoftestnameoncontextheader.getText().contains("Custom - "));
			homePage.testtab.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.testnameslist_on_test_tab.get(0).getText().equals(selectedTestName));
			Assert.assertTrue(homePage.testscheckboxlistwithinput.get(0).getAttribute("value").equals("true"));
			homePage.testtab.click();
			Thread.sleep(500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^verify STA reports custom test handling in reporting for deleting question from any test or assessment$")
	public void verify_STA_reports_custom_test_handling_in_reporting_for_deleting_question_from_any_test_or_assessment()
			throws Throwable {
		try {
			UtilityMethods.select_Specific_Grade_From_Roster("Grade 4");
			String selectedTestName;
			homePage.testtab.click();
			Thread.sleep(1000);
			homePage.allcheckbox_in_test_tab.click();
			Thread.sleep(500);
			homePage.searchbarontesttab.sendKeys("custom");
			Thread.sleep(500);
			homePage.testscheckboxlist.get(0).click();
			Thread.sleep(500);
			selectedTestName = homePage.testnameslist_on_test_tab.get(0).getText();
			log.info("Selected Test :" + selectedTestName);
			new Actions(Driver.webdriver).moveToElement(homePage.testapplybtn).click().build().perform();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			new Actions(Driver.webdriver).moveToElement(homePage.testsNameoncontextheader).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(selectedTestName.equals(homePage.tooltipoftestnameoncontextheader.getText()));
			Assert.assertTrue(homePage.tooltipoftestnameoncontextheader.getText().contains("Custom - "));
			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_deleted_custom_test_on_STA(selectedTestName);
			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_deleted_custom_test_on_STA(selectedTestName);
			js.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_deleted_custom_test_on_STA(selectedTestName);
			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_deleted_custom_test_on_STA(selectedTestName);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12961 completed");
	}

	private void verify_deleted_custom_test_on_STA(String selectedTestName) {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.testsNameoncontextheader).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(selectedTestName.equals(homePage.tooltipoftestnameoncontextheader.getText()));
			UtilityMethods.scrollPageDown(Driver.webdriver, 10);
			Thread.sleep(500);
			Assert.assertTrue(homePage.question_list_on_sta.get(2).getAttribute("class")
					.contains("bec_singleTest_multi_list_deletedQuestion_opacity_color"));
			Assert.assertTrue(homePage.district_avg_list_for_question_on_sta.get(2).findElement(By.xpath("hr"))
					.getAttribute("style").contains("background-color"));

			new Actions(Driver.webdriver).moveToElement(homePage.info_icon_on_deleted_qauestion_in_sta).click().build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.tool_tip_on_info_icon_on_deleted_qauestion_in_sta.getText()
					.equals("Question deleted by Tara Mendez on 01/09/20"));
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(500);
			homePage.filter_in_sta_for_district.click();
			Thread.sleep(1000);
			homePage.raw_rb_on_filter_in_sta.click();
			Thread.sleep(1000);
			homePage.apply_button_on_filter_in_sta.click();
			Thread.sleep(2000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 10);
			Thread.sleep(500);
			Assert.assertTrue(homePage.question_list_on_sta.get(2).getAttribute("class")
					.contains("bec_singleTest_multi_list_deletedQuestion_opacity_color"));
			Assert.assertTrue(homePage.district_avg_list_for_question_on_sta.get(2).findElement(By.xpath("hr"))
					.getAttribute("style").contains("background-color"));
			new Actions(Driver.webdriver).moveToElement(homePage.info_icon_on_deleted_qauestion_in_sta).click().build()
					.perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.tool_tip_on_info_icon_on_deleted_qauestion_in_sta.getText()
					.equals("Question deleted by Tara Mendez on 01/09/20"));
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(500);
			homePage.filter_in_sta_for_district.click();
			Thread.sleep(1000);
			homePage.view_rb_on_filter_in_sta.click();
			Thread.sleep(1000);
			homePage.apply_button_on_filter_in_sta.click();
			Thread.sleep(2000);
			for (int i = 0; i < homePage.single_question_in_question_column_on_table_on_sta.size(); i++) {
				Assert.assertTrue(
						!homePage.single_question_in_question_column_on_table_on_sta.get(i).getText().equals("3"));
			}

			homePage.filter_in_sta_for_district.click();
			Thread.sleep(1000);
			homePage.percentage_rb_on_filter_in_sta.click();
			Thread.sleep(1000);
			homePage.apply_button_on_filter_in_sta.click();
			Thread.sleep(2000);
			for (int i = 0; i < homePage.single_question_in_question_column_on_table_on_sta.size(); i++) {
				Assert.assertTrue(
						!homePage.single_question_in_question_column_on_table_on_sta.get(i).getText().equals("3"));
			}
			homePage.filter_in_sta_for_district.click();
			Thread.sleep(1000);
			homePage.question_rb_on_filter_in_sta.click();
			Thread.sleep(1000);
			homePage.apply_button_on_filter_in_sta.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^verify standard performance overtime line graph - custom test handling in reporting for deleting question from any test or assessment$")
	public void verify_standard_performance_overtime_line_graph_custom_test_handling_in_reporting_for_deleting_question_from_any_test_or_assessment()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			UtilityMethods.select_Specific_Grade_From_Roster("Grade 4");
			String selectedTestName;
			homePage.testtab.click();
			Thread.sleep(1000);
			homePage.allcheckbox_in_test_tab.click();
			Thread.sleep(500);
			homePage.searchbarontesttab.sendKeys("custom");
			Thread.sleep(500);
			homePage.testscheckboxlist.get(0).click();
			Thread.sleep(500);
			selectedTestName = homePage.testnameslist_on_test_tab.get(0).getText();
			log.info("Selected Test :" + selectedTestName);
			new Actions(Driver.webdriver).moveToElement(homePage.testapplybtn).click().build().perform();
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_custom_test_deleted_on_pot(selectedTestName);

			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_custom_test_deleted_on_pot(selectedTestName);

			js.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_custom_test_deleted_on_pot(selectedTestName);

			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			verify_custom_test_deleted_on_pot(selectedTestName);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12963 completed");
	}

	private void verify_custom_test_deleted_on_pot(String selectedTestName) {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.testsNameoncontextheader).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(selectedTestName.equals(homePage.tooltipoftestnameoncontextheader.getText()));
			Assert.assertTrue(homePage.tooltipoftestnameoncontextheader.getText().contains("Custom - "));

			js.executeScript("arguments[0].click();", homePage.performance_overtime_icon);
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			UtilityMethods.scrollPageDown(Driver.webdriver, 5);
			Thread.sleep(500);
			new Actions(Driver.webdriver).moveToElement(homePage.testnames_on_pot.get(0)).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.tooltip_on_test_name_on_linechart.getText().equals(selectedTestName));
			new Actions(Driver.webdriver).moveToElement(homePage.testScoreValueInCircle_onlinechart_pot.get(0)).click()
					.build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.testname_on_test_score_value_tooltip.getText().equals(selectedTestName));
			for (int i = 0; i < homePage.questionlistontooltip.size(); i++) {
				Assert.assertTrue(!homePage.questionlistontooltip.get(i).getText().equals("3"));
			}
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^verify summary no data screens within widgets for summary reports$")
	public void verify_summary_no_data_screens_within_widgets_for_summary_reports() throws Throwable {
		try {
			homePage.summary_context.click();
			Thread.sleep(2000);
			UtilityMethods.select_specific_district_term("2018-2019");
			IWait.explicit_wait(Driver.webdriver, homePage.studentmenu);
			Thread.sleep(2000);
			Assert.assertTrue(
					homePage.summary_context.getAttribute("class").contains("ar_summary_deactivate_lable_opacity"));
			new Actions(Driver.webdriver).moveToElement(homePage.summary_context).build().perform();
			Thread.sleep(500);
			Assert.assertTrue(homePage.tooltip_on_summary_context.getText()
					.equals("Summary reports are not available for past school year."));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12968 completed");
	}

	@Then("^verify summary layout with landing page$")
	public void verify_summary_layout_with_landing_page() throws Throwable {
		try {
			homePage.summary_context.click();
			Thread.sleep(2000);
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			Assert.assertTrue(homePage.active_summary_context.isDisplayed());
			Assert.assertTrue(
					homePage.summary_context_table_titles.get(0).getText().equals("Assessments | Test Status"));
			Assert.assertTrue(homePage.summary_context_table_titles.get(1).getText()
					.equals("Assessments | Standards Performance"));
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			Thread.sleep(500);
			Assert.assertTrue(homePage.summary_context_table_titles.get(2).getText()
					.equals("Oral Reading Records | Reading Level Progress"));

			Assert.assertTrue(homePage.contextheader_title_list.get(0).getText().equals("District:"));
			Assert.assertTrue(homePage.contextheader_title_list.get(1).getText().equals("Dates:"));
			String date_on_ch = homePage.contextheader_text_list.get(0).getText();

			homePage.datetab.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.districttermdropdownbtn.getText().equals(date_on_ch));

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12967,BU-12970 completed");
	}
}
