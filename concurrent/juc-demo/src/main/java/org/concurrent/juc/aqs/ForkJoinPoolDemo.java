package org.concurrent.juc.aqs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        int[] arr = new int[10000000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+1;
        }
        Long aLong = pool.submit(new SumTask(arr, 0, arr.length - 1)).get();
        System.out.println(aLong);

    }


    // fork join task
    static class SumTask extends RecursiveTask<Long> {
        int low;
        int high;
        int[] array;

        static final int SEQUENTIAL_THRESHOLD = 10000;

        public SumTask(int[] arr, int lo, int hi) {
            array = arr;
            low = lo;
            high = hi;
        }

        @Override
        protected Long compute() {
            if (high - low <= SEQUENTIAL_THRESHOLD) {
                long sum = 0;
                for (int i = low; i < high; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                int mid = low + (high - low) /2;
                SumTask left = new SumTask(array, low, mid);
                SumTask right = new SumTask(array, mid, high);
                left.fork();
                right.fork();
                //获取任务的执行结果,将阻塞当前线程直到对应的子任务完成运行并返回结果
                long rightAns = right.join();
                long leftAns = left.join();
                return leftAns + rightAns;
            }
        }
    }
}
