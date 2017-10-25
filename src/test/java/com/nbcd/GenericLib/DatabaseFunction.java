package com.nbcd.GenericLib;

//====================================================== All Required Packages =======================================================================================

import java.io.FileOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

//=====================================================================Class,Methods Declaration=========================================================================
public class DatabaseFunction
{

//==========================================Script Variables==============================================================================================================	
    public static com.codoid.products.fillo.Connection con;
    public static String computerName,sql,Path;
    public String sqlQuery;
    public static String fileType;
//==========================================================================================================================================================================
    
    Fillo fillo=new Fillo();  //API for Java to connect with Excel as database.
  
    public DatabaseFunction() //Constructor of the class.
    {
    	 
	    try
	    {
	        computerName = InetAddress.getLocalHost().getHostName();   // Name of the System where user is executing the Scripts.
	        Path=System.getProperty("user.dir");                       // Soruce Directory of the Source code
	        
	    }
	    catch (Exception exc)
	    {
	        System.out.println("DBConnection Failed"+exc);
	        
	    }
     }
    //##################################################################################################################################
    /** Name of the Fuction: fnGetDbConnection
        Description:This function will  return the connection object for the file type.
        Input Type: Type of the file which needs to connect as Database. Ex: 'Object Repository','Input'
        Return Value:  connection object. */
    
    public Connection fnGetDbConnection(String FileType)
    {   
    	String genericPath=System.getProperty("user.dir").concat("\\src\\main\\resources\\");
    	
    	try
    	{
	    	if("Input".equals(FileType))
	    	   
	    	{
	    		
	    		con = fillo.getConnection(genericPath +"TestData\\"+ "NBCCOM.xls");
	    	}
	    		    	
	    	if("Output".equals(FileType))
		    	   
	    	{
	    		
	    		String Path=System.getProperty("user.dir").concat("\\test-output\\");
	    		con = fillo.getConnection(Path + "Output.xls");
	    	}
	    	if("ObjectRepository".equals(FileType))
		    	   
	    	{
	    		
	    		System.out.println(genericPath+"ObjectRep\\" + "NBCCOM.xls");
	    		con = fillo.getConnection(genericPath+"ObjectRep\\" + "NBCCOM.xls");
	    		
	    	}
	    	if("TestRunner".equals(FileType))
		    	   
	    	{
	    		
	    		System.out.println(genericPath+"TestRunner\\" + "NBCCOM.xls");
	    		con = fillo.getConnection(genericPath+"TestRunner\\"  + "TestRunner.xls");
	    		
	    	}
	         System.out.println("Database connected Successfully.");
	       
    	}
    	catch(Exception exc)
    	{
    		
    		System.out.println("fnGetDbConnectionDBConnection Failed"+exc);
    	}
    	
    	return con;
     }
 //##########################################################################################################################################
 
    /** Name of the Fuction: fnExecuteSql
    Description:This function will  execute the sql query.
    Input Type: SQL Query,File Type,Col Name: * (all the records), colname(records for specific column). Ex: 'Object Repository','Input'
    Return Value:  connection object. */

    public List<String> fnExecuteSql(String sqlQuery,String FileType,String ColName)

    {

    	List<String> list = new ArrayList<String>();
    	ArrayList<String> colName;
	    try
	    {
	    	fnGetDbConnection(FileType);
		
	    	Recordset recordset=con.executeQuery(sqlQuery);
		
	    	colName=recordset.getFieldNames();
	
	    	if (ColName == "*") 
	    	{
	    		while(recordset.next())  //For All the Columns
	    		{
	    			for (int iLoop = 0; iLoop < colName.size(); iLoop++)
	    			{
				
	    				list.add(recordset.getField(colName.get(iLoop)));
					
	    			}
	    		}
	    	}
	    	else //For Single  Column
	    	{
	    		while(recordset.next())  //For All the Columns
	    		{
	    			list.add(recordset.getField(ColName));
	    		}
	    	}
			recordset.close();
			con.close();
	 
	    }
		catch (Exception SQLException)
		{       
		   System.out.print("Exception in fnExecuteSql:"+SQLException);
		    
		}
		return list;

    }
//##########################################################################################################################################


//<summary>
//Function to get the object details from database for the specified screen name.
//</summary>
//<param name="screenName">Screen Name</param>
//<returns>True if Screen Name exists in database else returns false.</returns>

	public Hashtable<String,String> getHashTestData(String sqlQuery,String FileType)
	
	{
		Hashtable<String, String> htbl = new Hashtable<String,String>();
		
		try
	{
		fnGetDbConnection("TestRunner");
		Recordset recordset=con.executeQuery(sqlQuery);
		 recordset.getFieldNames();
	
	    while(recordset.next())  
		{

	    	String TestClass = recordset.getField("TestClass");
	    	String Browser= recordset.getField("Browser");
	    	htbl.put(TestClass, Browser);
		}
	}
	catch (Exception exc)
	{       
	  System.out.println(exc);
	}
	return htbl;

	}
//#############################################################################################################

//##########################################################################################################################################

// <summary>
// Function to get the object details from database for the specified screen name.
// </summary>
// <param name="screenName">Screen Name</param>
// <returns>True if Screen Name exists in database else returns false.</returns>

	
	public List<String> getTestDataObject(String sqlQuery,String FileType)
	{
		
		List<String> list = new ArrayList<String>();
		ArrayList<String> colName;
	try
	{
		fnGetDbConnection(FileType);
		Recordset recordset=con.executeQuery(sqlQuery);
		 colName = recordset.getFieldNames();
	
	    while(recordset.next())  //For All the Columns
		{
			for (int iLoop = 0; iLoop < colName.size(); iLoop++)
			{
			
				list.add(recordset.getField(colName.get(iLoop)));
				
				
			}
		}
	}
	catch (Exception exc)
	{       
	  System.out.println(exc);
	}
	//return htbl;
	return list;
	}
//#############################################################################################################


}
