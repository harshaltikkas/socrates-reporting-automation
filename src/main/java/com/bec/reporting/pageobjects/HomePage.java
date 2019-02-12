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
	/**
	 *This saction contains page object/webelemet of Universal Selector 
	 */
	@FindBy(xpath="//i[contains(text(),'chevron_right')]/ancestor::span[@class='float-right']")
	public WebElement openarrow;
	
	@FindBy(xpath="//i[contains(text(),'chevron_left')]/ancestor::span[@class='float-right']")
	public WebElement closearrow;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]")
	public WebElement schoolTitleOnSliderMenu;	
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'District Term')]")
	public WebElement districtNameOnSliderMenu;
	
	@FindBy(xpath="//div[@class='universal-test-search']//span//div//input[@placeholder='Search']")
	public WebElement searchbarontesttab;
	
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
	public WebElement rosterapplybtn;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]/ancestor::div[@class='menu-item']/following-sibling::div//button[contains(text(),'Apply')]")
	public WebElement testapplybtn;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Date')]/ancestor::div[@class='menu-item']/following-sibling::div//button[contains(text(),'Apply')]")
	public WebElement dateapplybtn;

	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Roster')]/ancestor::div[@class='menu-item']/following-sibling::div//div[contains(text(),'Cancel')]")
	public WebElement rostercancelbtn;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]/ancestor::div[@class='menu-item']/following-sibling::div//div[contains(text(),'Cancel')]")
	public WebElement testcancelbtn;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Date')]/ancestor::div[@class='menu-item']/following-sibling::div//div[contains(text(),'Cancel')]")
	public WebElement datecancelbtn;
	
	@FindBy(xpath="//label[@class='input-checkbox checkbox-lightBlue']")
	public WebElement allcheckbox;
	
	@FindBy(xpath="//div[@class='input-checkbox checkbox-lightBlue']")
	public List<WebElement> testscheckboxlist;
	
	@FindBy(xpath="//div[@class='input-checkbox checkbox-lightBlue']/input")
	public List<WebElement> testscheckboxlistwithinput;
	
	@FindBy(xpath="//div[@class='test-results-row-set']//div[@class='test-results-col-full']")
	public List<WebElement> testnameslist;
	
	@FindBy(xpath="//div[@class='test-results-row-set']/div[2]/div[3]")
	public List<WebElement> testnoofresultlist;
	
	@FindBy(xpath="//div[@class='test-results-row-set']/div[2]/div[4]")
	public List<WebElement> earliestdatelist;
	
	@FindBy(xpath="//div[@class='test-results-row-set']/div[2]/div[5]")
	public List<WebElement> latestdatelist;
	
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

	@FindBy(xpath="//div[@class='test-results-header']/div/span[.='Name']/following-sibling::span[@class='togglers']/*[.='expand_more']")
	public WebElement namedownarrow;
	
	@FindBy(xpath="//div[@class='test-results-header']/div/span[.='Name']/following-sibling::span[@class='togglers']/i[.='expand_less']")
	public WebElement nameuparrow;
	
	@FindBy(xpath="//div[@class='test-results-header']/div/span[.='Number of Test Results Available']/following-sibling::span[@class='togglers']/i[.='expand_more']")
	public WebElement noofresultdownarrow;
	
	@FindBy(xpath="//div[@class='test-results-header']/div/span[.='Number of Test Results Available']/following-sibling::span[@class='togglers']/i[.='expand_less']")
	public WebElement noofresultuparrow;

	@FindBy(xpath="//div[@class='test-results-header']/div/span[.='Earliest Date']/following-sibling::span[@class='togglers']/i[.='expand_more']")
	public WebElement earliestdatedownarrow;
	
	@FindBy(xpath="//div[@class='test-results-header']/div/span[.='Earliest Date']/following-sibling::span[@class='togglers']/i[.='expand_less']")
	public WebElement earliestdateuparrow;

	@FindBy(xpath="//div[@class='test-results-header']/div/span[.='Latest Date']/following-sibling::span[@class='togglers']/i[.='expand_more']")
	public WebElement latestdatedownarrow;
	
	@FindBy(xpath="//div[@class='test-results-header']/div/span[.='Latest Date']/following-sibling::span[@class='togglers']/i[.='expand_less']")
	public WebElement latestdateuparrow;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//input[@type='text' and @placeholder='Search']")
	public WebElement searchbaronschooldropdown;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//input[@type='text' and @placeholder='Search']")
	public WebElement searchbaronclassdropdown;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//input[@type='text' and @placeholder='Search']")
	public WebElement searchbaronstudentdropdown;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//div//span[@class='menu-varient-search-cancel']")
	public WebElement searchcancelonschooldropdown;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]/ancestor::div[@class='menu-item']/following-sibling::div//span[@class='menu-varient-search-cancel']")
	public WebElement searchcancelontestsearchbar;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//div//span[@class='menu-varient-search-cancel']")
	public WebElement searchcancelonclassdropdown;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//div//span[@class='menu-varient-search-cancel']")
	public WebElement searchcancelonstudentdropdown;
	
	/*Paginator of school,class , student and test*/
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//div[@class='selector-pagination']")
	public WebElement schoolpaginator;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//div[@class='selector-pagination']")
	public WebElement classpaginator;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//div[@class='selector-pagination']")
	public WebElement studentpaginator;
	
	@FindBy(xpath="//div[@class='menu-item']/following-sibling::div//div[@class='selector-pagination']")
	public WebElement testpaginator;	
	
	/*School pagination circle list,enable right arrow and disable right arrow*/
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//div[@class='PaginationBubblesAndCount']//ul[@class='m-0 page-circle-list']//span")
	public List<WebElement> schoolpaginationcirclelist;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//span[@class='scroll-right float-right scroll-disable']")
	public WebElement schooldisabledrightarrow;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div//span[@class='scroll-right scroll-active float-right']")
	public WebElement schoolenabledrightarrow;
	
	/*Class pagination circle list,enable right arrow and disable right arrow*/
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//div[@class='PaginationBubblesAndCount']//ul[@class='m-0 page-circle-list']//span")
	public List<WebElement> classpaginationcirclelist;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//span[@class='scroll-right float-right scroll-disable']")
	public WebElement classdisabledrightarrow;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//span[@class='scroll-right scroll-active float-right']")
	public WebElement classenabledrightarrow;
	
	/*Test pagination circle list,enable right arrow and disable right arrow*/
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]/ancestor::div[@class='menu-item']/following-sibling::div//div[@class='PaginationBubblesAndCount']//ul[@class='m-0 page-circle-list']//span")
	public List<WebElement> testpaginationcirclelist;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]/ancestor::div[@class='menu-item']/following-sibling::div//span[@class='scroll-right float-right scroll-disable']")
	public WebElement testdisabledrightarrow;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]/ancestor::div[@class='menu-item']/following-sibling::div//span[@class='scroll-right scroll-active float-right']")
	public WebElement testenabledrightarrow;
	
	@FindBy(xpath="//span[@class='menu-name' and contains(text(),'Test')]/ancestor::div[@class='menu-item']/following-sibling::div//span[@class='scroll-left float-left scroll-active ']")
	public WebElement testenabledleftarrow;
	
	/*Student pagination circle list,enable right arrow and disable right arrow*/
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//div[@class='PaginationBubblesAndCount']//ul[@class='m-0 page-circle-list']//span")
	public List<WebElement> studentpaginationcirclelist;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//span[@class='scroll-right float-right scroll-disable']")
	public WebElement studentdisabledrightarrow;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//span[@class='scroll-right scroll-active float-right']")
	public WebElement studentenabledrightarrow;
	

	/*Context Header Elements*/
	@FindBy(xpath="//div[@class='context-header-main']//li/span[@class='context-header-title']")
	public List<WebElement> contextheadertextlist;
	
	@FindBy(xpath="//div[@class='context-header-main']//li/span[@class='context-header-text']")
	public List<WebElement> contextheaderdatalist;
	
	@FindBy(xpath="//div[@class='context-header-main']//li/span[@class='context-header-text']/span[@class='tooltiptext']")
	public List<WebElement> contextheadertooltiplist;
	
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
	
	@FindBy(xpath="//div[@class='test-results-body']//div[.='No Records Found']")
	public WebElement norecordontestsearch;
	
	
	@FindBy(xpath="//div[@class='page-selectors-inr']//li[@class='active' and contains(text(),'Standards Performance')]")
	public WebElement activestandardperformancebtn;
	
	@FindBy(xpath="//div[@class='page-selectors-inr']//li[@class='active' and contains(text(),'Test Scores')]")
	public WebElement activetestscoresbtn;
	
	@FindBy(xpath="//div[@class='page-selectors-inr']//li[contains(text(),'Test Scores')]")
	public WebElement testscoresbtn;
	
	@FindBy(xpath="//div[@class='single-filter text-center active-filter']//div[contains(text(),'Class')]")
	public WebElement activeclass;
	
	@FindBy(xpath="//div[@class='single-filter text-center active-filter']//div[contains(text(),'Student')]")
	public WebElement activestudent;
	
	@FindBy(xpath="//div[@class='single-filter-text' and contains(text(),'Student')]/preceding-sibling::div")
	public WebElement studentmenu;
	
	@FindBy(xpath="//div[@class='single-filter-text' and contains(text(),'Class')]/preceding-sibling::div")
	public WebElement classmenu;
	
	@FindBy(xpath="//div[@class='page-selectors-inr']//li[contains(text(),'Standards Performance')]")
	public WebElement standardperformancebtn;
	
	@FindBy(xpath="//div[@class='footer-indicate-title' and contains(text(),'Achievement Levels')]")
	public WebElement achievmentlevelslabel;
	
	@FindBy(xpath="//div[@class='footer-key-bar grey']/ancestor::div[@class='footer-single-indicator']/following-sibling::div[@class='footer-single-indicator-title' and contains(text(),'All')]")
	public WebElement alallwithgraycolor;
	
	@FindBy(xpath="//div[@class='footer-key-bar red']/ancestor::div[@class='footer-single-indicator']/following-sibling::div[@class='footer-single-indicator-title' and contains(text(),'Below 40%')]")
	public WebElement albelow40withredcolor;
	
	@FindBy(xpath="//div[@class='footer-key-bar orange']/ancestor::div[@class='footer-single-indicator']/following-sibling::div[@class='footer-single-indicator-title' and contains(text(),'40-59%')]")
	public WebElement al40_59withorangecolor;
	
	@FindBy(xpath="//div[@class='footer-key-bar yellow']/ancestor::div[@class='footer-single-indicator']/following-sibling::div[@class='footer-single-indicator-title' and contains(text(),'60-79%')]")
	public WebElement al60_79withorangecolor;
	
	@FindBy(xpath="//div[@class='footer-key-bar green']/ancestor::div[@class='footer-single-indicator']/following-sibling::div[@class='footer-single-indicator-title' and contains(text(),'80%+')]")
	public WebElement al80pluswithorangecolor;
	
	@FindBy(xpath="//li[contains(text(),'Overview')]")
	public WebElement overviewtext;

	/*Footer Elements*/
	@FindBy(xpath="//div[@class='footer-bottom']")
	public WebElement footer;
	
	@FindBy(xpath="//div[@class='footer-right']//a")
	public WebElement footerrightside;
	
	@FindBy(xpath="//div[@class='footer-left']")
	public WebElement footerleftside;
	
	@FindBy(xpath="//div[@class='footer-key-btn']/span/i")
	public WebElement reportingkey;
}
