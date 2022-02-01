package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateOrganisation {
public static void main(String[] args) throws IOException, InterruptedException {
		
		FileInputStream fis = new FileInputStream("./data/commonData.properties");
		Properties PrObj = new Properties();
		PrObj.load(fis);
		String Browser = PrObj.getProperty("browser");
		String Url = PrObj.getProperty("url");
		String Username = PrObj.getProperty("username");
		String Password = PrObj.getProperty("password");
		//File from excel
		FileInputStream fis1 = new FileInputStream("./data/data.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		String Proj_name = book.getSheet("Sheet1").getRow(1).getCell(2).getStringCellValue();
		
		 
		WebDriver driver = null;
		if(Browser.equals("firefox")) 
		{
			driver= new FirefoxDriver();
		}
		else  {

			driver = new ChromeDriver();
		}
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(Username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index' and .='Organizations']")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		String ORG_NAME = PrObj.getProperty("Organization");
		String WEBSITE = PrObj.getProperty("Website");
		
		
		driver.findElement(By.name("accountname")).sendKeys(ORG_NAME);
		driver.findElement(By.name("website")).sendKeys(WEBSITE);
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
	
		
		

		

	}


}
