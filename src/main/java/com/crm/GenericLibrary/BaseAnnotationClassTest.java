package com.crm.GenericLibrary;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.comcast.pomrepositylib.HomePage;
import com.crm.comcast.pomrepositylib.LoginPage;

public class BaseAnnotationClassTest {
	
	public WebDriver driver;
	/*object creation*/
	public JavaUtility jLib = new JavaUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public FileUtility fLib = new FileUtility();
	public WebdriverUtility wLib = new WebdriverUtility();
	public static WebDriver sDriver = null;
	
	
	@BeforeSuite(groups = {"smoketest","regressiontest"})
	public void configBS()
	{
		System.out.println("===Connect to Database===");
	}
	//@Parameters("BROWSER")
	@BeforeClass(groups = {"smoketest","regressiontest"})
	public void configBC(/*String BROWSER*/) throws Throwable
	{
		System.out.println("===Launch Browser===");
		String BROWSER = fLib.readDatafromPropertyfile("browser");
		String URL = fLib.readDatafromPropertyfile("url");
		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else
		{
			System.out.println("invalid browser");
		}
		wLib.maximizeWindow(driver);
		wLib.waitForpageLoad(driver);
		driver.get(URL);
		sDriver = driver;
		
	}

	@BeforeMethod(groups = {"smoketest","regressiontest"})
	public void configBM() throws Throwable
	{
		System.out.println("===Login to App===");
		String USERNAME = fLib.readDatafromPropertyfile("username");
		String PASSWORD = fLib.readDatafromPropertyfile("password");
		LoginPage login = new LoginPage(driver);
		login.loginToApp(USERNAME, PASSWORD);
		
	}
	
	@AfterMethod(groups = {"smoketest","regressiontest"})
	public void configAM()
	{
		System.out.println("===Logout from App===");
		HomePage home = new HomePage(driver);
		home.Logout();
	}
	
	@AfterClass(groups = {"smoketest","regressiontest"})
	public void configAC()
	{
		System.out.println("===Close the Browser===");
		wLib.CloseBrowser(driver);
	}
	
	@AfterSuite(groups = {"smoketest","regressiontest"})
	public void configAS()
	{
		System.out.println("===Disconnect from Database===");
	}
	
}
