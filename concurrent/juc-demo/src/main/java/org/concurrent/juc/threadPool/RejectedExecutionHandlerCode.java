package org.concurrent.juc.threadPool;

import java.util.concurrent.ThreadPoolExecutor;

public interface RejectedExecutionHandlerCode {
    void rejectedExecution(Runnable r, ThreadPoolExecutorCode executor);
}
