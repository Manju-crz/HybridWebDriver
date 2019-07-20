package com.mks.definitions;

public interface LegacyElements {
	
	public static enum ClickTypes{
		SimpleClick, JsClick, MouseLeftClick, MouseRightClick, DoubleClick;
	}
	
	public boolean isDisplayed();
	
	public boolean isEnabled();
	
	public boolean isSelected();
	
	
}
