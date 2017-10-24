package com.nbcd.Test;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.nbcd.GenericLib.Extent_Reports;
import com.nbcd.GenericLib.GetWebDriverInstance;

import com.nbcd.Pages.PGLaunchNBC;
import com.nbcd.Pages.PGShowPage;
public class TCShowPage  extends Extent_Reports
{
	public WebDriver driver;
	
	
	@Test(groups="TCShowPage")
	
	@Parameters({ "Browser"})
	 public void Shows(String Browser) throws InterruptedException, FilloException, IOException 
	 {
		
		PGShowPage objSP = new PGShowPage(Browser);
		
		objSP.ShowMenu();

	}
				
	

}


	
	

