package com.bec.reporting.steps;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.UtilityMethods;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class staging_failure {

	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	static JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;

	@Then("^Verify STA reports navigating from Test Status report in student context$")
	public void verify_STA_reports_navigating_from_Test_Status_report_in_student_context() throws Throwable {
		try {
			homePage.classmenu.click();
			Thread.sleep(500);
			UtilityMethods.wait_For_Test_Status_Section_Load();

			homePage.studentmenu.click();
			Thread.sleep(500);
			UtilityMethods.wait_For_Test_Status_Section_Load_under_student_context();

			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BE-2473,Bug-1completed");
	}

	@Then("^Verify to click on the Class subway nav filter in test status$")
	public void verify_to_click_on_the_Class_subway_nav_filter_in_test_status() throws Throwable {
		try {
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			Thread.sleep(10000);
			js.executeScript("arguments[0].click();", homePage.applyBtnOnstandardTab);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());
			Assert.assertTrue(homePage.active_comparison_tab.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			Thread.sleep(20000);
			js.executeScript("arguments[0].click();", homePage.applyBtnOnstandardTab);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.classmenu);
			Thread.sleep(15000);
			js.executeScript("arguments[0].click();", homePage.applyBtnOnstandardTab);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.studentmenu);
			Thread.sleep(10000);
			js.executeScript("arguments[0].click();", homePage.applyBtnOnstandardTab);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.test_status_btn);
			UtilityMethods.wait_For_Test_Status_Section_Load_under_student_context();
			Assert.assertTrue(homePage.active_test_status_btn.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.districtmenu);
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());

			log.info("trying to clicking on class menu, moving from school to class with test status");
			homePage.classmenu.click();
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario Bug-2 completed");
	}

	@Then("^Verify no data avaliable for Test Scores tab$")
	public void verify_no_data_avaliable_for_Test_Scores_tab() throws Throwable {
		try {
			js.executeScript("arguments[0].click();", homePage.test_status_btn);
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.active_test_status_btn.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());

			homePage.standardperformancebtn.click();
			Thread.sleep(4000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(2000);

			homePage.testscoresbtn.click();
			Thread.sleep(4000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario Bug-3 completed");
	}

	@Then("^Verify no data avaliable for STA tab$")
	public void verify_no_data_avaliable_for_STA_tab() throws Throwable {
		try {
			js.executeScript("arguments[0].click();", homePage.test_status_btn);
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.active_test_status_btn.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());

			homePage.statab.click();
			Thread.sleep(4000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(2000);

			homePage.standardperformancebtn.click();
			Thread.sleep(4000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			homePage.returntopreviousreport.click();
			Thread.sleep(2000);

			homePage.standardperformancebtn.click();
			Thread.sleep(4000);
			Assert.assertTrue(homePage.nodatavailableforyourselection.isDisplayed());
			Assert.assertTrue(homePage.returntopreviousreport.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario Bug-4 completed");
	}

	@Then("^Verify student analysis$")
	public void verify_student_analysis() throws Throwable {
		try {
			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.test_status_btn);
			UtilityMethods.wait_For_Test_Status_Section_Load_under_student_context();
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());
			Assert.assertTrue(homePage.active_test_status_btn.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.districtmenu);
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.activedistrictmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.activeschoolmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_Test_Status_Section_Load();
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			Assert.assertTrue(homePage.active_sta_tab.isDisplayed());

			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_STA_Section_Load();
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario Bug-5 completed");
	}
}
