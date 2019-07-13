package com.mks.connectors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.mks.directorizer.FilesInvestigator;
import com.mks.directorizer.FoldersInvestigator;
import com.mks.directorizer.SystemDirectoryPath;


public class Connection {

	private static Browser browser = null;
	private static WebDriver driver = null;

	public Connection() {
		this.browser = Browser.CHROME;
	}
	
	public Connection(Browser browser) {
		this.browser = browser;
	}
	
	public enum Browser {
		FIREFOX, CHROME, SAFARI, IE, EDGE
	};

	private static final Logger logger = LogManager.getLogger(Connection.class);

	public WebDriver launchBrowser() {
		switch (browser) {
		case CHROME:
			driver = Browsers.getChromeBrowser(getDriverPath("chromedriver"));
			break;
		case FIREFOX:
			driver = Browsers.getFirefoxBrowser(getDriverPath("geckodriver"));
			break;
		case SAFARI:
			throw new UnsupportedOperationException(String.format("Unsupported Browser %s", browser));
		case EDGE:
			throw new UnsupportedOperationException(String.format("Unsupported Browser %s", browser));
		case IE:
			throw new UnsupportedOperationException(String.format("Unsupported Browser %s", browser));
		default:
			throw new UnsupportedOperationException(String.format("Unsupported Browser %s", browser));
		}
		return driver;
	}
	
	public WebDriver launchBrowser(String URL) {
		launchBrowser();
		driver.get(URL);
		return driver;
	}
	
	
	
	private static String getDriverPath(String driverName) {
		String driverFolder;
		String driversFolderName = "drivers";
		
		if (SystemUtils.IS_OS_MAC) {
			driverFolder = File.separator + driversFolderName + File.separator;
		} else if (SystemUtils.IS_OS_LINUX) {
			driverFolder = File.separator + driversFolderName + File.separator;
		} else if (SystemUtils.IS_OS_WINDOWS) {
			driverFolder = File.separator + driversFolderName + File.separator;
			driverName = driverName + ".exe";
		} else {
			throw new UnsupportedOperationException("Unsupported Operating System");
		}
		String projFolder = new SystemDirectoryPath().getCurrentProjectFolder();
		String filePath = projFolder + driverFolder + driverName;
		
		FilesInvestigator fi = new FilesInvestigator();
		if(!fi.findFile(filePath))
			throw new InvalidPathException("", String.format("There should be a folder %s inside the project folder %s, which should contain the %s executable file inorder to execute scripts.", driversFolderName, projFolder, driverName));
		
		return filePath;
	}
	
	/*public static void setBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")
				|| browserName.equalsIgnoreCase("Mozilla"))
			browser = Browser.FIREFOX;
		else if (browserName.contains("chrome") || browserName.contains("Chrome") || browserName.equalsIgnoreCase("gc"))
			browser = Browser.CHROME;
		else
			browser = Browser.CHROME;
	}*/

	public static void setDriver(WebDriver currentDriver) {
		driver = currentDriver;
	}
	
	
	public static void closeDriverBrowsers() {
		if (driver != null)
			driver.quit();
		driver = null;
	}

	public static WebDriver getDriver() {
		return driver;
	}

}
