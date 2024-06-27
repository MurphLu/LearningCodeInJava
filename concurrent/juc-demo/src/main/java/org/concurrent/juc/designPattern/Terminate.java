package org.concurrent.juc.designPattern;

public class Terminate {
    volatile boolean isTerminated = false;
    boolean started;
    Thread thread;

    public static void main(String[] args) throws InterruptedException {
        Terminate t = new Terminate();
        t.start();
        Thread.sleep(5000);
        t.stop();
    }

    synchronized void start() {
        if (started) {
            return;
        }
        started = true;
        thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted() && !isTerminated) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                report();
            }
        });
        thread.start();
    }

    synchronized void stop(){
        isTerminated = true;
        thread.interrupt();
    }

    private void report(){
        System.out.println("采集信息");
    }
}
