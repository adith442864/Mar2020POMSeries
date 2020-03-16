package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil extends BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;
	Properties prop;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		jsUtil = new JavaScriptUtil(driver);
		prop = new Properties();
		
	}
	
	// Generic Methods:
	/**
	 * Explicit methods. 
	 * @param locator
	 * @return
	 */
	public boolean waitForelementPresent(By locator) {
		 wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 return true;
	}
	
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
	
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}

	
	
	/**
	 * This method is used to create the webelement on basis of By locator
	 * @param locator
	 * @return element
	 */
	public WebElement getElement(By locator) {
		WebElement element= null;
		try {
		//	if(waitForelementPresent(locator))		
			element = driver.findElement(locator);
			
			if(highlightElement) {
				jsUtil.flash(element);
			}
			
		} catch(Exception e) {
			System.out.println("some exception got occured while creating the web element...");
		}
		return element;
	}
	
	/**
	 * 
	 * @return
	 */
	public String doGetPageTitle() {
		return driver.getTitle();
	}
	
	
	/**
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch(Exception e) {
			System.out.println("some exception occured while clicking on the web element..");
		}
	}
	
	public void doActionClick(By locator){
		try {
			WebElement element = getElement(locator);
			Actions action = new Actions(driver);
			action.click(element);
		} catch (Exception e) {
			System.out.println("some exception got occurred while clicking on the web element.....");

		}
	}
	
	
	public void doActionSendKeys(By locator, String value){
		try {
			WebElement element = getElement(locator);
			Actions action = new Actions(driver);
			action.sendKeys(element, value);
		} catch (Exception e) {
			System.out.println("some exception got occurred while clicking on the web element.....");

		}
	}
	
	
	public void doSendKeys(By locator, String value) {
	try {	
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	} catch(Exception e) {
		System.out.println("some exception got occured while entering value in a field...");
		}
	}
	
	/**
	 * 
	 * @param locator
	 * @return boolean
	 */
	public boolean doIsDisplayed(By locator) {
	try {
		boolean flag = getElement(locator).isDisplayed();
		return flag;
		} catch (Exception e) {
		System.out.println("some exception got occured while element is displayed...");
		}
		return false;	
	}
	
	/**
	 * 
	 * @param locator
	 * @return String
	 */
	public String doGetText(By locator) {
		try {
			return getElement(locator).getText();
		}
		catch(Exception e) {
			System.out.println("some exception got occured while getting the text from webelement...");
		}
		return null;
	}
	
	
}
