package dev.codebase.gcj.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class Chap1Qu13Test {

	@Test
	public void testIsPermutation() {
		Chap1Qu13 qu = new Chap1Qu13();
		
		assertFalse(qu.isPermutation(null, null));
		assertFalse(qu.isPermutation(null, "yftfy"));
		assertFalse(qu.isPermutation("guwydg", null));
		assertFalse(qu.isPermutation("ggiuq", "ygys"));
		assertFalse(qu.isPermutation("s", "g"));
		assertTrue(qu.isPermutation("s", "s"));
		assertFalse(qu.isPermutation("fish", "ship"));
		assertTrue(qu.isPermutation("dogs", "gods"));
	}

}
