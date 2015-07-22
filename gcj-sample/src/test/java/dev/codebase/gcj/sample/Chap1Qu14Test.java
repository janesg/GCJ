package dev.codebase.gcj.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class Chap1Qu14Test {

	@Test
	public void testSpaceReplace() {
		Chap1Qu14 qu = new Chap1Qu14();
		
		assertEquals("%20", qu.spaceReplace("   "));
		assertEquals("%20Bob", qu.spaceReplace(" Bob  "));
		assertEquals("%20Bob%20", qu.spaceReplace(" Bob     "));
		assertEquals("Mr%20John%20Smith", qu.spaceReplace("Mr John Smith    "));
	}

}
