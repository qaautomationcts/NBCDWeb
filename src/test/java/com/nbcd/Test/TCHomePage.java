package com.nbcd.Test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.nbcd.GenericLib.Extent_Reports;
import com.nbcd.GenericLib.GetWebDriverInstance;
import com.nbcd.Pages.PGHomePage;

public class TCHomePage  extends Extent_Reports 
{
	public WebDriver driver;
	
	
	
	@Test(groups="TCHomePage")
	@Parameters({ "Browser"})
	 public void MenuDetails(String Browser) throws InterruptedException, FilloException, IOException 
	 {

		PGHomePage objHP = new PGHomePage(Browser);
		
		objHP.MenuDetails();

	}
}
