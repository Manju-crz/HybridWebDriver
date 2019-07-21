package com.mks.legacy;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activity.InvalidActivityException;
import javax.management.AttributeNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.StaleElementReferenceException;
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

public class MksCheckbox extends LegacyElementsActions {

	protected By checkboxLocator = null;
	protected WebElement checkbox = null;
	protected List<WebElement> checkboxes = null;
	protected int checkboxElementsFoundCount = 0;
	protected String labelAttributeName = null;

	/**
	 * Provides methods to work on checkbox type elements
	 * 
	 * @param locator Checkbox locator has to be passed as argument, which might
	 *                identify one or more elements in UI
	 */
	public MksCheckbox(By locator) {
		checkboxLocator = locator;
		checkbox = Finder.find(checkboxLocator);
	}

	/**
	 * Provides methods to work on checkbox type elements based on the attribute
	 * holding value same as label of the checkbox
	 * 
	 * @param locator                    Checkbox locator has to be passed as
	 *                                   argument, which might identify one or more
	 *                                   elements in UI
	 * @param attributeForCheckboxLabels The associated attribute which is having
	 *                                   checkbox label as value to the attribute
	 * @throws InvalidAlgorithmParameterException
	 */
	public MksCheckbox(By locator, String attributeForCheckboxLabels) throws InvalidAlgorithmParameterException {
		if ((attributeForCheckboxLabels == null) || attributeForCheckboxLabels.length() < 1)
			throw new InvalidAlgorithmParameterException(String.format(
					"The attributeForCheckboxLabels parameter spassed should have attribute type of checkbox with the attribute value as same as the label of checkbox. Else avoid this called constructor and use the alternative constructor."));
		labelAttributeName = attributeForCheckboxLabels;
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
	 * Given checkbox will be clicked, as per the given position of the checkbox
	 * elements starting from 1.
	 * 
	 */
	public void click(int... clickableElementsPositions) {

		List<Integer> lst = validateCheckboxesGivenPositionExistance(clickableElementsPositions);
		clickOnElementsBasedOnPositions(ClickTypes.SimpleClick, checkboxes, lst);
	}

	/**
	 * Given checkbox will be clicked, as per the given label of the checkbox
	 * 
	 * @throws AttributeNotFoundException
	 */
	public List<String> click(String... checkboxLabels) throws AttributeNotFoundException {

		Map<WebElement, String> validCheckBoxesForLabel = validateCheckboxesForGivenLabelAtttribure();

		List<String> nonClickedLabels = new ArrayList<String>();
		for (String label : checkboxLabels) {
			boolean foundLabel = false;
			for (WebElement chkbox : validCheckBoxesForLabel.keySet()) {
				if (label.equals(validCheckBoxesForLabel.get(chkbox))) {
					chkbox.click();
					foundLabel = true;
					break;
				}
			}
			if (!foundLabel)
				nonClickedLabels.add(label);
		}

		return nonClickedLabels;
	}

	/**
	 * Given checkbox will be clicked with JavaScriptExecutor
	 * 
	 * @throws InvalidActivityException
	 */
	public void jsClick(int... clickableElementsPositions) {
		List<Integer> lst = validateCheckboxesGivenPositionExistance(clickableElementsPositions);
		clickOnElementsBasedOnPositions(ClickTypes.JsClick, checkboxes, lst);
	}

	/**
	 * Given checkbox will be JS clicked, as per the given label of the checkbox
	 * 
	 * @throws AttributeNotFoundException
	 */
	public List<String> jsClick(String... checkboxLabels) throws AttributeNotFoundException {

		Map<WebElement, String> validCheckBoxesForLabel = validateCheckboxesForGivenLabelAtttribure();

		List<String> nonClickedLabels = new ArrayList<String>();
		for (String label : checkboxLabels) {
			boolean foundLabel = false;
			for (WebElement chkbox : validCheckBoxesForLabel.keySet()) {
				if (label.equals(validCheckBoxesForLabel.get(chkbox))) {
					WdOperators.jsClick(chkbox);
					foundLabel = true;
					break;
				}
			}
			if (!foundLabel)
				nonClickedLabels.add(label);
		}

		return nonClickedLabels;
	}

	/**
	 * Given checkbox will be mouse left clicked
	 * 
	 * @throws InvalidActivityException
	 */
	public void leftClick(int... clickableElementsPositions) {
		List<Integer> lst = validateCheckboxesGivenPositionExistance(clickableElementsPositions);
		clickOnElementsBasedOnPositions(ClickTypes.MouseLeftClick, checkboxes, lst);
	}

	/**
	 * Given checkbox will be mouse left clicked, as per the given label of the
	 * checkbox
	 * 
	 * @throws AttributeNotFoundException
	 */
	public List<String> leftClick(String... checkboxLabels) throws AttributeNotFoundException {

		Map<WebElement, String> validCheckBoxesForLabel = validateCheckboxesForGivenLabelAtttribure();

		List<String> nonClickedLabels = new ArrayList<String>();
		for (String label : checkboxLabels) {
			boolean foundLabel = false;
			for (WebElement chkbox : validCheckBoxesForLabel.keySet()) {
				if (label.equals(validCheckBoxesForLabel.get(chkbox))) {
					WdOperators.clickOnLocation(chkbox, 0, 0);
					foundLabel = true;
					break;
				}
			}
			if (!foundLabel)
				nonClickedLabels.add(label);
		}

		return nonClickedLabels;
	}

	/**
	 * Helps to select the checkbox, If at all already selected, then keeps as
	 * selected only without performing any action on it.
	 * 
	 * @return If given checkbox is not selected even after performing action on it
	 *         then it returns false; else if successfully selected then returns
	 *         true.
	 */
	public boolean select() {
		if (!checkbox.isSelected()) {
			click();
		}
		if (!checkbox.isSelected()) {
			jsClick();
		}
		if (!checkbox.isSelected()) {
			leftClick();
		}
		return checkbox.isSelected();
	}

	/**
	 * Helps to select the checkbox, If at all already selected, then keeps as
	 * selected only without performing any action on it. But the note is- it
	 * identifies checkbox elements to perform action , only if the checkbox is
	 * having the provided position in the UI and elements positions considers
	 * starting from 1.
	 * 
	 * @param selectableCheckboxesPositions
	 * @return If all provided positioned are selected then returns with empty
	 *         List<Integer>, If any of the positioned checkboxes fail to do the
	 *         chckbox selection action then such checkboxes positions are returned
	 *         in the form of List<Integer>
	 */
	public List<Integer> select(int... selectableCheckboxesPositions) {

		List<Integer> positions = validateCheckboxesGivenPositionExistance(selectableCheckboxesPositions);
		List<Integer> nonSelectedPositions = new ArrayList<>();
		int count = 0;
		for (WebElement checkbx : checkboxes) {
			count++;
			if (positions.contains(count)) {
				checkbox = checkbx;
				if (!select())
					nonSelectedPositions.add(count);
			}
		}
		return nonSelectedPositions;
	}

	/**
	 * Helps to select the checkbox. If at all already selected, then keeps as
	 * selected only without performing any action on it. But the note is- it
	 * identifies checkbox elements to perform action , only if the checkbox is
	 * having the provided label
	 * 
	 * @param checkboxLabels The checkboxes associated label text of which wants to
	 *                       select
	 * @return If all provided labels are selected then returns with empty
	 *         List<String>, If any of the labelled checkboxes are not found in the
	 *         UI or fails to do the chckbox selection action then such labels are
	 *         returned in the form of List<String>
	 * 
	 * @throws AttributeNotFoundException
	 */
	public List<String> select(String... checkboxLabels) throws AttributeNotFoundException {

		Map<WebElement, String> validCheckBoxesForLabel = validateCheckboxesForGivenLabelAtttribure();

		List<String> nonSelectedLabels = new ArrayList<String>();
		for (String label : checkboxLabels) {
			boolean selected = false;
			for (WebElement chkbox : validCheckBoxesForLabel.keySet()) {
				if (label.equals(validCheckBoxesForLabel.get(chkbox))) {
					checkbox = chkbox;
					selected = select();
					break;
				}
			}
			if (!selected)
				nonSelectedLabels.add(label);
		}

		return nonSelectedLabels;
	}

	/**
	 * Helps to un select the selected checkbox. If at all already un selected, then
	 * keeps as un selected only without performing any action on it.
	 * 
	 * @return If given checkbox is being as selected even after performing action
	 *         on it then it returns false; else if successfully un selected then
	 *         returns true.
	 */
	public boolean unSelect() {
		if (checkbox.isSelected()) {
			click();
		}
		if (checkbox.isSelected()) {
			jsClick();
		}
		if (checkbox.isSelected()) {
			leftClick();
		}
		if(checkbox.isSelected())
			return false;
		return true;
	}

	/**
	 * Helps to un select the checkbox. If at all already un selected, then keeps as
	 * un selected only without performing any action on it. But the note is- it
	 * identifies checkbox elements to perform action , only if the checkbox is
	 * having the provided position in the UI and elements positions considers
	 * starting from 1.
	 * 
	 * @param selectableCheckboxesPositions
	 * @return If all provided positioned are un selected then returns with empty
	 *         List<Integer>, If any of the positioned checkboxes fail to do the
	 *         chckbox un selection action then such checkboxes positions are
	 *         returned in the form of List<Integer>
	 */
	public List<Integer> unSelect(int... selectableCheckboxesPositions) {

		List<Integer> positions = validateCheckboxesGivenPositionExistance(selectableCheckboxesPositions);
		List<Integer> nonSelectedPositions = new ArrayList<>();
		int count = 0;
		for (WebElement checkbx : checkboxes) {
			count++;
			if (positions.contains(count)) {
				checkbox = checkbx;
				if (!unSelect())
					nonSelectedPositions.add(count);
			}
		}
		return nonSelectedPositions;
	}

	/**
	 * Helps to un select the checkbox. If at all already un selected, then keeps as
	 * un selected only without performing any action on it. But the note is- it
	 * identifies checkbox elements to perform action , only if the checkbox is
	 * having the provided label
	 * 
	 * @param checkboxLabels The checkboxes associated label text of which wants to
	 *                       un select
	 * @return If all provided labels are un selected then returns with empty
	 *         List<String>. If any of the labelled checkboxes are not found in the
	 *         UI or fails to do the chckbox un selection action then such labels
	 *         are returned in the form of List<String>
	 * 
	 * @throws AttributeNotFoundException
	 */
	public List<String> unSelect(String... checkboxLabels) throws AttributeNotFoundException {

		Map<WebElement, String> validCheckBoxesForLabel = validateCheckboxesForGivenLabelAtttribure();

		List<String> selectedLabels = new ArrayList<String>();
		for (String label : checkboxLabels) {
			boolean selected = false;
			for (WebElement chkbox : validCheckBoxesForLabel.keySet()) {
				if (label.equals(validCheckBoxesForLabel.get(chkbox))) {
					checkbox = chkbox;
					selected = unSelect();
					break;
				}
			}
			if (!selected)
				selectedLabels.add(label);
		}

		return selectedLabels;
	}

	@Override
	public boolean isSelected() {
		if (!checkbox.isSelected())
			return false;
		return true;
	}

	@Override
	public boolean isDisplayed() {
		try {
			if (checkbox.isDisplayed())
				return true;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean isEnabled() {
		if (!checkbox.isEnabled())
			return false;
		return true;
	}

	/**
	 * Helps to validate the given checkboxes having with the possible perform able
	 * positions in the UI
	 * 
	 * @param clickableElementsPositions
	 * @return
	 */
	private List<Integer> validateCheckboxesGivenPositionExistance(int... clickableElementsPositions) {
		checkboxes = Finder.findAll(checkboxLocator);
		int maxNum = MksArray.getLargest(clickableElementsPositions);
		checkboxElementsFoundCount = checkboxes.size();
		if (checkboxElementsFoundCount < maxNum)
			throw new NoElementFoundException(String.format(
					"Total %s checkboxes found with the checkboxes locator %s, and given input to select the checkbox of occurrence is %s in the ui ",
					checkboxElementsFoundCount, checkboxLocator.toString(), maxNum));
		return MksArray.getArraysAsList(clickableElementsPositions);
	}

	/**
	 * Helps to validate the given locator is having provided attributed label. Even
	 * it filters elements if not found with attribute values
	 * 
	 * @return
	 * @throws AttributeNotFoundException
	 */
	private Map<WebElement, String> validateCheckboxesForGivenLabelAtttribure() throws AttributeNotFoundException {
		checkboxes = Finder.findAll(checkboxLocator);
		Map<WebElement, String> validCheckBoxesForLabel = new HashMap<>();
		String attVal = "";

		for (WebElement chkbx : checkboxes) {
			attVal = chkbx.getAttribute(labelAttributeName);
			if (!(attVal == null))
				validCheckBoxesForLabel.put(chkbx, attVal);
		}

		if (validCheckBoxesForLabel.size() < 1) {
			throw new AttributeNotFoundException(String.format(
					"Given locator is %s, and the attribute provided is %s, So did not find the attribute value for given locators.",
					checkboxLocator.toString(), labelAttributeName));
		}
		return validCheckBoxesForLabel;
	}

}
