package io.ziheng.concurrency;

public class ThreadLocalDemo {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        System.out.println(System.getProperties());
        threadLocal.set("hello");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String val = threadLocal.get();
                System.out.println(Thread.currentThread().getName() + "线程取到的值：" + val);
                // 设置 threadLocal
                threadLocal.set("ziheng");
                val = threadLocal.get();
                System.out.println("重新设置之后，" + Thread.currentThread().getName() + "线程取到的值为：" + val);
                System.out.println(Thread.currentThread().getName() + "线程执行结束");
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread-0线程执行之后，" + Thread.currentThread().getName() + "线程取到的值：" + threadLocal.get());
    }
}
/* EOF */