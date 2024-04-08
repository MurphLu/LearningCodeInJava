package org.concurrent.juc.cas;

import org.concurrent.juc.factory.UnsafeFactory;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

public class IntroDemo {
    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
//        unsafe();
//        withSync();
//        withReentrantLock();
//        testCAS();
//        withCAS();
        withAtomicInteger();
    }

    private static void unsafe() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    num++;
                }
            });
            thread.start();
        }
        System.out.println(num);
    }

    private static final Object o = "";
    private static void withSync() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                synchronized (o) {
                    for (int j = 0; j < 10000; j++) {
                        num++;
                    }
                }
            });
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println(num);
    }

    static ReentrantLock reentrantLock = new ReentrantLock();

    private static void withReentrantLock() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                reentrantLock.lock();
                for (int j = 0; j < 10000; j++) {
                    num+=1;
                }
                reentrantLock.unlock();
            });
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println(num);
    }

    private static void testCAS() throws NoSuchFieldException {
        Entity entity = new Entity();
        Unsafe unsafe = UnsafeFactory.getUnsafe();
        long offset = UnsafeFactory.getUnsafeOffset(unsafe, Entity.class, "x");

        boolean b = unsafe.compareAndSwapInt(entity, offset, 0, 10);
        System.out.println("successful: " + b + "  " + entity.x);
    }

    // 乐观锁，对 cpu 消耗较大
    private static void withCAS() throws InterruptedException {
        CasLock casLock = new CasLock();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                for (;;){
                    if (casLock.getState()==0&& casLock.cas()){
                        try {
                            for (int j = 0; j < 10000; j++) {
                                num+=1;
                            }
                        } finally {
                            casLock.reset();
                        }
                        break;
                    }
                }
            });
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println(num);
    }

    static AtomicInteger numAtomic = new AtomicInteger(0);
    private static void withAtomicInteger() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                for (int j = 0; j < 10000; j++) {
                    numAtomic.incrementAndGet();
                }

            });
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println(numAtomic.get());
    }

    // 带版本的 cas，防止 ABA 问题
    static AtomicStampedReference<Integer> numAtomicRef = new AtomicStampedReference<Integer>(1, 1);
}


class Entity {
    public int x;
}
