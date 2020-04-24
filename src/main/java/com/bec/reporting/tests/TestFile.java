package com.bec.reporting.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestFile {
	public static void main(String args[]) {
		 File fileToBeModified = new File("C:\\Users\\htikkas\\Downloads\\DandD_item_218130.xml");
         
	        String oldContent = "";
	         
	        BufferedReader reader = null;
	         
	        FileWriter writer = null;
	         
	        try
	        {
	            reader = new BufferedReader(new FileReader(fileToBeModified));
	             
	            //Reading all the lines of input text file into oldContent
	             
	            String line = reader.readLine();
	             
	            while (line != null) 
	            {
	                oldContent = oldContent + line + System.lineSeparator();
	                 
	                line = reader.readLine();
	            }
	             
	            //Replacing oldString with newString in the oldContent
	             
	            String newContent = oldContent.replaceAll("Unicode (UTF-8)", "UTF-8");
	             
	            //Rewriting the input text file with newContent
	             
	            writer = new FileWriter(fileToBeModified);
	             
	            writer.write(newContent);
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            try
	            {
	                //Closing the resources
	                 
	                reader.close();
	                 
	                writer.close();
	            } 
	            catch (IOException e) 
	            {
	                e.printStackTrace();
	            }
	        }
	}
}
