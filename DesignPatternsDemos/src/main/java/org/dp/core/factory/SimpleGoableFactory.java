package org.dp.core.factory;

/**
 * 简单工厂
 * 可扩展性不好
 * 生产任意 goable
 */
public class SimpleGoableFactory {
    public Goable createCat() {
        System.out.println("cat created");
        return new Cat();
    }

    public Goable createTruck() {
        System.out.println("Truck created");
        return new Truck();
    }
}
