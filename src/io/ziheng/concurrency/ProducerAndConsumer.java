package io.ziheng.concurrency;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Producer implements Runnable {
    private int n;
    private List<Integer> list;
    private final Lock lock;
    private final Condition condition;
    public Producer(List<Integer> list, Lock lock, Condition condition) {
        this.list = list;
        this.n = 0;
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        produceLockCondition();
    }
    private void produceLockCondition() {
        while (true) {
            lock.lock();
            try {
                Thread.sleep(500);
                if (list.size() == 0) {
                    System.out.println("Procuder -> " + n);
                    list.add(n);
                    n++;
                    condition.signalAll();
                } else {
                    condition.await();
                }
            } catch (Exception e) {
                //TODO: handle exception
            } finally {
                lock.unlock();
            }
        }
    }
    private void produceSynchronized() {
        while (true) {
            synchronized(list) {
                try {
                    Thread.sleep(500);
                    if (list.size() == 0) {
                        System.out.println("Procuder -> " + n);
                        list.add(n);
                        n++;
                        list.notifyAll();
                    } else {
                        list.wait();
                    }
               } catch (Exception e) {
                    // ...
                }
            }
        }
    }
}

class Consumer implements Runnable {
    private List<Integer> list;
    private final Lock lock;
    private final Condition condition;
    public Consumer(List<Integer> list, Lock lock, Condition condition) {
        this.list = list;
        this.lock = lock;
        this.condition = condition;
    }
    @Override
    public void run() {
        consumeLockCondition();
    }
    private void consumeLockCondition() {
        while (true) {
            lock.lock();
            try {
                if (list.size() != 0) {
                    int i = list.get(0);
                    list.remove(0);
                    System.out.println("Consumer -> " + i);
                    condition.signalAll();
                } else {
                    condition.await();
                }
            } catch (Exception e) {
                // ...
            } finally {
                lock.unlock();
            }
        }
    }
    private void consumeSynchronized() {
        while (true) {
            synchronized(list) {
                try {
                    Thread.sleep(500);
                    if (list.isEmpty()) {
                        list.wait();
                    } else {
                        int i = list.get(0);
                        list.remove(0);
                        System.out.println("Consumer -> " + i);
                        list.notifyAll();
                    }
                } catch (Exception e) {
                    // ...
                }
            }
        }
    }
}

public class ProducerAndConsumer {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<Thread> threadList = Arrays.asList(
            new Thread(new Producer(list, lock, condition)),
            new Thread(new Consumer(list, lock, condition))
        );
        threadList.forEach(s -> s.start());
    }
}
/* EOF */