package com.nbcd.Pages;
import com.nbcd.Pages.PGLaunchNBC;

import com.relevantcodes.extentreports.LogStatus;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.nbcd.GenericLib.DatabaseFunction;
import com.nbcd.GenericLib.GetWebDriverInstance;
import com.nbcd.GenericLib.Utilities;
import com.nbcd.GenericLib.Extent_Reports;
public class PGHomePage extends GetWebDriverInstance
{
	
	private static WebDriver driver;
	String sql;
	protected static String showDetails;
	DatabaseFunction db = new DatabaseFunction();
	
	public List<String> lstObject,lstTestData;
	String sqlQry,Status;
	WebElement objMenu;
//Constructor to initialize all the Page Objects  
	
	
	public PGHomePage(String Browser) 
	{      
		
		try 
		{
			System.out.println("PGHomePage:"+ Browser);
			this.driver = GetWebDriverInstance.getBrowser(Browser);
			
		} 
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				
				
	}
	//=========================================================================================================================

	@Test
	  public PGHomePage MenuDetails( ) throws InterruptedException, FilloException 
	  {
		  if(new PGLaunchNBC(driver).LaunchNBC())
		  {
			  Assert.assertTrue(true,"NBC Logo is visible.");
			  
		  }
		  else
		  {
			  Assert.assertFalse(false,"NBC Logo is not visible.");
		  }
		  lstObject=db.getTestDataObject("Select * from HomePage","ObjectRepository");
		  lstTestData=db.getTestDataObject("Select * from HomePage where Menu IS NOT NULL","Input");
		
		
		try
		 {
		  List<WebElement> totalNavigationmenu = Utilities.returnElements(driver,lstObject.get(2),lstObject.get(1));
		
		  int totalNavigationmenuSize = totalNavigationmenu.size();
		
		  Iterator<WebElement> iter = totalNavigationmenu.iterator();
		  while(iter.hasNext()) {
		     WebElement we = iter.next();
		     for (int iLoop = 0; iLoop < lstTestData.size(); iLoop++) 
		     {
					if (lstTestData.get(iLoop).equalsIgnoreCase(we.getText()))
					{
						Assert.assertEquals(lstTestData.get(iLoop), we.getText(),"Expected Navigation Link is available.");
						System.out.println("Expected Menu:"+lstTestData.get(iLoop) +"||Actual Menu:"+we.getText());
						Extent_Reports.logger.log(LogStatus.PASS,"Expected Menu:"+ lstTestData.get(iLoop) +"||Actual Menu:"+we.getText());
	
						break;
					}
					
			 }
		     System.out.println(we.getText());
		   }
		  }
		 catch(Exception exc)
		 {
			 System.out.println(exc.getMessage());
		 }
		
		driver.close();
		return null;
	}
			
			  
	
}
