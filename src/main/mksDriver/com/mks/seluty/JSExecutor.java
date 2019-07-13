package com.mks.seluty;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.mks.connectors.Connection;

public class JSExecutor {
	private static final Logger logger = LogManager.getLogger(JSExecutor.class);

	/**
	 * Run a script
	 * 
	 * @param script
	 * @return One of Boolean, Double, Long, String, List, WebElement, or null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T executeScript(String script, Object... objects) {
		WebDriver driver = Connection.getDriver();
		if (driver instanceof JavascriptExecutor) {
			logger.trace("Executing Script: " + script);
			Object output = ((JavascriptExecutor) driver).executeScript(script, objects);
			if (output != null) {
				logger.trace("Response: " + output.toString());
			}

			return (T) output;
		}

		throw new UnsupportedOperationException("WebDriver is unable to execute javascript");
	}

	/**
	 * Run an Async script
	 * 
	 * @param script
	 * @return One of Boolean, Double, Long, String, List, WebElement, or null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T executeAsyncScript(String script) {
		WebDriver driver = Connection.getDriver();
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		if (driver instanceof JavascriptExecutor) {
			long start = System.currentTimeMillis();
			((JavascriptExecutor) driver)
					.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");
			System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
			logger.debug("Executing Script: " + script);
			Object output = ((JavascriptExecutor) driver).executeAsyncScript(script);
			if (output != null) {
				logger.debug("Response: " + output.toString());
			}

			return (T) output;
		}

		throw new UnsupportedOperationException("WebDriver is unable to execute javascript");
	}
}
