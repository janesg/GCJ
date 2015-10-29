package dev.codebase.gcj.sandbox;


public class NewAbstract {

    public static void main(String[] args) {
        
        // The fact that the body of the class is defined on call to constructor
        // means we are creating an anonymous subclass of the abstract class
        // ...we are not instantiating the anonymous class itself...which is not allowed
        AwesomeAbstractStatic oneStatic = new AwesomeAbstractStatic() {
            int i = 5;
            
            public void doSomething() {
                System.out.println("Impl static: i = " + i);
            }
            
            public void doSomething(String... params) {
                System.out.println("VarArg Impl static");
            }
        };

        oneStatic.doSomething();

        NewAbstract.AwesomeAbstractInstance twoInstance = new NewAbstract().new AwesomeAbstractInstance() {
            int i = 7;
            
            public void doSomething() {
                System.out.println("Impl instance: i = " + i);
            }
            
            public void doSomething(String... params) {
                System.out.println("VarArg Impl instance");
            }
        };

        twoInstance.doSomething();

     }

     private static abstract class AwesomeAbstractStatic {
         int i = 1;
         
         public void doSomething() {
             System.out.println("I am abstract static");
         }
     }
     
     private abstract class AwesomeAbstractInstance {
         int i = 2;
         
         public void doSomething() {
             System.out.println("I am abstract instance");
         }
     }    
     
}
