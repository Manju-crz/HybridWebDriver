package FunctionalTestAutomation.HybridWebDriver;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mks.directorizer.FilesInvestigator;
import com.mks.seluty.Finder;

public class FirstTest {
	
	
	
	
	@Test
	public void testMethod() {
		
		String file = "E:\\eclipse-workspace\\HybridWebDriver\\FunctionalTestAutomation\\ReferenceDocs\\ExampleWebSites";
		
		FilesInvestigator fi = new FilesInvestigator();
		
		System.out.println(" File finder is : " + fi.findFile(file));
		
	}
	
	
}
