package cucumberOptions;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features", 
	glue = "stepDefinitions", 
	monochrome = true, 
	tags = "@SmokeTest", 
	plugin = {
		"pretty", 
		"html:target/cucumber.html", 
		"json:target/cucumber.json" 
		},
	dryRun=false
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel=true)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}

}
