package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.inetbanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	ReadConfig readconfig=new ReadConfig();
	
	//public String baseURL="http://demo.guru99.com/v4/index.php";
	public String baseURL=readconfig.getApplicationURL();
	//public String username="mngr327166";
	public String username=readconfig.getUsername();
	//public String password="EtYhEhy";
	public String password=readconfig.getPassword();
	public WebDriver driver=null;

	public static Logger logger;
	
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws InterruptedException
	{			
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("./log4j.properties");
		//PropertyConfigurator.configure(System.getProperty("user.dir")+"//log4j.properties");
		
		if(br.equalsIgnoreCase("firefox"))
		{
		//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();		
		}
		else if(br.equalsIgnoreCase("chrome"))
		{
			
		}
		else if(br.equalsIgnoreCase("ie"))
		{
			
		}
		else if(br.equalsIgnoreCase("edge"))
		{
				System.setProperty("webdriver.edge.driver", "./Drivers//msedgedriver.exe");
				driver=new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);			
		driver.manage().window().maximize();		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/"  + tname + ".png" );
		//File target = new File(./Screenshots + "//"+tname + ".png")
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public static String randomstring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
	}
	
	public static String randomNum()
	{
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return generatedString2;
	}


}
