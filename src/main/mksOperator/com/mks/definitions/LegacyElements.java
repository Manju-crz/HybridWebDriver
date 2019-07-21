package com.mks.definitions;

public interface LegacyElements {

	public static enum ClickTypes {
		SimpleClick, JsClick, MouseLeftClick, MouseRightClick, DoubleClick;
	}

	/**
	 * Helps to identify the given element is displayed in the UI or no.
	 * 
	 * @return If displayed, then returns true; else returns false.
	 */
	public boolean isDisplayed();

	/**
	 * Helps to identify the given element is enabled in the UI or no.
	 * 
	 * @return If enabled, then returns true; else returns false.
	 */
	public boolean isEnabled();

	/**
	 * Helps to identify the given element is selected in the UI or no.
	 * 
	 * @return If selected, then returns true; else returns false.
	 */
	public boolean isSelected();

}
