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
package com.bec.reporting.tests;

import java.util.Properties;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".", glue = { "com.bec.reporting.steps" }, plugin = { "pretty", "html:target/htmlreports",
		"com.bec.reporting.utils.ExtentCucumberFormatter:target/cucumber-reports/extent_report/report.html",
		"json:target/json_report/Cucumber.json", "junit:target/junit_xml_report/Cucumber.xml",
		"rerun:target/failed_scenarios.txt" },

		tags = { "@Feature" }, monochrome = true)

public class TestRunner {

	public static Properties config;
	public static Scenario scenario;

}

	