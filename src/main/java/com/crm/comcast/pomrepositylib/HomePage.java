package com.crm.comcast.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Organizations")
	private WebElement organization;
	
	@FindBy(linkText = "Contacts")
	private WebElement contact;
	
	@FindBy(linkText = "Products")
	private WebElement product;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement image;
	
	@FindBy(xpath = "//a[.='Sign Out']")
	private WebElement signout;
	

	public WebElement getOrganization() {
		return organization;
	}

	public WebElement getContact() {
		return contact;
	}

	public WebElement getProduct() {
		return product;
	}

	public WebElement getImage() {
		return image;
	}

	public WebElement getSignout() {
		return signout;
	}
	/**
	 * This method is used for Logout App
	 */
	public void Logout()
	{
		Actions act = new Actions(driver);
		act.moveToElement(image).perform();
		signout.click();
		
		
	}
	
	
	
}
