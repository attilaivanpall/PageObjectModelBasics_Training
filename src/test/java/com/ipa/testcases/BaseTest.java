package com.ipa.testcases;

import org.testng.annotations.AfterSuite;

import com.ipa.base.Page;

public class BaseTest{

	@AfterSuite
	public void tearDown() {
		
		Page.quit();
		
	}
}
