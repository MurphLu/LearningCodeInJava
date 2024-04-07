package org.concurrent.juc.threadPool;

import java.util.Date;

public class Task implements Runnable{
    String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " name " + new Date().getSeconds());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
