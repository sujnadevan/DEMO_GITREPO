package com.crm.GenericLibrary;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListImpClass implements ITestListener{

	public void onTestFailure(ITestResult result) {
		
		String testName = result.getMethod().getMethodName();
		System.out.println(testName+"====Execute & i am Listening=======");
		
		EventFiringWebDriver eDriver = new EventFiringWebDriver(BaseAnnotationClassTest.sDriver);
		
		 File srcFile = eDriver.getScreenshotAs(OutputType.FILE);
		 try
		 {
			 
			 FileUtils.copyFile(srcFile, new File("./Screenshot/"+testName+".png"));
			 
		 }
		 catch(IOException e)
		 {
			
		 }
	}
	
	
	

}
