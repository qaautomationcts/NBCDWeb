package com.nbcd.Pages;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.nbcd.GenericLib.DatabaseFunction;
import com.nbcd.GenericLib.GetWebDriverInstance;
import com.nbcd.GenericLib.Synchronization;
import com.nbcd.GenericLib.Utilities;
public class PGLaunchNBC 
{
	
	public WebDriver driver;
	String sql;
	
	DatabaseFunction db = new DatabaseFunction();
	
	public List<String> lstObject;
	String sqlQry,Status;

	
//Constructor to initialize all the Page Objects  
		public PGLaunchNBC(WebDriver driver) 
		{          
			this.driver = driver; 
			
			
			
		}
//=========================================================================================================================
		WebElement objNBCLogo;
		@Test
		  public boolean  LaunchNBC( ) throws InterruptedException, FilloException 
		  {
			  lstObject=db.getTestDataObject("Select * from LaunchNBC","ObjectRepository");
			  driver.get("http://www.nbc.com");
			  Synchronization.implicitWait(driver, 5);
			  WebElement objNBCLogo= (WebElement) Utilities.returnElement(driver,lstObject.get(2),lstObject.get(1));
			  if (objNBCLogo.isDisplayed())
			  {
					return true;
					
			  }
			  else
			  {
				  return false;
			  }
			
				
		}
			
	
		  

}