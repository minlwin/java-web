package com.jdc.data;

public record MyData(int data1, int data2) {
	public int getData1() {
		return data1;
	}
	
	public int getData2() {
		return data2;
	}
}
