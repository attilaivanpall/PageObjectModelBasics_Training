package com.ipa.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ipa.base.Page;
import com.ipa.pages.ZohoAppPage;
import com.ipa.pages.crm.CRMHomePage;
import com.ipa.pages.crm.accounts.AccountsPage;
import com.ipa.pages.crm.accounts.CreateAccountPage;
import com.ipa.utilities.Utilities;

public class CreateAccountTest extends BaseTest{
	
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void createAccountTest(Hashtable<String, String> table) {
		
		//Page.test = Page.extent.createTest("createAccountTest");
		
		ZohoAppPage zp = new ZohoAppPage();
		Page.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Page.OR.getProperty("crmBtn_CSS"))));
		CRMHomePage crmHomePage = zp.goToCRM();
		AccountsPage accountsPage = Page.menu.goToAccounts();
		CreateAccountPage createAccountPage = accountsPage.goToCreateAccountPage();
		createAccountPage.createAccount(table.get("accountname"));
		
		Page.verifyEquals("a", "b");
		
		//Page.test.log(Status.PASS, "createAccount is successful.");
		//Page.log.info("createAccount is successful.");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
