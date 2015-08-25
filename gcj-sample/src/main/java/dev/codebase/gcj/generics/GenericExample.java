package dev.codebase.gcj.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericExample {

    public static void main(String[] args) {
        GenericExample ge = new GenericExample();
        //ge.assignSuperListToWildcardSub();
        //ge.assignSubListToWildcardSuper();
        
        ge.storeFruit(new ArrayList<Fruit>());
        //ge.storeApples(new ArrayList<Apple>());
    }
    
    public void assignSuperListToWildcardSub() {
        List<Fruit> fruits = new ArrayList<Fruit>();
        
        // Assign to list that can handle any element for which Apple is a super type
        // ....which should be Apple, Braeburn
        List<? super Apple> apples = fruits;
        //apples.add(new Fruit());
        //apples.add(new Cherry());
        apples.add(new Apple());
        apples.add(new Braeburn());
        System.out.println(apples);

        //Fruit f = apples.get(0);
        //Cherry c = apples.get(0);
        Apple a = (Apple) apples.get(0);
        Braeburn b = (Braeburn) apples.get(0);
    }
    
    public void assignSubListToWildcardSuper() {
        List<Apple> apples = new ArrayList<Apple>();
        
        // Assign to list that can handle any element for which Fruit is a sub type
        // ....which should be Food, Fruit
        List<? extends Fruit> fruits = apples;
        //fruits.add(new Food());
        //fruits.add(new Fruit());
        //fruits.add(new Cherry());
        //fruits.add(new Apple());
        //fruits.add(new Braeburn());
        System.out.println(apples);
    }
    
    // Consumer (add) --> super
    // A collection that can handle all elements for which Fruit is a super type
    public void storeFruit(Collection<? super Fruit> c) {
        //c.add(new Food());
        c.add(new Fruit());
        c.add(new Cherry());
        c.add(new Apple());
        c.add(new Braeburn());
        System.out.println(c);
    }


/*    
    public <E extends ? super Fruit> void storeFruit2(List<E> c) {
        c.add(new Fruit());
        c.add(new Cherry());
        c.add(new Apple());
        c.add(new Braeburn());
        System.out.println(c);
    }
    
    
    public <E extends Apple> void storeApples(Collection<E> c) {
        c.add(new Fruit());
        c.add(new Cherry());
        c.add(new Apple());
        c.add(new Braeburn());
        System.out.println(c);
    }
*/
    
    private static interface Edible {
    }
    
    private static class Food implements Edible {
    }
    
    private static class Fruit extends Food {
    }
    
    private static class Apple extends Fruit {
    }
    
    private static class Braeburn extends Apple {
    }
    
    private static class Cherry extends Fruit {
    }
}
