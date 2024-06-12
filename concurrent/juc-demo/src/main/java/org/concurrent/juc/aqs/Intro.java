package org.concurrent.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class Intro {
    public static void main(String[] args) {

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
