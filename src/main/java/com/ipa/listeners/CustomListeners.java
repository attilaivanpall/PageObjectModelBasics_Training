package com.ipa.listeners;

import java.io.IOException;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ipa.base.Page;
import com.ipa.utilities.Utilities;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getName().toUpperCase());
		
		if(!Utilities.isTestRunnable(result.getName(), excel)) {
			
			throw new SkipException("Skipping testcase: " + result.getName() + " as the runmode is NO.");
			
		}
	}

	
	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS, "The execution of " + result.getName().toUpperCase() + " testcase is PASSED!");
		extent.flush();
		log.info("The execution of " + result.getName().toUpperCase() + " testcase is PASSED!");
		log.info("***************************");
		
	}

	
	public void onTestFailure(ITestResult result) {
		
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(Status.FAIL, "The execution of " + result.getName().toUpperCase() + " testcase is FAILED!");
		test.log(Status.FAIL, result.getThrowable());
		//test.log(Status.FAIL, test.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\target\\" + Utilities.screenshotName));
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotName).build());
		//test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotName).build());
		extent.flush();
		log.info("The execution of " + result.getName().toUpperCase() + " testcase is FAILED!");
		log.info(result.getThrowable());
		log.info("***************************");
		
	}

	
	public void onTestSkipped(ITestResult result) {
		
		test.log(Status.SKIP, "Skipping testcase: " + result.getName() + " as the runmode is NO.");
		extent.flush();
		log.info("Skipping testcase: " + result.getName() + " as the runmode is NO.");
		log.info("***************************");
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	
	public void onTestFailedWithTimeout(ITestResult result) {
		
		
	}

	
	public void onStart(ITestContext context) {
		
		
	}

	
	public void onFinish(ITestContext context) {
		
		
	}

}
