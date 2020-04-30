package com.tests;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.pageobjects.BasePage;
import com.pageobjects.Page;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

	private WebDriver driver;
	Page page;
	public Properties prop;
	
	@BeforeClass
	public void setUpTest(ITestContext context) {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(prop.getProperty("browser").equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(prop.getProperty("browser").equals("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			System.out.println("no browser is defined in the xml file");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		page = new BasePage(this.driver);
	}
	

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
