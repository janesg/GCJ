package dev.codebase.gcj.sample;

import org.junit.Test;

import junit.framework.Assert;

public class Chap1Qu11Test {
	
	@Test
	public void testContainsAllUniqueChars() {
		Chap1Qu11 qu = new Chap1Qu11();
		
		Assert.assertTrue(qu.containsAllUniqueChars(null));
		Assert.assertTrue(qu.containsAllUniqueChars("ab"));
		Assert.assertFalse(qu.containsAllUniqueChars("aa"));
		Assert.assertTrue(qu.containsAllUniqueChars("abcdef"));
		Assert.assertTrue(qu.containsAllUniqueChars("aAbBcC"));
		Assert.assertFalse(qu.containsAllUniqueChars("aAbBcc"));
		
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
		Assert.assertTrue(qu.containsAllUniqueChars2(null));
		Assert.assertTrue(qu.containsAllUniqueChars2("ab"));
		Assert.assertFalse(qu.containsAllUniqueChars2("aa"));
		Assert.assertTrue(qu.containsAllUniqueChars2("abcdef"));
		Assert.assertTrue(qu.containsAllUniqueChars2("aAbBcC"));
		Assert.assertFalse(qu.containsAllUniqueChars2("aAbBcc"));
	}

}
