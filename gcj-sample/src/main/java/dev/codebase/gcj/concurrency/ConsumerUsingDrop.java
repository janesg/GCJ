package dev.codebase.gcj.concurrency;

import java.util.Random;

public class ConsumerUsingDrop implements Runnable {
    private Drop drop;

    public ConsumerUsingDrop(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        Random random = new Random();
        
        for (String message = drop.take(); !message.equals("DONE"); message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            
            try {
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {}
        }
    }
}