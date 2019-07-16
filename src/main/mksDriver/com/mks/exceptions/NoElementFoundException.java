package com.mks.exceptions;

import org.openqa.selenium.By;

public class NoElementFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoElementFoundException(By locator, String locatorName, String locatorText) {
		super(String.format("There is no element found with the %s - %s having a associated text %s",
				locatorName, locator.toString(), locatorText));
	}
	
	public NoElementFoundException(By locator, String locatorName, String locatorText, int textOccurrence) {
		super(String.format("There is no element found with the %s - %s having a associated text %s, with the occcurrence of text at position %s",
				locatorName, locator.toString(), locatorText, textOccurrence));
	}
	
	
	public NoElementFoundException(String message) {
		super(message);
	}
	
	
	public NoElementFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoElementFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoElementFoundException(Throwable cause) {
		super(cause);
	}
}
