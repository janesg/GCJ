package dev.codebase.gcj.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testLinkedList() {
        LinkedList<Integer> intList = new LinkedList<Integer>();
        assertTrue(intList.size() == 0);
    }

    @Test
    public void testAddT() {
        LinkedList<Integer> intList = new LinkedList<Integer>();
        intList.add(new Integer(1));
        assertTrue(intList.size() == 1);
    }

    @Test
    public void testAddIntT() {
        LinkedList<Integer> intList = new LinkedList<Integer>();
        intList.add(0, new Integer(1));
        assertTrue(intList.size() == 1);
        
        intList.add(0, new Integer(2));
        assertTrue(intList.size() == 2);
        
        intList.add(1, new Integer(3));
        assertTrue(intList.size() == 3);
        
        try {
            intList.add(4, new Integer(4));
            fail("Expected method to throw IndexOutOfBoundsException, but didn't" );
        } catch (IndexOutOfBoundsException e) {
            assertTrue(e.getMessage().equals("Attempting to add data at index [4] when list has [3] element(s)"));
        }        
    }

    @Test
    public void testGet() {        
        LinkedList<Integer> intList = new LinkedList<Integer>();
        
        assertNull(intList.get(0));
                
        Integer two = new Integer(2);
        intList.add(new Integer(1));
        intList.add(two);        
        intList.add(new Integer(3));
        assertTrue(intList.size() == 3);
        
        assertTrue(intList.get(1) == two);
        
        try {
            intList.get(4);
            fail("Expected method to throw IndexOutOfBoundsException, but didn't" );
        } catch (IndexOutOfBoundsException e) {
            assertTrue(e.getMessage().equals("Attempting to get data at index [4] when list has [3] element(s)"));
        }        
    }

    @Test
    public void testRemoveInt() {
        LinkedList<Integer> intList = new LinkedList<Integer>();
        
        try {
            intList.remove(0);
            fail("Expected method to throw IndexOutOfBoundsException, but didn't" );
        } catch (IndexOutOfBoundsException e) {
            assertTrue(e.getMessage().equals("Attempting to remove data at index [0] when list has [0] element(s)"));
        }
        
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        intList.add(one);
        intList.add(two);        
        intList.add(three);
        assertTrue(intList.size() == 3);
        
        assertTrue(intList.remove(0) == one);
        assertTrue(intList.remove(1) == three);
        assertTrue(intList.size() == 1);

        try {
            intList.get(4);
            fail("Expected method to throw IndexOutOfBoundsException, but didn't" );
        } catch (IndexOutOfBoundsException e) {
            assertTrue(e.getMessage().equals("Attempting to get data at index [4] when list has [1] element(s)"));
        }        
    }

    @Test
    public void testRemoveT() {
        LinkedList<Integer> intList = new LinkedList<Integer>();
        
        try {
            intList.remove(null);
            fail("Expected method to throw IllegalArgumentException, but didn't");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Attempting to remove data but specified argument as NULL"));
        }
        
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        intList.add(one);
        intList.add(two);        
        intList.add(three);
        assertTrue(intList.size() == 3);
        
        assertTrue(intList.remove(two));
        assertTrue(intList.remove(one));
        assertTrue(intList.size() == 1);

        assertFalse(intList.remove(two));
    }

    @Test
    public void testContains() {
        LinkedList<Integer> intList = new LinkedList<Integer>();
        
        try {
            intList.contains(null);
            fail("Expected method to throw IllegalArgumentException, but didn't");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Attempting to find data but specified argument as NULL"));
        }
        
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        intList.add(one);
        intList.add(two);        
        intList.add(three);
        assertTrue(intList.size() == 3);
        
        assertTrue(intList.contains(two));
        assertFalse(intList.contains(new Integer(4)));
    }

    @Test
    public void testClear() {
        LinkedList<Integer> intList = new LinkedList<Integer>();
        
        intList.add(new Integer(1));
        intList.add(new Integer(2));        
        intList.add(new Integer(3));
        assertTrue(intList.size() == 3);

        intList.clear();
        
        assertTrue(intList.size() == 0);        
    }

    @Test
    public void testIsEmpty() {
        LinkedList<Integer> intList = new LinkedList<Integer>();
        
        assertTrue(intList.isEmpty());
        
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        intList.add(one);
        intList.add(two);        
        intList.add(three);
        assertTrue(intList.size() == 3);
        
        assertFalse(intList.isEmpty());
        
        intList.remove(0);
        intList.remove(0);
        intList.remove(0);
        
        assertTrue(intList.isEmpty());
    }

    @Test
    public void testIndexOf() {
        LinkedList<Integer> intList = new LinkedList<Integer>();
        
        try {
            intList.indexOf(null);
            fail("Expected method to throw IllegalArgumentException, but didn't");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Attempting to find data but specified argument as NULL"));
        }
        
        Integer one = new Integer(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        intList.add(one);
        intList.add(two);        
        intList.add(three);
        assertTrue(intList.size() == 3);
        
        assertTrue(intList.indexOf(two) == 1);
        assertTrue(intList.indexOf(new Integer(4)) == -1);
    }

}
