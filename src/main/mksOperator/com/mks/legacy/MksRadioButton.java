package com.mks.legacy;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activity.InvalidActivityException;
import javax.management.AttributeNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.mks.definitions.LegacyElementsActions;
import com.mks.definitions.LegacyElements.ClickTypes;
import com.mks.exceptions.NoElementFoundException;
import com.mks.seluty.Finder;
import com.mks.seluty.WdOperators;
import com.mks.utilizer.MksArray;

public class MksRadioButton extends LegacyElementsActions {

	protected By radiobuttonLocator = null;
	protected WebElement radiobutton = null;
	protected List<WebElement> radiobuttons = null;
	protected int radiobuttonElementsFoundCount = 0;
	protected String labelAttributeName = null;

	/**
	 * Provides methods to work on radiobutton type elements
	 * 
	 * @param locator RadioButton locator has to be passed as argument, which might
	 *                identify one or more elements in UI
	 */
	public MksRadioButton(By locator) {
		radiobuttonLocator = locator;
		radiobutton = Finder.find(radiobuttonLocator);
	}

	/**
	 * Provides methods to work on radiobutton type elements based on the attribute
	 * holding value same as label of the radiobutton
	 * 
	 * @param locator                       RadioButton locator has to be passed as
	 *                                      argument, which might identify one or
	 *                                      more elements in UI
	 * @param attributeForRadioButtonLabels The associated attribute which is having
	 *                                      radiobutton label as value to the
	 *                                      attribute
	 * @throws InvalidAlgorithmParameterException
	 */
	public MksRadioButton(By locator, String attributeForRadioButtonLabels) throws InvalidAlgorithmParameterException {
		if ((attributeForRadioButtonLabels == null) || attributeForRadioButtonLabels.length() < 1)
			throw new InvalidAlgorithmParameterException(String.format(
					"The attributeForRadioButtonLabels parameter spassed should have attribute type of radiobutton with the attribute value as same as the label of radiobutton. Else avoid this called constructor and use the alternative constructor."));
		labelAttributeName = attributeForRadioButtonLabels;
		radiobuttonLocator = locator;
		radiobutton = Finder.find(radiobuttonLocator);
	}

	/**
	 * Given radiobutton will be clicked
	 */
	public void click() {
		radiobutton.click();
	}

	/**
	 * Given radiobutton will be clicked with JavaScriptExecutor
	 */
	public void jsClick() {
		WdOperators.jsClick(radiobutton);
	}

	/**
	 * Given radiobutton will be mouse left clicked
	 */
	public void leftClick() {
		WdOperators.clickOnLocation(radiobutton, 0, 0);
	}

	/**
	 * Given radiobutton will be clicked, as per the given position of the
	 * radiobutton elements starting from 1.
	 * 
	 */
	/*
	 * public void click(int... clickableElementsPositions) {
	 * 
	 * List<Integer> lst =
	 * validateRadioButtonsGivenPositionExistance(clickableElementsPositions);
	 * clickOnElementsBasedOnPositions(ClickTypes.SimpleClick, radiobuttons, lst); }
	 */

	/**
	 * Given radiobutton will be clicked, as per the given position of the
	 * radiobutton elements starting from 1.
	 * 
	 * @param clickableElementPosition
	 */
	public void click(int clickableElementPosition) {

		List<Integer> lst = validateRadioButtonsGivenPositionExistance(clickableElementPosition);
		clickOnElementsBasedOnPositions(ClickTypes.SimpleClick, radiobuttons, lst);
	}

	/**
	 * Given radiobutton will be clicked, as per the given label of the radiobutton
	 * 
	 * @throws AttributeNotFoundException
	 */
	/*
	 * public List<String> click(String... radiobuttonLabels) throws
	 * AttributeNotFoundException {
	 * 
	 * Map<WebElement, String> validRadioButtonsForLabel =
	 * validateRadioButtonsForGivenLabelAtttribure();
	 * 
	 * List<String> nonClickedLabels = new ArrayList<String>(); for (String label :
	 * radiobuttonLabels) { boolean foundLabel = false; for (WebElement rdobtn :
	 * validRadioButtonsForLabel.keySet()) { if
	 * (label.equals(validRadioButtonsForLabel.get(rdobtn))) { rdobtn.click();
	 * foundLabel = true; break; } } if (!foundLabel) nonClickedLabels.add(label); }
	 * 
	 * return nonClickedLabels; }
	 */

	/**
	 * Given radiobutton will be clicked, as per the given label of the radiobutton.
	 * 
	 * @param radiobuttonLabel
	 */
	public boolean click(String radiobuttonLabel) throws AttributeNotFoundException {

		Map<WebElement, String> validRadioButtonsForLabel = validateRadioButtonsForGivenLabelAtttribure();
		for (WebElement rdobtn : validRadioButtonsForLabel.keySet()) {
			if (radiobuttonLabel.equals(validRadioButtonsForLabel.get(rdobtn))) {
				rdobtn.click();
				return true;
			}
		}
		return false;
	}

	/**
	 * Given radiobutton will be clicked with JavaScriptExecutor
	 * 
	 * @throws InvalidActivityException
	 */
	/*
	 * public void jsClick(int... clickableElementsPositions) { List<Integer> lst =
	 * validateRadioButtonsGivenPositionExistance(clickableElementsPositions);
	 * clickOnElementsBasedOnPositions(ClickTypes.JsClick, radiobuttons, lst); }
	 */

	/**
	 * Given radiobutton will be JS clicked, as per the given position of the
	 * radiobutton elements starting from 1.
	 * 
	 * @param clickableElementPosition
	 */
	public void jsClick(int clickableElementPosition) {

		List<Integer> lst = validateRadioButtonsGivenPositionExistance(clickableElementPosition);
		clickOnElementsBasedOnPositions(ClickTypes.JsClick, radiobuttons, lst);
	}

	/**
	 * Given radiobutton will be JS clicked, as per the given label of the
	 * radiobutton
	 * 
	 * @throws AttributeNotFoundException
	 */
	/*
	 * public List<String> jsClick(String... radiobuttonLabels) throws
	 * AttributeNotFoundException {
	 * 
	 * Map<WebElement, String> validRadioButtonsForLabel =
	 * validateRadioButtonsForGivenLabelAtttribure();
	 * 
	 * List<String> nonClickedLabels = new ArrayList<String>(); for (String label :
	 * radiobuttonLabels) { boolean foundLabel = false; for (WebElement rdobtn :
	 * validRadioButtonsForLabel.keySet()) { if
	 * (label.equals(validRadioButtonsForLabel.get(rdobtn))) {
	 * WdOperators.jsClick(rdobtn); foundLabel = true; break; } } if (!foundLabel)
	 * nonClickedLabels.add(label); }
	 * 
	 * return nonClickedLabels; }
	 */

	/**
	 * Given radiobutton will be JS clicked, as per the given label of the
	 * radiobutton.
	 * 
	 * @param radiobuttonLabel
	 */
	public boolean jsClick(String radiobuttonLabel) throws AttributeNotFoundException {

		Map<WebElement, String> validRadioButtonsForLabel = validateRadioButtonsForGivenLabelAtttribure();
		for (WebElement rdobtn : validRadioButtonsForLabel.keySet()) {
			if (radiobuttonLabel.equals(validRadioButtonsForLabel.get(rdobtn))) {
				WdOperators.jsClick(rdobtn);
				return true;
			}
		}
		return false;
	}

	/**
	 * Given radiobutton will be mouse left clicked
	 * 
	 * @throws InvalidActivityException
	 */
	/*
	 * public void leftClick(int... clickableElementsPositions) { List<Integer> lst
	 * = validateRadioButtonsGivenPositionExistance(clickableElementsPositions);
	 * clickOnElementsBasedOnPositions(ClickTypes.MouseLeftClick, radiobuttons,
	 * lst); }
	 */

	/**
	 * Given radiobutton will be Mouse Left clicked, as per the given position of
	 * the radiobutton elements starting from 1.
	 * 
	 * @param clickableElementPosition
	 */
	public void leftClick(int clickableElementPosition) {

		List<Integer> lst = validateRadioButtonsGivenPositionExistance(clickableElementPosition);
		clickOnElementsBasedOnPositions(ClickTypes.MouseLeftClick, radiobuttons, lst);
	}

	/**
	 * Given radiobutton will be mouse left clicked, as per the given label of the
	 * radiobutton
	 * 
	 * @throws AttributeNotFoundException
	 */
	/*
	 * public List<String> leftClick(String... radiobuttonLabels) throws
	 * AttributeNotFoundException {
	 * 
	 * Map<WebElement, String> validRadioButtonsForLabel =
	 * validateRadioButtonsForGivenLabelAtttribure();
	 * 
	 * List<String> nonClickedLabels = new ArrayList<String>(); for (String label :
	 * radiobuttonLabels) { boolean foundLabel = false; for (WebElement rdobtn :
	 * validRadioButtonsForLabel.keySet()) { if
	 * (label.equals(validRadioButtonsForLabel.get(rdobtn))) {
	 * WdOperators.clickOnLocation(rdobtn, 0, 0); foundLabel = true; break; } } if
	 * (!foundLabel) nonClickedLabels.add(label); }
	 * 
	 * return nonClickedLabels; }
	 */

	/**
	 * Given radiobutton will be mpuse left clicked, as per the given label of the
	 * radiobutton.
	 * 
	 * @param radiobuttonLabel
	 */
	public boolean leftClick(String radiobuttonLabel) throws AttributeNotFoundException {

		Map<WebElement, String> validRadioButtonsForLabel = validateRadioButtonsForGivenLabelAtttribure();
		for (WebElement rdobtn : validRadioButtonsForLabel.keySet()) {
			if (radiobuttonLabel.equals(validRadioButtonsForLabel.get(rdobtn))) {
				WdOperators.clickOnLocation(rdobtn, 0, 0);
				return true;
			}
		}
		return false;
	}

	/**
	 * Helps to select the radiobutton, If at all already selected, then keeps as
	 * selected only without performing any action on it.
	 * 
	 * @return If given radiobutton is not selected even after performing action on
	 *         it then it returns false; else if successfully selected then returns
	 *         true.
	 */
	public boolean select() {
		if (!radiobutton.isSelected()) {
			click();
		}
		if (!radiobutton.isSelected()) {
			jsClick();
		}
		if (!radiobutton.isSelected()) {
			leftClick();
		}
		return radiobutton.isSelected();
	}

	/**
	 * Helps to select the radiobutton, If at all already selected, then keeps as
	 * selected only without performing any action on it. But the note is- it
	 * identifies radiobutton elements to perform action , only if the radiobutton
	 * is having the provided position in the UI and elements positions considers
	 * starting from 1.
	 * 
	 * @param selectableRadioButtonsPositions
	 * @return If all provided positioned are selected then returns with empty
	 *         List<Integer>, If any of the positioned radiobuttons fail to do the
	 *         chckbox selection action then such radiobuttons positions are
	 *         returned in the form of List<Integer>
	 */
	/*
	 * public List<Integer> select(int... selectableRadioButtonsPositions) {
	 * 
	 * List<Integer> positions =
	 * validateRadioButtonsGivenPositionExistance(selectableRadioButtonsPositions);
	 * List<Integer> nonSelectedPositions = new ArrayList<>(); int count = 0; for
	 * (WebElement radioBtn : radiobuttons) { count++; if
	 * (positions.contains(count)) { radiobutton = radioBtn; if (!select())
	 * nonSelectedPositions.add(count); } } return nonSelectedPositions; }
	 */

	/**
	 * Helps to select the radiobutton, If at all already selected, then keeps as
	 * selected only without performing any action on it. But the note is- it
	 * identifies radiobutton elements to perform action , only if the radiobutton
	 * is having the provided position in the UI and elements positions considers
	 * starting from 1.
	 * 
	 * @param selectableRadioButtonsPositions
	 * @return If all provided positioned are selected then returns with true; else
	 *         returns false;
	 */
	public boolean select(int selectableRadioButtonPosition) {

		List<Integer> positions = validateRadioButtonsGivenPositionExistance(selectableRadioButtonPosition);
		int count = 0;
		for (WebElement radioBtn : radiobuttons) {
			count++;
			if (positions.contains(count)) {
				radiobutton = radioBtn;
				if (select())
					return true;
			}
		}
		return false;
	}

	/**
	 * Helps to select the radiobutton, If at all already selected, then keeps as
	 * selected only without performing any action on it. But the note is- it
	 * identifies radiobutton elements to perform action, only if the given locator
	 * identifies more than one radio button element in the UI, so that it can cross
	 * check other radio buttons becomes un selected once after selecting the given
	 * positioned radio button.
	 * 
	 * @param selectableRadioButtonPosition
	 * @return If given radio button is selected then returns true, else returns
	 *         false;
	 * @throws InvalidAlgorithmParameterException Once after selecting given
	 *                                            element, if other radio button
	 *                                            from given group is still being
	 *                                            selected, then throws exception.
	 */
	public boolean selectFromGroup(int selectableRadioButtonPosition) throws InvalidAlgorithmParameterException {
		List<Integer> positions = validateRadioButtonsGivenPositionExistance(selectableRadioButtonPosition);
		validateMoreRadioButtonsFound();
		int count = 0;
		boolean selected = false;
		for (WebElement radioBtn : radiobuttons) {
			count++;
			if (positions.contains(count)) {
				radiobutton = radioBtn;
				if (select()) {
					selected = true;
					break;
				}
			}
		}
		if (selected) {
			for (int i = 0; i < radiobuttons.size(); i++) {
				if ((i + 1) != count) {
					radiobutton = radiobuttons.get(i);
					if (isSelected())
						throw new ElementNotSelectableException(String.format(
								"Once after selecting the radio button at the position %s, still found other radio button from the group are in selected state at the position %s, and the given group locator is %s",
								selectableRadioButtonPosition, (i + 1), radiobuttonLocator.toString()));
				}
			}
		}
		return selected;
	}

	/**
	 * Helps to select the radiobutton. If at all already selected, then keeps as
	 * selected only without performing any action on it. But the note is- it
	 * identifies radiobutton elements to perform action , only if the radiobutton
	 * is having the provided label
	 * 
	 * @param radiobuttonLabels The radiobuttons associated label text of which
	 *                          wants to select
	 * @return If all provided labels are selected then returns with empty
	 *         List<String>, If any of the labelled radiobuttons are not found in
	 *         the UI or fails to do the chckbox selection action then such labels
	 *         are returned in the form of List<String>
	 * 
	 * @throws AttributeNotFoundException
	 */
	/*
	 * public List<String> select(String... radiobuttonLabels) throws
	 * AttributeNotFoundException {
	 * 
	 * Map<WebElement, String> validRadioButtonsForLabel =
	 * validateRadioButtonsForGivenLabelAtttribure();
	 * 
	 * List<String> nonSelectedLabels = new ArrayList<String>(); for (String label :
	 * radiobuttonLabels) { boolean selected = false; for (WebElement rdobtn :
	 * validRadioButtonsForLabel.keySet()) { if
	 * (label.equals(validRadioButtonsForLabel.get(rdobtn))) { radiobutton = rdobtn;
	 * selected = select(); break; } } if (!selected) nonSelectedLabels.add(label);
	 * }
	 * 
	 * return nonSelectedLabels; }
	 */

	/**
	 * Helps to select the radiobutton, If at all already selected, then keeps as
	 * selected only without performing any action on it. But the note is- it
	 * identifies radiobutton elements to perform action , only if the radiobutton
	 * is having the provided label in the UI.
	 * 
	 * @param selectableRadioButtonsPositions
	 * @return If all provided positioned are selected then returns with true; else
	 *         returns false;
	 */
	public boolean select(String radiobuttonLabel) throws AttributeNotFoundException {

		Map<WebElement, String> validRadioButtonsForLabel = validateRadioButtonsForGivenLabelAtttribure();
		for (WebElement rdobtn : validRadioButtonsForLabel.keySet()) {
			if (radiobuttonLabel.equals(validRadioButtonsForLabel.get(rdobtn))) {
				radiobutton = rdobtn;
				return select();
			}
		}
		return false;
	}

	/**
	 * Helps to select the radiobutton, If at all already selected, then keeps as
	 * selected only without performing any action on it. But the note is- it
	 * identifies radiobutton elements to perform action, only if the given locator
	 * identifies more than one radio button element in the UI, so that it can cross
	 * check other radio buttons becomes un selected once after selecting the given
	 * labeled radio button.
	 * 
	 * @param radiobuttonLabel
	 * @return If given radio button is selected then returns true, else returns
	 *         false;
	 * @throws AttributeNotFoundException
	 * @throws InvalidAlgorithmParameterException Once after selecting given
	 *                                            element, if other radio button
	 *                                            from given group is still being
	 *                                            selected, then throws exception.
	 */
	public boolean selectFromGroup(String radiobuttonLabel)
			throws AttributeNotFoundException, InvalidAlgorithmParameterException {

		Map<WebElement, String> validRadioButtonsForLabel = validateRadioButtonsForGivenLabelAtttribure();
		validateMoreRadioButtonsFound();
		boolean selected = false;
		for (WebElement rdobtn : validRadioButtonsForLabel.keySet()) {
			if (radiobuttonLabel.equals(validRadioButtonsForLabel.get(rdobtn))) {
				radiobutton = rdobtn;
				selected = select();
			}
		}
		if (selected) {
			for (WebElement rdobtn : validRadioButtonsForLabel.keySet()) {
				if (!(radiobuttonLabel.equals(validRadioButtonsForLabel.get(rdobtn)))) {
					radiobutton = rdobtn;
					if (isSelected())
						throw new ElementNotSelectableException(String.format(
								"Once after selecting the radio button with the label %s, still found other radio button from the group is in selected state and the label of that is %s, where the given group locator is %s",
								radiobuttonLabel, validRadioButtonsForLabel.get(rdobtn),
								radiobuttonLocator.toString()));
				}
			}
		}
		return selected;
	}

	/**
	 * Helps to un select the selected radiobutton. If at all already un selected,
	 * then keeps as un selected only without performing any action on it.
	 * 
	 * @return If given radiobutton is being as selected even after performing
	 *         action on it then it returns false; else if successfully un selected
	 *         then returns true.
	 */
	/*
	 * public boolean unSelect() { if (radiobutton.isSelected()) { click(); } if
	 * (radiobutton.isSelected()) { jsClick(); } if (radiobutton.isSelected()) {
	 * leftClick(); } if (radiobutton.isSelected()) return false; return true; }
	 */

	/**
	 * Helps to un select the radiobutton. If at all already un selected, then keeps
	 * as un selected only without performing any action on it. But the note is- it
	 * identifies radiobutton elements to perform action , only if the radiobutton
	 * is having the provided position in the UI and elements positions considers
	 * starting from 1.
	 * 
	 * @param selectableRadioButtonsPositions
	 * @return If all provided positioned are un selected then returns with empty
	 *         List<Integer>, If any of the positioned radiobuttons fail to do the
	 *         chckbox un selection action then such radiobuttons positions are
	 *         returned in the form of List<Integer>
	 */
	/*
	 * public List<Integer> unSelect(int... selectableRadioButtonsPositions) {
	 * 
	 * List<Integer> positions =
	 * validateRadioButtonsGivenPositionExistance(selectableRadioButtonsPositions);
	 * List<Integer> nonSelectedPositions = new ArrayList<>(); int count = 0; for
	 * (WebElement radioBtn : radiobuttons) { count++; if
	 * (positions.contains(count)) { radiobutton = radioBtn; if (!unSelect())
	 * nonSelectedPositions.add(count); } } return nonSelectedPositions; }
	 */

	/**
	 * Helps to un select the radiobutton. If at all already un selected, then keeps
	 * as un selected only without performing any action on it. But the note is- it
	 * identifies radiobutton elements to perform action , only if the radiobutton
	 * is having the provided label
	 * 
	 * @param radiobuttonLabels The radiobuttons associated label text of which
	 *                          wants to un select
	 * @return If all provided labels are un selected then returns with empty
	 *         List<String>. If any of the labelled radiobuttons are not found in
	 *         the UI or fails to do the chckbox un selection action then such
	 *         labels are returned in the form of List<String>
	 * 
	 * @throws AttributeNotFoundException
	 */
	/*
	 * public List<String> unSelect(String... radiobuttonLabels) throws
	 * AttributeNotFoundException {
	 * 
	 * Map<WebElement, String> validRadioButtonsForLabel =
	 * validateRadioButtonsForGivenLabelAtttribure();
	 * 
	 * List<String> selectedLabels = new ArrayList<String>(); for (String label :
	 * radiobuttonLabels) { boolean selected = false; for (WebElement rdobtn :
	 * validRadioButtonsForLabel.keySet()) { if
	 * (label.equals(validRadioButtonsForLabel.get(rdobtn))) { radiobutton = rdobtn;
	 * selected = unSelect(); break; } } if (!selected) selectedLabels.add(label); }
	 * 
	 * return selectedLabels; }
	 */

	@Override
	public boolean isSelected() {
		if (!radiobutton.isSelected())
			return false;
		return true;
	}

	@Override
	public boolean isDisplayed() {
		try {
			if (radiobutton.isDisplayed())
				return true;
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean isEnabled() {
		if (!radiobutton.isEnabled())
			return false;
		return true;
	}

	/**
	 * Helps to validate the given radiobuttons having with the possible perform
	 * able positions in the UI
	 * 
	 * @param clickableElementsPositions
	 * @return
	 */
	private List<Integer> validateRadioButtonsGivenPositionExistance(int... clickableElementsPositions) {
		radiobuttons = Finder.findAll(radiobuttonLocator);
		int maxNum = MksArray.getLargest(clickableElementsPositions);
		radiobuttonElementsFoundCount = radiobuttons.size();
		if (radiobuttonElementsFoundCount < maxNum)
			throw new NoElementFoundException(String.format(
					"Total %s radiobuttons found with the radiobuttons locator %s, and given input to select the radiobutton of occurrence is %s in the ui ",
					radiobuttonElementsFoundCount, radiobuttonLocator.toString(), maxNum));
		return MksArray.getArraysAsList(clickableElementsPositions);
	}

	/**
	 * Helps to validate the given locator is having provided attributed label. Even
	 * it filters elements if not found with attribute values
	 * 
	 * @return
	 * @throws AttributeNotFoundException
	 */
	private Map<WebElement, String> validateRadioButtonsForGivenLabelAtttribure() throws AttributeNotFoundException {
		radiobuttons = Finder.findAll(radiobuttonLocator);
		Map<WebElement, String> validRadioButtonsForLabel = new HashMap<>();
		String attVal = "";

		for (WebElement rdobtn : radiobuttons) {
			attVal = rdobtn.getAttribute(labelAttributeName);
			if (!(attVal == null))
				validRadioButtonsForLabel.put(rdobtn, attVal);
		}

		if (validRadioButtonsForLabel.size() < 1) {
			throw new AttributeNotFoundException(String.format(
					"Given locator is %s, and the attribute provided is %s, So did not find the attribute value for given locators.",
					radiobuttonLocator.toString(), labelAttributeName));
		}
		return validRadioButtonsForLabel;
	}

	private void validateMoreRadioButtonsFound() throws InvalidAlgorithmParameterException {
		radiobuttons = Finder.findAll(radiobuttonLocator);
		if (radiobuttons.size() < 2) {
			throw new InvalidAlgorithmParameterException(String.format(
					"Currently identified %s elements in the UI using %s locator. Alteast 2 elements should be identified to work on this action",
					radiobuttons.size(), radiobuttonLocator));
		}
	}

}
