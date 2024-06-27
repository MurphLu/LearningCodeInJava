package org.concurrent.juc.aqs;

import java.util.concurrent.*;
import java.util.function.Consumer;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        futureTask();
//        completionService();
        completionFuture();
    }

    private static void futureTask() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,5,TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        FutureTask<Integer> t1 = new FutureTask<>(() -> {
            Thread.sleep(5000);
            return 10;
        });
        FutureTask<Integer> t2 = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return 10;
        });
        FutureTask<Integer> t3 = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return 10;
        });
        FutureTask<Integer> t4 = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return 10;
        });
        FutureTask<Integer> t5 = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return 10;
        });

        executor.submit(t1);
        executor.submit(t2);
        executor.submit(t3);
        executor.submit(t4);
        executor.submit(t5);

        // 执行慢的任务会阻塞后续 get()
        System.out.println(t1.get() + "  t1 finish");
        System.out.println(t2.get() + "  t1 finish");
        System.out.println(t3.get() + "  t1 finish");
        System.out.println(t4.get() + "  t1 finish");
        System.out.println(t5.get() + "  t1 finish");
    }

    private static void completionService() throws ExecutionException, InterruptedException {
        CompletionService<Integer> executor = new ExecutorCompletionService<>(Executors.newFixedThreadPool(5));
        executor.submit(()->{
            Thread.sleep(15000);
            return 10;
        });
        executor.submit(()->{
            Thread.sleep(1000);
            return 10;
        });
        executor.submit(()->{
            Thread.sleep(1000);
            return 10;
        });
        executor.submit(()->{
            Thread.sleep(1000);
            return 10;
        });
        executor.submit(()->{
            Thread.sleep(1000);
            return 10;
        });
        int i = 0;
        while (i++ < 5) {
            Future<Integer> take = executor.take();
            System.out.println(take.get() + " aaa");
        }
    }

    private static void completionFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->{
            System.out.println("烧水");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "烧水完成";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            System.out.println("准备茶叶茶具");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "准备完成";
        }), (water, tools) ->{
            System.out.println(water + " " + tools + "泡茶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "泡好了";
        }).thenApply((String s)->{
            System.out.println(s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "喝茶";
        });
        System.out.println(cf.get());
    }
}
