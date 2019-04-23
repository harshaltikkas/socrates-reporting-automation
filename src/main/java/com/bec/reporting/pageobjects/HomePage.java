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
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/span[@class='data-refresh']//i[@class='material-icons' and contains(text(),'autorenew')]")
	public WebElement classRefreshIcon;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/span[@class='data-refresh']//i[@class='material-icons' and contains(text(),'autorenew')]")
	public WebElement studentRefreshIcon;
	
	@FindBy(xpath="//input[@name='username']")
	public WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement password;
	
	@FindBy(xpath="//*[@class='field selectBox']")
	public WebElement usertypedropdown;
	
	@FindBy(xpath="//input[@id='login']")
	public WebElement loginbtn;
	
	@FindBy(xpath="//div[.='Class']//span[@class='data-refresh']//i[@class='material-icons' and contains(text(),'autorenew')]")
	public WebElement classdrpdwnrefreshicon;
	
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
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'School')]/following-sibling::div/button//div[@class='bec_tooltip_content']")
	public WebElement schooldropdownbtntooltip;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div/button")
	public WebElement classdropdownbtn;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div/button//div[@class='bec_tooltip_content']")
	public WebElement classdropdownbtntooltip;
	
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
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Class')]/following-sibling::div//div[@class='menu-dropdown-list-inr']/ul//div[contains(@style,'position: relative; display: block; width: 100%; cursor: pointer; border-radius: inherit; background-color: rgba(0, 0, 0, 0.2); height: 139px; transform: translateY(0px);')]")
	public WebElement classlistScroll;
	
	@FindBy(xpath="//div[@class='menu-title' and contains(text(),'Student')]/following-sibling::div//div[@class='menu-dropdown-list-inr']//ul//div/li")
	public List<WebElement> studentlistondropdown;
	
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
	
	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'School:')]/following-sibling::span//div[@class='bec_tooltip_content']")
	public WebElement tooltipofschoolnameoncontextheader;
	
	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'Class:')]/following-sibling::span")
	public WebElement classnameoncontextheader;
	
	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'Class:')]/following-sibling::span//div[@class='bec_tooltip_content']")
	public WebElement tooltipofclassnameoncontextheader;

	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'Student:')]/following-sibling::span")
	public WebElement studentnameoncontextheader;
	
	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'Student:')]/following-sibling::span//div[@class='bec_tooltip_content']")
	public WebElement studentnameoncontextheadertooltiptext;
	
	@FindBy(xpath="//div[@class='context-header-main']//span[contains(text(),'Tests:')]/following-sibling::span//div[@class='bec_tooltip_content']")
	public WebElement tooltipoftestnameoncontextheader;
	
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
	public WebElement activestudentmenu;
	
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
	public WebElement al60_79withyellowcolor;
	
	@FindBy(xpath="//div[@class='footer-key-bar green']/ancestor::div[@class='footer-single-indicator']/following-sibling::div[@class='footer-single-indicator-title' and contains(text(),'80%+')]")
	public WebElement al80pluswithgreencolor;
	
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
	
	/**
	 * Xpath list for Line_Chart_Test_Scores_vs_Time
	 */
	
	@FindBy(xpath="//*[name()='text' and @class='vx-axis-label y---axis']/*[name()='tspan']")
	public WebElement yaxistexton_linechart;
	
	@FindBy(xpath="//*[name()='line' and @class='vx-line' and @stroke-width='1']")
	public List<WebElement> horizontalline_onlinechart;
	
	@FindBy(xpath="//*[name()='line' and @class='vx-line']/following-sibling::*[name()='svg']//*[name()='tspan']")
	public List<WebElement> yaxislabelsonhorizontalline_onlinechart;
	
	@FindBy(xpath="//div[@class='Test_names_label']")
	public WebElement xaxistexton_linechart;
	
	@FindBy(xpath="//div[@class='test-results-pagination']")
	public WebElement paginator_onlinechart;
	
	@FindBy(xpath="//div[@class='table-and-graph-pagination-inr']//ul[@class='page-circle-list']//span")
	public List<WebElement> paginationcirclelist_onlinechart;
	
	@FindBy(xpath="//div[@class='test-results-pagination']//span[@class='scroll-left float-left scroll-active ']/i[.='chevron_left']")
	public WebElement enabledleftarrow_onlinechart;
	
	@FindBy(xpath="//div[@class='test-results-pagination']//span[@class='scroll-left float-left scroll-disable']/i[.='chevron_left']")
	public WebElement disabledleftarrow_onlinechart;

	@FindBy(xpath="//div[@class='line_chart_graph']//*[name()='g' and @class='vx-group vx-axis-tick custom_tick']//*[name()='text']")
	public List<WebElement> testNamesonPerPage_onlinechart;
	
	@FindBy(xpath="//div[@class='graph_tooltip_content']")
	public WebElement testNametooltip_onlinechart;
	
	@FindBy(xpath="//div[@class='line_chart_graph']/following-sibling::div[@class='test-results-pagination']//ul[@class='page-circle-list']//span[@style]")
	public WebElement disabledpaginationcircle_onlinechart;

	@FindBy(xpath="//*[name()='g']//*[name()='circle' and @fill]/following-sibling::*[name()='text']")
	public List<WebElement> testScoresonPerPage_onlinechart;
	
	@FindBy(xpath="//*[name()='g']//*[name()='circle' and @fill]")
	public List<WebElement> testScoreCircleClronPerPage_onlinechart;

	@FindBy(xpath="//div[@class='tooltip_row_cmp_percentage']")
	public WebElement tooltiprowpercent_onlinechart;
	
	@FindBy(xpath="//div[@class='overview-table-left-inner text-rotate']")
	public WebElement yaxislabelonstndrdperformanceinclass;

	@FindBy(xpath="//div[@class='performance-indicator']/ancestor::div[@class='overview-table-col greenBg']/preceding-sibling::div/span")
	public WebElement achlvl80ormorewithgreenclr;
	
	@FindBy(xpath="//div[@class='performance-indicator']/ancestor::div[@class='overview-table-col yellowBg']/preceding-sibling::div/span")
	public WebElement achlvl60_79withyellowclr;
	
	@FindBy(xpath="//div[@class='performance-indicator']/ancestor::div[@class='overview-table-col orangeBg']/preceding-sibling::div/span")
	public WebElement achlvl40_59withorangeclr;
	
	@FindBy(xpath="//div[@class='performance-indicator']/ancestor::div[@class='overview-table-col redBg']/preceding-sibling::div/span")
	public WebElement achlvlbelow40withredclr;
	
	@FindBy(xpath="//span[@class='greenColor']/i[@class='material-icons' and contains(text(),'arrow_drop_up')]")
	public WebElement greenuparrow;
	
	@FindBy(xpath="//span[@class='redColor']/i[@class='material-icons' and contains(text(),'arrow_drop_up')]")
	public WebElement reduparrow;
	
	@FindBy(xpath="//span[@class='redColor']/i[@class='material-icons' and contains(text(),'arrow_drop_down')]")
	public WebElement reddownarrow;
	
	@FindBy(xpath="//div[@class='overview-table-col' and @style]//div[contains(@class,'overview-table-col-inr-grd')]")
	public List<WebElement> strandnameslist;
	
	@FindBy(xpath="//div[@class='overview-table-col']/div[@class='overview-table-innr-grd-col-head-guard']//div[@class='bec_tooltip_content']")
	public WebElement strandnamestooltip;
	
	@FindBy(xpath="//div[@class='overview-table-col']//li[not(contains(@class,'StandardsNotAvailable'))]")
	public List<WebElement> standardnameslist;
	
	@FindBy(xpath="//div[@class='overview-table-col']/div[@class='overview-table-innr-grd-col-head-guard']/div[2]")
	public List<WebElement> groupingstripclrlist;
	
	@FindBy(xpath="//div[@class='overview-table-bar avarage-bar']/div[@class='overview-table-col' and @style]")
	public List<WebElement> averagelist;
	
	@FindBy(xpath="//div[@class='overview-table-col']/div[@class='overview-table-innr-grd-col-head-guard']//div[@class='bec_tooltip_content']")
	public List<WebElement> strandtooltiplist;
	
	@FindBy(xpath="//span[@class='scroll-left scroll-active']/i[contains(text(),'chevron_right')]")
	public WebElement enabledrightarrow;
	
	@FindBy(xpath="//i[@class='material-icons' and contains(text(),'show_chart')]")
	public WebElement performanceovrtimeicon;
	
	@FindBy(xpath="//i[@class='material-icons' and contains(text(),'show_chart')]/ancestor::span/following-sibling::span[@class='sidewidget-header-title']")
	public WebElement performanceovrtimeheader;
	
	@FindBy(xpath="//span[@class='infoIconBlock']")
	public WebElement infoicononperformanceovrtimeheader;
	
	@FindBy(xpath="//div[@class='infoIconTooltipBlockInr']")
	public WebElement tooltip;
	
	@FindBy(xpath="//div[@class='float-left widget-base-block-title text-center']/span")
	public WebElement defaultstrandnameinpotchart;
	
	@FindBy(xpath="//*[contains(text(),'Test Scores over Time')]/ancestor::div[@class='class_widget_title test_score_overtime']/following-sibling::div/div[@class='line_chart_graph']")
	public WebElement testscoreovertimelinechart;
	
	@FindBy(xpath="//div[@class='student-list-body']/div[@class='student-list-row']")
	public List<WebElement> noofstudentsinlist;	
	
	@FindBy(xpath="//*[name()='tspan' and contains(text(),'Test Scores (%)')]")
	public WebElement testscoreovertimetext;
	
	@FindBy(xpath="//div[@class='tooltip_main']//div[@class='tooltip_title']")
	public WebElement testnameontooltip;
	
	@FindBy(xpath="//div[@class='tooltip_main']//div[@class='tooltip_submitted_date']")
	public WebElement submitteddateontooltip;
	
	@FindBy(xpath="//div[@class='tooltip_main']//div[@class='tooltip_questions_right']/span")
	public List<WebElement> questionlistontooltip;
	
	@FindBy(xpath="//div[@class='tooltip_main']//div[@class='tooltip_questions_right']")
	public WebElement questionlistarea;
	
	@FindBy(xpath="//*[name()='g']//*[name()='circle']/following-sibling::*[name()='text']")
	public List<WebElement> testScoreValueInCircle_onlinechart;
	
	@FindBy(xpath="//span[@class='progress-text' and contains(text(),'All')]/preceding-sibling::span[@class='progress-number grey']")
	public WebElement textingraystripinstudentlist;
	
	@FindBy(xpath="//span[@class='progress-text' and contains(text(),'< 40%')]/preceding-sibling::span[@class='progress-number red']")
	public WebElement textinredstripinstudentlist;
	
	@FindBy(xpath="//span[@class='progress-text' and contains(text(),'40-59%')]/preceding-sibling::span[@class='progress-number orange']")
	public WebElement textinorangestripinstudentlist;
	
	@FindBy(xpath="//span[@class='progress-text' and contains(text(),'60-79%')]/preceding-sibling::span[@class='progress-number yellow']")
	public WebElement textinyellowstripinstudentlist;
	
	@FindBy(xpath="//span[@class='progress-text' and contains(text(),'80% ≥')]/preceding-sibling::span[@class='progress-number green']")
	public WebElement textingreenstripinstudentlist;
	
	@FindBy(xpath="//span[@class='progress-number grey']/following-sibling::span[@class='progress-text']")
	public WebElement textoutgraystripinstudentlist;
	
	@FindBy(xpath="//span[@class='progress-number red']/following-sibling::span[@class='progress-text']")
	public WebElement textoutredstripinstudentlist;
	
	@FindBy(xpath="//span[@class='progress-number orange']/following-sibling::span[@class='progress-text']")
	public WebElement textoutorangestripinstudentlist;
	
	@FindBy(xpath="//span[@class='progress-number yellow']/following-sibling::span[@class='progress-text']")
	public WebElement textoutyellowstripinstudentlist;
	
	@FindBy(xpath="//span[@class='progress-number green']/following-sibling::span[@class='progress-text']")
	public WebElement textoutgreenstripinstudentlist;
	
	@FindBy(xpath="//div[@class='class_test_overview_table_progress_bar_list']/ul/li")
	public List<WebElement> colouredStripOnStudentList;
	
	@FindBy(xpath="//div[@class='class_test_overview_table_progress_bar_list']/ul/li/span[contains(@class,'progress-number')]")
	public List<WebElement> TextInStripOnStudentList;
	
	@FindBy(xpath="//div[@class='selector-pagination']/div[@class='PaginationBubblesClassTestScoreDetailTable mx-auto']")
	public WebElement paginator_onstudentlist;
	
	@FindBy(xpath="//div[@class='selector-pagination']/div[@class='PaginationBubblesClassTestScoreDetailTable mx-auto']//div//ul[@class='page-circle-list']//span")
	public List<WebElement> studentlistpaginationcirclelist;
	
	@FindBy(xpath="//div[@class='selector-pagination']/div[@class='PaginationBubblesClassTestScoreDetailTable mx-auto']//div/following-sibling::span[@class='scroll-right scroll-active float-right']/i")
	public WebElement studentlistenabledrightarrow;
	
	@FindBy(xpath="//div[@class='selector-pagination']/div[@class='PaginationBubblesClassTestScoreDetailTable mx-auto']//div/following-sibling::span[@class='scroll-right float-right scroll-disable']/i")
	public WebElement studentlistdisabledrightarrow;
	
	@FindBy(xpath="//div[@class='selector-pagination']/div[@class='PaginationBubblesClassTestScoreDetailTable mx-auto']/span[@class='scroll-left float-left scroll-active ']/i")
	public WebElement studentlistenabledleftarrow;
	
	@FindBy(xpath="//div[@class='student-list-body']")
	public WebElement studentlist;
	
	@FindBy(xpath="//span[@class='sidewidget-header-title' and contains(text(),'Student List')]/preceding-sibling::span[@class='sidewidget-header-icon']")
	public WebElement studentlisticon;
	
	@FindBy(xpath="//div[@class='standard_perfom_tooltip']//span[@class='strand_Definition']")
	public List<WebElement> stranddefinitionlist;
	
	@FindBy(xpath="//div[@class='standard_perfom_tooltip']//span[@class='standard_sub_Definition']")
	public List<WebElement> standardsubdefinitionlist;
	
	@FindBy(xpath="//div[@class='standard_perfom_tooltip']//span[@class='strand_Description']")
	public List<WebElement> standarddescriptionlist;
	
	@FindBy(xpath="//div[@class='widget-base-sub-title text-center']/preceding-sibling::div[2]/span")
	public WebElement studentlistheader;
	
	@FindBy(xpath="//div[@class='widget-base-sub-title text-center']/preceding-sibling::div[1]/span")
	public WebElement studentlistsubheader;
	
	@FindBy(xpath="//div[@class='class_test_overview_table_header_left']")
	public WebElement selectedTestName;
	
	@FindBy(xpath="//div[@class='class_test_overview_table_header_right']/span")
	public WebElement selectedTestSubmittedDate;
	
	@FindBy(xpath="//div[@class='class_test_overview_table_header_left']//div[@class='bec_tooltip_content']")
	public WebElement tooltipofselectedTest;
	
	@FindBy(xpath="//span[@class='student-list-col-head-name' and contains(text(),'Students')]")
	public WebElement StudentRecordsSize;
	
	@FindBy(xpath="//div[@class='student-list-body']/div[@class='student-list-row']/div[@class='student-list-col' and @style]")
	public List<WebElement> studentnameslistinstudentlist;
	
	@FindBy(xpath="//div[@class='student-list-body']/div[@class='student-list-row']/div[@class='student-list-col' and not(@style)][1]")
	public List<WebElement> noofquestionsorsubmitdatelistinstudentlist;
	
	@FindBy(xpath="//div[@class='student-list-body']/div[@class='student-list-row']/div[@class='student-list-col']/span")
	public List<WebElement> scorelistinstudentlist;
	
	@FindBy(xpath="//div[@class='student-list-body']/div[@class='student-list-row']/div[@class='student-list-col']/span")
	public List<WebElement> studentscorelistinstudentlist;
	
	@FindBy(xpath="//span[@class='student-list-col-head-name active-student-list-col-head-name' and contains(text(),'Students')]/following-sibling::span/i[@class='material-icons' and contains(text(),'expand_less')]")
	public WebElement studentListnameuparrow;
	
	@FindBy(xpath="//span[@class='student-list-col-head-name' and contains(text(),'Students')]/following-sibling::span//i[contains(text(),'expand_more')]")
	public WebElement studentListnamedownarrow;
	
	@FindBy(xpath="//span[@class='student-list-col-head-name active-student-list-col-head-name' and contains(text(),'Number of Questions')]/following-sibling::span/i[@class='material-icons' and contains(text(),'expand_less')]")
	public WebElement studentListquestionuparrow;
	
	@FindBy(xpath="//span[@class='student-list-col-head-name' and contains(text(),'Number of Questions')]/following-sibling::span//i[contains(text(),'expand_more')]")
	public WebElement studentListquestiondownarrow;
	
	@FindBy(xpath="//span[@class='student-list-col-head-name active-student-list-col-head-name' and contains(text(),'Score')]/following-sibling::span/i[@class='material-icons' and contains(text(),'expand_less')]")
	public WebElement studentListscoreuparrow;
	
	@FindBy(xpath="//span[@class='student-list-col-head-name' and contains(text(),'Score')]/following-sibling::span//i[contains(text(),'expand_more')]")
	public WebElement studentListscoredownarrow;

	@FindBy(xpath="//div[@class='Test_names_label']")
	public WebElement testNamesLabelOnLineChart;
}
