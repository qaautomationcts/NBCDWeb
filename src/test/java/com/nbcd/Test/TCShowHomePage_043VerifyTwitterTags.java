package com.nbcd.Test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.nbcd.GenericLib.Extent_Reports;
import com.nbcd.Pages.PGShowHomePage_043VerifyTwitterTags;
public class TCShowHomePage_043VerifyTwitterTags  extends Extent_Reports
{
	public WebDriver driver;
	
	
	@Test(groups="TCShowHomePage")
	
	@Parameters({ "Browser"})
	 public void TwitterTags(String Browser) throws InterruptedException, FilloException, IOException 
	 {
		
		PGShowHomePage_043VerifyTwitterTags objSP = new PGShowHomePage_043VerifyTwitterTags(Browser);
		
		objSP.VerifyTwitterTags();

	}
				
	

}


	
	

