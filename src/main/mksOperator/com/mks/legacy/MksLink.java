package com.mks.legacy;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.naming.LinkException;
import javax.naming.directory.InvalidAttributesException;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mks.connectors.Connection;
import com.mks.definitions.LegacyElementsActions;
import com.mks.exceptions.NoElementFoundException;
import com.mks.knob.ArmWindows;
import com.mks.seluty.Finder;
import com.mks.seluty.WdOperators;

public class MksLink extends LegacyElementsActions {

	public MksLink(By locator) {
		elementLocator = locator;
		element = Finder.find(elementLocator);
	}

	/**
	 * Given link will be clicked
	 */
	public void click() {
		element.click();
	}

	/**
	 * Given link will be clicked with JavaScriptExecutor
	 */
	public void jsClick() {
		WdOperators.jsClick(element);
	}

	/**
	 * Given link will be mouse left clicked
	 */
	public void leftClick() {
		WdOperators.clickOnLocation(element, 0, 0);
	}

	/**
	 * If there are many links with same link text, then identifies the first link
	 * and returns the first link
	 * 
	 * @param linkText
	 * @return If no link identified, then returns null.
	 */
	public WebElement getLink(String linkText) {
		elements = Finder.findAll(elementLocator);
		System.out.println("All links are : " + elements.size());
		for (WebElement lnk : elements) {
			//System.out.println("lnk.getText().trim() : " + lnk.getText().trim());
			if (lnk.getText().trim().equals(linkText)) {
				return lnk;
			}
		}
		return null;
	}

	/**
	 * Helps to get the text of the link
	 * 
	 * @return
	 */
	public String getLinkText() {
		return WdOperators.getText(element);
	}

	/**
	 * Helps to get the text of all links
	 * 
	 * @return
	 */
	public List<String> getAllLinksText() {

		return WdOperators.getTextOfElements(Finder.findAll(elementLocator));
	}

	/**
	 * Helps to get the link, based on link text given along with the occurrence
	 * also will be utilized if more than one link is existing with same link text.
	 * If given positioned link is not existing in the link, then it will not get
	 * any link and return with null.
	 * 
	 * @param linkText             The link text of the UI should exactly match -
	 *                             case sensitive.
	 * @param ofLinkTextOccurrence The link text occurrence. Even if more links are
	 *                             identified with given locator, and wants to get
	 *                             second link of matching link text, then input 2
	 *                             would be appropriate.
	 * @return If found with given occurrence and able to get, then returns
	 *         WebElement. Else returns null.
	 */
	public WebElement getLink(String linkText, int ofLinkTextOccurrence) {
		if (ofLinkTextOccurrence < 1)
			throw new InvalidArgumentException(String.format(
					"The link text %s position should be greater than zero, on which tried to perform action. Currently given position occurrence is %s",
					linkText, ofLinkTextOccurrence));
		elements = Finder.findAll(elementLocator);
		int count = 0;
		for (WebElement lnk : elements) {
			if (lnk.getText().trim().equals(linkText)) {
				count++;
				if (count == ofLinkTextOccurrence) {
					return lnk;
				}
			}
			count++;
		}
		return null;
	}

	/**
	 * Helps to click on the link, based on link text given. If more than one link
	 * is existing in DOM with same link text, then it clicks on first occurrence
	 * element by default.
	 * 
	 * @param linkText The link text of the UI should exactly match - case
	 *                 sensitive.
	 * @return If found and clicked, then returns true. Else returns false.
	 */
	public void click(String linkText) {

		element = getLink(linkText);
		if (element == null)
			throw new NoElementFoundException(getExceptionMsg(linkText));
		else {
			element.click();
		}

	}

	/**
	 * Helps to click on the link, based on link text given along with the
	 * occurrence also will be utilized if more than one link is existing with same
	 * link text. If given positioned link is not existing in the link, then it will
	 * not click on any link and return with false.
	 * 
	 * @param linkText             The link text of the UI should exactly match -
	 *                             case sensitive.
	 * @param ofLinkTextOccurrence The link text occurrence. Even if more links are
	 *                             identified with given locator, and wants to click
	 *                             on second link of matching link text, then input
	 *                             2 would be appropriate.
	 * @return If found with given occurrence and clicked, then returns true. Else
	 *         returns false.
	 */
	public void click(String linkText, int ofLinkTextOccurrence) {
		element = getLink(linkText, ofLinkTextOccurrence);
		if (element == null)
			throw new NoElementFoundException(getExceptionMsg(linkText, ofLinkTextOccurrence));
		else {
			element.click();
		}
	}

	/**
	 * Helps to click on the link using mouse, based on link text given. If more
	 * than one link is existing in DOM with same link text, then it mouse left
	 * clicks on first occurrence element by default.
	 * 
	 * @param linkText The link text of the UI should exactly match - case
	 *                 sensitive.
	 * @return If found and mouse left clicked, then returns true. Else returns
	 *         false.
	 */
	public void leftClick(String linkText) {

		element = getLink(linkText);
		if (element == null)
			throw new NoElementFoundException(getExceptionMsg(linkText));
		else {
			WdOperators.clickOnLocation(element, 0, 0);
		}
	}

	/**
	 * Helps to click on the link using mouse left click, based on link text given
	 * along with the occurrence also will be utilized if more than one link is
	 * existing with same link text. If given positioned link is not existing in the
	 * link, then it will not click on any link and return with false.
	 * 
	 * @param linkText             The link text of the UI should exactly match -
	 *                             case sensitive.
	 * @param ofLinkTextOccurrence The link text occurrence. Even if more links are
	 *                             identified with given locator, and wants to click
	 *                             on second link of matching link text, then input
	 *                             2 would be appropriate.
	 * @return If found with given occurrence and mouse left clicked, then returns
	 *         true. Else returns false.
	 */
	public void leftClick(String linkText, int ofLinkTextOccurrence) {

		element = getLink(linkText, ofLinkTextOccurrence);
		if (element == null)
			throw new NoElementFoundException(getExceptionMsg(linkText, ofLinkTextOccurrence));
		else {
			WdOperators.clickOnLocation(element, 0, 0);
		}
	}

	/**
	 * Helps to click on the link using JavaScript click, based on link text given.
	 * If more than one link is existing in DOM with same link text, then it JS
	 * clicks on first occurrence element by default.
	 * 
	 * @param linkText The link text of the UI should exactly match - case
	 *                 sensitive.
	 * @return If found and JS clicked, then returns true. Else returns false.
	 */
	public void jsClick(String linkText) {

		element = getLink(linkText);
		if (element == null)
			throw new NoElementFoundException(getExceptionMsg(linkText));
		else {
			WdOperators.jsClick(element);
		}
	}

	/**
	 * Helps to click on the link using JavaScriptExecutor click, based on link text
	 * given along with the occurrence also will be utilized if more than one link
	 * is existing with same link text. If given positioned link is not existing in
	 * the link, then it will not JS click on any link and return with false.
	 * 
	 * @param linkText             The link text of the UI should exactly match -
	 *                             case sensitive.
	 * @param ofLinkTextOccurrence The link text occurrence. Even if more links are
	 *                             identified with given locator, and wants to JS
	 *                             click on second link of matching link text, then
	 *                             input 2 would be appropriate.
	 * @return If found with given occurrence and JS clicked, then returns true.
	 *         Else returns false.
	 */
	public void jsClick(String linkText, int ofLinkTextOccurrence) {

		element = getLink(linkText, ofLinkTextOccurrence);
		if (element == null)
			throw new NoElementFoundException(getExceptionMsg(linkText, ofLinkTextOccurrence));
		else {
			WdOperators.jsClick(element);
		}
	}

	/**
	 * It helps to return the href value of the given link locator.
	 * 
	 * @return If href found, then returns href value, else returns null
	 * @throws InvalidAttributesException In case of non-anchor locator, it will
	 *                                    throw exception.
	 */
	public String getHrefLink() throws InvalidAttributesException {
		if (element.getTagName().equals("a"))
			return element.getAttribute("href");
		else
			throw new InvalidAttributesException(String.format(
					"Given link locator should end with anchor tag a, inorder to identify the value of href attribute. Currently given link locator is %s",
					elementLocator.toString()));
	}

	/**
	 * Helps to open the given link in a new tab / window.
	 * 
	 * @throws InvalidAttributesException In case of non-anchor locator, it will
	 *                                    throw exception.
	 * @throws LinkException              If href value finds then works fine, else
	 *                                    throws exception
	 * @return
	 */
	public String openInNewTab() throws InvalidAttributesException, LinkException {
		String hrefLink = getHrefLink();
		if (hrefLink == null)
			throw new LinkException(String.format(
					"There is no href link found for the given locator %s, and so returned href with NULL",
					elementLocator.toString()));
		String str = ArmWindows.openNewTab();
		Connection.getDriver().get(hrefLink);
		return str;
	}


	/**
	 * Helps to open the given link in a new tab/ window. Note- Identifies the link with given link text in order to perform action on it.
	 * 
	 * @param linkText             The link text of the UI should exactly match -
	 *                             case sensitive.
	 * @throws InvalidAttributesException In case of non-anchor locator, it will
	 *                                    throw exception.
	 * @throws LinkException              If href value finds then works fine, else
	 *                                    throws exception
	 * @return
	 */
	public String openInNewTab(String linkText, int ofLinkTextOccurrence) throws InvalidAttributesException, LinkException {
		element = getLink(linkText, ofLinkTextOccurrence);
		if(element == null)
			throw new NoElementFoundException(String.format("Did not find the link with text %s, for the given locator %s and wit the link text position %s", linkText, elementLocator.toString(), ofLinkTextOccurrence));
		String hrefLink = getHrefLink();
		if (hrefLink == null)
			throw new LinkException(String.format(
					"There is no href link found for the given locator %s, and so returned href with NULL",
					elementLocator.toString()));
		String str = ArmWindows.openNewTab();
		Connection.getDriver().get(hrefLink);
		return str;
	}

	/**
	 * Helps to open the given link in a new tab/ window. Note- Identifies the link with given link text in order to perform action on it.
	 * 
	 * @param linkText             The link text of the UI should exactly match -
	 *                             case sensitive.
	 * @throws InvalidAttributesException In case of non-anchor locator, it will
	 *                                    throw exception.
	 * @throws LinkException              If href value finds then works fine, else
	 *                                    throws exception
	 * @return Returns the window identification of last controlled one.
	 */
	public String openInNewTab(String linkText) throws InvalidAttributesException, LinkException {
		element = getLink(linkText);
		if(element == null)
			throw new NoElementFoundException(String.format("Did not find the link with text %s, for the given locator %s", linkText, elementLocator.toString()));
		String hrefLink = getHrefLink();
		if (hrefLink == null)
			throw new LinkException(String.format(
					"There is no href link found for the given locator %s, and so returned href with NULL",
					elementLocator.toString()));
		String str = ArmWindows.openNewTab();
		Connection.getDriver().get(hrefLink);
		return str;
	}
	
	private String getExceptionMsg(String linkTxt) {
		return String.format("There is no link element found for the link locator %s with the link text %s",
				elementLocator.toString(), linkTxt);
	}

	private String getExceptionMsg(String linkTxt, int textOccurrence) {
		return String.format("%s, at the occcurrence of text at position %s", getExceptionMsg(linkTxt), textOccurrence);
	}

	@Override
	public boolean isDisplayed() {
		return isDisplayed(element);
	}

	@Override
	public boolean isEnabled() {
		return isEnabled(element);
	}

	@Override
	public boolean isSelected() {
		return isSelected(element);
	}

}
