package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
	
	
	By homepageLogo = By.cssSelector(".app_logo");
	
	
	

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isHomePageLogoPresent()
	{
		return findElement(homepageLogo).isDisplayed();
	}
	
	

}
