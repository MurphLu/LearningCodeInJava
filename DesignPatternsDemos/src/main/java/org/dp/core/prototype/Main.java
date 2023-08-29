package org.dp.core.prototype;

// clone
// 实现 cloneable
// super.clone, 直接将对象内存内容直接复制一份，并返回复制内容的指针，如果对象中有引用类型的属性，那么在clone方法中需要对引用类型进行克隆
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p = new Person();
        Person p2 = (Person)p.clone();
        System.out.println(p);
        System.out.println(p2);

    }
}

class Person implements Cloneable {
    int age = 10;
    int score = 90;

    Location loc = new Location(0,0);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person p = (Person)super.clone();
        p.loc = (Location)loc.clone();
        return p;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", score=" + score +
                ", loc=" + loc +
                '}';
    }
}

class Location implements Cloneable{
    double lat;
    double lan;
    public Location(double lat, double lan) {
        this.lat = lat;
        this.lan = lan;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}