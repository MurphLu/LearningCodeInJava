package org.concurrent.juc.aqs;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void cdlDemo() {
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
    }

    public static void ontCtl() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " getReady...");
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + " 开始执行...");
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" fire!!!!");
        countDownLatch.countDown();

    }
}
