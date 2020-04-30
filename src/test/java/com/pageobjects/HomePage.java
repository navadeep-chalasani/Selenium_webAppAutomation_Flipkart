package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	private By closeLoginPopUp = By.xpath("//button[@class='_2AkmmA _29YdH8']");
	private By searchInput = By.xpath("//input[@placeholder='Search for products, brands and more']");
	private By searchIconBtn = By.xpath("//button[@class='vh79eN']");

// --------------------------- Element getters -----------------------------
	public WebElement getCloseLoginPopUpBtn() {
		return getElement(closeLoginPopUp);
	}
	
	public WebElement getSearchEle() {
		return getElement(searchInput);
	}
	
	public WebElement getSearchIconBtn() {
		return getElement(searchIconBtn);
	}

// ------------------------- page actions -----------------------------
	
	public void closeLoginPopup() {
		getCloseLoginPopUpBtn().click();
	}
	
	// click on search and navigate Search results page
	public SearchResultPage enterSearchInput(String inputText) {
		getSearchEle().sendKeys(inputText);
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.RETURN)).build().perform();
		return getInstance(SearchResultPage.class);
	}


	

}

