package dev.codebase.gcj.sample;

import java.util.Date;

// This is an immutable class
public class Dog {

    // Mandatory
    private String name;
    private long dateOfBirth;       // store date as immutable type
    
    // Optional
    private Colour colour;
    private Integer numLegs;        // use wrapper object to detect if optional field set

    // Private constructor takes instance of its builder
    private Dog(DogBuilder db) {
        super();
        this.name = db.name;
        this.dateOfBirth = db.dateOfBirth;
        this.colour = db.colour;
        this.numLegs = db.numLegs;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        // Builder will not allow dateOfBirth to be NULL, so no need to check
        return new Date(dateOfBirth);
    }

    public Colour getColour() {
        // This optional attribute could be NULL
        return colour;
    }

    public int getNumLegs() {
        // Builder assigns this attribute a sensible default value
        return numLegs;
    }
    
    @Override
    public String toString() {
        return "Dog [name=" + name + ", " + 
                "dateOfBirth=" + new Date(dateOfBirth) + ", " +
                (colour != null ? "colour=" + colour + ", " : "") + 
                "numLegs=" + numLegs + "]";
    }

    public static enum Colour {
        BLACK,
        WHITE,
        TAN,
        BRINDLE;
    }
    
    public static class DogBuilder implements Builder<Dog> {
        
        private boolean useable;
        private String name;
        private Long dateOfBirth;
        private Colour colour;
        private Integer numLegs = new Integer(4);

        // Builder constructor takes built object's mandatory attribute values
        public DogBuilder(String name, Date dateOfBirth) {
            this.name = name;
            this.dateOfBirth = (dateOfBirth != null ? dateOfBirth.getTime() : null);
            this.useable = true;
        }
        
        public DogBuilder setColour(Colour colour) {
            this.colour = colour;
            return this;
        }

        public DogBuilder setNumLegs(int numLegs) {
            this.numLegs = numLegs;
            return this;
        }

        public Dog build() {
            if (!useable) {
                throw new IllegalStateException("Invalid to reuse a builder once build invoked");                
            } else {
                this.useable = false;
            }
            
            if (name == null || name.trim().length() == 0) {
                throw new IllegalArgumentException("Name is null or empty");
            }
            
            if (dateOfBirth == null) {
                throw new IllegalArgumentException("Date of birth is null");
            }
            
            return new Dog(this);
        }
    }
}
