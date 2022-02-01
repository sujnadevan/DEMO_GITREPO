package com.crm.comcast.ContactTest;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseAnnotationClassTest;
import com.crm.comcast.pomrepositylib.ContactInfoPage;
import com.crm.comcast.pomrepositylib.ContactPage;
import com.crm.comcast.pomrepositylib.CreateContactPage;
import com.crm.comcast.pomrepositylib.CreateOrganizationPage;
import com.crm.comcast.pomrepositylib.HomePage;
import com.crm.comcast.pomrepositylib.OrganizationInfoPage;
import com.crm.comcast.pomrepositylib.OrganizationPage;

@Listeners(com.crm.GenericLibrary.ListImpClass.class)

public class CreateContactTest extends BaseAnnotationClassTest{
	
	
	@Test(groups = "smoketest")
	public void createContact() throws Throwable
	{
		
		/*Navigate to Contact*/
		HomePage home = new HomePage(driver);
		home.getContact().click();
		
		
		/*click on createcontact*/
		ContactPage cp = new ContactPage(driver);
		wLib.waitForElementToBeClickable(driver, cp.getCreateContact());
		cp.getCreateContact().click();
		
		/*read data from excel*/
		String lName = eLib.getDataFromExcel("Contact", 4, 2)+jLib.getRandomNum();
		
		/*create contact*/
		CreateContactPage createCon = new CreateContactPage(driver);
		createCon.crtContact(lName);
		
		/*Verify*/
		ContactInfoPage conInfo = new ContactInfoPage(driver);
		String actMsg = conInfo.getSuccessMsg().getText();
		System.out.println(actMsg);
		Assert.assertTrue(actMsg.contains(lName));
		
	
	}
	
	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws Throwable
	{
		/*navigate to organization*/
		HomePage home = new HomePage(driver);
		home.getOrganization().click();
		
		
		
		/*click on create org */
		OrganizationPage org = new OrganizationPage(driver);
		wLib.waitForElementToBeClickable(driver, org.getCreateOrg());
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
		System.out.println(actMsg);
		Assert.assertTrue(actMsg.contains(orgName1));
		
		/*Navigate to Contact*/
		home.getContact().click();
		Thread.sleep(5000);
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
		Assert.assertTrue(actMsg1.contains(lName));
		

	}
}
