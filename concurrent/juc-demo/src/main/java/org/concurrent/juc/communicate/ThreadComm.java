package org.concurrent.juc.communicate;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.locks.LockSupport;

public class ThreadComm {
    public static void main(String[] args) throws InterruptedException, IOException {
//        withVolatile();
//        waitDemo();
//        lockParkDemo();
        withPipe();
    }


    static volatile boolean flag = true;

    public static void withVolatile() {
        new Thread(()->{
            while (true) {
                if (flag) {
                    System.out.println("turn off");
                    flag = false;

                }
            }
        }).start();

        new Thread(()-> {
            while (true) {
                if (!flag) {
                    System.out.println("turn on");
                    flag = true;
                }
            }
        }).start();
    }


    private static Object lock = new Object();
    public static void waitDemo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    while (flag) {
                        try{
                            System.out.println("wait start....");
                            lock.wait();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    System.out.println("wait end....");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (flag) {
                    synchronized (lock) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        lock.notify();
                        System.out.println("notify ....");
                        flag = false;
                    }
                }
            }
        }).start();
    }


    public static void lockParkDemo() throws InterruptedException {

        Thread parkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("park before");
                LockSupport.park();
                System.out.println("park after");
            }
        });
        parkThread.start();
        Thread.sleep(1000);
        LockSupport.unpark(parkThread);
    }


    public static void withPipe() throws IOException {
        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter();

        out.connect(in);

        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        int i;
                        if ((i = in.read()) != -1) {
                            System.out.println((char)i);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        printThread.start();
        int receive;
        try{
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        }finally {
            out.close();
        }

    }
}
