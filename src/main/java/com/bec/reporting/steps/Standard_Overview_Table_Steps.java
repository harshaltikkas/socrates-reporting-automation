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
 * Mar 01 2019		: BEC         : CREATED.
 * ------------------------------------------------------------------------
 *
 * ========================================================================
 */
package com.bec.reporting.steps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.bec.reporting.pageobjects.HomePage;
import com.bec.reporting.utils.CBTConfiguration;
import com.bec.reporting.utils.DatabaseConnection;
import com.bec.reporting.utils.Driver;
import com.bec.reporting.utils.Model;
import com.bec.reporting.utils.PaginationUtility;
import com.bec.reporting.utils.UtilityMethods;
import com.google.common.collect.Ordering;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Standard_Overview_Table_Steps {

	/**
	 * This is used to initialize webelement of the webpages
	 */
	HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
	public static boolean performanceMenuClicked = false,underClassContext=false,testScoreMenuClicked=false,underStudentContext=false;
	static String headerOnToolTip, subHeaderOnToolTip;
	
	public static void resetStatus() {
		Standard_Overview_Table_Steps.performanceMenuClicked = false;
		Standard_Overview_Table_Steps.underClassContext = false;
		Standard_Overview_Table_Steps.testScoreMenuClicked = false;
		Standard_Overview_Table_Steps.underStudentContext = false;
	}
	/**
	 * This method is used to click on standard performance tab in class context
	 * 
	 * @throws Throwable
	 */
	@When("^User Click on Standard Performance tab within the Class Context$")
	public void user_Click_on_Standard_Performance_tab_within_the_Class_Context() throws Throwable {
		try {
			JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;
			Thread.sleep(3000);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			Thread.sleep(1000);
			try {
				Assert.assertTrue(homePage.activeclassmenu.getAttribute("class").contains("active"));
			}
			catch(Exception e) {
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", homePage.classmenu);
				Thread.sleep(3000);
			}
			try {
				Assert.assertTrue(homePage.activestandardperformancebtn.getAttribute("class").contains("active"));
			}
			catch(Exception e) {
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", homePage.standardperformancebtn);
				Thread.sleep(3000);
			}
			performanceMenuClicked = true;
			underClassContext=true;
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method is used to verify the Y-Axis Label Text as given
	 * 
	 * @param yaxisText
	 * @throws Throwable
	 */
	@Then("^The Y-axis of the table should be labeled as \"([^\"]*)\"$")
	public void the_Y_axis_of_the_table_should_be_labeled_as(String yaxisText) throws Throwable {
		try {
			Assert.assertTrue(homePage.yaxislabelonstndrdperformanceinclass.getText().equals(yaxisText));
			CBTConfiguration.score = "pass";
		}
		catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 36_1 completed");
	}

	/**
	 * This method is used to verify the achievement level grouping percentage with
	 * associated colour on standard performance
	 * 
	 * @throws Throwable
	 */
	@Then("^The Y-axis should have the groupings strip of colours with the respective ranges in %$")
	public void the_Y_axis_should_have_the_groupings_strip_of_colours_with_the_respective_ranges_in() throws Throwable {
		try {
			Assert.assertTrue(homePage.achlvl80ormorewithgreenclr.getText().equals("≥ 80%"));
			Assert.assertTrue(homePage.achlvl60_79withyellowclr.getText().equals("60-79%"));
			Assert.assertTrue(homePage.achlvl40_59withorangeclr.getText().equals("40-59%"));
			Assert.assertTrue(homePage.achlvlbelow40withredclr.getText().equals("< 40%"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 36_2 completed");
	}

	/**
	 * This Method is used to check the achievment level green_red arrow sorting and
	 * also check the flipping of data in table based on arrow clicked
	 * 
	 * @throws Throwable
	 */
	@Then("^user should able to see sort arrows with the red and green colours and when the user clicks on that arrow sorting should happen$")
	public void user_should_able_to_see_sort_arrows_with_the_red_and_green_colours_and_when_the_user_clicks_on_that_arrow_sorting_should_happen()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.greenuparrow.isDisplayed());
			Assert.assertTrue(homePage.reddownarrow.isDisplayed());

			WebElement rightArrowEnable = null;
			boolean enabledRightArrowFound = false;
			List<String> reverseStandardList = new ArrayList<>();
			List<String> originalStandardList = new ArrayList<>();
			List<WebElement> elList;
			do {
				if (enabledRightArrowFound) {
					new Actions(Driver.webdriver)
							.moveToElement(homePage.strandnameslist.get(homePage.strandnameslist.size() - 1)).click()
							.build().perform();
					Thread.sleep(3000);
					new Actions(Driver.webdriver).moveToElement(homePage.reddownarrow).click().build().perform();
					Thread.sleep(2000);
					elList = Driver.webdriver.findElements(By.xpath(
							"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
									+ ((homePage.strandnameslist.size() - 1) + 2)
									+ "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
					for (int j = 0; j < elList.size(); j++) {
						reverseStandardList.add(elList.get(j).getText());
					}
					Thread.sleep(1000);
					JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;
					js.executeScript("arguments[0].click();", homePage.reduparrow);
					Thread.sleep(2000);
					elList = Driver.webdriver.findElements(By.xpath(
							"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
									+ ((homePage.strandnameslist.size() - 1) + 2)
									+ "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
					for (int j = 0; j < elList.size(); j++) {
						originalStandardList.add(elList.get(j).getText());
					}
					Assert.assertTrue(UtilityMethods.isReverse(originalStandardList, reverseStandardList));
					originalStandardList.clear();
					reverseStandardList.clear();
				} else {
					for (int i = 0; i < homePage.strandnameslist.size(); i++) {
						new Actions(Driver.webdriver).moveToElement(homePage.strandnameslist.get(i)).click().build()
								.perform();
						Thread.sleep(3000);
						new Actions(Driver.webdriver).moveToElement(homePage.reddownarrow).click().build().perform();
						Thread.sleep(2000);
						elList = Driver.webdriver.findElements(By.xpath(
								"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
										+ (i + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
						for (int j = 0; j < elList.size(); j++) {
							reverseStandardList.add(elList.get(j).getText());
						}
						Thread.sleep(1000);
						JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;
						js.executeScript("arguments[0].click();", homePage.reduparrow);
						Thread.sleep(2000);
						elList = Driver.webdriver.findElements(By.xpath(
								"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
										+ (i + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
						for (int j = 0; j < elList.size(); j++) {
							originalStandardList.add(elList.get(j).getText());
						}
						Assert.assertTrue(UtilityMethods.isReverse(originalStandardList, reverseStandardList));
						originalStandardList.clear();
						reverseStandardList.clear();
					}
				}
				try {
					rightArrowEnable = homePage.enabledrightarrow;
					rightArrowEnable.isEnabled();
					enabledRightArrowFound = true;
					rightArrowEnable.click();
					Thread.sleep(500);
				} catch (Exception e) {
					enabledRightArrowFound = false;
				}

			} while (enabledRightArrowFound);
			CBTConfiguration.score = "pass";

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 36_3 completed");
	}

	/**
	 * This method is used to verify when click on standard then colour should be
	 * changed to the belonging color of achivmenet level
	 * 
	 * @throws Throwable
	 */
	
	@Then("^click the standard and verify The colour should be changed to that of the achievement level where that standard is present$")
	public void click_the_standard_and_verify_The_colour_should_be_changed_to_that_of_the_achievement_level_where_that_standard_is_present()
			throws Throwable {
		try {
			WebElement rightArrowEnable = null;
			boolean enabledRightArrowFound = false;
			List<Model> lm=new ArrayList<Model>();
			List<WebElement> standardList;
			Integer schoolId = 0, classId=0;
			String cat="", subcat ="",desc="",strandName;
			do {
				Map<Integer,Integer> ids=UtilityMethods.getSchoolIdAndClassId();
				for(Map.Entry<Integer,Integer> entry:ids.entrySet()) {
					schoolId=entry.getKey();
					classId=entry.getValue();
				}
			
				if (enabledRightArrowFound) {
					int lastStrandIndex = homePage.strandnameslist.size() - 1;
					new Actions(Driver.webdriver).moveToElement(homePage.strandnameslist.get(lastStrandIndex)).click().build()
							.perform();
					Thread.sleep(3000);
					standardList = Driver.webdriver.findElements(By.xpath(
							"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
									+ (lastStrandIndex + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
					if (homePage.strandnameslist.get(lastStrandIndex).getText().contains("...")) {
						strandName = homePage.strandnamestooltip.getText();
					} else {
						strandName = homePage.strandnameslist.get(lastStrandIndex).getText();
					}
					lm=DatabaseConnection.getStandardAvgPerListInClassContext(DatabaseConnection.conn, schoolId, classId,
							strandName);

					Iterator<Model> iterator = lm.iterator();
					int standardIndex = 0;
					while (iterator.hasNext()) {
						Model m = (Model)iterator.next();
						Assert.assertTrue(m.getStandard_shortvalue().equals(standardList.get(standardIndex).getText()));
						try {
							standardList.get(standardIndex).click();
				
							cat=Driver.webdriver.findElement(By.xpath("//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["+(lastStrandIndex+2)+"]//li[not(contains(@class,'StandardsNotAvailable'))]//span[@class='strand_Definition']")).getText();								
							subcat=Driver.webdriver.findElement(By.xpath("//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["+(lastStrandIndex+2)+"]//li[not(contains(@class,'StandardsNotAvailable'))]//span[@class='standard_sub_Definition']")).getText();
							desc=Driver.webdriver.findElement(By.xpath("//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["+(lastStrandIndex+2)+"]//li[not(contains(@class,'StandardsNotAvailable'))]//span[@class='strand_Description']")).getText();
							
							Assert.assertTrue(cat.equals(m.getStandard_category()));
							Assert.assertTrue(subcat.equals(m.getStandard_subcategory()));
							Assert.assertTrue(desc.equals(m.getStandard_description()));
							//code here
							//Assert.assertTrue(UtilityMethods.VerifyTestScore(conn,m.getStandard_id(),schoolId,classId));
						} catch (Exception e) {
							UtilityMethods.searchandClickonELement(standardList.get(standardIndex),m,standardIndex,cat,subcat,desc,lastStrandIndex,DatabaseConnection.conn,m.getStandard_id(),schoolId,classId);
						}

						Thread.sleep(1000);
						UtilityMethods.verifyColorAndStandardAvgPercentage(standardList.get(standardIndex), m.getAvg_per());
						standardIndex++;
					}
					new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
					Thread.sleep(1000);
				} else {
					for (int strandIndex = 0; strandIndex < homePage.strandnameslist.size(); strandIndex++) {
						new Actions(Driver.webdriver).moveToElement(homePage.strandnameslist.get(strandIndex)).click().build()
								.perform();
						Thread.sleep(3000);
						standardList = Driver.webdriver.findElements(By.xpath(
								"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
										+ (strandIndex + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
						if (homePage.strandnameslist.get(strandIndex).getText().contains("...")) {
							strandName = homePage.strandnamestooltip.getText();
						} else {
							strandName = homePage.strandnameslist.get(strandIndex).getText();
						}
						lm=DatabaseConnection.getStandardAvgPerListInClassContext(DatabaseConnection.conn, schoolId, classId,
								strandName);					
						Iterator<Model> iterator = lm.iterator();
						
						int standardIndex = 0;
						while (iterator.hasNext()) {
							Model m = (Model)iterator.next();
							Assert.assertTrue(m.getStandard_shortvalue().equals(standardList.get(standardIndex).getText()));
							try {
								standardList.get(standardIndex).click();
								cat=Driver.webdriver.findElements(By.xpath("//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["+(strandIndex+2)+"]//li[not(contains(@class,'StandardsNotAvailable'))]//span[@class='strand_Definition']")).get(standardIndex).getText();								
								subcat=Driver.webdriver.findElements(By.xpath("//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["+(strandIndex+2)+"]//li[not(contains(@class,'StandardsNotAvailable'))]//span[@class='standard_sub_Definition']")).get(standardIndex).getText();
								desc=Driver.webdriver.findElements(By.xpath("//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["+(strandIndex+2)+"]//li[not(contains(@class,'StandardsNotAvailable'))]//span[@class='strand_Description']")).get(standardIndex).getText();
								
								Assert.assertTrue(cat.equals(m.getStandard_category()));
								Assert.assertTrue(subcat.equals(m.getStandard_subcategory()));
								Assert.assertTrue(desc.equals(m.getStandard_description()));
								
								//code here
								//Assert.assertTrue(UtilityMethods.VerifyTestScore(DatabaseConnection.conn,m.getStandard_id(),schoolId,classId));
							} catch (Exception e) {
								UtilityMethods.searchandClickonELement(standardList.get(standardIndex),m,standardIndex,cat,subcat,desc,strandIndex,DatabaseConnection.conn,m.getStandard_id(),schoolId,classId);
							}
							Thread.sleep(1000);
							UtilityMethods.verifyColorAndStandardAvgPercentage(standardList.get(standardIndex), m.getAvg_per());
							standardIndex++;
						}
						new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
						Thread.sleep(1000);
					}
				}
				try {
					rightArrowEnable = homePage.enabledrightarrow;
					rightArrowEnable.isEnabled();
					enabledRightArrowFound = true;
					rightArrowEnable.click();
					Thread.sleep(500);
				} catch (Exception e) {
					enabledRightArrowFound = false;
				}

			} while (enabledRightArrowFound);
			
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 36_4 completed");
	}
	
	/**
	 * This method is used to see the standard list in strand from student context
	 * @throws Throwable
	 */
	
	@Then("^The user should able to see the list of all the standards from different grades in the standards table$")
	public void the_user_should_able_to_see_the_list_of_all_the_standards_from_different_grades_in_the_standards_table() throws Throwable {
		try {
			WebElement rightArrowEnable = null;
			boolean enabledRightArrowFound = false;
			String strandName;
			List<Model> lm=new ArrayList<Model>();
			List<WebElement> standardList;
			Integer schoolId =0,classId=0,studentId=0;
			
			Map<Integer,Integer> ids=UtilityMethods.getSchoolIdAndClassId();
			for(Map.Entry<Integer,Integer> entry:ids.entrySet()) {
				schoolId=entry.getKey();
				classId=entry.getValue();
			}
			
			studentId=UtilityMethods.getStudentId();
		
			do {
				
				if (enabledRightArrowFound) {
					int lastStrandIndex = homePage.strandnameslist.size() - 1;
					new Actions(Driver.webdriver).moveToElement(homePage.strandnameslist.get(lastStrandIndex)).click().build()
							.perform();
					Thread.sleep(3000);
					standardList = Driver.webdriver.findElements(By.xpath(
							"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
									+ (lastStrandIndex + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
					if (homePage.strandnameslist.get(lastStrandIndex).getText().contains("...")) {
						strandName = homePage.strandnamestooltip.getText();
					} else {
						strandName = homePage.strandnameslist.get(lastStrandIndex).getText();
					}
					lm=DatabaseConnection.getStandardAvgPerListInStudentContext(DatabaseConnection.conn, schoolId, classId,
							strandName,studentId);	
					Iterator<Model> iterator = lm.iterator();
					int standardIndex = 0;
					while (iterator.hasNext()) {
						Model m = (Model)iterator.next();
						Assert.assertTrue(m.getStandard_shortvalue().equals(standardList.get(standardIndex).getText()));
						standardIndex++;	
					}
					new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
					Thread.sleep(1000);
				} else {
					for (int strandIndex = 0; strandIndex < homePage.strandnameslist.size(); strandIndex++) {
						new Actions(Driver.webdriver).moveToElement(homePage.strandnameslist.get(strandIndex)).click().build()
								.perform();
						Thread.sleep(3000);
						standardList = Driver.webdriver.findElements(By.xpath(
								"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
										+ (strandIndex + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
						if (homePage.strandnameslist.get(strandIndex).getText().contains("...")) {
							strandName = homePage.strandnamestooltip.getText();
						} else {
							strandName = homePage.strandnameslist.get(strandIndex).getText();
						}
						lm=DatabaseConnection.getStandardAvgPerListInStudentContext(DatabaseConnection.conn, schoolId, classId,
								strandName,studentId);					
						Iterator<Model> iterator = lm.iterator();
						
						int standardIndex = 0;
						while (iterator.hasNext()) {
							Model m = (Model)iterator.next();
							Assert.assertTrue(m.getStandard_shortvalue().equals(standardList.get(standardIndex).getText()));
							standardIndex++;
						}
						new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
						Thread.sleep(1000);
					}
				}
				try {
					rightArrowEnable = homePage.enabledrightarrow;
					rightArrowEnable.isEnabled();
					enabledRightArrowFound = true;
					rightArrowEnable.click();
					Thread.sleep(500);
				} 				
				catch (Exception e) {
					enabledRightArrowFound = false;
				}
			} while (enabledRightArrowFound);
			
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		DatabaseConnection.conn.close();
		log.info("Scenario 36_5 completed");
	}
	
	/**
	 * This method is used to verify by default first strand should be selected
	 * @throws Throwable
	 */
	@Then("^By default the first strands should be selected$")
	public void by_default_the_first_strands_should_be_selected() throws Throwable {
		try {
		
			for (int i = 0; i < homePage.strandnameslist.size(); i++) {
				if(i==0) {
				Assert.assertTrue(!homePage.strandnameslist.get(i).getAttribute("class").contains("undefined"));
				}
				else {
					Assert.assertTrue(homePage.strandnameslist.get(i).getAttribute("class").contains("undefined"));
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 37_1 completed");
	}

	
	/**
	 * This method is used to verify the x-axis Strands names in alphabetical order
	 * from left to right
	 * 
	 * @throws Throwable
	 */
	@Then("^The column headers should be populated as Strands as alphabetical order from left to right$")
	public void the_column_headers_should_be_populated_as_Strands_as_alphabetical_order_from_left_to_right()
			throws Throwable {
		try {
			List<String> list = new ArrayList<String>();
			WebElement rightArrowEnable = null;
			boolean enabledRightArrowFound = false;

			do {
				if (enabledRightArrowFound) {
					list.add(UtilityMethods.elipsisRemoval(
							homePage.strandnameslist.get(homePage.strandnameslist.size() - 1).getText()));
				} else {
					for (int i = 0; i < homePage.strandnameslist.size(); i++) {
						list.add(UtilityMethods.elipsisRemoval(homePage.strandnameslist.get(i).getText()));
					}
				}
				try {
					rightArrowEnable = homePage.enabledrightarrow;
					rightArrowEnable.isEnabled();
					enabledRightArrowFound = true;
					rightArrowEnable.click();
					Thread.sleep(500);
				} catch (Exception e) {
					enabledRightArrowFound = false;
				}

			} while (enabledRightArrowFound);
			// Verifying the Header(Strands) List in Alphabetical Order
			Assert.assertTrue(Ordering.natural().isOrdered(list));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 37_2 completed");
	}

	/**
	 * This method is used to check the grouping strips of colours with respective
	 * average score in percentage
	 * 
	 * @throws Throwable
	 */
	@Then("^The X-axis should have the groupings strip of colours with the respective average scores in % range below their text as 'Avg\\.xx%'$")
	public void the_X_axis_should_have_the_groupings_strip_of_colours_with_the_respective_average_scores_in_range_below_their_text_as_Avg_xx()
			throws Throwable {
		try {
			List<Integer> averageList = new ArrayList<Integer>();
			List<String> groupingStripClrList = new ArrayList<String>();
			String avgPer = "", clr = "";
			WebElement rightArrowEnable = null;
			boolean enabledRightArrowFound = false;

			do {
				if (enabledRightArrowFound) {
					clr = homePage.groupingstripclrlist.get(homePage.groupingstripclrlist.size() - 1)
							.getAttribute("class");
					avgPer = homePage.averagelist.get(homePage.averagelist.size() - 1).getText();
					groupingStripClrList.add(clr.substring(clr.indexOf(" "), clr.length() - 2));
					averageList.add(Integer.parseInt(avgPer.substring(5, avgPer.length() - 1)));
				} else {
					for (int i = 0; i < homePage.groupingstripclrlist.size(); i++) {
						clr = homePage.groupingstripclrlist.get(i).getAttribute("class");
						avgPer = homePage.averagelist.get(i).getText();
						groupingStripClrList.add(clr.substring(clr.indexOf(" ") + 1, clr.length() - 2));
						averageList.add(Integer.parseInt(avgPer.substring(5, avgPer.length() - 1)));
					}
				}
				try {
					rightArrowEnable = homePage.enabledrightarrow;
					rightArrowEnable.isEnabled();
					enabledRightArrowFound = true;
					rightArrowEnable.click();
					Thread.sleep(500);
				} catch (Exception e) {
					enabledRightArrowFound = false;
				}

			} while (enabledRightArrowFound);
			// Verifying average score with colour group
			for (int i = 0; i < averageList.size(); i++) {
				if (averageList.get(i) < 40) {
					Assert.assertTrue(groupingStripClrList.get(i).trim().equals("red"));
				} else if (averageList.get(i) >= 40 && averageList.get(i) <= 59) {
					Assert.assertTrue(groupingStripClrList.get(i).trim().equals("orange"));
				} else if (averageList.get(i) >= 60 && averageList.get(i) <= 79) {
					Assert.assertTrue(groupingStripClrList.get(i).trim().equals("yellow"));
				} else if (averageList.get(i) >= 80) {
					Assert.assertTrue(groupingStripClrList.get(i).trim().equals("green"));
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 37_3 completed");
	}

	/**
	 * This method is used to verify the tooltip text on strands who have elippsis
	 * 
	 * @throws Throwable
	 */
	@Then("^The User should be able to see the tool tip having full text of the strand\\.$")
	public void the_User_should_be_able_to_see_the_tool_tip_having_full_text_of_the_strand() throws Throwable {
		try {
			WebElement rightArrowEnable = null, toolTip;
			boolean enabledRightArrowFound = false;
			Actions action = new Actions(Driver.webdriver);
			do {
				if (enabledRightArrowFound) {
					if (homePage.strandnameslist.get(homePage.strandnameslist.size() - 1).getText().contains("...")) {
						toolTip = homePage.strandnamestooltip;
						Assert.assertTrue(toolTip.getText().contains(UtilityMethods.elipsisRemoval(
								homePage.strandnameslist.get(homePage.strandnameslist.size() - 1).getText())));
					}
				} else {
					for (int i = 0; i < homePage.strandnameslist.size(); i++) {
						action.moveToElement(homePage.strandnameslist.get(i)).build().perform();
						if (homePage.strandnameslist.get(i).getText().contains("...")) {
							toolTip = homePage.strandnamestooltip;
							Assert.assertTrue(toolTip.getText().contains(
									UtilityMethods.elipsisRemoval(homePage.strandnameslist.get(i).getText())));
						}
					}
				}
				try {
					rightArrowEnable = homePage.enabledrightarrow;
					rightArrowEnable.isEnabled();
					enabledRightArrowFound = true;
					rightArrowEnable.click();
					Thread.sleep(500);
				} catch (Exception e) {
					enabledRightArrowFound = false;
				}

			} while (enabledRightArrowFound);

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 37_6 completed");
	}

	/**
	 * This method is used to verify the clicking on strand one by one and the
	 * background colour should be filled with achievement level colour
	 * 
	 * @throws Throwable
	 */
	@Then("^The User click on the strand in header then background of strand colour should be filled with achievement level colour$")
	public void the_User_click_on_the_strand_in_header_then_background_of_strand_colour_should_be_filled_with_achievement_level_colour()
			throws Throwable {
		try {
			String stripClr = "", bgColor = "";
			WebElement rightArrowEnable = null;
			boolean enabledRightArrowFound = false;
			do {
				if (enabledRightArrowFound) {
					stripClr = homePage.groupingstripclrlist.get(homePage.groupingstripclrlist.size() - 1)
							.getAttribute("class");
					stripClr = stripClr.substring(stripClr.indexOf(" ") + 1, stripClr.length() - 2).trim();
					new Actions(Driver.webdriver)
							.moveToElement(homePage.strandnameslist.get(homePage.strandnameslist.size() - 1)).click()
							.build().perform();
					Thread.sleep(3000);
					bgColor = homePage.strandnameslist.get(homePage.strandnameslist.size() - 1).getAttribute("class");
					bgColor = bgColor.substring(bgColor.indexOf(" ") + 1, bgColor.length() - 2).trim();
					Assert.assertTrue(stripClr.equals(bgColor));
				} else {
					for (int i = 0; i < homePage.groupingstripclrlist.size(); i++) {
						stripClr = homePage.groupingstripclrlist.get(i).getAttribute("class");
						stripClr = stripClr.substring(stripClr.indexOf(" ") + 1, stripClr.length() - 2).trim();
						new Actions(Driver.webdriver).moveToElement(homePage.strandnameslist.get(i)).click().build()
								.perform();
						Thread.sleep(3000);
						bgColor = homePage.strandnameslist.get(i).getAttribute("class");
						bgColor = bgColor.substring(bgColor.indexOf(" ") + 1, bgColor.length() - 2).trim();
						Assert.assertTrue(stripClr.equals(bgColor));
						new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
						Thread.sleep(1000);
					}
				}
				try {
					rightArrowEnable = homePage.enabledrightarrow;
					rightArrowEnable.isEnabled();
					enabledRightArrowFound = true;
					rightArrowEnable.click();
					Thread.sleep(3000);
				} catch (Exception e) {
					enabledRightArrowFound = false;
				}
			} while (enabledRightArrowFound);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 37_5 completed");
	}

	/**
	 * Method to click on Performance Over time chart icon
	 * 
	 * @throws Throwable
	 */
	@When("^Click on the icon to maximize the Chart$")
	public void click_on_the_icon_to_maximize_the_Chart() throws Throwable {
		try {
			homePage.performanceovrtimeicon.click();
			Thread.sleep(1500);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}
	

	/** 
	 * This method is used to verify the first alpha last name student based on class and student selection  
	 * @throws Throwable
	 */
	@Then("^first alphabetical student is the one picked when the teacher hits the student button$")
	public void first_alphabetical_student_is_the_one_picked_when_the_teacher_hits_the_student_button()
			throws Throwable {
		try {
			new Actions(Driver.webdriver).moveToElement(homePage.studentnameoncontextheader).build().perform();
			String studentNameonUI=homePage.studentnameoncontextheadertooltiptext.getText();
			Assert.assertTrue(DatabaseConnection.getFirstAlphaLastNameStudentByAlphaClassAndSchoolName().equals(studentNameonUI));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 37_4 completed");
	}


	/**
	 * This method is used to verify the performance over time minimize and maximize
	 * the icon and verify the tooltip text on info icon
	 * 
	 * @throws Throwable
	 */
	@Then("^The user should be able to see the Chart header and the icon next to it can be selected and click on same icon to minimize the Chart$")
	public void the_user_should_be_able_to_see_the_Chart_header_and_the_icon_next_to_it_can_be_selected_and_click_on_same_icon_to_minimize_the_Chart()
			throws Throwable {
		try {
			String tooltipText = "Note: The average score listed in the line graph below is calculated from all data available for each assessment based on the content selected. It does not assume the cohort of students remains the same across the assessments listed.";
			// verifying for student context
			homePage.studentmenu.click();
			Thread.sleep(2000);
			new Actions(Driver.webdriver).moveToElement(homePage.infoicononperformanceovrtimeheader).build().perform();
			Assert.assertTrue(homePage.tooltip.getText().equals(tooltipText));
			// verifying for class context
			homePage.classmenu.click();
			Thread.sleep(2000);
			homePage.performanceovrtimeicon.click();
			Assert.assertTrue(homePage.performanceovrtimeheader.getText().equals("Performance Over Time"));
			new Actions(Driver.webdriver).moveToElement(homePage.infoicononperformanceovrtimeheader).build().perform();
			Assert.assertTrue(homePage.tooltip.getText().equals(tooltipText));
			// minimizing and checking the info icon is not display
			new Actions(Driver.webdriver).moveToElement(homePage.performanceovrtimeicon).click().build().perform();
			Thread.sleep(1000);
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 38_1 completed");
	}

	/**
	 * This method is used to verify that the first default selected Strand name is
	 * default header in performance over time chart
	 * 
	 * @throws Throwable
	 */
	@Then("^Verify that the chart header should be the first alphabetical strand by defaultly$")
	public void verify_that_the_chart_header_should_be_the_first_alphabetical_strand_by_defaultly() throws Throwable {
		try {
			String firstStrandName;
			if (homePage.strandnameslist.get(0).getText().contains("...")) {
				firstStrandName = homePage.strandnamestooltip.getText();
			} else {
				firstStrandName = homePage.strandnameslist.get(0).getText();
			}
			Assert.assertTrue(homePage.defaultstrandnameinpotchart.getText().contains(firstStrandName));
			click_on_the_icon_to_maximize_the_Chart();
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 38_2 completed");
	}

	/**
	 * This method is used to verify the clicking on strand names one by one and
	 * that name should become the header of performance over time chart here also checking the avg percentage of each strand in their column in SP table.
	 * 
	 * @throws Throwable
	 */
	@Then("^Select the Strand within the Strand header from the Standard table and selected strand becomes the header of the Line Chart$")
	public void select_the_Strand_within_the_Strand_header_from_the_Standard_table_and_selected_strand_becomes_the_header_of_the_Line_Chart()
			throws Throwable {
		try {
			String strandName = "",standard="";
			WebElement rightArrowEnable = null;
			boolean enabledRightArrowFound = false;
			List<WebElement> standardList;
			
			Integer schoolId=0,classId=0,avg=0;

			Map<Integer,Integer> ids=UtilityMethods.getSchoolIdAndClassId();
			for(Map.Entry<Integer,Integer> entry:ids.entrySet()) {
				schoolId=entry.getKey();
				classId=entry.getValue();
			}
			
			do {
				if (enabledRightArrowFound) {
					new Actions(Driver.webdriver)
					.moveToElement(homePage.strandnameslist.get(homePage.strandnameslist.size() - 1)).click()
					.build().perform();
					Thread.sleep(3000);
					if (homePage.strandnameslist.get(homePage.strandnameslist.size() - 1).getText().contains("...")) {
						strandName = homePage.strandnamestooltip.getText();
					} else {
						strandName = homePage.strandnameslist.get(homePage.strandnameslist.size() - 1).getText();
					}
					standardList = Driver.webdriver.findElements(By.xpath(
							"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
									+ ((homePage.strandnameslist.size() - 1) + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
					standard=standardList.get(0).getText();
					standard=standard.substring(0, standard.indexOf("."));
					avg=DatabaseConnection.getStrandAvgInSPInClass(DatabaseConnection.conn, schoolId, classId, standard);
					Assert.assertTrue(homePage.strandavglist.get(homePage.strandnameslist.size() - 1).getText().equals("Avg. "+avg+"%"));
					
					click_on_the_icon_to_maximize_the_Chart();
					Assert.assertTrue(homePage.defaultstrandnameinpotchart.getText().contains(strandName));
					new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
					Thread.sleep(1000);
				} else {
					for (int i = 0; i < homePage.groupingstripclrlist.size(); i++) {
						new Actions(Driver.webdriver).moveToElement(homePage.strandnameslist.get(i)).click().build()
						.perform();
						Thread.sleep(3000);
						if (homePage.strandnameslist.get(i).getText().contains("...")) {
							strandName = homePage.strandnamestooltip.getText();
						} else {
							strandName = homePage.strandnameslist.get(i).getText();
						}
						standardList = Driver.webdriver.findElements(By.xpath(
								"//div[@class='overview-table-body']//div[@class='overview-table-row']//div[@class='overview-table-col']["
										+ (i + 2) + "]//li[not(contains(@class,'StandardsNotAvailable'))]"));
						standard=standardList.get(0).getText();
						standard=standard.substring(0, standard.indexOf("."));
						avg=DatabaseConnection.getStrandAvgInSPInClass(DatabaseConnection.conn, schoolId, classId, standard);
						Assert.assertTrue(homePage.strandavglist.get(i).getText().equals("Avg. "+avg+"%"));
						
						click_on_the_icon_to_maximize_the_Chart();
						Assert.assertTrue(homePage.defaultstrandnameinpotchart.getText().contains(strandName));
						new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
						Thread.sleep(1000);
					}
				}				
				try {
					rightArrowEnable = homePage.enabledrightarrow;
					rightArrowEnable.isEnabled();
					enabledRightArrowFound = true;
					rightArrowEnable.click();
					Thread.sleep(3000);
				} catch (Exception e) {
					enabledRightArrowFound = false;
				}
			} while (enabledRightArrowFound);

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 38_3 completed");
	}

	/**
	 * This method is used to click on Test Score button in class context
	 * 
	 * @throws Throwable
	 */
	@When("^User click on Class Context and Test Score button$")
	public void user_click_on_Class_Context_and_Test_Score_button() throws Throwable {
		try {
			UtilityMethods.scrollPageUp(Driver.webdriver);
			JavascriptExecutor jse2 = (JavascriptExecutor)Driver.webdriver;
			Thread.sleep(1000);
			try {
				Assert.assertTrue(homePage.activeclassmenu.getAttribute("class").contains("active"));
			}
			catch(Exception e) {
				Thread.sleep(3000);
				jse2.executeScript("arguments[0].click();", homePage.classmenu);
				Thread.sleep(3000);
			}
			try {
				Assert.assertTrue(homePage.activetestscoresbtn.getAttribute("class").equals("active"));
			}
			catch(Exception e) {
				Thread.sleep(3000);
				jse2.executeScript("arguments[0].click();", homePage.testscoresbtn);
				Thread.sleep(3000);
			}
			testScoreMenuClicked=true;
			underClassContext=true;
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method is to verify the student list is display in class context in test score, and also validating with db
	 * 
	 * @throws Throwable
	 */
	@Then("^line chart of Test score over time should be displayed to the right of it Student list should display$")
	public void line_chart_of_Test_score_over_time_should_be_displayed_to_the_right_of_it_Student_list_should_display()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.testscoreovertimelinechart.isDisplayed());

			Integer schoolId = 0, classId = 0;

			Map<Integer, Integer> ids = UtilityMethods.getSchoolIdAndClassId();
			for (Map.Entry<Integer, Integer> entry : ids.entrySet()) {
				schoolId = entry.getKey();
				classId = entry.getValue();
			}

			PaginationUtility.methodOne();
			if (PaginationUtility.paginatorFound) {
				PaginationUtility.methodTwo();
				if (PaginationUtility.enabledLeftArrowFound) {
					do {
						PaginationUtility.methodThree();
						if (PaginationUtility.doneWithThreeCircle) {
							PaginationUtility.methodFour();
							for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
								PaginationUtility.verifyToolTipDatawithTestScoreCircle(j, schoolId, classId);
							}
							UtilityMethods.scrollPageUp(Driver.webdriver, 2);
							Thread.sleep(1000);
						} else {
							for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
								PaginationUtility.methodFive(i);
								for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
									PaginationUtility.verifyToolTipDatawithTestScoreCircle(j, schoolId, classId);
								}
								UtilityMethods.scrollPageUp(Driver.webdriver, 2);
								Thread.sleep(1000);
							}
							PaginationUtility.doneWithThreeCircle = true;
						}
						PaginationUtility.methodSix();
					} while (!PaginationUtility.disableLeftArrowFound);
				} else {
					for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
						PaginationUtility.methodFive(i);
						for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
							PaginationUtility.verifyToolTipDatawithTestScoreCircle(j, schoolId, classId);
						}
						UtilityMethods.scrollPageUp(Driver.webdriver, 2);
						Thread.sleep(1000);
					}
				}

			} else {
				for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
					PaginationUtility.verifyToolTipDatawithTestScoreCircle(j, schoolId, classId);
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 40_1 completed");
	}

	/**
	 * This method is used to click on Test Score button in Student COntext
	 * 
	 * @throws Throwable
	 */
	@When("^User click on Student Context and Test Score button$")
	public void user_click_on_Student_Context_and_Test_Score_button() throws Throwable {
		try {
			JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;
			UtilityMethods.scrollPageUp(Driver.webdriver);
			UtilityMethods.wait_For_Context_Header_Section();

			try {
				Assert.assertTrue(homePage.activestudentmenu.getAttribute("class").contains("active"));
			}catch(Exception e) {
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", homePage.studentmenu);
				Thread.sleep(3000);
			}
			try {
				Assert.assertTrue(homePage.activetestscoresbtn.getAttribute("class").equals("active"));
			}catch(Exception e) {
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", homePage.testscoresbtn);
				Thread.sleep(3000);
				testScoreMenuClicked=true;
			}
			
			underStudentContext=true;
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method is used to verify the Student LIst is not present in Student
	 * Context- Test Score
	 * 
	 * @throws Throwable
	 */
	@Then("^line chart of Test score over time should be displayed of that student but Student list should not be displayed$")
	public void line_chart_of_Test_score_over_time_should_be_displayed_of_that_student_but_Student_list_should_not_be_displayed()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.testscoreovertimetext.isDisplayed());
			Integer schoolId = 0, classId = 0, studentId = 0;

			Map<Integer, Integer> ids = UtilityMethods.getSchoolIdAndClassId();
			for (Map.Entry<Integer, Integer> entry : ids.entrySet()) {
				schoolId = entry.getKey();
				classId = entry.getValue();
			}
			studentId = UtilityMethods.getStudentId();

			PaginationUtility.methodOne();
			if (PaginationUtility.paginatorFound) {
				PaginationUtility.methodTwo();
				if (PaginationUtility.enabledLeftArrowFound) {
					do {
						PaginationUtility.methodThree();
						if (PaginationUtility.doneWithThreeCircle) {
							PaginationUtility.methodFour();
							for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
								PaginationUtility.verifyStudentListNotDisplayWithStudentContext(j, schoolId, classId,
										studentId);
							}
							UtilityMethods.scrollPageUp(Driver.webdriver, 2);
							Thread.sleep(1000);
						} else {
							for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
								PaginationUtility.methodFive(i);
								for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
									PaginationUtility.verifyStudentListNotDisplayWithStudentContext(j, schoolId,
											classId, studentId);
								}
								UtilityMethods.scrollPageUp(Driver.webdriver, 2);
								Thread.sleep(1000);
							}
							PaginationUtility.doneWithThreeCircle = true;
						}
						PaginationUtility.methodSix();
					} while (!PaginationUtility.disableLeftArrowFound);
				} else {
					for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
						PaginationUtility.methodFive(i);
						for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
							PaginationUtility.verifyStudentListNotDisplayWithStudentContext(j, schoolId, classId,
									studentId);
						}
						UtilityMethods.scrollPageUp(Driver.webdriver, 2);
						Thread.sleep(1000);
					}
				}
			} else {
				for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
					PaginationUtility.verifyStudentListNotDisplayWithStudentContext(j, schoolId, classId, studentId);
				}
			}
			try {
				Assert.assertTrue(homePage.noofstudentsinlist.get(0).isDisplayed());
				log.info("The Student List is display in Student Context Test Score");
				UtilityMethods.processException(new Exception());
			} catch (Exception e) {
				log.info("The Student List is not display in Student Context Test Score");
				CBTConfiguration.score = "pass";
			}
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 40_2 completed");
	}

	/**
	 * This method is used to click on Standard Performance Button in Student
	 * Context
	 * 
	 * @throws Throwable
	 */
	@When("^User Click on Standard Performance tab within the Student Context$")
	public void user_Click_on_Standard_Performance_tab_within_the_Student_Context() throws Throwable {
		try {

			JavascriptExecutor js = (JavascriptExecutor) Driver.webdriver;
			try {
				Assert.assertTrue(homePage.activestudentmenu.getAttribute("class").contains("active"));
			} catch (Exception e) {
				UtilityMethods.scrollPageUp(Driver.webdriver);
				UtilityMethods.wait_For_Context_Header_Section();
				js.executeScript("arguments[0].click();", homePage.studentmenu);
				Thread.sleep(3000);
			}
			try {
				Assert.assertTrue(homePage.activestandardperformancebtn.getAttribute("class").equals("active"));
			} catch (Exception e) {
				Thread.sleep(1000);
				js.executeScript("arguments[0].click();", homePage.standardperformancebtn);
				Thread.sleep(3000);
			}
			performanceMenuClicked=true;				
			underStudentContext=true;
			UtilityMethods.wait_For_Performance_Over_Time_Section_Load();
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method is used to click on circle and verify the overlay tooltip items
	 * on performance line chart
	 * 
	 * @throws Throwable
	 */
	@Then("^User click on the circle within the line chart and should able to see the overlay of Tool tip which have following items$")
	public void user_click_on_the_circle_within_the_line_chart_and_should_able_to_see_the_overlay_of_Tool_tip_which_have_following_items()
			throws Throwable {
		try {
			PaginationUtility.methodOne();
			if (PaginationUtility.paginatorFound) {
				PaginationUtility.methodTwo();
				if (PaginationUtility.enabledLeftArrowFound) {
					do {
						PaginationUtility.methodThree();
						if (PaginationUtility.doneWithThreeCircle) {
							PaginationUtility.methodFour();
							for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
								PaginationUtility.verifyToolTipDetailsonLineChart(j);
							}
							UtilityMethods.scrollPageUp(Driver.webdriver, 2);
							Thread.sleep(1000);
						} else {
							for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
								PaginationUtility.methodFive(i);
								for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
									PaginationUtility.verifyToolTipDetailsonLineChart(j);
								}
								UtilityMethods.scrollPageUp(Driver.webdriver, 2);
								Thread.sleep(1000);
							}
							PaginationUtility.doneWithThreeCircle = true;
						}
						PaginationUtility.methodSix();
					} while (!PaginationUtility.disableLeftArrowFound);
				} else {
					for (int i = PaginationUtility.circleList.size() - 1; i >= 0; i--) {
						PaginationUtility.methodFive(i);
						for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
							PaginationUtility.verifyToolTipDetailsonLineChart(j);
						}
					}
				}

			} else {
				for (int j = homePage.testNamesonPerPage_onlinechart.size() - 1; j >= 0; j--) {
					PaginationUtility.verifyToolTipDetailsonLineChart(j);
				}
			}
			if (PaginationUtility.toolTipDisplayAfterClosing) {
				log.info("ToolTip Still Display after closing the Overlay.");
				CBTConfiguration.score = "fail";
			} else {
				CBTConfiguration.score = "pass";
			}
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 41_2 completed");
	}

	/**
	 * This method is used to verify the colour strip with the % range
	 * 
	 * @throws Throwable
	 */
	@Then("^different coloured strips should be displayed$")
	public void different_coloured_strips_should_be_displayed() throws Throwable {
		try {
			Assert.assertTrue(homePage.textoutgraystripinstudentlist.getText().equals("All"));
			Assert.assertTrue(homePage.textoutredstripinstudentlist.getText().equals("< 40%"));
			Assert.assertTrue(homePage.textoutorangestripinstudentlist.getText().equals("40-59%"));
			Assert.assertTrue(homePage.textoutyellowstripinstudentlist.getText().equals("60-79%"));
			Assert.assertTrue(homePage.textoutgreenstripinstudentlist.getText().equals("≥ 80%"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 42_1 completed");
	}

	/**
	 * This method is used to vrify the blue strip highlighted when any coloured
	 * strip is clicked and the associated student list shuld be display with the
	 * colour
	 * 
	 * @throws Throwable
	 */
	@Then("^click on different coloured strips,blue strip should be display under the clicked strip and the no\\. of student records with that colour should be display$")
	public void click_on_different_coloured_strips_blue_strip_should_be_display_under_the_clicked_strip_and_the_no_of_student_records_with_that_colour_should_be_display()
			throws Throwable {
		try {
			int recordsOnClickedStrip = 0, totalCount = 0;
			for (int i = 0; i < homePage.classcolouredStripOnStudentList.size(); i++) {
				if (!(homePage.TextInStripOnStudentList.get(i).getText().equals("0"))) {
					homePage.classcolouredStripOnStudentList.get(i).click();
					Thread.sleep(1000);
					Assert.assertTrue(
							homePage.classcolouredStripOnStudentList.get(i).getAttribute("class").equals("active_progress"));
					Thread.sleep(1000);
					recordsOnClickedStrip = Integer.parseInt(homePage.TextInStripOnStudentList.get(i).getText());
					
					PaginationUtility.studentListMethodOne();
					if (PaginationUtility.paginatorFound) {
						PaginationUtility.studentListMethodTwo();
						if (PaginationUtility.enabledRightArrowFound) {
							do {
								PaginationUtility.studentListMethodThree();
								if (PaginationUtility.doneWithThreeCircle) {
									PaginationUtility.studentListMethodFour();
									totalCount += homePage.noofstudentsinlist.size();
								} else {
									for (int x = 0; x < PaginationUtility.circleList.size(); x++) {
										PaginationUtility.studentListMethodFive(x);
										totalCount += homePage.noofstudentsinlist.size();
									}
									PaginationUtility.doneWithThreeCircle = true;
								}
								PaginationUtility.studentListMethodSix();
							} while (!PaginationUtility.disableRightArrowFound);
						} else {
							for (int x = 0; x < PaginationUtility.circleList.size(); x++) {
								PaginationUtility.studentListMethodFive(x);
								totalCount += homePage.noofstudentsinlist.size();
							}
						}
						PaginationUtility.paginatorFound = false;
					} else {
						totalCount = homePage.noofstudentsinlist.size();
					}
					Assert.assertTrue(recordsOnClickedStrip == totalCount);
				}
			}
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 42_2 completed");
	}

	/**
	 * This method is used to verify the student list with minimize and maximize
	 * also with default data display in the student record
	 * 
	 * @throws Throwable
	 */
	@Then("^user should able to see Student List and the icon next to it can be selected to minimize or maximize the Student List window$")
	public void user_should_able_to_see_Student_List_and_the_icon_next_to_it_can_be_selected_to_minimize_or_maximize_the_Student_List_window() throws Throwable {
		try {
			Assert.assertTrue(homePage.studentlist.isDisplayed());
			// clicking on icon to minimize the student list
			homePage.studentlisticon.click();
			Thread.sleep(500);
			try {
				Assert.assertTrue(homePage.studentlist.isDisplayed());
				CBTConfiguration.score = "fail";
				Assert.fail();
			} catch (Exception e) {
				CBTConfiguration.score = "pass";
			}
			UtilityMethods.scrollPageDown(Driver.webdriver, 11);
			// clicking on icon to maximize the student list
			homePage.studentlisticon.click();
			Thread.sleep(500);
			Assert.assertTrue(homePage.studentlist.isDisplayed());
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 43_1 completed");
	}

	/**
	 * This method is used to select random Standard in Strand Names list
	 * 
	 * @throws Throwable
	 */
	@When("^Click or select strand and standard from the standard table$")
	public void click_or_select_strand_and_standard_from_the_standard_table() throws Throwable {
		try {
			int standardListSize = homePage.standardnameslist.size();
			int randomNumber = (int) (Math.random() * standardListSize);
			UtilityMethods.scrollPageDown(Driver.webdriver, 4);
			Thread.sleep(2000);
			new Actions(Driver.webdriver).moveToElement(homePage.standardnameslist.get(randomNumber)).build().perform();
			Thread.sleep(3000);
			headerOnToolTip = homePage.stranddefinitionlist.get(randomNumber).getText();
			String subcat= homePage.standardsubdefinitionlist.get(randomNumber).getText();
			subHeaderOnToolTip =subcat.substring(0, subcat.indexOf(" "))+" "+ homePage.standarddescriptionlist.get(randomNumber).getText();
			homePage.standardnameslist.get(randomNumber).click();
			Thread.sleep(5000);
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
	}

	/**
	 * This method is used to verify the selected Strand and Standard from left side
	 * with right side student list records on Test Score Menu with class context
	 * 
	 * @throws Throwable
	 */
	@Then("^The student list related to the selected standard within the perticular strand with the header and the subheader is displayed$")
	public void the_student_list_related_to_the_selected_standard_within_the_perticular_strand_with_the_header_and_the_subheader_is_displayed()
			throws Throwable {
		try {
			Assert.assertTrue(homePage.studentlistheader.getText().equals(headerOnToolTip));
			Assert.assertTrue(homePage.studentlistsubheader.getText().equals(subHeaderOnToolTip));
			/** Note :student list related to the selected standard within the perticular strand with the header and the subheader is displayed & 
			 * already covered in previously created method
			 */
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 43_6 completed");
	}

	/**
	 * This method is used to verify the left most column as student(X) , where X is
	 * the total number belong to the achievement level colour
	 * 
	 * @throws Throwable
	 */
	@Then("^Verify the left most column header should be 'Student \\(X\\)'$")
	public void verify_the_left_most_column_header_should_be_Student_X() throws Throwable {
		try {
			String allRecord = homePage.textingraystripinstudentlist.getText();
			Assert.assertTrue(homePage.StudentRecordsSize.getText().contains("Students (" + allRecord + ")"));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 43_2 completed");
	}

	/**
	 * This method is used to verify the studentlist, with click or select any
	 * student in list then the student context should be activate and the
	 * information of that student should be display
	 * 
	 * @throws Throwable
	 */
	@Then("^select the student within the student list and Student context with information for that individual student should be shown$")
	public void select_the_student_within_the_student_list_and_Student_context_with_information_for_that_individual_student_should_be_shown()
			throws Throwable {
		try {
			String schoolName, className;
			HomePage homePage = PageFactory.initElements(Driver.webdriver, HomePage.class);
			new Actions(Driver.webdriver).moveToElement(homePage.schoolnameoncontextheader).build().perform();
			if (homePage.schoolnameoncontextheader.getText().contains("...")) {
				schoolName = homePage.tooltipofschoolnameoncontextheader.getText();
			} else {
				schoolName = homePage.schoolnameoncontextheader.getText();
			}
			new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
			new Actions(Driver.webdriver).moveToElement(homePage.classnameoncontextheader).build().perform();
			if (homePage.classnameoncontextheader.getText().contains("...")) {
				className = homePage.tooltipofclassnameoncontextheader.getText();
			} else {
				className = homePage.classnameoncontextheader.getText();
			}
			new Actions(Driver.webdriver).moveToElement(homePage.overviewtext).build().perform();
			int studentListSize = homePage.noofstudentsinlist.size();
			int randomNumber = (int) (Math.random() * studentListSize);
			String StudentName = homePage.studentnameslistinstudentlist.get(randomNumber).getText();
			UtilityMethods.scrollPageDown(Driver.webdriver, 4);
			new Actions(Driver.webdriver).moveToElement(homePage.studentnameslistinstudentlist.get(randomNumber))
					.click().build().perform();
			Thread.sleep(2000);
			UtilityMethods.scrollPageUp(Driver.webdriver);
			String studentNameonContext = homePage.studentnameoncontextheader.getText();
			Assert.assertTrue(homePage.activestudentmenu.isDisplayed());
			Assert.assertTrue(studentNameonContext.contains(StudentName));
			Assert.assertTrue(homePage.schoolnameoncontextheader.getText().equals(schoolName));
			Assert.assertTrue(homePage.classnameoncontextheader.getText().equals(className));
			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 43_5 completed");
	}

	/**
	 * This method is used to verify the right most columns as score in student list
	 * 
	 * @throws Throwable
	 */
	@Then("^Verify the right most column header should be 'Score'$")
	public void verify_the_right_most_column_header_should_be_Score() throws Throwable {
		try {
			int score = 0;
			WebElement scoreElement = null;

			PaginationUtility.studentListMethodOne();
			if (PaginationUtility.paginatorFound) {
				PaginationUtility.studentListMethodTwo();
				if (PaginationUtility.enabledRightArrowFound) {
					do {
						PaginationUtility.studentListMethodThree();
						if (PaginationUtility.doneWithThreeCircle) {
							PaginationUtility.studentListMethodFour();
							for (int j = 0; j < homePage.studentscorelistinstudentlist.size(); j++) {
								score = Integer.parseInt(homePage.studentscorelistinstudentlist.get(j).getText());
								scoreElement = homePage.studentscorelistinstudentlist.get(j);
								UtilityMethods.verifyColorAndScoreOnStudentList(scoreElement, score);
							}
						} else {
							for (int x = 0; x < PaginationUtility.circleList.size(); x++) {
								PaginationUtility.studentListMethodFive(x);
								for (int j = 0; j < homePage.studentscorelistinstudentlist.size(); j++) {
									score = Integer.parseInt(homePage.studentscorelistinstudentlist.get(j).getText());
									scoreElement = homePage.studentscorelistinstudentlist.get(j);
									UtilityMethods.verifyColorAndScoreOnStudentList(scoreElement, score);
								}
							}
							PaginationUtility.doneWithThreeCircle = true;
						}
						PaginationUtility.studentListMethodSix();
					} while (!PaginationUtility.disableRightArrowFound);
				} else {
					for (int x = 0; x <PaginationUtility.circleList.size(); x++) {
						PaginationUtility.studentListMethodFive(x);
						for (int j = 0; j < homePage.studentscorelistinstudentlist.size(); j++) {
							score = Integer.parseInt(homePage.studentscorelistinstudentlist.get(j).getText());
							scoreElement = homePage.studentscorelistinstudentlist.get(j);
							UtilityMethods.verifyColorAndScoreOnStudentList(scoreElement, score);
						}
					}
				}
				PaginationUtility.paginatorFound = false;
			} else {
				for (int j = 0; j < homePage.studentscorelistinstudentlist.size(); j++) {
					score = Integer.parseInt(homePage.studentscorelistinstudentlist.get(j).getText());
					scoreElement = homePage.studentscorelistinstudentlist.get(j);
					UtilityMethods.verifyColorAndScoreOnStudentList(scoreElement, score);
				}
			}
			CBTConfiguration.score = "pass";

		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 43_3 completed");
	}

	@Then("^All the column headers should be sortable with ascending and descending order$")
	public void all_the_column_headers_should_be_sortable_with_ascending_and_descending_order() throws Throwable {
		try {
			/**Verifying UI Content in Student List with DB data*/
			List<Model> lm = new ArrayList<Model>();
			Integer schoolId=0,classId=0;

			Map<Integer,Integer> ids=UtilityMethods.getSchoolIdAndClassId();
			for(Map.Entry<Integer,Integer> entry:ids.entrySet()) {
				schoolId=entry.getKey();
				classId=entry.getValue();
			}
			
			String firstStrandName;
			if (homePage.strandnameslist.get(0).getText().contains("...")) {
				firstStrandName = homePage.strandnamestooltip.getText();
			} else {
				firstStrandName = homePage.strandnameslist.get(0).getText();
			}
			lm = DatabaseConnection.getStudentDetailListInSPInClassByStrand(DatabaseConnection.conn, schoolId, classId, firstStrandName);
			Iterator<Model> iterator = lm.iterator();
			int index = 0;
			while (iterator.hasNext()) {
				Model m = (Model) iterator.next();
				Assert.assertTrue(m.getStudent_name().equals(homePage.studentnameslistinstudentlist.get(index).getText()));
				Assert.assertTrue(m.getNo_of_questions()==Integer.parseInt(homePage.noofquestionsorsubmitdatelistinstudentlist.get(index).getText()));
				Assert.assertTrue(m.getStudent_score_avg()==Integer.parseInt(homePage.scorelistinstudentlist.get(index).getText()));
				UtilityMethods.verifyColorAndScoreOnStudentList(homePage.scorelistinstudentlist.get(index), m.getStudent_score_avg());
				index++;
			}

			String name = "";
			UtilityMethods.scrollPageDown(Driver.webdriver, 10);
			Actions actions = new Actions(Driver.webdriver);
			// clicking on name down arrow
			actions.moveToElement(homePage.studentListnamedownarrow).click().build().perform();
			Thread.sleep(500);
			List<String> listItem = new ArrayList<String>();
			List<Integer> numericlistItem = new ArrayList<Integer>();
			for (int i = 0; i < homePage.studentnameslistinstudentlist.size(); i++) {
				name = homePage.studentnameslistinstudentlist.get(i).getText();
				listItem.add(name.substring(name.indexOf(" ") + 1));
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(listItem));
			listItem.clear();

			// clicking on name up arrow
			actions.moveToElement(homePage.studentListnameuparrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.studentnameslistinstudentlist.size(); i++) {
				name = homePage.studentnameslistinstudentlist.get(i).getText();
				listItem.add(name.substring(name.indexOf(" ") + 1));
			}
			Assert.assertTrue(Ordering.natural().isOrdered(listItem));
			listItem.clear();

			// clicking on number of questions down arrow
			actions.moveToElement(homePage.studentListquestiondownarrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.noofquestionsorsubmitdatelistinstudentlist.size(); i++) {
				numericlistItem.add(Integer.parseInt(homePage.noofquestionsorsubmitdatelistinstudentlist.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(numericlistItem));
			numericlistItem.clear();

			// clicking on number of questions up arrow
			actions.moveToElement(homePage.studentListquestionuparrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.noofquestionsorsubmitdatelistinstudentlist.size(); i++) {
				numericlistItem.add(Integer.parseInt(homePage.noofquestionsorsubmitdatelistinstudentlist.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().isOrdered(numericlistItem));
			numericlistItem.clear();

			// clicking on score down arrow
			actions.moveToElement(homePage.studentListscoredownarrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.scorelistinstudentlist.size(); i++) {
				numericlistItem.add(Integer.parseInt(homePage.scorelistinstudentlist.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().reverse().isOrdered(numericlistItem));
			numericlistItem.clear();

			// clicking on score up arrow
			actions.moveToElement(homePage.studentListscoreuparrow).click().build().perform();
			Thread.sleep(500);
			for (int i = 0; i < homePage.scorelistinstudentlist.size(); i++) {
				numericlistItem.add(Integer.parseInt(homePage.scorelistinstudentlist.get(i).getText()));
			}
			Assert.assertTrue(Ordering.natural().isOrdered(numericlistItem));
			numericlistItem.clear();

			CBTConfiguration.score = "pass";
		} catch (Exception e) {
			UtilityMethods.processException(e);
		}
		log.info("Scenario 43_4 completed");
	}

}
