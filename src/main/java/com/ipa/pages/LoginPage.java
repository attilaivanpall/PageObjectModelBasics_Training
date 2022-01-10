package com.ipa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ipa.base.Page;

public class LoginPage extends Page{

	public ZohoAppPage doLogin(String username,
						String password) {
		
		type("loginIdFld_ID", username);
		click("nextBtn_ID");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("passwordFld_ID"))));
		type("passwordFld_ID", password);
		click("nextBtn_ID");
		
		return new ZohoAppPage();
	}
}
