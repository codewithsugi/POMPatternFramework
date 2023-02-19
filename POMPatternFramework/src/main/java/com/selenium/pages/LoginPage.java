/**
 * Login page
 */
package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Sugithanandan Ramalingam
 *
 */
public class LoginPage extends BasePage{
	
	/**
	 * we are not using page factory init to init the elements because we cannot parameterize the elements
	 * so using By is the best solution and more over we have many api support with locator as argument
	 */
	
	
	By username = By.id("user-name");
	
	By password = By.id("password");
	
	By loginButton = By.id("login-button");
	
	By loginErroButton = By.cssSelector(".error-button");
	
	
	

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public boolean isUserNameFieldPresent()
	{
		return findElement(username).isDisplayed();
	}
	
	public boolean isPasswordFieldPresent()
	{
		return findElement(password).isDisplayed();
	}
	
	public boolean isLoginButtonPresent()
	{
		return findElement(loginButton).isDisplayed();
	}
	
	public LoginPage enterUserName(String userName)
	{
		enterText(username, userName);
		return this;
	}
	
	public LoginPage enterPassword(String passWord)
	{
		enterText(password, passWord);
		return this;
	}
	
	public HomePage clickLogin()
	{
		click(loginButton);
		return new HomePage(driver);
	}
	
	public boolean isErrorDisplayed()
	{
		click(loginButton);
		return findElement(loginErroButton).isDisplayed();
		
	}
}
