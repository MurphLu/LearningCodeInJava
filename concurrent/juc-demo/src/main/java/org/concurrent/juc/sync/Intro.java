package org.concurrent.juc.sync;

/**
 * <h2>临界区(Critical section)<h2/>
 * 一个程序运行多个线程本身是没有问题的问题出在多个线程访问共享资源
 * 多个线程读共享资源其实也没有问题
 * 在多个线程对共享资源读写操作
 * 时发生指令交错，就会出现问题一段代码块内如果存在对共享资源的多线程读写操作，称这段代码块为临界区，其共享资源为临界资源
 *
 * synchronized是JVM内置锁，基于Monitor机制实现，
 * 依赖底层操作系统的互斥原语Mutex(互斥量)，它是一个重量级锁，性能较低。
 * 当然JVM内置锁在1.5之后版本做了重大的优化，
 * 如锁粗化(Lock Coarsening)、锁消除(Lock Elimination)、轻量级锁(LightweightLocking)、
 * 偏向锁(Biased Locking)、自适应自旋(Adaptive Spinning)等技术来减少锁操作的开销，
 * 内置锁的并发性能已经基本与Lock持平。
 * The Java® Language Specification
 * Each object is associated with a monitor ($17.1),
 * which is used by synchronized methods ($8.4.3)
 * and the synchronized statement ($ 14. 19)
 * to provide control over concurrent access to state by multiple threads ($17 (Threads and Locks)).
 *
 * The Java Virtual Machine Specification
 * The Java Virtual Machine supports synchronization of both methods and
 * sequences of instructions within a method bv a
 * single synchronization construct:the monitor.
 *
 * Java虚拟机通过一个同步结构支持方法和方法中的指令序列的同步:monitor。
 * 同步方法是通过方法中的access_flags 中设置 ACC_SYNCHRONIZED 标志来实现:
 * 同步代码块是通过 monitorenter 和 monitorext 来实现。
 * 两个指令的执行是 JVM 通过调用操作系统的互斥原语 mutex 来实现，被阻塞的线程会被挂起、等待重新调度，
 * 会导致"用户态和内核态"两个态之间来回切换，对性能有较大影响，
 *
 *
 * Monitor(管程/监视器)
 * Monitor，直译为“监视器”，而操作系统领域一般翻译为“管程”。
 * 管程是指管理共享变量以及对共享变量操作的过程，让它们支持并发。
 * 在Java1.5之前，Java语言提供的唯一并发语言就是管程，java 1.5之后提供的SDK并发包也是以管程为基础的。
 * 除了java之外，C、C++、C#等高级语言也都是支持管程的。
 * synchronized关键字和wait()、notify()、notifyAl()这三个方法是Java中实现管程技术的组成部分。
 *
 * MESA模型
 * 在管程的发展史上，先后出现过三种不同的管程模型，分别是Hasen模型、Hoare模型和MESA模型。
 * 现在正在广泛使用的是MESA模型。下面我们便介绍MESA模型:
 */
public class Intro {
    public static void main(String[] args) throws InterruptedException {
        syncDemo();
    }

    private static int counter = 0;
    private static void increment(){
        synchronized (lock){
            counter++;
        }

    }

    private static void decrement(){
        synchronized (lock) {
            counter--;
        }
    }

    static final Object lock = "";
    private static void syncDemo() throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                increment();

            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                decrement();

            }
        });
        t1.start();
        t2.start();
        do {
            //            break;
        } while (t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED);
        System.out.println(counter);
    }
}
