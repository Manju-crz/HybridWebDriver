package com.mks.connectors;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * A utility of selenium web driver browser related activity.
 * 
 * @author Manjunath KS
 *
 */
public class Browsers {

	public static WebDriver getChromeBrowser(String driverPath) {
		WebDriver webDriverObject = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", driverPath);
		capabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
		capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(ChromeOptions.CAPABILITY, createChromeOptions());
		capabilities.setCapability("marionette", false);
		webDriverObject = new ChromeDriver(capabilities);
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Dimension dimension = new Dimension(toolKit.getScreenSize().width, toolKit.getScreenSize().height);
		webDriverObject.manage().window().setSize(dimension);
		Capabilities caps = ((RemoteWebDriver) webDriverObject).getCapabilities();
		return webDriverObject;
	}

	private static ChromeOptions createChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("password_manager_enabled", false);
		chromeOptions.setExperimentalOption("prefs", prefs);
		chromeOptions.addArguments("--disable-notifications");
		if (Boolean.parseBoolean(System.getProperty("privateBrowsingEnabled"))) {
			chromeOptions.addArguments("--incognito");
		}
		
		return chromeOptions;
	}

	public static WebDriver getFirefoxBrowser(String driverPath) {
		WebDriver webDriverObject = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		System.setProperty("webdriver.gecko.driver", driverPath);
		capabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
		capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(FirefoxDriver.PROFILE, createFirefoxProfile());
		capabilities.setCapability("marionette", false);
		capabilities.setCapability("acceptInsecureCerts", true);
		webDriverObject = new FirefoxDriver(capabilities);
		webDriverObject.manage().window().maximize();
		Capabilities caps = ((RemoteWebDriver) webDriverObject).getCapabilities();
		return webDriverObject;
	}

	private static FirefoxProfile createFirefoxProfile() {
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
		if (Boolean.parseBoolean(System.getProperty("privateBrowsingEnabled"))) {
			firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
		}
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"text/plain, application/vnd.ms-excel, text/csv, text/comma-separated-values, application/octet-stream");
		return firefoxProfile;
	}
}
