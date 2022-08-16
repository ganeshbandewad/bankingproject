package com.inetBanking.testCases;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	public String baseurl = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
			
	/*
public String baseurl = "https://demo.guru99.com/v4/";
public String username = "mngr428786";
public String password = "ypYhyhy";
public static WebDriver driver;
	
*/
	
public static Logger Logger;

@Parameters("browser")
@BeforeClass

public void setup(String br)
{
	
	Logger = Logger.getLogger("ebanking");
	//PropertyConfigurator.configure("Log4j.properties");
	DOMConfigurator.configure("log4j.xml");
	System.out.println(" driver");
	
if(br.equals("chrome"))
{
	System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());

	//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
	//System.setProperty("webdriver.chrome.driver", "./Drivers\\chromedriver.exe");

	driver = new ChromeDriver();


}
	else if(br.equals("edge"))
	{
		System.setProperty("webdriver.edge.driver", readconfig.getEdgePath());
	
		driver = new EdgeDriver();

	}
	else if(br.equals("fireFox"))
	{
		System.setProperty("webdriver.gecko.driver", readconfig.getfireFoxPath());
		
		driver = new FirefoxDriver();
	}
driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
driver.get(baseurl);

}

@AfterClass
public void tearDown()
{
driver.quit();
}


public void captureScreen(WebDriver driver, String tname) throws IOException {
TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
File target=new File(System.getProperty("user.dir") + "/Screenshot/" + tname + ".png");
FileUtils.copyFile(source,target);
System.out.println("Screenshot taken");

}
}












