package com.qa.hubspot.tests;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {
	
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp() throws InterruptedException {
	basePage = new BasePage();
	prop = basePage.init_properties();
	String browser = prop.getProperty("browser");
	driver = basePage.init_driver(browser);
	driver.get(prop.getProperty("url"));
	loginPage = new LoginPage(driver);
	userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));

	homePage = loginPage.doLogin(userCred);
		
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyHomePageHeaderTest() {
		String headerValue = homePage.getHomePageHeader();
		Assert.assertEquals(headerValue, Constants.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3)
	public void verifyLoggedInUserAccountName() {
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("Logged In Account Name is :" +accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	

	

}
