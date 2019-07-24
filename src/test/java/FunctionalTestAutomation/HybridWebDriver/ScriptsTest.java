package FunctionalTestAutomation.HybridWebDriver;

import java.awt.AWTException;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.List;

import javax.activity.InvalidActivityException;
import javax.management.AttributeNotFoundException;
import javax.naming.LinkException;
import javax.naming.directory.InvalidAttributesException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.mks.advanced.MksAdvCheckbox;
import com.mks.connectors.Connection;
import com.mks.connectors.Connection.Browser;
import com.mks.knob.ArmWindows;
import com.mks.legacy.MksCheckbox;
import com.mks.legacy.MksLink;
import com.mks.legacy.MksRadioButton;
import com.mks.seluty.Finder;
import com.mks.seluty.WdOperators;
import com.mks.utilizer.SoftSleeper;

public class ScriptsTest {

	static String URL = "http://the-internet.herokuapp.com/";
	static By firstPageLinks = By.xpath("//ul/li/a");

	static By checkboxes = By.xpath("//input[@type='checkbox']");
	static By firstCheckbox = By.xpath("(//input[@type='checkbox'])[1]");
	static By secondCheckbox = By.xpath("(//input[@type='checkbox'])[2]");
	
	//@Test
	public void testForIsDisplayed() {
		WebDriver driver2 = Connection.launchBrowser("https://www.binance.com/en/login");
		By currencyLocator = By.xpath("(//li[@role='menu'])[4]");
		By firstCurrencyType = By.xpath("(//li[@role='menu']//ul/li)[1]");
		
		WdOperators.moveMouseTo(Finder.find(currencyLocator));
		SoftSleeper.seconds(2);
		MksCheckbox ch2 = new MksCheckbox(firstCurrencyType);
		System.out.println("currencyElement.isDisplayed() : " + ch2.isDisplayed());
		WdOperators.moveMouseTo(Finder.find(By.id("login_input_login")));
		SoftSleeper.seconds(2);
		//currencyElement = Finder.find(firstCurrencyType);
		System.out.println("currencyElement.isDisplayed() : " + new MksCheckbox(firstCurrencyType).isDisplayed());
	}
	
	//@Test
	public void testForAttributesTest() {
		WebDriver driver2 = Connection.launchBrowser("http://www.echoecho.com/htmlforms09.htm");
		String checkBoxesXpath = "//input[@name='Checkbox']";
		String checkBoxXpaths = "//input[@type='checkbox']";
		
		List<WebElement> firstThreeElements = driver2.findElements(By.xpath(checkBoxesXpath));
		List<WebElement> firstSixElements = driver2.findElements(By.xpath(checkBoxXpaths));
		
		System.out.println("driver2.findElements(By.xpath(checkBoxesXpath)).size() is : " + firstThreeElements.size());
		System.out.println("driver2.findElements(By.xpath(checkBoxXpaths)).size() is : " + firstSixElements.size());
		
		System.out.println("firstThreeElements.get(0).getAttribute() is : " + firstThreeElements.get(0).getAttribute("name"));
		System.out.println("firstThreeElements.get(0).getAttribute() is : " + firstThreeElements.get(0).getAttribute("value"));
		System.out.println("firstThreeElements.get(0).getAttribute() is : " + firstThreeElements.get(0).getAttribute("checked"));
		System.out.println("firstThreeElements.get(0).getAttribute() is : " + firstThreeElements.get(0).getAttribute("micked"));
		
		System.out.println("firstSixElements.get(0).getAttribute() is : " + firstSixElements.get(4).getAttribute("name"));
		System.out.println("firstSixElements.get(0).getAttribute() is : " + firstSixElements.get(4).getAttribute("value"));
		System.out.println("firstSixElements.get(0).getAttribute() is : " + firstSixElements.get(4).getAttribute("checked"));
		String res = firstSixElements.get(4).getAttribute("kicked");
		
	}
	
	
	//@Test
	public void testCheckBox() throws InvalidAlgorithmParameterException, AttributeNotFoundException {
		
		/*
		WebDriver driver = Connection.launchBrowser(URL);
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
		*/
		
		/*
		String checkBoxesXpath = "//input[@name='Checkbox']";
		WebDriver driver1 = Connection.launchBrowser(Browser.CHROME, "http://www.echoecho.com/htmlforms09.htm");
		MksCheckbox ch2 = new MksCheckbox(By.xpath(checkBoxesXpath));
		ch2.leftClick(1, 2, 3);
		System.out.println("clicked-----------------------------");
		SoftSleeper.seconds(2);
		System.out.println("ch.isSelected() is : " + ch2.isSelected());
		System.out.println("ch.isDisplayed() is : " + ch2.isDisplayed());
		System.out.println("ch.isEnabled() is : " + ch2.isEnabled());
		Connection.closeDriverBrowsers();
		*/
		
		driver1 = Connection.launchBrowser(Browser.CHROME, "http://www.echoecho.com/htmlforms09.htm");
		String checkBoxXpaths = "//input[@type='checkbox']";
		MksCheckbox ch2 = new MksCheckbox(By.xpath(checkBoxXpaths), "value");
		//ch2.leftClick(1, 2, 3);
		//SoftSleeper.seconds(2);
		System.out.println("non selected labels are : " + ch2.select("Milk", "Butter", "Chbgceese"));
		System.out.println("clicked-----------------------------");
		SoftSleeper.seconds(2);
		System.out.println("non clicked labels are : " + ch2.jsClick("Milk"));
		SoftSleeper.seconds(3);
		ch2 = new MksCheckbox(By.xpath(checkBoxXpaths));
		System.out.println("Select based on positions : " + ch2.select(2));
		SoftSleeper.seconds(2);
		
		System.out.println("Un Select based on positions : " + ch2.unSelect(3));
		SoftSleeper.seconds(5);
		
	}

	//@Test
	public void testAdvCheckBox() throws InvalidAlgorithmParameterException, AttributeNotFoundException {
		
		/*
		WebDriver driver = Connection.launchBrowser(URL);
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
		*/
		
	}

	WebDriver driver1 = null;
	WebDriver driver = null;

	//@Test
	public void testConnection() {

		String checkBoxesXpath = "//input[@name='Checkbox']";

		driver = Connection.launchBrowser(Browser.CHROME,
		"http://www.echoecho.com/htmlforms09.htm");
		SoftSleeper.seconds(2);
		MksCheckbox ch = new MksCheckbox(By.xpath(checkBoxesXpath));
		ch.leftClick(1, 2, 3);
		System.out.println("clicked-----------------------------");
		SoftSleeper.seconds(2);
		System.out.println("ch.isSelected() is : " + ch.isSelected());
		System.out.println("ch.isDisplayed() is : " + ch.isDisplayed());
		System.out.println("ch.isEnabled() is : " + ch.isEnabled());

		driver1 =
		Connection.launchBrowser("https://html.com/attributes/input-disabled/");
		SoftSleeper.seconds(2);
		By disabledElement = By.xpath("//h1[@class='title-page']/code");
		By disabledinput = By.xpath("//div[@class='render']/form/input");
		driver1 = Connection.getDriver();
		driver1.findElement(disabledElement).click();
		SoftSleeper.seconds(2);
		System.out.println("driver1.findElement(disabledinput) : " + driver1.findElement(disabledinput).isEnabled());
		// Connection.getBrowserTitle(); SoftSleeper.seconds(3);
		MksCheckbox ch1 = new MksCheckbox(disabledElement);
		System.out.println("ch.isSelected() is 11: " + ch1.isSelected());
		System.out.println("ch.isDisplayed() is : 11" + ch1.isDisplayed());
		System.out.println("ch.isEnabled() is : 11" + ch1.isEnabled());
	}

	// @Test
	public void testConnection1() {

		String checkBoxesXpath = "//input[@name='Checkbox']";

		// driver = Connection.launchBrowser(Browser.CHROME,
		// "http://www.echoecho.com/htmlforms09.htm");
		SoftSleeper.seconds(2);
		MksCheckbox ch = new MksCheckbox(By.xpath(checkBoxesXpath));
		ch.leftClick(1, 2, 3);
		System.out.println("clicked-----------------------------");
		SoftSleeper.seconds(2);
		System.out.println("ch.isSelected() is : " + ch.isSelected());
		System.out.println("ch.isDisplayed() is : " + ch.isDisplayed());
		System.out.println("ch.isEnabled() is : " + ch.isEnabled());

		// driver1 =
		// Connection.launchBrowser("https://html.com/attributes/input-disabled/");
		SoftSleeper.seconds(2);
		By disabledElement = By.xpath("//h1[@class='title-page']/code");
		By disabledinput = By.xpath("//div[@class='render']/form/input");
		driver1 = Connection.getDriver();
		driver1.findElement(disabledElement).click();
		SoftSleeper.seconds(2);
		System.out.println("driver1.findElement(disabledinput) : " + driver1.findElement(disabledinput).isEnabled());
		// Connection.getBrowserTitle(); SoftSleeper.seconds(3);
		MksCheckbox ch1 = new MksCheckbox(disabledElement);
		System.out.println("ch.isSelected() is 11: " + ch1.isSelected());
		System.out.println("ch.isDisplayed() is : 11" + ch1.isDisplayed());
		System.out.println("ch.isEnabled() is : 11" + ch1.isEnabled());

	}

	//@Test
	public void testRadioButton() throws InvalidAlgorithmParameterException, AttributeNotFoundException {
		
		driver1 = Connection.launchBrowser(Browser.CHROME, "http://www.echoecho.com/htmlforms10.htm");
		String radioButtonGrp1 = "//input[@type='radio'][@name='group1']";
		MksRadioButton rd = new MksRadioButton(By.xpath(radioButtonGrp1));
		
		/*
		 * rd.click(); SoftSleeper.seconds(2); rd.leftClick(2); SoftSleeper.seconds(2);
		 */
		rd = new MksRadioButton(By.xpath(radioButtonGrp1), "value");
		
		System.out.println("Select based on positions : " + new MksRadioButton(By.xpath("(//input[@type='radio'][@name='group1'])[2]")).isSelected());
		System.out.println("Select based on positions : " + new MksRadioButton(By.xpath("(//input[@type='radio'][@name='group1'])[3]")).isSelected());
		
		/*
		System.out.println("Selcet grp 1 radio button : " + rd.select("Milk"));
		SoftSleeper.seconds(2);
		System.out.println("Selcet grp 1 radio button click: " + rd.jsClick("Butter"));
		SoftSleeper.seconds(2);
		
		System.out.println("Select based on positions : " + rd.select(3));
		SoftSleeper.seconds(2);
		System.out.println("Is Select based on positions 2: " + rd.select(2));
		SoftSleeper.seconds(2);
		System.out.println("Is Select based on positions 3: " + rd.select(3));
		SoftSleeper.seconds(2);
		
		
		
		System.out.println("Select cheese from grp : " + rd.selectFromGroup("Cheese"));
		SoftSleeper.seconds(2);
		System.out.println("Select cheese from grp : " + rd.selectFromGroup(1));
		SoftSleeper.seconds(2);
		*/
		SoftSleeper.seconds(4);
		rd = new MksRadioButton(By.xpath("//td[@class='table5']//input[@type='radio']"), "value");
		

		System.out.println("Select cheese from grp : " + rd.selectFromGroup("Cheese"));
		SoftSleeper.seconds(2);
		System.out.println("Select cheese from grp : " + rd.selectFromGroup(4));
		SoftSleeper.seconds(2);
		
	}
	
	//@Test
	public void testLink() throws InvalidAttributesException, AWTException {
		 driver = Connection.launchBrowser("http://www.echoecho.com/htmlforms10.htm");
		 MksLink lnk = new MksLink(By.xpath("//form[@action='dummy']/a"));
		 System.out.println("lnk.getHrefLink() is : " + lnk.getHrefLink());
		 try {
			lnk.openInNewTab();
		} catch (LinkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 SoftSleeper.seconds(3);
		 
		 System.out.println("Size of the table is : " + driver.findElement(By.xpath("//td[@class='table8'][contains(text(),'HTML')]")).getText().trim());
		 String checkBoxXpaths = "//input[@type='checkbox']";
		 System.out.println("Checkboxes size is : " + driver.findElements(By.xpath(checkBoxXpaths)).size());
		 
	}
	
	//@Test
	public void testWindows() throws InvalidAttributesException, LinkException {
		
		driver = Connection.launchBrowser("http://www.echoecho.com/htmlforms10.htm");
		By links = By.xpath("//body[@class='thebody']//a");
		
		MksLink link = new MksLink(links);
		String firstWindowTitle = driver.getTitle();
		System.out.println("firstWindowTitle is : " + firstWindowTitle);
		System.out.println("-------------------------------------------");
		String firstWindow = link.openInNewTab("check boxes");
		SoftSleeper.seconds(2);
		String secindWindowTitle = driver.getTitle();
		System.out.println("secindWindowTitle is : " + secindWindowTitle);
		System.out.println("-------------------------------------------");
		
		String secondWindow = ArmWindows.switchToWindow(firstWindow);
		link.openInNewTab("here");
		SoftSleeper.seconds(2);
		String thirdWindowTitle = driver.getTitle();
		System.out.println("thirdWindowTitle is : " + thirdWindowTitle);
		System.out.println("-------------------------------------------");
		
		String thirdWindow = ArmWindows.switchToWindow(firstWindow);
		link.openInNewTab("About EchoEcho");
		SoftSleeper.seconds(2);
		String fourthWindowTitle = driver.getTitle();
		System.out.println("fourthWindowTitle is : " + fourthWindowTitle);
		System.out.println("-------------------------------------------");
		
		ArmWindows.switchToWindow(secondWindow);
		secindWindowTitle = driver.getTitle();
		System.out.println("secindWindowTitle again is : " + secindWindowTitle);
		System.out.println("-------------------------------------------");
		
		/*String secindWindowTitle = driver.getTitle();
		System.out.println("secindWindowTitle is : " + secindWindowTitle);
		String secondCheckboxWindow = ArmWindows.switchToWindow(firstWindow);
		System.out.println("link.getAllLinksText() is : " + link.getAllLinksText());
		String firstWindowAgain = ArmWindows.switchToOtherWindow(secindWindowTitle);
		System.out.println("link.getAllLinksText() is : " + new MksLink(links).getAllLinksText());
		SoftSleeper.seconds(20, "");*/
		
		
		
	}
	
	
	@Test
	public void testAdvCheckbox() throws InvalidParameterSpecException {
		
		driver = Connection.launchBrowser("https://www.flipkart.com/mobile-phones-store?otracker=nmenu_sub_Electronics_0_Mobiles");
		By brand = By.xpath("//div[text()='Brand']");
		By links = By.xpath("//body[@class='thebody']//a");
		SoftSleeper.seconds(2);
		WdOperators.scrollTo(Finder.find(brand), true);
		SoftSleeper.seconds(5);
		
		By checkBoxesLocator = By.xpath("//label/input[@type='checkbox']");
		By associatedLabelsLocator = By.xpath("//label/div[@class='_1GEhLw']");
		
		
		MksAdvCheckbox adv = new MksAdvCheckbox(checkBoxesLocator, associatedLabelsLocator);
		System.out.println("Get checkbox : " + adv.getCheckbox("2GB"));
		WdOperators.jsClick(adv.getCheckbox("2 GB"));
		SoftSleeper.seconds(5);
		
		List<String> allCheckboxText = new ArrayList<String>();
		allCheckboxText = adv.getAllCheckboxsText();
		System.out.println("allCheckboxText size : " + allCheckboxText.size());
		System.out.println("allCheckboxText :: " + allCheckboxText.toString());
		
		SoftSleeper.seconds(5);
		
		
	}
	
	
	@AfterMethod
	public void afetrClassQuit() {

		Connection.setDriver(driver);
		Connection.closeDriverBrowsers();

		Connection.setDriver(driver1);
		Connection.closeDriverBrowsers();

	}

}
