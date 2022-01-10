package com.ipa.pages.crm.accounts;

import com.ipa.base.Page;

public class AccountsPage extends Page{

	
	public CreateAccountPage goToCreateAccountPage() {
		
		click("createAccountBtn_CSS");
		
		return new CreateAccountPage();
	}
}
