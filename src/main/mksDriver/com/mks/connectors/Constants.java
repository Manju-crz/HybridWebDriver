package com.mks.connectors;

/**
 * Constants are referred from here to all selenium utility.
 * 
 * @author Manjunath KS
 *
 */
public class Constants {

	private static long elementsFinderTimeout;
	private static long pageloadTimeout;

	public static long getElementsFinderTimeout() {
		return elementsFinderTimeout;
	}

	public static void setElementsFinderTimeout(int elementsFinderTimeout) {
		Constants.elementsFinderTimeout = elementsFinderTimeout;
	}

	public static long getPageloadTimeout() {
		return pageloadTimeout;
	}

	public static void setPageloadTimeout(int pageloadTimeout) {
		Constants.pageloadTimeout = pageloadTimeout;
	}

}
