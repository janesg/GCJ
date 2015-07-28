package dev.codebase.gcj.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitWise {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitWise.class);
    
    public static void main(String args[]) {
        int a = 60;     /* 60 =  0011 1100 */
        int b = 13;     /* 13 =  0000 1101 */
        int c = -13;    /* -13 = 1111 0011 */
        int d = 0;

        d = a & b;      /* 12 =  0000 1100 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("b = 13 :       0000 0000 0000 0000 0000 0000 0000 1101");
        LOGGER.info("a & b  :       0000 0000 0000 0000 0000 0000 0000 1100");
        LOGGER.info("bitwise AND : a & b = " + d + "\n");

        d = a | b;      /* 61 =  0011 1101 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("b = 13 :       0000 0000 0000 0000 0000 0000 0000 1101");
        LOGGER.info("a | b  :       0000 0000 0000 0000 0000 0000 0011 1101");
        LOGGER.info("bitwise inclusive OR : a | b = " + d + "\n");

        d = a ^ b;      /* 49 =  0011 0001 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("b = 13 :       0000 0000 0000 0000 0000 0000 0000 1101");
        LOGGER.info("a ^ b  :       0000 0000 0000 0000 0000 0000 0011 0001");
        LOGGER.info("bitwise exclusive OR : a ^ b = " + d + "\n");

        d = ~a;         /* -61 = 1100 0011 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("~a     :       1111 1111 1111 1111 1111 1111 1100 0011");
        LOGGER.info("bitwise complement : ~a = " + d + "\n");

        d = a << 2;     /* 240 = 1111 0000 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("a << 2 :       0000 0000 0000 0000 0000 0000 1111 0000");
        LOGGER.info("signed left shift : a << 2 = " + d + "\n");

        d = a >> 2;     /* 15 =  0000 1111 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("a >> 2 :       0000 0000 0000 0000 0000 0000 0000 1111");
        LOGGER.info("signed right shift : a >> 2  = " + d + "\n");

        d = a >>> 2;    /* 15 =  0000 1111 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("a >>> 2:       0000 0000 0000 0000 0000 0000 0000 1111");
        LOGGER.info("unsigned right shift : a >>> 2 = " + d + "\n");

        d = a & c;      /* 48 =  0011 0000 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("c = -13:       1111 1111 1111 1111 1111 1111 1111 0011");
        LOGGER.info("a & c  :       0000 0000 0000 0000 0000 0000 0011 0000");
        LOGGER.info("bitwise AND : a & c = " + d + "\n");

        d = a | c;      /* -1 =  1111 1111 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("c = -13:       1111 1111 1111 1111 1111 1111 1111 0011");
        LOGGER.info("a | c  :       1111 1111 1111 1111 1111 1111 1111 1111");
        LOGGER.info("bitwise inclusive OR : a | c = " + d + "\n");

        d = a ^ c;      /* -49 =  1100 1111 */
        LOGGER.info("a = 60 :       0000 0000 0000 0000 0000 0000 0011 1100");
        LOGGER.info("c = -13:       1111 1111 1111 1111 1111 1111 1111 0011");
        LOGGER.info("a ^ c  :       1111 1111 1111 1111 1111 1111 1100 1111");
        LOGGER.info("bitwise exclusive OR : a ^ c = " + d + "\n");

        d = ~c;         /* 12 = 0000 1100 */
        LOGGER.info("c = -13:       1111 1111 1111 1111 1111 1111 1111 0011");
        LOGGER.info("~c     :       0000 0000 0000 0000 0000 0000 0000 1100");
        LOGGER.info("bitwise complement : ~c = " + d + "\n");

        d = c << 2;     /* -52  = 1100 1100 */
        LOGGER.info("c = -13:       1111 1111 1111 1111 1111 1111 1111 0011");
        LOGGER.info("c << 2 :       1111 1111 1111 1111 1111 1111 1100 1100");
        LOGGER.info("signed left shift : c << 2 = " + d + "\n");

        d = c >> 2;     /* -4 =  1111 1100 */
        LOGGER.info("c = -13:       1111 1111 1111 1111 1111 1111 1111 0011");
        LOGGER.info("c >> 2 :       1111 1111 1111 1111 1111 1111 1111 1100");
        LOGGER.info("signed right shift : c >> 2  = " + d + "\n");

        d = c >>> 2;    /* 1073741820 =  1111 1100 */
        LOGGER.info("c = -13:       1111 1111 1111 1111 1111 1111 1111 0011");
        LOGGER.info("c >>> 2:       0011 1111 1111 1111 1111 1111 1111 1100");
        LOGGER.info("unsigned right shift : c >>> 2 = " + d + "\n");
    }
}
