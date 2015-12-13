package com.skoczylas.CsvReader;

public class Row {
	private Double high;
	private Double low;

	Row(Double high, Double low){
		this.high = high;
		this.low = low;
	}
	public Double getHigh() {
		return high;
	}

	public Double getLow() {
		return low;
	}

}
