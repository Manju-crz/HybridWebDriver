package com.mks.knob;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.NotFoundException;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.mks.connectors.Connection;

public class ArmWindows {

	/**
	 * Helps to find the total active windows count.
	 * 
	 * @return
	 */
	public static int getActiveWindowsSize() {
		Set<String> winid = Connection.getDriver().getWindowHandles();
		return winid.size();
	}

	/**
	 * Helps to open the new tab/window and switches control to the new tab
	 * 
	 * @return Returns the window identification of last controlled one.
	 * @throws NotFoundException
	 */
	public static String openNewTab() {
		int currentWindowsSize = getActiveWindowsSize();
		WebDriver driver = Connection.getDriver();
		ArrayList<String> preTabs = new ArrayList<String>(driver.getWindowHandles());
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		if ((currentWindowsSize + 1) != tabs.size())
			throw new NotFoundException(String.format(
					"Already existing windows count was %s, and after the new tab action performed, did not get the one incremented count of windows. Where the found post action windows size is %s",
					currentWindowsSize, tabs.size()));
		ArrayList<String> postTabs = new ArrayList<String>(driver.getWindowHandles());
		postTabs.removeAll(preTabs);
		ArmWindows.switchToWindow(postTabs.get(0));
		return tabs.get(0);
	}

	/**
	 * It just switches to the new window which appeared before calling this method.
	 * Note- If there is no new window appeared and still trying to switch then
	 * throws exception
	 * 
	 * @return Returns the window identification of last controlled one.
	 */
	public static String switchToNewWindow() {
		int currentWindowsSize = getActiveWindowsSize();
		Set<String> winid = Connection.getDriver().getWindowHandles();
		if (currentWindowsSize == 1)
			throw new NotFoundException(String.format(
					"Only one window has been found while switching to new window, and the found window title is %s. Atleast 2 active windows required to switch to new window.",
					Connection.getDriver().getTitle()));
		Iterator<String> iter = winid.iterator();
		String parent = iter.next();
		String tab = iter.next();
		Connection.getDriver().switchTo().window(tab);
		return parent;
	}

	/**
	 * It helps to identify all the current active windows and returns their
	 * identifications.
	 * 
	 * @return
	 */
	public static List<String> getAllActiveWindows() {
		Set<String> winid = Connection.getDriver().getWindowHandles();
		Iterator<String> iter = winid.iterator();
		List<String> allWindows = new ArrayList<String>();
		while (iter.hasNext()) {
			allWindows.add(iter.next());
		}
		return allWindows;
	}

	/**
	 * It helps to switch to the required window if existing actively.
	 * 
	 * @param windowIdentification If doesn't find given window identification from
	 *                             active windows list then throws exception.
	 */
	public static String switchToWindow(String windowIdentification) {

		String str = Connection.getDriver().getWindowHandle();
		List<String> allWindows = getAllActiveWindows();
		if (allWindows.contains(windowIdentification))
			Connection.getDriver().switchTo().window(windowIdentification);
		else
			throw new NotFoundException(String.format(
					"Did not find the window with the given identification %s, and the existing windows are",
					windowIdentification, allWindows.toString()));
		return str;
	}

	/**
	 * It helps to switch to the required window if existing actively. Note: If
	 * there are more than one active windows existing with same window title, then
	 * will switch to the first found window with that title.
	 * 
	 * @param windowTitle to which window title needs the switch
	 * @InvalidArgumentException If the provided input window title and the current
	 *                           active window title is same, then will throw an
	 *                           exception.
	 * @NotFoundException If the given window title not at all existing in the
	 *                    active windows titles list, then will throw the exception
	 * @return The current window title is returned which would be active before the
	 *         switch.
	 */
	public static String switchToOtherWindow(String windowTitle) {

		String currentWindowTitle = Connection.getDriver().getTitle();
		if (windowTitle.trim().equals(currentWindowTitle.trim()))
			throw new InvalidArgumentException(String.format(
					"Driver instance current active window title is %s, and the given parameter to switch to other window title is %s. Both are being same, in order to switch to other window it has to be other window's title",
					currentWindowTitle, windowTitle));
		List<String> allwindows = getAllActiveWindows();
		boolean switched = false;
		for (String str : allwindows) {
			switchToWindow(str);
			if (Connection.getDriver().getTitle().trim().equals(windowTitle.trim())) {
				switched = true;
				break;
			}
		}
		if (!switched)
			throw new NotFoundException(String.format("Did not find the other window with the title %s.", windowTitle));
		return currentWindowTitle;
	}

}
