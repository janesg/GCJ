package dev.codebase.gcj.sample;

public class EqualsHashCodeCompareTo implements Comparable<EqualsHashCodeCompareTo> {

    private int id;
    private String name;
    private long thingy;
    private double sum;
    
    public EqualsHashCodeCompareTo(int id, String name, long thingy, double sum) {
        super();
        this.id = id;
        this.name = name;
        this.thingy = thingy;
        this.sum = sum;
    }

    @Override
    public boolean equals(Object obj) {
        // Check for null object
        if (obj == null) return false;
        // Compare object references...same object must be equal
        if (obj == this) return true;        
        // Classes must match
        if (this.getClass() != obj.getClass()) return false;
        
        EqualsHashCodeCompareTo ehc = (EqualsHashCodeCompareTo) obj;
        
        return id == ehc.id 
               && (name != null && name.equals(ehc.name))
               && thingy == ehc.thingy
               && Double.doubleToLongBits(sum) == Double.doubleToLongBits(ehc.sum); 
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (int) (thingy ^ (thingy >>> 32));
        
        long tmp = Double.doubleToLongBits(sum);
        result = prime * result + (int) (tmp ^ (tmp >>> 32));
        
        return result;
    }

    @Override
    public int compareTo(EqualsHashCodeCompareTo o) {
        
        if (o == null) throw new NullPointerException("Input parameter is null");
        
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        
        if (this == o) return EQUAL;
        
        if (this.id < o.id) return BEFORE;
        if (this.id > o.id) return AFTER;
        
        int comparison = compareStrings(this.name, o.name);
        
        if (comparison != EQUAL) return comparison;
        
        if (this.thingy < o.thingy) return BEFORE;
        if (this.thingy > o.thingy) return AFTER;
        
        comparison = Double.compare(this.sum, o.sum);
        
        if (comparison != EQUAL) return comparison;

        // Got to here so compare as EQUAL
        // optional check that compatible with equals
        assert this.equals(o) : "compareTo not compatible with equals";
        
        return EQUAL;
    }    
    
    private int compareStrings(final String s1, final String s2) {
        if (s1 == s2) {
            //Nulls or exact equality
            return 0;
        } else if (s1 == null) {
            //s1 null and s2 not null, so s1 less
            return -1;
        } else if (s2 == null) {
            //s2 null and s1 not null, so s1 greater
            return 1;
        } else {
            return s1.compareTo(s2);
        }
    }
    
}
