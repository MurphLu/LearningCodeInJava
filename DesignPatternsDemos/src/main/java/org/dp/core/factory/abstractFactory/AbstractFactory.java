package org.dp.core.factory.abstractFactory;

import org.dp.core.factory.abstractFactory.bean.Food;
import org.dp.core.factory.abstractFactory.bean.Vehicle;
import org.dp.core.factory.abstractFactory.bean.Weapon;

public abstract class AbstractFactory {
    abstract Vehicle createVehicle();
    abstract Food createFood();
    abstract Weapon createWeapon();
}
