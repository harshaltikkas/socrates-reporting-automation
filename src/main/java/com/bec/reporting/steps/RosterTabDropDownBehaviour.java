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
 * JAN 14 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.steps;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.IWait;
import com.bec.reporting.utils.UtilityMethods;
import com.google.common.base.Verify;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RosterTabDropDownBehaviour {

	/**
	 *This is used to initialize webelement of the webpages 
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	public static String selectedSchoolText,selectedclassText,selectedStudentText;
	int schoolcount=0,selectedschool=0,classcount=0,selectedclass=0,studentcount=0,selectedstudent=0;

	/**
	 * This method click on roster tab, and select random school,random class and random student from dropdown
	 * @throws Throwable
	 */
	@When("^User Click on Roster tab within the Universal Selector Tab and select options from dropdown$")
	public void user_Click_on_Roster_tab_within_the_Universal_Selector_Tab_and_select_options_from_dropdown() throws Throwable {
		try {
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
			Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
			Thread.sleep(500);

			//**selecting school from dropdown*//
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			schoolcount=homePage.schoollist.size();
			selectedschool=(int) (Math.random()*schoolcount);
			UtilityMethods.scrollPageDown(Driver.webdriver,selectedschool);
			Thread.sleep(500);
			homePage.schoollist.get(selectedschool).click();	
			//homePage.schoollist.get(1).click();		//for testing	
			selectedSchoolText=homePage.schooldropdownbtn.getText();
			System.out.println("Selected School Name: "+selectedSchoolText);
			//**selecting class from dropdownd*//
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			classcount=homePage.classlist.size();
			selectedclass=(int) (Math.random()*classcount);
			UtilityMethods.scrollPageDown(Driver.webdriver,selectedclass);
			Thread.sleep(500);
			homePage.classlist.get(selectedclass).click();				
			//homePage.classlist.get(1).click();	//for testing			
			selectedclassText=homePage.classdropdownbtn.getText();
			System.out.println("Selected Class Name: "+selectedclassText);
			
			//**selecting student from dropdown*//
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			studentcount=homePage.studentlistondropdown.size();
			selectedstudent=(int) (Math.random()*studentcount);
			UtilityMethods.scrollPageDown(Driver.webdriver,selectedstudent);
			Thread.sleep(500);
			homePage.studentlistondropdown.get(selectedstudent).click();
			selectedStudentText=homePage.studentdropdownbtn.getText();
			System.out.println("Selected Student Name: "+selectedStudentText);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method verify that selected option from the dropdown in school,class and student should be on top while comes back to dropdown
	 * @throws Throwable
	 */
	@Then("^Verify selected option should appear at the top of the list\\.$")
	public void verify_selected_option_should_appear_at_the_top_of_the_list() throws Throwable {
		try {
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.schoollist.get(0).getText().equals(selectedSchoolText));
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.classlist.get(0).getText().equals(selectedclassText));
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.studentlistondropdown.get(0).getText().equals(selectedStudentText));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 10 Completed");
	}

	/**
	 * 
	 * This method seaches school,class and student on their respected search bar and randomly select within found result and if not record found click on cancel icon 'X' 
	 * @throws Throwable
	 */
	@Then("^Searches anything what’s being typed in showing \"([^\"]*)\" to cancel and displays them as options to select from below the search bar and filters the list\\.$")
	public void searches_anything_what_s_being_typed_in_showing_to_cancel_and_displays_them_as_options_to_select_from_below_the_search_bar_and_filters_the_list(String arg1) throws Throwable {
		try {
			//school dropdown search test
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			Verify.verify(homePage.searchbaronschooldropdown.isDisplayed());
			homePage.searchbaronschooldropdown.sendKeys(UtilityMethods.generateRandomString(1));
			Thread.sleep(500);
			Verify.verify(homePage.searchcancelonschooldropdown.isDisplayed());
			if(homePage.schoollist.get(0).getText().equals("No Records Available")) {
				homePage.searchcancelonschooldropdown.click();
			}
			else {
				schoolcount=homePage.schoollist.size();
				selectedschool=(int) (Math.random()*schoolcount);
				UtilityMethods.scrollPageDown(Driver.webdriver,selectedschool);
				Thread.sleep(500);
				homePage.schoollist.get(selectedschool).click();	
			}
			Thread.sleep(500);
			homePage.schooldropdownbtn.click();
			//class dropdown search test
			homePage.classdropdownbtn.click();
			Thread.sleep(500);
			Verify.verify(homePage.searchbaronclassdropdown.isDisplayed());
			homePage.searchbaronclassdropdown.sendKeys(UtilityMethods.generateRandomString(1));
			Thread.sleep(500);
			Verify.verify(homePage.searchcancelonclassdropdown.isDisplayed());
			if(homePage.classlist.get(0).getText().equals("No Records Available")) {
				homePage.searchcancelonclassdropdown.click();
			}
			else {
				classcount=homePage.classlist.size();
				selectedclass=(int) (Math.random()*classcount);
				UtilityMethods.scrollPageDown(Driver.webdriver,selectedclass);
				Thread.sleep(500);
				homePage.classlist.get(selectedclass).click();	
			}
			Thread.sleep(500);
			homePage.classdropdownbtn.click();
			//student dropdown search test
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			Verify.verify(homePage.searchbaronstudentdropdown.isDisplayed());
			homePage.searchbaronstudentdropdown.sendKeys(UtilityMethods.generateRandomString(1));
			Thread.sleep(500);
			Verify.verify(homePage.searchcancelonstudentdropdown.isDisplayed());
			if(homePage.studentlistondropdown.get(0).getText().equals("No Records Available")) {
				homePage.searchcancelonstudentdropdown.click();
			}
			else {
				studentcount=homePage.studentlistondropdown.size();
				selectedstudent=(int) (Math.random()*studentcount);
				UtilityMethods.scrollPageDown(Driver.webdriver,selectedstudent);
				Thread.sleep(500);
				homePage.studentlistondropdown.get(selectedstudent).click();	
			}
			Thread.sleep(500);
			homePage.studentdropdownbtn.click();
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 12 Completed");
	}

	/**
	 * This method is used to verify All option should be in top in student dropdown and once selected it show All as selected text in dropdown
	 * @throws Throwable
	 */
	@Then("^\"([^\"]*)\" option should be display at first position in list and can not be scrollable$")
	public void option_should_be_display_at_first_position_in_list_and_can_not_be_scrollable(String arg1) throws Throwable {
		try {
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			Verify.verify(homePage.studentalllist.get(0).getText().equals("All"));
			homePage.studentalllist.get(0).click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.studentdropdownbtn.getText().equals("All"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 13 Completed");
	}

}
