package com.mks.seluty;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.mks.connectors.Connection;
import com.mks.connectors.Constants;
import com.mks.utilizer.SoftSleeper;

public class WdOperators {
	
	private static final Logger logger = LogManager.getLogger(Camera.class);
	
    public enum TypingSpeed {
        FAST,
        SLOW
    }
	
	public static void click(WebElement element) {
        logger.trace(String.format("Clicking on '%s' with class '%s'", element.getTagName(), element.getAttribute("class")));
        //Camera.takeScreenshot(String.format("Before clicking on %s", element.getTagName()));
        element.click();
    }
	
	public static void jsClick(WebElement element) {
        JSExecutor.executeScript("arguments[0].click();", element);
    }
	
	
    public static void doubleClick(WebElement element) {
        logger.trace(String.format("Double clicking on '%s' with class '%s'", element.getTagName(), element.getAttribute("class")));
        //Camera.takeScreenshot(String.format("Before double clicking on %s", element.getTagName()));
        Actions action = new Actions(Connection.getDriver());
        action.doubleClick(element).build().perform();
    }
	
    
    public static void holdAndClick(Keys key, WebElement... elements) {
        logger.trace(String.format("Holding down %s key while clicking elements", key));
        Actions action = new Actions(Connection.getDriver());
        action = action.keyDown(key);
        for (WebElement element : elements) {
            action = action.click(element);
        }
        //Camera.takeScreenshot("Before holding and clicking");
        action.keyUp(key).build().perform();
    }

   

    public static void clickAndHold(Keys key, WebElement... elements) {
        logger.trace(String.format("Holding down %s key after clicking elements", key));
        Actions action = new Actions(Connection.getDriver());
        for (WebElement element : elements) {
            action.click(element).keyDown(key).build().perform();
        }
        //Camera.takeScreenshot("clicking and holding");
    }
    
    public static void rightClick(WebElement element) {
        logger.trace(String.format("Context clicking on '%s' with class '%s'", element.getTagName(), element.getAttribute("class")));
        Actions action = new Actions(Connection.getDriver());
        action.contextClick(element).build().perform();
    }


    public static void rightClickToNewTab(WebElement element) {
    	Actions action = new Actions(Connection.getDriver());
        action.contextClick(element).build().perform();
        SoftSleeper.seconds(1);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
    }
    
    
    public static void clear(WebElement element) {
        logger.trace(String.format("Clearing element '%s' with class '%s'", element.getTagName(), element.getAttribute("class")));
        element.clear();
    }
   

    /*public static void setText(WebElement element, String characters) {
        type(element, characters, TypingSpeed.FAST);
    }*/
    
    
    public static void type(WebElement element, String characters, TypingSpeed speed) {
        logger.debug(String.format("Typing '%s'", characters));
        if (speed == TypingSpeed.SLOW) {
            logger.trace("Typing Slowly");
            for (char character : characters.toCharArray()) {
                SoftSleeper.milliseconds(Constants.DELAY_KEY_PRESS_MS, "Key Press");
                StringBuilder singleCharBuilder = new StringBuilder();
                singleCharBuilder.append(character);
                element.sendKeys(singleCharBuilder);
            }
        } else {
            logger.trace("Typing Fast");
            sendKeysWithOpenParentheses(element, characters);
        }
    }
    
    
    public static String getText(WebElement element) {
        logger.trace(String.format("Getting text from '%s' with class '%s'", element.getTagName(), element.getAttribute("class")));
        String text = element.getText();
        logger.trace(String.format("Retrieved text: '%s'", text));
        return text;
    }

    public static String getValue(WebElement element) {
        return getAttribute(element, "value");
    }

    public static String getSource(WebElement element) {
        return getAttribute(element, "src");
    }

    public static String getAlt(WebElement element) {
        return getAttribute(element, "alt");
    }

    public static String getXlink(WebElement element) {
        return getAttribute(element, "xlink:href");
    }

    public static String getTitle(WebElement element) {
        return getAttribute(element, "title");
    }

    public static String getId(WebElement element) {
        return getAttribute(element, "id");
    }

    public static String getDataId(WebElement element) {
        return getAttribute(element, "data-id");
    }

    public static String getClass(WebElement element) {
        return getAttribute(element, "class");
    }

    public static String getDataValue(WebElement element) {
        return getAttribute(element, "data-value");
    }
    
    /**
     * Moves mouse to element
     *
     * @param element
     */
    public static void moveMouseTo(WebElement element) {
        new Actions(Connection.getDriver()).moveToElement(element).build().perform();
    }

    /**
     * Moves mouse to an offset from center of element
     *
     * @param element
     * @offsetx x offset from midpoint of element
     * @offsety y offset from midpoint of element
     */
    public static void moveMouseTo(WebElement element, int offsetx, int offsety) {
        new Actions(Connection.getDriver()).moveToElement(element, offsetx, offsety).build().perform();
    }
   

    /**
     * Clicks on location which is offset from the center of an element
     *
     * @param element
     * @offsetx x offset from midpoint of element
     * @offsety y offset from midpoint of element
     */
    public static void clickOnLocation(WebElement element, int offsetx, int offsety) {
        new Actions(Connection.getDriver()).moveToElement(element, offsetx, offsety).click().build().perform();
    }

    /**
     * Scroll element into view if not displayed
     *
     * @param element
     */
    public static void scrollTo(WebElement element) {
        scrollTo(element, false);
    }

    /**
     * Scroll element into view if not displayed.
     *
     * @param element
     * @param force
     *            - true, force element to scroll into view
     */
    public static void scrollTo(WebElement element, boolean force) {
        if (force || !element.isDisplayed()) {
            //Camera.takeScreenshot("Before Scroll");
            JSExecutor.executeScript("arguments[0].scrollIntoView(false);", element);
            SoftSleeper.milliseconds(Constants.DELAY_SCROLL_MS, "Element Scroll");
        }
    }

    /**
     * This will click element elementToClick and drag the mouse to
     * elementToDragOn, then it will release the click.
     *
     * @param elementToClick
     * @param elementToDragOn
     */
    public static void clickAndDragTo(WebElement elementToClick, WebElement elementToDragTo) {
        logger.trace(String.format("Clicking and dragging element '%s' to location of '%s'", elementToClick.getText(), elementToDragTo.getText()));
        Actions action = new Actions(Connection.getDriver());
        action.dragAndDrop(elementToClick, elementToDragTo).build().perform();
    }
    
    public static boolean isSelected(WebElement element) {
        logger.trace(String.format("Checking if '%s' with class '%s' is selected", element.getTagName(), element.getAttribute("class")));
        return element.isSelected();
    }
   
    public static boolean isDisplayed(WebElement element) {
        logger.trace(String.format("Checking if '%s' with class '%s' is displayed", element.getTagName(), element.getAttribute("class")));
        return element.isDisplayed();
    }
   
    public static boolean isEnabled(WebElement element) {
        logger.trace(String.format("Checking if '%s' with class '%s' is enabled", element.getTagName(), element.getAttribute("class")));
        return element.isEnabled();
    }
    
    /**
     * To be used for debugging purposes only
     *
     * @param element
     * @return
     */
    public static void traceElement(WebElement element) {
        logger.trace(String.format("*** '%s' with style '%s' with class '%s' with size %s", element.getTagName(), element.getAttribute("style"), element.getAttribute("class"), element.getSize()));
    }

    /**
     * Workaround for Selenium Bug
     * https://code.google.com/p/selenium/issues/detail?id=6822 Unable to send
     * Open Parentheses
     */
    private static void sendKeysWithOpenParentheses(WebElement element, String characters) {
        if (characters == null) {
            return;
        }
        String openParenthesese = Keys.chord(Keys.SHIFT, "9");
        String testValue = characters.replaceAll("\\(", openParenthesese);
        element.sendKeys(testValue);
    }
    
    /**
     * Used to remove redundant code from get methods.
     *
     * @param element
     * @param attribute
     * @return
     */
    public static String getAttribute(WebElement element, String attribute) {
        logger.trace(String.format("Getting %s from '%s' with class '%s'", attribute, element.getTagName(), element.getAttribute("class")));
        String attrVal = element.getAttribute(attribute);
        logger.trace(String.format("Retrieved %s: '%s'", attribute, attrVal));
        return attrVal;
    }

    public static void sendReturn(WebElement webElement) {
        logger.trace(String.format("Sending Return on '%s' with class '%s'", webElement.getTagName(), webElement.getAttribute("class")));
        webElement.sendKeys(Keys.RETURN);
        logger.trace("Return Sent");
    }

    /**
     * It helps to get selected options from the select box ie The element should be with 'Select' tag name only.
     *
     * @param selectBox
     *            Found element type of select tag
     * @return All already selected elements.
     */
    public static List<String> getSelectBoxSelectedOptions(WebElement selectBox) {
        Select select = new Select(selectBox);
        List<WebElement> elements = select.getAllSelectedOptions();
        List<String> selectedOptions = new ArrayList<>();
        for (WebElement element : elements) {
            selectedOptions.add(element.getText().trim());
        }
        return selectedOptions;
    }

    /**
     * Helps to get the text of all given elements
     *
     * @param elements
     *            List of found elements
     * @return
     */
    public static List<String> getTextOfElements(List<WebElement> elements) {
        List<String> subTxt = new ArrayList<>();
        for (WebElement textElement : elements) {
            subTxt.add(textElement.getText().trim());
        }
        return subTxt;
    }
    
    
    
    
}
