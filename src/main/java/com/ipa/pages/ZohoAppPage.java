package com.ipa.pages;

import com.ipa.base.Page;
import com.ipa.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page{
	
	public CRMHomePage goToCRM() {
		
		click("crmBtn_CSS");
		
		return new CRMHomePage();
	}
}
