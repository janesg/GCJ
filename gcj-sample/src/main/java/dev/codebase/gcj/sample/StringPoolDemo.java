package dev.codebase.gcj.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringPoolDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringPoolDemo.class);
	
	public static void main(String[] args) {
		
	    // Declare String literal
	    String strLit = "eBay";
	    
		// Declare individual new String objects (some with same value as literal)
        String strA = new String("eBay");
        String strB = new String("eBay");
        String strC = new String("Paypal");
 
        // Proof that even though literal exists, the other objects with same value are
        // not referencing same String object from String Pool
        LOGGER.info("strA == strLit equates to {}", strA == strLit);

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
