package com.skoczylas.stockAnalyst;

import java.util.List;

import com.skoczylas.Analyst.DataAnalyst;
import com.skoczylas.CsvReader.CsvFileReader;
import com.skoczylas.CsvReader.Row;

/**
 * Main Application
 *
 */
public class App {
	public static void main(String[] args) {
		Double difference = readDifference(args);
		List<Row> dataToAnalysis = CsvFileReader.readFile();
		DataAnalyst da = new DataAnalyst();
		da.analyze(dataToAnalysis, difference);
		System.out.println("exit");
	}

	private static Double readDifference(String[] args) {
		if (0 == args.length) {
			System.out.println(
					"You need to pass 'X' difference as an argument for program e.g. 'java -jar stockAnalyst.jar 0.0070' ");
			System.out.println("Nothing was given, so default value will be used: 0.0098");
			return 0.0098;
		} else {
			try{
			System.out.println("The difference value used for analysis is: " + args[0]);
			return Double.valueOf(args[0]);
			}catch(NumberFormatException e){
				System.out.println("Incorrect value given, so default value will be used: 0.0098");
				return 0.0098;
			}
		}
	}
}
