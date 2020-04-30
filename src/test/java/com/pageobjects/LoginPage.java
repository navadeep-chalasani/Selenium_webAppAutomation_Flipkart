package com.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	private By pageHeading = By.xpath("//span[contains(text(),'Login or Signup')]");
	
	public WebElement getHeaderElement() {
		waitForElementPresent(pageHeading);
		return getElement(pageHeading);
	}
	
	public String getHeaderText() {
		
		return getHeaderElement().getText();
		
	}

}
