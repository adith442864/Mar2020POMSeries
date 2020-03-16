package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class ContactsPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	// By locators:
	By createContactButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]");
	By createContactFormButton = By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]");

	By email = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");
	
	By contactsClick = By.xpath("(//i18n-string[text()='Contacts'])[1])");
	

	public ContactsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	
	public String getContactsPageTitle() {
		elementUtil.waitForTitlePresent("Contacts");
		return elementUtil.doGetPageTitle();
	}
	
	
	public void createNewContact(String mail, String fn, String ln, String jobtitle) {
		
		elementUtil.waitForelementPresent(createContactButton);
		elementUtil.doClick(createContactButton);
		
		elementUtil.waitForelementPresent(email);
		elementUtil.doSendKeys(email, mail);
		
		elementUtil.waitForelementPresent(firstName);
		elementUtil.doSendKeys(firstName, fn);
		
		elementUtil.doActionSendKeys(lastName, ln);
		
		elementUtil.waitForelementPresent(jobTitle);
		elementUtil.doSendKeys(jobTitle, jobtitle);
		
//		elementUtil.doClick(createContactFormButton);
		jsUtil.clickElementByJS(elementUtil.getElement(createContactFormButton));
		
//		elementUtil.waitForelementPresent(contactsClick);
//		elementUtil.doClick(contactsClick);
		
		
	}
	

}
