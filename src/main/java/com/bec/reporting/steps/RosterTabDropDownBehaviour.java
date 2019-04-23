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
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.DatabaseConnection;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.IWait;
import com.bec.reporting.utils.UtilityMethods;
import com.google.common.base.Verify;
import com.google.common.collect.Ordering;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RosterTabDropDownBehaviour {

	/**
	 *This is used to initialize webelement of the webpages 
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	public static String selectedSchoolText,selectedclassText,selectedStudentText;
	int schoolcount=0,selectedschool=0,classcount=0,selectedclassIndex=0,studentcount=0,selectedstudent=0;

	
	@Then("^User Click on Roster tab within the Universal Selector Tab and bydefault first alpha school and first alpha class and 'all' student are selected$")
	public void user_Click_on_Roster_tab_within_the_Universal_Selector_Tab_and_bydefault_first_alpha_school_and_first_alpha_class_and_all_student_are_selected() throws Throwable {
		try {
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
			Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
			Thread.sleep(500);
			String defaultSelectedSchool,defaultSelectedClass;
			String apiFirstSchool=DatabaseConnection.getFirstAlphaSchoolName();
			String apiFirstClass=DatabaseConnection.getFirstAlphaClassNameBySchoolName(apiFirstSchool);
			
			new Actions(Driver.webdriver).moveToElement(homePage.schooldropdownbtn).build().perform();
			defaultSelectedSchool=homePage.schooldropdownbtntooltip.getText();
			Thread.sleep(500);
			
			new Actions(Driver.webdriver).moveToElement(homePage.classdropdownbtn).build().perform();
			defaultSelectedClass=homePage.classdropdownbtntooltip.getText();
			Thread.sleep(500);
			Assert.assertTrue(defaultSelectedSchool.equals(apiFirstSchool));
			Assert.assertTrue(defaultSelectedClass.equals(apiFirstClass));
			Assert.assertTrue(homePage.studentdropdownbtn.getText().equals("All"));
			homePage.rostercancelbtn.click();
			Thread.sleep(2000);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 10_1 Completed");
	}

	@Then("^User Click on Roster tab within the Universal Selector Tab and the School and Class names and the student last names should be displayed in alphabetical ascending order within their respective dropdowns$")
	public void user_Click_on_Roster_tab_within_the_Universal_Selector_Tab_and_the_School_and_Class_names_and_the_student_last_names_should_be_displayed_in_alphabetical_ascending_order_within_their_respective_dropdowns() throws Throwable {
		try {
			List<String> schoolList = new ArrayList<>();
			List<String> classList=new ArrayList<>();
			List<String>studentList=new ArrayList<>();
			Thread.sleep(2000);
			homePage.rostertab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
			Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
			Thread.sleep(1000);
			
			//**checking school dropdown in ascending order*//
			homePage.schooldropdownbtn.click();
			Thread.sleep(2000);
			schoolcount=homePage.schoollist.size();
			String scName;
			for (int i = 0; i < schoolcount; i++) {
				scName=homePage.schoollist.get(i).getText();
				if (scName.equals("")) {
					UtilityMethods.scroll_Div(homePage.schoollist.get(i), 10);
				}
				schoolList.add(homePage.schoollist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(schoolList));
			
			selectedschool=(int) (Math.random()*schoolcount);
			selectedSchoolText=schoolList.get(selectedschool);
			log.info("Selected School:"+selectedSchoolText);
			
			homePage.searchbaronschooldropdown.sendKeys(selectedSchoolText);
			Thread.sleep(1000);
			Driver.webdriver.findElement(By.xpath("//li[.='"+selectedSchoolText+"']")).click();
			int count = 1;
			try {
				do {
					log.info("Thread sleep called for class Loading :" + count +" Times");
					Thread.sleep(2000);
					count++;
					if(count>10) {
						log.error("Issue in Class Data Loading");
						UtilityMethods.processException(new Exception());
					}
				} while (homePage.classRefreshIcon.isDisplayed() && count<=10);
			} catch (Exception e) {
				log.info("Class Refresh Icon Display off");
				count = 1;
			}
			Thread.sleep(2000);
			//**checking class dropdown in ascending order*//
			homePage.classdropdownbtn.click();
			Thread.sleep(2000);
			classcount=homePage.classlist.size();			
			String clName;
			for (int i = 0; i < classcount; i++) {
				clName=homePage.classlist.get(i).getText();
				if (clName.equals("")) {
					UtilityMethods.scroll_Div(homePage.classlist.get(i), 10);
				}
				classList.add(homePage.classlist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(classList));
			selectedclassIndex=(int) (Math.random()*classcount);
			selectedclassText=classList.get(selectedclassIndex);
			log.info("Selected Class:"+selectedclassText);
			Thread.sleep(1000);
			homePage.searchbaronclassdropdown.sendKeys(selectedclassText);
			Thread.sleep(1000);
			Driver.webdriver.findElement(By.xpath("//li[.='"+selectedclassText+"']")).click();
			try {
				do {
					log.info("Thread sleep called for Student Loading :" + count +" Times");
					Thread.sleep(2000);
					count++;
					if(count>10) {
						log.error("Issue in Student Data Loading");
						UtilityMethods.processException(new Exception());
					}
				} while (homePage.studentRefreshIcon.isDisplayed()  && count<=10);
			} catch (Exception e) {
				log.info("Student Refresh Icon Display off");
			}
			Thread.sleep(2000);
			//**checking student dropdown in ascending order*//
			homePage.studentdropdownbtn.click();
			Thread.sleep(2000);
			studentcount=homePage.studentlistondropdown.size();
			String tempString;
			UtilityMethods.scrollPageDown(Driver.webdriver, 4);
			List<String> list=new ArrayList<>();
			for (int i = 0; i < studentcount; i++) {
				tempString=homePage.studentlistondropdown.get(i).getText();
				if (tempString.equals("")) {
					UtilityMethods.scroll_Div(homePage.studentlistondropdown.get(i), 10);
					tempString=homePage.studentlistondropdown.get(i).getText();
				}
				list.add(tempString);
				studentList.add(tempString.substring(tempString.indexOf(" ")+1)+" "+tempString.substring(0, tempString.indexOf(" ")));
			}
			
			Assert.assertTrue(Ordering.natural().isOrdered(studentList));
			selectedstudent=(int) (Math.random()*studentcount);
			Thread.sleep(1000);
			selectedStudentText=list.get(selectedstudent);
			log.info("Selected Student:"+selectedStudentText);	
			homePage.searchbaronstudentdropdown.sendKeys(selectedStudentText);
			Thread.sleep(1000);
			Driver.webdriver.findElement(By.xpath("//li[.='"+selectedStudentText+"']")).click();
			homePage.rosterapplybtn.click();
			Thread.sleep(2000);	
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 10_2 Completed");
	}

	/**
	 * This method verify that selected option from the dropdown in school,class and student should be on top while comes back to dropdown
	 * @throws Throwable
	 */
	@Then("^Verify selected option should appear at the top of the list$")
	public void verify_selected_option_should_appear_at_the_top_of_the_list() throws Throwable {
		try {
			UtilityMethods.scrollPageUp(Driver.webdriver);
			UtilityMethods.scrollPageDown(Driver.webdriver,4);
			new Actions(Driver.webdriver).moveToElement(homePage.rostertab).click().build().perform();
			IWait.explicit_wait(Driver.webdriver, homePage.schoolTitleOnSliderMenu);
			Verify.verify(homePage.schoolTitleOnSliderMenu.isDisplayed());
			Thread.sleep(500);
			
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.schoollist.get(0).getText().equals(selectedSchoolText));
			homePage.schooldropdownbtn.click();
			Thread.sleep(500);
			try {
				new Actions(Driver.webdriver).moveToElement(homePage.classdropdownbtn).build().perform();
				String clName = Driver.webdriver.findElement(By.xpath(
						"//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div/button//div[@class='bec_tooltip_content']"))
						.getText();
				Assert.assertTrue(clName.equals(selectedclassText));	
			}
			catch(Exception e) {
				homePage.classdropdownbtn.click();
				Thread.sleep(500);
				Assert.assertTrue(homePage.classlist.get(0).getText().equals(selectedclassText));
				homePage.classdropdownbtn.click();
				Thread.sleep(500);
		
			}
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.studentlistondropdown.get(0).getText().equals(selectedStudentText));
			homePage.studentdropdownbtn.click();
			Thread.sleep(500);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 10_3 Completed");
	}

	/**
	 * 
	 * This method seaches school,class and student on their respected search bar and randomly select within found result and if not record found click on cancel icon 'X' 
	 * @throws Throwable
	 */
	@Then("^Searches anything what’s being typed in showing \"([^\"]*)\" to cancel and displays them as options to select from below the search bar and filters the list\\.$")
	public void searches_anything_what_s_being_typed_in_showing_to_cancel_and_displays_them_as_options_to_select_from_below_the_search_bar_and_filters_the_list(String arg1) throws Throwable {
		try {
			int count = 0;
			//school dropdown search test
			homePage.schooldropdownbtn.click();
			Thread.sleep(1000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			Verify.verify(homePage.searchbaronschooldropdown.isDisplayed());
			homePage.searchbaronschooldropdown.sendKeys(UtilityMethods.generateRandomString(1));
			Thread.sleep(1000);
			Verify.verify(homePage.searchcancelonschooldropdown.isDisplayed());
			if(homePage.schoollist.get(0).getText().equals("No Records Available")) {
				homePage.searchcancelonschooldropdown.click();
				homePage.schooldropdownbtn.click();
				Thread.sleep(2000);
			}
			else {
				schoolcount=homePage.schoollist.size();
				selectedschool=(int) (Math.random()*schoolcount);
				Thread.sleep(500);
				homePage.schoollist.get(selectedschool).click();
				try {
					do {
						log.info("Thread sleep called for class Loading :" + count +" Times");
						Thread.sleep(2000);
						count++;
						if(count>10) {
							log.error("Issue in Class Data Loading");
							UtilityMethods.processException(new Exception());
						}
					} while (homePage.classRefreshIcon.isDisplayed() && count<=10);
				} catch (Exception e) {
					log.info("Class Refresh Icon Display off");
					count = 1;
				}
				Thread.sleep(1000);
			}
			
			//class dropdown search test
			homePage.classdropdownbtn.click();
			Thread.sleep(1000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 2);
			Verify.verify(homePage.searchbaronclassdropdown.isDisplayed());
			homePage.searchbaronclassdropdown.sendKeys(UtilityMethods.generateRandomString(1));
			Thread.sleep(500);
			Verify.verify(homePage.searchcancelonclassdropdown.isDisplayed());
			if(homePage.classlist.get(0).getText().equals("No Records Available")) {
				homePage.searchcancelonclassdropdown.click();
				homePage.classdropdownbtn.click();
				Thread.sleep(2000);
			}
			else {
				schoolcount=homePage.classlist.size();
				selectedclassIndex=(int) (Math.random()*schoolcount);
				Thread.sleep(500);
				homePage.classlist.get(selectedclassIndex).click();	
				try {
					do {
						log.info("Thread sleep called for class Loading :" + count +" Times");
						Thread.sleep(2000);
						count++;
						if(count>10) {
							log.error("Issue in Student Data Loading");
							UtilityMethods.processException(new Exception());
						}
					} while (homePage.studentRefreshIcon.isDisplayed() && count<=10);
				} catch (Exception e) {
					log.info("Class Student Icon Display off");				
				}
				Thread.sleep(2000);
			}
			//student dropdown search test
			homePage.studentdropdownbtn.click();
			Thread.sleep(2000);
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			Verify.verify(homePage.searchbaronstudentdropdown.isDisplayed());
			homePage.searchbaronstudentdropdown.sendKeys(UtilityMethods.generateRandomString(1));
			Thread.sleep(500);
			Verify.verify(homePage.searchcancelonstudentdropdown.isDisplayed());
			if(homePage.studentlistondropdown.get(0).getText().equals("No Records Available")) {
				homePage.searchcancelonstudentdropdown.click();
				homePage.studentdropdownbtn.click();
				Thread.sleep(2000);
			}
			else {
				studentcount=homePage.studentlistondropdown.size();
				selectedstudent=(int) (Math.random()*studentcount);
				UtilityMethods.scrollPageDown(Driver.webdriver,selectedstudent);
				Thread.sleep(500);
				homePage.studentlistondropdown.get(selectedstudent).click();				
			}
			Thread.sleep(1000);
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
			UtilityMethods.scrollPageDown(Driver.webdriver, 3);
			homePage.studentdropdownbtn.click();
			Thread.sleep(2000);
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
