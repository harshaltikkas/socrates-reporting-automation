package com.bec.reporting.tests;

import java.util.Properties;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "@target/failed_scenarios.txt", glue = { "com.bec.reporting.steps" },
		
		plugin = { "pretty", "html:target/htmlreports",
				"com.bec.reporting.utils.ExtentCucumberFormatter:target/cucumber-reports/extent_report_rerun/report.html",
				"json:target/json_report/Cucumber.json",
				"junit:target/junit_xml_report/Cucumber.xml",
				"rerun:target/failed_scenarios.txt"})

public class TestRunnerTwo {
	public static Properties config;
	public static Scenario scenario;
}
