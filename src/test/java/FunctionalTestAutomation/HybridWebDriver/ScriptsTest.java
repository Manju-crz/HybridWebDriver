package FunctionalTestAutomation.HybridWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.mks.connectors.Connection;
import com.mks.connectors.Connection.Browser;
import com.mks.legacy.MksCheckbox;
import com.mks.legacy.MksLink;
import com.mks.utilizer.SoftSleeper;

public class ScriptsTest {
	
	static String URL = "http://the-internet.herokuapp.com/";
	static By firstPageLinks = By.xpath("//ul/li/a");
	
	static By checkboxes = By.xpath("//input[@type='checkbox']");
	static By firstCheckbox = By.xpath("(//input[@type='checkbox'])[1]");
	static By secondCheckbox = By.xpath("(//input[@type='checkbox'])[2]");
	
	
	
	
	@Test
	public void testMethod() {
		
		WebDriver driver = new Connection(Browser.CHROME).launchBrowser(URL);
		System.out.println("=== " + new MksLink(firstPageLinks).click("Checkboxes"));
		SoftSleeper.seconds(3);
		System.out.println("Checbx === " + new MksCheckbox(checkboxes).click("checkbox 1"));
		SoftSleeper.seconds(3);
		
		
		Connection.closeDriverBrowsers();
		
		
		
		
	}
	
	
	
	
}
