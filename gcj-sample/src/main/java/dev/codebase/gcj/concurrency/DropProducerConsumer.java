package dev.codebase.gcj.concurrency;

public class DropProducerConsumer {
    public static void main(String[] args) {
        Drop drop = new Drop();
        
        (new Thread(new ProducerUsingDrop(drop))).start();
        (new Thread(new ConsumerUsingDrop(drop))).start();
    }
}
