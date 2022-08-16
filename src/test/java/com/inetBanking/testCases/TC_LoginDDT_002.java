package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;


public class TC_LoginDDT_002 extends BaseClass
{

	@Test(dataProvider="LoginData")
	
	
	public void loginDDT(String user,String pwd) throws Exception
	{
	
	
	
	LoginPage lp = new LoginPage(driver);
	lp.setUsername(user);
	Logger.info("username provided");
	lp.setPassword(pwd);
	Logger.info("pwd provided");

	lp.clickSubmit();
	Logger.info("login done");
Thread.sleep(3000);
	
	if(isAlertPresent()==true) {
		driver.switchTo().alert().accept(); //close the alert
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		Logger.warn("Login failled");
	}
	else
	{
		Assert.assertTrue(true);
		Logger.info("login passed");
		Thread.sleep(3000);

          lp.clickLogout();
       driver.switchTo().alert().accept();//close logout alert
       driver.switchTo().defaultContent();
	}
	
}
	
	public boolean isAlertPresent() //user defined method created to check alert is present or not...can put it in base class
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	
	
@DataProvider(name="LoginData")
String [][] getData() throws IOException
{
	String path=System.getProperty("user.dir")+"src//test//java//com//inetBanking//testData//LoginData.xlsx";

	int rownum=XLUtils.getRowCount(path, "Sheet1");
	int colcount=XLUtils.getCellCount(path, "Sheet",1);
	 
	String logindata[][]=new String[rownum][colcount];
	
	for(int i=1;i<=rownum;i++)
	{
		for(int j=0;j<colcount;j++)
		{
			logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i,j); // 1 0
		}

	}
return logindata;
}
}