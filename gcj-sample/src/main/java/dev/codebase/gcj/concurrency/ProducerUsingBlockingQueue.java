package dev.codebase.gcj.concurrency;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ProducerUsingBlockingQueue implements Runnable {
    private BlockingQueue<String> bq;

    public ProducerUsingBlockingQueue(BlockingQueue<String> bq) {
        this.bq = bq;
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
            try {
                bq.put(importantInfo[i]);
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
            }
        }
        
        try {
            bq.put("DONE");
        } catch (InterruptedException e) {
        }
    }
}