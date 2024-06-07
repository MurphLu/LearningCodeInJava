### Synchronized

应用场景：解决线程安全问题

加锁：序列化的访问临界资源

原理

原理
> jvm指令:方法上(acc_synchronized)代码块(monitorenter monitorexit)
>
> 管程(Monitor): java锁体系的设计思想， 设计理论基础
> 
> MESA:  入口等待队列(互斥) 多个条件队列(同步 阻塞唤醒机制)
> 
> java实现monitor: object wait/notify/notifyAll 重量级操作: (用户态到内核态的切换)
> 
> 优化: 偏向锁 轻量级锁 重量级锁
> 
> 偏向锁:不存在竞争偏向某个线程thread1,后续进入同步块的逻辑没有加锁解锁的开销
> 
> 轻量级锁:线程间存在轻微的竞争(线程交替执行，临界区逻辑简单)CAS获取锁，失败膨胀
> 
> 重量级锁:多线程竟争激烈的场景, 膨胀期间创建一个monitor对象CAS自旋 阻塞