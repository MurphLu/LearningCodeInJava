package org.concurrent.juc.aqs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/Join是一种基于分治算法的模型，在并发处理计算型任务时有着显著的优势。其效率的提升主要得益于两个方面:任务切分:将大的任务分割成更小粒度的小任务，让更多的线程参与执行，<br/>
 * 任务窃取:通过任务窃取，充分地利用空闲线程，并减少竞争。<br/>
 * 在使用FordoinPo0时，需要特别注意任务的类型是否为纯函数计算类型，也就是这些任务不应该关心状态或者外界的变化，这样才是最安全的做法。如果是阳塞类型任务，那么你需要谨慎评估技术方案。虽然ForkJoinPool也能处理阻塞类型任务，但可能会带来复杂的管理成本。
 * <img src="forkjoinPool.png"/>
 */
public class ForkJoinPoolDemo {

    // 适合做计算密集型任务
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
