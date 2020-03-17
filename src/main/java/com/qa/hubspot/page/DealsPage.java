package com.qa.hubspot.page;

import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;

public class DealsPage extends BasePage {
	
	public WebDriver driver;
	
	public DealsPage(WebDriver driver) {
		this.driver=driver;
		System.out.println("deals page constructor...");
		
	}

}
