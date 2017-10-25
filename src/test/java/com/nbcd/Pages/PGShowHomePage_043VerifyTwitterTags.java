package com.nbcd.Pages;
import com.relevantcodes.extentreports.LogStatus;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.nbcd.GenericLib.DatabaseFunction;
import com.nbcd.GenericLib.Extent_Reports;
import com.nbcd.GenericLib.GetWebDriverInstance;
import com.nbcd.GenericLib.Utilities;

public class PGShowHomePage_043VerifyTwitterTags extends GetWebDriverInstance
{

	private static WebDriver driver;
	String sql;
	protected static String showDetails;
	DatabaseFunction db = new DatabaseFunction();
	
	public List<String> lstObject,lstTestData;
	String sqlQry,Status;
	WebElement objTwitterCard,objTwitterSite,objTwitterSiteId,objTwitterTitle,objTwitterDescription,objTwitterImage;
	
//=================================================================================================================================================================================	
//Constructor to initialize all the Page Objects  
	public PGShowHomePage_043VerifyTwitterTags(String Browser) 
	{      
		try 
			{
				
				this.driver = GetWebDriverInstance.getBrowser(Browser);
				lstTestData=db.getTestDataObject("Select * from VerifyTwitterTags","Input");
				lstObject=db.getTestDataObject("Select * from VerifyTwitterTags","ObjectRepository");
			} 
			catch (MalformedURLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
					
					
		}
//=========================================================================================================================
	@Test
		  public PGShowHomePage_043VerifyTwitterTags VerifyTwitterTags( ) throws InterruptedException, FilloException 
		  {
			
			//Launching Browser with valid URL.
			     driver.get(lstTestData.get(0));
			
			try
			 {
				objTwitterCard =Utilities.returnElement(driver,lstObject.get(2),lstObject.get(1));
				objTwitterSite=Utilities.returnElement(driver,lstObject.get(5),lstObject.get(4));
				objTwitterSiteId=Utilities.returnElement(driver,lstObject.get(8),lstObject.get(7));
				objTwitterTitle=Utilities.returnElement(driver,lstObject.get(11),lstObject.get(10));
				objTwitterDescription=Utilities.returnElement(driver,lstObject.get(14),lstObject.get(13));
				objTwitterImage=Utilities.returnElement(driver,lstObject.get(17),lstObject.get(16));
				
			/**twitter:card*/
				
				if (lstTestData.get(1).equalsIgnoreCase(objTwitterCard.getAttribute("content")))
				{
					Extent_Reports.logger.log(LogStatus.PASS,"Expected Result for '"+lstObject.get(0) +"':"+lstTestData.get(1) +"\n"+"Actual Result for '"+lstObject.get(0) +"':"+objTwitterCard.getAttribute("content"));
				}
				else
				{
					Extent_Reports.logger.log(LogStatus.FAIL,"Expected Result for '"+lstObject.get(0) +"':"+lstTestData.get(1) +"\n"+"Actual Result for '"+lstObject.get(0) +"':"+objTwitterCard.getAttribute("content"));

				}
			/**twitter:site*/	
				if (lstTestData.get(2).equalsIgnoreCase(objTwitterSite.getAttribute("content")))
				{
					Extent_Reports.logger.log(LogStatus.PASS,"Expected Result for '"+lstObject.get(3) +"':"+lstTestData.get(2) +"\n"+"Actual Result for '"+lstObject.get(3) +"':"+objTwitterSite.getAttribute("content"));
				}
				else
				{
					Extent_Reports.logger.log(LogStatus.FAIL,"Expected Result for '"+lstObject.get(3) +"':"+lstTestData.get(2) +"\n"+"Actual Result for '"+lstObject.get(3) +"':"+objTwitterSite.getAttribute("content"));

				}
				
			/**twitter:site:id*/
				if (lstTestData.get(3).equalsIgnoreCase(objTwitterSiteId.getAttribute("content")))
				{
					Extent_Reports.logger.log(LogStatus.PASS,"Expected Result for '"+lstObject.get(6) +"':"+lstTestData.get(3) +"\t"+"Actual Result for '"+lstObject.get(6) +"':"+objTwitterSiteId.getAttribute("content"));
				}
				else
				{
					Extent_Reports.logger.log(LogStatus.FAIL,"Expected Result for '"+lstObject.get(6) +"':"+lstTestData.get(3) +"\t"+"Actual Result for '"+lstObject.get(6) +"':"+objTwitterSiteId.getAttribute("content"));

				}
				
			/** twitter:title */
				if (lstTestData.get(4).equalsIgnoreCase(objTwitterTitle.getAttribute("content")))
				{
					Extent_Reports.logger.log(LogStatus.PASS,"Expected Result for '"+lstObject.get(9) +"':"+lstTestData.get(4) +"\t"+"Actual Result for '"+lstObject.get(9) +"':"+objTwitterTitle.getAttribute("content"));
				}
				else
				{
					Extent_Reports.logger.log(LogStatus.FAIL,"Expected Result for '"+lstObject.get(9) +"':"+lstTestData.get(4) +"\t"+"Actual Result for '"+lstObject.get(9) +"':"+objTwitterTitle.getAttribute("content"));

				}
				/**twitter:description*/
				if (lstTestData.get(5).equalsIgnoreCase(objTwitterDescription.getAttribute("content")))
				{
					Extent_Reports.logger.log(LogStatus.FAIL,"Expected Result for '"+lstObject.get(12) +"':Not Null"+lstTestData.get(5) +"\t"+"Actual Result for '"+lstObject.get(12) +"':"+objTwitterDescription.getAttribute("content"));

				}
				else
				{
					Extent_Reports.logger.log(LogStatus.PASS,"Expected Result for '"+lstObject.get(12) +"':Not Null"+lstTestData.get(5) +"\t"+"Actual Result for '"+lstObject.get(12) +"':"+objTwitterDescription.getAttribute("content"));

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
