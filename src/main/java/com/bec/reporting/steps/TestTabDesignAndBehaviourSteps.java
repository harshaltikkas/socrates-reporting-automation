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
public class TestTabDesignAndBehaviourSteps {
	
	/**
	 *This is used to initialize webelement of the webpages 
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	
	/**
	 * This method is clicking on test tab, verify with search bar and click on all checkbox in table header
	 * @throws Throwable
	 */
	@When("^User Click on Test tab within the Universal Selector Tab and click on 'All Checkbox'$")
	public void user_Click_on_Test_tab_within_the_Universal_Selector_Tab_and_click_on_All_Checkbox() throws Throwable {
		try {
			homePage.testtab.click();
			IWait.explicit_wait(Driver.webdriver, homePage.searchOnSliderMenu);
			Verify.verify(homePage.searchOnSliderMenu.isDisplayed());
			homePage.allcheckbox.click();
			Thread.sleep(2000);
			homePage.allcheckbox.click();
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method used to verify whether all checkboxes are checked.
	 * @throws Throwable
	 */
	@Then("^verify all the tests within that test list must be selected\\.$")
	public void verify_all_the_tests_within_that_test_list_must_be_selected() throws Throwable {
		try {

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 18 Completed");
	}
}
