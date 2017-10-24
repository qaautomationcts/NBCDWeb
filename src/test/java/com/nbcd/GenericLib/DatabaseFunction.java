package com.nbcd.GenericLib;

import java.io.FileOutputStream;
import java.net.InetAddress;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class DatabaseFunction
{
    public static com.codoid.products.fillo.Connection con;
  
    public static String computerName,sql,Path;
    public static int projectVersionID,projectID,runID,testSuiteID,testCaseID,testDataID;
    public String sqlQuery;
    public static String fileType;
  
    Fillo fillo=new Fillo();
   /* String  extensionFormat = new SimpleDateFormat("yyyyMMddHHmm'.xls'").format(new Date());
    String filename = System.getProperty("user.dir").concat("\\test-output\\Output"+extensionFormat) ;
*/   
    public DatabaseFunction()
    {
    	 
	    try
	    {
	        computerName = InetAddress.getLocalHost().getHostName();
	        Path=System.getProperty("user.dir");
	        
	    }
	    catch (Exception exc)
	    {
	        System.out.println("DBConnection Failed"+exc);
	        
	    }
  }
    //##################################################################################################################################
    public Connection fnGetDbConnection(String FileType)
    {   
    	String genericPath1=System.getProperty("user.dir").concat("\\src\\test\\resources\\TestData\\");
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

    public void fnWriteToExcel(String strQuery) throws FilloException
    {
    	
    	int iCnt = 0;
    	FileOutputStream fileOut;
    	Connection connection;
    	
    
    	fnGetDbConnection("Output");
    	       
    	         
    	          con.executeUpdate(strQuery);

    	          con.close();
    	}
//=======================================================================
    public void fnTestData(String strQuery) throws FilloException
    {
    	fnGetDbConnection("Input");
    	con.executeUpdate(strQuery);
    	con.close();
    }
		
		
    
    //##########################################################################################################################################

    // <summary>
    // Function to execute Sql Qry
    // </summary>
    // <param name="sqlquery">SqlQuery</param>
    // <returns>Records</returns>

public List<String> fnExecuteSql(String sqlQuery,String FileType,String ColName)
//public List<String> fnExecuteSql(String sqlQuery,String FileType)
 
{

	List<String> list = new ArrayList<String>();
	String colVal = null;
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
//##########################################################################################################################################

//<summary>
//Function to get the object details from database for the specified screen name.
//</summary>
//<param name="screenName">Screen Name</param>
//<returns>True if Screen Name exists in database else returns false.</returns>

	public Hashtable<String,String> getHashTestData(String sqlQuery,String FileType)
	
	{
		Hashtable<String, String> htbl = new Hashtable<String,String>();
		
		String colVal = null;
		ArrayList<String> colName;
	try
	{
		fnGetDbConnection("TestRunner");
		Recordset recordset=con.executeQuery(sqlQuery);
		 colName = recordset.getFieldNames();
	
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

	//public Hashtable<String,String> getObjects(String sqlQuery,String FileType)
	public List<String> getTestDataObject(String sqlQuery,String FileType)
	{
		//Hashtable<String, String> htbl = new Hashtable<String,String>();
		List<String> list = new ArrayList<String>();
		String colVal = null;
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
