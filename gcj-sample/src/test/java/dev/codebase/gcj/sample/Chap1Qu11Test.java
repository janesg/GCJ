package dev.codebase.gcj.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class Chap1Qu11Test {
	
	@Test
	public void testContainsAllUniqueChars() {
		Chap1Qu11 qu = new Chap1Qu11();
		
		assertTrue(qu.containsAllUniqueChars(null));
		assertTrue(qu.containsAllUniqueChars("ab"));
		assertFalse(qu.containsAllUniqueChars("aa"));
		assertTrue(qu.containsAllUniqueChars("abcdef"));
		assertTrue(qu.containsAllUniqueChars("aAbBcC"));
		assertFalse(qu.containsAllUniqueChars("aAbBcc"));
		
		assert qu.containsAllUniqueChars(null);
		assert qu.containsAllUniqueChars("ab");
		assert !qu.containsAllUniqueChars("aa");
		assert qu.containsAllUniqueChars("abcdef");
		assert qu.containsAllUniqueChars("aAbBcC");
		assert !qu.containsAllUniqueChars("aAbBcc");		
	}

	@Test
	public void testContainsAllUniqueChars2() {
		Chap1Qu11 qu = new Chap1Qu11();
		
		assertTrue(qu.containsAllUniqueChars2(null));
		assertTrue(qu.containsAllUniqueChars2("ab"));
		assertFalse(qu.containsAllUniqueChars2("aa"));
		assertTrue(qu.containsAllUniqueChars2("abcdef"));
		assertTrue(qu.containsAllUniqueChars2("aAbBcC"));
		assertFalse(qu.containsAllUniqueChars2("aAbBcc"));
	}

}
