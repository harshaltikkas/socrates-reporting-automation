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
package com.bec.reporting.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IWait {

	/**
	 * This method is used to wait for the webpage specific time 
	 * @param time
	 * @return
	 */
	public static boolean implicit_wait(long time) {
		try {
			Driver.webdriver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * This method is used to wait for the element for in webpage
	 * @param driver
	 * @param el
	 * @return
	 */
	public static boolean explicit_wait(WebDriver driver, WebElement el) {
		try {
			new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class)
					.until(ExpectedConditions.visibilityOf(el));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
