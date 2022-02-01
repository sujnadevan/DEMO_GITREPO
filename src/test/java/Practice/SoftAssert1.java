package Practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssert1 {

	@Test
	public void AssertV1()
	{
		System.out.println("Step1");
		System.out.println("Step2");
		SoftAssert s = new SoftAssert();
		s.assertEquals("A", "B");
		
		System.out.println("Step3");
		System.out.println("Step4");
		s.assertAll();
	}
	
	@Test
	public void AssertV2()
	{
		System.out.println("Step1");
		System.out.println("Step2");
		Assert.assertEquals("A", "A");
		System.out.println("Step3");
		System.out.println("Step4");
	}
}
