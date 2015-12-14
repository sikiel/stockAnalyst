package com.skoczylas.Analyst;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.skoczylas.CsvReader.Row;

import au.com.bytecode.opencsv.CSVWriter;

public class DataAnalyst {
	Double high;
	Double low;
	int highIndex, lowIndex;
	Double difference;
	Boolean search = true;

	public void analyze(List<Row> dataToAnalysis, Double difference) {
		this.difference = difference;
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter("result.csv"), ',', CSVWriter.NO_QUOTE_CHARACTER);
			int index = initData(dataToAnalysis, writer);
			while (search) {
				index = searchHigh(dataToAnalysis, index);
				String out = high.toString().concat(",").concat(String.valueOf(highIndex));
				index = searchLow(dataToAnalysis, index);
				out = out + "," + low.toString() + "," + String.valueOf(lowIndex);
				String[] entries = out.split(",");
				writer.writeNext(entries);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int initData(List<Row> dataToAnalysis, CSVWriter writer) {
		high = dataToAnalysis.get(0).getHigh();
		low = dataToAnalysis.get(0).getLow();
		int index = 0;
		String header = "high,indexH,low,indexL";
		String[] head = header.split(",");
		writer.writeNext(head);
		return index;
	}

	private int searchLow(List<Row> dataToAnalysis, int index) {
		lowIndex = index;
		low = dataToAnalysis.get(index).getLow();
		boolean notFound = true;
		while (notFound) {
			index++;
			if (index < dataToAnalysis.size()) {
				if (dataToAnalysis.get(index).getLow() < low) {
					low = dataToAnalysis.get(index).getLow();
					lowIndex = index;
				}
				if (dataToAnalysis.get(index).getHigh() - low >= difference) {
					notFound = false;
				}
			} else {
				search = false;
				notFound = false;
			}
		}
		return index;
	}

	private int searchHigh(List<Row> dataToAnalysis, int index) {
		highIndex = index;
		high = dataToAnalysis.get(index).getHigh();
		boolean notFound = true;
		while (notFound) {
			index++;
			if (index < dataToAnalysis.size()) {
				if (dataToAnalysis.get(index).getHigh() > high) {
					high = dataToAnalysis.get(index).getHigh();
					highIndex = index;
				}
				if (high - dataToAnalysis.get(index).getLow() >= difference) {
					notFound = false;
				}
			} else {

				notFound = false;
			}
		}
		return index;
	}

}
