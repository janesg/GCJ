package dev.codebase.gcj.sample;

import java.util.HashSet;
import java.util.Set;

public class Chap1Qu11 {

	public boolean containsAllUniqueChars(String str) {
		
		if (str == null || str.length() < 2) {
			return true;
		}
		
		for (int i = 0 ; i < str.length(); i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean containsAllUniqueChars2(String str) {
		
		if (str == null || str.length() < 2) {
			return true;
		}
		
		Set<Integer> elems = new HashSet<Integer>();
		
		for (int i = 0; i < str.length(); i++) {
			if (!elems.add(new Integer(str.charAt(i)))) {
				return false;
			}
		}
		
		return true;
	}
	
}
