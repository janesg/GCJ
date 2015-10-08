package dev.codebase.gcj.multithread;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Phaser;

public class PhaserDemo {

    private static final int NUM_TASKS = 10;
    
    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();
        
        for (int i = 0; i < NUM_TASKS; i++) {        
            tasks.add(new Runnable() {
                @Override
                public void run() {
                    System.out.printf("%s running at %d%n", Thread.currentThread().getName(), 
                                                            System.nanoTime());
                }
            });
        }
        
        runTasks(tasks);
    }

    static void runTasks(List<Runnable> tasks) {
        
        final Phaser phaser = new Phaser(1); // "1" to register self
        
        // create and start threads
        for (final Runnable task : tasks) {
            
            System.out.printf("Thread %s registering with Phaser%n", 
                              Thread.currentThread().getName());

            phaser.register();
            
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(50 + (int) (Math.random() * 300));
                    } catch (InterruptedException ie) {
                        System.out.println("interrupted thread");
                    }
                    
                    System.out.printf("Thread %s arrived at Phaser and awaiting advance%n", 
                                      Thread.currentThread().getName());
                    
                    phaser.arriveAndAwaitAdvance(); // await all creation
                    
                    System.out.printf("All registered threads arrived at Phaser...Thread %s advancing%n", 
                                      Thread.currentThread().getName());
          
                    task.run();
                }
            }.start();
        }

        System.out.printf("Thread %s deregistering from Phaser%n", 
                          Thread.currentThread().getName());

        // allow threads to start and deregister self
        phaser.arriveAndDeregister();
    }
}