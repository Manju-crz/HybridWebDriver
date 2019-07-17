package com.mks.utilizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MksArray {
	
	
	public static List<String> getArrayListFromArray(String... array) {
		
		List<String> arrayList = new ArrayList<String>();
		for(String str: array) {
			arrayList.add(str);
		}
		return arrayList;
	}
	
	public static boolean isStringExistsInArrayIgnoringCase(String stringToFind, String... array) {
		
		for(String str: array) {
			if(str.equalsIgnoreCase(stringToFind))
				return true;
		}
		return false;
	}
	
	public static boolean isStringExistsInArray(String stringToFind, String... array) {
		
		for(String str: array) {
			if(str.equals(stringToFind))
				return true;
		}
		return false;
	}
	
	
	public static int getLargest(int ... intValues ) {
		Arrays.sort(intValues);
		int arraySize = intValues.length;
		return intValues[arraySize-1];
	}
	
	
	public static List<Integer> getArraysAsList(int ... intValues ){

		List<Integer> arrayList = new ArrayList<Integer>();
		for(Integer str: intValues) {
			arrayList.add(str);
		}
		return arrayList;
	}
	
}
