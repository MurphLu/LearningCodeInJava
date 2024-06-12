package org.concurrent.juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AqsImpl extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock() {
        tryAcquire(1);
    }

    public void unlock() {
        tryRelease(1);
    }
}
