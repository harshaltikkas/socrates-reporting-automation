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
package com.bec.reporting.pageobjects;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	@FindBy(xpath="//i[contains(text(),'chevron_right')]/ancestor::span[@class='float-right']")
	public WebElement openarrow;
	
	@FindBy(xpath="//i[contains(text(),'chevron_left')]/ancestor::span[@class='float-right']")
	public WebElement closearrow;
	
	@FindBy(xpath="//li[contains(text(),'Overview')]")
	public WebElement overviewtext;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]")
	public WebElement schoolTitleOnSliderMenu;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'District Term')]")
	public WebElement districtNameOnSliderMenu;
	
	@FindBy(xpath="//div[@class='universal-test-search']//span//div//input[@placeholder='Search']")
	public WebElement searchOnSliderMenu;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Roster')]")
	public WebElement rostertab;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]")
	public WebElement testtab;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Date')]")
	public WebElement datetab;

	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div/button")
	public WebElement schooldropdownbtn;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div/button")
	public WebElement classdropdownbtn;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div/button")
	public WebElement studentdropdownbtn;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'District Term')]/following-sibling::div/button")
	public WebElement districttermdropdownbtn;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Roster')]/ancestor::div[@class='menu-item']/following-sibling::div//button[contains(text(),'Apply')]")
	public WebElement rosterapplyfilterbtn;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]/ancestor::div[@class='menu-item']/following-sibling::div//button[contains(text(),'Apply')]")
	public WebElement testapplyfilterbtn;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Date')]/ancestor::div[@class='menu-item']/following-sibling::div//button[contains(text(),'Apply')]")
	public WebElement dateapplyfilterbtn;

	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Roster')]/ancestor::div[@class='menu-item']/following-sibling::div//div[contains(text(),'Cancel')]")
	public WebElement rostercancelbtn;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]/ancestor::div[@class='menu-item']/following-sibling::div//div[contains(text(),'Cancel')]")
	public WebElement testcancelbtn;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Date')]/ancestor::div[@class='menu-item']/following-sibling::div//div[contains(text(),'Cancel')]")
	public WebElement datecancelbtn;
	
	@FindBy(xpath="//label[@class='input-checkbox checkbox-lightBlue']")
	public WebElement allcheckbox;
	
	@FindBy(xpath="//div[@class='input-checkbox checkbox-lightBlue']")
	public List<WebElement> testnamecheckboxlist;
	
	@FindBy(xpath="//div[@class='test-results-col-full']")
	public List<WebElement> testnameslist;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'District Term')]/following-sibling::div//ul/li")
	public List<WebElement> districttermlist;
	
	@FindBy(xpath="//span[@class='scroll-right scroll-active float-right']/i")
	public WebElement rightarrowofpaginationontesttab;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//li")
	public List<WebElement> schoollist;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//li")
	public List<WebElement> classlist;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//div[@class='menu-dropdown-list-inr']//ul//div/li")
	public List<WebElement> studentlist;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//div[@class='menu-dropdown-list-inr']//ul//li")
	public List<WebElement> studentalllist;

	/*Context Header Elements*/
	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'School:')]/following-sibling::span")
	public WebElement schoolnameoncontextheader;
	
	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'Class:')]/following-sibling::span")
	public WebElement classnameoncontextheader;

	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'Student:')]/following-sibling::span")
	public WebElement studentnameoncontextheader;
	
	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'Tests:')]/following-sibling::span")
	public WebElement nooftestoncontextheader;
	
	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'Dates:')]/following-sibling::span")
	public WebElement datesoncontextheader;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//input[@type='text' and @placeholder='Search']")
	public WebElement searchbaronschooldropdown;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//input[@type='text' and @placeholder='Search']")
	public WebElement searchbaronclassdropdown;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//input[@type='text' and @placeholder='Search']")
	public WebElement searchbaronstudentdropdown;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//div//span[@class='menu-varient-search-cancel']")
	public WebElement searchcancelonschooldropdown;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//div//span[@class='menu-varient-search-cancel']")
	public WebElement searchcancelonclassdropdown;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//div//span[@class='menu-varient-search-cancel']")
	public WebElement searchcancelonstudentdropdown;
	
	/*Footer Elements*/
	@FindBy(xpath="//div[@class='footer-key-btn']/span/i")
	public WebElement footerkey;
	

}
