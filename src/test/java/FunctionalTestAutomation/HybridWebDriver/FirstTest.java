package FunctionalTestAutomation.HybridWebDriver;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mks.dateUtil.SystemDateTime;
import com.mks.directorizer.FilesInvestigator;
import com.mks.seluty.Finder;

public class FirstTest {
	
	
	@Test
	public void testMethod() {
		/*
		String file = "E:\\eclipse-workspace\\HybridWebDriver\\FunctionalTestAutomation\\ReferenceDocs\\ExampleWebSites.txt";
		
		FilesInvestigator fi = new FilesInvestigator();
		
		System.out.println(" File finder is : " + fi.findFile(file));
		
		System.out.println("found --- " + fi.findFile("E:\\eclipse-workspace\\HybridWebDriver", "pom.xml"));
		
		
		System.out.println("====== " + System.getProperty("user.dir"));
		*/
		
		SystemDateTime sdt = new SystemDateTime();
		sdt.getCurrentSystemDate();
		sdt.getCurrentSystemTime();
		sdt.getCurrentSystemDateAndTime();
		System.out.println("==============");
		System.out.println("Foarmt is dd/MM/yyyy : " + sdt.getCurrentSystemDate("dd/MM/yyyy"));
		System.out.println("Foarmt is yyyy/dd/MM : " + sdt.getCurrentSystemDate("yyyy/dd/MM"));
		System.out.println("Foarmt is yyyy/MM/dd : " + sdt.getCurrentSystemDate("yyyy/MM/dd"));
		
		System.out.println("Foarmt is dd/MMM/yyyy : " + sdt.getCurrentSystemDate("dd/MMM/yyyy"));
		System.out.println("Foarmt is MMM/dd/yyyy : " + sdt.getCurrentSystemDate("MMM/dd/yyyy"));
		System.out.println("Foarmt is yyyy/dd/MMM : " + sdt.getCurrentSystemDate("yyyy/dd/MMM"));
		System.out.println("Foarmt is yyyy/MMM/dd : " + sdt.getCurrentSystemDate("yyyy/MMM/dd"));
		
		
		System.out.println("==============");
		System.out.println("Foarmt is dd/MM/yyyy : " + sdt.getCurrentSystemDate("dd-MM-yyyy"));
		System.out.println("Foarmt is yyyy/dd/MM : " + sdt.getCurrentSystemDate("yyyy-dd-MM"));
		System.out.println("Foarmt is yyyy/MM/dd : " + sdt.getCurrentSystemDate("yyyy-MM-dd"));
		
		System.out.println("Foarmt is dd/MMM/yyyy : " + sdt.getCurrentSystemDate("dd-MMM-yyyy"));
		System.out.println("Foarmt is MMM/dd/yyyy : " + sdt.getCurrentSystemDate("MMM-dd-yyyy"));
		System.out.println("Foarmt is yyyy/dd/MMM : " + sdt.getCurrentSystemDate("yyyy-dd-MMM"));
		System.out.println("Foarmt is yyyy/MMM/dd : " + sdt.getCurrentSystemDate("yyyy-MMM-dd"));
		
		
		
		
		
		
		
		System.out.println("==============");
		System.out.println("Foarmt is dd/MM/yyyy : " + sdt.getCurrentSystemDate("dd/MM"));
		System.out.println("Foarmt is yyyy/dd/MM : " + sdt.getCurrentSystemDate("yyyy/MM"));
		System.out.println("Foarmt is yyyy/MM/dd : " + sdt.getCurrentSystemDate("yyyy/dd"));
		
		System.out.println("Foarmt is dd/MMM/yyyy : " + sdt.getCurrentSystemDate("MMM/yyyy"));
		System.out.println("Foarmt is MMM/dd/yyyy : " + sdt.getCurrentSystemDate("dd/yyyy"));
		System.out.println("Foarmt is yyyy/dd/MMM : " + sdt.getCurrentSystemDate("yyyy/MMM"));
		
		System.out.println("==============");
		System.out.println("Foarmt is dd/MM/yyyy : " + sdt.getCurrentSystemDate("dd-MM"));
		System.out.println("Foarmt is yyyy/dd/MM : " + sdt.getCurrentSystemDate("yyyy-MM"));
		System.out.println("Foarmt is yyyy/MM/dd : " + sdt.getCurrentSystemDate("yyyy-dd"));
		
		System.out.println("Foarmt is dd/MMM/yyyy : " + sdt.getCurrentSystemDate("MMM-yyyy"));
		System.out.println("Foarmt is MMM/dd/yyyy : " + sdt.getCurrentSystemDate("dd-yyyy"));
		System.out.println("Foarmt is yyyy/dd/MMM : " + sdt.getCurrentSystemDate("yyyy-MMM"));
		
		
		System.out.println("==============");
		System.out.println("Foarmt is dd/MM/yyyy : " + sdt.getCurrentSystemDate("dd"));
		System.out.println("Foarmt is yyyy/dd/MM : " + sdt.getCurrentSystemDate("MM"));
		System.out.println("Foarmt is yyyy/MM/dd : " + sdt.getCurrentSystemDate("yyyy"));
		
		
		
		
		
		
		/*
		sdt.getCurrentSystemTime();
		sdt.getCurrentSystemDateAndTime();
		*/
		
	}
	
	
	
	
}
