package com.mks.connectors;

/**
 * Constants are referred from here to all selenium utility.
 * 
 * @author Manjunath KS
 *
 */
public class Constants {
	
	public static final long DELAY_KEY_PRESS_MS = 250;
	public static final long DELAY_SCROLL_MS = 250;
	
	
	
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
