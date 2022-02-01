package com.crm.comcast.orgTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibrary.ExcelUtility;
import com.crm.GenericLibrary.FileUtility;
import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.WebdriverUtility;

public class CreateOrgWithIndTyTest {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		JavaUtility jLib = new JavaUtility();
		ExcelUtility eLib = new ExcelUtility();
		FileUtility fLib = new FileUtility();
		WebdriverUtility wLib = new WebdriverUtility();
		
		WebDriver driver = null;
		String BROWSER = fLib.readDatafromPropertyfile("browser");
		String URL = fLib.readDatafromPropertyfile("url");
		String USERNAME = fLib.readDatafromPropertyfile("username");
		String PASSWORD = fLib.readDatafromPropertyfile("password");
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser");
		}
		wLib.maximizeWindow(driver);
		wLib.waitForpageLoad(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		String OrgName = eLib.getDataFromExcel("Org", 4, 2)+jLib.getRandomNum();
		String Indus = eLib.getDataFromExcel("Org", 4, 3);
		String Cust = eLib.getDataFromExcel("Org", 4, 4);
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(OrgName);
		WebElement ele = driver.findElement(By.xpath("//select[@name='industry']"));
		wLib.select(ele, Indus);
		WebElement ele2 = driver.findElement(By.xpath("//select[@name='accounttype']"));
		wLib.select(ele2, Cust);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actData = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(actData.contains(OrgName)) 
		{
			System.out.println("TestCase Pass.......");
		}
		else
		{
			System.out.println("TestCase Fail......");
		}
		WebElement ele3 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.mousehover(driver, ele3);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		driver.quit();

	}

}
