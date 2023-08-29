package org.dp.core.proxy.p03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Tank tank = new Tank();

        Movable m = (Movable)Proxy.newProxyInstance(
                Tank.class.getClassLoader(),
                new Class[]{Movable.class},
                new LogHandler(tank)
        );

        m.move();


        Movable m2 = (Movable)Proxy.newProxyInstance(
                Tank.class.getClassLoader(),
                new Class[]{Movable.class},
                new ImproveLogHandler(tank)
        );
        m2.move();
    }
}


class LogHandler implements InvocationHandler {

    Tank tank;

    public LogHandler(Tank tank) {
        this.tank = tank;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method " + method.getName() + " start");
        Object o = method.invoke(tank, args);
        System.out.println("method " + method.getName() + " end");
        return o;
    }
}

class ImproveLogHandler implements InvocationHandler {

    Object obj;

    public ImproveLogHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method " + method.getName() + " start");
        Object o = method.invoke(obj, args);
        System.out.println("method " + method.getName() + " end");
        return o;
    }
}


class Tank implements Movable{
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

interface Movable {
    public void move();
}