package org.concurrent.juc.aqs;

import java.util.concurrent.*;

/**
 * <img src="Array&LinkedBlockingQueue.png"/>
 * <img  src="SynchronousQueue.png" />
 * <img src="LinkedTransferQueue&PriorityBlockingQueue.png" />
 * <img src="DelayQueue.png" />
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        // 环形数组
        // 一个 ReentrantLock
        // condition: notEmpty, notFull
        // put 过程，满了之后 notFul.await()，添加成功 notEmpty.signal();
        // take 过程，空了之后 notEmpty.await()，take 成功 notFul.signal()
        BlockingQueue<Integer> arrayQueue = new ArrayBlockingQueue<>(5);

        // 链表
        // 两个 ReentrantLock， putLock, takeLock
        //
        BlockingQueue<Integer> linkQueue = new LinkedBlockingQueue<>(5);

        // 容量为 0，只在线程间传递，
        // 公平 Thread 链表存储，先进先出
        // 非公平 thread 栈存储，后进先出
        BlockingQueue<Integer> syncQueue = new SynchronousQueue<>();

        BlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>();
    }

}
