package com.mks.exceptions;

import org.openqa.selenium.By;

public class AssociatedElementsCountMisMatchException extends RuntimeException {

	public AssociatedElementsCountMisMatchException(By firstLocator, String firstLocatorName, int firstLocatorCount,
			By secondLocator, String secondLocatorName, int secondLocatorCount) {
		super(String.format(
				"Size of the elements found with %s and the size of the elements found with %s should be same.\nCurrently found %s - %s elements size is %s\nCurrently found %s - %s elements size is ",
				firstLocatorName, secondLocatorName, firstLocatorName, firstLocator.toString(), firstLocatorCount,
				secondLocatorName, secondLocator.toString(), secondLocatorCount));
	}

	public AssociatedElementsCountMisMatchException(String message) {
		super(message);
	}

	public AssociatedElementsCountMisMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssociatedElementsCountMisMatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AssociatedElementsCountMisMatchException(Throwable cause) {
		super(cause);
	}
}
