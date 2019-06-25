package com.mks.connectors;

/**
 * Constants are referred from here to all selenium utility.
 * 
 * @author Manjunath KS
 *
 */
public class Constants {

	private static int elementsFinderTimeout;
	private static int pageloadTimeout;

	public static int getElementsFinderTimeout() {
		return elementsFinderTimeout;
	}

	public static void setElementsFinderTimeout(int elementsFinderTimeout) {
		Constants.elementsFinderTimeout = elementsFinderTimeout;
	}

	public static int getPageloadTimeout() {
		return pageloadTimeout;
	}

	public static void setPageloadTimeout(int pageloadTimeout) {
		Constants.pageloadTimeout = pageloadTimeout;
	}

}
