package com.nbcd.GenericLib;

//============================================IMPORT PACKAGES====================================================================================================
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

//=========================================================CLASS & METHODS =============================================================================================
public class GetWebDriverInstance {

	private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	
 	private static String  genericPath=System.getProperty("user.dir").concat("\\src\\test\\resources\\DriverExecutable\\");
 	
	
	/*
	 * Factory method for getting browsers
	 */
	public static WebDriver getBrowser(String browserName) throws MalformedURLException 
	{
		WebDriver driver = null;
		Properties prop = new Properties();
		try 
		{

			 InputStream input = new FileInputStream("cloudPathConfig.properties");
			// load a properties file
				prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));

		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		switch (browserName.toUpperCase()) 
		{
			case "FIREFOX":
				   System.setProperty("webdriver.gecko.driver", genericPath+"geckodriver.exe");
		 			String path=new File(".").getAbsolutePath();
	
				driver = drivers.get("Firefox");
				if (driver == null) 
				{
					driver = new FirefoxDriver();
					drivers.put("Firefox", driver);
				}
				break;
			case "IE":
				driver = drivers.get("IE");
				if (driver == null) 
				{
					System.setProperty("webdriver.ie.driver", genericPath+"IEDriverServer.exe");
					
					driver = new InternetExplorerDriver();
					drivers.put("IE", driver);
				}
			break;
			case "CHROME":
				driver = drivers.get("Chrome");
				if (driver == null) 
				{
					System.out.println(genericPath);
					
					System.setProperty("webdriver.chrome.driver",genericPath+"chromedriver.exe");  
					ChromeOptions options = new ChromeOptions(); 
					options.addArguments("disable-infobars"); 
					//driver = new ChromeDriver();
					
					 driver = new ChromeDriver(options);

					
				}
				break;
			case "REMOTE":
					final String USERNAME =prop.getProperty("username");
					final String AUTOMATE_KEY = prop.getProperty("accessKey");;
					final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
					DesiredCapabilities caps = new DesiredCapabilities();
				    caps.setCapability("browser",prop.getProperty("browser"));
				    caps.setCapability("browser_version", prop.getProperty("browser_version"));
				    caps.setCapability("resolution", "1024x768");
				    caps.setCapability("os", prop.getProperty("os"));
				    caps.setCapability("os_version",prop.getProperty("os_version"));
				    caps.setCapability("browserstack.debug", "true");
			        /* driver = new RemoteWebDriver(
			            new URL("https://nbcd1@9xkmc7sMxp3EijVDA2wn@hub.browserstack.com/wd/hub"),
			            caps  );*/
			      driver = new RemoteWebDriver(new URL(URL), caps);
			      driver.get(URL); 
				break;
				
				
			case "OPERA":
			
				
					driver = new OperaDriver();
				
				
				break;
				
			case "SAFARI":
				// Takes the system proxy settings automatically
				
				driver = new SafariDriver();
				break;
				
				
		}
		driver.manage().window().maximize();
 		driver.manage().deleteAllCookies();
		return driver;
	}
	
	public static void closeAllDriver() 
	{
		for (String key : drivers.keySet()) 
		{
			drivers.get(key).close();
			drivers.get(key).quit();
		}
    }
	
	
}
