# Socrates-Reporting-Automation
Project to automate the UI testcases of Socrates-Reporting-Automation 

## 1. Download the project.

In order to start using the project you need to create your own Fork on Github and then clone the project:

```bash
git clone https://github.com/benchmarkeducation/socrates-reporting-automation.git
```

## 2.Setup
* Check configuration.properties for browsers and their os and versions to be included for the test 
* Set environment variables with your [CrossBrowser Username and Access Key] in configuration.properties

## 3.Running your tests
* To run a single test, run `mvn install -Dbrowser=<BROWSER>`
* To run particular test in multiple in parallel browsers, run `make -j 2 test_firefox test_ie .... n`
* To run parallel all tests, run `make run_all_in_parallel`


## Notes
* You need to provide webdrivers of individual browser on whome you want to execute test cases depend upon different OS.
* You need to configure "Make" i.e. "make-3.81-bin" on your machine.
* Need to set classpath for maven,make till bin(folder).
* `make -j 2 test_firefox test_ie .... n` , make -j 2 saying two instances of browser will launch (firefox & ie).
* You can view your test results on the [CrossBrowser Automate dashboard](https://app.crossbrowsertesting.com/selenium?tab=recent)

## 3.Lombok Cnfiguration for Logging.

* Add the following to your POM file:
```
	<dependency>
		<groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<version>1.16.20</version>
		<scope>provided</scope>
	</dependency>
	```
* Update your project
* Go to maven repository in your local system: e.g.C:\Users\HarshalT\.m2\repository\org\projectlombok\lombok\1.16.20
copy the path
* Open cmd and go to the above path location and paste the following command:
java -jar lombok-1.16.20.jar
enter.
* Restart your eclipse and update the project.
