package com.mks.seluty;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class ExplicitConditions {
	  
	  /**
	   * Returns when one element is visible
	   * 
	   * @param locator
	   * @return
	   */
	  public static ExpectedCondition<WebElement> visibilityOfOneElement(By locator) {
	    return new ExpectedCondition<WebElement>() {
	      @Override
	      public WebElement apply(WebDriver webDriver) {
	        List<WebElement> elements = webDriver.findElements(locator);
	        for (WebElement element : elements) {
	          if (element.isDisplayed()) {
	            return element;
	          }
	        }

	        return null;
	      }
	    };
	  }

	  /**
	   * Returns when an element found by locator has another element found by sublocator that is visible
	   * 
	   * @param locator
	   * @param sublocator
	   * @return
	   */
	  public static ExpectedCondition<WebElement> visiblityOfOneNestedElement(By locator, By sublocator) {
	    return new ExpectedCondition<WebElement>() {
	      @Override
	      public WebElement apply(WebDriver webDriver) {
	        List<WebElement> elements = webDriver.findElements(locator);
	        for (WebElement element : elements) {
	          List<WebElement> subElements = element.findElements(sublocator);
	          for (WebElement subElement : subElements) {
	            if (subElement.isDisplayed()) {
	              return subElement;
	            }
	          }
	        }

	        return null;
	      }

	    };

	  }
}
