package dev.codebase.gcj.sandbox;


public class OopBase {

    int x = 0;
    
    static int y = 0;
    
    public OopBase() {
        System.out.println(this.getClass().getSimpleName() + " constructor called... x : " + x);
        add(3);
    }

    public void add(int input) {
        System.out.println(this.getClass().getSimpleName() + ".add() called with input : " + input);
        x += input;
        System.out.println(this.getClass().getSimpleName() + ".add() x : " + x);
    }

    public static void printClass() {
        System.out.println("Class is : " + new Object() { }.getClass().getEnclosingClass().getSimpleName());
    }

}
