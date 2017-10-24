package com.nbcd.Pages;
import com.nbcd.Pages.PGLaunchNBC;
import com.relevantcodes.extentreports.LogStatus;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.nbcd.GenericLib.DatabaseFunction;
import com.nbcd.GenericLib.Extent_Reports;
import com.nbcd.GenericLib.GetWebDriverInstance;
import com.nbcd.GenericLib.Utilities;

public class PGShowPage extends GetWebDriverInstance
{

	private static WebDriver driver;
	String sql;
	protected static String showDetails;
	DatabaseFunction db = new DatabaseFunction();
	
	public List<String> lstObject,lstTestData;
	String sqlQry,Status;
	
//Constructor to initialize all the Page Objects  
	
	
		public PGShowPage(String Browser) 
		{      
			
			try 
			{
				System.out.println("PGHomePage:"+ Browser);
				this.driver = GetWebDriverInstance.getBrowser(Browser);
				lstObject=db.getTestDataObject("Select * from Show","ObjectRepository");
				  lstTestData=db.getTestDataObject("Select * from Show where Title IS NOT NULL","Input");
			} 
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
					
					
		}
		//=========================================================================================================================
		WebElement objShow,objShowName,objShowTitle;
		@Test
		  public PGHomePage ShowMenu( ) throws InterruptedException, FilloException 
		  {
			  if(new PGLaunchNBC(driver).LaunchNBC())
			  {
				  Assert.assertTrue(true,"NBC Logo is visible.");
				  
			  }
			  else
			  {
				  Assert.assertFalse(false,"NBC Logo is not visible.");
			  }
			  
			
			  
			
			try
			 {
				objShow =Utilities.returnElement(driver,lstObject.get(2),lstObject.get(1));
			
				objShow.click();
				
				objShowName=Utilities.returnElement(driver,lstObject.get(5),lstObject.get(4));
				objShowName.click();
				objShowTitle=Utilities.returnElement(driver,lstObject.get(8),lstObject.get(7));
				WebElement myDynamicElement = (new WebDriverWait(driver, 15))
						  .until(ExpectedConditions.presenceOfElementLocated((By) objShowTitle));
				
 
				
				if (lstTestData.get(0).equalsIgnoreCase(objShowTitle.getText()))
				
				{
					System.out.println("HERE");
					Extent_Reports.logger.log(LogStatus.PASS, lstTestData.get(0) +"::"+objShowTitle.getText());
				}
				else
				{System.out.println("NHERE");
					Extent_Reports.logger.log(LogStatus.FAIL, lstTestData.get(0) +"::"+objShowTitle.getText());
				}
			 }
			 catch(Exception exc)
			 {
				 System.out.println(exc.getMessage());
			 }
			

			return null;
		}
				

}
