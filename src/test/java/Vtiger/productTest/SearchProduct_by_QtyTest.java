package Vtiger.productTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchProduct_by_QtyTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileInputStream fis = new FileInputStream("./data/TC_12Test.properties");
		Properties PrObj = new Properties();
		PrObj.load(fis);
		String BROWSER = PrObj.getProperty("browser");
		String URL = PrObj.getProperty("url");
		String Username = PrObj.getProperty("username");
		String Pwd = PrObj.getProperty("password");
		WebDriver driver = null;
		if(BROWSER.equals("chrome"))
		{
			//driver = new ChromeDriver();
		}
		else
		{
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Pwd);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("//a[.='Products']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
		driver.findElement(By.name("productname")).sendKeys("Micromax");
		
		String txt1 = "6346";
		driver.findElement(By.xpath("//td[@class='dvtCellInfo']/input[@id='qty_per_unit']")).sendKeys(txt1);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//input[@value='  Save  '])[1]")).click();
	    Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@href='index.php?module=Products&action=index']")).click();
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys("6346");
		WebElement ele = driver.findElement(By.id("bas_searchfield"));
		Select s = new Select(ele);
		s.selectByValue("qty_per_unit");
		driver.findElement(By.xpath("//input[@value=' Search Now ']/../input[@name='submit']")).click();
		String ele1 = driver.findElement(By.xpath("//tr[@class='lvtCoData']/td[6]")).getText();
		
		if(ele1.equals(txt1))
		{
			System.out.println("Pas......... ");
		}
		else
		{
			System.out.println("Fail.........");
		}
		
		
		
		
		
	}

}
