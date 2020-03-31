package com.bec.reporting.steps;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.UtilityMethods;
import com.google.common.collect.Ordering;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test_Status_Steps {
	/**
	 * This is used to initialize webelement of the webpages
	 */
	static HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	public static JavascriptExecutor jse = (JavascriptExecutor) Driver.webdriver;

	@When("^User Click on Test Status tab within the District Context$")
	public void user_Click_on_Test_Status_tab_within_the_District_Context() throws Throwable {
		try {
			UtilityMethods.wait_For_Context_Header_Section();
			Assert.assertTrue(homePage.activedistrictmenu.getAttribute("district").contains("active"));
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", homePage.districtmenu);
			Thread.sleep(3000);
		}
		try {
			Assert.assertTrue(homePage.active_test_status_btn.getAttribute("class").contains("active_tab"));
		} catch (Exception e) {
			jse.executeScript("arguments[0].click();", homePage.test_status_btn);
		}
		UtilityMethods.wait_For_Test_Status_Section_Load();
	}

	@Then("^Verify info icon text on summary$")
	public void verify_info_icon_text_on_summary() throws Throwable {
		try {
			String info_text = "Note: Students who have taken a test more than once will be repeated in the test status results.";
			Assert.assertTrue(homePage.info_icon_in_ts.isDisplayed());
			homePage.info_icon_in_ts.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.tool_tip_on_info_icon_in_ts.getText().equals(info_text));
			new Actions(Driver.webdriver).moveByOffset(200, 200).click().build().perform();
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario TestStatus1 completed");
	}

	@Then("^Verify summary and detail on test status$")
	public void verify_summary_and_detail_on_test_status() throws Throwable {
		try {
			// verifying the order of columns in summary in Test Status
			Assert.assertTrue(homePage.columns_header_in_summary_in_ts.get(0).getText().contains("Test Name"));
			Assert.assertTrue(homePage.columns_header_in_summary_in_ts.get(1).getText().contains("Not Started"));
			Assert.assertTrue(homePage.columns_header_in_summary_in_ts.get(2).getText().contains("In Progress"));
			Assert.assertTrue(
					homePage.columns_header_in_summary_in_ts.get(3).getText().contains("Needs to\n" + "Be Graded"));
			Assert.assertTrue(homePage.columns_header_in_summary_in_ts.get(4).getText().contains("Complete"));

			// Verifying by default the Test Names are in ascending order
			List<String> testNames = new ArrayList<String>();
			for (int i = 0; i < homePage.test_names_in_Test_Name_Column.size(); i++) {
				testNames.add(homePage.test_names_in_Test_Name_Column.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(testNames));
			testNames.clear();
			String testName_on_ch = UtilityMethods.getTestsNameonUI();
			String total_no_of_test = testName_on_ch.substring(testName_on_ch.indexOf("(") + 1,
					testName_on_ch.indexOf(")"));
			String count_on_header = homePage.columns_header_in_summary_in_ts.get(0).getText();
			count_on_header = count_on_header.substring(count_on_header.indexOf("(") + 1, count_on_header.indexOf(")"));
			Assert.assertTrue(total_no_of_test.equals(count_on_header));

			int rand_no = (int) (Math.random() * homePage.test_status_number_in_summary_in_ts.size());
			new Actions(Driver.webdriver).moveToElement(homePage.test_status_number_in_summary_in_ts.get(rand_no))
					.build().perform();
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			Thread.sleep(500);
			log.info("Test Status Number:" + homePage.test_status_number_in_summary_in_ts.get(rand_no).getText());
			homePage.test_status_number_in_summary_in_ts.get(rand_no).click();
			UtilityMethods.wait_For_Test_Status_Section_Load();

			String columnHeader = homePage.column_header_in_summary_in_ts.getText() + ":";
			String testName = homePage.selected_numbers_test_name_in_summary_in_ts.getText();
			log.info("Column Header =>" + columnHeader);
			log.info("Test Name =>" + testName);
			String detail_title = homePage.detail_title_status_in_ts.getText() + homePage.detail_title_in_ts.getText();
			log.info("Detail Title =>" + detail_title);
			Assert.assertTrue(columnHeader.concat(testName).equals(detail_title));

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario TestStatus2 completed");
	}

	@Then("^Verify sorting order in summary$")
	public void verify_sorting_order_in_summary() throws Throwable {
		try {
			List<String> listItem = new ArrayList<String>();
			List<Integer> ts_numbers = new ArrayList<Integer>();
			// clicking on test name up arrow,checking alpha descending
			new Actions(Driver.webdriver).moveToElement(homePage.test_name_up_arrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.test_names_in_Test_Name_Column.size(); i++) {
				listItem.add(homePage.test_names_in_Test_Name_Column.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(listItem));
			listItem.clear();

			// clicking on test name down arrow,checking alpha ascending
			jse.executeScript("arguments[0].click();", homePage.test_name_down_arrow);
			Thread.sleep(500);

			for (int i = 0; i < homePage.test_names_in_Test_Name_Column.size(); i++) {
				listItem.add(homePage.test_names_in_Test_Name_Column.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(listItem));
			listItem.clear();

			// clicking on Not Started up arrow
			new Actions(Driver.webdriver).moveToElement(homePage.not_started_up_arrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.not_started_numbers_in_not_started_column.size(); i++) {
				ts_numbers.add(Integer.parseInt(homePage.not_started_numbers_in_not_started_column.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(ts_numbers));
			ts_numbers.clear();

			// clicking on Not Started down arrow
			jse.executeScript("arguments[0].click();", homePage.not_started_down_arrow);
			Thread.sleep(500);

			for (int i = 0; i < homePage.not_started_numbers_in_not_started_column.size(); i++) {
				ts_numbers.add(Integer.parseInt(homePage.not_started_numbers_in_not_started_column.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().isOrdered(ts_numbers));
			ts_numbers.clear();

			// clicking on In Progress up arrow
			new Actions(Driver.webdriver).moveToElement(homePage.in_progress_up_arrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.in_progress_numbers_in_in_progress_column.size(); i++) {
				ts_numbers.add(Integer.parseInt(homePage.in_progress_numbers_in_in_progress_column.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(ts_numbers));
			ts_numbers.clear();

			// clicking on In Progress down arrow
			jse.executeScript("arguments[0].click();", homePage.in_progress_down_arrow);
			Thread.sleep(500);

			for (int i = 0; i < homePage.in_progress_numbers_in_in_progress_column.size(); i++) {
				ts_numbers.add(Integer.parseInt(homePage.in_progress_numbers_in_in_progress_column.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().isOrdered(ts_numbers));
			ts_numbers.clear();

			// clicking on Needs To Be Graded up arrow
			new Actions(Driver.webdriver).moveToElement(homePage.needs_tbg_up_arrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.needs_to_be_graded_numbers_in_needs_to_be_graded_column.size(); i++) {
				ts_numbers.add(Integer
						.parseInt(homePage.needs_to_be_graded_numbers_in_needs_to_be_graded_column.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(ts_numbers));
			ts_numbers.clear();

			// clicking on Needs To Be Graded down arrow
			jse.executeScript("arguments[0].click();", homePage.needs_tbg_down_arrow);
			Thread.sleep(500);

			for (int i = 0; i < homePage.needs_to_be_graded_numbers_in_needs_to_be_graded_column.size(); i++) {
				ts_numbers.add(Integer
						.parseInt(homePage.needs_to_be_graded_numbers_in_needs_to_be_graded_column.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().isOrdered(ts_numbers));
			ts_numbers.clear();

			// clicking on Complete up arrow
			new Actions(Driver.webdriver).moveToElement(homePage.complete_up_arrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.complete_numbers_in_complete_column.size(); i++) {
				ts_numbers.add(Integer.parseInt(homePage.complete_numbers_in_complete_column.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(ts_numbers));
			ts_numbers.clear();

			// clicking on Complete down arrow
			jse.executeScript("arguments[0].click();", homePage.complete_down_arrow);
			Thread.sleep(500);

			for (int i = 0; i < homePage.complete_numbers_in_complete_column.size(); i++) {
				ts_numbers.add(Integer.parseInt(homePage.complete_numbers_in_complete_column.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().isOrdered(ts_numbers));
			ts_numbers.clear();

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario TestStatus3 completed");
	}

	@Then("^Verify sorting order in detail$")
	public void verify_sorting_order_in_detail() throws Throwable {
		try {
			int rand_no = (int) (Math.random() * homePage.test_status_number_in_summary_in_ts.size());
			new Actions(Driver.webdriver).moveToElement(homePage.test_status_number_in_summary_in_ts.get(rand_no))
					.build().perform();
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			Thread.sleep(500);
			homePage.test_status_number_in_summary_in_ts.get(rand_no).click();
			Thread.sleep(500);
			List<String> sc_name = new ArrayList<String>();
			List<Date> date_list = new ArrayList<Date>();
			// clicking on test name up arrow,checking alpha descending
			new Actions(Driver.webdriver).moveToElement(homePage.school_up_arrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.school_names_in_school_Column.size(); i++) {
				sc_name.add(homePage.school_names_in_school_Column.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(sc_name));
			sc_name.clear();

			// clicking on test name down arrow,checking alpha ascending
			jse.executeScript("arguments[0].click();", homePage.school_down_arrow);
			Thread.sleep(500);

			for (int i = 0; i < homePage.school_names_in_school_Column.size(); i++) {
				sc_name.add(homePage.school_names_in_school_Column.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(sc_name));
			sc_name.clear();

			// Create a SimpleDateFormat object for Date String converting.
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
			// clicking on Start Date up arrow
			new Actions(Driver.webdriver).moveToElement(homePage.start_date_up_arrow).click().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.start_dates_in_start_date_Column.size(); i++) {
				date_list.add(sdf.parse(homePage.start_dates_in_start_date_Column.get(i).getText()));
			}
			Assert.assertTrue(UtilityMethods.isDatesSortedInDecendingOrder(date_list));
			date_list.clear();

			// clicking on Start Date down arrow
			jse.executeScript("arguments[0].click();", homePage.start_date_down_arrow);
			Thread.sleep(500);
			for (int i = 0; i < homePage.start_dates_in_start_date_Column.size(); i++) {
				date_list.add(sdf.parse(homePage.start_dates_in_start_date_Column.get(i).getText()));
			}
			Assert.assertTrue(UtilityMethods.isDatesSortedInAscendingOrder(date_list));
			date_list.clear();

			// clicking on Due Date up arrow
			new Actions(Driver.webdriver).moveToElement(homePage.due_date_up_arrow).click().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.due_dates_in_due_date_Column.size(); i++) {
				date_list.add(sdf.parse(homePage.due_dates_in_due_date_Column.get(i).getText()));
			}
			Assert.assertTrue(UtilityMethods.isDatesSortedInDecendingOrder(date_list));
			date_list.clear();

			// clicking on Due Date down arrow
			jse.executeScript("arguments[0].click();", homePage.due_date_down_arrow);
			Thread.sleep(500);

			for (int i = 0; i < homePage.due_dates_in_due_date_Column.size(); i++) {
				date_list.add(sdf.parse(homePage.due_dates_in_due_date_Column.get(i).getText()));
			}
			Assert.assertTrue(UtilityMethods.isDatesSortedInAscendingOrder(date_list));
			date_list.clear();

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario TestStatus4 completed");
	}
}
