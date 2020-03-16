package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.ElementUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	// 1. By Locators
	By header = By.className("private-page__title");
	By accountName = By.cssSelector("span.account-name");
	
	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");
	
	// 2. Constructor. 
	public HomePage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//3. Page Methods:
	public String getHomePageTitle() {
		elementUtil.waitForTitlePresent(Constants.HOME_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	

	public String getHomePageHeader() {
		elementUtil.waitForelementPresent(header);
		return elementUtil.doGetText(header);
	}
	
	
	public String getLoggedInUserAccountName() {
		elementUtil.waitForelementPresent(accountName);
		return elementUtil.doGetText(accountName);
	}
	
	
	public void clickOnContacts() {
		elementUtil.waitForelementPresent(mainContactsLink);
		elementUtil.doClick(mainContactsLink);
		
		elementUtil.waitForelementPresent(childContactsLink);
		elementUtil.doClick(childContactsLink);
		
	}
	
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	
}
