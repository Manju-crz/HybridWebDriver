package com.mks.definitions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.mks.seluty.WdOperators;

public abstract class LegacyElementsActions implements LegacyElements{
	
	protected WebElement element = null;
	protected By elementLocator = null;
	protected List<WebElement> elements = null;
	
	protected void clickOnElementsBasedOnPositions(ClickTypes type, List<WebElement> elements, List<Integer> positions) {
		
		int count = 0;
		switch (type) {
			case SimpleClick:
				for(WebElement element : elements) {
					count ++;
					if(positions.contains(count))
						element.click();
				}
				break;
			case JsClick:
				for(WebElement element : elements) {
					count ++;
					if(positions.contains(count))
						WdOperators.jsClick(element);
				}
				break;
			case MouseLeftClick:
				for(WebElement element : elements) {
					count ++;
					if(positions.contains(count))
						WdOperators.clickOnLocation(element, 0, 0);
				}
				break;
			case MouseRightClick:
				//throw new InvalidActivityException("Currently mouse right click is not supported");
			case DoubleClick:
				for(WebElement element : elements) {
					count ++;
					if(positions.contains(count))
						WdOperators.doubleClick(element);
				}
				break;
		}
	}
	
	protected void clickOnElement(ClickTypes type, WebElement element) {
		switch (type) {
		case SimpleClick:
					element.click();
			break;
		case JsClick:
					WdOperators.jsClick(element);
			break;
		case MouseLeftClick:
					WdOperators.clickOnLocation(element, 0, 0);
			break;
		case MouseRightClick:
			//throw new InvalidActivityException("Currently mouse right click is not supported");
		case DoubleClick:
					WdOperators.doubleClick(element);
			break;
		}
	}
	

	
	public boolean isSelected(WebElement element) {
		if (!element.isSelected())
			return false;
		return true;
	}

	public boolean isDisplayed(WebElement element) {
		try {
			if (element.isDisplayed())
				return true;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean isEnabled(WebElement element) {
		if (!element.isEnabled())
			return false;
		return true;
	}

	
	public boolean isSelected() {
		if (!element.isSelected())
			return false;
		return true;
	}

	public boolean isDisplayed() {
		try {
			if (element.isDisplayed())
				return true;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean isEnabled() {
		if (!element.isEnabled())
			return false;
		return true;
	}
	
	public void click() {
		WdOperators.click(element);
	}
	
	public void jsClick() {
		WdOperators.jsClick(element);
	}

	public void leftClick() {
		WdOperators.jsClick(element);
	}

	public void rightClick() {
		WdOperators.jsClick(element);
	}
	
	public void doubleClick() {
		WdOperators.jsClick(element);
	}
	
	public void moveCursorOn() {
		
	}
	
	public String getText() {
		return element.getText();
	}

	public String getValue() {
		return WdOperators.getValue(element);
	}
	
	public void scrollPageDownTill() {
		
	}
	
}
