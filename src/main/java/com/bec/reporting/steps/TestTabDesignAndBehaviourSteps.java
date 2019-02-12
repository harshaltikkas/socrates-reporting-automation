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
 * JAN 29 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.steps;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.IWait;
import com.bec.reporting.utils.UtilityMethods;
import com.google.common.base.Verify;
import com.google.common.collect.Ordering;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestTabDesignAndBehaviourSteps {

	/**
	 *This is used to initialize webelement of the webpages 
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	int testcount=0,selectedTest=0;
	static int totalTestCount=0;
	/**
	 * This method is clicking on test tab, verify with search bar and click on all checkbox in table header
	 * @throws Throwable
	 */
	@When("^User Click on Test tab within the Universal Selector Tab and click on 'All Checkbox'$")
	public void user_Click_on_Test_tab_within_the_Universal_Selector_Tab_and_click_on_All_Checkbox() throws Throwable {
		try {
			homePage.testtab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.searchbarontesttab);
			Verify.verify(homePage.searchbarontesttab.isDisplayed());
			//TC 44 will implement here, will check whether all checkbox checked or not bydefault
			homePage.allcheckbox.click();
			Thread.sleep(2000);
			homePage.allcheckbox.click();
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method used to verify whether all checkboxes are checked with the value true and checking pagination.
	 * @throws Throwable
	 */
	@Then("^verify all the tests within that test list must be selected\\.$")
	public void verify_all_the_tests_within_that_test_list_must_be_selected() throws Throwable {
		try {
			boolean doneWithThreeCircle = false, disableRightArrowFound = false, enabledRightArrowFound = false,
					paginatorFound = false;
			WebElement enabledRightArrow = null;
			List<WebElement> circleList = null;
			try {
				homePage.testpaginator.isDisplayed();
				paginatorFound = true;
			} catch (Exception e) {
				System.out.println("Paginator Not Found");
			}
			if (paginatorFound) {
				try {
					circleList = homePage.testpaginationcirclelist;
					enabledRightArrow = homePage.testenabledrightarrow;
					enabledRightArrow.isDisplayed();
					enabledRightArrowFound = true;
				} catch (Exception e) {
					System.out.println("Enabled Right Arrow on Paginator is not found");
				}
				if (enabledRightArrowFound) {
					do {
						try {
							homePage.testdisabledrightarrow.isDisplayed();
							disableRightArrowFound = true;
						} catch (Exception e) {
							System.out.println("Disabled Right Arrow on Paginator is not found");
						}
						if (doneWithThreeCircle) {
							circleList.get(2).click();
							Thread.sleep(500);
							for (int j = 0; j < homePage.testscheckboxlist.size(); j++) {
								Assert.assertTrue(homePage.testscheckboxlistwithinput.get(j).getAttribute("value").equals("true"));
							}
						} else {
							for (int i = 0; i < circleList.size(); i++) {
								circleList.get(i).click();
								Thread.sleep(500);
								for (int j = 0; j < homePage.testscheckboxlist.size(); j++) {
									Assert.assertTrue(homePage.testscheckboxlistwithinput.get(j).getAttribute("value").equals("true"));
								}
							}
							doneWithThreeCircle = true;
						}
						try {
							enabledRightArrow.click();
						} catch (Exception e) {
						}
					} while (!disableRightArrowFound);
				} else {
					for (int i = 0; i < circleList.size(); i++) {
						circleList.get(i).click();
						Thread.sleep(500);
						for (int j = 0; j < homePage.testscheckboxlist.size(); j++) {
							Assert.assertTrue(homePage.testscheckboxlistwithinput.get(j).getAttribute("value").equals("true"));
						}
					}
				}

			} else {
				for (int i = 0; i < homePage.testscheckboxlist.size(); i++) {
					Assert.assertTrue(homePage.testscheckboxlistwithinput.get(i).getAttribute("value").equals("true"));
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 18 Completed");
	}
	
	/**
	 * This method is used to check state of deselected test checkbox
	 * @throws Throwable
	 */
	@Then("^verify checkbox state as deselected while unchecked on checkbox\\.$")
	public void verify_checkbox_state_as_deselected_while_unchecked_on_checkbox() throws Throwable {
		try {
			//Deselecting all checkboxes
			homePage.allcheckbox.click();
			Thread.sleep(500);
			int count = 0, selectcheckbox = 0;
			count = homePage.testscheckboxlist.size();
			selectcheckbox = (int) (Math.random() * count);
			UtilityMethods.scrollPageDown(Driver.webdriver, selectcheckbox);
			homePage.testscheckboxlist.get(selectcheckbox).click();
			
			Verify.verify(homePage.testscheckboxlistwithinput.get(selectcheckbox).getAttribute("value").equals("true"));
			homePage.testscheckboxlist.get(selectcheckbox).click();
			Thread.sleep(500);
			Assert.assertEquals("false",(homePage.testscheckboxlistwithinput.get(selectcheckbox).getAttribute("value")));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 19 Completed");
	}
	
	/**
	 *This method used to verify the test tab header list of table with their respective tooltip 
	 * @param headerName
	 * @param toolTipText
	 * @throws Throwable
	 */
	@Then("^verify the elements within test list one beside the other with following elements \"([^\"]*)\" and tooltiptext \"([^\"]*)\"$")
	public void verify_the_elements_within_test_list_one_beside_the_other_with_following_elements_and_tooltiptext(String headerName, String toolTipText) throws Throwable {
		try {
			WebElement el = Driver.webdriver.findElement(By.xpath("//div[@class='test-results-header']/div/span[.='"+headerName+"']"));
			Actions builder = new Actions(Driver.webdriver);
			Action mouseOver = builder.moveToElement(el).build();
			mouseOver.perform();
			String ttt=Driver.webdriver.findElement(By.xpath("//div[@class='test-results-header']/div/span[.='"+headerName+"']/following-sibling::span[@class='tooltiptext']")).getText();
			Assert.assertTrue(ttt.equals(toolTipText));
			
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 20 Completed");
	}
	
	/**
	 * This method is  used to perfrom sorting using up and down arrow on test headers
	 * @throws Throwable
	 */
	@Then("^arrows with the elements within the test list should be sorted as follows$")
	public void arrows_with_the_elements_within_the_test_list_should_be_sorted_as_follows() throws Throwable {
		try {
			//clicking on name up arrow
			Actions actions = new Actions(Driver.webdriver);
			actions.moveToElement(homePage.nameuparrow).click().perform();
			Thread.sleep(500);
			List<String> listItem = new ArrayList<String>();
			
			for (int i = 0; i < homePage.testnameslist.size(); i++) {
				listItem.add(homePage.testnameslist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(listItem));
			listItem.clear();
			
			//clicking on no of result up arrow
			actions.moveToElement(homePage.noofresultuparrow).click().perform();
			Thread.sleep(500);
			
			for (int i = 0; i < homePage.testnoofresultlist.size(); i++) {
				listItem.add(homePage.testnoofresultlist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(listItem));
			listItem.clear();
			
			//clicking on earliest result up arrow
			actions.moveToElement(homePage.earliestdateuparrow).click().perform();
			Thread.sleep(500);
			
			for (int i = 0; i < homePage.earliestdatelist.size(); i++) {
				listItem.add(homePage.earliestdatelist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(listItem));
			listItem.clear();
			
			//clicking on latest result up arrow
			actions.moveToElement(homePage.latestdateuparrow).click().perform();
			Thread.sleep(500);
			
			for (int i = 0; i < homePage.latestdatelist.size(); i++) {
				listItem.add(homePage.latestdatelist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().isOrdered(listItem));
			listItem.clear();
			//TODO code is done for cliking on down arrow but not clicked actual ,so sorting not performing. 
			
			//clicking on name down arrow
			/*actions.moveToElement(homePage.namedownarrow).click().perform();
			Thread.sleep(500);
			
			for (int i = 0; i < homePage.testnameslist.size(); i++) {
				listItem.add(homePage.testnameslist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(listItem));*/
			//clicking on no. of Results down arrow
			//clicking on Earliest Date down arrow
			//clicking on Latest Date down arrow
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 21 Completed");
	}
	
	/**
	 * This is used to check by default earliest date list should be in descending order 
	 * @throws Throwable
	 */
	@Then("^verify earliest date is dispalying in descending order$")
	public void verify_earliest_date_is_dispalying_in_descending_order() throws Throwable {
		try {
			List<String> listItem = new ArrayList<String>();
			for (int i = 0; i < homePage.earliestdatelist.size(); i++) {
				listItem.add(homePage.earliestdatelist.get(i).getText());
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(listItem));
			listItem.clear();
			
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 28 Completed");
	}

	/**
	 * This method is used to verify the search bar on Test Tab, search for the test and select the searched test also verify the 'X' icon on search bar
	 * @throws Throwable
	 */
	@Then("^Searches anything what’s being typed in showing 'X' to cancel and displays them as options to select from below the search bar and filters the list\\.$")
	public void searches_anything_what_s_being_typed_in_showing_X_to_cancel_and_displays_them_as_options_to_select_from_below_the_search_bar_and_filters_the_list() throws Throwable {
		try {
			homePage.allcheckbox.click();
			Thread.sleep(500);
			homePage.searchbarontesttab.sendKeys(UtilityMethods.generateRandomString(1));
			Thread.sleep(500);
			Verify.verify(homePage.searchcancelontestsearchbar.isDisplayed());
			try {
				Verify.verify(homePage.norecordontestsearch.isDisplayed());
				homePage.searchcancelontestsearchbar.click();
			}
			catch(NoSuchElementException ne) {
				testcount=homePage.testnameslist.size();
				selectedTest=(int) (Math.random()*testcount);
				UtilityMethods.scrollPageDown(Driver.webdriver,selectedTest);
				Thread.sleep(500);
				homePage.testnameslist.get(selectedTest).click();	
			}
			Thread.sleep(500);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 29 Completed");
	}
	
	/**
	 * This method is used to test whether no impact on search result on test bar to total count of test
	 * @throws Throwable
	 */
	@Then("^search anything and get the result, the result count will not impact on no\\. of total tests$")
	public void search_anything_and_get_the_result_the_result_count_will_not_impact_on_no_of_total_tests() throws Throwable {
		try {
			homePage.allcheckbox.click();
			Thread.sleep(500);
			//Counting the total test
			totalTestCount=countTotalTest();
			homePage.searchbarontesttab.sendKeys(UtilityMethods.generateRandomString(1));
			Thread.sleep(500);
			Verify.verify(homePage.searchcancelontestsearchbar.isDisplayed());
			try {
				Verify.verify(homePage.norecordontestsearch.isDisplayed());
				homePage.searchcancelontestsearchbar.click();
			}
			catch(NoSuchElementException ne) {
				testcount=homePage.testnameslist.size();
	
			}
			Thread.sleep(500);
			Assert.assertTrue(Driver.webdriver.findElement(By.xpath("//span[@class='test-count' and contains(text(),"+totalTestCount+")]")).isDisplayed());
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
			System.out.println("Total Test Count is not matched with actual test count");
		}
		log.info("Scenario 30 Completed");
	}

	/**
	 * This method is used to count the total test names on test tab
	 * @return
	 */
	public static int countTotalTest() {
		HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
		try {
			boolean doneWithThreeCircle = false, disableRightArrowFound = false, enabledRightArrowFound = false,
					paginatorFound = false;
			WebElement enabledRightArrow = null;
			List<WebElement> circleList = null;
			try {
				homePage.testpaginator.isDisplayed();
				paginatorFound = true;
			} catch (Exception e) {
				System.out.println("Paginator Not Found");
			}
			if (paginatorFound) {
				try {
					circleList = homePage.testpaginationcirclelist;
					enabledRightArrow = homePage.testenabledrightarrow;
					enabledRightArrow.isDisplayed();
					enabledRightArrowFound = true;
				} catch (Exception e) {
					System.out.println("Enabled Right Arrow on Paginator is not found");
				}
				if (enabledRightArrowFound) {
					do {
						try {
							homePage.testdisabledrightarrow.isDisplayed();
							disableRightArrowFound = true;
						} catch (Exception e) {
							System.out.println("Disabled Right Arrow on Paginator is not found");
						}
						if (doneWithThreeCircle) {
							circleList.get(2).click();
							Thread.sleep(500);
							totalTestCount+=homePage.testscheckboxlist.size();
						} else {
							for (int i = 0; i < circleList.size(); i++) {
								circleList.get(i).click();
								Thread.sleep(500);
								totalTestCount+=homePage.testscheckboxlist.size();
							}
							doneWithThreeCircle = true;
						}
						try {
							enabledRightArrow.click();
						} catch (Exception e) {
						}
					} while (!disableRightArrowFound);
				} else {
					for (int i = 0; i < circleList.size(); i++) {
						circleList.get(i).click();
						Thread.sleep(500);
						totalTestCount+=homePage.testscheckboxlist.size();
					}
				}

			} else {
					totalTestCount+=homePage.testscheckboxlist.size();
				
			}
		}
		catch(Exception e) {
			UtilityMethods.processException(e);
		}
		return totalTestCount;
		
	} 
}
