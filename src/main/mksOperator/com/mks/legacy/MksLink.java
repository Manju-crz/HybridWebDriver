package com.mks.legacy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;

import com.mks.seluty.Finder;
import com.mks.seluty.WdOperators;

public class MksLink {

	private static By linkLocator = null;
	private static WebElement link = null;
	private static List<WebElement> links = null;

	public MksLink(By locator) {
		linkLocator = locator;
		link = Finder.find(linkLocator);
	}

	/**
	 * Given link will be clicked
	 */
	public void click() {
		link.click();
	}

	/**
	 * Given link will be clicked with JavaScriptExecutor
	 */
	public void jsClick() {
		WdOperators.jsClick(link);
	}

	/**
	 * Given link will be mouse left clicked
	 */
	public void leftClick() {
		WdOperators.clickOnLocation(link, 0, 0);
	}
	
	/**
	 * If there are many links with same link text, then identifies the first link
	 * and returns the first link
	 * 
	 * @param linkText
	 * @return If no link identified, then returns null.
	 */
	public WebElement getLink(String linkText) {
		links = Finder.findAll(linkLocator);
		System.out.println("All links are : " + links.size());
		for (WebElement lnk : links) {
			System.out.println("lnk.getText().trim() : " + lnk.getText().trim());
			if (lnk.getText().trim().equals(linkText)) {
				return lnk;
			}
		}
		return null;
	}
	
	/**
	 * Helps to get the text of the link
	 * @return
	 */
	public String getLinkText() {
		return WdOperators.getText(link);
	}

	/**
	 * Helps to get the text of all links
	 * @return
	 */
	public List<String> getAllLinksText() {
		
		return WdOperators.getTextOfElements(Finder.findAll(linkLocator));
	}
	
	/**
	 * Helps to get the link, based on link text given along with the occurrence
	 * also will be utilized if more than one link is existing with same link text.
	 * If given positioned link is not existing in the link, then it will not get
	 * any link and return with null.
	 * 
	 * @param linkText
	 *            The link text of the UI should exactly match - case sensitive.
	 * @param ofLinkTextOccurrence
	 *            The link text occurrence. Even if more links are identified with
	 *            given locator, and wants to get second link of matching link text,
	 *            then input 2 would be appropriate.
	 * @return If found with given occurrence and able to get, then returns
	 *         WebElement. Else returns null.
	 */
	public WebElement getLink(String linkText, int ofLinkTextOccurrence) {
		if (ofLinkTextOccurrence < 1)
			throw new InvalidArgumentException(String.format("The link text %s position should be greater than zero, on which tried to perform action. Currently given position occurrence is %s", linkText, ofLinkTextOccurrence));
		links = Finder.findAll(linkLocator);
		int count = 0;
		for (WebElement lnk : links) {
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
	 * @param linkText
	 *            The link text of the UI should exactly match - case sensitive.
	 * @return If found and clicked, then returns true. Else returns false.
	 */
	public boolean click(String linkText) {

		link = getLink(linkText);
		if (link == null)
			return false;
		else {
			link.click();
			return true;
		}

	}

	/**
	 * Helps to click on the link, based on link text given along with the
	 * occurrence also will be utilized if more than one link is existing with same
	 * link text. If given positioned link is not existing in the link, then it will
	 * not click on any link and return with false.
	 * 
	 * @param linkText
	 *            The link text of the UI should exactly match - case sensitive.
	 * @param ofLinkTextOccurrence
	 *            The link text occurrence. Even if more links are identified with
	 *            given locator, and wants to click on second link of matching link
	 *            text, then input 2 would be appropriate.
	 * @return If found with given occurrence and clicked, then returns true. Else
	 *         returns false.
	 */
	public boolean click(String linkText, int ofLinkTextOccurrence) {
		link = getLink(linkText, ofLinkTextOccurrence);
		if (link == null)
			return false;
		else {
			link.click();
			return true;
		}
	}

	/**
	 * Helps to click on the link using mouse, based on link text given. If more
	 * than one link is existing in DOM with same link text, then it mouse left
	 * clicks on first occurrence element by default.
	 * 
	 * @param linkText
	 *            The link text of the UI should exactly match - case sensitive.
	 * @return If found and mouse left clicked, then returns true. Else returns
	 *         false.
	 */
	public boolean leftClick(String linkText) {

		link = getLink(linkText);
		if (link == null)
			return false;
		else {
			WdOperators.clickOnLocation(link, 0, 0);
			return true;
		}
	}

	/**
	 * Helps to click on the link using mouse left click, based on link text given
	 * along with the occurrence also will be utilized if more than one link is
	 * existing with same link text. If given positioned link is not existing in the
	 * link, then it will not click on any link and return with false.
	 * 
	 * @param linkText
	 *            The link text of the UI should exactly match - case sensitive.
	 * @param ofLinkTextOccurrence
	 *            The link text occurrence. Even if more links are identified with
	 *            given locator, and wants to click on second link of matching link
	 *            text, then input 2 would be appropriate.
	 * @return If found with given occurrence and mouse left clicked, then returns
	 *         true. Else returns false.
	 */
	public boolean leftClick(String linkText, int ofLinkTextOccurrence) {

		link = getLink(linkText, ofLinkTextOccurrence);
		if (link == null)
			return false;
		else {
			WdOperators.clickOnLocation(link, 0, 0);
			return true;
		}
	}
	
	/**
	 * Helps to click on the link using JavaScript click, based on link text given. If more
	 * than one link is existing in DOM with same link text, then it JS
	 * clicks on first occurrence element by default.
	 * 
	 * @param linkText
	 *            The link text of the UI should exactly match - case sensitive.
	 * @return If found and JS clicked, then returns true. Else returns
	 *         false.
	 */
	public boolean jsClick(String linkText) {

		link = getLink(linkText);
		if (link == null)
			return false;
		else {
			WdOperators.jsClick(link);
			return true;
		}
	}

	/**
	 * Helps to click on the link using JavaScriptExecutor click, based on link text given
	 * along with the occurrence also will be utilized if more than one link is
	 * existing with same link text. If given positioned link is not existing in the
	 * link, then it will not JS click on any link and return with false.
	 * 
	 * @param linkText
	 *            The link text of the UI should exactly match - case sensitive.
	 * @param ofLinkTextOccurrence
	 *            The link text occurrence. Even if more links are identified with
	 *            given locator, and wants to JS click on second link of matching link
	 *            text, then input 2 would be appropriate.
	 * @return If found with given occurrence and JS clicked, then returns
	 *         true. Else returns false.
	 */
	public boolean jsClick(String linkText, int ofLinkTextOccurrence) {

		link = getLink(linkText, ofLinkTextOccurrence);
		if (link == null)
			return false;
		else {
			WdOperators.jsClick(link);
			return true;
		}
	}
	
}
