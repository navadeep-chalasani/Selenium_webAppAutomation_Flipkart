package com.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageobjects.HomePage;
import com.pageobjects.LoginPage;
import com.pageobjects.ProductDetailPage;
import com.pageobjects.SearchResultPage;

public class ProductDetailPageTest extends BaseTest {
	HomePage hmpg;
	SearchResultPage srchPg; 
	ProductDetailPage pdg;
	LoginPage loginPg;
	
	@Test
	public void navigateToLoginPageWhenBuyingWithoutLogin() {
		hmpg = page.getInstance(HomePage.class);
		hmpg.closeLoginPopup();
		srchPg = hmpg.enterSearchInput("shoes");
		pdg =srchPg.navigateToFirstProduct();
		pdg.selectSize();
		loginPg = pdg.clickBuyNow();
		String headerText = loginPg.getHeaderText();
		Assert.assertEquals(headerText, "LOGIN OR SIGNUP");
		
	}
	


	

	
	
}
