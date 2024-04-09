package org.concurrent.juc.sync;

public class Intro {
    public static void main(String[] args) throws InterruptedException {
        syncDemo();
    }

    private static int counter = 0;
    private static void increment(){
        synchronized (lock){
            counter++;
        }

    }

    private static void decrement(){
        synchronized (lock) {
            counter--;
        }
    }

    static final Object lock = "";
    private static void syncDemo() throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                increment();

            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                decrement();

            }
        });
        t1.start();
        t2.start();
        do {
            //            break;
        } while (t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED);
        System.out.println(counter);
    }
}
