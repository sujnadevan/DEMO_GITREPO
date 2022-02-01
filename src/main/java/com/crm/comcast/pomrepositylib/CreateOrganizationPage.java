package com.crm.comcast.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.crm.GenericLibrary.WebdriverUtility;

public class CreateOrganizationPage extends WebdriverUtility {
	
	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@name='accountname']")
	private WebElement orgName;
	
	@FindBy(xpath="//select[@name='industry']")
	private WebElement industry;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getIndustry() {
		return industry;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	/**
	 * This method is used to create Organisation using only Organization Name
	 * @param ogName
	 */
	public void createOrg(String ogName)
	{
		orgName.sendKeys(ogName);
		saveBtn.click();
	}
	/**
	 * This method is used to create Organization using Organization Name,Industry Type
	 * @param ogName
	 * @param indus
	 */
	public void createOrg(String ogName,String indus)
	{
		orgName.sendKeys(ogName);
		select(industry, indus);
		saveBtn.click();
		
	}
}
