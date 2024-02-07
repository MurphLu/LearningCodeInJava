package org.base.map.generic;

public class ChildClass extends BaseClass<String, Gc1>{
    public ChildClass() {
        this.s = new Gc1();
        this.t = "s";
    }
}
