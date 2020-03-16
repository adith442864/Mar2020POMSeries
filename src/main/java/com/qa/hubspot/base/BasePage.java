package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
//	public WebDriver driver;
	public Properties prop;
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	
	public WebDriver init_driver(String browserName) {
		
		highlightElement = prop.getProperty("highlight").equals("yes") ? true : false ;
		System.out.println("browser name is :"+browserName);
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equals("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		
//			if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
//				ChromeOptions co= new ChromeOptions();
//				co.addArguments("--headless");
//				driver = new ChromeDriver(co);
//			}
//			else {
//				driver = new ChromeDriver();
//			}
			
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
//			if(prop.getProperty("headless").equalsIgnoreCase("yes")) {
//				FirefoxOptions fo = new FirefoxOptions();
//				fo.addArguments("--headless");
//				driver = new FirefoxDriver(fo);
//			}
//			else {
//				driver = new FirefoxDriver();
//			}
			
		}
		else if(browserName.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			tldriver.set(new EdgeDriver());
			
		}
		else {
			System.out.println("browser name "+browserName +"is not found, please pass the correct browser");
		}
		
		getDriver().manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
		
//		driver.get("https://app.hubspot.com/login");
		return getDriver();
		
	}
	
	
	public Properties init_properties() {
		
		prop = new Properties();
		String path = null;
		String env =null;
		
		try {
			env = System.getProperty("env");
			
			if(env.equals("qa")) {
				path = "D:\\Workspace\\Mar2020POMSeries\\src\\main"
						+ "\\java\\com\\qa\\hubspot\\config\\qa.config.properties";
			} else if(env.equals("stg")) {
				path = "D:\\Workspace\\Mar2020POMSeries\\src\\main\\java"
						+ "\\com\\qa\\hubspot\\config\\stg.config.properties";
			}
		} catch(Exception e) {
			path = "D:\\Workspace\\Mar2020POMSeries\\src\\main"
					+ "\\java\\com\\qa\\hubspot\\config\\config.properties";
		}
			
		try {
			FileInputStream fs = new FileInputStream(path);
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	/**
	 * take screenshot
	 * @return
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("screenshot captured failed...");
		}

		return path;

	}
	
	

}
