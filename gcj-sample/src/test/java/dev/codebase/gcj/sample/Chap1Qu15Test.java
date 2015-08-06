package dev.codebase.gcj.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class Chap1Qu15Test {

	@Test
	public void testCompress() {
		Chap1Qu15 qu = new Chap1Qu15();
		
		assertNull(qu.compress(null));
		assertEquals("", qu.compress(""));
		assertEquals("a", qu.compress("a"));
		assertEquals("a10", qu.compress("aaaaaaaaaa"));
		assertEquals("abcde", qu.compress("abcde"));
		assertEquals("a2b1c5a3", qu.compress("aabcccccaaa"));
	}

}
