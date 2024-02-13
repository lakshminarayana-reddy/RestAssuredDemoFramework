package com.petstore.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager implements ITestListener {
	public ExtentReports reports;
	public ExtentSparkReporter sparkReporter;
	public ExtentTest test;
	String reportName;
	@Override
	public void onTestStart(ITestResult result) {
		String timeStamp = new SimpleDateFormat("dd.mm.yyyy").format(new Date());
		reportName="Test_Report"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\"+reportName);
		sparkReporter.config().setDocumentTitle("RestAssuredDemoFramework");
		sparkReporter.config().setReportName("Pet store APIs");
		reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
		reports.setSystemInfo("User", "Lakshmi Narayana");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test=reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test=reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL,"Test Failed");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test=reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		if(reports!=null)
			reports.flush();
	}
	

}
