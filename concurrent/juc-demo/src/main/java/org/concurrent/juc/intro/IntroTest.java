package org.concurrent.juc.intro;

import org.concurrent.juc.factory.UnsafeFactory;
import sun.misc.Unsafe;

public class IntroTest {
    // volatile
    volatile boolean flag = true;
    int count = 0;

    public void stop() {
        flag = false;
    }

    public void load() {
        while (flag) {
            count++;
            // System.out.println 中有 synchronized
//            System.out.println(count);

            // 使用内存屏障
//            UnsafeFactory.getUnsafe().storeFence();

            // 释放时间片，线程上下文切换
            Thread.yield();

        }
        System.out.println(Thread.currentThread().getName() + " 跳出循环 count: " + count);
        //java 中 内存可见性如何保证
        // 1. jvm 层面，内存屏障
        // 2. 上下文切换

        // java
        // volatile  锁机制
    }

    public static void main(String[] args) throws InterruptedException {
        IntroTest test = new IntroTest();
        Thread thread = new Thread(test::load);
        Thread thread1 = new Thread(test::stop);
        thread.start();
        Thread.sleep(1000);
        thread1.start();
    }
}
