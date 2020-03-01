package io.ziheng.concurrency;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Arrays;

public class OrderedPrint implements Runnable {

    private static final Object lockA = new Object();
    private static final Lock lockB = new ReentrantLock();

    private static int end = 1024;
    private static int current = 1;

    private int index;

    public OrderedPrint(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        runInternalWithAQSLock();
        //runInternalWithSynchronized();
    }

    private void runInternalWithSynchronized() {
        while (current < end) {
            synchronized(lockA) {
                if (current < end && current % 3 == this.index) {
                    System.out.println(current);
                    current++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private void runInternalWithAQSLock() {
        while (current < end) {
            try {
                lockB.lock();
                if (current < end && current % 3 == this.index) {
                    System.out.println(current);
                    current++;
                    Thread.sleep(1000);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            } finally {
                lockB.unlock();
            }
        }
    }
    
    public static void main(String[] args) {
        List<Thread> threadList = Arrays.asList(
                new Thread(new OrderedPrint(0)),
                new Thread(new OrderedPrint(1)),
                new Thread(new OrderedPrint(2))
        );
        threadList.forEach(th -> th.start());
    }

}
/* EOF */