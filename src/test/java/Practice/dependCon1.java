package Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class dependCon1 {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("./data/comData.properties");
		Properties PrObj = new Properties();
		PrObj.load(fis);
		String BROWSER = PrObj.getProperty("browser");
		String URL = PrObj.getProperty("url");
		String Username = PrObj.getProperty("username");
		String Pwd = PrObj.getProperty("password");
		WebDriver driver = null;
		
		if(BROWSER.equals("firefox")) 
		{
			driver= new FirefoxDriver();
		}
		else  {

			//driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		driver.get(URL);
		driver.findElement(By.name("username")).sendKeys(Username);
		driver.findElement(By.name("password")).sendKeys(Pwd);
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
	    
		String Proj_name = PrObj.getProperty("Project_Name");
		String Proj_mgr = PrObj.getProperty("Project_Manager");
		//String Proj_status = PrObj.getProperty("Project_Status");
		
		driver.findElement(By.name("projectName")).sendKeys(Proj_name);
		driver.findElement(By.name("createdBy")).sendKeys(Proj_mgr);
		WebElement ele = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		ele.click();
		Select s = new Select(ele);
		s.selectByValue("Completed");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		Connection conn = null;
		try {
		Driver dref = new Driver();
		DriverManager.registerDriver(dref);
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		System.out.println("========Connection is done==========");
		Statement stat = conn.createStatement();
		String query = "select project_name from project";
		ResultSet resultset = stat.executeQuery(query);
		boolean flag = false;
		
		while(resultset.next())
		{
			
		String actProj = resultset.getString(1);
		if(actProj.equals(Proj_name))
		{
			 flag = true;
			 break;
		}
		}
		if(flag == true)
		{
			System.out.println("Project present");
			
		}
		else {
			System.out.println("Project not present");
		     }
		}
		
		catch(Exception e)
		{
			
		}
		finally
		{
		conn.close();
		System.out.println("========Connection is done==========");
		}
		driver.findElement(By.xpath("//a[.='Logout']")).click();
	}}

		



