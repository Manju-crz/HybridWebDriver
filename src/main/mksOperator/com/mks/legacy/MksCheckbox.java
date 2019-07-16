package com.mks.legacy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;

import com.mks.connectors.Connection;
import com.mks.holders.PrimeObject;
import com.mks.seluty.Finder;
import com.mks.seluty.WdOperators;
import com.mks.utilizer.SoftSleeper;

public class MksCheckbox implements LegacyElements{

	protected static By checkboxLocator = null;
	protected static WebElement checkbox = null;
	protected static List<WebElement> checkboxs = null;

	public MksCheckbox(By locator) {
		checkboxLocator = locator;
		checkbox = Finder.find(checkboxLocator);
	}

	/**
	 * Given checkbox will be clicked
	 */
	public void click() {
		checkbox.click();
	}

	/**
	 * Given checkbox will be clicked with JavaScriptExecutor
	 */
	public void jsClick() {
		WdOperators.jsClick(checkbox);
	}

	/**
	 * Given checkbox will be mouse left clicked
	 */
	public void leftClick() {
		WdOperators.clickOnLocation(checkbox, 0, 0);
	}
	
	
	public boolean selectIfNotSelected() {
		
		if(!checkbox.isSelected()) {
			checkbox.click();
			SoftSleeper.milliseconds(100);
			checkbox = Finder.find(checkboxLocator);
			if(!checkbox.isSelected())
				return false;
			return true;
		}
		return true;
	}
	
	
	
	public boolean isSelected() {
		if(!checkbox.isSelected())
			return false;
		return true;
	}
	
	
	
}
