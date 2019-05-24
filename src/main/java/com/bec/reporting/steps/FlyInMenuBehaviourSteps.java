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
import org.openqa.selenium.support.ui.Select;
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
	 * method used to launch the browser with the url and provide the username,password and usertype 
	 * @throws Throwable
	 */
	@Given("^User is on portal's login screen with username as \"([^\"]*)\" and password as \"([^\"]*)\" and usertype as \"([^\"]*)\"$")
	public void user_is_on_portal_s_login_screen_with_username_as_and_password_as_and_usertype_as(String username, String password,String usertype) throws Throwable {
		log.info("User is on portal's login screen");
		try {
			prop = FileRead.readProperties();
			Driver.launch_browser(prop.getProperty("portalUrl"));
			Thread.sleep(3000);
			homePage.username.clear();
			homePage.username.sendKeys(username);
			homePage.password.clear();
			homePage.password.sendKeys(password);
			Select select=new Select(homePage.usertypedropdown);
			select.selectByVisibleText(usertype);
			Thread.sleep(2000);
			homePage.loginbtn.click();
			//Thread.sleep(5000);
			IWait.explicit_wait(Driver.webdriver, homePage.overviewtext);					
			Assert.assertTrue("User is on SSO portal's home screen", homePage.overviewtext.isDisplayed());
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}
	
	/**
	 * This method is used to open and close the Universal Selector Tab one by one
	 * @param tabName
	 * @throws Throwable
	 */
	@Then("^User is able to open and close the \"([^\"]*)\" tab within the Universal Selector Tab$")
	public void user_is_able_to_open_and_close_the_tab_within_the_Universal_Selector_Tab(String tabName) throws Throwable {
		try {
			IWait.explicit_wait(Driver.webdriver, homePage.openarrow);
			Verify.verify(homePage.openarrow.isDisplayed());
			Driver.webdriver.findElement(By.xpath("//span[@class='menu-name' and contains(text(),'" + tabName + "')]"))
					.click();
			Thread.sleep(1000);
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
			homePage.closearrow.click();
			Thread.sleep(1000);
			Assert.assertTrue(homePage.openarrow.isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 1 for "+tabName+" Completed");		
	}

	/**
	 * method used to click on roster tab and verify school text above school dropdown 
	 * @throws Throwable
	 */
	@When("^User Click on Roster tab within the Universal Selector Tab$")
	public void user_Click_on_Roster_tab_within_the_Universal_Selector_Tab() throws Throwable {
		try {
			IWait.explicit_wait(Driver.webdriver, homePage.rostertab);
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
			Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
			Thread.sleep(500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}
	
	/**
	 * This method is used to verify the different student type selection in Roster Tab and after applying button verify the content with
	 * context header info
	 * @param studentType
	 * @throws Throwable
	 */
	@Then("^User should be able to select school and Class and student as \"([^\"]*)\" and apply and verify with context header information$")
	public void user_should_be_able_to_select_school_and_Class_and_student_as_and_apply_and_verify_with_context_header_information(String studentType) throws Throwable {
		try {
			String selectedSchool,selectedClass,selectedStudent;
			// selecting school from dropdown
			homePage.schooldropdownbtn.click();
			Thread.sleep(1000);
			int randomSchoolIndex=UtilityMethods.generateRandomNumberBySkippingIndex(homePage.schoollist.size(), 0);
			selectedSchool=homePage.schoollist.get(randomSchoolIndex).getText();
			homePage.schoollist.get(randomSchoolIndex).click();			
			log.info("Selected School is:" +selectedSchool );
			Assert.assertTrue(homePage.classRefreshIcon.isDisplayed());
			int count = 1;
			try {
				do {
					log.info("Thread sleep called for class Loading :" + count + " Times");
					Thread.sleep(2000);
					count++;
				} while (homePage.classRefreshIcon.isDisplayed());
			} catch (Exception e) {
				log.info("Class Refresh Icon Display off");
				count = 1;
			}
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			// selecting class from dropdown
			homePage.classdropdownbtn.click();
			Thread.sleep(1000);
			selectedClass=homePage.classlist.get(0).getText();
			homePage.classlist.get(0).click();
			log.info("Selected Class is:" + selectedClass);
			Assert.assertTrue(homePage.studentRefreshIcon.isDisplayed());
			try {
				do {
					log.info("Thread sleep called for Student Loading :" + count + " Times");
					Thread.sleep(2000);
					count++;
				} while (homePage.studentRefreshIcon.isDisplayed());
			} catch (Exception e) {
				log.info("Student Refresh Icon Display off");
			}
			// selecting student from dropdown
			homePage.studentdropdownbtn.click();
			Thread.sleep(1000);
			int customSize=0;
			//This is to unselect all student from dropdown,as default is all selected
			homePage.studentalllist.get(0).click();
			Thread.sleep(1000);
			UtilityMethods.scrollPageDown(Driver.webdriver,5);
			switch (studentType) {
			case "single":
				homePage.studentalllist.get(2).click();
				break;
			case "multiple":
				for (int i = 1; i < homePage.studentalllist.size(); i=i+2) {
					if(homePage.studentalllist.get(i).getText().equals("")) {
						UtilityMethods.scroll_Div(homePage.studentalllist.get(i), 20);
					}
					homePage.studentalllist.get(i).click();
					Thread.sleep(1000);
					customSize++;
				}
				break;
			default:
				homePage.studentalllist.get(0).click();
				break;
			}
			Thread.sleep(1000);
			selectedStudent=homePage.studentdropdownbtn.getText();
			log.info("Selected Student is:" + selectedStudent);

			homePage.rosterapplybtn.click();
			Thread.sleep(3000);
			UtilityMethods.scrollPageUp(Driver.webdriver,8);
			/**
			 * verifying class and school and student on context menu by comparing dropdown
			 * text and context menu values
			 */
			String schoolNameonCH,classNameonCH,studentTextonCH;
			if (homePage.schoolnameoncontextheader.getText().contains("...")) {
				new Actions(Driver.webdriver).moveToElement(homePage.schoolnameoncontextheader).build().perform();
				schoolNameonCH = homePage.tooltipofschoolnameoncontextheader.getText();
				new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
			} else {
				schoolNameonCH = homePage.schoolnameoncontextheader.getText();
			}

			if (homePage.classnameoncontextheader.getText().contains("...")) {
				new Actions(Driver.webdriver).moveToElement(homePage.classnameoncontextheader).build().perform();
				classNameonCH = homePage.tooltipofclassnameoncontextheader.getText();
				new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
			}
			else {
				classNameonCH = homePage.classnameoncontextheader.getText();
			}

			Assert.assertTrue(selectedSchool.equals(schoolNameonCH));
			Assert.assertTrue(selectedClass.equals(classNameonCH));

			switch (studentType) {
			
			case "single":

				if (homePage.studentnameoncontextheader.getText().contains("...")) {
					new Actions(Driver.webdriver).moveToElement(homePage.studentnameoncontextheader).build().perform();
					studentTextonCH = homePage.studentnameoncontextheadertooltiptext.getText();
				} else {
					studentTextonCH = homePage.studentnameoncontextheader.getText();
				}

				Assert.assertTrue(studentTextonCH.contains(selectedStudent));
				Assert.assertTrue(homePage.activestudentmenu.isDisplayed());
				break;

			case "multiple":
				
				if (homePage.studentnameoncontextheader.getText().contains("...")) {
					new Actions(Driver.webdriver).moveToElement(homePage.studentnameoncontextheader).build().perform();
					studentTextonCH = homePage.studentnameoncontextheadertooltiptext.getText();
				} else {
					studentTextonCH = homePage.studentnameoncontextheader.getText();
				}
				Assert.assertTrue(studentTextonCH.equals("Custom ("+customSize+")"));
				Assert.assertTrue(homePage.activeclassmenu.isDisplayed());
				break;
				
			default:
				Assert.assertTrue(homePage.activeclassmenu.isDisplayed());
				break;
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 2 for "+studentType+" Completed");		
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
			IWait.explicit_wait(Driver.webdriver,homePage.testtab);
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
			homePage.allcheckbox.click();
			Thread.sleep(500);
			
			String selectedTestName;
			int testCheckBoxListSize = 0, randomNumber = 0;
			testCheckBoxListSize = homePage.testscheckboxlist.size();
			randomNumber = (int) (Math.random() * testCheckBoxListSize);
			
			switch (testType) {
			case "single":
				UtilityMethods.scrollPageDown(Driver.webdriver, randomNumber+1);
				selectedTestName=homePage.testnameslist.get(randomNumber).getText();
				homePage.testscheckboxlist.get(randomNumber).click();
				Thread.sleep(500);
				UtilityMethods.scrollPageDown(Driver.webdriver);
				Thread.sleep(1000);
				UtilityMethods.scrollPageDown(Driver.webdriver);
				Thread.sleep(1000);
				new Actions(Driver.webdriver).moveToElement(homePage.testapplybtn).click().build().perform();
				Thread.sleep(3000);
				UtilityMethods.scrollPageUp(Driver.webdriver);
				Thread.sleep(2000);
				new Actions(Driver.webdriver).moveToElement(homePage.nooftestoncontextheader).build().perform();
				Assert.assertTrue(selectedTestName.equals(homePage.tooltipoftestnameoncontextheader.getText()));
				break;
			case "multiple":
				int noOfSelectedTest=0;
				for (int i = 0; i < testCheckBoxListSize; i = i + 2) {
					homePage.testscheckboxlist.get(i).click();
					UtilityMethods.scrollPageDown(Driver.webdriver, i + 2);
					Thread.sleep(500);
					noOfSelectedTest++;
				}
				homePage.testapplybtn.click();
				Thread.sleep(3000);
				UtilityMethods.scrollPageUp(Driver.webdriver);
				Thread.sleep(500);
				Assert.assertTrue(homePage.nooftestoncontextheader.getText().equals("Custom ("+noOfSelectedTest+")"));
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
			UtilityMethods.scrollPageDown(Driver.webdriver, 11);
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
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Thread.sleep(1000);
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
			IWait.explicit_wait(Driver.webdriver,homePage.rostertab);
			WebElement el = Driver.webdriver.findElement(By.xpath("//span[@class='menu-name' and contains(text(),'"
					+ tabName + "')]/ancestor::div[@class='menu-item']"));
			Actions builder = new Actions(Driver.webdriver);
			Action mouseOver = builder.moveToElement(el).build();
			mouseOver.perform();
			WebElement ttt=Driver.webdriver.findElement(By.xpath("//span[@class='menu-name' and contains(text(),'"+tabName+"')]/ancestor::div[@class='menu-item']//div[@class='bec_tooltip_content']"));
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
