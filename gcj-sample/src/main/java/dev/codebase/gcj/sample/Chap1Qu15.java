package dev.codebase.gcj.sample;

public class Chap1Qu15 {

	public String compress(String str) {
		
		if (str == null || str.length() < 2) {
			return str;
		}
		
		char[] letters = str.toCharArray();
		char current = letters[0];
		int count = 1;
		
		StringBuilder stb = new StringBuilder(String.valueOf(current));
		
		for (int i = 1; i < str.length(); i++) {
			if (letters[i] == current) {
				count++;
			} else {
				stb.append(String.valueOf(count));
				current = letters[i];
				stb.append(String.valueOf(current));
				count = 1;
			}
		}
		
		stb.append(String.valueOf(count));
		
		return stb.toString().length() < str.length() ? stb.toString() : str;
	}
}
