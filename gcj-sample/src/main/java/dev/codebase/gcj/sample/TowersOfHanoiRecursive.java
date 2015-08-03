package dev.codebase.gcj.sample;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TowersOfHanoiRecursive<T extends Comparable<? super T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TowersOfHanoiRecursive.class);
    
    private Tower towerA;
    private Tower towerB;
    private Tower towerC;
    
    private TowersOfHanoiRecursive() {
        towerA = new Tower("A");
        towerB = new Tower("B");
        towerC = new Tower("C");
    }

    private void moveItems(int itemCount, Tower from, Tower to, Tower buffer) {
        if (itemCount <= 0) return;
        
        moveItems(itemCount - 1, from, buffer, to);
        moveTop(from, to);
        moveItems(itemCount - 1, buffer, to, from);
        
    }
    
    private void moveTop(Tower from, Tower to) {
        if (from.getTop() != null) {
            if (to.itemCount() == 0 || 
                from.getTop().compareTo(to.getTop()) < 0) {            
                LOGGER.info("Moving [" + from.getTop() + "] from Tower:" + from.getName() +
                            " to Tower:" + to.getName());
                to.addItem(from.removeTop());
            } else {
                LOGGER.info("Unable to move [" + from.getTop() + "] from Tower:" + from.getName() +
                            " to Tower:" + to.getName() + " as covered item [" + to.getTop() + "] is too small");            
            }            
        } else {
            LOGGER.info("Unable to move top item from Tower:" + from.getName() + " as it is empty");
        }
    }
    
    public static void main(String[] args) {

        LOGGER.info("\n\nTowers of Hanoi : Integer Example:\n");
        
        TowersOfHanoiRecursive<Integer> toh = new TowersOfHanoiRecursive<Integer>();
        
        for (int i = 5; i > 0; i--) {
            toh.towerA.addItem(new Integer(i));
        }
        
        LOGGER.info("Items on source Tower : " + toh.towerA.getItems());
        LOGGER.info("Items on buffer Tower : " + toh.towerB.getItems());
        LOGGER.info("Items on destination Tower : " + toh.towerC.getItems());
        
        toh.moveItems(toh.towerA.itemCount(), toh.towerA, toh.towerC, toh.towerB);
        
        LOGGER.info("Items on source Tower : " + toh.towerA.getItems());
        LOGGER.info("Items on buffer Tower : " + toh.towerB.getItems());
        LOGGER.info("Items on destination Tower : " + toh.towerC.getItems());
        
        LOGGER.info("\n\nTowers of Hanoi : String Example:\n");
        
        TowersOfHanoiRecursive<String> toh2 = new TowersOfHanoiRecursive<String>();
        
        for (char c = 69; c >= 65; c--) {
            char[] chars = new char[3];
            chars[1] = c;
            chars[0] = chars[2] = (char) (((int) c) + 32);
            toh2.towerA.addItem(String.valueOf(chars));
        }
        
        LOGGER.info("Items on source Tower : " + toh2.towerA.getItems());
        LOGGER.info("Items on buffer Tower : " + toh2.towerB.getItems());
        LOGGER.info("Items on destination Tower : " + toh2.towerC.getItems());
        
        toh2.moveItems(toh2.towerA.itemCount(), toh2.towerA, toh2.towerC, toh2.towerB);
        
        LOGGER.info("Items on source Tower : " + toh2.towerA.getItems());
        LOGGER.info("Items on buffer Tower : " + toh2.towerB.getItems());
        LOGGER.info("Items on destination Tower : " + toh2.towerC.getItems());
        
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
