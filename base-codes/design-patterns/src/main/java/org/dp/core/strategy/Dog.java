package org.dp.core.strategy;

public class Dog implements Comparable<Dog>{
    int weight;

    public Dog(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Dog o) {
        if(this.weight > o.weight) { return 1; }
        else if(this.weight < o.weight) { return -1; }
        return 0;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "weight=" + weight +
                '}';
    }
}
