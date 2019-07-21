package com.mks.legacy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.mks.definitions.LegacyElementsActions;
import com.mks.exceptions.NoElementFoundException;
import com.mks.seluty.Finder;
import com.mks.seluty.WdOperators;

public class MksButton extends LegacyElementsActions {

	private By buttonLocator = null;
	private WebElement button = null;
	private List<WebElement> buttons = null;

	public MksButton(By locator) {
		buttonLocator = locator;
		button = Finder.find(buttonLocator);
	}

	/**
	 * Given button will be clicked
	 */
	public void click() {
		button.click();
	}

	/**
	 * Given button will be clicked with JavaScriptExecutor
	 */
	public void jsClick() {
		WdOperators.jsClick(button);
	}

	/**
	 * Given button will be mouse left clicked
	 */
	public void leftClick() {
		WdOperators.clickOnLocation(button, 0, 0);
	}
	
	/**
	 * If there are many buttons with same button text, then identifies the first button
	 * and returns the first button
	 * 
	 * @param buttonText
	 * @return If no button identified, then returns null.
	 */
	public WebElement getButton(String buttonText) {
		buttons = Finder.findAll(buttonLocator);
		System.out.println("All buttons are : " + buttons.size());
		for (WebElement btn : buttons) {
			System.out.println("btn.getText().trim() : " + btn.getText().trim());
			if (btn.getText().trim().equals(buttonText)) {
				return btn;
			}
		}
		return null;
	}
	
	/**
	 * Helps to get the text of the button
	 * @return
	 */
	public String getButtonText() {
		return WdOperators.getText(button);
	}

	/**
	 * Helps to get the text of all buttons
	 * @return
	 */
	public List<String> getAllButtonsText() {
		
		return WdOperators.getTextOfElements(Finder.findAll(buttonLocator));
	}
	
	/**
	 * Helps to get the button, based on button text given along with the occurrence
	 * also will be utilized if more than one button is existing with same button text.
	 * If given positioned button is not existing in the button, then it will not get
	 * any button and return with null.
	 * 
	 * @param buttonText
	 *            The button text of the UI should exactly match - case sensitive.
	 * @param ofButtonTextOccurrence
	 *            The button text occurrence. Even if more buttons are identified with
	 *            given locator, and wants to get second button of matching button text,
	 *            then input 2 would be appropriate.
	 * @return If found with given occurrence and able to get, then returns
	 *         WebElement. Else returns null.
	 */
	public WebElement getButton(String buttonText, int ofButtonTextOccurrence) {
		if (ofButtonTextOccurrence < 1)
			throw new InvalidArgumentException(String.format("The button text %s position should be greater than zero, on which tried to perform action. Currently given position occurrence is %s", buttonText, ofButtonTextOccurrence));
		buttons = Finder.findAll(buttonLocator);
		int count = 0;
		for (WebElement btn : buttons) {
			if (btn.getText().trim().equals(buttonText)) {
				count++;
				if (count == ofButtonTextOccurrence) {
					return btn;
				}
			}
			count++;
		}
		return null;
	}

	/**
	 * Helps to click on the button, based on button text given. If more than one button
	 * is existing in DOM with same button text, then it clicks on first occurrence
	 * element by default.
	 * 
	 * @param buttonText
	 *            The button text of the UI should exactly match - case sensitive.
	 * @return If found and clicked, then returns true. Else returns false.
	 */
	public void click(String buttonText) {

		button = getButton(buttonText);
		if (button == null)
			throw new NoElementFoundException(getExceptionMsg(buttonText));
		else {
			button.click();
		}

	}

	/**
	 * Helps to click on the button, based on button text given along with the
	 * occurrence also will be utilized if more than one button is existing with same
	 * button text. If given positioned button is not existing in the button, then it will
	 * not click on any button and return with false.
	 * 
	 * @param buttonText
	 *            The button text of the UI should exactly match - case sensitive.
	 * @param ofButtonTextOccurrence
	 *            The button text occurrence. Even if more buttons are identified with
	 *            given locator, and wants to click on second button of matching button
	 *            text, then input 2 would be appropriate.
	 * @return If found with given occurrence and clicked, then returns true. Else
	 *         returns false.
	 */
	public void click(String buttonText, int ofButtonTextOccurrence) {
		button = getButton(buttonText, ofButtonTextOccurrence);
		if (button == null)
			throw new NoElementFoundException(getExceptionMsg(buttonText, ofButtonTextOccurrence));
		else {
			button.click();
		}
	}

	/**
	 * Helps to click on the button using mouse, based on button text given. If more
	 * than one button is existing in DOM with same button text, then it mouse left
	 * clicks on first occurrence element by default.
	 * 
	 * @param buttonText
	 *            The button text of the UI should exactly match - case sensitive.
	 * @return If found and mouse left clicked, then returns true. Else returns
	 *         false.
	 */
	public void leftClick(String buttonText) {

		button = getButton(buttonText);
		if (button == null)
			throw new NoElementFoundException(getExceptionMsg(buttonText));
		else {
			WdOperators.clickOnLocation(button, 0, 0);
		}
	}

	/**
	 * Helps to click on the button using mouse left click, based on button text given
	 * along with the occurrence also will be utilized if more than one button is
	 * existing with same button text. If given positioned button is not existing in the
	 * button, then it will not click on any button and return with false.
	 * 
	 * @param buttonText
	 *            The button text of the UI should exactly match - case sensitive.
	 * @param ofButtonTextOccurrence
	 *            The button text occurrence. Even if more buttons are identified with
	 *            given locator, and wants to click on second button of matching button
	 *            text, then input 2 would be appropriate.
	 * @return If found with given occurrence and mouse left clicked, then returns
	 *         true. Else returns false.
	 */
	public void leftClick(String buttonText, int ofButtonTextOccurrence) {

		button = getButton(buttonText, ofButtonTextOccurrence);
		if (button == null)
			throw new NoElementFoundException(getExceptionMsg(buttonText, ofButtonTextOccurrence));
		else {
			WdOperators.clickOnLocation(button, 0, 0);
		}
	}
	
	/**
	 * Helps to click on the button using JavaScript click, based on button text given. If more
	 * than one button is existing in DOM with same button text, then it JS
	 * clicks on first occurrence element by default.
	 * 
	 * @param buttonText
	 *            The button text of the UI should exactly match - case sensitive.
	 * @return If found and JS clicked, then returns true. Else returns
	 *         false.
	 */
	public void jsClick(String buttonText) {

		button = getButton(buttonText);
		if (button == null)
			throw new NoElementFoundException(getExceptionMsg(buttonText));
		else {
			WdOperators.jsClick(button);
		}
	}

	/**
	 * Helps to click on the button using JavaScriptExecutor click, based on button text given
	 * along with the occurrence also will be utilized if more than one button is
	 * existing with same button text. If given positioned button is not existing in the
	 * button, then it will not JS click on any button and return with false.
	 * 
	 * @param buttonText
	 *            The button text of the UI should exactly match - case sensitive.
	 * @param ofButtonTextOccurrence
	 *            The button text occurrence. Even if more buttons are identified with
	 *            given locator, and wants to JS click on second button of matching button
	 *            text, then input 2 would be appropriate.
	 * @return If found with given occurrence and JS clicked, then returns
	 *         true. Else returns false.
	 */
	public void jsClick(String buttonText, int ofButtonTextOccurrence) {

		button = getButton(buttonText, ofButtonTextOccurrence);
		if (button == null)
			throw new NoElementFoundException(getExceptionMsg(buttonText, ofButtonTextOccurrence));
		else {
			WdOperators.jsClick(button);
		}
	}
	

	private String getExceptionMsg(String buttonTxt) {
		return String.format("There is no button element found for the button locator %s with the button text %s",
				buttonLocator.toString(), buttonTxt);
	}
	
	private String getExceptionMsg(String buttonTxt, int textOccurrence) {
		return String.format("%s, at the occcurrence of text at position %s",
				getExceptionMsg(buttonTxt), textOccurrence);
	}


	@Override
	public boolean isSelected() {
		if (!button.isSelected())
			return false;
		return true;
	}

	@Override
	public boolean isDisplayed() {
		try {
			if (button.isDisplayed())
				return true;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean isEnabled() {
		if (!button.isEnabled())
			return false;
		return true;
	}
	
}
