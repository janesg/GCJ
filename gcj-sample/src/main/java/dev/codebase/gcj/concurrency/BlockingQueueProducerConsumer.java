package dev.codebase.gcj.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class BlockingQueueProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<String> bq = new SynchronousQueue<String>();
        
        (new Thread(new ProducerUsingBlockingQueue(bq))).start();
        (new Thread(new ConsumerUsingBlockingQueue(bq))).start();
    }
}
