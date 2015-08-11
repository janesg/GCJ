package dev.codebase.gcj.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ProducerConsumerExample2 {
    public static void main(String[] args) {
        BlockingQueue<String> bq = new SynchronousQueue<String>();
        
        (new Thread(new Producer2(bq))).start();
        (new Thread(new Consumer2(bq))).start();
    }
}
