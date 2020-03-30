package io.ziheng.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ZeroEvenOdd implements Runnable {

    private String s;
    private int n;

    private static Semaphore zero = new Semaphore(1);
    private static Semaphore even = new Semaphore(0);
    private static Semaphore odd = new Semaphore(0);

    public ZeroEvenOdd(String s, int n) {
        this.s = s;
        this.n = n;
    }

    @Override
    public void run() {
        if (s.equals("zero")) {
            printZero();
        }
        else if (s.equals("even")) {
            printEven();
        }
        else if (s.equals("odd")) {
            printOdd();
        }
    }
    private void printOdd() {
        for (int i = 1; i < n; i += 2) {
            try {
                odd.acquire();
                System.out.println(i);
                zero.release();
            } catch (Exception e) {
                // ...
            }
        }
    }
    private void printEven() {
        for (int i = 2; i < n; i += 2) {
            try {
                even.acquire();
                System.out.println(i);
                zero.release();
            } catch (Exception e) {
                // ...
            }
        }
    }
    private void printZero() {
        for (int i = 1; i < n; i++) {
            try {
                zero.acquire();
                System.out.println(0);
                if (i % 2 == 0) {
                    even.release();
                } else {
                    odd.release();
                }
            } catch (Exception e) {
                // ...
            }
        }
    }
    public static void main(String[] args) {
        int n = 10;
        List<Thread> threadList = Arrays.asList(
            new Thread(new ZeroEvenOdd("zero", n)),
            new Thread(new ZeroEvenOdd("even", n)),
            new Thread(new ZeroEvenOdd("odd", n))
        );
        threadList.forEach(th -> th.start());
    }
}
/* EOF */