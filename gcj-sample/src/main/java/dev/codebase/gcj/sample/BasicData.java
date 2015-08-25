package dev.codebase.gcj.sample;

import java.util.Arrays;
import java.util.Collection;

import com.google.common.collect.HashMultiset;

public class BasicData implements Comparable<BasicData> {

    private int intA;
    private boolean boolB;
    private long longC;
    private double doubD;
    private String strE;
    private String[] strArrF;
    private Collection<Object> collG;
    
    public BasicData(int intA, boolean boolB, 
                     long longC, double doubD, 
                     String strE, String[] strArrF,
                     Collection<Object> collG) {
        super();
        this.intA = intA;
        this.boolB = boolB;
        this.longC = longC;
        this.doubD = doubD;
        this.strE = strE;
        this.strArrF = strArrF;
        this.collG = collG;
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) return true;

        boolean result = false;
        
        if (obj == null) return result;
        if (this.getClass() != obj.getClass()) return result;
        
        BasicData bd = (BasicData) obj;
        
        if (this.intA != bd.intA) return result;
        if (this.boolB != bd.boolB) return result;
        if (this.longC != bd.longC) return result;
        if (Double.doubleToLongBits(this.doubD) != Double.doubleToLongBits(bd.doubD)) return result;
        
        if ((this.strE != null && bd.strE == null) || 
            (this.strE == null && bd.strE != null)) return result;
        
        if (this.strE != null && !this.strE.equals(bd.strE)) return result;
        
        if ((this.strArrF != null && bd.strArrF == null) ||
            (this.strArrF == null && bd.strArrF != null)) return result;
        
        if (!Arrays.equals(this.strArrF, bd.strArrF)) return result;
        
        if ((this.collG != null && bd.collG == null) ||
            (this.collG == null && bd.collG != null)) return result;
        
        return HashMultiset.create(this.collG).equals(HashMultiset.create(bd.collG));
    }
    
    @Override 
    public int hashCode() {
        int prime = 31;
        
        int result = 1;
        
        result = result * prime + intA;
        result = result * prime + (boolB ? 1 : 0);
        result = result * prime + (int) (longC ^ (longC >>> 32));
        
        long temp = Double.doubleToLongBits(doubD);
        result = result * prime + (int) (temp ^ (temp >>> 32));
        
        result = result * prime + (strE != null ? strE.hashCode() : 0);
        
        result = result * prime + (strArrF != null ? Arrays.hashCode(strArrF) : 0);
        
        result = result * prime + (collG != null ? HashMultiset.create(collG).hashCode() : 0);
        
        return result;
    }
    
    public int compareTo(BasicData obj) {

        if (obj == null) throw new NullPointerException("Input parameter is null");
                
        final int EQUAL = 0;
        final int BEFORE = -1;
        final int AFTER = 1;
        
        if (this == obj) return EQUAL;
        
        if (this.intA < obj.intA) return BEFORE;
        if (this.intA > obj.intA) return AFTER;
        
        if (!this.boolB && obj.boolB) return BEFORE;
        if (this.boolB && !obj.boolB) return AFTER;
        
        if (this.longC < obj.longC) return BEFORE;
        if (this.longC > obj.longC) return AFTER;
        
        int compare = Double.compare(this.doubD, obj.doubD);
        if (compare != EQUAL) return compare;
        
        if (this.strE == null && obj.strE != null) return BEFORE;
        if (this.strE != null && obj.strE == null) return AFTER;
        
        if (this.strE != null && obj.strE != null) {
            compare = this.strE.compareTo(obj.strE);
            if (compare != EQUAL) return compare;
        }
        
        // Arrays and Collections don't implement Comparable
        // We could check for equality but what would concept of ordering mean ?

        return EQUAL;
    }
}
