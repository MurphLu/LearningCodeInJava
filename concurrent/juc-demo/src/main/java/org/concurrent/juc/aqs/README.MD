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

### Semaphore
> Semaphore，俗称信号量，它是操作系统中PV操作的原语在java的实现，它也是基于AbstractQueuedSynchronizer实现的.
> 
> Semaphore的功能非常强大，大小为1的信号量就类似于互斥锁，通过同时只能有一个线程获取信号量实现。大小为n(n > 0)的信号量可以实现限流的功能，它可以实现只能有n个线程同时获取信号量。
>

#### PV操作
> 是操作系统一种实现进程互斥与同步的有效方法。PV操作与信号量(S)的处理相关，P表示通过的意思，"表示释放的意思。用PV操作来管理共享资源时，首先要确保PV操作自身执行的正确性。

##### P操作的主要动作是:
1. S减1;
2. 若S减1后仍大于或等于0，则进程继续执行;
3. 若S减1后小于0，则该进程被阻塞后放入等待该信号量的等待队列中，然后转进程调度

##### V操作的主要动作是:
1. S加1;
2. 若相加后结果大于0，则进程继续执行;
3. 若相加后结果小于或等于0，则从该信号的等待队列中释放一个等待进程，然后再返回原进程继续执行或转进程调度

#### 构造方法
```java
class Semaphore{
    public Semaphore(int permits) {
        sync = new NonfairSync(permits);
    }
    
    public Semaphore(int permits, boolean fair) {
        sync = fair ? new FairSync(permits) : new NonfairSync(permits);
    }
}

// permits 代表许可数量
// fair 表示公平性，如果为 true 的话，下次执行的线程会是等待最久的线程
```


### CountDownLatch 
CountDownLatch(闭锁)是一个同步协助类，允许一个或多个线程等待，直到其他线程完成操作集。</br>
CountDownLatch使用给定的计数值(count)初始化。</br>
await方法会阻塞直到当前的计数值(count)由于countDown方法的调用达到0，coun为0之后所有等待的线程都会被释放，并且随后对await方法的调用都会立即返回。</br>
这是一个一次性现象-- count不会被重置。</br>
如果你需要一个重置count的版本，那么请考虑使用CyclicBarrier.