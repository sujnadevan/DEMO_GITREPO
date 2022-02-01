package com.crm.comcast.OrganizationTest;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibrary.ExcelUtility;
import com.crm.GenericLibrary.FileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.WebdriverUtility;
import com.crm.comcast.pomrepositylib.CreateOrganizationPage;
import com.crm.comcast.pomrepositylib.HomePage;
import com.crm.comcast.pomrepositylib.LoginPage;
import com.crm.comcast.pomrepositylib.OrganizationInfoPage;
import com.crm.comcast.pomrepositylib.OrganizationPage;

public class CreateOrgTest {
public static void main(String[] args) throws Throwable {
	
	WebDriver driver = null;
	
	/*create object*/
	JavaUtility jLib = new JavaUtility();
	ExcelUtility eLib = new ExcelUtility();
	FileUtility fLib = new FileUtility();
	WebdriverUtility wLib = new WebdriverUtility();
	
	/*read common data*/
	String BROWSER = fLib.readDatafromPropertyfile("browser");
	String URL = fLib.readDatafromPropertyfile("url");
	String USERNAME = fLib.readDatafromPropertyfile("username");
	
	/*step1 Launch Browser*/
	String PASSWORD = fLib.readDatafromPropertyfile("password");
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
	
	/*Login to App*/
	driver.get(URL);
	LoginPage login = new LoginPage(driver);
	login.loginToApp(USERNAME, PASSWORD);
	
	
	HomePage home = new HomePage(driver);
	home.getOrganization().click();
	
	/*navigate to Org*/
	OrganizationPage org = new OrganizationPage(driver);
	org.getCreateOrg().click();
	
	/*read data from Excel*/
	String orgName = eLib.getDataFromExcel("Org", 1, 2)+jLib.getRandomNum();
	
	/*create Orgnaization*/
	CreateOrganizationPage createOrg = new CreateOrganizationPage(driver);
	createOrg.createOrg(orgName);
	
	/*Verify*/
	OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
	String actMsg = orgInfo.getSuccessMsg().getText();
	if(actMsg.contains(orgName))
	{
		System.out.println("Test Case is Pass");
	}
	else
	{
		System.out.println("Test Case is Fail");
	}
	
	home.Logout();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}
