package org.dp.core;

import org.dp.core.singleton.Singleton01;
import org.dp.core.singleton.Singleton03;
import org.dp.core.strategy.Comparator;
import org.dp.core.strategy.Dog;
import org.dp.core.strategy.Sorter;
import org.dp.core.strategy.StrategySorter;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        strategyTest();
    }

    public static void singletonTest() {
        Singleton03.test();
    }

    public static void strategyTest() {
        StrategySorter<Dog> sorter = new StrategySorter<>();
        Dog[] dogs = new Dog[]{new Dog(2), new Dog(1), new Dog(3)};
        sorter.sort(dogs, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }
        });
        System.out.println(Arrays.toString(dogs));
    }
}
