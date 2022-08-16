package com.inetBanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.markuputils.MarkupHelper;
public class Reporting extends TestListenerAdapter 
{

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.ss").format(new Date()); //time stamp
		String repName = "Test-Report-"+timeStamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/Reports/"+repName);//specify location
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");//specify location

		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment0", "QA");
		extent.setSystemInfo("User", "Ganu");
		
		htmlReporter.config().setDocumentTitle("InetBanking Test Project");//Title of Report
		htmlReporter.config().setReportName("Functional Test Report");//name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of he Chart
		htmlReporter.config().setTheme(Theme.DARK);
	}
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//creat new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));//send the passed information...
	}
		
	public void onTestFailure(ITestResult tr)
	{	
		logger=extent.createTest(tr.getName());//creat new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));//send the passed information
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath);
		
		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			}
		catch(IOException e)
			{
			e.printStackTrace();
			}
	}	
	}
	public void onTestSkipped(ITestResult tr)
	{
		
		logger=extent.createTest(tr.getName());//creat new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));//send the passed information
	System.out.println("test skipped msg from reporting class");
	}
	
	public void onFinish(ITestContext testcontest)
	{
		extent.flush();
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
