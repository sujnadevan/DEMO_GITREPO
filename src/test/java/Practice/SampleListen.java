package Practice;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.GenericLibrary.BaseAnnotationClassTest;
import com.crm.comcast.pomrepositylib.HomePage;

@Listeners(com.crm.GenericLibrary.ListImpClass.class)

public class SampleListen extends BaseAnnotationClassTest {
	
	@Test
	public void ScreenshotTest(WebDriver driver) throws Throwable
	{
		HomePage home = new HomePage(driver);
		home.getOrganization().click();
			Assert.assertEquals(true, false);
		
	}

	
}
