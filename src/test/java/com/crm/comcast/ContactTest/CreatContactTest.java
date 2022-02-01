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
import com.crm.comcast.pomrepositylib.HomePage;
import com.crm.comcast.pomrepositylib.LoginPage;

public class CreatContactTest {
	public static void main(String[] args) throws Throwable {
		

		WebDriver driver = null;
		/*create object*/
		JavaUtility jLib = new JavaUtility();
		ExcelUtility eLib = new ExcelUtility();
		FileUtility fLib = new FileUtility();
		WebdriverUtility wLib = new WebdriverUtility();
		
		/*read data*/
		String BROWSER = fLib.readDatafromPropertyfile("browser");
		String URL = fLib.readDatafromPropertyfile("url");
		String USERNAME = fLib.readDatafromPropertyfile("username");
		
		
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
		/*Login in to App*/
		driver.get(URL);
		LoginPage login = new LoginPage(driver);
		login.loginToApp(USERNAME, PASSWORD);
		
		/*Navigate to Contact*/
		HomePage home = new HomePage(driver);
		home.getContact().click();
		
		//Thread.sleep(5000);
		/*click on createcontact link*/
		
		ContactPage cp = new ContactPage(driver);
		wLib.waitForElementToBeClickable(driver, cp.getCreateContact());
		cp.getCreateContact().click();
		
		/*read data from excel*/
		String lName = eLib.getDataFromExcel("Contact", 1, 3)+jLib.getRandomNum();
		
		/*create the contact*/
		CreateContactPage createCon = new CreateContactPage(driver);
		createCon.crtContact(lName);
		
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
