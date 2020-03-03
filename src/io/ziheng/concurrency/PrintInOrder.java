package io.ziheng.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintInOrder {
    private static Lock lock = new ReentrantLock();
    private static int current = 1;

    public PrintInOrder() {
        // ...
    }
    
    public void first(Runnable printFirst) throws InterruptedException {
        while (current < 4) {
            try {
                lock.lock();
                if (current % 3 == 1) {
                    // printFirst.run() outputs "first". Do not change or remove this line.
                    printFirst.run();
                    current++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (current < 4) {
            try {
                lock.lock();
                if (current % 3 == 2) {
                    // printSecond.run() outputs "second". Do not change or remove this line.
                    printSecond.run();
                    current++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (current < 4) {
            try {
                lock.lock();
                if (current % 3 == 0) {
                    // printThird.run() outputs "third". Do not change or remove this line.
                    printThird.run();
                    current++;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
/* EOF */