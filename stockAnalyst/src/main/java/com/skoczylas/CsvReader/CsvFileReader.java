package com.skoczylas.CsvReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CsvFileReader {

	public static List<Row>  readFile(){
		List<Row> rows = new ArrayList<Row>();
		try {
			CSVReader reader = new CSVReader(new FileReader("EURUSD240.csv"), ',');
			 String [] nextLine; 
		     while ((nextLine = reader.readNext()) != null) {
		        // nextLine[] is an array of values from the line
		    	 rows.add(new Row(Double.valueOf(nextLine[3]), Double.valueOf(nextLine[4])));
		        //System.out.println("Adding Row HIGH: " + Double.valueOf(nextLine[3]) +" LOW: " +Float.valueOf(nextLine[4]));
		     }
		     reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}
}
