package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailPage extends BasePage {

	public ProductDetailPage(WebDriver driver) {
		super(driver);
	}
	

	private By size9Ele = By.xpath("//li[@id='swatch-3-size']");
	
	private By 	buyMeBtn = By.xpath("//button[@class='_2AkmmA _2Npkh4 _2kuvG8 _7UHT_c']");

// --------------------------- Element getters -----------------------------
	public WebElement getSize9() {
		waitForElementPresent(size9Ele);
		return getElement(size9Ele);
				
	}
	
	public WebElement getBuyNowBtn() {
		waitForElementPresent(buyMeBtn);
		return getElement(buyMeBtn);
				
	}
	// ------------------------- page actions -----------------------------
	public void selectSize() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,800)");
		getSize9().click();
	}
	
	public LoginPage clickBuyNow() {
		getBuyNowBtn().click();
		return getInstance(LoginPage.class);
	}

}
