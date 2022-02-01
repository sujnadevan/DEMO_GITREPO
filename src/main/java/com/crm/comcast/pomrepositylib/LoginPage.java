package com.crm.comcast.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage { //Rule1 : Create a saperate java class for every page in application
	
	//Rule3 : To Execute all the element & initialize the elements PageFactory.initElements [initialization] in the constructor
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Rule 2 : Identify all possible element using @findBy,@FindAll & @findbys [declaration]
	//@FindBy(LN = "LV") and declare the webelement as private 
	
	@FindBy(name = "user_name")
	private WebElement username;
	
	@FindBy(name = "user_password")
	private WebElement password;
	
	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath="//input[@type='submit']") })
	private WebElement loginBtn;
	
	//Rule4 : Decalare all the elements as private and provide getter method, buisness method [Utilization]
	
	public WebElement getUsername()
	{
		return username;
	}
	
	public WebElement getPassword() 
	{
		return password;
	}
	
	public WebElement getLoginBtn()
	{
		return loginBtn;
	}
	/**
	 * This method is used for Login to App
	 * @param un
	 * @param pwd
	 */
	public void loginToApp(String un ,String pwd) // Rule 5 iddentify  the reusable business library and implement them in same POM class
	{
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
	}
}

























