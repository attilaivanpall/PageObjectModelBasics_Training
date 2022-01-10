package com.ipa.base;

import org.openqa.selenium.WebDriver;

import com.ipa.pages.crm.accounts.AccountsPage;

public class TopMenu {

	WebDriver driver;
	
	public TopMenu(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	
	
	public AccountsPage goToAccounts() {
		
		Page.click("accountsMenu_CSS");
		
		return new AccountsPage();
	}
	
	
	
}
