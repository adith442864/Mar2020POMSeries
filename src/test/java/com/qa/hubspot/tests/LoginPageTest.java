package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.Credentials;



public class LoginPageTest {
	
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp() {
		
		basePage= new BasePage();
		prop = basePage.init_properties();
		String browser = prop.getProperty("browser");
		driver = basePage.init_driver(browser);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority=1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getPageTitle();
		System.out.println("login page title is:"+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
			
	}
	
	@Test(priority=2)
	public void verifySignUpLinkTest() {
		Assert.assertTrue(loginPage.checkSignUpLink());
	}
	
	@Test(priority=3)
	public void doLoginTest()  {
		homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getLoggedInUserAccountName();
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
	}
	
	
	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][]= 
			{{"test1@gmail.com","test123"}, 
				{"test","test"},
				{" ", "test1234 "}
			};
		
		return data;
	}
	
// Negative Test Case:
	@Test(priority=4, dataProvider="getLoginInvalidData", enabled=false)
	public void login_InvalidTestCases(String username, String pwd)  {
		userCred.setAppUsername(username); //setter
		userCred.setAppPassword(pwd); // setter
		loginPage.doLogin(userCred);
		
		Assert.assertTrue(loginPage.checkLoginErrorMsg());
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
		

}
