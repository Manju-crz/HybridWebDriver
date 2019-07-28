package com.mks.exceptions;

import org.openqa.selenium.By;

public class ElementsCountMisMatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementsCountMisMatchException(By firstLocator, String firstLocatorName, int firstLocatorCount,
			By secondLocator, String secondLocatorName, int secondLocatorCount) {
		super(String.format(
				"Size of the elements found with %s and the size of the elements found with %s should be same.\nCurrently found %s - %s elements size is %s\nCurrently found %s - %s elements size is %s",
				firstLocatorName, secondLocatorName, firstLocatorName, firstLocator.toString(), firstLocatorCount,
				secondLocatorName, secondLocator.toString(), secondLocatorCount));
	}

	public ElementsCountMisMatchException(String message) {
		super(message);
	}

	public ElementsCountMisMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public ElementsCountMisMatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ElementsCountMisMatchException(Throwable cause) {
		super(cause);
	}
}
