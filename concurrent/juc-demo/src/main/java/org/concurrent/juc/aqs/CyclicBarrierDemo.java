package org.concurrent.juc.aqs;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CyclicBarrierDemo {
    public static void cbDemo(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 10; i++) {

            new Thread(()-> {
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " 开始执行");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + " 执行结束");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
    }

    public static void cbWithThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+ " 准备执行");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+ " 准备执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void reentrantLockD() {
        ReentrantLock lock = new ReentrantLock();
        Condition trail = lock.newCondition();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                lock.lock();
                try{
                    if (finalI==9) {
                        Thread.sleep(1000);
                        trail.signalAll(); // 条件队列转同步队列
                    } else {
                        // 释放锁
                        trail.await();
                        // 获取锁
                    }

                    System.out.println(Thread.currentThread().getName() + " run");
                }catch (Exception ex) {
                    ex.printStackTrace();
                }finally {
                    lock.unlock(); // 唤醒同步队列中 head 后续节点所在的线程
                }
            }).start();
        }

    }
}
