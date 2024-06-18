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
如果你需要一个重置count的版本，那么请考虑使用 CyclicBarrier.

#### CountDownLatch实现原理
底层基于 AbstractQueuedSynchronizer 实现，CountDownLatch 构造函数中指定的count直接赋给AQS的state;
每次countDown()则都是release(1)减1，最后减到 0 时 unpark 阻塞线程; </br>
这一步是由最后一个执行 cuntdown 方法的线程执行的。</br>
而调用await()方法时，当前线程就会判断 state 属性是否为 0，如果为 0 则继续往下执行，如果不为 0，则使当前线程进入等待状态，直到某个线程将 state 属性置为0，其就会唤醒在 await() 方法中等待的线程

#### CountDownLatch与Thread.join的区别
- CountDownLatch的作用就是允许一个或多个线程等待其他线程成操作，看起来有点类似 join() 方法，但其提供了比 join() 更加灵活的 API.</br>
- CountDownLatch 可以手动控制在 n 个线程里调用 n 次 countDown (方法使计数器进行减一操作，也可以在一个线程里调用n次执行减一操作。
- 而 join() 的实现原理是不停检査join线程是否存活，如果 join 线程存活则让当前线程永远等待。所以两者之间相对来说还是 CountDownLatch使用起来较为灵活.

#### CountDownLatch与cyclicBarrier的区别
> CountDownLatch 和 CyclicBarrier 都能够实现线程之间的等待，只不过它们侧重点不同:
1. CountDownLatch的计数器只能使用一次，而CyclicBarrier的计数可以使用reset()方法重置。所以CyclicBarrier能处理更为复杂的业务场次景，比如如果计算发生错误，可以重置计数器，并让线程们重新执行
2. CyclicBarrier 还提供 getNumberWaiting (可以获得 CyclicBarrier 阻塞的线程数量)、isBroken (用来知道阻塞的线程是否被中断)等方法。会阻塞子线程。 
3. CountDownLatch 会阻塞主线程，CyclicBarrier 不会阻塞主线程，只会阻塞子线程
4. CountDownLatch 和 CyclicBarrier 都能够实现线程之间的等待，只不过它们侧重点不同。CountDownLatch 一般用于一个或多个线程，等待其他线程执行完任务后，再执行。CyclicBarrier 一般用于一组线程互相待至某个状态，然后这一组线程再同时执行。
5. CyclicBarrier 还可以提供一个 barrierAction，合并多线程计算结果
6. CyclicBarrier 是通过 ReentrantLock 的"独占锁"和 Condition 来实现组线程的阻塞唤醒的，而 CountDownLatch 则是通过 AQS 的“共享锁”实现

### CyclicBarrier
> 字面意思回环栅栏(循环屏障)通过它可以实现让一组线程等待至某个状态(屏障点)之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后, CyclicBarrier可以被重用。

- condition.signalAll 条件队列专同步队列 -> 转为 AQS 执行逻辑


### ReentrantReadWriteLock

#### 读写锁介绍
> 现实中有这样一种场景:对共享资源有读和写的操作，且写操作没有读操作那么频繁(读多写少)。在没有写操作的时候，多个线程同时读一个资源没有任何问题，所以应该允许多个线程同时读取共享资源(读读可以并发);</br>
> 但是如果一个线程想去写这些共享资源，就不应该允许其他线程对该资源进行读和写操作了(读写，写读，写写互斥)。在读多于写的情况下，读写锁能够提供比排它锁更好的并发性和吞吐量。</br>
> 针对这种场置，JAVA的并发包提供了读写锁ReenraniReadwiteLac，它内部，维护了一对相关的锁，一个用于只读提作，称为读锁:一个用于写入操作，称为写锁， 描述如下:

- 线程进入读锁的前提条件:
1. 没有其他线程的写锁
2. 没有写请求或者有写请求，但调用线程和持有锁的线程是同一个。
- 线程进入写锁的前提条件:
1. 没有其他线程的读锁
2. 没有其他线程的写锁

- 而读写锁有以下三个重要的特性:
1. 公平选择性:支持非公平(默认)和公平的锁获取方式，吞吐量还是非公平优于公平，
2. 可重入:读锁和写锁都支持线程重入。以读写线程为例:读线程获取读锁后，能够再次获取读锁。写线程在获取写锁之后能够再次获取写锁，同时也可以获取读锁
3. 锁降级:遵循获取写锁、再获取读锁最后释放写锁的次序，写锁能够降级成为读锁。