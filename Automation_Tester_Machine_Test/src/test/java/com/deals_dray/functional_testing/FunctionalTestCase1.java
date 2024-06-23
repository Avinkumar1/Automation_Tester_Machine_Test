package com.deals_dray.functional_testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

public class FunctionalTestCase1 
{
	public static WebDriver driver;
	public static void main(String[] args) throws IOException 
	{
		FileInputStream file = new FileInputStream("./src/test/resource/DealsDrayFunctionalTestCase.properties");
		Properties pobj = new Properties();
		pobj.load(file);
		String browserName = pobj.getProperty("browserName");
		if(browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equals("safari"))
		{
			driver = new SafariDriver();
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		String timeStamp = LocalDateTime.now().toString().replace(":", "-");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(pobj.getProperty("url"));
		driver.findElement(By.name("username")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.name("password")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//button[text()='Login']")).submit();
		driver.findElement(By.xpath("//span[text()='Order']/../..//div[@class='MuiBox-root css-70qvj9']")).click();
		driver.findElement(By.xpath("//span[text()='Orders']")).click();
		driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();
		driver.findElement(By.id("mui-7")).sendKeys("C:\\Users\\avinn\\Downloads\\demo-data.xlsx");
		driver.findElement(By.xpath("//button[text()='Import']")).click();
		WebElement targetElement = driver.findElement(By.id("mui-193"));
		Actions actions = new Actions(driver);
		actions.scrollToElement(targetElement).perform();
		TakesScreenshot ts = (TakesScreenshot)driver;
		File tempScreenShot = ts.getScreenshotAs(OutputType.FILE);
		File permScreenShot = new File("./Output/" + timeStamp + "ValidateScreenShot.png");
		FileUtils.copyFile(tempScreenShot, permScreenShot);
		driver.findElement(By.xpath("//strong[text()='PREXO-MIS-ADMIN']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Logout')]")).click();
		driver.manage().window().minimize();
		driver.quit();
	}
}
