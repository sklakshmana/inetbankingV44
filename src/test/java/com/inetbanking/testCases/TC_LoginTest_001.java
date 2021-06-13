package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException 
	{
		//test = extent.createTest("Test");
			
		LoginPage lp=new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("Entered username");		
		
		lp.setPassword(password);
		logger.info("Entered password");
		
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login test passes");
			//test.pass("Guru99 Bank Manager HomePage");
		}
		else
		{
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
			
			//test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("failed.png").build());
		}
		
		/*
		 test = extent.createTest("Test");
		
		//driver.get("https://www.google.com");
		
		test.pass("Navigated to google.com");
		
		
		// log(Status, details)
        test.log(Status.INFO, "This step shows usage of log(status, details)");

        // info(details)
        test.info("This step shows usage of info(details)");
        
        // log with snapshot
        test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("failed.png").build());
		 
		 
		 */
		
	}

}
