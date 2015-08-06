package dev.codebase.gcj.sample;

public class Chap1Qu14 {

	public String spaceReplace(String str) {
		
		char[] chars = str.toCharArray();
		
		for (int i = 0; i < str.length(); i++) {
			if (chars[i] == ' ') {
				for (int j = str.length() - 1; j > i + 2; j--) {
					chars[j] = chars[j-2];
				}
				
				chars[i] = '%';
				chars[i+1] = '2';
				chars[i+2] = '0';
			}
		}
		
		return String.valueOf(chars);
	}
}
