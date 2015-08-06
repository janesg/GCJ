package dev.codebase.gcj.sample;

import static org.junit.Assert.*;
import static dev.codebase.gcj.sample.Dog.Colour.*;

import java.util.Date;

import org.junit.Test;

public class DogBuilderTest {

    @Test
    public void buildDogWithNoName() {
        Dog.DogBuilder db = new Dog.DogBuilder(null, null);
        
        Dog dog = null;
        
        try {
            dog = db.build();
            fail("Expected method to throw IllegalArgumentException, but didn't");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Name is null or empty"));
        } catch (Exception e2) {
            fail("Threw some unexpected exception type: " + e2.getClass().getName());            
        }
        
        System.out.println(dog != null ? dog.toString() : "Dog is NULL");
    }

    @Test
    public void buildUnbornDog() {
        Dog.DogBuilder db = new Dog.DogBuilder("Twinkle", null);
        
        Dog dog = null;
        
        try {
            dog = db.build();
            fail("Expected method to throw IllegalArgumentException, but didn't");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Date of birth is null"));
        } catch (Exception e2) {
            fail("Threw some unexpected exception type: " + e2.getClass().getName());            
        }
        
        System.out.println(dog != null ? dog.toString() : "Dog is NULL");
    }
    
    @Test
    public void buildBasicDog() {
        Date dob = new Date();
        Dog.DogBuilder db = new Dog.DogBuilder("Doggie", dob);
        
        Dog dog = db.build();
        
        assertNotNull(dog);
        assertTrue(dog.getName().equals("Doggie"));
        assertTrue(dog.getDateOfBirth().equals(dob));
        assertTrue(dog.getNumLegs() == 4);
        
        System.out.println(dog);
    }
    
    @Test
    public void testInvalidBuilder() {
        Date dob = new Date();
        Dog.DogBuilder db = new Dog.DogBuilder("Doggie", dob);
        
        Dog dog = db.build();
        
        assertNotNull(dog);
        assertTrue(dog.getName().equals("Doggie"));
        assertTrue(dog.getDateOfBirth().equals(dob));
        assertTrue(dog.getNumLegs() == 4);

        System.out.println(dog);
        
        // Try to reuse the same builder once built has been called
        db.setColour(BRINDLE);
        
        try {
            dog = db.build();
            fail("Expected method to throw IllegalStateException, but didn't");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().equals("Invalid to reuse a builder once build invoked"));
        } catch (Exception e2) {
            fail("Threw some unexpected exception type: " + e2.getClass().getName());            
        }
        
    }

    @Test
    public void buildWhiteDog() {
        Date dob = new Date();
        Dog.DogBuilder db = new Dog.DogBuilder("Snowie", dob);
        
        Dog dog = db.setColour(WHITE).build();
        
        assertNotNull(dog);
        assertTrue(dog.getName().equals("Snowie"));
        assertTrue(dog.getDateOfBirth().equals(dob));
        assertTrue(dog.getColour().equals(WHITE));
        assertTrue(dog.getNumLegs() == 4);

        System.out.println(dog);
    }
    
    @Test
    public void buildTanDogWith3Legs() {
        Date dob = new Date();
        Dog.DogBuilder db = new Dog.DogBuilder("Oscar", dob);
        
        Dog dog = db.setColour(TAN).setNumLegs(3).build();
        
        assertNotNull(dog);
        assertTrue(dog.getName().equals("Oscar"));
        assertTrue(dog.getDateOfBirth().equals(dob));
        assertTrue(dog.getColour().equals(TAN));
        assertTrue(dog.getNumLegs() == 3);

        System.out.println(dog);
    }
    
}
