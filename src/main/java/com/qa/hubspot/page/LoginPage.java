package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class LoginPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	// 1. By Locators
	By emailId = By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By loginErrorText = By.xpath("//div[@class='private-alert__inner']/h5");
	
	// 2. Constructor..
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	//3. Page Methods/Actions:
	
	public String getPageTitle() {
		elementUtil.waitForTitlePresent(Constants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public String getPageTitleUsingJs() {
		return jsUtil.getTitleByJS();
	}
	
	
	public boolean checkSignUpLink() {
		elementUtil.waitForelementPresent(signUpLink);
		return elementUtil.doIsDisplayed(signUpLink);
	}
	
	//passing multiple params in doLogin method is not a good approach.
	// getusername, password from Credentials class. 
	
	public  HomePage doLogin(Credentials userCred)  {
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		elementUtil.waitForelementPresent(emailId);
		elementUtil.doSendKeys(emailId, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginBtn);
		return new HomePage(driver);
		
	}
	
	
	public boolean checkLoginErrorMsg() {
		return elementUtil.doIsDisplayed(loginErrorText);
	}
	

}
