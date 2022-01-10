package com.ipa.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ipa.base.Page;
import com.ipa.pages.HomePage;
import com.ipa.pages.LoginPage;
import com.ipa.pages.ZohoAppPage;
import com.ipa.utilities.Utilities;

public class LoginTest extends BaseTest{

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String, String> table) {
		
		
		//Page.test = Page.extent.createTest("loginTest");
		//Page.extent.createTest("loginTest").log(Status.INFO, "Navigation to Home page.");
		//test.log(Status.INFO, "Navigation to Home page.");
		HomePage home = new HomePage();
		//Page.log.info("Navigating to the Home page.");
		
		LoginPage lp = home.goToLogin();
		//test.log(Status.INFO, "Navigation to Login Page.");
		//Page.log.info("Navigation to Login Page.");
		
		ZohoAppPage zp = lp.doLogin(table.get("username"), table.get("password"));
		//test.log(Status.INFO, "Navigation to ZohoApp Page.");
		Page.test.log(Status.PASS, "Login is successful.");
		//Page.log.info("Navigation to ZohoApp Page.");
		Page.log.info("Login is successful.");
		
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
