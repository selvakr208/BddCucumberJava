Feature: Launch and search the MakeMyTrip website 


@SmokeTest 
Scenario Outline: Launch the homepage and search for flights 

	Given User is on MakeMyTrip Landing page
	And user click on close on Ad pop up if exist 
	And User click on Flights radio button 
	And User dismiss the modaldialgue pop up if exist
	And User click on RoundTrip radio button 
	And User enters the from city as "HYD" 
	And User enters the to city as "MAA" 
	And User the enters the start date as <StartDate> 
	And User enters the return date as <ReturnDate> 
	When User click on search button 
	Then User should see the search result page successfully 
	
	
	
	Examples: 
		| StartDate         | ReturnDate      |
		| 29 December 2023 	| 24 February 2024  |