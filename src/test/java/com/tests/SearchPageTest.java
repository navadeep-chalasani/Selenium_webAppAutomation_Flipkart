package com.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pageobjects.HomePage;
import com.pageobjects.SearchResultPage;

public class SearchPageTest extends BaseTest {
	HomePage hmpg;
	SearchResultPage srchPg;
	
	@Test()
	public void verifySearchResults() {
		hmpg = page.getInstance(HomePage.class);
		hmpg.closeLoginPopup();
		srchPg = hmpg.enterSearchInput("shoes");
		boolean hasFootwear = srchPg.areSearchResultsOfProperBreadcrumb("Footwear");
		boolean hasShoe = srchPg.checkIfEachResultIsOfShoe();
		SoftAssert softAssertion= new SoftAssert();
		softAssertion.assertTrue(hasFootwear);
		softAssertion.assertTrue(hasShoe);
		softAssertion.assertAll();
	}
	

	@Test(dependsOnMethods = { "verifySearchResults" })
	@Parameters({"brand"})
	public void validateifProductIsFromSelectedBrand(String brand) {
		srchPg.selectFilter(brand);
		boolean areProductsOfSelectedBrand = srchPg.validateIfResultsAreOfSelectedBrandFilter(brand);
		Assert.assertTrue(areProductsOfSelectedBrand);
	}
	
	@Test(dependsOnMethods = { "verifySearchResults", "validateifProductIsFromSelectedBrand"})
	@Parameters({"maxPrice"})
	public void validateifProductsInPriceRange(String maxPrice) {
		srchPg.selectMaxPrice(maxPrice);;
		boolean areProductsInPriceRanges = srchPg.validateIfResultsAreInPriceRange(maxPrice);
		Assert.assertTrue(areProductsInPriceRanges);
	}
	

	
	
}
