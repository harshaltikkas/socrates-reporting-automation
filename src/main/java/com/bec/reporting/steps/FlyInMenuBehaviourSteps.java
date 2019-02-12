/*
 * Copyright Benchmark Education Company
 *
 * (C) Copyright BEC - All rights reserved.
 *
 * NOTICE:  All information contained herein or attendant here to is,
 *          and remains, the property of Benchmark.  Many of the
 *          intellectual and technical concepts contained herein are
 *          proprietary to Benchmark. Any dissemination of this
 *          information or reproduction of this material is strictly
 *          forbidden unless prior written permission is obtained
 *          from Benchmark.
 *
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 * Revision History
 * ========================================================================
 * DATE				: PROGRAMMER  : DESCRIPTION
 * ========================================================================
 * JAN 04 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.steps;

import java.util.Properties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.FileRead;
import com.bec.reporting.utils.IWait;
import com.bec.reporting.utils.UtilityMethods;
import com.google.common.base.Verify;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FlyInMenuBehaviourSteps {
	
	/**
	 *This is used to initialize webelement of the webpages 
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	public static Properties prop;
	public static String toolTipText="";
	
	/**
	 * method used to launch the browser with the url
	 * @throws Throwable
	 */
	@Given("^User is on sso portal's home page$")
	public void user_is_on_sso_portal_s_home_page() throws Throwable {
		log.info("User is on sso portal's home page");
		try {
			prop = FileRead.readProperties();
			Driver.launch_browser(prop.getProperty("portalUrl"));
			Thread.sleep(3000);
			IWait.explicit_wait(Driver.webdriver, homePage.overviewtext);
			Assert.assertTrue("User is on SSO portal's home screen", homePage.overviewtext.isDisplayed());
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * method used to verify openarrow and click on Tab on Universal Selector
	 * @param  tabName
	 * @throws Throwable
	 */
	@When("^User Click on open arrows of \"([^\"]*)\" tab within the Universal Selector Tab$")
	public void user_Click_on_open_arrows_of_tab_within_the_Universal_Selector_Tab(String tabName) throws Throwable {
		try {
			Verify.verify(homePage.openarrow.isDisplayed());
			Driver.webdriver.findElement(By.xpath("//span[@class='menu-name' and contains(text(),'" + tabName + "')]"))
					.click();
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * method used to verify closearrow display 
	 * @param  tabName
	 * @throws Throwable
	 */
	@Then("^User should be able to open the slider for the \"([^\"]*)\" Tab and able to see the close arrows$")
	public void user_should_be_able_to_open_the_slider_for_the_Tab_with_and_able_to_see_the_close_arrows(String tabName) throws Throwable {
		try {
			if (tabName.equals("Roster")) {
				IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
				Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
			} else if (tabName.equals("Test")) {
				IWait.explicit_wait(Driver.webdriver, homePage.searchbarontesttab);
				Verify.verify(homePage.searchbarontesttab.isDisplayed());
			} else {
				IWait.explicit_wait(Driver.webdriver, homePage.districtNameOnSliderMenu);
				Verify.verify(homePage.districtNameOnSliderMenu.isDisplayed());
			}
			Assert.assertTrue(homePage.closearrow.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * method used to click on close arrow
	 * @param  tabName
	 * @throws Throwable
	 */
	@When("^User Click on close arrows for \"([^\"]*)\" tab within the Universal Selector Tab$")
	public void user_Click_on_close_arrows_for_tab_within_the_Universal_Selector_Tab(String tabName) throws Throwable {
		try {
			IWait.implicit_wait(2);
			homePage.closearrow.click();
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * method used to verify open arrow display 
	 * @param  tabName
	 * @throws Throwable
	 */
	@Then("^User should be able to close the slider for the \"([^\"]*)\" Tab and able to see the open arrows$")
	public void user_should_be_able_to_close_the_slider_for_the_Tab_and_able_to_see_the_open_arrows(String tabName) throws Throwable {
		try {
			IWait.implicit_wait(2);
			Assert.assertTrue(homePage.openarrow.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 1 Completed");
	}

	/**
	 * method used to click on roster tab and verify school text above school dropdown 
	 * @throws Throwable
	 */
	@When("^User Click on Roster tab within the Universal Selector Tab$")
	public void user_Click_on_Roster_tab_within_the_Universal_Selector_Tab() throws Throwable {
		try {
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
			Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
			Thread.sleep(500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * method used to select school,class and student from dropdown on roster tab and apply filter
	 * and verify the text on context header 
	 * @param school
	 * @param classNm
	 * @param student
	 * @throws Throwable
	 */
	@Then("^User should be able to select School \"([^\"]*)\" and Class \"([^\"]*)\" and Student \"([^\"]*)\" drop downlist and click on apply filter button$")
	public void user_should_be_able_to_select_School_and_Class_and_Student_drop_downlist_and_click_on_apply_filter_button(String school, String classNm, String student) throws Throwable {
		try {
			// selecting school from dropdown
			homePage.schooldropdownbtn.click();
			WebElement selectSchool = Driver.webdriver.findElement(By.xpath(
					"//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//li[contains(text(),'"
							+ school + "')]"));
			IWait.explicit_wait(Driver.webdriver, selectSchool);
			selectSchool.click();
			String schoolName = homePage.schooldropdownbtn.getText();
			Thread.sleep(1000);
			// selecting class from dropdown
			homePage.classdropdownbtn.click();
			WebElement selectClass = Driver.webdriver.findElement(By.xpath(
					"//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//li[contains(text(),'"
							+ classNm + "')]"));
			IWait.explicit_wait(Driver.webdriver, selectClass);
			selectClass.click();
			String className = homePage.classdropdownbtn.getText();
			Thread.sleep(1000);
			// selecting student from dropdown
			homePage.studentdropdownbtn.click();
			WebElement selectStudent = Driver.webdriver.findElement(By.xpath(
					"//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//li[contains(text(),'"
							+ student + "')]"));
			IWait.explicit_wait(Driver.webdriver, selectStudent);
			selectStudent.click();
			String studentName = homePage.studentdropdownbtn.getText();
			Thread.sleep(1000);
			homePage.rosterapplybtn.click();
			Thread.sleep(1000);
			/**
			 * verifying class and school and student on context menu by comparing dropdown
			 * text and context menu values
			 */
			String studentTextOnContextHeader;
			String schoolTextOnContextHeader=homePage.schoolnameoncontextheader.getText().trim();
			String classTextOnContextHeader=homePage.classnameoncontextheader.getText().trim();			
			Assert.assertTrue(schoolName.contains(UtilityMethods.elipsisRemoval(schoolTextOnContextHeader)));
			Assert.assertTrue(className.contains(UtilityMethods.elipsisRemoval(classTextOnContextHeader)));
			if (!(studentName.equals("All"))) {
				studentTextOnContextHeader=homePage.studentnameoncontextheader.getText().trim();
				Assert.assertTrue(studentName.contains(UtilityMethods.elipsisRemoval(studentTextOnContextHeader)));
				Assert.assertTrue(homePage.activestudent.isDisplayed());
			}
			Assert.assertTrue(homePage.activeclass.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 2 Completed");
	}

	/**
	 * method used to click on cancel button on roster tab 
	 * @throws Throwable
	 */	
	@Then("^User should be able to click on cancel button to close the Roster Tab\\.$")
	public void user_should_be_able_to_click_on_cancel_button_to_close_the_Roster_Tab() throws Throwable {
		try {			
			homePage.rostercancelbtn.click();
			Thread.sleep(500);
			Assert.assertEquals(false, homePage.schoolTitleOnSliderMenu.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 3 Completed");
	}

	/**
	 * method used to click on test tab on universal selector 
	 * @throws Throwable
	 */	
	@When("^User Click on Test tab within the Universal Selector Tab$")
	public void user_Click_on_Test_tab_within_the_Universal_Selector_Tab() throws Throwable {
		try {
			homePage.testtab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.searchbarontesttab);
			Verify.verify(homePage.searchbarontesttab.isDisplayed());
			Thread.sleep(500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}
	
	/**
	 * method used to select test(s) and apply filter on test tab and verify with context header 
	 * @throws Throwable
	 */	
	@Then("^User should be able to select \"([^\"]*)\" Test and click on apply filter button$")
	public void user_should_be_able_to_select_Test_and_click_on_apply_filter_button(String testType) throws Throwable {
		try {
			String selectedTest;
			int count = 0, selectcheckbox = 0;
			count = homePage.testscheckboxlist.size();
			selectcheckbox = (int) (Math.random() * count);
			homePage.allcheckbox.click();
			Thread.sleep(500);
			switch (testType) {
			case "single":
				homePage.testscheckboxlist.get(selectcheckbox).click();
				selectedTest=homePage.testnameslist.get(selectcheckbox).getText();
				Thread.sleep(500);
				homePage.testapplybtn.click();
				Thread.sleep(500);
				UtilityMethods.scrollPageUp(Driver.webdriver);
				Thread.sleep(500);
				Assert.assertTrue(selectedTest.contains(UtilityMethods.elipsisRemoval(homePage.nooftestoncontextheader.getText())));
				break;
			case "multiple":
				int noOfSelectedTest=0;
				for (int i = 0; i < count; i = i + 2) {
					homePage.testscheckboxlist.get(i).click();
					Thread.sleep(500);
					noOfSelectedTest++;
				}
				homePage.testapplybtn.click();
				Thread.sleep(500);
				UtilityMethods.scrollPageUp(Driver.webdriver);
				Thread.sleep(500);
				Assert.assertTrue(homePage.nooftestoncontextheader.getText().equals("Custom Selection ("+noOfSelectedTest+")"));
				break;
			default:
				/**
				 * This block can not be executed ,earlier we keep this clicking on all checkbox and then apply filter but now
				 * if by default all checkbox are selected then no apply button is enable.
				 */
				//Default end here
				break;
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 4 Completed");
	}

	/**
	 * method used to click on cancel button on test tab 
	 * @throws Throwable
	 */	
	@Then("^User should be able to click on cancel button to close the Test Tab\\.$")
	public void user_should_be_able_to_click_on_cancel_button_to_close_the_Test_Tab() throws Throwable {
		try {
			homePage.testcancelbtn.click();
			Thread.sleep(500);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Assert.assertEquals(false, homePage.searchbarontesttab.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 5 Completed");
	}
	
	/**
	 * method used to click on date tab within universal selector 
	 * @throws Throwable
	 */	
	@When("^User Click on Date tab within the Universal Selector Tab$")
	public void user_Click_on_Date_tab_within_the_Universal_Selector_Tab() throws Throwable {
		try {
			homePage.datetab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.districtNameOnSliderMenu);
			Verify.verify(homePage.districtNameOnSliderMenu.isDisplayed());
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * method used to select District Term on date tab and apply filter and verify with context header
	 * @throws Throwable
	 */	
	@Then("^User should be able to select district term and click on apply filter button$")
	public void user_should_be_able_to_select_district_term_and_click_on_apply_filter_button() throws Throwable {
		try {
			// selecting Random District Term from the list
			homePage.districttermdropdownbtn.click();
			int count = 0, selectdistrictterm = 0;
			count = homePage.districttermlist.size();
			selectdistrictterm = (int) (Math.random() * count);
			homePage.districttermlist.get(selectdistrictterm).click();
			Thread.sleep(500);
			String districtterm = homePage.districttermdropdownbtn.getText();
			Thread.sleep(500);
			homePage.dateapplybtn.click();
			Thread.sleep(500);
			/**
			 * Verifying District Term on context menu by comparing dropdown text and
			 * context menu values
			 */
			Assert.assertEquals(districtterm, homePage.datesoncontextheader.getText().trim());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 6 Completed");
	}

	/**
	 * method used to click on cancel button on Date tab 
	 * @throws Throwable
	 */	
	@Then("^User should be able to click on cancel button to close the Date Tab\\.$")
	public void user_should_be_able_to_click_on_cancel_button_to_close_the_Date_Tab() throws Throwable {
		try {
			homePage.datecancelbtn.click();
			Thread.sleep(500);
			Assert.assertEquals(false, homePage.districtNameOnSliderMenu.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 7 Completed");
	}

	/**
	 * method used to mouse hover on tab in universal selector
	 * @param tabName 
	 * @throws Throwable
	 */	
	@When("^User mousehover on \"([^\"]*)\" tab within the Universal Selector Tab$")
	public void user_mousehover_on_tab_within_the_Universal_Selector_Tab(String tabName) throws Throwable {
		try {
			WebElement el = Driver.webdriver.findElement(By.xpath("//span[@class='menu-name' and contains(text(),'"
					+ tabName + "')]/ancestor::div[@class='menu-item']"));
			Actions builder = new Actions(Driver.webdriver);
			Action mouseOver = builder.moveToElement(el).build();
			mouseOver.perform();
			WebElement ttt=Driver.webdriver.findElement(By.xpath("//span[@class='menu-name' and contains(text(),'"+tabName+"')]/following-sibling::span[@class='tooltiptext']"));
			toolTipText=ttt.getText();
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * method used to verify tooltip text of Tab on universal selector
	 * @param tabName 
	 * @param toolTip 
	 * @throws Throwable
	 */	
	@Then("^\"([^\"]*)\" tab show tool tip \"([^\"]*)\"$")
	public void tab_show_tool_tip(String tabName, String toolTip) throws Throwable {
		try {
			// Verifying for tooltiptext
			Assert.assertTrue(toolTipText.equals(toolTip));
			System.out.println(tabName+" shows tooltip "+toolTip);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 8 Completed");
	}
}
