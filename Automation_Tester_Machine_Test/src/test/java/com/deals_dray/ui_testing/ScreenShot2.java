package com.deals_dray.ui_testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class ScreenShot2 
{
	public static WebDriver driver;
	public static void main(String[] args) throws IOException 
	{
		FileInputStream file = new FileInputStream("./src/test/resource/DealsDray2.properties");
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
		org.openqa.selenium.Dimension definedResolutions = new org.openqa.selenium.Dimension(1366,768);
		driver.manage().window().setSize(definedResolutions);
		TakesScreenshot ts = (TakesScreenshot)driver;
		File tempScreenShot = ts.getScreenshotAs(OutputType.FILE);
		File permScreenShot = new File("./Output/" + timeStamp + "GetCalleyFromBrowser1366-768.png");
		FileUtils.copyFile(tempScreenShot, permScreenShot);
		driver.manage().window().minimize();
		driver.quit();
	}
}
