package org.dp.core.factory.abstractFactory;

import org.dp.core.factory.abstractFactory.bean.Food;
import org.dp.core.factory.abstractFactory.bean.Vehicle;
import org.dp.core.factory.abstractFactory.bean.Weapon;

public class Main {
    public static void main(String[] args) {
        AbstractFactory factory = new ModernFactory();
        Vehicle v = factory.createVehicle();
        Weapon w = factory.createWeapon();
        Food f = factory.createFood();
    }
}
