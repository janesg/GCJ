package dev.codebase.gcj.multithread;

public class ThreadDemo {

    private int count = 1;

    public synchronized void doSomething() {
        for (int i = 0; i < 10; i++)
            System.out.println(count++);
    }

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        Thread a1 = new A(demo);
        Thread a2 = new A(demo);
        a1.start();
        a2.start();
    }
}

class A extends Thread {

    ThreadDemo demo;

    public A(ThreadDemo td) {
        demo = td;
    }

    @Override
    public void run() {
        demo.doSomething();
    }
}