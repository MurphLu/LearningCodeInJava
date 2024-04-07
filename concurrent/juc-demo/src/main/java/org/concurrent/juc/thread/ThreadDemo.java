package org.concurrent.juc.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        newThread();
//        threadFunction();
        threadInterrupt();
    }

    public static void threadState() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
            }
        });
        System.out.println("thread state: " + thread.getState());
        thread.start();
        System.out.println("thread state: " + thread.getState());
        Thread.sleep(100);
        System.out.println("thread state: " + thread.getState());
        LockSupport.unpark(thread);
        Thread.sleep(100);
        System.out.println("thread state: " + thread.getState());
    }

    private static void threadFunction() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t start");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t end");
            }
        });

        long start = System.currentTimeMillis();
        thread.start();
        // 主线程等待线程执行完成
        thread.join();
        long take = System.currentTimeMillis() - start;
        System.out.println("执行时间：" + take);
        System.out.println("Main finished");
    }

    private static void threadInterrupt() {
        final int[] count = {0};
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println("======");
////                    try {
//////                        Thread.sleep(1000);
////                    } catch (InterruptedException e) { //InterruptedException 会重置标志位
//////                        e.printStackTrace();
////                        Thread.currentThread().interrupt();
////                    }
//                    if(Thread.currentThread().isInterrupted()) {
//                        System.out.println(Thread.currentThread().getName() + " isInterrupted");
//                    }
//                    if(Thread.interrupted()){
//                        System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().isInterrupted());
//                    }
//                    if (count[0]++ > 1000) {
//                        System.out.println("exit while");
//                        break;
//                    }
//                }
//            }
//        });
//        t.start();
//        t.interrupt();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted() && count[0] < 1000000) {
                    count[0]++;
                    System.out.println(count[0]);
                }
                System.out.println("thead stop");
            }
        });
        t1.start();
        t1.interrupt();
    }

    // new Thread + runnable
    // thread.start() 不能直接嗲用 run 方法，需要调用 start 方法，开启线程并调用 thread 的 run 方法
    // 如果直接调用 run，则为普通方法调用，是在当前线程直接执行任务，不会开启新线程
    // java thread -> jvm thread -> os thread
    // java 的线程是内核级线程
    private static void newThread() throws ExecutionException, InterruptedException {
        Thread t1 = new ThreadChild();
        t1.start();

        Thread t2 = new Thread(new RunnableTask());
        t2.start();

        // 线程池执行 callable 也是创建一个 runnable，并将返回结果保存到 Future 中，通过 future 的 get 可以获取到返回结果
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<String> submit = service.submit(new CallerTask());
        String s = submit.get();
        System.out.println("result from CallerTask in ThreadPool " + s);
        service.shutdown();

        new Thread(()-> System.out.println("with lambda " + Thread.currentThread().getName())).start();
    }

    static class RunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println("thread with runnable " + Thread.currentThread().getName());
        }
    }

    static class ThreadChild extends Thread {
        @Override
        public void run() {
            System.out.println("thread with new " + Thread.currentThread().getName());
        }
    }

    static class CallerTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "return val";
        }
    }
}
