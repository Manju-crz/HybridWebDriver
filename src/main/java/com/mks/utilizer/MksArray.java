package com.mks.utilizer;

import java.util.ArrayList;
import java.util.List;

public class MksArray {
	
	
	public List<String> getArrayListFromArray(String... array) {
		
		List<String> arrayList = new ArrayList<String>();
		for(String str: array) {
			arrayList.add(str);
		}
		return arrayList;
	}
	
	public boolean isStringExistsInArrayIgnoringCase(String stringToFind, String... array) {
		
		for(String str: array) {
			if(str.equalsIgnoreCase(stringToFind))
				return true;
		}
		return false;
	}
	
	public boolean isStringExistsInArray(String stringToFind, String... array) {
		
		for(String str: array) {
			if(str.equals(stringToFind))
				return true;
		}
		return false;
	}
	
}
