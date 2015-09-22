package dev.codebase.gcj.concurrency;

import java.util.Random;

public class ProducerUsingDrop implements Runnable {
    private Drop drop;

    public ProducerUsingDrop(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };
        
        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {}
        }
        
        drop.put("DONE");
    }
}