package org.concurrent.juc.threadPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
//        new ThreadPoolTest().threadTest();
//        new ThreadPoolTest().testPool();
        new ThreadPoolTest().scheduledThreadPoolTest();
    }

    private void scheduledThreadPoolTest() {
        // 队列为 DelayedWorkQueue
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(5);
        Task task = new Task("task");
        System.out.println("task Created: " + new Date().getSeconds());
        // 只执行一次
//        executor.schedule(task, 2, TimeUnit.SECONDS);
        // 周期执行
        // initialDelay 第一次延迟，delay 后续每次任务延迟时间
//        executor.scheduleWithFixedDelay(task, 2, 2, TimeUnit.SECONDS);

        // initialDelay 第一次任务延迟时间，
        // period 任务最小执行时间，如果任务在 period 内执行完也要等到 period 的时间才会继续执行下一个，如果超出了 period 则直接继续执行下一轮
        executor.scheduleAtFixedRate(task, 0, 6, TimeUnit.SECONDS);
//        executor.shutdown();

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
