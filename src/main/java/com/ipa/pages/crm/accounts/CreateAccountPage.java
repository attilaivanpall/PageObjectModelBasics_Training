package com.ipa.pages.crm.accounts;

import com.ipa.base.Page;

public class CreateAccountPage extends Page{

	public void createAccount(String accountName) {
		
		type("accountNameFld_CSS", accountName);
		
	}
}
