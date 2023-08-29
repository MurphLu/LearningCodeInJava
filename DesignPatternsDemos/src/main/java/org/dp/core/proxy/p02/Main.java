package org.dp.core.proxy.p02;

public class Main {
    public static void main(String[] args) {
        Movable proxy = new MovableLogProxy(new MovableTimeProxy(new Tank()));
        proxy.move();
    }
}

class Tank implements Movable {
    @Override
    public void move() {
        System.out.println("move....");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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

class MovableLogProxy implements Movable {
    Movable m;

    public MovableLogProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("start....");
        m.move();
        System.out.println("end....");
    }
}

interface Movable {
    void move();
}