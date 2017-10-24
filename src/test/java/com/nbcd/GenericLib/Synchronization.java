package com.nbcd.GenericLib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Synchronization 
{   
	
	//private static WebDriver driver;
    private enum  Property {
    	clickable, visible;
    }
    
	public   static void  explicitWait(WebDriver driver,WebElement objectID,String objectProperty)
	{
		WebElement elements;
		WebDriverWait wait = new WebDriverWait(driver, 90);
		
		Property objProp = Property.valueOf(objectProperty); // surround with try/catch
		switch(objProp)
		{
		case clickable:
			 elements = wait.until(ExpectedConditions.elementToBeClickable(objectID));
			break;
		case visible:
			 elements = wait.until(ExpectedConditions.visibilityOf(objectID));
			break;
			
		}
		
	System.out.println(objProp);
	}
	public static void implicitWait(WebDriver driver,int Secs)
	{

			driver.manage().timeouts().implicitlyWait(Secs, TimeUnit.SECONDS);
	}
	
	
}
