package org.concurrent.juc.aqs;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
//        readWrite();
        writeDownToRead();
    }

    private static void writeDownToRead() {
        // int state, 高 16 位读，低 16 位 写， HoldCounter 维护重入次数，存在 threadLocal 中
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        AtomicReference<String> s = new AtomicReference<>("");
        new Thread(()-> {
            writeLock.lock();
            try {
                System.out.println("write.....");
                s.set("100");
                // 解决多线程全局变量可见性问题，如果 writeLock 解锁之后获取读锁，可能会由于可见性问题读到脏数据，
                // 写锁解锁之前获取读锁，可以保证在写锁解锁之后其他线程无法获取读锁
                readLock.lock();
                System.out.println(" after write.....");
            }catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                writeLock.unlock();
            }

            try{
                System.out.println(s.get());
            } finally {
                readLock.unlock();
            }
        }).start();

    }

    private static void readWrite() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        for (int i = 0; i < 3; i++) {
            new Thread(()-> write(writeLock)).start();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(()-> read(readLock)).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(()-> write(writeLock)).start();
        }
    }

    private static void read(Lock lock) {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "  read");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "  read finish");
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private static void write(Lock lock) {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "  write");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "  write finish");
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
