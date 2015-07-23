package dev.codebase.gcj.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringPoolDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringPoolDemo.class.getName());
	
	public static void main(String[] args) {
		
		// Declare individual new String objects
        String strA = new String("eBay");
        String strB = new String("eBay");
        String strC = new String("Paypal");
 
        // Declare String literals which will reside in StringPool
        String strE = "amazon";
        String strF = "amazon";
        String strG = "facebook";
 
        // Create a String reference and assign an existing String's reference to it
        // so that both references point to the same String object in memory.
        String strD = strA;
        String strH = strE;
 
        // Reference Equality
        LOGGER.info("strA == strB equates to {}", strA == strB);
        assert !(strA == strB);	// This will be false - not using StringPool
        assert !(strA == strC);
        assert (strA == strD);
        LOGGER.info("strE == strF equates to {}", strE == strF);
        assert (strE == strF);	// This will be true - using StringPool
        assert !(strE == strG);
        assert (strE == strH);
        
        // Logical Equality
        assert (strA.equals(strB));
        assert !(strA.equals(strC));
        assert (strA.equals(strD));
        assert (strE.equals(strF));
        assert !(strE.equals(strG));
        assert (strE.equals(strH));

	}

}
