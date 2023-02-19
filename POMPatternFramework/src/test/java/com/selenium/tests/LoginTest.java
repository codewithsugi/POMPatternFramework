package com.selenium.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.base.BaseTest;
import com.selenium.pages.LoginPage;
import com.selenium.tests.utils.TestUtility;
import com.selenium.utils.TestConstants;

public class LoginTest extends BaseTest {
	
	private static Logger log = Logger.getLogger(LoginTest.class);

	LoginPage loginPage;

	@BeforeClass
	public void setup() throws IOException {
		log.info("initiation Begins");
		loginPage = new LoginPage(driver);
		log.info("Driver initiated successfully");
	}

	@Test(priority = 1)
	public void testUserNameField() {
		Assert.assertEquals(loginPage.isUserNameFieldPresent(), true);

	}

	@Test(priority = 2)
	public void testPasswordField() {
		Assert.assertEquals(loginPage.isUserNameFieldPresent(), true);

	}

	@Test(priority = 3)
	public void testLoginButton() {
		Assert.assertEquals(loginPage.isLoginButtonPresent(), true);

	}
	
	@Test(dataProvider = "getDataForLoginCheck", priority = 4)
	public void testWithInvalidUSerNameAndPassword(String username, String password)
	{
		
		Assert.assertEquals(loginPage.enterUserName(username)
				.enterPassword(password).isErrorDisplayed(), true);
		
	}

	@Test(priority = 5)
	public void testLogin() {
		Assert.assertEquals(loginPage.enterUserName(properties.getProperty("username"))
				.enterPassword(properties.getProperty("password")).clickLogin().isHomePageLogoPresent(), true);

	}
	
	@DataProvider
	public Object[][] getDataForLoginCheck()
	{
		return TestUtility.readExcel(TestConstants.LOGIN_TESTDATA_PATH, "Sheet1");
		
	}

	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
}
