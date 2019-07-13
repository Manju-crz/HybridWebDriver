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

	private static By checkboxLocator = null;
	private static WebElement checkbox = null;
	private static List<WebElement> checkboxs = null;

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
	
	
	
	
	
	
	
	
	
	
	
	// All below code is still TO BE MODIFIED due to lables and checkboxes synced up clicks.
	
	private static By checkboxLabelsLocator = null;
	private static WebElement checkboxLabel = null;
	public MksCheckbox(By checkBoxesLocator, By associatedLabelsLocator) {
		checkboxLocator = checkBoxesLocator;
		checkbox = Finder.find(checkboxLocator);
		checkboxLabel = Finder.find(associatedLabelsLocator);
	}

	
	

	/**
	 * If there are many checkboxs with same checkbox text, then identifies the first checkbox
	 * and returns the first checkbox
	 * 
	 * @param checkboxText
	 * @return If no checkbox identified, then returns null.
	 */
	public WebElement getCheckbox(String checkboxText) {
		checkboxs = Finder.findAll(checkboxLocator);
		System.out.println("All checkboxs are : " + checkboxs.size());
		for (WebElement chkbx : checkboxs) {
			System.out.println("chkbx.getText().trim() : " + chkbx.getText().trim());
			if (chkbx.getText().trim().equals(checkboxText)) {
				return chkbx;
			}
		}
		return null;
	}
	
	/**
	 * Helps to get the text of the checkbox
	 * @return
	 */
	public String getCheckboxText() {
		return WdOperators.getText(checkbox);
	}

	/**
	 * Helps to get the text of all checkboxs
	 * @return
	 */
	public List<String> getAllCheckboxsText() {
		
		return WdOperators.getTextOfElements(Finder.findAll(checkboxLocator));
	}
	
	/**
	 * Helps to get the checkbox, based on checkbox text given along with the occurrence
	 * also will be utilized if more than one checkbox is existing with same checkbox text.
	 * If given positioned checkbox is not existing in the checkbox, then it will not get
	 * any checkbox and return with null.
	 * 
	 * @param checkboxText
	 *            The checkbox text of the UI should exactly match - case sensitive.
	 * @param ofCheckboxTextOccurrence
	 *            The checkbox text occurrence. Even if more checkboxs are identified with
	 *            given locator, and wants to get second checkbox of matching checkbox text,
	 *            then input 2 would be appropriate.
	 * @return If found with given occurrence and able to get, then returns
	 *         WebElement. Else returns null.
	 */
	public WebElement getCheckbox(String checkboxText, int ofCheckboxTextOccurrence) {
		if (ofCheckboxTextOccurrence < 1)
			throw new InvalidArgumentException(String.format("The checkbox text %s position should be greater than zero, on which tried to perform action. Currently given position occurrence is %s", checkboxText, ofCheckboxTextOccurrence));
		checkboxs = Finder.findAll(checkboxLocator);
		int count = 0;
		for (WebElement chkbx : checkboxs) {
			if (chkbx.getText().trim().equals(checkboxText)) {
				count++;
				if (count == ofCheckboxTextOccurrence) {
					return chkbx;
				}
			}
			count++;
		}
		return null;
	}

	/**
	 * Helps to click on the checkbox, based on checkbox text given. If more than one checkbox
	 * is existing in DOM with same checkbox text, then it clicks on first occurrence
	 * element by default.
	 * 
	 * @param checkboxText
	 *            The checkbox text of the UI should exactly match - case sensitive.
	 * @return If found and clicked, then returns true. Else returns false.
	 */
	public boolean click(String checkboxText) {

		checkbox = getCheckbox(checkboxText);
		if (checkbox == null)
			return false;
		else {
			checkbox.click();
			return true;
		}

	}

	/**
	 * Helps to click on the checkbox, based on checkbox text given. If more than one checkbox
	 * is existing in DOM with same checkbox text, then it clicks on first occurrence
	 * element by default.
	 * 
	 * @param checkboxesTextToClick
	 *            The checkbox text of the UI should exactly match - case sensitive.
	 * @return If found and clicked, then returns true. Else returns false.
	 */
	public List<String> click(String... checkboxesTextToClick) {
		
		List<String> nonFoundCheckboxes = new ArrayList<>();
		for(String checkboxText : checkboxesTextToClick)
		{
			checkbox = getCheckbox(checkboxText);
			if (checkbox == null)
				nonFoundCheckboxes.add(checkboxText);
			else {
				checkbox.click();
				SoftSleeper.milliseconds(100);
			}
		}
		return nonFoundCheckboxes;
	}
	
	/**
	 * Helps to click on the checkbox, based on checkbox text given along with the
	 * occurrence also will be utilized if more than one checkbox is existing with same
	 * checkbox text. If given positioned checkbox is not existing in the checkbox, then it will
	 * not click on any checkbox and return with false.
	 * 
	 * @param checkboxText
	 *            The checkbox text of the UI should exactly match - case sensitive.
	 * @param ofCheckboxTextOccurrence
	 *            The checkbox text occurrence. Even if more checkboxs are identified with
	 *            given locator, and wants to click on second checkbox of matching checkbox
	 *            text, then input 2 would be appropriate.
	 * @return If found with given occurrence and clicked, then returns true. Else
	 *         returns false.
	 */
	public boolean click(String checkboxText, int ofCheckboxTextOccurrence) {
		checkbox = getCheckbox(checkboxText, ofCheckboxTextOccurrence);
		if (checkbox == null)
			return false;
		else {
			checkbox.click();
			return true;
		}
	}

	/**
	 * Helps to click on the checkbox using mouse, based on checkbox text given. If more
	 * than one checkbox is existing in DOM with same checkbox text, then it mouse left
	 * clicks on first occurrence element by default.
	 * 
	 * @param checkboxText
	 *            The checkbox text of the UI should exactly match - case sensitive.
	 * @return If found and mouse left clicked, then returns true. Else returns
	 *         false.
	 */
	public boolean leftClick(String checkboxText) {

		checkbox = getCheckbox(checkboxText);
		if (checkbox == null)
			return false;
		else {
			WdOperators.clickOnLocation(checkbox, 0, 0);
			return true;
		}
	}

	/**
	 * Helps to click on the checkbox using mouse left click, based on checkbox text given
	 * along with the occurrence also will be utilized if more than one checkbox is
	 * existing with same checkbox text. If given positioned checkbox is not existing in the
	 * checkbox, then it will not click on any checkbox and return with false.
	 * 
	 * @param checkboxText
	 *            The checkbox text of the UI should exactly match - case sensitive.
	 * @param ofCheckboxTextOccurrence
	 *            The checkbox text occurrence. Even if more checkboxs are identified with
	 *            given locator, and wants to click on second checkbox of matching checkbox
	 *            text, then input 2 would be appropriate.
	 * @return If found with given occurrence and mouse left clicked, then returns
	 *         true. Else returns false.
	 */
	public boolean leftClick(String checkboxText, int ofCheckboxTextOccurrence) {

		checkbox = getCheckbox(checkboxText, ofCheckboxTextOccurrence);
		if (checkbox == null)
			return false;
		else {
			WdOperators.clickOnLocation(checkbox, 0, 0);
			return true;
		}
	}
	
	/**
	 * Helps to click on the checkbox using JavaScript click, based on checkbox text given. If more
	 * than one checkbox is existing in DOM with same checkbox text, then it JS
	 * clicks on first occurrence element by default.
	 * 
	 * @param checkboxText
	 *            The checkbox text of the UI should exactly match - case sensitive.
	 * @return If found and JS clicked, then returns true. Else returns
	 *         false.
	 */
	public boolean jsClick(String checkboxText) {

		checkbox = getCheckbox(checkboxText);
		if (checkbox == null)
			return false;
		else {
			WdOperators.jsClick(checkbox);
			return true;
		}
	}

	/**
	 * Helps to click on the checkbox using JavaScriptExecutor click, based on checkbox text given
	 * along with the occurrence also will be utilized if more than one checkbox is
	 * existing with same checkbox text. If given positioned checkbox is not existing in the
	 * checkbox, then it will not JS click on any checkbox and return with false.
	 * 
	 * @param checkboxText
	 *            The checkbox text of the UI should exactly match - case sensitive.
	 * @param ofCheckboxTextOccurrence
	 *            The checkbox text occurrence. Even if more checkboxs are identified with
	 *            given locator, and wants to JS click on second checkbox of matching checkbox
	 *            text, then input 2 would be appropriate.
	 * @return If found with given occurrence and JS clicked, then returns
	 *         true. Else returns false.
	 */
	public boolean jsClick(String checkboxText, int ofCheckboxTextOccurrence) {

		checkbox = getCheckbox(checkboxText, ofCheckboxTextOccurrence);
		if (checkbox == null)
			return false;
		else {
			WdOperators.jsClick(checkbox);
			return true;
		}
	}
	
}
