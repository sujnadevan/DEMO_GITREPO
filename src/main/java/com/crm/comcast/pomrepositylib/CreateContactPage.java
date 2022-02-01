package com.crm.comcast.pomrepositylib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibrary.WebdriverUtility;

public class CreateContactPage extends WebdriverUtility{

	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(name="lastname")
	private WebElement lastName;
	public WebElement getLastName() 
	{
		return lastName;
	}
	
	@FindBy(xpath="//Input[@name='account_name']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement orgClick;
	
	public WebElement getOrgClick() {
		return orgClick;
	}

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	/**
	 * This method is used to create contact
	 * @param lName
	 */
	public void crtContact(String lName)
	{
		lastName.sendKeys(lName);
		saveBtn.click();
	}
	/**
	 * This method is used to create organisation with contact
	 * @param lName
	 * @param orgName
	 */
	
	public void crtContact(String lName,WebDriver driver,String orgName)
	{
		lastName.sendKeys(lName);	
		orgClick.click();
		
		switchToWindow(driver, "Accounts&action");
		
		OrganizationPage org = new OrganizationPage(driver);
		org.getSearchEdt().click();
		org.getSearchEdt().sendKeys(orgName);
		org.getSearchBtn().click();
		
		driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
		switchToWindow(driver, "Contacts&action");
		
		saveBtn.click();
		
	}
	
	
	
}
