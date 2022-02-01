package com.crm.comcast.ContactTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibrary.ExcelUtility;
import com.crm.GenericLibrary.FileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.WebdriverUtility;
import com.crm.comcast.pomrepositylib.ContactInfoPage;
import com.crm.comcast.pomrepositylib.ContactPage;
import com.crm.comcast.pomrepositylib.CreateContactPage;
import com.crm.comcast.pomrepositylib.CreateOrganizationPage;
import com.crm.comcast.pomrepositylib.HomePage;
import com.crm.comcast.pomrepositylib.LoginPage;
import com.crm.comcast.pomrepositylib.OrganizationInfoPage;
import com.crm.comcast.pomrepositylib.OrganizationPage;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
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
		String orgName1 = eLib.getDataFromExcel("Contact", 1, 2)+jLib.getRandomNum();
		
		/*create Orgnaization*/
		CreateOrganizationPage createOrg = new CreateOrganizationPage(driver);
		createOrg.createOrg(orgName1);
		
		/*Verify*/
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		wLib.waitForElementVisibility(driver, orgInfo.getSuccessMsg());
		String actMsg = orgInfo.getSuccessMsg().getText();
		if(actMsg.contains(orgName1))
		{
			System.out.println("Organization is created and Test Case ===> Pass");
			
		}
		else
		{
			System.out.println("Organization is not created and Test Case ==> Fail");
		}
		/*Navigate to Contact*/
		home.getContact().click();
		
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContact().click();
		
		/*read data from excel*/
		String lName = eLib.getDataFromExcel("Contact", 1, 3)+jLib.getRandomNum();
		
		/*create the contact*/
		CreateContactPage createCon = new CreateContactPage(driver);
		createCon.crtContact(lName, driver,orgName1);
		
		/*Verify*/
		ContactInfoPage conInfo = new ContactInfoPage(driver);
		String actMsg1 = conInfo.getSuccessMsg().getText();
		if(actMsg1.contains(lName))
		{
			System.out.println("contact is created and Test Case ===> Pass");
		}
		else
		{
			System.out.println("contact is not created and Test Case ===> Fail");
		}
		
		home.Logout();
		

	}

}
