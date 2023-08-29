package org.dp.core.proxy.p01;

public class Tank implements Movable{

    public static void main(String[] args) {
        new Tank().move();
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("move....");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("process take:" + (end - start));
    }
}

class MovableTimeProxy implements Movable {
    Movable m;

    public MovableTimeProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        m.move();
        long end = System.currentTimeMillis();
        System.out.println("process take:" + (end - start));
    }
}

interface Movable {
    void move();
}