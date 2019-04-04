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

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilityMethods {
	static int ctr = 0;
	/**
	 * This method is used to scroll to element using mousehover
	 * 
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
	 * 
	 * @param driver
	 */
	public static void scrollPageUp(WebDriver driver) {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.PAGE_UP).build().perform();
	}

	/**
	 * This method used to arrow down till the no. of count
	 * 
	 * @param driver
	 */
	public static void scrollPageDown(WebDriver driver, int count) {
		Actions builder = new Actions(driver);
		for (int i = 0; i < count; i++) {
			builder.sendKeys(Keys.ARROW_DOWN).build().perform();
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * This method is called when any exception occur while executing scenrios.
	 * 
	 * @param e
	 */
	public static void processException(Exception e) {
		CBTConfiguration.score = "fail";
		log.error(e.getMessage());
		Assert.fail();
	}

	/**
	 * This method is used to compare context header text with removing elipsis and
	 * universal seector options selection
	 * 
	 * @param text
	 * @return
	 */
	public static String elipsisRemoval(String text) {
		if (text.contains("...")) {
			text = text.substring(0, text.indexOf("."));
		}
		return text;
	}

	/**
	 * This method is used to generate random string based on the length of string u
	 * want.
	 * 
	 * @param strLength
	 * @return
	 */
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

	/**
	 * This method is used to generate randm number of the desire length
	 * 
	 * @param numberLength
	 * @return
	 */
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

	/**
	 * This method is used to compare the test score value with the range and return
	 * true if color match with the circle value
	 * 
	 * @param element
	 * @param scoreValue
	 * @return
	 */
	public static boolean verifyColorAndScoreOnLineChart(WebElement element, int scoreValue) {
		try {
			if (scoreValue < 40) {
				Assert.assertTrue(element.getAttribute("fill").equalsIgnoreCase("#FF5B5B"));
			} else if (scoreValue >= 40 && scoreValue <= 59) {
				Assert.assertTrue(element.getAttribute("fill").equalsIgnoreCase("#FF8E2D"));
			} else if (scoreValue >= 60 && scoreValue <= 79) {
				Assert.assertTrue(element.getAttribute("fill").equalsIgnoreCase("#FFC52D"));
			} else {
				Assert.assertTrue(element.getAttribute("fill").equalsIgnoreCase("#32AC41"));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method is used to verify the class attribute of the webelement with the
	 * score belong to it on student list.
	 * 
	 * @param element
	 * @param scoreValue
	 * @return
	 */
	public static boolean verifyColorAndScoreOnStudentList(WebElement element, int scoreValue) {
		try {
			if (scoreValue < 40) {
				Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase("student-list-grade red"));
			} else if (scoreValue >= 40 && scoreValue <= 59) {
				Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase("student-list-grade orange"));
			} else if (scoreValue >= 60 && scoreValue <= 79) {
				Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase("student-list-grade yellow"));
			} else {
				Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase("student-list-grade green"));
			}
			return true;
		} catch (Exception e) {
			processException(e);
			return false;
		}
	}

	/**
	 * This method is used to verify the date format is MM/dd/YYYY using reguler
	 * expression
	 * 
	 * @param submissionDate
	 */
	public static void checkDateFormat(String submissionDate) {
		try {
			String regex = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(submissionDate);
			Assert.assertTrue(matcher.matches());
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/*
	 * public static void printDateTime() { SimpleDateFormat formatter = new
	 * SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); Date date = new Date();
	 * System.out.println(formatter.format(date)); }
	 */

	/**
	 * This method is used to Veriy the Colour of the Standards records belong to
	 * that average percentage
	 * 
	 * @param element
	 * @param avgPer
	 * @return
	 */
	public static boolean verifyColorAndStandardAvgPercentage(WebElement element, int avgPer) {
		try {
			if (avgPer < 40) {
				Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase("redBg"));
			} else if (avgPer >= 40 && avgPer <= 59) {
				Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase("orangeBg"));
			} else if (avgPer >= 60 && avgPer <= 79) {
				Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase("yellowBg"));
			} else {
				Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase("greenBg"));
			}
			return true;
		} catch (Exception e) {
			processException(e);
			return false;
		}
	}

	/**
	 * This method is to verify the two List are reverse to each other.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isReverse(List<String> a, List<String> b) {
		if (a.size() != b.size()) // If a and b are not of the same length how can they be reverse?
			return false;
		for (int i = 0; i < a.size(); i++)
			if (!(a.get(i).equals(b.get(a.size() - i - 1)))) {
				return false;
			}
		return true;
	}

	/**
	 * This is recursive method used to search,on focus & click for the specific
	 * element till page end,if element found return true
	 * 
	 * @param standardList
	 * @return
	 */
	public static boolean searchandClickonELement(WebElement standardList, Model m, int standardIndex, String cat,
			String subcat, String desc, int strandIndex) {
		try {
			new Actions(Driver.webdriver)
					.moveToElement(Driver.webdriver.findElement(By.xpath("//li[contains(text(),'Overview')]"))).build()
					.perform();
			standardList.click();
			cat = Driver.webdriver.findElements(By.xpath(
					"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
							+ (strandIndex + 2)
							+ "]//li[not(contains(@class,'StandardsNotAvailable'))]//span[@class='strand_Definition']"))
					.get(standardIndex).getText();
			subcat = Driver.webdriver.findElements(By.xpath(
					"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
							+ (strandIndex + 2)
							+ "]//li[not(contains(@class,'StandardsNotAvailable'))]//span[@class='standard_sub_Definition']"))
					.get(standardIndex).getText();
			desc = Driver.webdriver.findElements(By.xpath(
					"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
							+ (strandIndex + 2)
							+ "]//li[not(contains(@class,'StandardsNotAvailable'))]//span[@class='strand_Description']"))
					.get(standardIndex).getText();

			Assert.assertTrue(cat.equals(m.getStandard_category()));
			Assert.assertTrue(subcat.equals(m.getStandard_subcategory()));
			Assert.assertTrue(desc.equals(m.getStandard_description()));
		} catch (Exception e) {
			UtilityMethods.scrollPageDown(Driver.webdriver, 1);
			searchandClickonELement(standardList, m, standardIndex, cat, subcat, desc, strandIndex);
		}
		return true;
	}
	
	/**
	 * This method is used to skip the specific index position while generating random number
	 * @param numberLength
	 * @return
	 */
	public static int generateRandomNumberBySkippingIndex(int numberLength) {
		int skipIndex = 7; // Number to exclude
		int number = (int) (Math.random() * numberLength);
		while (number == skipIndex) {
			number = (int) (Math.random() * numberLength);
		}
		return number;
	}

	
	/**
	 * This method is used to scroll down the div(vertical scroll bar) in Student,class and student Dropdown
	 * @param webelement
	 * @param scrollPoints
	 * @return
	 */
	public static boolean scroll_Div(WebElement webelement, int scrollPoints) {
		try {
			Actions dragger = new Actions(Driver.webdriver);
			if (ctr >= 1) {
				scrollPageDown(Driver.webdriver, 2);
			}
			ctr++;
			dragger.moveToElement(webelement).clickAndHold().moveByOffset(0, scrollPoints).build().perform();
			Thread.sleep(500);
			if(webelement.getText().equals("")) {
				scroll_Div(webelement, scrollPoints);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		ctr=0;
		return true;
	}
}
