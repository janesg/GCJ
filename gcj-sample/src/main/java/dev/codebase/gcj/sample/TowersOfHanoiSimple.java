package dev.codebase.gcj.sample;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TowersOfHanoiSimple<T extends Comparable<? super T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TowersOfHanoiSimple.class);
    
    private Tower towerA;
    private Tower towerB;
    private Tower towerC;
    
    public TowersOfHanoiSimple() {
        towerA = new Tower("A");
        towerB = new Tower("B");
        towerC = new Tower("C");
    }
    
    public void moveTop(Tower from, Tower to) {
        if (from.itemCount() == 0) {
            LOGGER.info("Finished !!!");
            return;
        }
        
        if (to.itemCount() == 0 || 
            from.getTop().compareTo(to.getTop()) < 0) {            
            LOGGER.info("Moving [" + from.getTop() + "] from Tower:" + from.getName() +
                        " to Tower:" + to.getName());
            to.addItem(from.removeTop());
        } else {
            LOGGER.info("Unable to move [" + from.getTop() + "] from Tower:" + from.getName() +
                        " to Tower:" + to.getName() + " as covered item is too small");            
        }
    }
    
    public static void main(String[] args) {

        TowersOfHanoiSimple<Integer> toh = new TowersOfHanoiSimple<Integer>();
        
        for (int i = 4; i > 0; i--) {
            toh.towerA.addItem(new Integer(i));
        }
        
        toh.moveTop(toh.towerA, toh.towerC);
        
        LOGGER.info("Items on Tower C : " + toh.towerC.getItems());
        
        toh.moveTop(toh.towerA, toh.towerB);
        toh.moveTop(toh.towerC, toh.towerA);
        toh.moveTop(toh.towerB, toh.towerC);
        toh.moveTop(toh.towerA, toh.towerC);
        
        LOGGER.info("Items on Tower C : " + toh.towerC.getItems());
        
        toh.moveTop(toh.towerA, toh.towerB);        
        toh.moveTop(toh.towerC, toh.towerB);        
        toh.moveTop(toh.towerC, toh.towerA);        
        toh.moveTop(toh.towerB, toh.towerA);        
        toh.moveTop(toh.towerB, toh.towerC);        
        toh.moveTop(toh.towerA, toh.towerB);
        toh.moveTop(toh.towerA, toh.towerC);
        toh.moveTop(toh.towerB, toh.towerC);

        LOGGER.info("Items on Tower C : " + toh.towerC.getItems());

        toh.moveTop(toh.towerA, toh.towerB);    // 4 to B        
        toh.moveTop(toh.towerC, toh.towerB);    // 1 to B    
        toh.moveTop(toh.towerC, toh.towerA);    // 2 to A    
        toh.moveTop(toh.towerB, toh.towerA);    // 1 to A
        
        toh.moveTop(toh.towerC, toh.towerB);    // 3 to B    
        toh.moveTop(toh.towerA, toh.towerB);    // 1 to B
        toh.moveTop(toh.towerA, toh.towerC);    // 2 to C
        toh.moveTop(toh.towerB, toh.towerC);    // 1 to C
        toh.moveTop(toh.towerB, toh.towerA);    // 3 to A
        toh.moveTop(toh.towerC, toh.towerB);    // 1 to B
        toh.moveTop(toh.towerC, toh.towerA);    // 2 to A
        toh.moveTop(toh.towerB, toh.towerA);    // 1 to A
        toh.moveTop(toh.towerB, toh.towerC);    // 4 to C

        // Repeat sequence for 3
        toh.moveTop(toh.towerA, toh.towerC);
        
        toh.moveTop(toh.towerA, toh.towerB);
        toh.moveTop(toh.towerC, toh.towerA);
        toh.moveTop(toh.towerB, toh.towerC);
        toh.moveTop(toh.towerA, toh.towerC);
        
        toh.moveTop(toh.towerA, toh.towerB);        
        toh.moveTop(toh.towerC, toh.towerB);        
        toh.moveTop(toh.towerC, toh.towerA);        
        toh.moveTop(toh.towerB, toh.towerA);        
        toh.moveTop(toh.towerB, toh.towerC);        
        toh.moveTop(toh.towerA, toh.towerB);
        toh.moveTop(toh.towerA, toh.towerC);
        toh.moveTop(toh.towerB, toh.towerC);
        
        LOGGER.info("Items on Tower C : " + toh.towerC.getItems());

    }

    private class Tower {
        private Deque<T> items;
        private String name;
        
        private Tower(String name) {
            this.items = new ArrayDeque<T>();
            this.name = name;
        }

        public List<T> getItems() {
            return new ArrayList<T>(items);
        }

        public String getName() {
            return name;
        }
        
        public void addItem(T item) {
            items.addFirst(item);
        }
        
        public T getTop() {
            return items.size() > 0 ? items.peekFirst() : null;
        }
        
        public T removeTop() {
            return items.size() > 0 ? items.removeFirst() : null;
        }

        public int itemCount() {
            return items.size();
        }
    }
}
