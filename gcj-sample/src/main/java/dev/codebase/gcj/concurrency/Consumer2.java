package dev.codebase.gcj.concurrency;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer2 implements Runnable {
    private BlockingQueue<String> bq;

    public Consumer2(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        Random random = new Random();
        
        try {
            for (String message = bq.take(); !message.equals("DONE"); message = bq.take()) {
                System.out.format("MESSAGE RECEIVED: %s%n", message);
                Thread.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException e) {
        }
    }
}