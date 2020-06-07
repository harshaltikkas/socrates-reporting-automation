package com.bec.reporting.steps;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.IWait;
import com.bec.reporting.utils.UtilityMethods;
import com.google.common.collect.Ordering;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sprint_37_Steps {

	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	static JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;

	@Then("^Verify School dropdown is displayed in Roster tab$")
	public void verify_School_dropdown_is_displayed_in_Roster_tab() throws Throwable {
		try {
			String s = "All";
			int schoolcount = 0;
			List<String> schoolList = new ArrayList<String>();
			homePage.rostertab.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.schooldropdownbtn.isDisplayed());
			log.info("School dropdown is displayed");
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			String schoolname = homePage.schooldropdownbtn.getText();
			Assert.assertNotEquals(s, schoolname);
			schoolcount = homePage.schoollist.size();
			String scName;
			for (int i = 0; i < schoolcount; i++) {
				scName = homePage.schoollist.get(i).getText();
				if (scName.equals("")) {
					UtilityMethods.scroll_Div(homePage.schoollist.get(i), 5);
				}
				schoolList.add(homePage.schoollist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(schoolList));
			schoolList.clear();
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			try {
				Assert.assertTrue(homePage.gradedropdownbtn.isDisplayed());
				log.info("Grade dropdown is displayed");
			} catch (Exception e) {
				log.info("Grade dropdown is not displayed");
			}
			IWait.check_Absence_of_Element(homePage.teachersdropdownbtn);
			log.info("Teacher dropdown is not displayed");
			Assert.assertTrue(homePage.classdropdownbtn.isDisplayed());
			log.info("Class dropdown is displayed");
			String classname = homePage.classdropdownbtn.getText();
			Assert.assertNotEquals(s, classname);
			Assert.assertTrue(homePage.studentdropdownbtn.isDisplayed());
			log.info("Student dropdown is displayed");
			String studentname = homePage.studentdropdownbtn.getText();
			Assert.assertEquals(s, studentname);
			log.info("All is selected in Student dropdown");
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Sprint 37_TC-2370 completed");
	}

	@Then("^Verify School dropdown is not displayed in Roster tab$")
	public void verify_School_dropdown_is_not_displayed_in_Roster_tab() throws Throwable {
		try {
			String s = "All";
			homePage.rostertab.click();
			Thread.sleep(500);
			IWait.check_Absence_of_Element(homePage.schooldropdownbtn);
			log.info("School dropdown is not displayed");

			try {
				Assert.assertTrue(homePage.gradedropdownbtn.isDisplayed());
				log.info("Grade dropdown is displayed");
			} catch (Exception e) {
				log.info("Grade dropdown is not displayed");
			}
			IWait.check_Absence_of_Element(homePage.teachersdropdownbtn);
			log.info("Teacher dropdown is not displayed");
			Assert.assertTrue(homePage.classdropdownbtn.isDisplayed());
			log.info("Class dropdown is displayed");
			String classname = homePage.classdropdownbtn.getText();
			Assert.assertNotEquals(s, classname);
			Assert.assertTrue(homePage.studentdropdownbtn.isDisplayed());
			log.info("Student dropdown is displayed");
			String studentname = homePage.studentdropdownbtn.getText();
			Assert.assertEquals(s, studentname);
			log.info("All is selected in Student dropdown");
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Sprint 37_TC-2371 completed");
	}

}
