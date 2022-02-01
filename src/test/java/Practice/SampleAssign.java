package Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SampleAssign {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileInputStream fis = new FileInputStream("./data/commonData.properties");
		Properties PrObj = new Properties();
		PrObj.load(fis);
		String Browser = PrObj.getProperty("browser");
		String Url = PrObj.getProperty("url");
		String Username = PrObj.getProperty("username");
		String Password = PrObj.getProperty("password");
		
		FileInputStream fis1 = new FileInputStream("./data/data.xlsx");
		Workbook book = WorkbookFactory.create(fis1);
		org.apache.poi.ss.usermodel.Sheet s1 = book.getSheet("Sheet1");
	
		String ORG_NAME = s1.getRow(1).getCell(0).getStringCellValue();
		String WEBSITE = s1.getRow(1).getCell(1).getStringCellValue();
		
		WebDriver driver = null;
		if(Browser.equals("firefox")) 
		{
			driver= new FirefoxDriver();
		}
		else  {

			//driver = new ChromeDriver();
		}
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(Username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index' and .='Organizations']")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		//String ORG_NAME = PrObj.getProperty("Organization");
		
		//String TCKR_SYM = PrObj.getProperty("Ticker_Symbol");
		
		driver.findElement(By.name("accountname")).sendKeys(ORG_NAME);
		driver.findElement(By.name("website")).sendKeys(WEBSITE);
		
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
		String txt = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(txt.contains(ORG_NAME))
		{
			System.out.println("Project created");
		}
		else
		{
			System.out.println("Project is not created");
		}
		
		

		

	}

}
