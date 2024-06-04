package org.concurrent.juc.atomic;

import java.util.concurrent.atomic.LongAdder;

public class AtomicClasses {
    // LongAdder, DoubleAdder 在高并发大量任务执行时，要比 AtomicLong, AtomicDouble 效率更高
    // LongAdder, DoubleAdder 在高并发下会根据线程分槽，各个线程分别计算，做后累加返回结果，这种情况下，cas 等待时间会大大减少
//    LongAdder
}