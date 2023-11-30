package org.dp.core.factory;

/**
 * 任何可以产生对象的方法或类，都可以称之为工厂
 * 单例也是一种工厂
 */
public class Factory {
    public static void main(String[] args) {
        Goable go = new Cat();
        go.go();
    }
}
