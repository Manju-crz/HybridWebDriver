package com.mks.legacy;

import java.util.ArrayList;
import java.util.List;

import javax.activity.InvalidActivityException;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;

import com.mks.connectors.Connection;
import com.mks.definitions.LegacyElements;
import com.mks.definitions.LegacyElementsActions;
import com.mks.exceptions.NoElementFoundException;
import com.mks.holders.PrimeObject;
import com.mks.seluty.Finder;
import com.mks.seluty.WdOperators;
import com.mks.utilizer.MksArray;
import com.mks.utilizer.SoftSleeper;

public class MksCheckbox extends LegacyElementsActions{

	protected static By checkboxLocator = null;
	protected static WebElement checkbox = null;
	protected static List<WebElement> checkboxes = null;
	protected static int checkboxElementsFoundCount = 0;
	
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
	
	/**
	 * Given checkbox will be clicked, as per the given position of the checkbox elements starting from 1.
	 * @throws InvalidActivityException 
	 */
	public void click(int ... clickableElementsPositions) throws InvalidActivityException {
		
		List<Integer> lst = validateCheckboxesGivenPositionExistance(clickableElementsPositions);
		clickOnElementsBasedOnPositions(ClickTypes.SimpleClick, checkboxes, lst);
	}
	
	/**
	 * Given checkbox will be clicked with JavaScriptExecutor
	 * @throws InvalidActivityException 
	 */
	public void jsClick(int ... clickableElementsPositions) throws InvalidActivityException {
		List<Integer> lst = validateCheckboxesGivenPositionExistance(clickableElementsPositions);
		clickOnElementsBasedOnPositions(ClickTypes.JsClick, checkboxes, lst);
	}

	/**
	 * Given checkbox will be mouse left clicked
	 * @throws InvalidActivityException 
	 */
	public void leftClick(int ... clickableElementsPositions) throws InvalidActivityException {
		List<Integer> lst = validateCheckboxesGivenPositionExistance(clickableElementsPositions);
		clickOnElementsBasedOnPositions(ClickTypes.MouseLeftClick, checkboxes, lst);
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
	
	
	private List<Integer> validateCheckboxesGivenPositionExistance(int ... clickableElementsPositions) {
		checkboxes = Finder.findAll(checkboxLocator); 
		int maxNum = MksArray.getLargest(clickableElementsPositions);
		checkboxElementsFoundCount = checkboxes.size();
		if(checkboxElementsFoundCount < maxNum)
			throw new NoElementFoundException(String.format("Total %s checkboxes found with the checkboxes locator %s, and given input to select the checkbox of occurrence is %s in the ui ", checkboxElementsFoundCount, checkboxLocator.toString(), maxNum));
		return MksArray.getArraysAsList(clickableElementsPositions);
	}
	
}
