package com.ipa.pages;

import com.ipa.base.Page;

public class HomePage extends Page{

	
	public LoginPage goToLogin() {
		
		click("loginBtn_CSS");
		
		return new LoginPage();
	}
	
	
	
}
