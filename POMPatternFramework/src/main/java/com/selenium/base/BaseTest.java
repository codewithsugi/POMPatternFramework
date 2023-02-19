package com.selenium.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.selenium.utils.TestConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	private static Logger log = Logger.getLogger(BaseTest.class);

	public static Properties properties;

	public static WebDriver driver;

	
	public Properties resourceLoader() throws IOException {
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new FileReader("src//main//resources//config.properties"));
			properties = new Properties();
			try {
				properties.load(bufferedReader);
			} catch (Exception e) {
				log.info("Exception occured during property load " + e.getMessage());
			}
		} catch (Exception e) {
			log.info("Exception occured during reading " + e.getMessage());
		} finally {
			bufferedReader.close();
		}
		return properties;
	}
	
	@BeforeSuite
	public WebDriver initDriver() throws IOException {
		
		resourceLoader();
		
		String browserName = properties.getProperty("browserName");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}

		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		// based on the page time we can configure 
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestConstants.PAGE_TIMEOUT_IN_SECONDS));

		return driver;
	}
	
	@AfterSuite
	public void tearDown()
	{
		driver.quit();
	}

}
