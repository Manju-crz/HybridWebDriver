package com.mks.holders;

import java.nio.file.FileSystemException;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mks.connectors.Connection;


public class PageObject {

	protected String windowHandle = null;
	protected String callersWindowHandle = null;
	protected final Logger logger = LogManager.getLogger(getClass());

	public PageObject() throws FileSystemException {
		windowHandle = getWindowHandle();
	}

	public PageObject(String url) throws FileSystemException {
		logger.info(String.format("Opening url: %s", url));
		Connection.getDriver().get(url);
		windowHandle = getWindowHandle();
	}

	public String getTitle() throws FileSystemException {
		String title = Connection.getDriver().getTitle();
		logger.trace(String.format("Retrieved title: '%s'", title));
		return title;
	}

	public String getURL() throws FileSystemException {
		String url = Connection.getDriver().getCurrentUrl();
		logger.trace(String.format("Retrieved URL: '%s'", url));
		return url;
	}

	public String getWindowHandle() throws FileSystemException {
		windowHandle = Connection.getDriver().getWindowHandle();
		logger.trace(String.format("Retrieved handle: '%s'", windowHandle));
		return windowHandle;
	}

	public void switchToNewWindow() throws FileSystemException {
		logger.info("Switching to new window");
		WebDriver wd = Connection.getDriver();
		Set<String> handles = wd.getWindowHandles();
		String[] handleArray = handles.toArray(new String[handles.size()]);
		wd.switchTo().window(handleArray[handles.size() - 1]);
	}

	public void switchToFrame(WebElement elem) throws FileSystemException {
		logger.info("Switching to Frame");
		WebDriver wd = Connection.getDriver();
		wd.switchTo().frame(elem);
	}

	public PageObject reload() throws FileSystemException {
		logger.info("Reloading " + this.getClass().getName());
		WebDriver wd = Connection.getDriver();
		wd.navigate().refresh();
		return this;
	}

	/**
	 * Sets focus to the page
	 * 
	 * @return
	 * @throws FileSystemException
	 */
	public PageObject getFocus() throws FileSystemException {
		logger.info("Obtaining focus for " + this.getClass().getSimpleName());
		WebDriver wd = Connection.getDriver();
		wd.switchTo().window(windowHandle);
		return this;
	}

	/**
	 * Closes the current page and return to the caller page if known
	 * 
	 * @throws FileSystemException
	 */
	public void close() throws FileSystemException {
		logger.info("Closing " + this.getClass().getSimpleName());
		WebDriver wd = Connection.getDriver();
		wd.close();
		if (callersWindowHandle != null) {
			wd.switchTo().window(callersWindowHandle);
		}
	}

	/**
	 * Re set browser size
	 * 
	 * @throws FileSystemException
	 */
	public void setBrowserSize(int width, int height) throws FileSystemException {
		logger.info("Re set browser size");
		WebDriver wd = Connection.getDriver();
		wd.manage().window().setSize(new Dimension(width, height));
	}

}
