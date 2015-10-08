package dev.codebase.gcj.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class CyclicBarrierDemo {

   final static int NUM_THREADS = 5;
    
   public static void main(String[] args) {
      
      Runnable action = new Runnable() {
          @Override
          public void run() {
              String name = Thread.currentThread().getName();
              System.out.printf("Thread %s executing barrier action.%n", name);
              
          }
      };
      
      final CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, action);
      final CountDownLatch doneSignal = new CountDownLatch(NUM_THREADS);
      
      Runnable task = new Runnable() {
          @Override
          public void run() {
              String name = Thread.currentThread().getName();
              System.out.printf("%s about to join game...%n", name);
              
              try {
                  barrier.await();
              } catch (BrokenBarrierException bbe) {
                  System.out.println("barrier is broken");
                  return;
              } catch (InterruptedException ie) {
                  System.out.println("thread interrupted");
                  return;
              }
              
              System.out.printf("%s has joined game%n", name);
              doneSignal.countDown();
          }
      };
      
      ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

      for (int i = 0; i < NUM_THREADS; i++) {
          executor.submit(task);          
      }
      
      try {
        doneSignal.await();
      } catch (InterruptedException e) {
          System.out.println("thread interrupted");
          return;
      }
      
      System.out.printf("All tasks complete so resetting the barrier and going around again....\n");
      
      barrier.reset();
      
      for (int i = 0; i < NUM_THREADS; i++) {
          executor.submit(task);          
      }
      
      executor.shutdown();
      
   }
}
