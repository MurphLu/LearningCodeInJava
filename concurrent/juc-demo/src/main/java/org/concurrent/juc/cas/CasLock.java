package org.concurrent.juc.cas;

import org.concurrent.juc.factory.UnsafeFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CasLock {
    private volatile int state;


    private Unsafe unsafe;
    private long offset;

    public CasLock() {
        this.state = 0;
        unsafe = UnsafeFactory.getUnsafe();

        offset = UnsafeFactory.getUnsafeOffset(unsafe, CasLock.class, "state");

    }

    public int getState() {
        return state;
    }

    public boolean cas() {
        return unsafe.compareAndSwapInt(this, offset, 0, 1);
    }

    public void reset() {
        state = 0;
    }
}
