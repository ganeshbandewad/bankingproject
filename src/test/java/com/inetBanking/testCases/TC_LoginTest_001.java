package com.inetBanking.testCases;

import java.io.IOException;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{

	
	@Test
	public void loginTest() throws IOException
	{
		
		//driver.get(baseurl);
		Logger.info("url opened");
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);	
		Logger.info("entered un");
		System.out.println("username entered");

		lp.setPassword(password);
		Logger.info("emtered pw");
		System.out.println("pass entered");

    	lp.clickSubmit();
		Logger.info("click submit");
		System.out.println(" login btn click");

    	
    	if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
    			{
    		Assert.assertTrue(true);
    		Logger.info("login test passed");

    			}
    	else
    	{

    		captureScreen(driver,"loginTest");
    		Assert.assertTrue(false);
    		Logger.info("login test failled");

    	}		
    			
	}
	
	

}
