package FunctionalTestAutomation.HybridWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.mks.advanced.MksAdvCheckbox;
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
		new MksLink(firstPageLinks).click("Checkboxes");
		SoftSleeper.seconds(3);
		
		MksCheckbox ch = new MksCheckbox(firstCheckbox);
		System.out.println("Is selected : " + ch.isSelected());
		ch.click();
		SoftSleeper.seconds(2);
		System.out.println("Is selected : " + ch.isSelected());
		
		MksCheckbox ch1 = new MksCheckbox(secondCheckbox);
		System.out.println("Is selected : " + ch1.isSelected());
		ch1.click();
		SoftSleeper.seconds(2);
		
		Connection.closeDriverBrowsers();
		
	}
	
	
	
	
}
