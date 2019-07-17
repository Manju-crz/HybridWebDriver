package com.mks.advanced;

import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;

import com.mks.exceptions.ElementsCountMisMatchException;
import com.mks.exceptions.NoElementFoundException;
import com.mks.legacy.MksCheckbox;
import com.mks.seluty.Finder;
import com.mks.seluty.WdOperators;
import com.mks.utilizer.SoftSleeper;

public class MksAdvCheckbox extends MksCheckbox{

	private MksAdvCheckbox(By locator) {
		super(locator);
	}
	
	
	private static By checkboxLabelsLocator = null;
	private static WebElement checkboxLabel = null;
	private static List<WebElement> checkboxLabels = null;
	private static int elementsSize = 0;
	public MksAdvCheckbox(By checkBoxesLocator, By associatedLabelsLocator) throws InvalidParameterSpecException {
		super(checkBoxesLocator);
		checkboxLabelsLocator = associatedLabelsLocator;
		checkboxLabel = Finder.find(associatedLabelsLocator);
		checkboxLabels = Finder.findAll(associatedLabelsLocator);
		checkboxes = Finder.findAll(checkboxLocator);
		elementsSize = checkboxLabels.size();
		int labelsSize = checkboxes.size();
		if(!(labelsSize == elementsSize))
			throw new ElementsCountMisMatchException(checkBoxesLocator, "checkBoxesLocator", elementsSize, associatedLabelsLocator, "associatedLabelsLocator", labelsSize);
	}
	
	/**
	 * If there are many checkboxs with same checkbox text, then identifies the first checkbox
	 * and returns the first checkbox
	 * 
	 * @param checkboxText
	 * @return If no checkbox identified, then returns null.
	 */
	public WebElement getCheckbox(String checkboxText) {
		for(int i=0; i<elementsSize; i++) {
			if(checkboxLabels.get(i).getText().trim().equals(checkboxText.trim()))
			{
				return checkboxes.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Helps to get the text of all checkboxs
	 * @return
	 */
	public List<String> getAllCheckboxsText() {
		
		return WdOperators.getTextOfElements(Finder.findAll(checkboxLabelsLocator));
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
		int count = 0, loopCount = 0;
		for (WebElement label : checkboxLabels) {
			if (label.getText().trim().equals(checkboxText.trim())) {
				count ++;
				if (count == ofCheckboxTextOccurrence) {
					return checkboxes.get(loopCount);
				}
			}
			loopCount++;
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
	public void click(String checkboxText) {

		checkbox = getCheckbox(checkboxText);
		if (checkbox == null)
			throw new NoElementFoundException(getExceptionMsg(checkboxText));
		else {
			checkbox.click();
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
	public void click(String checkboxText, int ofCheckboxTextOccurrence) {
		checkbox = getCheckbox(checkboxText, ofCheckboxTextOccurrence);
		if (checkbox == null)
			throw new NoElementFoundException(getExceptionMsg(checkboxText, ofCheckboxTextOccurrence));
		else {
			checkbox.click();
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
	public void leftClick(String checkboxText) {

		checkbox = getCheckbox(checkboxText);
		if (checkbox == null)
			throw new NoElementFoundException(getExceptionMsg(checkboxText));
		else {
			WdOperators.clickOnLocation(checkbox, 0, 0);
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
	public void leftClick(String checkboxText, int ofCheckboxTextOccurrence) {

		checkbox = getCheckbox(checkboxText, ofCheckboxTextOccurrence);
		if (checkbox == null)
			throw new NoElementFoundException(getExceptionMsg(checkboxText, ofCheckboxTextOccurrence));
		else {
			WdOperators.clickOnLocation(checkbox, 0, 0);
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
	public void jsClick(String checkboxText) {

		checkbox = getCheckbox(checkboxText);
		if (checkbox == null)
			throw new NoElementFoundException(getExceptionMsg(checkboxText));
		else {
			WdOperators.jsClick(checkbox);
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
	public void jsClick(String checkboxText, int ofCheckboxTextOccurrence) {
		
		checkbox = getCheckbox(checkboxText, ofCheckboxTextOccurrence);
		if (checkbox == null)
			throw new NoElementFoundException(getExceptionMsg(checkboxText, ofCheckboxTextOccurrence));
		else {
			WdOperators.jsClick(checkbox);
		}
	}
	
	private String getExceptionMsg(String labelText) {
		return String.format("There is no checkbox element found with the checkbox locator %s and the check box labes locators %s having a label text %s",
				checkboxLocator.toString(), checkboxLabelsLocator.toString(), labelText);
	}
	
	private String getExceptionMsg(String labelText, int textOccurrence) {
		return String.format("%s, with the occcurrence of text at position %s",
				getExceptionMsg(labelText), textOccurrence);
	}
	
	
}
