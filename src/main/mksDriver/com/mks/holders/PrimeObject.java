package com.mks.holders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.mks.exceptions.NavigationException;
import com.mks.seluty.ExplicitConditions;
import com.mks.seluty.Finder;


public class PrimeObject {

	protected By locator;
	protected String description;

	/**
	 * The {@link org.openqa.selenium.WebElement WebElement} webElement is the
	 * latest result of the {@link #bindToDOM()} method.
	 */
	protected WebElement webElement;
	protected final Logger logger = LogManager.getLogger(getClass());

	protected PrimeObject(By locator, String description) {
		this.locator = locator;
		this.description = description;
		bindToDOM();
	}

	protected PrimeObject(By parentLocator, By locator, String description) {
		this.locator = locator;
		this.description = description;
		bindToDOM(parentLocator);
	}

	protected void bindToDOM() {
		logger.debug(String.format("Binding %s with locator %s", description, locator));
		try {
			webElement = Finder.find(locator, Finder.DEFAULT_WAIT, ExplicitConditions.visibilityOfOneElement(locator));
		} catch (NoSuchElementException e) {
			throw new NavigationException(String.format(
					"Unable to bind %s to the DOM, no displayed elements match locator %s", description, locator), e);
		}
	}

	protected void bindToDOM(By parentLocator) {
		logger.debug(String.format("Binding %s with locator %s under parent locator %s", description, locator,
				parentLocator));
		try {
			webElement = Finder.findChild(parentLocator, locator, Finder.DEFAULT_WAIT,
					ExplicitConditions.visiblityOfOneNestedElement(parentLocator, locator));
		} catch (NoSuchElementException e) {
			throw new NavigationException(String.format(
					"Unable to bind %s to the DOM, no displayed elements matches\nParent Locator: %s\nLocator: %s",
					description, parentLocator, locator), e);
		}
	}

	public String getDescription() {
		return description;
	}

}
