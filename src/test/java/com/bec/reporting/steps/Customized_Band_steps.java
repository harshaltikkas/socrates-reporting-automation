package com.bec.reporting.steps;

import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.API_Connection;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.UtilityMethods;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Customized_Band_steps {

	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	static JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;

	@Then("^verify custom achievement levels on single test analysis reports table for different levels$")
	public void verify_custom_achievement_levels_on_single_test_analysis_reports_table_for_different_levels()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			List<String> list = API_Connection.get_Achievement_Levels();
			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			log.info("clicked on District Menu");
			verify_color_and_avg_score_of_STA("view", list);

			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_STA_Section_Load();
			log.info("clicked on School Menu");
			verify_color_and_avg_score_of_STA("question", list);

			js.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_STA_Section_Load();
			log.info("clicked on Class Menu");
			verify_color_and_avg_score_of_STA("view", list);

			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_STA_Section_Load();
			log.info("clicked on Student Menu");
			verify_color_and_avg_score_of_STA("question", list);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12347 completed");
	}

	public void verify_color_and_avg_score_of_STA(String sortBy, List<String> list) throws InterruptedException {
		int score = 0;
		WebElement scoreElement = null;
		String per = "";
		for (int j = 0; j < homePage.avg_list_for_question_on_sta.size(); j++) {
			per = homePage.avg_list_for_question_on_sta.get(j).getText();
			if (per.contains("%")) {
				per = per.substring(0, per.indexOf("%"));
			}
			score = Integer.parseInt(per);
			scoreElement = homePage.avg_list_for_question_on_sta.get(j);
			UtilityMethods.verifyColorAndScoreOnSTA(scoreElement, score, list);
		}
		WebElement el = Driver.webdriver
				.findElement(By.xpath("//div[@class='bec_singleTest_multi_list_single_label']"));
		log.info("verified color with score verifying on " + el.getText() + " filter");
		homePage.filter_in_sta_for_district.click();
		Thread.sleep(1000);
		Driver.webdriver.findElement(
				By.xpath("//span[@class='bec_singleTest_filter_radio_label text-capitalize' and contains(text(),'"
						+ sortBy + "')]/preceding-sibling::span"))
				.click();
		Thread.sleep(500);
		homePage.apply_button_on_filter_in_sta.click();
		Thread.sleep(2000);
		for (int j = 0; j < homePage.avg_list_for_question_on_sta.size(); j++) {
			per = homePage.avg_list_for_question_on_sta.get(j).getText();
			if (per.contains("%")) {
				per = per.substring(0, per.indexOf("%"));
			}
			score = Integer.parseInt(per);
			scoreElement = homePage.avg_list_for_question_on_sta.get(j);
			UtilityMethods.verifyColorAndScoreOnSTA(scoreElement, score, list);
		}
		el = Driver.webdriver.findElement(By.xpath("//div[@class='bec_singleTest_multi_list_single_label']"));
		log.info("verified color with score verifying on " + el.getText() + " filter");
	}

	@Then("^verify custom achievement levels on grouping modal and reports table for class level$")
	public void verify_custom_achievement_levels_on_grouping_modal_and_reports_table_for_class_level()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			Assert.assertTrue(homePage.activeclassmenu.isDisplayed());
			List<String> list = API_Connection.get_Achievement_Levels();
			js.executeScript("arguments[0].click();", homePage.groupingTab);
			Thread.sleep(5000);

			UtilityMethods.select_view_on_viewDropdown_on_Grouping_Tab("CaCCSS English Language Arts");
			homePage.applyBtnOngroupingTab.click();
			UtilityMethods.wait_For_Strands_Text_After_Apply_BtnOn_GroupingTab();

			WebElement scoreElement = null;
			List<WebElement> avg_score_list = Driver.webdriver.findElements(
					By.xpath("//div[@class='bec_group_multi_list_Single_group_body_row_avg_score']/span"));

			for (int j = 0; j < avg_score_list.size(); j++) {
				scoreElement = avg_score_list.get(j);
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, avg_score_list.get(j).getText(),
						list);
			}

			List<WebElement> language_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_group_multi_list_Single_group_body_row_strand_score_list']/div[1]//span/div"));

			for (int j = 0; j < language_list.size(); j++) {
				scoreElement = language_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, language_list.get(j).getText(),
						list);
			}

			List<WebElement> rfs_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_group_multi_list_Single_group_body_row_strand_score_list']/div[2]//span/div"));

			for (int j = 0; j < rfs_list.size(); j++) {
				scoreElement = rfs_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, rfs_list.get(j).getText(), list);
			}

			List<WebElement> rit_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_group_multi_list_Single_group_body_row_strand_score_list']/div[3]//span/div"));

			for (int j = 0; j < rit_list.size(); j++) {
				scoreElement = rit_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, rit_list.get(j).getText(), list);
			}

			List<WebElement> rl_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_group_multi_list_Single_group_body_row_strand_score_list']/div[4]//span/div"));

			for (int j = 0; j < rl_list.size(); j++) {
				scoreElement = rl_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, rl_list.get(j).getText(), list);
			}

			List<WebElement> writing_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_group_multi_list_Single_group_body_row_strand_score_list']/div[5]//span/div"));

			for (int j = 0; j < writing_list.size(); j++) {
				scoreElement = writing_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, writing_list.get(j).getText(), list);
			}

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12348 completed");
	}

	@Then("^verify custom achievement levels on comparison modal and reports table for different levels$")
	public void verify_custom_achievement_levels_on_comparison_modal_and_reports_table_for_different_levels()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			List<String> list = API_Connection.get_Achievement_Levels();
			log.info("Verifying comparison table score and color value under district menu");
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			Thread.sleep(15000);
			UtilityMethods
					.select_view_on_viewDropdown_on_Edit_Standards_comparison_popup("CaCCSS English Language Arts");
			homePage.applyBtnOnstandardTab.click();
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			WebElement scoreElement = null;
			List<WebElement> avg_score_list = Driver.webdriver.findElements(
					By.xpath("//div[@class='bec_compare_multi_list_Single_compare_body_row_avg_score']/span"));

			for (int j = 0; j < avg_score_list.size(); j++) {
				scoreElement = avg_score_list.get(j);
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, avg_score_list.get(j).getText(),
						list);
			}

			List<WebElement> language_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[1]//span/div"));

			for (int j = 0; j < language_list.size(); j++) {
				scoreElement = language_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, language_list.get(j).getText(),
						list);
			}

			List<WebElement> rfs_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[2]//span/div"));

			for (int j = 0; j < rfs_list.size(); j++) {
				scoreElement = rfs_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, rfs_list.get(j).getText(), list);
			}

			List<WebElement> rit_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[3]//span/div"));

			for (int j = 0; j < rit_list.size(); j++) {
				scoreElement = rit_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, rit_list.get(j).getText(), list);
			}

			List<WebElement> rl_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[4]//span/div"));

			for (int j = 0; j < rl_list.size(); j++) {
				scoreElement = rl_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, rl_list.get(j).getText(), list);
			}

			List<WebElement> writing_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[5]//span/div"));

			for (int j = 0; j < writing_list.size(); j++) {
				scoreElement = writing_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, writing_list.get(j).getText(), list);
			}
			log.info("Verifying comparison table score and color value under school menu");
			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			Thread.sleep(15000);
			UtilityMethods.select_view_on_viewDropdown_on_Edit_Standards_comparison_popup("Claims and Targets");
			homePage.applyBtnOnstandardTab.click();
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();

			avg_score_list = Driver.webdriver.findElements(
					By.xpath("//div[@class='bec_compare_multi_list_Single_compare_body_row_avg_score']/span"));

			for (int j = 0; j < avg_score_list.size(); j++) {
				scoreElement = avg_score_list.get(j);
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, avg_score_list.get(j).getText(),
						list);
			}

			List<WebElement> lc_one_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[1]//span/div"));

			for (int j = 0; j < lc_one_list.size(); j++) {
				scoreElement = lc_one_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, lc_one_list.get(j).getText(), list);
			}

			List<WebElement> lc_two_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[2]//span/div"));

			for (int j = 0; j < lc_two_list.size(); j++) {
				scoreElement = lc_two_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, lc_two_list.get(j).getText(), list);
			}
			log.info("Verifying comparison table score and color value under class menu");
			js.executeScript("arguments[0].click();", homePage.classmenu);
			Thread.sleep(15000);
			UtilityMethods.select_view_on_viewDropdown_on_Edit_Standards_comparison_popup("Depth of Knowledge");
			homePage.applyBtnOnstandardTab.click();
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();

			avg_score_list = Driver.webdriver.findElements(
					By.xpath("//div[@class='bec_compare_multi_list_Single_compare_body_row_avg_score']/span"));

			for (int j = 0; j < avg_score_list.size(); j++) {
				scoreElement = avg_score_list.get(j);
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, avg_score_list.get(j).getText(),
						list);
			}

			List<WebElement> dok2_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[1]//span/div"));

			for (int j = 0; j < dok2_list.size(); j++) {
				scoreElement = dok2_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, dok2_list.get(j).getText(), list);
			}

			List<WebElement> dok3_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[2]//span/div"));

			for (int j = 0; j < dok3_list.size(); j++) {
				scoreElement = dok3_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, dok3_list.get(j).getText(), list);
			}

			List<WebElement> dok1_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[3]//span/div"));

			for (int j = 0; j < dok1_list.size(); j++) {
				scoreElement = dok1_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, dok1_list.get(j).getText(), list);
			}

			List<WebElement> dok4_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[4]//span/div"));

			for (int j = 0; j < dok4_list.size(); j++) {
				scoreElement = dok4_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, dok4_list.get(j).getText(), list);
			}

			log.info("Verifying comparison table score and color value under student menu");
			js.executeScript("arguments[0].click();", homePage.studentmenu);
			Thread.sleep(15000);
			UtilityMethods.select_view_on_viewDropdown_on_Edit_Standards_comparison_popup("Claims and Targets");
			homePage.applyBtnOnstandardTab.click();
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();

			avg_score_list = Driver.webdriver.findElements(
					By.xpath("//div[@class='bec_compare_multi_list_Single_compare_body_row_avg_score']/span"));

			for (int j = 0; j < avg_score_list.size(); j++) {
				scoreElement = avg_score_list.get(j);
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, avg_score_list.get(j).getText(),
						list);
			}

			List<WebElement> lc1_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[1]//span/div"));

			for (int j = 0; j < lc1_list.size(); j++) {
				scoreElement = lc1_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, lc1_list.get(j).getText(), list);
			}

			List<WebElement> lc2_list = Driver.webdriver.findElements(By.xpath(
					"//div[@class='bec_compare_multi_list_Single_compare_body_row_strand_score_list']/div[2]//span/div"));

			for (int j = 0; j < lc2_list.size(); j++) {
				scoreElement = lc2_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, lc2_list.get(j).getText(), list);
			}

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12349 completed");
	}

	@Then("^verify custom achievement levels on test scores over time line chart for different levels$")
	public void verify_custom_achievement_levels_on_test_scores_over_time_line_chart_for_different_levels()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			List<String> list = API_Connection.get_Achievement_Levels();
			log.info("verifying test score over time with achivement color on district menu");
			js.executeScript("arguments[0].click();", homePage.test_scores_btn);
			UtilityMethods.wait_For_Test_Score_Detail_Section();

			for (int i = homePage.testScoresonPerPage_onlinechart.size() - 1; i >= 0; i--) {
				UtilityMethods.verifyColorAndScoreOnLineChart(homePage.testScoreCircleClronPerPage_onlinechart.get(i),
						Integer.parseInt(homePage.testScoresonPerPage_onlinechart.get(i).getText()), list);
			}
			log.info("verifying test score over time with achivement color on school menu");
			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			for (int i = homePage.testScoresonPerPage_onlinechart.size() - 1; i >= 0; i--) {
				UtilityMethods.verifyColorAndScoreOnLineChart(homePage.testScoreCircleClronPerPage_onlinechart.get(i),
						Integer.parseInt(homePage.testScoresonPerPage_onlinechart.get(i).getText()), list);
			}
			log.info("verifying test score over time with achivement color on class menu");
			js.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			for (int i = homePage.testScoresonPerPage_onlinechart.size() - 1; i >= 0; i--) {
				UtilityMethods.verifyColorAndScoreOnLineChart(homePage.testScoreCircleClronPerPage_onlinechart.get(i),
						Integer.parseInt(homePage.testScoresonPerPage_onlinechart.get(i).getText()), list);
			}
			log.info("verifying test score over time with achivement color on student menu");
			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_Test_Score_Overview_Section_Load();
			for (int i = homePage.testScoresonPerPage_onlinechart.size() - 1; i >= 0; i--) {
				UtilityMethods.verifyColorAndScoreOnLineChart(homePage.testScoreCircleClronPerPage_onlinechart.get(i),
						Integer.parseInt(homePage.testScoresonPerPage_onlinechart.get(i).getText()), list);
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12485 completed");
	}

	@Then("^verify custom achievement levels on key toggle for different levels$")
	public void verify_custom_achievement_levels_on_key_toggle_for_different_levels() throws Throwable {
		try {
			List<String> list = API_Connection.get_Achievement_Levels();
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			log.info("verifying key toggle color range on district-SP-Overview");
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on district-SP-comparison tab");
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			Thread.sleep(7000);
			homePage.applyBtnOnstandardTab.click();
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on district-SP-summary tab");
			js.executeScript("arguments[0].click();", homePage.summarytab);
			UtilityMethods.wait_For_Summary_Tab_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on district-test score-overview tab");
			js.executeScript("arguments[0].click();", homePage.test_scores_btn);
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on district-test score-comparison tab");
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on district-STA tab");
			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on school-STA tab");
			js.executeScript("arguments[0].click();", homePage.schoolmenu);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on school-test score-comparison tab");
			js.executeScript("arguments[0].click();", homePage.test_scores_btn);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_test_score();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on school-test score-overview tab");
			js.executeScript("arguments[0].click();", homePage.overviewtext);
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on school-sp-overview tab");
			js.executeScript("arguments[0].click();", homePage.standardperformancebtn);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on school-SP-comparison tab");
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			Thread.sleep(7000);
			homePage.applyBtnOnstandardTab.click();
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on school-SP-summary tab");
			js.executeScript("arguments[0].click();", homePage.summarytab);
			UtilityMethods.wait_For_Summary_Tab_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on class-SP-summary tab");
			js.executeScript("arguments[0].click();", homePage.classmenu);
			UtilityMethods.wait_For_Summary_Tab_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on class-SP-comparison tab");
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			Thread.sleep(10000);
			homePage.applyBtnOnstandardTab.click();
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on class-SP-Overview tab");
			js.executeScript("arguments[0].click();", homePage.overviewtext);
			UtilityMethods.wait_For_Student_List_AND_OR_Class_List_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on class-Test Score-Overview tab");
			js.executeScript("arguments[0].click();", homePage.test_scores_btn);
			UtilityMethods.wait_For_Test_Score_Detail_Section();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on class-Test Score-comparison tab");
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_test_score();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on class-STA tab");
			js.executeScript("arguments[0].click();", homePage.statab);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on student-STA tab");
			js.executeScript("arguments[0].click();", homePage.studentmenu);
			UtilityMethods.wait_For_STA_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on student-Test Score-comparison tab");
			js.executeScript("arguments[0].click();", homePage.test_scores_btn);
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_test_score();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on student-test score-Overview tab");
			js.executeScript("arguments[0].click();", homePage.overviewtext);
			UtilityMethods.wait_For_Test_Score_Overview_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on student-SP-Overview tab");
			js.executeScript("arguments[0].click();", homePage.standardperformancebtn);
			UtilityMethods.wait_For_Performance_Over_Time_Line_Chart_Section_Load();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on student-SP-comparison tab");
			js.executeScript("arguments[0].click();", homePage.comparisontab);
			Thread.sleep(7000);
			homePage.applyBtnOnstandardTab.click();
			UtilityMethods.wait_For_Comparison_Tab_Section_Load_under_standard_performance();
			verify_key_toggle_achievement_level(list);

			log.info("verifying key toggle color range on student-SP-summary tab");
			js.executeScript("arguments[0].click();", homePage.summarytab);
			UtilityMethods.wait_For_Summary_Tab_Section_Load();
			verify_key_toggle_achievement_level(list);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12486 completed");
	}

	public void verify_key_toggle_achievement_level(List<String> list) {
		try {
			homePage.reportingkey.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.key_achievmentlevelslabel.isDisplayed());
			Assert.assertTrue(homePage.key_al_redcolor.getText().equals(
					"Below " + (Integer.parseInt(list.get(0).substring(list.get(0).indexOf("-") + 1)) + 1) + "%"));
			Assert.assertTrue(homePage.key_al_orangecolor.getText().equals(list.get(1) + "%"));
			Assert.assertTrue(homePage.key_al_yellowcolor.getText().equals(list.get(2) + "%"));
			Assert.assertTrue(homePage.key_al_greencolor.getText()
					.equals(list.get(3).substring(0, list.get(3).indexOf("-")) + "%+"));
			Assert.assertTrue(homePage.key_al_graycolor.getText().equals("No Data Available"));
			homePage.reportingkey.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	@Then("^verify custom achievement levels on standard summary report for different levels$")
	public void verify_custom_achievement_levels_on_standard_summary_report_for_different_levels() throws Throwable {
		try {
			List<String> list = API_Connection.get_Achievement_Levels();
			Assert.assertTrue(homePage.activestandardperformancebtn.isDisplayed());
			log.info("Verifying score and color on District Menu");
			js.executeScript("arguments[0].click();", homePage.summarytab);
			UtilityMethods.wait_For_Summary_Tab_Section_Load();
			verify_color_and_avg_score_of_Summary(list);
			
			js.executeScript("arguments[0].click();", homePage.schoolmenu);		
			log.info("Verifying score and color on School Menu");
			UtilityMethods.wait_For_Summary_Tab_Section_Load();
			verify_color_and_avg_score_of_Summary(list);

			js.executeScript("arguments[0].click();", homePage.classmenu);
			log.info("Verifying score and color on Class Menu");
			UtilityMethods.wait_For_Summary_Tab_Section_Load();
			verify_color_and_avg_score_of_Summary(list);

			js.executeScript("arguments[0].click();", homePage.studentmenu);
			log.info("Verifying score and color on Student Menu");
			UtilityMethods.wait_For_Summary_Tab_Section_Load();
			verify_color_and_avg_score_of_Summary(list);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario BU-12532 completed");
	}

	public void verify_color_and_avg_score_of_Summary(List<String> list) {
		try {
			WebElement scoreElement = null;
			String per = "";
			List<WebElement> avg_score_list = Driver.webdriver.findElements(By
					.xpath("//div[contains(@class,'bec_summary_multi_list_Single_compare_body_row_avg_score')]/span"));

			for (int j = 0; j < avg_score_list.size(); j++) {
				scoreElement = avg_score_list.get(j);
				per = avg_score_list.get(j).getText();
				if (per.contains("%")) {
					per = per.substring(0, per.indexOf("%"));
				}
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, per, list);
			}

			List<WebElement> dok1_list = Driver.webdriver.findElements(By.xpath(
					"//div[contains(@class,'bec_summary_multi_list_Single_compare_body_row_strand_score_list')]/div[1]//span/div"));

			for (int j = 0; j < dok1_list.size(); j++) {
				scoreElement = dok1_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, dok1_list.get(j).getText(), list);
			}

			List<WebElement> dok2_list = Driver.webdriver.findElements(By.xpath(
					"//div[contains(@class,'bec_summary_multi_list_Single_compare_body_row_strand_score_list')]/div[2]//span/div"));

			for (int j = 0; j < dok2_list.size(); j++) {
				scoreElement = dok2_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, dok2_list.get(j).getText(), list);
			}

			List<WebElement> dok3_list = Driver.webdriver.findElements(By.xpath(
					"//div[contains(@class,'bec_summary_multi_list_Single_compare_body_row_strand_score_list')]/div[3]//span/div"));

			for (int j = 0; j < dok3_list.size(); j++) {
				scoreElement = dok3_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, dok3_list.get(j).getText(), list);
			}

			List<WebElement> dok4_list = Driver.webdriver.findElements(By.xpath(
					"//div[contains(@class,'bec_summary_multi_list_Single_compare_body_row_strand_score_list')]/div[4]//span/div"));

			for (int j = 0; j < dok4_list.size(); j++) {
				scoreElement = dok4_list.get(j).findElement(By.xpath(".."));
				UtilityMethods.verifyColorAndScoreOn_Different_Table(scoreElement, dok4_list.get(j).getText(), list);
			}
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

}
