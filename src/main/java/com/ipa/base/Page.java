package com.ipa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ipa.utilities.ExcelReader;
import com.ipa.utilities.Utilities;


public class Page {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
	public static ExtentTest test;
	public static Logger log = LogManager.getLogger();
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ipa\\excel\\testdata.xlsx");
	public static Date d = new Date();
	public static WebDriverWait wait;
	public static TopMenu menu;
	
	
	public Page() {
		

		
		extent.attachReporter(spark);
		
		
		if(driver==null) {
			
			
			System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_"));
			
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			File file = new File(".\\src\\test\\resources\\com\\ipa\\properties\\log4j2_1.properties");
			context.setConfigLocation(file.toURI());
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ipa\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				config.load(fis);
				log.debug("Config file is loaded.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ipa\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				OR.load(fis);
				log.info("OR file is loaded.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("chrome")) {
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ipa\\executables\\chromedriver.exe");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				driver = new ChromeDriver(options);
				
			} else if(config.getProperty("browser").equals("firefox")) {
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ipa\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				
			} else if(config.getProperty("browser").equals("opera")) {
				
				System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\com\\ipa\\executables\\operadriver.exe");
				driver = new OperaDriver();
				
			}
			
			log.info(config.getProperty("browser").toUpperCase() + " browser is launched.");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			driver.get(config.getProperty("testsiteurl"));
			log.info(config.getProperty("testsiteurl")  + " URL is opened.");
			wait = new WebDriverWait(driver, 10);
			menu = new TopMenu(driver);
		}
	}
	
	
	
	public static void click(String locator) {
		
		if(locator.endsWith("_CSS")) {
			
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			
			
		} else if(locator.endsWith("_XPATH")) {
			
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			
		} else if(locator.endsWith("_ID")) {
			
			driver.findElement(By.id(OR.getProperty(locator))).click();
			
		}
		
		test.log(Status.INFO, "Clicing on " + locator + " button.");
		log.info("Clicing on " + locator + " button.");
		
	}
	
	
	
	public static void quit() {
		
		//log.info("End of test log.");
		//log.info("**********************************************");
		//extent.flush();
		driver.quit();
		
		
	}
	
	
	
	
	public static void type(String locator, String value) {
		
		if(locator.endsWith("_CSS")) {
			
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			
		} else if(locator.endsWith("_XPATH")) {
			
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			
		} else if(locator.endsWith("_ID")) {
			
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			
		}
		
		test.log(Status.INFO, "Typing value: " + value + " into " + locator + " field.");
		log.info("Typing value: " + value + " into " + locator + " field.");
		
	}
	
	
	public static void verifyEquals(String actual, String expected) {
		
		try {
		
			Assert.assertEquals(actual, expected);
			test.log(Status.PASS, "Verification is successful!");
			log.info("Verification is successful!");
		
		} catch(Throwable t) {
			
			try {
				Utilities.captureScreenshot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			test.log(Status.FAIL, "Verfication is unsuccessful!");
			//test.fail(MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotName).build());
			test.addScreenCaptureFromPath(Utilities.screenshotName);
			test.log(Status.FAIL, t.getMessage());
			log.debug("Verfication is unsuccessful!");
			log.debug(t.getMessage());
		}
		
		
	}
	
	
}
