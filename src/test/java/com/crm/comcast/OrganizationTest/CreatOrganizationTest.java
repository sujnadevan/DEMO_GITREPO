package com.crm.comcast.OrganizationTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseAnnotationClassTest;
import com.crm.comcast.pomrepositylib.CreateOrganizationPage;
import com.crm.comcast.pomrepositylib.HomePage;
import com.crm.comcast.pomrepositylib.OrganizationInfoPage;
import com.crm.comcast.pomrepositylib.OrganizationPage;

public class CreatOrganizationTest extends BaseAnnotationClassTest {

	@Test(groups="smoketest")
	public void createOrg() throws Throwable
	{
		
		HomePage home = new HomePage(driver);
		home.getOrganization().click();
		
		Thread.sleep(5000);
		
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
		Assert.assertTrue(actMsg.contains(orgName));
		
		
		
	}
	
	@Test(groups="regressiontest")
	public void createOrgIndusType() throws Throwable
	{
		
		HomePage home = new HomePage(driver);
		home.getOrganization().click();
		
		/*navigate to Org*/
		Thread.sleep(5000);
		OrganizationPage org = new OrganizationPage(driver);
		org.getCreateOrg().click();
		
		/*read data from Excel*/
		String orgName = eLib.getDataFromExcel("Org", 4, 2)+jLib.getRandomNum();
		String industry = eLib.getDataFromExcel("Org", 4, 3);
		/*create Orgnaization*/
		Thread.sleep(5000);
		CreateOrganizationPage createOrg = new CreateOrganizationPage(driver);
		createOrg.createOrg(orgName,industry);
		
		/*Verify*/
		OrganizationInfoPage orgInfo = new OrganizationInfoPage(driver);
		String actMsg = orgInfo.getSuccessMsg().getText();
		Assert.assertTrue(actMsg.contains(orgName));
		

	}
}
