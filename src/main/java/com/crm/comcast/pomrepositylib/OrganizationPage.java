package com.crm.comcast.pomrepositylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

	public OrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
    @FindAll({@FindBy(xpath ="//img[@src='themes/softed/images/btnL3Add.gif']"),@FindBy(xpath="//img[@alt='Create Organization...']")})
	private WebElement createOrg;

	public WebElement getCreateOrg() {
		return createOrg;
	}
	@FindBy(xpath="//input[@name='search_text']")
	private WebElement searchEdt;
	
	public WebElement getSearchEdt() {
		return searchEdt;
	}


	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBtn;

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	
    
}
