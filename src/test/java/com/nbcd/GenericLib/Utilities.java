package com.nbcd.GenericLib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utilities 
{
	private static WebDriver driver;
	// Refreshing DOM Elements
	public static WebElement returnElement(WebDriver driver,String locatorType, String locatorPath) {
	    switch (locatorType.toLowerCase()) {
	    case "id":
	        return driver.findElement(By.id(locatorPath));
	    case "idCollection":
	    	 return (WebElement) driver.findElements(By.id(locatorPath));
	    case "xpath":
	        return driver.findElement(By.xpath(locatorPath));
	    
	    case "name":
	        return driver.findElement(By.name(locatorPath));

	    case "classname":
	        return driver.findElement(By.className(locatorPath));
	        
	    case "classnamecollection":
	        List<WebElement> labels = driver.findElements(By.className(locatorPath));
	        		return (WebElement) labels;
	    case "cssselector":
	        return driver.findElement(By.cssSelector(locatorPath));

	    case "linktext":
	        return driver.findElement(By.linkText(locatorPath));

	    case "tagname":
	        return driver.findElement(By.tagName(locatorPath));

	    default:
	        throw new RuntimeException("Unknown locator " + locatorType + " : " + locatorPath);
	    }
	}
	
	public static List<WebElement> returnElements(WebDriver driver,String locatorType, String locatorPath) {
	    switch (locatorType.toLowerCase()) {
	    case "id":
	        return driver.findElements(By.id(locatorPath));
	    
	    case "xpath":
	        return driver.findElements(By.xpath(locatorPath));
	    
	    case "name":
	        return driver.findElements(By.name(locatorPath));

	    case "classname":
	        return driver.findElements(By.className(locatorPath));
	        
	  
	    case "cssselector":
	        return driver.findElements(By.cssSelector(locatorPath));

	    case "linktext":
	        return driver.findElements(By.linkText(locatorPath));

	    case "tagname":
	        return driver.findElements(By.tagName(locatorPath));

	    default:
	        throw new RuntimeException("Unknown locator " + locatorType + " : " + locatorPath);
	    }
	}
}
