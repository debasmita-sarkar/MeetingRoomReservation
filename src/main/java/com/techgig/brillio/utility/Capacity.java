package com.techgig.brillio.utility;

public enum Capacity {
	Small(4),
	Big(8);

	Capacity(int i) {
		this.size=i;
	}
	private int size; 	
   
   	public int getCapacity() {
   		return size;
   	}

}
