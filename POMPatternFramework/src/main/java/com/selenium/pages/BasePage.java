package com.selenium.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Sugithanandan Ramalingam
 *
 */
public class BasePage
{

	public WebDriver driver;
	WebDriverWait wait;
	Select select = null;
	JavascriptExecutor js;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	
	/**
	 * Find element with default wait 10 seconds
	 * @param locator
	 * @return WebElemnt
	 */
	public WebElement findElement(By locator) {
		this.waitForElement(locator);
		return driver.findElement(locator);
	}

	
	/**
	 * Find elements with default wait 10 seconds
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> findElements(By locator) {
		this.waitForElement(locator);
		return driver.findElements(locator);
	}
	
	
	
	/**
	 * Find element with custom wait in seconds
	 * @param locator
	 * @param waitTime in seconds
	 * @return WebElement
	 */
	public WebElement findElement(By locator, int waitTime) {
		this.waitForElement(locator, waitTime);
		return driver.findElement(locator);
	}

	
	
	/**
	 * Find elements with custom wait in seconds
	 * @param locator
	 * @param WaitTime in seconds
	 * @return List<WebElement>
	 */
	public List<WebElement> findElements(By locator, int WaitTime) {
		this.waitForElement(locator, WaitTime);
		return driver.findElements(locator);
	}

	
	/**
	 * Check presence of element within 10 seconds
	 * @param locator
	 * @return boolean
	 */
	public boolean waitForElement(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	
	/**
	 * Check presence of element with custom wait seconds
	 * @param locator
	 * @param waitTime in seconds
	 * @return boolean
	 */
	public boolean waitForElement(By locator, int waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	/**
	 * Click element with default wait 10 seconds
	 * @param locator
	 */
	public void click(By locator) {
		this.findElement(locator).click();
	}
	
	
	/**
	 * Enter text after clearing the text with default wait 10 seconds
	 * @param locator
	 * @param text to enter
	 */
	public void enterText(By locator, String text) {
		this.findElement(locator).clear();
		this.findElement(locator).sendKeys(text);
	}

	/**
	 * Get the text with default wait 10 seconds
	 * @param locator
	 * @return String
	 */
	public String getText(By locator) {
		return this.findElement(locator).getText();
	}

	
	/**
	 * Get attribute with default wait 10 seconds
	 * @param locator
	 * @param attribute
	 * @return String 
	 */
	public String getAttribute(By locator, String attribute) {
		return this.findElement(locator).getAttribute(attribute);
	}
	
	/**
	 * Wait for element visibility since it will check for height and width greater than zero
	 * @param locator
	 * @param waitTime
	 * @return boolean
	 */
	public boolean waitUntilElementVisible(By locator, int waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}
	
	/**
	 * DropDownSelectByText
	 * @param locator
	 * @param text
	 */
	public void dropDownSelectByText(By locator, String text)
	{
		select = new Select(this.findElement(locator));
		select.selectByVisibleText(text);
	}
	
	/**
	 * dropDownSelectByIndex
	 * @param locator
	 * @param index
	 */
	public void dropDownSelectByIndex(By locator, int index)
	{
		select = new Select(this.findElement(locator));
		select.selectByIndex(index);
	}
	
	/**
	 * dropDownSelectByValue
	 * @param locator
	 * @param valuedropDownSelectByValue
	 */
	public void dropDownSelectByValue(By locator, String value)
	{
		select = new Select(this.findElement(locator));
		select.selectByValue(value);
	}
	

	/**
	 * dropDownDeselectByText
	 * @param locator
	 * @param text
	 */
	public void dropDownDeselectByText(By locator, String text)
	{
		select = new Select(this.findElement(locator));
		select.deselectByVisibleText(text);
	}
	
	
	/**
	 * dropDownDeselectByIndex
	 * @param locator
	 * @param index
	 */
	public void dropDownDeselectByIndex(By locator, int index)
	{
		select = new Select(this.findElement(locator));
		select.deselectByIndex(index);
	}
	
	/**
	 * dropDownDeselectByValue
	 * @param locator
	 * @param value
	 */
	public void dropDownDeselectByValue(By locator, String value)
	{
		select = new Select(this.findElement(locator));
		select.deselectByValue(value);
	}
	
	//
	
	/**
	 * dropDownDeselectAll
	 * @param locator
	 */
	public void dropDownDeselectAll(By locator)
	{
		select = new Select(this.findElement(locator));
		select.deselectAll();
	}
	
	/**
	 * scrollToElement using java script
	 * @param locator
	 */
	public void scrollToElement(By locator)
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", this.findElement(locator));
	}
	

	/**
	 * scrollToBottom
	 */
	public void scrollToBottom()
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	
	/**
	 * ScrollUsingOffset
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public void ScrollUsingOffset(int xCoordinate, int yCoordinate)
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+xCoordinate+","+yCoordinate+")", "");
	}
}
