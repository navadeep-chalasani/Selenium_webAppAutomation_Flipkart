package com.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver,45);
	}
	
	//abstract methods
	
	public abstract void pageRefresh();
	
	public abstract WebElement getElement(By locator);
	
	public abstract List<WebElement> getElements(By locator);
	
	public abstract void waitForVisibilityOfAllElementsLocated(By locator);
	
	public abstract void waitAllElementsPresent(By locator);
	
	public abstract void waitForElementPresent(By locator);
	
	public abstract void waitForPageTitle(String title);
	
	//generic method
	
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		 try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		return null;
	}
	
	
	
}
