### AQS 抽象层 (AbstractQueuedSynchronizer)

- 同步等待队列（cas， volatile int state） 入队，出队
- 加锁，解锁（实现层）
- 条件等待队列（condition await/signal, signalAll）

#### AQS具备的特性:
- 阻塞等待队列 
- 共享/独占 
- 公平/非公平 
- 可重入 
- 允许中断


#### AQS 内部维护属性
```
private volatile int state;

protected final int getState() {
    return state;
}

protected final void setState(int newState) {
    state = newState;
}

protected final boolean compareAndSetState(int expect, int update) {
    return STATE.compareAndSet(this, expect, update);
}
```


#### AQS定义两种资源共享方式
- Exclusive-独占，只有一个线程能执行，如 ReentrantLock
- Share-共享，多个线程可以同时执行，如 Semaphore/CountDownLatch

#### AQS定义两种
- 同步等待队列： 主要用于维护获取互斥锁失败时入队的线程
- 条件等待队列： 调用 `await()` 的时候会释放锁，然后线程会加入到条件队列，调用 `signal()` 唤醒的时候会把条件队列中的线程节点移动到同步队列中，等待再次获得锁

#### AQS 定义了 5 个队列中节点的状态：

1. 0 初始化状态，表示当前节在 sync 队列中，等待获取锁
2. CANCELLED =  1; 表示当前线程被取消
3. SIGNAL    = -1; 表示当前节点的后继节点包含的线程需要运行，也就是 unpark
4. CONDITION = -2; 表示当前节点在等待 condition，也就是在 condition 队列中
5. PROPAGATE = -3; 表示当前场景下后续的 acquireShared 能够得以执行


#### 同步等待队列
>AQS当中的同步等待队列也称CLH队列，CLH 队列是craig、Landin、Hagersten三人发明的一种基于双向链表数据结构的队列，是FIFO先进先出线程等待队列，Java中的 CLH 队列是原 CLH 队列的一个变种,线程由原自旋机制改为阻塞机制。

**AQS 依赖CLH同步队列来完成同步状态的管理:**
- 当前线程如果获取同步状态失败时，AQS则会将当前线程已经等待状态等信息构造成一个节点(Node)并将其加入到 CLH 同步队列，同时会阻塞当前线程当同步状态释放时，会把首节点唤醒(公平锁)，使其再次尝试获取同步状态。
- 通过 signal 或 signalAll 将条件队列中的节点转移到同步队列。(由条件队列转化为同步队列)


### ReentrantLock
> ReentrantLock 是一种基于AQS框架的应用实现，是JDK中的一种线程并发访问的同步手段，它的功能类似于synchronized是一种互斥锁，可以保证线程安全相

对于 synchronized, ReentrantLock具备如下特点:
- 可中断
- 可以设置超时时间
- 可以设置为公平锁
- 支持多个条件变量
- 与 synchronized 一样，都支持可重入


#### 几点 synchronized 和 ReentrantLock 的区别:
- synchronized 是JVM层次的锁实现，ReentrantLock 是JDK层次的锁实现:
- synchronized 的锁状态是无法在代码中直接判断的，但是 ReentrantLock 可以通过 ReentrantLock#isLocked 判断
- synchronized 是非公平锁，ReentrantLock 是可以是公平也可以是非公平的;
- synchronized 是不可以被中断的，而 ReentrantLock#lockInterruptibly 方法是可以被中断的
- 在发生异常时 synchronized 会自动释放锁，而 ReentrantLock 需要开发者在 finally 块中显示释放锁
- ReentrantLock获取锁的形式有多种:如立即返回是否成功的 tryLock(),以及等待指定时长的获取，更加灵活
- synchronized在特定的情况下对于已经在等待的线程是后来的线程先获得锁(回顾一下 synchronized 的唤醒策略)，而 ReentrantLock 对于已经在等待的线程是先来的线程先获得锁

