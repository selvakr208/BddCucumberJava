package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

	public WebDriver driver;
	public HomePage homePage;
	public SearchResultPage searchResultPage;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage getHomePage() {

		homePage = new HomePage(driver);
		return homePage;
	}

	public SearchResultPage getSearchResultPage() {
		searchResultPage = new SearchResultPage(driver);
		return searchResultPage;
	}

}
