package dev.codebase.gcj.sample;

import java.util.Arrays;

public class Chap1Qu13 {

	public boolean isPermutation(String str1, String str2) {
		
		if (str1 == null || str2 == null || str1.length() != str2.length()) {
			return false;
		}
		
		char[] str1Chars = str1.toCharArray();
		char[] str2Chars = str2.toCharArray();
		
		Arrays.sort(str1Chars);
		Arrays.sort(str2Chars);
		
		return Arrays.equals(str1Chars, str2Chars);
	}
}
