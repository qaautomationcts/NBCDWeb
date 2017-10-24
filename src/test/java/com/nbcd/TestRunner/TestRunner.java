package com.nbcd.TestRunner;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.nbcd.GenericLib.DatabaseFunction;

public class TestRunner
{
	public Hashtable<String, String> htTestRunner;
	@SuppressWarnings("deprecation")
	@Test
	public void tRun()
	{

		try
    	{
			DatabaseFunction objDBF= new DatabaseFunction();
			htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes'","TestRunner");
    	}
		catch(Exception exc)
		{
			System.out.println("Exception in TestRunner:"+ exc.getMessage());
		}
		//Create an instance on TestNG
		 TestNG myTestNG = new TestNG();
		 
		//Create an instance of XML Suite and assign a name for it.
		 XmlSuite mySuite = new XmlSuite();
		 //mySuite.setName("NBCAutomation parallel="+ "classes" + "  thread-count="+"2");
		 mySuite.setName("NBCAutomation");
		//mySuite.setParallel("classes");
		//Create an instance of XmlTest and assign a name for it.
		 XmlTest myTest = new XmlTest(mySuite);
		 myTest.setName("NBCAutomation");
		 
		
		//Create a list which can contain the classes that you want to run.
		 List<XmlClass> myClasses = new ArrayList<XmlClass> ();
		 	Set set = htTestRunner.entrySet();
		    Iterator intIterator = set.iterator();
		    while (intIterator.hasNext()) 
		    {
		      Map.Entry entry = (Map.Entry) intIterator.next();
		      System.out.println(entry.getKey() + " : " + entry.getValue());
		      myClasses.add(new XmlClass((String) entry.getKey()));
		    //Assign that to the XmlTest Object created earlier.
				 myTest.setXmlClasses(myClasses);
				//Add any parameters that you want to set to the Test.
				 myTest.addParameter("Browser", (String) entry.getValue());
		    }
	
		 
		//Create a list of XmlTests and add the Xmltest you created earlier to it.
		 List<XmlTest> myTests = new ArrayList<XmlTest>();
		 myTests.add(myTest);
		 
		//add the list of tests to your Suite.
		 mySuite.setTests(myTests);
		 
		//Add the suite to the list of suites.
		 List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		 mySuites.add(mySuite);
		 
		//Set the list of Suites to the testNG object you created earlier.
		 myTestNG.setXmlSuites(mySuites);
		 
		 System.out.println(mySuites);
		//invoke run() - this will run your class.
		 myTestNG.run();
		 
		
	
	}

}
