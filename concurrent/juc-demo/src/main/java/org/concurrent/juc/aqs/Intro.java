package org.concurrent.juc.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Intro {
    public static void main(String[] args) {

//        reentrantLock();
//        semaphore();

        countDownLatch();
    }

    private static void countDownLatch() {
        CountDownLatch startSingle = new CountDownLatch(1);
        CountDownLatch doneSingle = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    startSingle.await();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 开始执行...");
                    doneSingle.countDown();
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startSingle.countDown();
        try {
            doneSingle.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("workDon and do something else");

//        countDownLatch.countDown();
    }

    private static void semaphore() {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try{
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 开始买票");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 买票结束");
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }

    private static void reentrantLock() {
        ReentrantLock lock = new ReentrantLock(true);

        // 不一定回完全按照顺序执行
        ReentrantLock unFairLock = new ReentrantLock(false);

        for (int i = 0; i <500; i++) {
            new Thread(()->{
                lock.lock();
                try{
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " running...");
                } finally {
                    lock.unlock();
                }
            }).start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                lock.lock();
                try{
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 插入线程 running...");
                } finally {
                    lock.unlock();
                }
            }).start();
        }
    }
}
