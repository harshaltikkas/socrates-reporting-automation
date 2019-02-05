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
 * JAN 06 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.utils;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilityMethods {
    
	/**
	 * This method is used to scroll to element using mousehover 
	 * @param driver
	 * @param element
	 */
	public static void scrollToElement(WebDriver driver, WebElement element) {
		Actions builder = new Actions(driver);
		Action mouseOver = builder.moveToElement(element).build();
		mouseOver.perform();
	}

	/**
	 * This method used to page up
	 * @param driver
	 */
	public static void scrollPageUp(WebDriver driver) {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.PAGE_UP).build().perform();
	}

	/**
	 * This method used to arrow down till the no. of count
	 * @param driver
	 */
	public static void scrollPageDown(WebDriver driver, int count) {
		Actions builder = new Actions(driver);
		for (int i = 0; i < count; i++) {
			builder.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
	}
	
	/**
	 * This method is called when any exception occur while executing scenrios.
	 * @param e
	 */
	public static void processException(Exception e){
		CBTConfiguration.score = "fail";
		log.error(e.getMessage());
		Assert.fail();
	}

	/**
	 * This method is used to compare context header text with removing elipsis and universal seector options selection
	 * @param text
	 * @return
	 */
	public static String elipsisRemoval(String text){
		if(text.contains("...")) {
			text=text.substring(0, text.indexOf("."));
		}
		return text;
	}

	public static String generateRandomString(int strLength) {
		String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < strLength; i++) {
			int randIndex = random.nextInt(aToZ.length());
			stringBuilder.append(aToZ.charAt(randIndex));
		}
		return stringBuilder.toString();
	}

	public static long generateRandomNumber(int numberLength) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		// first not 0 digit
		sb.append(random.nextInt(9) + 1);
		for (int i = 0; i < numberLength - 1; i++) {
			sb.append(random.nextInt(10));
		}
		return Long.valueOf(sb.toString()).longValue();
	}
}
