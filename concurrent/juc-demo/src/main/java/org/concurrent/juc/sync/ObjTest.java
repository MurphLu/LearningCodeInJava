package org.concurrent.juc.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 偏向锁撤销之调用对象HashCode
 * 调用锁对象的obj.hashCode() 或 System.identityHashCode(obj) 方法会导致该对象的偏向锁被撤销。
 * 因为对于一个对象，其Hashcode只会生成一次并保存，偏向锁是没有地方保存hashcode的。
 * 轻量级锁会在锁记录中记录 hashCode
 *
 * 重量级锁会在 Monitor 中记录 hashCode当对象处于可偏向(也就是线程ID为0)和已偏向的状态下，调用HashCode计算将会使对象再也无法偏向
 *
 * 当对象可偏向时，MarkWord将变成未锁定状态，并只能升级成轻量锁;
 * 当对象正处于偏向锁时，调用HashCode将使偏向锁强制升级成重量锁。
 * <br/>
 * <br/>
 *
 * <img src="obj_struct.png" />
 *
 * <br/>
 * -XX:BiasedLockingStartupDelay=1000 如果是用偏向锁 开启偏向锁的延迟时间
 * 关闭偏向锁
 * -XX:-UseBiasedLocking
 * 开启偏向锁，默认对象会开启偏向锁
 * -XX:+UseBiasedLocking
 *
 * 当 JVM 启用了偏向锁模式，那么新创建的对象 Mark Word 中的 Thread Id 为 0，此时出于可偏向但未偏向任何线程，也叫做匿名偏向状态
 *
 * <h3>偏向锁延迟偏向<h3/>
 * 偏向锁模式存在偏向锁延迟机制:
 * HotSpot 虚拟机在启动后有个 4 的延迟才会对每个新建的对象开启偏向锁模式。JVM启动时会进行一系列的复杂活动，比如装载配置，系统类初始化等等。在这个过程中会使用大量synchronized关键字对对象加锁，且这些锁大多数都不是偏向锁。为了减少初始化时间，JVM默认延时加载偏向锁。
 *
 *
 *  01 无锁
 *  101 偏向锁
 *  00 轻量级锁
 *  10 重量级锁
 *
 *  偏向锁状态下没有位置来存储 hasCode
 *  偏向锁 -> hashCode -> 无锁
 *  偏向锁 -> Sync 加锁 -> 偏向锁
 *  偏向锁 -> Sync 加锁 -> 解锁 -> Sync 加锁 -> 轻量级锁 （如果系统底层对于线程有优化，两个 thread，底层地址相同的话，到最后也有可能还是偏向所）
 *  无锁 -> Sync 加锁 -> 轻量级锁
 *  无锁 -> Sync 加锁 -> 轻量级锁 -> 另一个线程 Sync 抢夺 -> 重量级锁
 *
 *  obj.wait(100) 会膨胀为重量级锁，会调用内核的 park
 *
 *
 *  <br/>
 *  <br/>
 *  <img src="lock_states.png" />
 */
public class ObjTest {
    public static void main(String[] args) throws InterruptedException {
//        lock_status();
        reBiased();
    }

    private static void lock_status() throws InterruptedException {
        Object o1 = new Object();
        printObj(o1);
        new Thread(()->{
            synchronized (o1){
                // 无锁 -> 轻量级锁
                printObj(o1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 此时有另一线程抢夺 轻量级锁 -> 重量级锁
                printObj(o1);
            }
        }).start();
        new Thread(()->{
            synchronized (o1){
                // 重量级锁
                printObj(o1);
            }
        }).start();

        Thread.sleep(1000);
        // 锁释放，无锁
        printObj(o1);
        new Thread(()->{
            synchronized (o1) {
                try {
                    o1.wait(100); // wait 会升级为重量级锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after wait");
                printObj(o1);
            }

        }).start();

        Thread.sleep(1000);
        Thread.sleep(5000);
        Object o = new Object();

        printObj(o);

        new Thread(() -> {
            synchronized (o) {
                printObj(o);
                try {
                    o.wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printObj(o);
            }
        }).start();
//        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (o) {
                printObj(o);
            }

        }).start();

        Thread.sleep(3000);
        // 释放完之后变为无锁
        printObj(o);
    }

    // -XX:+PrintFlagsFinal 打印 jvm 参数

    // intx BiasedLockingBulkRebiasThreshold         = 20 默认批量重偏向阈值
    // intx BiasedLockingBulkRevokeThreshold         = 40 默认偏向锁批量撤销阈值
    private static void reBiased() throws InterruptedException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new Object());
        }
        new Thread(()->{
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                synchronized (o) {
                    if (i > 16 && i <= 22 || i > 38) {
                        System.out.println("thread 1 第 " +(i+1)+ " 次加锁");
                        printObj(o);
                    }
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(3000);

        new Thread(()->{
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                synchronized (o) {
                    if (i >= 16 && i <= 22 || i > 38) {
                        System.out.println("thread 2 第 " +(i+1)+ " 次加锁");
                        printObj(o);
                    }
                }
            }
        }).start();
        Thread.sleep(3000);

        new Thread(()->{
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                synchronized (o) {
                    if (i >= 16 && i <= 22 || i > 38) {
                        System.out.println("thread 3 第 " +(i+1)+ " 次加锁");
                        printObj(o);
                    }
                }
            }
        }).start();
        Thread.sleep(3000);


        Object o = new Object();
        printObj(o);

    }

    static void printObj(Object o) {
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}