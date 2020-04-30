package com.pageobjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchResultPage extends BasePage {

	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

	private By breadcrumbs = By.cssSelector("._1HEvv0 a");
	private By shoeSearchResultEle = By.xpath("//div[contains(@class,'IIdQZO _1R0K0g _1SSAGr')]");
	private By filter = By.cssSelector("._4IiNRh");
	private By filteredResult = By.xpath("//div[@class='_2LFGJH']");
	private By maxPriceFilter = By.cssSelector("._1YoBfV .fPjUPw");
	private By brandSearch = By.cssSelector("._3vKPvR");

// --------------------------- Element getters -----------------------------
	public List<WebElement> getBreadCrumbs() {
		waitForVisibilityOfAllElementsLocated(breadcrumbs);
		return getElements(breadcrumbs);
	}

	public List<WebElement> getAllShoeSearchResults() {
		waitForVisibilityOfAllElementsLocated(shoeSearchResultEle);
		return getElements(shoeSearchResultEle);
	}

	public List<WebElement> getAllFilters() {
		waitForVisibilityOfAllElementsLocated(filter);
		return getElements(filter);
	}

	public List<WebElement> getAllFilteredResults() {
		waitAllElementsPresent(filteredResult);
		return getElements(filteredResult);
	}

	public WebElement getMaxPriceDropDownElement() {
		waitForVisibilityOfElementLocated(maxPriceFilter);
		return getElement(maxPriceFilter);
	}
	public WebElement getBrandSearchElement() {
		waitForElementPresent(brandSearch);
		return getElement(brandSearch);
	}

// ------------------------- page actions -----------------------------

	public boolean areSearchResultsOfProperBreadcrumb(String reqBreadcrumb) {

		List<WebElement> breadCrumbs = getBreadCrumbs();
		boolean hasReqBreadcrumb = false;
		for (WebElement e : breadCrumbs) {

			if (e.getText().equals(reqBreadcrumb)) {
				hasReqBreadcrumb = true;
			}
		}
		return hasReqBreadcrumb;
	}

	public boolean checkIfEachResultIsOfShoe() {
		List<WebElement> resultEle = getAllShoeSearchResults();
		boolean hasShoe = false;
		for (WebElement e : resultEle) {
			String srcText = e.findElement(By.xpath(".//img")).getAttribute("src");
			if (srcText.contains("shoe")) {
				hasShoe = true;
			}
		}
		return hasShoe;
	}

	public void selectFilter(String filter)  {
		getBrandSearchElement().sendKeys(filter);
		List<WebElement> allFilters = getAllFilters();
		for (WebElement fe : allFilters) {
			String filterText = fe.findElement(By.xpath(".//div[@class='_1GEhLw']")).getText();
			if (filterText.equals(filter)) {
				fe.click();
				break;
			}
		}
	}

	public void selectMaxPrice(String maxPrice) {
		Select priceSelect = new Select(getMaxPriceDropDownElement());
		priceSelect.selectByValue(maxPrice);

	}

	public boolean validateIfResultsAreOfSelectedBrandFilter(String filter) {
		pageRefresh();
		List<WebElement> filteredResults = getAllFilteredResults();
		if (filteredResults.size() != 0) {
			boolean isOfSelectedBrand = true;
			for (WebElement fv : filteredResults) {
				String brandText = fv.findElement(By.cssSelector("div._2LFGJH > div:first-child")).getText();
				if (!brandText.equals(filter)) {
					isOfSelectedBrand = false;
					break;
				}
			}
			return isOfSelectedBrand;
		}
		return false;
	}

	public boolean validateIfResultsAreInPriceRange(String maxPrice) {
		pageRefresh();
		int max = Integer.parseInt(maxPrice);
		List<WebElement> filteredResults = getAllFilteredResults();
		if (filteredResults.size() != 0) {

			boolean isBetweenMinAndMax = true;
			for (WebElement fv : filteredResults) {
				String priceValue = fv.findElement(By.cssSelector("._1vC4OE")).getText();
				String s = priceValue.substring(1, priceValue.length());
				int price = Integer.parseInt(s);
				if (!(price > 0 && price <= max)) {
					isBetweenMinAndMax = false;
					break;
				}
			}
			return isBetweenMinAndMax;

		}

		return false;

	}
	
	public ProductDetailPage navigateToFirstProduct() {
		String currentHandle = driver.getWindowHandle();
		List<WebElement> resultEle = getAllShoeSearchResults();
		WebElement firstElement = resultEle.get(0);
		firstElement.click();
		
		Set<String> tabs = driver.getWindowHandles();
		for(String s : tabs) {
			if(!s.equals(currentHandle)) {
				driver.switchTo().window(s);
			}
		}
		pageRefresh();
		return getInstance(ProductDetailPage.class);
	}

}
