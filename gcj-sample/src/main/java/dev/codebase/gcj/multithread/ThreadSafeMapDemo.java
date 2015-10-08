package dev.codebase.gcj.multithread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
public class ThreadSafeMapDemo {
 
    public final static int THREAD_POOL_SIZE = 5;
 
    public static Map<String, Integer> hashTable = null;
    public static Map<String, Integer> synchronizedMap = null;
    public static Map<String, Integer> concurrentHashMap = null;
 
    public static void main(String[] args) throws InterruptedException {
 
        // Test with Hashtable Object
        hashTable = new Hashtable<String, Integer>();
        crunchifyPerformTest(hashTable);
 
        // Test with synchronizedMap Object
        synchronizedMap = Collections.synchronizedMap(new HashMap<String, Integer>());
        crunchifyPerformTest(synchronizedMap);
 
        // Test with ConcurrentHashMap Object
        concurrentHashMap = new ConcurrentHashMap<String, Integer>();
        crunchifyPerformTest(concurrentHashMap);
 
    }
 
    public static void crunchifyPerformTest(final Map<String, Integer> mapObj) throws InterruptedException {
 
        System.out.println("Test started for: " + mapObj.getClass());
        long averageTime = 0;
        
        for (int i = 0; i < 5; i++) {
 
            long startTime = System.nanoTime();
            ExecutorService execService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
 
            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                
                execService.execute(new Runnable() {
                    @SuppressWarnings("unused")
                    @Override
                    public void run() {
 
                        for (int i = 0; i < 500000; i++) {
                            Integer randNum = (int) Math.ceil(Math.random() * 550000);
 
                            // Retrieve value. We are not using it anywhere
                            mapObj.get(String.valueOf(randNum));
 
                            // Put value
                            mapObj.put(String.valueOf(randNum), randNum);
                        }
                    }
                });
            }
 
            // Make sure executor stops
            execService.shutdown();
 
            // Blocks until all tasks have completed execution after a shutdown request
            execService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
 
            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            averageTime += totalTime;
            
            System.out.println("500K entried added/retrieved in " + totalTime + " ms");
        }
        
        System.out.println("For " + mapObj.getClass() + " the average time is " + averageTime / 5 + " ms\n");
    }
}