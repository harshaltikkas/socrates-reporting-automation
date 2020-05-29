package com.bec.reporting.tests;

import java.util.Properties;

import cucumber.api.Scenario;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "@target/failed_scenarios.txt",//Cucumber execute failed scenarios from this file
glue = { "com.bec.reporting.steps" },
plugin = { "pretty", 		
		   "com.bec.reporting.utils.ExtentCucumberFormatter:target1/cucumber-reports/extent_report_failed/report.html",		 
		   "rerun:target1/failed_scenarios.txt"
		   },
tags = { "@Feature" }, 
monochrome = true)
public class _TestRunnerIT {
	public static Properties config;
	public static Scenario scenario;
}
