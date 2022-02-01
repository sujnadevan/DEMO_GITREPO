package com.crm.GenericLibrary;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;



public class WebdriverUtility {
	/**
	 * This class contains WebDriver Utility
	 * @author isabe
	 */
		/**
		 * This method will maximize the window
		 * @param driver
		 */
		public void maximizeWindow(WebDriver driver) {
			driver.manage().window().maximize();
		}
		/**
		 * This method will wait till the page load
		 * @param driver
		 */
		public void waitForpageLoad(WebDriver driver)
		{
			driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		}
		/**
		 * This method will wait till the element become clickable
		 * @param driver
		 * @param element
		 */
		public void waitForElementToBeClickable(WebDriver driver, WebElement element)
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		/**
		 * This method will wait till element is found and perform click 
		 * @param element
		 * @throws Throwable
		 */
		public void waitAndClick(WebElement element) throws Throwable
		{
			int count =0;
			while(count<20);
			{
				try {
					element.click();
					
				}
				catch(Exception e) {
					Thread.sleep(1000);
					count++;
				}
			}
		}
		/**
		 * This method is used to handle Dropdown using index
		 * @param ele
		 * @param index
		 */
		public void select(WebElement ele,int index)
		{
			Select sel=new Select(ele);
			sel.selectByIndex(index);
			
		}
		/**
		 * This method is used to handle Dropdown using value
		 * @param ele
		 * @param value
		 */
		public void select(WebElement ele, String value)
		{
			Select sel=new Select(ele);
			sel.selectByValue(value);
		}
		/**
		 * This method is used to handle Dropdown using element address
		 * @param text
		 * @param elem
		 */
		public void select( String text,WebElement elem)
		{
			Select sel=new Select(elem);
			sel.selectByVisibleText(text);
		}
		/**
		 * This method is used to perform doubleclick 
		 * @param driver
		 */
		public void doubleClick(WebDriver driver)
		{
			Actions a=new Actions(driver);
			a.doubleClick().perform();
		}
		/**
		 * This methos is used to perform mousehover
		 * @param driver
		 * @param ele
		 */
		public void mousehover(WebDriver driver,WebElement ele)
		{
			Actions a=new Actions(driver);
			a.moveToElement(ele).perform();
		}
		/**
		 * This method is used to perform right click action
		 * @param driver
		 * @param ele
		 */
		public void rightClick(WebDriver driver, WebElement ele)
		{
			Actions a=new Actions(driver);
			a.contextClick(ele).perform();
		}
		/**
		 * This method is used to perform drag and drop action
		 * @param driver
		 * @param src
		 * @param dest
		 */
		public void dragAndDrop(WebDriver driver, WebElement src, WebElement dest)
		{
			Actions a=new Actions(driver);
			
			a.dragAndDrop(src, dest).perform();
		}
		/**
		 * This method is used to accept the alert popup
		 * @param driver
		 */
		public void acceptAlert(WebDriver driver)
		{
			driver.switchTo().alert().accept();
		}
		/**
		 * This method is used to dismiss the alert popup
		 * @param driver
		 */
		public void dismissAlert(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}
		
		/**
		 * This method is used to takeScreenshot of a failed testcases
		 * @param driver
		 * @param screenshotname
		 * @throws IOException
		 */
		public void gerScreenshot(WebDriver driver,String screenshotname) throws IOException
		{
			TakesScreenshot ts=(TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("./screenshot/"+screenshotname+".png");
			Files.copy(src, dest);
		}
		/**
		 * Switch to window depending on partial title
		 * @param driver
		 * @param partialWinTitle
		 */
		public void switchToWindow(WebDriver driver,String partialWinTitle)
		{
			Set<String> set = driver.getWindowHandles();
			Iterator<String> it = set.iterator();
			while(it.hasNext())
			{
				String windowid = it.next();
				driver.switchTo().window(windowid);
				String currentwindowtitle = driver.getTitle();
				if(currentwindowtitle.contains(partialWinTitle))
				{
					break;
				}
			}
		}
		/**
		 * This method is used to switch to frame using index
		 * @param driver
		 * @param index
		 */
		public void switchtoFrame(WebDriver driver, int index)
		{
			driver.switchTo().frame(index);
		/**This method is used to switch to frame using index
			 * 
			 */
		}
		public void  switchtoFrame(WebDriver driver, String idOrName)
		{
			driver.switchTo().frame(idOrName);
			
		}
		public void switchtoFrame(WebDriver driver, WebElement ele)
		{
			driver.switchTo().frame(ele);
		}
		public void switchtoDefaultFrame(WebDriver driver)
		{
			driver.switchTo().defaultContent();
		}
		public void switchtoFrame(WebDriver driver)
		{
			driver.switchTo().parentFrame();
		}
		/**
		 * Scroll until element is found
		 * @param driver
		 * 
		 * @param element
		 */
		public void scrollToElement(WebDriver driver,WebElement element)
		{
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("argument[0].scrollIntoView()",element);
		}
		
		public void passEnterKey() throws AWTException
		{
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		}
		/**
		 * this method wait for the element to be visibile
		 * @param driver
		 * @param element
		 */
		public void waitForElementVisibility(WebDriver driver,WebElement element)
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		/**
		 * this method is used to close the browser
		 * @param driver
		 */
		public void CloseBrowser(WebDriver driver)
		{
			driver.quit();
		}
	}





