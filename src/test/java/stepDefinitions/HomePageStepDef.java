package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.test.base.TestContextSetup;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.SearchResultPage;



public class HomePageStepDef {
	
	public WebDriver driver;
	
	public HomePage homePage;
	public SearchResultPage searchResultPage;
	TestContextSetup testContextSetup;
	
	public HomePageStepDef(TestContextSetup testContextSetup)
	{
		this.testContextSetup=testContextSetup;
		this.homePage = testContextSetup.pageObjectManager.getHomePage();
		this.searchResultPage = testContextSetup.pageObjectManager.getSearchResultPage();
		
	}

	@Given("User is on MakeMyTrip Landing page")
	public void user_is_on_make_my_trip_landing_page() {
		Assert.assertTrue(homePage.getTitleLandingPage().contains("MakeMyTrip"));
	}
	
	@Given("user click on close on Ad pop up if exist")
	public void user_click_on_close_on_ad_pop_up_if_exist() {
		homePage
		.close_adPopUp()
		.acceptPageCookies();
	}
	@Given("User click on Flights radio button")
	public void user_click_on_flights_radio_button() {
		homePage.clickFlightRaidoButton();
	    
	}
	@Given("User dismiss the modaldialgue pop up if exist")
	public void user_dismiss_the_modaldialgue_pop_up_if_exist() {
		homePage.dismissModalDialogue();
	    
	}
	@Given("User click on RoundTrip radio button")
	public void user_click_on_round_trip_radio_button() {
		homePage.clickRoundTripRaidoButton();
	}
	@Given("User enters the from city as {string}")
	public void user_enters_the_from_city_as(String fromLocation) {
		homePage.enterFromLocation(fromLocation);
	}
	@Given("User enters the to city as {string}")
	public void user_enters_the_to_city_as(String toLocation) {
		homePage.enterToLocation(toLocation);
	}
	@Given("^User the enters the start date as (.+)$")
	public void user_the_enters_the_start_date_as(String startDate) {
		homePage.enterStartDate(startDate);
	}
	@Given("^User enters the return date as (.+)$")
	public void user_enters_the_return_date_as(String returnDate) {
		homePage.enterReturnDate(returnDate);
	}
	@When("User click on search button")
	public void user_click_on_button() {
		homePage.clickSearch();
	}
	@Then("User should see the search result page successfully")
	public void user_should_see_the_search_result_page_successfully() {
		//searchResultPage.verifySearchResultPageisLanded();
		Assert.assertEquals(searchResultPage.verifySearchResultPageisLanded(), true);
	}
	
}
