package pageObjects;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage {

	public WebDriver driver;
	public FileReader locatorsReader;
	public Properties locatorProperties;
	
	public SearchResultPage(WebDriver driver) {
		
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
	
	By OkayMessage = By.xpath(getLocatorPropFile().getProperty("okayMessage"));
	
	public boolean verifySearchResultPageisLanded() {
		
		
		if (driver.findElements(OkayMessage).size() > 0) {
			return true;
		} else {
			return false;

		}

	}
	
}
