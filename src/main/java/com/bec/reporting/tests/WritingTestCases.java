package com.bec.reporting.tests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.bec.reporting.utils.UtilityMethods;

public class WritingTestCases {
	static ChromeDriver webdriver;
	private static String[] columns = { "Key", "Status", "Summary" };
	// private static List<Employee> employees = new ArrayList<>();
	// Initializing employees data to insert into the excel file
	/*
	 * static { Calendar dateOfBirth = Calendar.getInstance(); dateOfBirth.set(1992,
	 * 7, 21); employees.add(new Employee("Rajeev Singh", "rajeev@example.com",
	 * dateOfBirth.getTime(), 1200000.0));
	 * 
	 * dateOfBirth.set(1965, 10, 15); employees.add(new Employee("Thomas cook",
	 * "thomas@example.com", dateOfBirth.getTime(), 1500000.0));
	 * 
	 * dateOfBirth.set(1987, 4, 18); employees.add(new Employee("Steve Maiden",
	 * "steve@example.com", dateOfBirth.getTime(), 1800000.0)); }
	 */

	public static void main(String args[]) {
		try {
			
			System.setProperty("webdriver.chrome.driver",
					"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("start-maximized");
			webdriver = new ChromeDriver(options);
			webdriver.get("https://benchmarkeducation.atlassian.net/secure/BrowseProjects.jspa");
			Thread.sleep(3000);
			
			loginintoProject();
			
			webdriver.findElement(By.xpath("//tbody//span[ .='BU Assessment Reporting']"))
					.click();
			Thread.sleep(6000);

			/*webdriver.findElement(By.xpath("//div[@class='css-xuepxh' and .='Reports']")).click();
			Thread.sleep(6000);*/

			webdriver.findElement(By.xpath("//a[@data-testid='NavigationItem' and .='Sprint Report']")).click();
			Thread.sleep(6000);
			/** change sprint value here **/
			writeTestCases("BU Sprint 19", "C:/Users/htikkas/Desktop/Sprint_Wise_Test_Case.xlsx");
			
			
			webdriver.quit();
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		} catch (Exception e) {
			System.out.println("Error in program");
			webdriver.quit();
		}
	}

	private static void loginintoProject() {
		try {
			WebElement ee=webdriver.findElement(By.xpath("//div[@role='presentation']//a[@id='profileGlobalItem']"));
			new Actions(webdriver).moveToElement(ee).click().build().perform();
			Thread.sleep(5000);
			webdriver.findElement(By.xpath("//input[@id='username']")).sendKeys("htikkas@intraedge.com");
			Thread.sleep(1000);
			webdriver.findElement(By.xpath("//button[@id='login-submit']")).click();
			Thread.sleep(2000);
			webdriver.findElement(By.xpath("//input[@id='password']")).sendKeys("Nahipata@143");
			Thread.sleep(1000);
			webdriver.findElement(By.xpath("//button[@id='login-submit']")).click();
			Thread.sleep(10000);
		} catch (Exception e) {
			System.out.println("Unable to login in atlantis");
			webdriver.quit();
		}

	}

	public static void writeTestCases(String sprintName, String fileName) {
		try {
			/*WebElement dd = webdriver.findElement(By.xpath("//a[@id='ghx-items-trigger']"));
			dd.click();Thread.sleep(500);
			dd.click();
			dd.clear();
			Thread.sleep(500);
			dd.sendKeys(sprintName);
			Thread.sleep(500);
			dd.sendKeys(Keys.ENTER);
			Thread.sleep(3000);*/

			List<WebElement> listofIssueType = webdriver
					.findElements(By.xpath("//td[@class='ghx-nowrap' and contains(text(),'Xray Test')]"));
			List<WebElement> listOfKeys = webdriver.findElements(By.xpath(
					"//td[@class='ghx-nowrap' and contains(text(),'Xray Test')]/preceding-sibling::td[@class='ghx-nowrap']"));
			List<WebElement> listOfSummary = webdriver.findElements(
					By.xpath("//td[@class='ghx-nowrap' and contains(text(),'Xray Test')]/preceding-sibling::td[1]"));

			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}

			/*
			 * XSSFWorkbook workbook = new XSSFWorkbook(); XSSFSheet sheet =
			 * workbook.createSheet(sprintName.substring(sprintName.lastIndexOf(" ")));
			 */

			/** Writing code here */

			// Create a Workbook
			Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

			/*
			 * CreationHelper helps us create instances of various things like DataFormat,
			 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
			 */
			// CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet
			Sheet sheet = workbook.createSheet(sprintName.substring(sprintName.lastIndexOf(" ")));
			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.BLACK.getIndex());

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Create a Row
			Row headerRow = sheet.createRow(0);

			// Create cells
			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			// Create Cell Style for formatting Date
			/*
			 * CellStyle dateCellStyle = workbook.createCellStyle();
			 * dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
			 * "dd-MM-yyyy"));
			 */

			// Create Other rows and cells with Table data of atlantis page
			int rowNum = 1;
			// for (WebElement el : listofIssueType) {
			for (int i = 0; i < listofIssueType.size(); i++) {

				Row row = sheet.createRow(rowNum++);

				row.createCell(0).setCellValue(listOfKeys.get(i).getText());

				row.createCell(1).setCellValue("");

				/*
				 * Cell dateOfBirthCell = row.createCell(2);
				 * dateOfBirthCell.setCellValue(employee.getDateOfBirth());
				 * dateOfBirthCell.setCellStyle(dateCellStyle);
				 */

				row.createCell(2).setCellValue(listOfSummary.get(i).getText());
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();
			System.out.println("File successfully created");
		} catch (Exception e) {
			System.out.println("Error to write data in file");
			webdriver.quit();
		}
	}

}
