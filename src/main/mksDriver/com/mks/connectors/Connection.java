package com.mks.connectors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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


public class Connection {

	private static Browser browser = null;
	private static WebDriver driver = null;

	public enum Browser {
		FIREFOX, CHROME, SAFARI, IE, EDGE
	};

	private static final Logger logger = LogManager.getLogger(Connection.class);

	public static WebDriver launchBrower() {
		switch (browser) {
		case CHROME:
			driver = Browsers.getChromeBrowser(getDriverPath("chromedriver"));
			break;
		case FIREFOX:
			driver = Browsers.getFirefoxBrowser(getDriverPath("geckodriver"));
			break;
		case SAFARI:
		case EDGE:
		case IE:
			throw new UnsupportedOperationException(String.format("Unsupported Browser %s", browser));
		default:
			throw new UnsupportedOperationException(String.format("Unsupported Browser %s", browser));
		}
		driver.manage().timeouts().implicitlyWait(Constants.getElementsFinderTimeout(), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.getPageloadTimeout(), TimeUnit.SECONDS);
		return driver;
	}

	private static String getDriverPath(String driverName) {
		String driverFolder;
		if (SystemUtils.IS_OS_MAC) {
			driverFolder = File.separator + "servers" + File.separator;
		} else if (SystemUtils.IS_OS_LINUX) {
			driverFolder = File.separator + "servers" + File.separator;
		} else if (SystemUtils.IS_OS_WINDOWS) {
			driverFolder = File.separator + "servers" + File.separator;
			driverName = driverName + ".exe";
		} else {
			throw new UnsupportedOperationException("Unsupported Operating System");
		}

		String filePath = System.getProperty("user.dir") + driverFolder + driverName;
		logger.trace("File Path:" + filePath);
		File file = new File(filePath);
		try {
			if (!SystemUtils.IS_OS_WINDOWS) {
				Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
				List<PosixFilePermission> driverPerms = Arrays.asList(PosixFilePermission.GROUP_EXECUTE,
						PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_EXECUTE,
						PosixFilePermission.OTHERS_READ, PosixFilePermission.OWNER_EXECUTE,
						PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE);
				perms.addAll(driverPerms);
				Files.setPosixFilePermissions(Paths.get(filePath), perms);
			}
		} catch (UnsupportedOperationException e) {
			logger.warn("Driver could not be set as an executable");
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Using driver at:" + file.toString());
		return file.toString();
	}

	public static void setBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")
				|| browserName.equalsIgnoreCase("Mozilla"))
			browser = Browser.FIREFOX;
		else if (browserName.contains("chrome") || browserName.contains("Chrome") || browserName.equalsIgnoreCase("gc"))
			browser = Browser.CHROME;
		else
			browser = Browser.CHROME;
	}

	public static void setDriver(WebDriver currentDriver) {
		driver = currentDriver;
	}

	public static void closeAll() {
		if (driver != null)
			driver.quit();
		driver = null;
	}

	public static WebDriver getDriver() {
		return driver;
	}

}
