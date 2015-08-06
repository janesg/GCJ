package dev.codebase.gcj.sample;

public class LinkedList<T> {

    private Node<T> head;
    private int count;
    
    public LinkedList() {
        this.head = new Node<T>(null);
        this.count = 0;
    }
    
    public int size() {
        return count;
    }
    
    public boolean add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Attempting to add data but specified argument as NULL");
        }
        
        // Add to end of list
        if (count == 0) {
            head.data = data;
        } else {
            Node<T> n = head;
            
            while (n.next != null) {
                n = n.next;
            }
            
            n.next = new Node<T>(data);
            n.next.prev = n;
        }
        
        count++;
        
        return true;
    }
    
    public void add(int index, T data) {        
        if (data == null) {
            throw new IllegalArgumentException("Attempting to add data but specified argument as NULL");
        }
        
        if (index > count) {
            throw new IndexOutOfBoundsException("Attempting to add data at index [" + 
                                                index + "] when list has [" + count + "] element(s)");
        }
        
        Node<T> n = head;
        
        for (int i = 0; i < index && n.next != null; i++) {
            n = n.next;
        }
        
        Node<T> newN = new Node<T>(data);
        
        // Insert data at index and shift everything (from that point) along
        // Point previous to newN
        if (n.prev != null) {
            n.prev.next = newN;
            newN.prev = n.prev;
        }
        
        // Point newN to existing & visa versa
        newN.next = n;
        n.prev = newN;
        
        count++;        
    }
    
    public T get(int index) {        
        if (index > count) {
            throw new IndexOutOfBoundsException("Attempting to get data at index [" + 
                                                index + "] when list has [" + count + "] element(s)");
        }
        
        Node<T> n = head;
        
        for (int i = 0; i < index && n.next != null; i++) {
            n = n.next;
        }
        
        return n.data;
    }
    
    public T remove(int index) {
        if (index >= count) {
            throw new IndexOutOfBoundsException("Attempting to remove data at index [" + 
                                                index + "] when list has [" + count + "] element(s)");
        }
        
        Node<T> n = head;
        
        for (int i = 0; i < index && n.next != null; i++) {
            n = n.next;
        }
        
        // Point previous to next
        if (n.prev != null) {
            n.prev.next = n.next;
        } else {
            // Removing first element so reset head to next
            head = n.next;
        }
                
        if (n.next != null) {
            n.next.prev = n.prev;
        }
        
        count--;
        
        return n.data;
    }
    
    public boolean remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Attempting to remove data but specified argument as NULL");
        }
        
        Node<T> n = head;
        Node<T> dataNode = null;
        
        if (data.equals(n.data)) {
            dataNode = n;
        }
        
        // Find first node that matches data (if it exists)
        while (dataNode == null && n.next != null) {
            n = n.next;
            if (data.equals(n.data)) {
                dataNode = n;
            }
        }
        
        if (dataNode == null) {
            return false;
        }
        
        // Point previous to next
        if (dataNode.prev != null) {
            dataNode.prev.next = dataNode.next;
        } else {
            // Removing first element so reset head to next
            head = dataNode.next;
        }
                
        if (dataNode.next != null) {
            dataNode.next.prev = dataNode.prev;
        }
        
        count--;
        
        return true;
    }
    
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Attempting to find data but specified argument as NULL");
        }
        
        Node<T> n = head;
        Node<T> dataNode = null;
        
        if (data.equals(n.data)) {
            dataNode = n;
        }
        
        // Find first node that matches data (if it exists)
        while (dataNode == null && n.next != null) {
            n = n.next;
            if (data.equals(n.data)) {
                dataNode = n;
            }
        }
        
        return dataNode != null;
    }
    
    public void clear() {
        head = null;
        count = 0;
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public int indexOf(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Attempting to find data but specified argument as NULL");
        }
        
        Node<T> n = head;
        Node<T> dataNode = null;
        int index = -1;
        
        if (data.equals(n.data)) {
            dataNode = n;
            index = 0;
        }
        
        // Find first node that matches data (if it exists)
        for (int i = 1; dataNode == null && n.next != null; i++) {
            n = n.next;
            if (data.equals(n.data)) {
                dataNode = n;
                index = i;
            }
        }
        
        return index;
    }
    
    // Declaring static nested class means we can reuse the same type parameter T as the enclosing class
    // If we were accessing outer class attributes then this would have to be an inner class
    // and must use alternative type parameter from outer class otherwise get the compiler warning:
    //  'Type parameter T is hiding the type T'
    private static class Node<T> {
        Node<T> next;
        Node<T> prev;
        T data;
        
        Node (T data) {            
            this.data = data;
        }
    }
}
