package dev.codebase.gcj.sandbox;


public class OopExtA extends OopBase {

    // This variable shadows the one defined in parent class
    int x = 1;
    
    public OopExtA() {
        System.out.println(this.getClass().getSimpleName() + " constructor called... x : " + x);
        add(2);
    }
    
    @Override
    public void add(int input) {
        System.out.println(this.getClass().getSimpleName() + ".add() called with input : " + input);
        x += input * 2;
        System.out.println(this.getClass().getSimpleName() + ".add() x : " + x);
    }

    public static void printIt(OopBase b) {
        System.out.println("*** Value of x : " + b.x + "\n");
    }
    
    public static void main(String[] args) {
        System.out.println("*** Base:Base");
        OopBase bb = new OopBase();
        printIt(bb);
        printClass();
        
        System.out.println("*** Base:Extension");
        OopBase be = new OopExtA();
        printIt(be);
        printClass();

        System.out.println("*** Extension:Extension");
        OopExtA ee = new OopExtA();
        printIt(ee);
        printClass();
    }

}
