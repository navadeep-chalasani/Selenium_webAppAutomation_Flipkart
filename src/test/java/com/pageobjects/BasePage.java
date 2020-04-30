package com.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage  extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public WebElement getElement(By locator) {
		
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Some error occured while creating element"+locator.toString());
			e.printStackTrace();
		}
		return element;
	}

	@Override
	public void waitForElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Some error occured when trying to locate "+locator.toString());
			e.printStackTrace();
		}
	}
	@Override
	public void waitForVisibilityOfAllElementsLocated(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			System.out.println("Some error occured when trying to locate "+locator.toString());
			e.printStackTrace();
		}
	}
	
	public void waitForVisibilityOfElementLocated(By locator) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Some error occured when trying to locate "+locator.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void waitForPageTitle(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));			
		} catch (Exception e) {
			System.out.println("Some error occured when trying to wait for title "+title);
			e.printStackTrace();
		}		
	}

	@Override
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	@Override
	public void waitAllElementsPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception e) {
			System.out.println("Some error occured when trying to locate "+locator.toString());
			e.printStackTrace();
		}
		
	}

	@Override
	public void pageRefresh() {
		driver.navigate().refresh();
	}


}
