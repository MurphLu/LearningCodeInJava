package org.concurrent.juc.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        new ThreadPoolTest().threadTest();
        new ThreadPoolTest().testPool();
    }


    private void threadTest() throws InterruptedException {
        Long start = System.currentTimeMillis();
        final Random random = new Random();

        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Thread thread =new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
            thread.start();
            thread.join();
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(list.size());
    }

    private void testPool() throws InterruptedException {
        Long start = System.currentTimeMillis();
        final Random random = new Random();

        final List<Integer> list = new ArrayList<>();
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100000; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        service.shutdown();
        boolean b = service.awaitTermination(1, TimeUnit.DAYS);
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(list.size());
    }


    private void pools() {
        // 不推荐使用

        // CPU 100%
        ExecutorService service1 = Executors.newCachedThreadPool();

        ExecutorService service = Executors.newFixedThreadPool(10);

        ExecutorService service3 = Executors.newSingleThreadExecutor();


        // public ThreadPoolExecutor(int corePoolSize,              核心线程数
        //                              int maximumPoolSize,        最大线程数（核心+临时 当核心线程全忙时会在限制数内创建临时线程）
        //                              long keepAliveTime,         核心线程之外的线程最大存活时间
        //                              TimeUnit unit,              时间单位
        //                              BlockingQueue<Runnable> workQueue) 任务队列


        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                20,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10));
    }
}
