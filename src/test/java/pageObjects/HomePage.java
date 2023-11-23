package pageObjects;

import java.io.FileReader;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utilities.StringManipulation;

import org.openqa.selenium.support.ui.Wait; 



public class HomePage {

	public WebDriver driver;
	public FileReader locatorsReader;
	public Properties locatorProperties;
	StringManipulation stringManipulation = new StringManipulation();
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;

	}

	public Properties getLocatorPropFile() {

		try {

			if (locatorProperties == null) {
				locatorProperties = new Properties();
				locatorsReader = new FileReader(System.getProperty("user.dir") + "/configfiles/locators.properties");
				locatorProperties.load(locatorsReader);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return locatorProperties;

	}

	By Iframe_notification = By.xpath(getLocatorPropFile().getProperty("iframe_notification"));
	By Ad_PopupCloseBtn = By.xpath(getLocatorPropFile().getProperty("ad_Popup"));
	By Accept_btn = By.xpath(getLocatorPropFile().getProperty("accept_btn"));
	By Flights_rdio_btn = By.xpath(getLocatorPropFile().getProperty("flights_rdio_btn"));
	By Modaldialogue_btn = By.xpath(getLocatorPropFile().getProperty("modaldialogue_btn"));
	By Roundtrip_rdio_btn = By.xpath(getLocatorPropFile().getProperty("roundtrip_rdio_btn"));
	By FromCity_Input_id = By.id(getLocatorPropFile().getProperty("fromCity_Input_id"));
	By Hyderabad_fromcity_dropdown = By.xpath(getLocatorPropFile().getProperty("Hyderabad_fromcity_dropdown"));
	By ToCity_Input_id = By.id(getLocatorPropFile().getProperty("toCity_Input_id"));
	By Chennai_fromcity_dropdown = By.xpath(getLocatorPropFile().getProperty("chennai_fromcity_dropdown"));
	By SearchButton = By.xpath(getLocatorPropFile().getProperty("searchButton"));
	By DepartureMonths = By.xpath(getLocatorPropFile().getProperty("departureMonths"));
	By ToMonth = By.xpath(getLocatorPropFile().getProperty("toMonth"));
	By ReturnMonths = By.xpath(locatorProperties.getProperty("returnMonths"));
	

	public String getTitleLandingPage() {
		return driver.getTitle();
	}
	
	public HomePage close_adPopUp() {
		
		
		int numberOfWindows = driver.getWindowHandles().size();
		
		if(driver.findElements(Iframe_notification).size()>0) {
			driver.switchTo().frame(driver.findElement(Iframe_notification));
		}
	
		
		System.out.println("===> the number of windows is "+numberOfWindows);
		
		
		  if(driver.findElements(Ad_PopupCloseBtn).size()>0) {
			  driver.findElement(Ad_PopupCloseBtn).click();
		  System.out.println("======> pop up displayed");
		  driver.switchTo().defaultContent(); } else {
		  System.out.println("======> pop up NOT displayed"); }
		 
		return this;
		
	}

	public HomePage acceptPageCookies() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(Accept_btn)));
		driver.findElement(Accept_btn).click();
		return this;
	}

	public HomePage clickFlightRaidoButton() {
		 driver.findElement(Flights_rdio_btn).click();
		 return this;
				  
	}
	
	public HomePage dismissModalDialogue() {
		
		if(driver.findElements(Modaldialogue_btn).size()>0) {
			driver.findElement(Modaldialogue_btn).click();
		}else {
			System.out.println("Modal dialogue pop up not displayed");
		}		
		 return this;
				  
	}
	public HomePage clickRoundTripRaidoButton() {
		 driver.findElement(Roundtrip_rdio_btn).click();
		 return this;
				  
	}
	public HomePage enterFromLocation(String fromLocation) {
		WebElement fromLocationElement =
				  driver.findElement(FromCity_Input_id);
		fromLocationElement.sendKeys(fromLocation);
				  driver.findElement(Hyderabad_fromcity_dropdown).click();
		 return this;
				  
	}
	
	public HomePage enterToLocation(String toLocation) {

		WebElement toLocationElement = driver.findElement(ToCity_Input_id);
		toLocationElement.click();
		toLocationElement.sendKeys(toLocation);
		driver.findElement(Chennai_fromcity_dropdown).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this;
				  
	}
	
	public HomePage enterStartDate(String startDate) {
		System.out.println("==========> Start of Date picker");
		String departureDateInput = startDate;
		String departureJourneyMonthYear = stringManipulation.getStringWithIndex(departureDateInput, 2);
		String departureJourneyDate = stringManipulation.getStringWithIndex(departureDateInput, 0, 2);

		System.out.println("The string indexed " + departureJourneyMonthYear);
		
		
		
		int i = 0;
		label1: do {
			List<WebElement> departureMonths = driver
					.findElements(DepartureMonths);
			System.out.println(departureMonths.size());
			if (departureMonths.size() > 0) {
				for (WebElement month : departureMonths) {
					System.out.println("The text is " + month.getText());
					i = i + 1;
					System.out.println("The j values is " + i);
					if (month.getText()
							.equalsIgnoreCase(stringManipulation.getStringWithoutSpace(departureJourneyMonthYear))) {
						month.click();

						WebElement elem = driver.findElement(By.xpath("//*[@class='DayPicker-Month'][" + i
								+ "]//div[@class='DayPicker-Day']//p[text()='" + departureJourneyDate
								+ "']/ancestor::div[@class='DayPicker-Day' and contains(@aria-label,'"
								+ departureJourneyDate + "')]"));
						elem.click(); // Thread.sleep(2000); i = 0;
						System.out.println("The i value after resetting to 0 is " + i);
						break label1;

					}
				}
			}
			if (i >= departureMonths.size()) {
				System.out.println("Im inside the departure month navigation....");
				WebElement toMonth = driver.findElement(ToMonth);
				toMonth.click();
				i = 0;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (true);
		 return this;
				  
	}
	
	
	public HomePage enterReturnDate(String returnParamDate) {
		System.out.println("=================== Return date picker ===== ");
		String returnDateInput = returnParamDate;

		String returnJourneyMonthYear = stringManipulation.getStringWithIndex(returnDateInput, 2);
		String returnJourneyDate = stringManipulation.getStringWithIndex(returnDateInput, 0, 2);
		System.out.println("The return Journey date is after manipulation " + returnJourneyDate);
		System.out.println("The string indexed " + returnJourneyMonthYear);
		
		int j = 0;
		label1: do {
			List<WebElement> returnMonths = driver
					.findElements(ReturnMonths);
			System.out.println(returnMonths.size());
			if (returnMonths.size() > 0) {
				for (WebElement month : returnMonths) {
					System.out.println("The text is " + month.getText());
					j = j + 1;
					System.out.println("The j values is " + j);
					if (month.getText()
							.equalsIgnoreCase(stringManipulation.getStringWithoutSpace(returnJourneyMonthYear))) {
						month.click();

						WebElement elem = driver.findElement(By.xpath("//*[@class='DayPicker-Month'][" + j
								+ "]//div[@class='DayPicker-Day']//p[text()='" + returnJourneyDate
								+ "']/ancestor::div[@class='DayPicker-Day' and contains(@aria-label,'"
								+ returnJourneyDate + "')]"));
						elem.click();
				//		Thread.sleep(2000);
						j = 0;
						System.out.println("The j value after resetting to 0 is " + j);
						break label1;

					}
				}
			}
			if (j >= returnMonths.size()) {
				System.out.println("Im inside the month navigation....");
				WebElement toMonth = driver.findElement(ToMonth);
				toMonth.click();
				j = 0;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (true);
		 return this;
				  
	}
	
	public SearchResultPage clickSearch() {

		WebElement searchButton = driver.findElement(SearchButton);
		searchButton.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SearchResultPage(driver);
				  
	}
	
}
