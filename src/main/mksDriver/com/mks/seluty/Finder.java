package com.mks.seluty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
import com.mks.connectors.Connection;
import com.mks.connectors.Constants;

public class Finder {
	 /* If you are passing anything other than a locator, you are doing it wrong */

	  /**
	   * Simple wait that reproduces implicit wait behavior
	   */
	  public static Wait<WebDriver> DEFAULT_WAIT = new FluentWait<WebDriver>(Connection.getDriver()).withTimeout(Constants.getElementsFinderTimeout(), TimeUnit.SECONDS)
	      .pollingEvery(200, TimeUnit.MILLISECONDS).ignoreAll(Arrays.asList(NoSuchElementException.class));

	  public static Wait<WebDriver> NO_WAIT = new FluentWait<WebDriver>(Connection.getDriver()).withTimeout(0, TimeUnit.SECONDS);

	  /**
	   * Returns a Wait for Finder that has a custom timeout value. This is useful if the element you're looking for might take longer than the default timeout to appear.
	   *
	   * @param timeout The number of seconds to wait for the element to appear before throwing an exception.
	   * @return a Wait with the custom timeout value.
	   */
	  public static Wait<WebDriver> customTimeoutWait(int timeout) {
	    return new FluentWait<WebDriver>(Connection.getDriver()).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(50, TimeUnit.MILLISECONDS).ignoreAll(Arrays.asList(NoSuchElementException.class));
	  }

	  private static final Logger logger = LogManager.getLogger(Finder.class);

	  public static WebElement find(By locator) {
	    return find(locator, DEFAULT_WAIT);
	  }

	  public static WebElement find(By locator, Wait<WebDriver> wait) {
	    return find(locator, wait, ExpectedConditions.presenceOfElementLocated(locator));
	  }

	  public static WebElement find(By locator, Wait<WebDriver> wait, Function<WebDriver, WebElement> expectedCondition) {
	    logger.trace("Finding WebElement for locator " + locator);
	    try {
	      WebElement result = wait.until(expectedCondition);
	      logger.trace("Found WebElement for locator " + locator);
	      return result;
	    } catch (TimeoutException e) {
	      String exceptionString = String.format("No WebElement found for locator: %s", locator);
	      logger.trace(exceptionString);
	      throw new NoSuchElementException(exceptionString, e);
	    }
	  }

	  public static WebElement findChild(By parentLocator, By locator) {
	    return findChild(parentLocator, locator, DEFAULT_WAIT);
	  }

	  public static WebElement findChild(By parentLocator, By locator, Wait<WebDriver> wait) {
	    return findChild(parentLocator, locator, wait, ExpectedConditions.presenceOfNestedElementLocatedBy(parentLocator, locator));
	  }

	  public static WebElement findChild(By parentLocator, By locator, Wait<WebDriver> wait, Function<WebDriver, WebElement> expectedCondition) {
	    logger.trace("Finding child WebElement of parent locator " + parentLocator + " for locator " + locator);
	    try {
	      WebElement result = wait.until(expectedCondition);
	      logger.trace("Found child WebElement of parent locator " + parentLocator + " for locator " + locator);
	      return result;
	    } catch (TimeoutException e) {
	      throw new NoSuchElementException(String.format("No WebElement found for parentLocator: %s locator: %s", parentLocator, locator), e);
	    }
	  }

	  public static List<WebElement> findAll(By locator) {
	    return findAll(locator, DEFAULT_WAIT);
	  }

	  public static List<WebElement> findAll(By locator, Wait<WebDriver> wait) {
	    return findAll(locator, wait, ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	  }

	  public static List<WebElement> findAll(By locator, Wait<WebDriver> wait, Function<WebDriver, List<WebElement>> expectedCondition) {
	    logger.trace("Finding All WebElement(s) for locator " + locator);
	    List<WebElement> elements;
	    try {
	      elements = wait.until(expectedCondition);
	    } catch (TimeoutException e) {
	      elements = new ArrayList<WebElement>();
	    }

	    if (elements.isEmpty()) {
	      logger.trace("Found No WebElement(s) for locator " + locator);
	    } else {
	      logger.trace("Found WebElement(s) for locator " + locator);
	    }

	    return elements;
	  }

	  public static List<WebElement> findAllChild(By parentLocator, By locator) {
	    return findAllChild(parentLocator, locator, DEFAULT_WAIT);
	  }

	  public static List<WebElement> findAllChild(By parentLocator, By locator, Wait<WebDriver> wait) {
	    return findAllChild(parentLocator, locator, wait, ExpectedConditions.presenceOfNestedElementsLocatedBy(parentLocator, locator));
	  }

	  public static List<WebElement> findAllChild(By parentLocator, By locator, Wait<WebDriver> wait, Function<WebDriver, List<WebElement>> expectedCondition) {
	    logger.trace("Finding All child WebElement of parent locator " + parentLocator + " for locator " + locator);
	    List<WebElement> elements;
	    try {
	      elements = wait.until(expectedCondition);
	    } catch (TimeoutException e) {
	      elements = new ArrayList<WebElement>();
	    }

	    if (elements.isEmpty()) {
	      logger.trace("Found No child WebElement(s) of parent locator " + parentLocator + " for locator " + locator);
	    } else {
	      logger.trace("Found child WebElement(s) of parent locator " + parentLocator + " for locator " + locator);
	    }

	    return elements;
	  }
	  
	  public static void reloadDriver() {
	      DEFAULT_WAIT = new FluentWait<WebDriver>(Connection.getDriver()).withTimeout(Constants.getPageloadTimeout(), TimeUnit.SECONDS)
	              .pollingEvery(500, TimeUnit.MILLISECONDS).ignoreAll(Arrays.asList(NoSuchElementException.class));
	      NO_WAIT = new FluentWait<WebDriver>(Connection.getDriver()).withTimeout(0, TimeUnit.SECONDS);
	  }

	  private Finder() {
	  }
	  
	  public static WebElement findChild(WebElement parentElement, By locator) {
	    return findChild(parentElement, locator, DEFAULT_WAIT, ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, locator));
	  }
	  private static WebElement findChild(WebElement parentElement, By locator, Wait<WebDriver> wait, Function<WebDriver, WebElement> expectedCondition) {
		    logger.trace("Finding child WebElement of parent locator ");
		    try {
		      WebElement result = wait.until(expectedCondition);
		      logger.trace("Found child WebElement of parent locator ");
		      return result;
		    } catch (TimeoutException e) {
		      throw new NoSuchElementException(String.format("No WebElement found for parentLocator: %s locator: %s", parentElement, locator), e);
		    }
	  }
}
