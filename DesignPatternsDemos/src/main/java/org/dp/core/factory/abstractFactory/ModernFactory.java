package org.dp.core.factory.abstractFactory;

import org.dp.core.factory.abstractFactory.bean.*;

public class ModernFactory extends AbstractFactory{
    @Override
    Vehicle createVehicle() {
        return new Car();
    }

    @Override
    Food createFood() {
        return new Candy();
    }

    @Override
    Weapon createWeapon() {
        return new Gun();
    }
}
